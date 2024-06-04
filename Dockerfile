FROM maven:3.9.7-sapmachine-22 AS build

WORKDIR /usr/app

COPY . .

RUN mvn clean package -DskipTests

FROM openjdk:23

WORKDIR /srv/red

COPY --from=build /usr/app/target/*.jar ./server.jar

EXPOSE 8080

CMD ["java","-jar","./server.jar"]