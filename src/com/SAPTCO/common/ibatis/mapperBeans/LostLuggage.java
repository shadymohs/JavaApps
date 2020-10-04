package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;

public class LostLuggage {

	
	private Long lostLuggageId;
	private String stickerNumber;
	private Long tripNumber;
	private String ticketNumber;
	private Long stationId;
	private String branch;
	private Long branchId;
	private String currentLocation;
	private Date foundDate;
	private String refNumber;
	private String contacteNumber;
	private Long linkedComplaintId;
	private String reservationNumber;
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
	private String fromStation;
	private String toStation;
	private String passengerName;
	private String foundInStation;
	
	private Float  storeFee;
	private String recipientName;
	private String recipientId;
	private String recipientMobile;
	
	//search field
		private Date fromDate;
		private Date toDate;
		
		private Boolean onlyMyLuggage = false;
		
		 public Date getFromDate() {
			 
			return fromDate;
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
		
		public String getTripDateAsString() {
			 if (tripDate!= null){
			SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
	    	return DATE_FORMAT.format(tripDate);}
	    	 else
				 return null;
		}
		
		public void setToDate(Date toDate) {
			this.toDate = toDate;
		}
	
	public String getFoundInStation() {
		return foundInStation;
	}
	public void setFoundInStation(String foundInStation) {
		this.foundInStation = foundInStation;
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
	public String getFromStation() {
		return fromStation;
	}
	public void setFromStation(String fromStation) {
		this.fromStation = fromStation;
	}
	public String getToStation() {
		return toStation;
	}
	public void setToStation(String toStation) {
		this.toStation = toStation;
	}
	public String getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public Long getLostLuggageId() {
		return lostLuggageId;
	}
	public void setLostLuggageId(Long lostLuggageId) {
		this.lostLuggageId = lostLuggageId;
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
	public String getCurrentLocation() {
		return currentLocation;
	}
	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}
	
	
    
    public String getFoundDateAsString() {
    	if (foundDate!= null) {
    	SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(SystemConstants.Date_Format);
    	return DATE_FORMAT.format(foundDate);}
    	 else
			 return null;
	}
    
	public void setFoundDateAsString(String foundDateAsString) {
		 try {
				if(foundDateAsString != null){
					SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
					this.foundDate = df.parse(foundDateAsString);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
	}
	
	public Date getFoundDate() {
		return foundDate;
	}
	public void setFoundDate(Date foundDate) {
		this.foundDate = foundDate;
	}
	public String getRefNumber() {
		return refNumber;
	}
	public void setRefNumber(String refNumber) {
		this.refNumber = refNumber;
	}
	public String getContacteNumber() {
		return contacteNumber;
	}
	public void setContacteNumber(String contacteNumber) {
		this.contacteNumber = contacteNumber;
	}
	public Long getLinkedComplaintId() {
		return linkedComplaintId;
	}
	public void setLinkedComplaintId(Long linkedComplaintId) {
		this.linkedComplaintId = linkedComplaintId;
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
	public String getPassengerName() {
		return passengerName;
	}
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	public Float getStoreFee() {
		return storeFee;
	}
	public void setStoreFee(Float storeFee) {
		this.storeFee = storeFee;
	}
	public String getRecipientName() {
		return recipientName;
	}
	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
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
	public String getRecipientMobile() {
		return recipientMobile;
	}
	public void setRecipientMobile(String recipientMobile) {
		this.recipientMobile = recipientMobile;
	}
	
	
	
	
}
