package com.teikametrics.controller.externalintegration;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.teikametrics.common.BaseController;
import com.teikametrics.common.BaseResponse;
import com.teikametrics.common.Constants;
import com.teikametrics.externalintegration.github.GitHubVo;
import com.teikametrics.github.GitHubEvents;
import com.teikametrics.response.externalintegration.GitHubEventsResponse;

@RestController
@CrossOrigin
@RequestMapping("/data_ingestion/github/events")

public class GitHubController extends BaseController{


	private Logger logger = Logger.getLogger(GitHubController.class);	


	@RequestMapping(value = "/v1/pull/{page}", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> pullEvents(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse,@PathVariable String page) {
		logger.info("Entry into method:pullEvents");
		BaseResponse response = new GitHubEventsResponse();
		try {
			String events=GitHubEvents.getInstance().getEvents(page);			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			List<GitHubVo> data = objectMapper.readValue(events, TypeFactory.defaultInstance().constructCollectionType(List.class, GitHubVo.class));
			((GitHubEventsResponse) response).setData(data);
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
