package com.wppele.entity;

import java.util.Date;

public class Messages {
	private String name;
	private String content;
	private String time;
	private String pictureUrl;
	
	
	public Messages(String name, String content, String time, String pictureUrl) {
		super();
		this.name = name;
		this.content = content;
		this.time = time;
		this.pictureUrl = pictureUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	/*@Override
	public String toString() {
		return "Messages [name=" + name + ", content=" + content + ", time="
				+ time + ", pictureUrl=" + pictureUrl + "]";
	}*/
	
	
}
