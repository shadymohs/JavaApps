package com.SAPTCO.common.ibatis.mapperBeans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;

/**
 * @author alqassemga
 *
 */
public class LineCostBean {

	
	private LineBean line  = new LineBean(); 
	private Long lineCostId;
	private Date startDate;
	private Date endDate;
	private String startDateAsString;
	private String endDateAsString;
	private Float perKMCost;
    private Float perHourCost;
	//for detail
    private String tripCode;
    private Long lineCostDtlId;
	private Timestamp createdDate;
	private Long createdBy;
	private Timestamp updatedDate;
	private Long updatedBy;
	
	
	
	public LineBean getLine() {
		return line;
	}
	public void setLine(LineBean line) {
		this.line = line;
	}
	public Long getLineCostId() {
		return lineCostId;
	}
	public void setLineCostId(Long lineCostId) {
		this.lineCostId = lineCostId;
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
	
	
	
	public Float getPerKMCost() {
		return perKMCost;
	}
	public void setPerKMCost(Float perKMCost) {
		this.perKMCost = perKMCost;
	}
	public Float getPerHourCost() {
		return perHourCost;
	}
	public void setPerHourCost(Float perHourCost) {
		this.perHourCost = perHourCost;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
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
	public Long getLineCostDtlId() {
		return lineCostDtlId;
	}
	public void setLineCostDtlId(Long lineCostDtlId) {
		this.lineCostDtlId = lineCostDtlId;
	}
	
	
}
