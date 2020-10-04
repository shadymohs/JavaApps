package com.SAPTCO.financialServices.dto;

import com.SAPTCO.common.dto.BaseDto;


 /**
 * @author Shady
 *
 */
public class FADto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String tagNum;
	private String region;
	private String assetNum;
	private String empNum;
	private String fromDept;
	private String toDept;
	private String parentTag;
	private String parentAsset;

	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTagNum() {
		return tagNum;
	}
	public void setTagNum(String tagNum) {
		this.tagNum = tagNum;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getAssetNum() {
		return assetNum;
	}
	public void setAssetNum(String assetNum) {
		this.assetNum = assetNum;
	}
	public String getEmpNum() {
		return empNum;
	}
	public void setEmpNum(String empNum) {
		this.empNum = empNum;
	}
	public String getFromDept() {
		return fromDept;
	}
	public void setFromDept(String fromDept) {
		this.fromDept = fromDept;
	}
	public String getToDept() {
		return toDept;
	}
	public void setToDept(String toDept) {
		this.toDept = toDept;
	}
	public String getParentTag() {
		return parentTag;
	}
	public void setParentTag(String parentTag) {
		this.parentTag = parentTag;
	}
	public String getParentAsset() {
		return parentAsset;
	}
	public void setParentAsset(String parentAsset) {
		this.parentAsset = parentAsset;
	}	
}
