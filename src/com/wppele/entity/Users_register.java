package com.wppele.entity;

import java.io.Serializable;
/**
 * 注册bean
 * @author yuzheng
 *
 */
public class Users_register implements Serializable{
	
	/**
	 * 用于网络传，正确性
	 */
	private static final long serialVersionUID = 1L;
	private String uunumber;
	private String password;
	private String nickname;
	private String realname;
	private String idnumber;
	private String telphone;
	private String opration;
	public String getUunumber() {
		return uunumber;
	}
	public void setUunumber(String uunumber) {
		this.uunumber = uunumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getIdnumber() {
		return idnumber;
	}
	public void setIdnumber(String idnumber) {
		this.idnumber = idnumber;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getOpration() {
		return opration;
	}
	public void setOpration(String opration) {
		this.opration = opration;
	}
	
}
