FROM frolvlad/alpine-oraclejdk8:slim
ADD ["target/img-bank-1.0-SNAPSHOT.jar", "app.jar"]
EXPOSE 8080 8080
ENV JAVA_OPTS="-Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8787,suspend=n"
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=docker -Dspring.datasource.url=jdbc:postgresql://mypostgres:5432/mydb -jar /app.jar" ]
