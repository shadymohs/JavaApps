package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.Date;

import com.SAPTCO.common.dto.BaseDto;

public class AssetsBean extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String assetNumber;
	private String assetType;
	private String plateNumber;
	private String chassisNumber;
	private Long seats;
	private String sectorDesc;
	private String assetLocation;
	private Date licenseExpDate;
	private Long assetCategory;
	private Long systemId;
	
	
	
	public String getAssetNumber() {
		return assetNumber;
	}
	public void setAssetNumber(String assetNumber) {
		this.assetNumber = assetNumber;
	}
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	
	public String getSectorDesc() {
		return sectorDesc;
	}
	public void setSectorDesc(String sectorDesc) {
		this.sectorDesc = sectorDesc;
	}
	public String getAssetLocation() {
		return assetLocation;
	}
	public void setAssetLocation(String assetLocation) {
		this.assetLocation = assetLocation;
	}
	public Date getLicenseExpDate() {
		return licenseExpDate;
	}
	public void setLicenseExpDate(Date licenseExpDate) {
		this.licenseExpDate = licenseExpDate;
	}
	
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public Long getSeats() {
		return seats;
	}
	public void setSeats(Long seats) {
		this.seats = seats;
	}
	public Long getAssetCategory() {
		return assetCategory;
	}
	public void setAssetCategory(Long assetCategory) {
		this.assetCategory = assetCategory;
	}
	
	
	
	
	
	

}

