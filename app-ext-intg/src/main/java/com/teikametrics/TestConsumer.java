package com.teikametrics;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class TestConsumer {
	
	   public static void main(String[] args) throws Exception {
		      
		      Properties props = new Properties();
		      props.put("bootstrap.servers", "localhost:9092");
		      props.put("group.id", "1");
		      props.put("enable.auto.commit", "true");
		      props.put("auto.commit.interval.ms", "1000");
		      props.put("session.timeout.ms", "30000");
		      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
		      
		      KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		      
		      consumer.subscribe(Arrays.asList("testkafka-bhargav"));
		      System.out.println("Subscribed to topic " + "testkafka-bhargav");
		      int i = 0;
		         
		      while (true) {
		         ConsumerRecords<String, String> records = consumer.poll(100);
		            for (ConsumerRecord<String, String> record : records)
		               System.out.printf("offset = %d, key = %s, value = %s\n", 
		               record.offset(), record.key(), record.value());
		      }     
		   }

}
