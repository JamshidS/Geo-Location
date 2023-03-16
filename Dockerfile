FROM openjdk:19
EXPOSE 8080
ADD targer/geo-server.jar geo-server.jar
ENTRYPOINT ["java","jar","geo-server.jar"]