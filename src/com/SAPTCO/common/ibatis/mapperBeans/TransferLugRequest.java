package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.SAPTCO.common.config.SystemConstants;

public class TransferLugRequest {

	private Long    requestId;
	private LostLuggage   luggage = new LostLuggage();
	private TransferRequestStatus   requestStatus = new TransferRequestStatus() ;
	private String   user;
	private Timestamp   requestDate;
	private String   newLocation;
	private Long    newStationId;
	private String newStationLN;
	private Timestamp   updatedDate;
	private String    updatedBy;
	
	
	 public String getRequestDateAsString() {
	    	if (requestDate!= null) {
	    	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
	    	return DATE_FORMAT.format(requestDate);}
	    	 else
				 return null;
		}
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public LostLuggage getLuggage() {
		return luggage;
	}
	public void setLuggage(LostLuggage luggage) {
		this.luggage = luggage;
	}
	public TransferRequestStatus getRequestStatus() {
		return requestStatus;
	}
	public void setRequestStatus(TransferRequestStatus requestStatus) {
		this.requestStatus = requestStatus;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Timestamp getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}
	public String getNewLocation() {
		return newLocation;
	}
	public void setNewLocation(String newLocation) {
		this.newLocation = newLocation;
	}
	
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Long getNewStationId() {
		return newStationId;
	}
	public void setNewStationId(Long newStationId) {
		this.newStationId = newStationId;
	}
	public String getNewStationLN() {
		return newStationLN;
	}
	public void setNewStationLN(String newStationLN) {
		this.newStationLN = newStationLN;
	}
	
	
	
}
