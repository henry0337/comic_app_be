FROM eclipse-temurin:17-jdk-focal

WORKDIR /app

COPY gradlew local.properties build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY src ./src

RUN ./gradlew build -x test --no-daemon
RUN ./gradlew bootJar --no-daemon

CMD ["java", "-jar", "build/libs/ComicApp-1.0.1.jar"]