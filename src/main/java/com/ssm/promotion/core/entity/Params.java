package com.ssm.promotion.core.entity;

import java.io.Serializable;

public class Params implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String userId = "";
	private String key = "";
	private String value = "";
	private String description = "";
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		switch(this.key){
		case "DBName":
			this.description = "数据库名称";
			break;
		case "DBPassword":
			this.description = "数据库登录密码";
			break;
		case "DBServer":
			this.description = "数据库服务器地址";
			break;
		case "DBType":
			this.description = "数据库类型：SQLServer=1，Oracle=2";
			break;
		case "DBUserName":
			this.description = "数据库登录用户名";
			break;
		case "IGetConfigAddress":
			this.description = "获取系统配置参数Rest接口地址";
			break;
		case "InReaderID":
			this.description = "进入读卡器编号";
			break;
		case "InReaderName":
			this.description = "进入读卡器名称";
			break;
		case "ISwipingCardAddress":
			this.description = "发送工单刷卡记录Rest接口地址";
			break;
		case "IValidateUserAddress":
			this.description = "验证用户Rest接口地址";
			break;
		case "IVendorAddress":
			this.description = "发送工单供应商记录Rest接口地址";
			break;
		case "IWorkOrderAddress":
			this.description = "发送工单Rest接口地址";
			break;
		case "MachineRoomID":
			this.description = "机房编号";
			break;
		case "MachineRoomName":
			this.description = "机房名称";
			break;
		case "OutReaderID":
			this.description = "离开读卡器编号";
			break;
		case "OutReaderName":
			this.description = "离开读卡器名称";
			break;
		case "RefreshDBInterval":
			this.description = "刷新数据间隔，单位：毫秒";
			break;
		case "InReaderAction":
			this.description = "进入读卡器动作：1，进入；0，离开";
			break;
		case "OutReaderAction":
			this.description = "离开读卡器动作：1，进入；0，离开";
			break;
		case "IGetITILWorkOrderAddressPostfix":
			this.description = "获取ITIL工单地址后缀";
			break;
		case "IGetITILWorkOrderAddressPrefix":
			this.description = "获取ITIL工单地址前缀";
			break;
		case "LoginITILPassWord":
			this.description = "登录ITIL密码";
			break;
		case "LoginITILUserName":
			this.description = "登录ITIL用户名";
			break;
		}
		return this.description;
	}
		
	public void setDescription(String description) {
		this.description = description;
	}
}
