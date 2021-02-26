This is an Usability Guide of how to use the Data Ingestion System

Prerequisites:

The following are required before the Application is launched:

1)Java 8(minimum)
2)Install Apache Zookeeper 3.6.2 and launch the same - /zookeeper/apache-zookeeper-3.6.2/bin execute zkServer
3)Install Apache Kafka 2.7 and launch the same - ./bin/windows/kafka-server-start.bat ./config/server.properties
4)Create Folder /data_ingestion/logs for logging(Log4j is used), though it will get automatically created, good to create them manually before hand
5)Create app_config.properties in /data_ingestion/config and set the following:
	- github_events_topic=github_events_topic
	- github_events_url=https://api.github.com/events?per_page=
In the event app_config.properties is not there, they would default to github_events_topic and https://api.github.com/events?per_page= for topic and url respectively.
6)Install Maven.Goto the code data_ingestion and run mvn clean install -DskipTests to generate target classes to launch the application


How to Launch the Application:

Once you take the code from GIT - https://github.com/BhargavMaddikera-BM/data_ingestion.git master branch:

1) goto /app-web/target
2) execute java -jar app-web-0.0.1-SNAPSHOT.jar com.teikametrics.AppWebApplication

