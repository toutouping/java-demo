package com.ssm.promotion.core.entity;

import java.io.Serializable;

public class Center implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Integer id;
	private String name = "";
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
