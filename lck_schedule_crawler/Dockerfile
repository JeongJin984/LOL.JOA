# syntax=docker/dockerfile:1
FROM python:3
COPY . /app
WORKDIR /app
RUN pip install -r requirements.txt

CMD ["python3", "-u", "main2.py"]