FROM openjdk:11
EXPOSE 8080
ADD /build/libs/delivery-service-0.0.1-SNAPSHOT.jar deliveryservice.jar
ENTRYPOINT ["java", "-jar", "deliveryservice.jar"]