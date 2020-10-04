package com.SAPTCO.manageAllowance.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;


/**
 * @author alqassemga
 *
 */
public class OtherAllowanceDto extends BaseDto{

	
	private static final long serialVersionUID = 1L;
	
	private AllowanceDetailBean allowanceDetailBean = new AllowanceDetailBean();
	private List<AllowanceDetailBean> driversAllowance = new  ArrayList<AllowanceDetailBean>();
	private String codeDesc;
	private String procResult;
	private List<AllowanceTypeBean> allowanceTypeList = new ArrayList<AllowanceTypeBean>();
	
	//search parameters
	private String date;
	private Long driverNumber;
	private Long allowanceNumber;
	private Long allowanceType;
	private String tripCode;
	
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Long getDriverNumber() {
		return driverNumber;
	}
	public void setDriverNumber(Long driverNumber) {
		this.driverNumber = driverNumber;
	}
	public Long getAllowanceNumber() {
		return allowanceNumber;
	}
	public void setAllowanceNumber(Long allowanceNumber) {
		this.allowanceNumber = allowanceNumber;
	}
	public List<AllowanceTypeBean> getAllowanceTypeList() {
		return allowanceTypeList;
	}
	public void setAllowanceTypeList(List<AllowanceTypeBean> allowanceTypeList) {
		this.allowanceTypeList = allowanceTypeList;
	}
	public Long getAllowanceType() {
		return allowanceType;
	}
	public void setAllowanceType(Long allowanceType) {
		this.allowanceType = allowanceType;
	}
	public AllowanceDetailBean getAllowanceDetailBean() {
		return allowanceDetailBean;
	}
	public void setAllowanceDetailBean(AllowanceDetailBean allowanceDetailBean) {
		this.allowanceDetailBean = allowanceDetailBean;
	}
	public List<AllowanceDetailBean> getDriversAllowance() {
		return driversAllowance;
	}
	public void setDriversAllowance(List<AllowanceDetailBean> driversAllowance) {
		this.driversAllowance = driversAllowance;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
}