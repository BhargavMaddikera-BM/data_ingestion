This is an Usability Guide of how to use the Data Ingestion System

a) **Prerequisites - The following are required before the Application is launched:**

1) Java 8(minimum)

2) Install Apache Zookeeper 3.6.2. Go to /zookeeper/apache-zookeeper-3.6.2/bin and execute zkServer. Also set ZOOKEEPER_HOME variable in Environment Settings of the OS

3) Install Apache Kafka 2.7. Go to /kafka/kafka_2.12-2.7.0 and execute .\bin\windows\kafka-server-start.bat .\config\server.properties

4) Create Folder /data_ingestion/logs for logging(Log4j is used), though it will get automatically created, good to create them manually before hand

5) Create app_config.properties in /data_ingestion/config folder and set the following properties
	- github_events_topic=github_events_topic
	- github_events_url=https://api.github.com/events?per_page=
In the event app_config.properties is not there, they would default to github_events_topic and https://api.github.com/events?per_page= for github_events_topic and github_events_url respectively.

6) Install Maven 3.6.3


b) **Steps to Launch the Application:**

1) Take the code from GIT - https://github.com/BhargavMaddikera-BM/data_ingestion.git master branch

2) Go to data_ingestion folder and run mvn clean install -DskipTests to generate target classes to launch the application

3) Go to /app-web/target

4) execute java -jar app-web-0.0.1-SNAPSHOT.jar com.teikametrics.AppWebApplication


c) **Projects/Components Structure:**

1) app-web: 

       - It has all the Controllers, request,response,filter. This is basically the web component
	   
2) app-service:
 
       - It has all the Business Tier code and logic. 
	   - This is also the place where Kafka Messages are consumed by a Custom Defined Thread. 
	   - Also, it holds the Cache Service. for now, static variable is used as Cache. In real time scenarios, will be using Hazelcast-in
         memory data grid, the implementation of which will then go inside app-dao.
		 
3) app-dao: 

       - This has all the Database related code. Though Kafka is not meant for Storage unlike SQL and NoSQL Databases, here Kafka is 
	     treated as a Data Store as Data resides in Kafka Partitions until its consumed. Kafka Producer is in app-dao component.
		 
4) app-vo:

	   - This is the place that has all the Value Objects. Any interaction between the layers has to be via Custom Defined Value Objects
	   
5) app-ulity: 

	   - It has Custom Defined Exceptions both Checked and Runtime as well as Date utilities.
	   
6) app-ext-intg: 

	   - Any External System Integration has to happen via this component. since GitHub is an external system, any calls and interaction 
	     happens in this layer.


d) **Others:**

1) No particular/specific repository is considered for now w.r.t fetching events.

2) https://api.github.com/events?per_page= is invoked that fetches events from all repositories.
