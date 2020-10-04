package com.SAPTCO.urbanFest.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.BaseDto;

public class JndrReportDto  extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fromDate;
	private Date toDate;
	private Long fromStationId;
	private Long toStationId;
	
	public Long getFromStationId() {
		return fromStationId;
	}

	public void setFromStationId(Long fromStationId) {
		this.fromStationId = fromStationId;
	}

	public Long getToStationId() {
		return toStationId;
	}

	public void setToStationId(Long toStationId) {
		this.toStationId = toStationId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Date getFromDate() {
		 
		return fromDate;
	}
	
	public String getFromDateAsString() {
		 if (fromDate!= null){
		 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MMM/yyyy");
	    	return DATE_FORMAT.format(fromDate);}
		 else
			 return null;
			
		}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}
	public String getToDateAsString() {
		 if (toDate!= null){
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MMM/yyyy"); //SystemConstants.Date_Format);
    	return DATE_FORMAT.format(toDate);}
    	 else
			 return null;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

}
