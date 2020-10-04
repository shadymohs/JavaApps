package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;

/**
 * @author alqassemga
 *
 */
public class BusTypeCostBean {

	
	private Long busTypeCostId;
	private Date startDate;
	private Date endDate;
	private String startDateAsString;
	private String endDateAsString;
	private BusTypeBean  busType = new BusTypeBean();
	private Long busTypeCostAmount;
	
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp updatedDate;
	private Long updatedBy;
	
	public String getStartDateAsString() {
		try {
			if(startDate != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				 String startDateSt = df.format(startDate); 
				 startDateAsString=startDateSt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return startDateAsString;
	}
	public void setStartDateAsString(String startDateAsString) {
		try {
			if(startDateAsString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				startDate = df.parse(startDateAsString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.startDateAsString = startDateAsString;
	}
	public String getEndDateAsString() {
		try {
			if(endDate != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				 String endDateSt = df.format(endDate); 
				 endDateAsString= endDateSt;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return endDateAsString;
	}
	public void setEndDateAsString(String endDateAsString) {
		try {
			if(endDateAsString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				endDate = df.parse(endDateAsString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.endDateAsString = endDateAsString;
	}
	
	public Long getBusTypeCostId() {
		return busTypeCostId;
	}
	public void setBusTypeCostId(Long busTypeCostId) {
		this.busTypeCostId = busTypeCostId;
	}
	
	public BusTypeBean getBusType() {
		return busType;
	}
	public void setBusType(BusTypeBean busType) {
		this.busType = busType;
	}
	public Long getBusTypeCostAmount() {
		return busTypeCostAmount;
	}
	public void setBusTypeCostAmount(Long busTypeCostAmount) {
		this.busTypeCostAmount = busTypeCostAmount;
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
	
	
}
