package com.wppele.entity;

import java.io.Serializable;
/**
 * ¡ƒÃÏ–≈œ¢bean
 * @author yuzheng
 *
 */
public class ChatInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String date;
	private String content;
	private String iconurl;
	private boolean isComeMesssge=true;
	
	public ChatInfo(String nickname,String content,String date,String iconurl,boolean isComeMesssge) {
		super();
		this.nickname=nickname;
		this.content=content;
		this.date=date;
		this.iconurl=iconurl;
		this.isComeMesssge=isComeMesssge;
	}
	
	public ChatInfo() {
	}

	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean getComeMesssge() {
		return isComeMesssge;
	}
	public void setComeMesssge(boolean isComeMesssge) {
		this.isComeMesssge = isComeMesssge;
	}
	
}
