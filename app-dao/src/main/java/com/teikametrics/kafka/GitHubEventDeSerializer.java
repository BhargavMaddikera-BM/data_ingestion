package com.teikametrics.kafka;

import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teikametrics.github.GitHubVo;

public class GitHubEventDeSerializer implements Deserializer<GitHubVo>{

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GitHubVo deserialize(String arg0, byte[] arg1) {
		// TODO Auto-generated method stub
		ObjectMapper mapper = new ObjectMapper();
	    GitHubVo githubVo = null;
	    try {
	    	githubVo = mapper.readValue(arg1, GitHubVo.class);
	    } catch (Exception e) {

	      e.printStackTrace();
	    }
	    return githubVo;	}

}
