package com.SAPTCO.hajLimo.dto;

import java.io.Serializable;

public class HajLimoWSDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer procResult;
	private String lastTransaction;
	private String vehicle;	
	private String toLocation;
	private Long batchNumber;
	private Boolean isNewService = false;
	
	public Integer getProcResult() {
		return procResult;
	}
	public void setProcResult(Integer procResult) {
		this.procResult = procResult;
	}
	public String getLastTransaction() {
		return lastTransaction;
	}
	public void setLastTransaction(String lastTransaction) {
		this.lastTransaction = lastTransaction;
	}
	public String getVehicle() {
		return vehicle;
	}
	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	public String getToLocation() {
		return toLocation;
	}
	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}
	public Long getBatchNumber() {
		return batchNumber;
	}
	public void setBatchNumber(Long batchNumber) {
		this.batchNumber = batchNumber;
	}
	public Boolean getIsNewService() {
		return isNewService;
	}
	public void setIsNewService(Boolean isNewService) {
		this.isNewService = isNewService;
	}
}
