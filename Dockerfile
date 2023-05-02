FROM openjdk:11

COPY ./src/main /app/src/main

WORKDIR /app

RUN javac src/main/*.java src/main/commands/*.java src/main/exceptions/*.java

CMD ["java", "-cp", "src", "main.Main"]
