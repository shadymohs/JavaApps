package com.SAPTCO.lostLuggage.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.TransferLugRequest;

public class TransferLugRequestDto {
	
	private TransferLugRequest transferLugRequest;
	private String procResult;
	private String operation;
	//search field
	private Date fromDate;
	private Date toDate;
	
	public TransferLugRequest getTransferLugRequest() {
		return transferLugRequest;
	}
	public void setTransferLugRequest(TransferLugRequest transferLugRequest) {
		this.transferLugRequest = transferLugRequest;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
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
	
	 public String getFromDateAsString() {
		 if (fromDate!= null){
		 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
	    	return DATE_FORMAT.format(fromDate);}
		 else
			 return null;
			
		}
	 
	 public String getToDateAsString() {
		 if (toDate!= null){
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
    	return DATE_FORMAT.format(toDate);}
    	 else
			 return null;
	}
	

}
