package com.teikametrics.github;

import com.teikametrics.common.BaseVo;

public class GitHubVo extends BaseVo{
	
	private String id;
	private String type;
	private GitHubActorVo actor;
	private GitHubRepoVo repo;
	private GitHubPayloadVo payload;
	private String created_at;
	private GitHubOrgVo org;
	
	public GitHubOrgVo getOrg() {
		return org;
	}
	public void setOrg(GitHubOrgVo org) {
		this.org = org;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public GitHubActorVo getActor() {
		return actor;
	}
	public void setActor(GitHubActorVo actor) {
		this.actor = actor;
	}
	public GitHubRepoVo getRepo() {
		return repo;
	}
	public void setRepo(GitHubRepoVo repo) {
		this.repo = repo;
	}
	public GitHubPayloadVo getPayload() {
		return payload;
	}
	public void setPayload(GitHubPayloadVo payload) {
		this.payload = payload;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	

}
