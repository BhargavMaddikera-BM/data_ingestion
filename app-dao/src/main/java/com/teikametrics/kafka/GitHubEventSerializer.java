package com.teikametrics.kafka;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teikametrics.github.GitHubVo;

public class GitHubEventSerializer implements Serializer<GitHubVo> {

	@Override
	public void close() {

	}
	
	@Override
	public byte[] serialize(String arg0, GitHubVo arg1) {
		// TODO Auto-generated method stub
		byte[] retVal = null;
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			retVal = objectMapper.writeValueAsString(arg1).getBytes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

}
