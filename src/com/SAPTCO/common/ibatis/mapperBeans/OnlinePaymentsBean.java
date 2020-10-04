package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/ 

public class OnlinePaymentsBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private String reservationNumber;
	private String paidAmount;
	private String creationDate;
	private String transactionNumber;
	private String billIdPerClient;
	private String billID;
	private String expiryDate;
	private String sadadCreationDate;
	
	public String getReservationNumber() {
		return reservationNumber;
	}
	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	public String getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}
	public String getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}
	public String getTransactionNumber() {
		return transactionNumber;
	}
	public void setTransactionNumber(String transactionNumber) {
		this.transactionNumber = transactionNumber;
	}
	public String getBillIdPerClient() {
		return billIdPerClient;
	}
	public void setBillIdPerClient(String billIdPerClient) {
		this.billIdPerClient = billIdPerClient;
	}
	public String getBillID() {
		return billID;
	}
	public void setBillID(String billID) {
		this.billID = billID;
	}
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getSadadCreationDate() {
		return sadadCreationDate;
	}
	public void setSadadCreationDate(String sadadCreationDate) {
		this.sadadCreationDate = sadadCreationDate;
	}

}
