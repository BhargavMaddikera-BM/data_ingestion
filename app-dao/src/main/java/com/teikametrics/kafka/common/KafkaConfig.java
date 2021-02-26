package com.teikametrics.kafka.common;

import java.io.FileReader;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import com.teikametrics.externalintegration.github.GitHubVo;

public class KafkaConfig {
	
	public static String getGithubEventsTopic() {
		return githubEventsTopic;
	}

	public static void setGithubEventsTopic(String githubEventsTopic) {
		KafkaConfig.githubEventsTopic = githubEventsTopic;
	}

	private static KafkaConfig kafkaConfig;
	
	private static String githubEventsTopic;
	
	private KafkaConfig(){
		
	}
	
	public static KafkaConfig getInstance(){
		if(kafkaConfig==null){
			kafkaConfig=new KafkaConfig();
			
			FileReader reader;
			try {
				reader = new FileReader("/data_ingestion/config/app_config.properties");
				Properties p=new Properties();  
				p.load(reader);  
				githubEventsTopic=p.getProperty("github_events_topic");
				if(githubEventsTopic==null){
					githubEventsTopic="github-events-topic";
				}
			} catch (Exception e) {
				githubEventsTopic="github-events-topic";
			}

		}
		return kafkaConfig;
	}

	public Producer<String, GitHubVo> createGitHubEventProducer(){
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");   
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer","com.teikametrics.kafka.common.GitHubEventSerializer");
		Producer<String, GitHubVo> producer = new KafkaProducer<String, GitHubVo>(props);
		return producer;

	}

	public KafkaConsumer<String, GitHubVo> createGitHubEventConsumer(){
		Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("group.id", "1");
		props.put("enable.auto.commit", "true");
		props.put("auto.commit.interval.ms", "1000");
		props.put("session.timeout.ms", "30000");
		props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "com.teikametrics.kafka.common.GitHubEventDeSerializer");
		KafkaConsumer<String, GitHubVo> consumer = new KafkaConsumer<String, GitHubVo>(props);
        consumer.subscribe(Collections.singletonList(githubEventsTopic));
		return consumer;

	}


}
