package com.SAPTCO.lostLuggage.dto;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggage;
import com.SAPTCO.common.ibatis.mapperBeans.LuggageComplaint;

public class ComplaintDto  extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private LuggageComplaint complaintObj = new LuggageComplaint();
	private String procResult;
	private String operation;
	private List<LostLuggage> matchedLostLuggage = new ArrayList<LostLuggage>();
	
	
	
	
	public LuggageComplaint getComplaintObj() {
		return complaintObj;
	}
	public void setComplaintObj(LuggageComplaint complaintObj) {
		this.complaintObj = complaintObj;
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
	public List<LostLuggage> getMatchedLostLuggage() {
		return matchedLostLuggage;
	}
	public void setMatchedLostLuggage(List<LostLuggage> matchedLostLuggage) {
		this.matchedLostLuggage = matchedLostLuggage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
