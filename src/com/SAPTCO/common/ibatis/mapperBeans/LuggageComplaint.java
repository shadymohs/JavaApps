package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;

public class LuggageComplaint {

	
	private Long luggageComplaintId;
	private String stickerNumber;
	private Long tripNumber;
	private String ticketNumber;
	private Long stationId;
	private String branch;
	private Long branchId;
	private String contacteNumber;
	private String customerName;
	private Long linkedLuggageId;
	
	private LostLuggageStatus  currentStatus = new LostLuggageStatus();
	private LostLuggageSize   size = new LostLuggageSize();
	private LostLuggageClass  luggageClass = new LostLuggageClass();
	private LostLuggageColor   color = new LostLuggageColor();
	
	
	private Timestamp createdDate;
	private String createdBy;
	private Timestamp updatedDate;
	private String updatedBy;
	
	private String specialMark;
	private String remarks;
	private String luggageContent;
	private String line;
	
	//
	private Date tripDate;
	private String tripCode;
	private String registeredInStation;
	
	//search field
	private Date fromDate;
	private Date toDate;
	private Boolean onlyMyLuggage = false;
	//compensation
	private String receiptNumber;
	private Float  repaymentValue;
	
	 public Date getFromDate() {
		 
		return fromDate;
	}
	 public String getReceiptNumber() {
		return receiptNumber;
	}
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	public Float getRepaymentValue() {
		return repaymentValue;
	}
	public void setRepaymentValue(Float repaymentValue) {
		this.repaymentValue = repaymentValue;
	}
	public String getFromDateAsString() {
		 if (fromDate!= null){
		 SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
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
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
    	return DATE_FORMAT.format(toDate);}
    	 else
			 return null;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getTripDateAsString() {
		if (tripDate!= null) {
	    	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
	    	return DATE_FORMAT.format(tripDate);
		    }
    	 else
			 return null;
		}
	    
	public void setTripDateAsString(String tripDateAsString) {
	 try {
			if(tripDateAsString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
					this.tripDate = df.parse(tripDateAsString);
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
				
		}
	
	public String getSpecialMark() {
		return specialMark;
	}
	public void setSpecialMark(String specialMark) {
		this.specialMark = specialMark;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getLuggageContent() {
		return luggageContent;
	}
	public void setLuggageContent(String luggageContent) {
		this.luggageContent = luggageContent;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public Date getTripDate() {
		return tripDate;
	}
	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	public String getRegisteredInStation() {
		return registeredInStation;
	}
	public void setRegisteredInStation(String registeredInStation) {
		this.registeredInStation = registeredInStation;
	}
	public Long getLuggageComplaintId() {
		return luggageComplaintId;
	}
	public void setLuggageComplaintId(Long luggageComplaintId) {
		this.luggageComplaintId = luggageComplaintId;
	}
	public String getStickerNumber() {
		return stickerNumber;
	}
	public void setStickerNumber(String stickerNumber) {
		this.stickerNumber = stickerNumber;
	}
	public Long getTripNumber() {
		return tripNumber;
	}
	public void setTripNumber(Long tripNumber) {
		this.tripNumber = tripNumber;
	}
	public String getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getContacteNumber() {
		return contacteNumber;
	}
	public void setContacteNumber(String contacteNumber) {
		this.contacteNumber = contacteNumber;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public Long getLinkedLuggageId() {
		return linkedLuggageId;
	}
	public void setLinkedLuggageId(Long linkedLuggageId) {
		this.linkedLuggageId = linkedLuggageId;
	}
	public LostLuggageStatus getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(LostLuggageStatus currentStatus) {
		this.currentStatus = currentStatus;
	}
	public LostLuggageSize getSize() {
		return size;
	}
	public void setSize(LostLuggageSize size) {
		this.size = size;
	}
	public LostLuggageClass getLuggageClass() {
		return luggageClass;
	}
	public void setLuggageClass(LostLuggageClass luggageClass) {
		this.luggageClass = luggageClass;
	}
	public LostLuggageColor getColor() {
		return color;
	}
	public void setColor(LostLuggageColor color) {
		this.color = color;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
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
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Boolean getOnlyMyLuggage() {
		return onlyMyLuggage;
	}
	public void setOnlyMyLuggage(Boolean onlyMyLuggage) {
		this.onlyMyLuggage = onlyMyLuggage;
	}
	
	
}
