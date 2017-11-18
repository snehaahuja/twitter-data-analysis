package com.ccoew.adbms.project;

public class Def {

	String name;
	String tweet;
	
	public Def(String name, String tweet) {
		super();
		this.name = name;
		this.tweet = tweet;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTweet() {
		return tweet;
	}
	public void setTweet(String tweet) {
		this.tweet = tweet;
	}
	
	
}
