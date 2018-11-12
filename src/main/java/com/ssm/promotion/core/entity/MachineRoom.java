package com.ssm.promotion.core.entity;

import java.io.Serializable;

public class MachineRoom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id = "";
	private String code = "";
	private String name = "";
	private String cityId = "";
	private String centerId = "";
	private String ip = "";
	private String inReaderId = "";
	private String outReaderId = "";
	private String city = "";
	private String center = "";
	private String inReaderNum = "";
	private String inReader = "";
	private String outReaderNum = "";
	private String outReader = "";
	
	public String getInReaderId() {
		return inReaderId;
	}
	public void setInReaderId(String inReaderId) {
		this.inReaderId = inReaderId;
	}
	public String getOutReaderId() {
		return outReaderId;
	}
	public void setOutReaderId(String outReaderId) {
		this.outReaderId = outReaderId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCityId() {
		return cityId;
	}
	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
	public String getCenterId() {
		return centerId;
	}
	public void setCenterId(String centerId) {
		this.centerId = centerId;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCenter() {
		return center;
	}
	public void setCenter(String center) {
		this.center = center;
	}
	public String getInReaderNum() {
		return inReaderNum;
	}
	public void setInReaderNum(String inReaderNum) {
		this.inReaderNum = inReaderNum;
	}
	public String getInReader() {
		return inReader;
	}
	public void setInReader(String inReader) {
		this.inReader = inReader;
	}
	public String getOutReaderNum() {
		return outReaderNum;
	}
	public void setOutReaderNum(String outReaderNum) {
		this.outReaderNum = outReaderNum;
	}
	public String getOutReader() {
		return outReader;
	}
	public void setOutReader(String outReader) {
		this.outReader = outReader;
	}
	
}
