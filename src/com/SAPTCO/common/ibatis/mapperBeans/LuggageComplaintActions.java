package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;

public class LuggageComplaintActions {

	
	private  Long  luggageComplaintActionId;
	private Long  transactionSequence;
	private LuggageComplaint  luggageComplaintObj;
	private String remarks;
	private LostLuggageStatus  status;
	private Timestamp createdDate;
	private Long createdBy;
	
	
	public Long getLuggageComplaintActionId() {
		return luggageComplaintActionId;
	}
	public void setLuggageComplaintActionId(Long luggageComplaintActionId) {
		this.luggageComplaintActionId = luggageComplaintActionId;
	}
	public Long getTransactionSequence() {
		return transactionSequence;
	}
	public void setTransactionSequence(Long transactionSequence) {
		this.transactionSequence = transactionSequence;
	}
	public LuggageComplaint getLuggageComplaintObj() {
		return luggageComplaintObj;
	}
	public void setLuggageComplaintObj(LuggageComplaint luggageComplaintObj) {
		this.luggageComplaintObj = luggageComplaintObj;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public LostLuggageStatus getStatus() {
		return status;
	}
	public void setStatus(LostLuggageStatus status) {
		this.status = status;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	
	
	
}
