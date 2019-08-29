package com.example.demoMutiModul.config;

public class ConfigNormals {

	private String siteName = "vduty.com";

	final public int ERROR_OVERFLOW = 1;
	final public int ERROR_DUMP_NAME = 2;
	
	public String getErrorName(int errcode)
	{
		if (errcode==1) return "ERROR_OVERFLOW";
		else
			return "ERROR_DUMP_NAME";
	}
	
	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	
}
