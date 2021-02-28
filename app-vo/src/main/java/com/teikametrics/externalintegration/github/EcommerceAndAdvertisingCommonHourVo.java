package com.teikametrics.externalintegration.github;

import com.teikametrics.common.BaseVo;

public class EcommerceAndAdvertisingCommonHourVo extends BaseVo{
	
	private String hour;
	private int numberOfCommit;
	public String getHour() {
		return hour;
	}
	public void setHour(String hour) {
		this.hour = hour;
	}
	public int getNumberOfCommit() {
		return numberOfCommit;
	}
	public void setNumberOfCommit(int numberOfCommit) {
		this.numberOfCommit = numberOfCommit;
	}

}
