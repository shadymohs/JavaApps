package com.SAPTCO.manageAllowance.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.manageAllowance.dto.AllowanceTypeDto;

public interface AllowanceTypesDao {
	
	public void insertAllowanceType(AllowanceTypeDto allowanceTypeDto)  throws Exception ;
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception ;
	public void updateAllowanceTypes(AllowanceTypeDto allowanceTypeDto) throws Exception;
	public List<SystemBean> getSystemsList() throws Exception;

}
