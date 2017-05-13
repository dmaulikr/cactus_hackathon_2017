FROM python:3.6

RUN mkdir /cactus
ADD requirements.txt /cactus/requirements.txt
RUN pip install -r /cactus/requirements.txt

ADD ./cactus /cactus

WORKDIR /cactus
EXPOSE 8841

CMD python manage.py runserver 0.0.0.0:8841