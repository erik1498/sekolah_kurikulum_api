FROM openjdk:11
COPY ./target/sekolah-app-docker.jar sekolah-app-docker.jar
CMD ["java", "-jar", "./sekolah-app-docker.jar"]