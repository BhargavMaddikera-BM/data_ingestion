package com.teikametrics.externalintegration.github;

public class GitHubPayloadCommitsVo {
	
	private String sha;
	private String message;
	private boolean distinct;
	private String url;
	private GitHubPayloadCommitsAuthorVo author;
	public String getSha() {
		return sha;
	}
	public void setSha(String sha) {
		this.sha = sha;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isDistinct() {
		return distinct;
	}
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public GitHubPayloadCommitsAuthorVo getAuthor() {
		return author;
	}
	public void setAuthor(GitHubPayloadCommitsAuthorVo author) {
		this.author = author;
	}

}
