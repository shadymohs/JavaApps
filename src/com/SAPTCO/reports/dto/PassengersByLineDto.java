package com.SAPTCO.reports.dto;

import java.util.Date;

import com.SAPTCO.common.dto.BaseDto;

public class PassengersByLineDto  extends BaseDto {
	
    private static final long serialVersionUID = 1L;
	
	private Date fromDate = new Date();
	private Date toDate = new Date();
	
	private String lineCode;
	private Boolean isSummary = false;
	
	
	
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
	public String getLineCode() {
		return lineCode;
	}
	public void setLineCode(String lineCode) {
		this.lineCode = lineCode;
	}
	public Boolean getIsSummary() {
		return isSummary;
	}
	public void setIsSummary(Boolean isSummary) {
		this.isSummary = isSummary;
	}
	
	

}
