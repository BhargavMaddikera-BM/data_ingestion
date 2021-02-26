package com.teikametrics.response.externalintegration;

import java.util.Map;

import com.teikametrics.common.BaseResponse;
import com.teikametrics.externalintegration.github.GitHubVo;

public class EcommerceAndAdvertisingConsumeResponse extends BaseResponse{
	
	private Map<String,GitHubVo> data;

	public Map<String, GitHubVo> getData() {
		return data;
	}

	public void setData(Map<String, GitHubVo> data) {
		this.data = data;
	}

}
