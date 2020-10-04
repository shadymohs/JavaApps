package com.SAPTCO.reports.dto;

import java.util.Date;

import com.SAPTCO.common.dto.BaseDto;

public class CostAnalysisReportDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private String tripCode;
	private Long noOfPassengers;
	private Long goingReturn = (long) 1;
	private String lineCode;
	private Boolean isSummary = false;
	private Long  stationId;
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Long getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(Long noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	public Long getGoingReturn() {
		return goingReturn;
	}
	public void setGoingReturn(Long goingReturn) {
		this.goingReturn = goingReturn;
	}
	public Boolean getIsSummary() {
		return isSummary;
	}
	public void setIsSummary(Boolean isSummary) {
		this.isSummary = isSummary;
	}
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	
	
	

}
