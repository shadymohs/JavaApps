package com.SAPTCO.common.ibatis.mapperBeans;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;

/**
 * @author alqassemga
 *
 */
public class DailySDPTargetBean {

	private Long targetId;
	private Date targetDate;
	private SDPBean mainSDP = new SDPBean();
	private Long mainSDPAmount;
	private Long agentsAmount;
	
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp updatedDate;
	private Long updatedBy;
	
	private String targetDateAsString;
	
	public Long getTargetId() {
		return targetId;
	}
	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	public SDPBean getMainSDP() {
		return mainSDP;
	}
	public void setMainSDP(SDPBean mainSDP) {
		this.mainSDP = mainSDP;
	}
	public Long getMainSDPAmount() {
		return mainSDPAmount;
	}
	public void setMainSDPAmount(Long mainSDPAmount) {
		this.mainSDPAmount = mainSDPAmount;
	}
	public Long getAgentsAmount() {
		return agentsAmount;
	}
	public void setAgentsAmount(Long agentsAmount) {
		this.agentsAmount = agentsAmount;
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
	public Timestamp getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getTargetDateAsString() {
		
		try {
			if(targetDate != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				 String startDateSt = df.format(targetDate); 
				 targetDateAsString=startDateSt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return targetDateAsString;
	}
	public void setTargetDateAsString(String targetDateAsString) {
		
		try {
			if(targetDateAsString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				targetDate = df.parse(targetDateAsString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.targetDateAsString = targetDateAsString;
	}
	
	
	
	
}
