package com.SAPTCO.common.config;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
@ManagedBean(name="versionInf")
@SessionScoped
public class VersionInf{
	
	@Value("#{versionConfig['ver.major']}")
	private String versionMajor;
	@Value("#{versionConfig['ver.minor']}")
	private String versionMinor;
	@Value("#{versionConfig['ver.type']}")
	private String versionType;
	
	public String getVersion(){
		return versionMajor + "." + versionMinor + "_"
				+ versionType;
	}

	public String getVersionMajor() {
		return versionMajor;
	}

	public void setVersionMajor(String versionMajor) {
		this.versionMajor = versionMajor;
	}

	public String getVersionMinor() {
		return versionMinor;
	}

	public void setVersionMinor(String versionMinor) {
		this.versionMinor = versionMinor;
	}

	public String getVersionType() {
		return versionType;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}
	
}
