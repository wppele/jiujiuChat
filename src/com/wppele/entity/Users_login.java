package com.wppele.entity;

import java.io.Serializable;

public class Users_login implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3657063636771762123L;
	
	
	private String username;
	private String password;
	private String opration;
	private String userip;
	private int connectport;
	private boolean isonline;
	public String getUserip() {
		return userip;
	}
	public void setUserip(String userip) {
		this.userip = userip;
	}
	public int getConnectport() {
		return connectport;
	}
	public void setConnectport(int connectport) {
		this.connectport = connectport;
	}
	public boolean isIsonline() {
		return isonline;
	}
	public void setIsonline(boolean isonline) {
		this.isonline = isonline;
	}
	public String getOpration() {
		return opration;
	}
	public void setOpration(String opration) {
		this.opration = opration;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
