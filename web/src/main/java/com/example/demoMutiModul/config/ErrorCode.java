package com.example.demoMutiModul.config;

public enum ErrorCode {
	ERR_DUMPLICATION_NAME("DUMP",1),ERR_PARAMETER_FAIL("PARAM",2),ERR_OVERFLOW("FLOW",3);
	private String name;
	private int index;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	
	
	ErrorCode(String name,int index) {
		this.name = name;
		this.index = index;
	}
	public  String getName(int index) {
		for(ErrorCode err :ErrorCode.values()) {
			if(err.getIndex() == index) {
				return err.name;
			}
		} 
		return null;
	}

}
