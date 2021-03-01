package com.teikametrics.response.events;

import java.util.Map;

import com.teikametrics.common.BaseResponse;
import com.teikametrics.github.GitHubVo;

public class EventProcessingConsumeResponse extends BaseResponse{
	
	private Map<String,GitHubVo> data;

	public Map<String, GitHubVo> getData() {
		return data;
	}

	public void setData(Map<String, GitHubVo> data) {
		this.data = data;
	}

}
