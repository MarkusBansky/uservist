# open image for JRE 14
FROM adoptopenjdk:14-jre-openj9

# developer
MAINTAINER Markiian Benovskyi

# environment variables
ENV DB_HOST 127.0.0.1
ENV DB_PORT 5432
ENV DB_PASSWORD postgres
ENV DB_USER postgres
ENV DB_NAME uservist
ENV DB_SCHEMA uservist

ENV MAIL_USERNAME gmail@gmail.com
ENV MAIL_PASSWORD 1234567890

# public variables
ENV PROFILE=prod

# create volume
RUN mkdir /uservist
VOLUME /uservist

# copy the latest jar file locally into docker container
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} uservist-service.jar

# expose the port
EXPOSE 9090

# set entry point for java app
ENTRYPOINT java -Dspring.datasource.url=jdbc:postgresql://${DB_HOST}:${DB_PORT}/${DB_NAME} -Dspring.datasource.username=${DB_USER} -Dspring.datasource.password=${DB_PASSWORD} -Dspring.datasource.jpa.properties.hibernate.default_schema=${DB_SCHEMA} -Dspring.mail.username=${MAIL_USERNAME} -Dspring.mail.password=${MAIL_PASSWORD} -jar uservist-service.jar