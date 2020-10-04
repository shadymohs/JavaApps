package com.SAPTCO.manageAllowance.bao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.manageAllowance.dto.AllowanceTypeDto;

/**
 * @author alqassemga
 *
 */
public interface AllowanceTypesBao {

	
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception ;
	
	public void insertAllowanceType(AllowanceTypeDto allowanceTypeDto)  throws Exception ;

	public void updateAllowanceTypes(AllowanceTypeDto allowanceTypeDto) throws Exception;

	public List<SystemBean> getSystemsList() throws Exception;
}
