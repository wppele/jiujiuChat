package com.wppele.entity;

import java.io.Serializable;

public class UserInfo_FriendList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String UUnumber;//�û���
	private String nickname;//�ǳ�
	private String mood;//����
	private String pictureurl;//ͷ������
	private String isonline;//�Ƿ�����
	private String userip;//�û�ip
	public String getUUnumber() {
		return UUnumber;
	}
	public void setUUnumber(String uUnumber) {
		UUnumber = uUnumber;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMood() {
		return mood;
	}
	public void setMood(String mood) {
		this.mood = mood;
	}
	public String getPictureurl() {
		return pictureurl;
	}
	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}
	public String getIsonline() {
		return isonline;
	}
	public void setIsonline(String isonline) {
		this.isonline = isonline;
	}
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	@Override
	public String toString() {
		return "UserInfo_FriendList [UUnumber=" + UUnumber + ", nickname="
				+ nickname + ", mood=" + mood + ", pictureurl=" + pictureurl
				+ ", isonline=" + isonline + ", userip=" + userip + "]";
	}
	
}
