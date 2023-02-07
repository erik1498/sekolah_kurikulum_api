FROM openjdk:11
COPY ./target/sekolah_kurikulum_image.jar sekolah_kurikulum_image.jar
CMD ["java", "-jar", "./sekolah_kurikulum_image.jar"]