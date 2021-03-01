package com.teikametrics.response.events;

import java.util.List;

import com.teikametrics.common.BaseResponse;
import com.teikametrics.events.EventProcessingCommonHourVo;

public class EventProcessingCommonHourResponse extends BaseResponse{
	
	private List<EventProcessingCommonHourVo>data;

	public List<EventProcessingCommonHourVo> getData() {
		return data;
	}

	public void setData(List<EventProcessingCommonHourVo> data) {
		this.data = data;
	}

}
