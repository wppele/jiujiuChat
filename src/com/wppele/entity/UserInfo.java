package com.wppele.entity;

import java.io.Serializable;

public class UserInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5529417537420077409L;
	
	private int id;
	private String username;//用户名
	private String nickname;//昵称
	private String mystate;//心情
	private String sex;//性别
	private String birthday;//生日
	private String language;//语言
	private int age;//年龄
	private String  animalsign;//生肖
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMystate() {
		return mystate;
	}
	public void setMystate(String mystate) {
		this.mystate = mystate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAnimalsign() {
		return animalsign;
	}
	public void setAnimalsign(String animalsign) {
		this.animalsign = animalsign;
	}
	
}
