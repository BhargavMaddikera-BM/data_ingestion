package com.teikametrics.response.externalintegration;

import java.util.List;

import com.teikametrics.common.BaseResponse;
import com.teikametrics.externalintegration.github.EcommerceAndAdvertisingCommonHourVo;

public class EcommerceAndAdvertisingCommonHourResponse extends BaseResponse{
	
	private List<EcommerceAndAdvertisingCommonHourVo>data;

	public List<EcommerceAndAdvertisingCommonHourVo> getData() {
		return data;
	}

	public void setData(List<EcommerceAndAdvertisingCommonHourVo> data) {
		this.data = data;
	}

}
