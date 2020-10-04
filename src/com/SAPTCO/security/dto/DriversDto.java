package com.SAPTCO.security.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.DriversBean;
 
/**
 * @author Shady
*/

public class DriversDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private List<DriversBean> driversList = new ArrayList<DriversBean>();
	private DriversBean driverObj = new DriversBean();
	private String codeDesc;
	private String procResult;
	private Integer month;
	private Integer year;
	private Long systemId;
	private Long branchId;
	
	
	public List<DriversBean> getDriveresList() {
		return driversList;
	}
	public void setDriveresList(List<DriversBean> driversList) {
		this.driversList = driversList;
	}	
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public DriversBean getDriverObj() {
		return driverObj;
	}
	public void setDriverObj(DriversBean driverObj) {
		this.driverObj = driverObj;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	
}