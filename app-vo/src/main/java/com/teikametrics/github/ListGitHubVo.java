package com.teikametrics.github;

import java.util.List;

import com.teikametrics.common.BaseVo;

public class ListGitHubVo extends BaseVo{
	
	private List<GitHubVo>gitHubs;

	public List<GitHubVo> getGitHubs() {
		return gitHubs;
	}

	public void setGitHubs(List<GitHubVo> gitHubs) {
		this.gitHubs = gitHubs;
	}

	
}
