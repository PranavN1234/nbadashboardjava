FROM openjdk:17

COPY target/nbadashboardjava-0.0.1-SNAPSHOT.jar nbadashboardjava-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/nbadashboardjava-0.0.1-SNAPSHOT.jar"]