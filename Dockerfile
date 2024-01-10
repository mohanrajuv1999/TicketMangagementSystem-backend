FROM openjdk:17

COPY target/*.jar ticketmanagement.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","/ticketmanagement.jar" ]