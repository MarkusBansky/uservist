# open image for JRE 14
FROM adoptopenjdk:14-jre-openj9

# developer
MAINTAINER Markiian Benovskyi

# public env variables
ENV PROFILE prod
ENV EXECUTABLE uservist-service.jar

# create volume
RUN mkdir /uservist
VOLUME /uservist

# copy the latest jar file locally into docker container
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} ${EXECUTABLE}

# expose the port
EXPOSE 9090

# set entry point for java app
ENTRYPOINT ["/bin/bash", "-c", "java -Dspring.profiles.active=$PROFILE -jar $EXECUTABLE"]