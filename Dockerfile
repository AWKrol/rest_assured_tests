FROM maven:3-openjdk-18

USER root

RUN mkdir -p /home/ubuntu/api-tests
WORKDIR /home/ubuntu/api-tests

COPY . .

RUN mvn test
#ENTRYPOINT ["./entrypoint.sh"]