package com.teikametrics.cache;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.teikametrics.ApplicationException;
import com.teikametrics.externalintegration.github.GitHubVo;



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


	public void addGitHubEvent(String id, GitHubVo data )throws ApplicationException{
		gitHubEventMap.put(id, data);
	}

	public void addGitHubEvents(List<GitHubVo> data )throws ApplicationException{
		for(int i=0;i<data.size();i++)
			gitHubEventMap.put(data.get(i).getId(), data.get(i));
	}

	public void removeGitHubEvent(String id)throws ApplicationException{
		if(gitHubEventMap.containsKey(id)){
			gitHubEventMap.remove(id);
		}
	}

	public void removeGitHubEvents(List<String> idList)throws ApplicationException{
		for(int i=0;i<idList.size();i++){
			if(gitHubEventMap.containsKey(idList.get(i))){
				gitHubEventMap.remove(idList.get(i));
			}
		}		
	}

	public void updateGitHubEvent(String id, GitHubVo data)throws ApplicationException{
		gitHubEventMap.put(id, data);
	}

	public void updateGitHubEvents(List<GitHubVo> data )throws ApplicationException{
		for(int i=0;i<data.size();i++)
			gitHubEventMap.put(data.get(i).getId(), data.get(i));
	}

	public GitHubVo getGitHubEvent(String id)throws ApplicationException{
		if(gitHubEventMap.containsKey(id)){
			return gitHubEventMap.get(id);
		}
		return null;
	}
	
	
	public Map<String,GitHubVo>  getAllEvents()throws ApplicationException{
		return gitHubEventMap;
	}
	
	@SuppressWarnings("rawtypes")
	public Map<String,GitHubVo>  getAllEventsByRange(int startpos, int endpos)throws ApplicationException{
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
}
