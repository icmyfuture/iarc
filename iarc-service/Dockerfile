FROM frolvlad/alpine-oraclejdk8:slim
ADD ./target/iarc-service-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.war"]
