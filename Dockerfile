FROM openjdk:8

#COPY target/spring-boot-docker-app.jar  /usr/app/
COPY target/sb-co-api.jar  sb-co-api.jar

#WORKDIR /usr/app/

#ENTRYPOINT ["java", "-jar", "spring-boot-docker-app.jar"]
ENTRYPOINT ["java", "-jar", "sb-co-api.jar"]
