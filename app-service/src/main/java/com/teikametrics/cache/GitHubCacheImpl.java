package com.teikametrics.cache;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.teikametrics.ApplicationException;
import com.teikametrics.DateHelper;
import com.teikametrics.github.GitHubPayloadCommitsVo;
import com.teikametrics.github.GitHubVo;



public class GitHubCacheImpl{

	private static GitHubCacheImpl cacheImpl;

	private GitHubCacheImpl(){

	}

	public static GitHubCacheImpl getInstance(){
		if(cacheImpl==null){
			cacheImpl=new GitHubCacheImpl();
		}
		return cacheImpl;
	}

	private Map<String,GitHubVo> gitHubEventMap=new ConcurrentHashMap<String,GitHubVo>();	
	private Map<String,List<String>> commonHourOfDayMap=new ConcurrentHashMap<String,List<String>>();
	private Map<String,List<String>>commitMessageMap=new ConcurrentHashMap<String,List<String>>();
	private Map<String,String>commonHourOfDayKeyRemovalMap=new ConcurrentHashMap<String,String>();


	public void addGitHubEvent(String id, GitHubVo data )throws ApplicationException{
		String currentHour=DateHelper.getInstance().getCurrentDateTillHour();
		if(commonHourOfDayMap.containsKey(currentHour)){
			List<String>offSetIdList=commonHourOfDayMap.get(currentHour);
			offSetIdList.add(id);

		}else{
			List<String>offSetIdList=new ArrayList<String>();
			offSetIdList.add(id);
			commonHourOfDayMap.put(currentHour, offSetIdList);
		}
		gitHubEventMap.put(id, data);
		commonHourOfDayKeyRemovalMap.put(id, currentHour);
		if(data.getPayload()!=null && data.getPayload().getCommits()!=null){
			if(data.getPayload().getCommits().size()>0){
				List<String> message=new ArrayList<String>();
				for(int i=0;i<data.getPayload().getCommits().size();i++){
					GitHubPayloadCommitsVo gitHubPayloadCommitsVo=data.getPayload().getCommits().get(i);
					message.add(gitHubPayloadCommitsVo.getMessage());
				}			
				commitMessageMap.put(id, message);
			}
		}
	}

	public void removeGitHubEvent(String id)throws ApplicationException{
		if(commitMessageMap.containsKey(id)){
			commitMessageMap.remove(id);
		}		
		if(commonHourOfDayKeyRemovalMap.containsKey(id)){
			String value=commonHourOfDayKeyRemovalMap.get(id);
			List<String>commonHourIdList=commonHourOfDayMap.get(value);
			if(commonHourIdList!=null && commonHourIdList.size()>0){
				String valueTobeRemoved=null;
				for(int i=0;i<commonHourIdList.size();i++){
					String offSetId=commonHourIdList.get(i);
					if(offSetId.equals(id)){
						valueTobeRemoved=id;
						if(valueTobeRemoved!=null){
							commonHourIdList.remove(valueTobeRemoved);
							commonHourOfDayMap.put(value, commonHourIdList);
							break;

						}
					}
				}
			}
			commonHourOfDayKeyRemovalMap.remove(id);
		}
		
		if(gitHubEventMap.containsKey(id)){
			gitHubEventMap.remove(id);
		}

	}

	public void updateGitHubEvent(String id, GitHubVo data)throws ApplicationException{
		gitHubEventMap.put(id, data);
	}

	public GitHubVo getGitHubEvent(String id)throws ApplicationException{
		if(gitHubEventMap.containsKey(id)){
			return gitHubEventMap.get(id);
		}
		return null;
	}


	public Map<String,GitHubVo>  getAllGitHubEvents()throws ApplicationException{
		return gitHubEventMap;
	}

	@SuppressWarnings("rawtypes")
	public Map<String,GitHubVo>  getAllGitHubEventsByRange(int startpos, int endpos)throws ApplicationException{
		Iterator it=gitHubEventMap.entrySet().iterator();
		Map<String,GitHubVo> gitHubEventMap_lc=new ConcurrentHashMap<String,GitHubVo>();
		int i=0;
		if(gitHubEventMap.size()<startpos){
			throw new ApplicationException("Total Events is less than Start Position");
		}

		if(gitHubEventMap.size()<endpos){
			throw new ApplicationException("Total Events is less than End Position");
		}		
		while(it.hasNext()){
			Map.Entry entry=(Map.Entry)it.next();
			String key=(String) entry.getKey();
			GitHubVo value=(GitHubVo) entry.getValue();
			if(i>=startpos && i<=endpos)
			{
				gitHubEventMap_lc.put(key, value);
			}
			if(i>endpos){
				break;
			}
			++i;
		}
		return gitHubEventMap_lc;
	}

	public Map<String, List<String>> getCommitMessageMap() {
		return Collections.unmodifiableMap(commitMessageMap);
	}
	public Map<String, List<String>> getCommonHourOfDayMap() {
		return Collections.unmodifiableMap(commonHourOfDayMap);
	}




}
