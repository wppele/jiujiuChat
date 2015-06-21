package com.wppele.entity;

import java.io.Serializable;
/**
 * 联系人bean
 * @author yuzheng
 *
 */
public class Users_contact implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nickname;
	private String isonline;
	private String pictureurl;
	private String mood;//心情
	
	public Users_contact(String nickname, String mood, String isonline, String pictureurl) {
		super();
		this.nickname = nickname;
		this.mood = mood;
		this.isonline = isonline;
		this.pictureurl = pictureurl;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIsonline() {
		return isonline;
	}
	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	
}
