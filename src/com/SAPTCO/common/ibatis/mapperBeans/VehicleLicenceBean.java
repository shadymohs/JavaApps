package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.Date;

public class VehicleLicenceBean {

	private Long serialNo;
	private Long tagNumber;
	private Long sectorId;
	private String sectorName;
	private String FXplateNo;
	
	private Long plateNumber;
	private String plateLetterLeft;
	private String plateLetterMiddle;
	private String plateLetterRight;
	private Long plateType;
	
	private Long busSeats;
	private Date licenseExpDate;
	private Boolean registerWASEL;
	private String hijriExpDate;
	
	
	
	
	public String getHijriExpDate() {
		return hijriExpDate;
	}
	public void setHijriExpDate(String hijriExpDate) {
		this.hijriExpDate = hijriExpDate;
	}
	public Long getPlateType() {
		return plateType;
	}
	public void setPlateType(Long plateType) {
		this.plateType = plateType;
	}
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public Long getTagNumber() {
		return tagNumber;
	}
	public void setTagNumber(Long tagNumber) {
		this.tagNumber = tagNumber;
	}
	public Long getSectorId() {
		return sectorId;
	}
	public void setSectorId(Long sectorId) {
		this.sectorId = sectorId;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getFXplateNo() {
		return FXplateNo;
	}
	public void setFXplateNo(String fXplateNo) {
		FXplateNo = fXplateNo;
	}
	public Long getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(Long plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getPlateLetterLeft() {
		return plateLetterLeft;
	}
	public void setPlateLetterLeft(String plateLetterLeft) {
		this.plateLetterLeft = plateLetterLeft;
	}
	public String getPlateLetterMiddle() {
		return plateLetterMiddle;
	}
	public void setPlateLetterMiddle(String plateLetterMiddle) {
		this.plateLetterMiddle = plateLetterMiddle;
	}
	public String getPlateLetterRight() {
		return plateLetterRight;
	}
	public void setPlateLetterRight(String plateLetterRight) {
		this.plateLetterRight = plateLetterRight;
	}
	
	public Long getBusSeats() {
		return busSeats;
	}
	public void setBusSeats(Long busSeats) {
		this.busSeats = busSeats;
	}
	public Date getLicenseExpDate() {
		return licenseExpDate;
	}
	public void setLicenseExpDate(Date licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}
	public Boolean getRegisterWASEL() {
		return registerWASEL;
	}
	public void setRegisterWASEL(Boolean registerWASEL) {
		this.registerWASEL = registerWASEL;
	}



	
	
	
}
