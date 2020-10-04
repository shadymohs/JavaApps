package com.SAPTCO.lostLuggage.dto;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggage;
import com.SAPTCO.common.ibatis.mapperBeans.LuggageComplaint;

public class LostLuggageDto extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LostLuggage lostLuggageObj = new LostLuggage();
	private String stickerNumber;
	private String reservationNumber;
	private String procResult;
	private String operation;
	private List<LuggageComplaint> matchedComplaintsList = new ArrayList<LuggageComplaint>();

	
	public List<LuggageComplaint> getMatchedComplaintsList() {
		return matchedComplaintsList;
	}

	public void setMatchedComplaintsList(
			List<LuggageComplaint> matchedComplaintsList) {
		this.matchedComplaintsList = matchedComplaintsList;
	}

	public LostLuggage getLostLuggageObj() {
		return lostLuggageObj;
	}

	public void setLostLuggageObj(LostLuggage lostLuggageObj) {
		this.lostLuggageObj = lostLuggageObj;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getProcResult() {
		return procResult;
	}

	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}

	public String getStickerNumber() {
		return stickerNumber;
	}

	public void setStickerNumber(String stickerNumber) {
		this.stickerNumber = stickerNumber;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getReservationNumber() {
		return reservationNumber;
	}

	public void setReservationNumber(String reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	
	
	

}
