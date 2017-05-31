# Example Supermarket Service

## Prerequisites

In order to build the project, you will have to install the following:

* [Java 8] (http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* This project includes the [Gradle Wrapper] (https://docs.gradle.org/current/userguide/gradle_wrapper.html) which will automatically downloaded and install the correct version of Gradle.

## Build

Gradle will automatically download all project dependencies on demand, so there is no specific commands to run.
Also, every build task is configured to run 'clean' by default.

### Build fat jar
To build a fat jar (jar + all dependencies), execute:

```
./gradlew build
```

### Docker build
```
docker build -t journal-metadata-service .
docker build -t journal-metadata-service:1.0.{VERSION_NUMBER} .
e.g: docker build -t journal-metadata-service:1.0.1 .
```

## Configuration

Configuration file 'application.yml' is present in config directory. It also holds the application.yml for all the environments.


## Running the app

### Gradle
```
./gradlew run
```

### Docker run
```
docker run -e ENVIRONMENT=${ENVIRONMENT} -p 8080:8080 -v `pwd`/logs/:/var/journal-metadata-service/logs/ journal-metadata-service
e.g: docker run -e ENVIRONMENT=local -p 8080:8080 -v `pwd`/logs/:/var/journal-metadata-service/logs/ journal-metadata-service
```

*Note*: Append the `-d` parameter to the run command if you want to run in detach mode (in the background).

## Testing
To run the unit tests, execute:

```
./gradlew unitTest
```

**Note**: Please note these will be run as part of the build anyway

To run the integration tests, execute:

```
./gradlew integrationTest
```

To run the sanity tests, execute:

```
./gradlew tests -Ptype=sanity -Purl=http://localhost:8080
```

To run the regression tests, execute:

```
./gradlew tests -Ptype=regression -Purl=http://localhost:8080
```

**Note 1**: type parameter is mandatory, values can be [sanity, regression]

**Note 2**: url parameter is mandatory, value must be in the format [PROTOCOL://HOST:PORT]

### From IntelliJ / Eclipse
You can run the Main method found under JournalMetadataApplication and add the following program arguments: ```server config/local/application.yml```

## Documentation
Once you run the application, the documentation of the API can be found at: http://localhost:8080/swagger