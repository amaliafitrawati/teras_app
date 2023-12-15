import pandas as pd
import numpy as np
import math
from sklearn.preprocessing import MinMaxScaler
from keras.models import Sequential
from keras.layers import Dense, LSTM, Dropout
import warnings
warnings.filterwarnings('ignore')
import tensorflow as tf
from tensorflow import keras
from tensorflow.python.keras.models import Model
from tensorflow.keras.layers import BatchNormalization
from tensorflow.python.keras.layers import Input, Activation, Conv2D, MaxPooling2D, UpSampling2D, Lambda, \
Conv2DTranspose, Permute, advanced_activations, Add, LeakyReLU, Dropout, ActivityRegularization
from tensorflow.python.keras import regularizers
from tensorflow.python.keras import backend
from tensorflow.python.keras.callbacks import EarlyStopping
import json
import urllib
from flask import Flask, request, jsonify
from datetime import datetime, timedelta



class Result():

  def fetch_data_result(self):
    updated = "updated.csv"
    self.df_beras = pd.read_csv(updated)

  @staticmethod   
  def preprocess_new_data(df):
    grouped_df = df.drop(['UID'], axis=1)
    grouped_df = grouped_df.groupby(['Province']).sum()
    grouped_df.loc['Total'] = grouped_df.sum()
    grouped_df = grouped_df.T
    grouped_df.index = pd.to_datetime(grouped_df.index, format="%d/%m/%Y",infer_datetime_format=True)
    return grouped_df

  @staticmethod
  def daily_data(df):
        new_df = df.diff()
        new_df = new_df.iloc[1:, :]
        return new_df

if __name__ == "__main__":
    r = Result()
    r.fetch_data_result()
  
class inputBeras:
  def __init__(self):
    self.r = Result()
    self.Beras = self.r.preprocess_new_data(r.df_beras)
    self.Beras_process = self.berasProcess()
    self.X_train_list = self.splitData()

  def berasProcess(self):
    # input ke variable beras
    Beras = self.r.preprocess_new_data(r.df_beras)
    Beras_process = Beras.reset_index()

    Beras_process = Beras_process.rename(columns={'index': 'Date'})
    return Beras_process


  def splitData(self):
    # simpan data based on province ke closevalue_data dan bagi trainingdatalength 80% dri keseluruhan
    Beras_process = self.Beras_process
    training_data_lengths = []
    CloseValue_data = []
    for state in Beras_process.columns[1:]:
          Close = Beras_process.filter([state])
          CloseValue = Close.values
          TrainingDataLength = math.ceil(len(CloseValue)*.8)
          CloseValue_data.append(CloseValue)
          training_data_lengths.append(TrainingDataLength)
    CloseValue_data = np.array(CloseValue_data)
    # print(TrainingDataLength)
    # print(training_data_lengths)
    # print(CloseValue_data)

    # masukin closevalue_data ke berasdata_array
    BerasData_array = []

    scaler = MinMaxScaler(feature_range=(0, 1))

    for sample in CloseValue_data:
        # Reshape each sample assuming it's a 2D array (time_steps, features)
        reshaped_sample = sample.reshape(-1, sample.shape[-1])

        # Fit and transform each individual sample
        scaled_sample = scaler.fit_transform(reshaped_sample)

        # Reshape the scaled sample back to its original shape
        scaled_sample = scaled_sample.reshape(sample.shape)

        # Append the scaled sample to the BerasData_array
        BerasData_array.append(scaled_sample)

    BerasData_array = np.array(BerasData_array)

    # membuat x_train dan y_train tiap provinsi

    X_train_list = []  # A list to hold multiple X_train sets
    Y_train_list = []  # A list to hold corresponding Y_train sets

    for h in range(34):  # Assuming you have 34 elements in BerasData_array
        Backcandles = 15
        # len TrainData 46
        TrainData = BerasData_array[h][0:TrainingDataLength]

        X_train, Y_train = [], []  # Initialize X_train and Y_train for each iteration

        for i in range(Backcandles, len(TrainData)):
            X_train.append(TrainData[i - Backcandles:i, 0])

            Y_train.append(TrainData[i, 0])


            # if i <= Backcandles:
            #     print("X_train:", X_train, "\nY_train:", Y_train)


        X_train,Y_train = np.array(X_train), np.array(Y_train)
        X_train = X_train.reshape(X_train.shape[0], X_train.shape[1], 1)
        X_train_list.append(X_train)
        Y_train_list.append(Y_train)
        return X_train_list

class Regressor:
    def __init__(self, daily_df):
        self.b = inputBeras()
        self.Beras_process = daily_df
        self.daily_df = daily_df
        self.forecast_interval = 1
        self.next_year = None
        # self.model = self.load_model()

        # Load the pre-trained model
        self.model = self.load_model()

    def load_model(self):
        # Load your model from the saved .h5 file
        return keras.models.load_model("model.h5")

    @staticmethod
    def generate_dates(start, interval_forecast):
        index = pd.date_range(start, periods= 1  + 1, freq='Y')
        return index[1:]
    
    def run_predictions(self):
        next_year = []
        for state in self.Beras_process.columns[1:]:
            tempdf = self.Beras_process.set_index('Date')[state]
            state_reg = Regressor(daily_df=tempdf)
            lstm_state_data = state_reg.predicts(row=state, num_estimator=1)
            if (lstm_state_data['forecast'] == 0.0).any():
                next_year.append(lstm_state_data['interval_high'].iloc[-1])
            else:
                next_year.append(lstm_state_data['forecast'].iloc[-1])

        self.next_year = next_year
        return next_year
  

    def predicts(self, lookback=15, row='Total', num_estimator=10):
          train = self.daily_df
          train = np.array(train).reshape((-1, 1))
          scaler = MinMaxScaler()
          train = scaler.fit_transform(train)
          train = train.ravel()

          x_train = []
          for i in range(len(train) - lookback - 1 + 1):
              x = train[i:i + lookback]
              x_train.append(x)

          train_pred = []
          for i in range(len(train) - lookback + 1):
              train_pred.append(train[i:i + lookback])

          train_pred = np.array(x_train)
          train_pred = train_pred.reshape((train_pred.shape[0], train_pred.shape[1], 1))
          
          y_hat = []
          y_hat.append(scaler.inverse_transform(self.model.predict(train_pred)))

          y_hat = np.array(y_hat)
          y_hat = y_hat.reshape(-1, 1)
          latest_pred = scaler.inverse_transform(self.model.predict(train_pred[-1].reshape((1, train_pred.shape[1], 1))))
          ans = []
          for i in range(y_hat.shape[0]):
              temp = np.concatenate((np.array([np.nan] * i), y_hat[i],
                                    np.array([np.nan] * (y_hat.shape[0] - i + 1 - 1))), axis=0)
              ans.append(temp)

          ans.append(np.concatenate((np.array([np.nan] * (y_hat.shape[0] + 1 - 1)), latest_pred[0]),
                            axis=0))


          ans = np.array(ans)
          low, high = [], []
          pred = []
          for i in range(ans.shape[1]):
              low.append(max(np.nanmin(ans[:, i]), 0))
              high.append(np.nanmax(ans[:, i]))
              pred.append(max(np.nanmean(ans[:, i]), 0))

          forecasted_days = self.generate_dates(self.daily_df.index[-1], 1)
          preds = pd.DataFrame(data=pred, columns=["forecast"],
                              index=self.daily_df.index[lookback:].union(forecasted_days))
          interval_low = pd.DataFrame(data=low, columns=["interval_low"],
                                      index=self.daily_df.index[lookback:].union(forecasted_days))
          interval_high = pd.DataFrame(data=high, columns=["interval_high"],
                                      index=self.daily_df.index[lookback:].union(forecasted_days))

          daily_df = pd.concat([self.daily_df, preds, interval_low, interval_high], axis=1)
          # model.save("model_rice.h5")
          return daily_df
    # ... (rest of the class remains unchanged)

    # Create an instance of Regressor when the app starts
regressor_instance = Regressor(daily_df=None) 
app = Flask(__name__)
@app.route("/prediction", methods=["GET"])
def index():
        result = Result()
        result.fetch_data_result()

        b = inputBeras()
        processed_data = b.Beras_process

        # Use the pre-trained model for predictions
        regressor_instance = Regressor(daily_df=processed_data)
        next_year_predictions = regressor_instance.run_predictions()

        # Assuming next_year_predictions is a list of predictions
        # return jsonify({"predictions": next_year_predictions})
        predictions_with_province = [
        {"province": province, "prediction": prediction}
        for province, prediction in zip(processed_data.columns[1:], next_year_predictions)
        ]

        return jsonify(predictions_with_province)
if __name__ == "__main__":
  app.run(debug=True)
