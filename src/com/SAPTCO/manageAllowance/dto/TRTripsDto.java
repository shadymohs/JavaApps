package com.SAPTCO.manageAllowance.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
 
/**
 * @author Shady
*/

public class TRTripsDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private AllowanceDetailBean allowanceDetailBean = new AllowanceDetailBean();
	private List<AllowanceDetailBean> detailsList = new ArrayList<AllowanceDetailBean>();
	private List<FinancialElementBean> financialElementList =new ArrayList<FinancialElementBean>();
	private Long tripNumber;
	private String tripCode;
	private String tripDate;
	private String procResult;
	
	/**
	 * @return the financialElementList
	 */
	public List<FinancialElementBean> getFinancialElementList() {
		return financialElementList;
	}
	/**
	 * @param financialElementList the financialElementList to set
	 */
	public void setFinancialElementList(
			List<FinancialElementBean> financialElementList) {
		this.financialElementList = financialElementList;
	}
	
	public AllowanceDetailBean getAllowanceDetailBean() {
		return allowanceDetailBean;
	}
	public void setAllowanceDetailBean(AllowanceDetailBean allowanceDetailBean) {
		this.allowanceDetailBean = allowanceDetailBean;
	}
	public List<AllowanceDetailBean> getDetailsList() {
		return detailsList;
	}
	public void setDetailsList(List<AllowanceDetailBean> detailsList) {
		this.detailsList = detailsList;
	}
	public Long getTripNumber() {
		return tripNumber;
	}
	public void setTripNumber(Long tripNumber) {
		this.tripNumber = tripNumber;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	public String getTripDate() {
		return tripDate;
	}
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	
}