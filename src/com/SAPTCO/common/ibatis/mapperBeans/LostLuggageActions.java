package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;

public class LostLuggageActions {

	
	private  Long  lostLuggageActionId;
	private Long  transactionSequence;
	private LostLuggage  lostLugggageobj;
	private String remarks;
	private LostLuggageStatus  status;
	private Timestamp createdDate;
	private Long createdBy;
	
	
	public Long getLostLuggageActionId() {
		return lostLuggageActionId;
	}
	public void setLostLuggageActionId(Long lostLuggageActionId) {
		this.lostLuggageActionId = lostLuggageActionId;
	}
	public Long getTransactionSequence() {
		return transactionSequence;
	}
	public void setTransactionSequence(Long transactionSequence) {
		this.transactionSequence = transactionSequence;
	}
	public LostLuggage getLostLugggageobj() {
		return lostLugggageobj;
	}
	public void setLostLugggageobj(LostLuggage lostLugggageobj) {
		this.lostLugggageobj = lostLugggageobj;
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
