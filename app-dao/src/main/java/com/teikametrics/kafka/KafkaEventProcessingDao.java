package com.teikametrics.kafka;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Repository;

import com.teikametrics.ApplicationException;
import com.teikametrics.common.BaseDao;
import com.teikametrics.github.GitHubVo;


@Repository
public class KafkaEventProcessingDao extends BaseDao {

	
	public void publish(GitHubVo data)throws ApplicationException{

		Producer<String, GitHubVo> producer=KafkaConfig.getInstance().createGitHubEventProducer();
		producer.send(new ProducerRecord<String, GitHubVo>(KafkaConfig.getInstance().getGithubEventsTopic(),data));
		producer.close();
	}	


}
