package com.teikametrics.github;

import java.util.List;

public class GitHubPayloadVo {
	
	private String ref;
	private String ref_type;
	private String master_branch;
	private String description;
	private String pusher_type;
	private String push_id;
	private int size;
	private int distinct_size;
	private String head;
	private String before;
	private List<GitHubPayloadCommitsVo> commits;
	public String getPush_id() {
		return push_id;
	}
	public void setPush_id(String push_id) {
		this.push_id = push_id;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getDistinct_size() {
		return distinct_size;
	}
	public void setDistinct_size(int distinct_size) {
		this.distinct_size = distinct_size;
	}
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public List<GitHubPayloadCommitsVo> getCommits() {
		return commits;
	}
	public void setCommits(List<GitHubPayloadCommitsVo> commits) {
		this.commits = commits;
	}
	public String getRef() {
		return ref;
	}
	public void setRef(String ref) {
		this.ref = ref;
	}
	public String getRef_type() {
		return ref_type;
	}
	public void setRef_type(String ref_type) {
		this.ref_type = ref_type;
	}
	public String getMaster_branch() {
		return master_branch;
	}
	public void setMaster_branch(String master_branch) {
		this.master_branch = master_branch;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPusher_type() {
		return pusher_type;
	}
	public void setPusher_type(String pusher_type) {
		this.pusher_type = pusher_type;
	}

}
