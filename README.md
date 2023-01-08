# TheGameNotification- A Spring boot standalone notification service.

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

![alt text](https://github.com/313hemant313/TheGameNotification/blob/main/TheGameNotification.png?raw=true)


TheGameNotification uses a number of projects to work properly:

* [Spring Boot](https://spring.io/projects/spring-boot) - Open source Java-based framework
* [Maven](https://maven.apache.org/what-is-maven.html) Maven is a powerful project management tool that is based on POM (project object model)
* [Postgresql](https://www.postgresql.org/) - The World's Most Advanced Open Source Relational Database.
* [Hibernate](https://hibernate.org/) - More than an ORM
* [HikariCP](https://github.com/brettwooldridge/HikariCP) - HikariCP is a "zero-overhead" production ready JDBC connection pool
* [Apache Kafka](https://kafka.apache.org) - Apache Kafka is an open-source distributed event streaming platform used by thousands of companies for high-performance data pipelines, streaming analytics, data integration, and mission-critical applications.

### Prerequisites
The following items should be installed in your system:
* Java 18 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE
    * [IntelliJ IDEA](https://www.jetbrains.com/idea/)
    * [Eclipse](https://www.eclipse.org)
    * [Spring Tools Suite](https://spring.io/tools) (STS)
    * [VS Code](https://code.visualstudio.com)

### Supported channels

* SMS
```sh
Using AWS Pinpoint api, to add any other vendor api just create a service and autowire in SmsNotificationService.class
```
* WHATSAPP
```sh
Using Facebook whatsapp api, to add any other vendor api just create a service and autowire in WhatsappNotificationService.class
```
* EMAIL
```sh
Using AWS Pinpoint api, to add any other vendor api just create a service and autowire in EmailNotificationService.class
```

### Usage
Add dependency in pom.xml
```
<dependency>
  <groupId>tech.thegamedefault</groupId>
  <artifactId>the-game-notification</artifactId>
  <version>1.0.0</version>
</dependency>
```
Required configurations
- POSTGRESQL_URL
- POSTGRESQL_USERNAME
- POSTGRESQL_PASSWORD
- KAFKA_BOOTSTRAP_SERVERS
- PINPOINT_ACCESS_KEY
- PINPOINT_ACCESS_SECRET
- PINPOINT_REGION
- PINPOINT_PROJECT_ID
- WHATSAPP_URL
- WHATSAPP_PHONE_NUMBER_ID
- WHATSAPP_ACCOUNT_ID
- WHATSAPP_ACCESS_TOKEN

### Class Diagram
![alt text](https://github.com/313hemant313/TheGameNotification/blob/main/ClassDiagram.png?raw=true)

### Exposed Http Endpoints

| Feature                                   | Route |
|-------------------------------------------| ------ |
| Send any type of notification             | [ /notification/send ] |

### Kafka Consumer Payload for different type of channels
```sh
{
  "sms": {
    "appId": "TheGameDefault.TECH",
    "notificationType": "SMS",
    "message": "This is a test message from TheGameNotification service.",
    "destinations": [
      "+919XXXXXXXXX"
    ]
  },
  "whatsapp": {
    "appId": "TheGameDefault.TECH",
    "notificationType": "WHATSAPP",
    "message": "This is a test message from TheGameNotification service.",
    "destinations": [],
    "destination": "919XXXXXXXXX"
  },
  "email": {
    "appId": "TheGameDefault.TECH",
    "notificationType": "EMAIL",
    "subject": "Test email from notification service",
    "message": "This is a test message from TheGameNotification service.",
    "htmlBody": null,
    "destinations": [
      "+919XXXXXXXXX"
    ]
  }
}
```

### To Run
```sh
./mvnw spring-boot:run
```
### To Build
```sh
./mvnw clean install
```

### To run via docker compose [Not tested]
```sh
docker compose up --build
```