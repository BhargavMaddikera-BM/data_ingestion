package com.teikametrics.response.externalintegration;

import java.util.Map;

import com.teikametrics.common.BaseResponse;

public class EcommerceAndAdvertisingCommonWordsInCommitResponse extends BaseResponse{
	
	private Map<String,Integer> data;

	public Map<String, Integer> getData() {
		return data;
	}

	public void setData(Map<String, Integer> data) {
		this.data = data;
	}
}
