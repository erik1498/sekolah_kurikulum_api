FROM openjdk:11
COPY ./target/sekolah_docker.jar sekolah_docker.jar
CMD ["java", "-jar", "./sekolah_docker.jar"]