package com.teikametrics.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

import com.teikametrics.EventProcessingService;
import com.teikametrics.common.BaseController;
import com.teikametrics.common.BaseResponse;
import com.teikametrics.common.Constants;
import com.teikametrics.events.EventProcessingCommonHourVo;
import com.teikametrics.github.GitHubVo;
import com.teikametrics.response.events.EventProcessingCommonHourResponse;
import com.teikametrics.response.events.EventProcessingCommonWordsInCommitResponse;
import com.teikametrics.response.events.EventProcessingConsumeResponse;
import com.teikametrics.response.events.EventProcessingPublishResponse;

@RestController
@CrossOrigin
@RequestMapping("/data_ingestion/events")
public class EventProcessingController extends BaseController {

	private Logger logger = Logger.getLogger(EventProcessingController.class);

	@Autowired
	EventProcessingService eventProcessingService;

	@RequestMapping(value = "/v1/publish/{number}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> publish(HttpServletRequest httpRequest, HttpServletResponse httpResponse,
			@PathVariable String number) {
		logger.info("Entry into method:publish");
		BaseResponse response = new EventProcessingPublishResponse();
		try {
			List<GitHubVo> data = eventProcessingService.publish(number);
			((EventProcessingPublishResponse) response).setData(data);
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

	@RequestMapping(value = "/v1", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> fetchAllEvents(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		logger.info("Entry into method:fetchAllEvents");
		BaseResponse response = new EventProcessingConsumeResponse();
		try {
			Map<String, GitHubVo> data = eventProcessingService.fetchAllEvents();
			((EventProcessingConsumeResponse) response).setData(data);
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

	@RequestMapping(value = "/v1/{startpos}/{endpos}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> fetchEventsByRange(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse, @PathVariable String startpos, @PathVariable String endpos) {
		logger.info("Entry into method:fetchEventsByRange");
		BaseResponse response = new EventProcessingConsumeResponse();
		try {
			Map<String, GitHubVo> data = eventProcessingService.fetchEventsByRange(Integer.parseInt(startpos),
					Integer.parseInt(endpos));
			((EventProcessingConsumeResponse) response).setData(data);
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/v1/commit/common_hour", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> fetchCommitedEventsByCommonHourOfDay(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		logger.info("Entry into method:fetchCommitedEventsByCommonHourOfDay");
		BaseResponse response = new EventProcessingCommonHourResponse();
		try {
			Map<String,Integer> data=eventProcessingService.fetchCommitedEventsByCommonHourOfDay();
			List<EventProcessingCommonHourVo>finalData=new ArrayList<EventProcessingCommonHourVo>();
			int i=0;
			int value=0;
			Iterator it=data.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String,Integer> entry=(Map.Entry<String,Integer>)it.next();
				if(i==0 || entry.getValue()==value){
					EventProcessingCommonHourVo eventProcessingCommonHourVo=new EventProcessingCommonHourVo();
					eventProcessingCommonHourVo.setHour(entry.getKey());
					eventProcessingCommonHourVo.setNumberOfCommit(entry.getValue());
					if(i==0){
						value=eventProcessingCommonHourVo.getNumberOfCommit();
					}
					finalData.add(eventProcessingCommonHourVo);
				}
				++i;
			}			
			((EventProcessingCommonHourResponse) response).setData(finalData);
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


	@RequestMapping(value = "/v1/commit/common_word", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> fetchCommonWordsInCommitMessage(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		logger.info("Entry into method:fetchCommonWordsInCommitMessage");
		BaseResponse response = new EventProcessingCommonWordsInCommitResponse();
		try {
			Map<String,Integer> data=eventProcessingService.fetchCommonWordsInCommitMessage();
			Map<String,Integer> finalData=new LinkedHashMap<String,Integer>();
			Iterator<Entry<String, Integer>> it=data.entrySet().iterator();
			int i=0;
			while(it.hasNext()){
				if(i==5){
					break;
				}
				Map.Entry<String, Integer> entry=(Map.Entry<String, Integer>)it.next();
				String key=entry.getKey();
				int value=entry.getValue();				
				finalData.put(key,value);
				++i;
			}
			((EventProcessingCommonWordsInCommitResponse) response).setData(finalData);
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
