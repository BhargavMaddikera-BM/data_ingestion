package com.teikametrics.response.externalintegration;

import java.util.List;

import com.teikametrics.common.BaseResponse;
import com.teikametrics.externalintegration.github.GitHubVo;

public class GitHubEventsResponse extends BaseResponse{
	
	private List<GitHubVo> data;

	public List<GitHubVo> getData() {
		return data;
	}

	public void setData(List<GitHubVo> data) {
		this.data = data;
	}

}