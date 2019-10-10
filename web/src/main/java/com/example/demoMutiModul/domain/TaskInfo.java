package com.example.demoMutiModul.domain;

import java.util.Date;

public class TaskInfo {
	
	public TaskInfo(String name) {
		this.packageMessage = "";
		this.name = name;
		this.crateDate = new Date();		
	}
	
	private int flagId;
	public int getFlagId() {
		return flagId;
	}
	public void setFlagId(int flagId) {
		this.flagId = flagId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	private String name;
	private Date crateDate = new Date();
	public Date getCrateDate() {
		return crateDate;
	}
	public void setCrateDate(Date crateDate) {
		this.crateDate = crateDate;
	}
	public int getPackagePercent() {
		return packagePercent;
	}
	public void setPackagePercent(int packagePercent) {
		this.packagePercent = packagePercent;
	}
	public boolean isPackageSuccess() {
		return packageSuccess;
	}
	public void setPackageSuccess(boolean packageSuccess) {
		this.packageSuccess = packageSuccess;
	}
	public String getPackageMessage() {
		return packageMessage;
	}
	public void setPackageMessage(String packageMessage) {
		this.packageMessage = packageMessage;
	}
	private int packagePercent=0;
	private boolean packageSuccess = false;
	private String packageMessage ="";
	
	

}
