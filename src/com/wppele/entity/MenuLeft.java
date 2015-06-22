package com.wppele.entity;

public class MenuLeft {
	
	private String iconurl;
	private String name;
	private String Arrowurl;
	
	public MenuLeft(String iconurl, String name, String Arrowurl) {
		super();
		this.iconurl = iconurl;
		this.name = name;
		this.Arrowurl = Arrowurl;
	}

	public String getIconurl() {
		return iconurl;
	}

	public void setIconurl(String iconurl) {
		this.iconurl = iconurl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getArrowurl() {
		return Arrowurl;
	}

	public void setArrowurl(String arrowurl) {
		Arrowurl = arrowurl;
	}
	
}
