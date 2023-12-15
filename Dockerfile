# Python image to use
FROM python:3.10-slim

ENV PYTHONUNBUFERED True

# Copy the local code to the container image
ENV APP_HOME /APP_HOME
WORKDIR $APP_HOME
COPY . ./

#Install all required packages specified in requirement.txt
RUN pip install -r requirement.txt

# Run web services on container using gunicorn
CMD exec gunicorn --bind :$PORT --workers 1 --threads 8 --timeout 0 main:app