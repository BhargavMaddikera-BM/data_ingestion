package com.teikametrics.controller.externalintegration;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teikametrics.common.BaseController;
import com.teikametrics.common.BaseResponse;
import com.teikametrics.common.Constants;
import com.teikametrics.externalintegration.EcommerceAndAdvertisingService;
import com.teikametrics.externalintegration.github.GitHubVo;
import com.teikametrics.response.externalintegration.EcommerceAndAdvertisingConsumeResponse;
import com.teikametrics.response.externalintegration.EcommerceAndAdvertisingPublishResponse;

@RestController
@CrossOrigin
//This will be part of Settings and Preferences. Hence sp.
@RequestMapping("/data_ingestion/ecommerce_advertising")
public class EcommerceAndAdvertisingController extends BaseController {

	private Logger logger = Logger.getLogger(EcommerceAndAdvertisingController.class);	

	@Autowired
	EcommerceAndAdvertisingService ecommerceAndAdvertisingService;

	@RequestMapping(value = "/v1/publish/events/{number}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> publish(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse,@PathVariable String number) {
		logger.info("Entry into method:publish");
		BaseResponse response = new EcommerceAndAdvertisingPublishResponse();
		try {
			List<GitHubVo> data=ecommerceAndAdvertisingService.publish(number);
			((EcommerceAndAdvertisingPublishResponse) response).setData(data);
			response = constructResponse(response, Constants.SUCCESS, Constants.SUCCESS_ECOM_ADV_PUBLISH,
					Constants.SUCCESS_ECOM_ADV_PUBLISH, Constants.SUCCESS_DURING_PUBLISH);
			logger.info("Response Payload:" + generateRequestAndResponseLogPaylod(response));
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			response = constructResponse(response, Constants.FAILURE, Constants.FAILURE_ECOM_ADV_PUBLISH,
					e.getMessage(), Constants.FAILURE_DURING_PUBLISH);
			logger.error("Error Payload:" + generateRequestAndResponseLogPaylod(response));
			return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}


	@RequestMapping(value = "/v1/events", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> fetchAllEvents(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		logger.info("Entry into method:fetchAllEvents");
		BaseResponse response = new EcommerceAndAdvertisingConsumeResponse();
		try {
			Map<String,GitHubVo> data=ecommerceAndAdvertisingService.fetchAllEvents();
			((EcommerceAndAdvertisingConsumeResponse) response).setData(data);
			response = constructResponse(response, Constants.SUCCESS, Constants.SUCCESS_GIT_HUB_EVENTS_FETCH,
					Constants.SUCCESS_GIT_HUB_EVENTS_FETCH, Constants.SUCCESS_DURING_GET);
			logger.info("Response Payload:" + generateRequestAndResponseLogPaylod(response));
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			response = constructResponse(response, Constants.FAILURE, Constants.FAILURE_GIT_HUB_EVENTS_FETCH,
					e.getMessage(), Constants.FAILURE_DURING_GET);
			logger.error("Error Payload:" + generateRequestAndResponseLogPaylod(response));
			return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@RequestMapping(value = "/v1/events/{startpos}/{endpos}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> fetchEventsByRange(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse,@PathVariable String startpos,@PathVariable String endpos) {
		logger.info("Entry into method:fetchEventsByRange");
		BaseResponse response = new EcommerceAndAdvertisingConsumeResponse();
		try {
				Map<String,GitHubVo> data=ecommerceAndAdvertisingService.fetchEventsByRange(Integer.parseInt(startpos),Integer.parseInt(endpos));
				((EcommerceAndAdvertisingConsumeResponse) response).setData(data);
				response = constructResponse(response, Constants.SUCCESS, Constants.SUCCESS_GIT_HUB_EVENTS_FETCH,
						Constants.SUCCESS_GIT_HUB_EVENTS_FETCH, Constants.SUCCESS_DURING_GET);
				logger.info("Response Payload:" + generateRequestAndResponseLogPaylod(response));
				return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block

			response = constructResponse(response, Constants.FAILURE, Constants.FAILURE_GIT_HUB_EVENTS_FETCH,
					e.getMessage(), Constants.FAILURE_DURING_GET);
			logger.error("Error Payload:" + generateRequestAndResponseLogPaylod(response));
			return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
