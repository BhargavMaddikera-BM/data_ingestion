package com.teikametrics.response.events;

import java.util.Map;

import com.teikametrics.common.BaseResponse;

public class EventProcessingCommonWordsInCommitResponse extends BaseResponse{
	
	private Map<String,Integer> data;

	public Map<String, Integer> getData() {
		return data;
	}

	public void setData(Map<String, Integer> data) {
		this.data = data;
	}
}
