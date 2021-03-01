package com.teikametrics.util;

import java.time.Duration;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import com.teikametrics.ApplicationException;
import com.teikametrics.ApplicationRuntimeException;
import com.teikametrics.cache.GitHubCacheImpl;
import com.teikametrics.github.GitHubVo;
import com.teikametrics.kafka.KafkaConfig;


public class GitHubEventSubscriberThread extends Thread{	
	
	
	public GitHubEventSubscriberThread(){
	}

	public void run(){
		Consumer<String, GitHubVo> consumer=KafkaConfig.getInstance().createGitHubEventConsumer();
		while (true) {
			ConsumerRecords<String, GitHubVo> records = consumer.poll(Duration.ofSeconds(2));
			for (ConsumerRecord<String, GitHubVo> record : records)
			{
				try {
					GitHubCacheImpl.getInstance().addGitHubEvent(new Long(record.offset()).toString(), record.value());
				} catch (ApplicationException e) {
					// TODO Auto-generated catch block
					throw new ApplicationRuntimeException(e);
				}
			}
			
		}

	}

}
