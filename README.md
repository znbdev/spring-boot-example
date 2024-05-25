spring-boot-example
-----
This is a springboot example.
这是一个 springboot 示例。

# How to run

### First find “Your Password” and change it to your own password.
- YourPassword => `spring.datasource.password=YourPassword`

### Run docker container

```bash
cd docker/mysql-phpmyadmin
docker-compose up -d
# OR
podman-compose up -d
```

### Build code

##### Gradle build

```bash
cd [target repository dir]
./gradlew clean build --refresh-dependencies --stacktrace
```

##### Maven build

```bash
cd [target repository dir]
mvn clean install -Dmaven.test.skip -Dcheckstyle.skip
```

### Run application on terminal or command line interface (CLI)

```bash
java -jar target/spring-boot-example-0.0.1-SNAPSHOT.jar --spring.profiles.active=local --server.port=8080
```

# Intellij IDEA

### Environment variables

`spring.profiles.active=local;server.port=8080`

# Note

### Dependency tree

```bash
./gradlew dependencies --configuration compileClasspath > tree.txt
# OR
mvn dependency:tree -Dverbose > tree.txt
```

# Author

* znb
* znbdev@outlook.com

# License

spring-boot-example is under [MIT license](https://en.wikipedia.org/wiki/MIT_License).

spring-boot-example is Confidential.
