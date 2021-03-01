package com.teikametrics.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teikametrics.common.BaseController;
import com.teikametrics.common.BaseResponse;
import com.teikametrics.common.Constants;
import com.teikametrics.response.github.GitHubEventsResponse;

@RestController
@CrossOrigin
@RequestMapping("/data_ingestion/github/oauth")

public class GitHubOAuthController  extends BaseController{
	
	@RequestMapping(value = "/v1/callback", method = RequestMethod.GET)
	public ResponseEntity<BaseResponse> gitHubOAuthCallBack(HttpServletRequest httpRequest,
			HttpServletResponse httpResponse) {
		BaseResponse response = new GitHubEventsResponse();
		try {
			return new ResponseEntity<BaseResponse>(response, HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block

			response = constructResponse(response, Constants.FAILURE, Constants.FAILURE_GIT_HUB_EVENTS_FETCH,
					e.getMessage(), Constants.FAILURE_DURING_GET);
			return new ResponseEntity<BaseResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}


}
