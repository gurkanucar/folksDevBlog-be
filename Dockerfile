FROM openjdk:11
ARG JAR_FILE=target/folksdevblog-be-2.5.5.jar
ADD ${JAR_FILE} folksdevblog-be-2.5.5.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "folksdevblog-be-2.5.5.jar"]
