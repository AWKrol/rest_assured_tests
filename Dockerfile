FROM maven:3-openjdk-18

USER root

RUN mkdir -p /home/ubuntu/api-tests
WORKDIR /home/ubuntu/api-tests

COPY . .


ENTRYPOINT ["/bin/bash"]
CMD ["entrypoint.sh"]