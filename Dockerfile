# Menggunakan base image Python
FROM python:3.10-slim

ENV PYTHONUNBUFFERED True

# copy the local code to the container image
ENV APP_HOME /app
WORKDIR $APP_HOME
COPY  . .

# install all required packages specified in requirements.txt
RUN pip install -r requirements.txt

# run web service on container using gunicorn
CMD ["python", "main.py"]