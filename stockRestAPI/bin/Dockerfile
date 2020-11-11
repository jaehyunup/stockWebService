FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ARG JAR_FILE=target/stockRestAPI-0.0.1-RELEASE.war
ADD ${JAR_FILE} stockRestAPIserver.war
ENTRYPOINT ["java","-jar","stockRestAPIserver.war"]