package com.teikametrics.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teikametrics.ApplicationRuntimeException;

public abstract class BaseController {
	
	public BaseResponse constructResponse(BaseResponse baseResponse,String status,String description,String message,String code)
	{
		baseResponse.setResponseStatus(status);
		baseResponse.setResponseDescription(description);
		baseResponse.setResponseCode(code);
		baseResponse.setResponseMessage(message);
		//baseResponse.setStatus(baseResponse.getResponseStatus());
		return baseResponse;
	}
	
	public String generateRequestAndResponseLogPaylod(Object o)throws ApplicationRuntimeException{
		try {
			ObjectMapper Obj = new ObjectMapper();
			String jsonStr;		
			jsonStr = Obj.writeValueAsString(o);
			return jsonStr;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			throw new ApplicationRuntimeException(e);
		}		
	}
	

}