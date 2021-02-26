package com.teikametrics.externalintegration;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.teikametrics.ApplicationException;
import com.teikametrics.cache.GitHubCacheImpl;
import com.teikametrics.common.BaseService;
import com.teikametrics.externalintegration.github.GitHubVo;
import com.teikametrics.github.GitHubEvents;
import com.teikametrics.kafka.externalintegration.EcommerceAndAdvertisingDao;

@Service
public class EcommerceAndAdvertisingService extends BaseService{

	@Autowired
	EcommerceAndAdvertisingDao ecommerceAndAdvertisingDao;
	

	public List<GitHubVo> publish(String number)throws ApplicationException{
		List<GitHubVo> data=null;
		try {
			String events=GitHubEvents.getInstance().getEvents(number);			
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			data = objectMapper.readValue(events, TypeFactory.defaultInstance().constructCollectionType(List.class, GitHubVo.class));
			if(data!=null){
				for(int i=0;i<data.size();i++){
					ecommerceAndAdvertisingDao.publish(data.get(i));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(e);
		}
		return data;

	}
	
	
	public Map<String,GitHubVo> fetchAllEvents()throws ApplicationException{
		return GitHubCacheImpl.getInstance().getAllEvents();
	}
	
	public Map<String,GitHubVo> fetchEventsByRange(int startpos, int endpos)throws ApplicationException{
		return GitHubCacheImpl.getInstance().getAllEventsByRange(startpos,endpos);
	}

}