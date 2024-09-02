FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY applications/app-service/build/libs/GeolocalizacionIps.jar GeolocalizacionIps.jar
ENV JAVA_OPTS="  -XX:+UseContainerSupport -XX:MaxRAMPercentage=70 -Djava.security.egd=file:/dev/./urandom"
# Replace with a non-root user to avoid running the container with excessive privileges
ENTRYPOINT [ "sh", "-c", "sleep 15 && java $JAVA_OPTS  -jar GeolocalizacionIps.jar" ]
