package com.SAPTCO.manageAllowance.dto;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;

public class AllowanceTypeDto {
	
	
	private List<AllowanceTypeBean> allowanceTypeList = new ArrayList<AllowanceTypeBean>();
	private AllowanceTypeBean allowanceType = new AllowanceTypeBean();

	public List<AllowanceTypeBean> getAllowanceTypeList() {
		return allowanceTypeList;
	}

	public void setAllowanceTypeList(List<AllowanceTypeBean> allowanceTypeList) {
		this.allowanceTypeList = allowanceTypeList;
	}

	public AllowanceTypeBean getAllowanceType() {
		return allowanceType;
	}

	public void setAllowanceType(AllowanceTypeBean allowanceType) {
		this.allowanceType = allowanceType;
	}
	

}
