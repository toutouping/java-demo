package com.ssm.promotion.core.entity;

import java.io.Serializable;

public class Reader implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id = "";
	private String centerId = "";
	private String number = "";
	private String name = "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
