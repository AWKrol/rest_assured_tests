FROM maven:3

USER root

RUN mkdir -p /home/ubuntu/api-tests
WORKDIR /home/ubuntu/api-tests

COPY . .


ENTRYPOINT ["./entrypoint.sh"]