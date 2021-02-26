package com.teikametrics.github;

import java.io.FileReader;
import java.util.Properties;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.teikametrics.ApplicationException;

public class GitHubEvents {

	private static GitHubEvents gitHubEvents;
	private static String events_url;

	private GitHubEvents(){

	}

	public static GitHubEvents getInstance(){
		if(gitHubEvents==null){
			gitHubEvents=new GitHubEvents();
			FileReader reader;
			try {
				reader = new FileReader("/data_ingestion/config/app_config.properties");
				Properties p=new Properties();  
				p.load(reader);  
				events_url=p.getProperty("github_events_url");
				if(events_url==null){
					events_url="https://api.github.com/events?per_page=";
				}
			} catch (Exception e) {
				events_url="https://api.github.com/events?per_page=";
			}
		}
		return gitHubEvents;
	}

	public String getEvents(String page)throws ApplicationException{
		try{

			String url=events_url+page;
			WebTarget webTarget = ClientBuilder.newClient().target(url);
			Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON_TYPE);
			Response response = invocationBuilder.get();
			String str= response.readEntity(String.class);
			System.out.println(str);
			return str;			
		}catch(Exception e){
			throw new ApplicationException(e);
		}


	}

}
