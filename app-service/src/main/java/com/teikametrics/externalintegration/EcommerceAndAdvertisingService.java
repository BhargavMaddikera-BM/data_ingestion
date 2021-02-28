package com.teikametrics.externalintegration;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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


	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String,Integer> fetchCommitedEventsByCommonHourOfDay()throws ApplicationException{
		Map<String, List<String>>data= GitHubCacheImpl.getInstance().getCommonHourOfDayMap();
		Map<String,Integer>data_lc=new HashMap<String,Integer>();
		Iterator it=data.entrySet().iterator();
		while(it.hasNext()){
			Map.Entry entry=(Map.Entry)it.next();
			String key=(String) entry.getKey();
			List<String>value=(List<String>) entry.getValue();
			data_lc.put(key, value.size());
		}		
		Map<String, Integer> finalMap=sortByComparator(data_lc,false);
		data_lc.clear();
		return finalMap;
	}


	private Map<String, Integer> sortByComparator(Map<String, Integer> unsortMap, final boolean order)
	{

		List<Entry<String, Integer>> list = new LinkedList<Entry<String, Integer>>(unsortMap.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>()
		{
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2)
			{
				if (order)
				{
					return o1.getValue().compareTo(o2.getValue());
				}
				else
				{
					return o2.getValue().compareTo(o1.getValue());

				}
			}
		});

		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Entry<String, Integer> entry : list)
		{
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;	    
	}



}