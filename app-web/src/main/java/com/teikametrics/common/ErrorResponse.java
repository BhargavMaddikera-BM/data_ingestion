package com.teikametrics.common;

public class ErrorResponse extends BaseResponse {
	private ErrorVo data;

	public ErrorVo getData() {
		return data;
	}

	public void setData(ErrorVo data) {
		this.data = data;
	}

}
