package com.SAPTCO.reports.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.HrBranchesBean;

public class IntegAllowanceDto extends BaseDto{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date fromDate = new Date();;
	private Date toDate = new Date();
	private Long driverId;
	private Boolean isIntegrated;
	private String tripCode;
	private HrBranchesBean hrBranchesBean = new HrBranchesBean();
	private List<HrBranchesBean> hrBranchesList =  new ArrayList<HrBranchesBean>();
	
	
	
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public boolean isIntegrated() {
		return isIntegrated;
	}
	public void setIntegrated(boolean isIntegrated) {
		this.isIntegrated = isIntegrated;
	}
	
	public Boolean getIsIntegrated() {
		if(isIntegrated == null)
			isIntegrated = false;
		return isIntegrated;
	}
	
	
	
	public int getIsFinIntegrated()
	{
		if (getIsActive())
		 return 1;
		else 
			return 0;
		
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	/**
	 * @return the hrBranchesList
	 */
	public List<HrBranchesBean> getHrBranchesList() {
		return hrBranchesList;
	}
	/**
	 * @param hrBranchesList the hrBranchesList to set
	 */
	public void setHrBranchesList(List<HrBranchesBean> hrBranchesList) {
		this.hrBranchesList = hrBranchesList;
	}
	/**
	 * @return the hrBranchesBean
	 */
	public HrBranchesBean getHrBranchesBean() {
		return hrBranchesBean;
	}
	/**
	 * @param hrBranchesBean the hrBranchesBean to set
	 */
	public void setHrBranchesBean(HrBranchesBean hrBranchesBean) {
		this.hrBranchesBean = hrBranchesBean;
	}
	
	
}
