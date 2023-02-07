FROM openjdk:11-alpine
COPY ./target/sekolah_kurikulum_docker.jar sekolah_kurikulum_docker.jar
CMD ["java", "-jar", "./sekolah_kurikulum_docker.jar"]