package com.SAPTCO.manageAllowance.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.manageAllowance.bao.AllowanceTypesBao;
import com.SAPTCO.manageAllowance.dao.AllowanceTypesDao;
import com.SAPTCO.manageAllowance.dto.AllowanceTypeDto;

/**
 * @author alqassemga
 *
 */
@Service("allowanceTypesBao")
public class AllowanceTypesBaoImpl  implements AllowanceTypesBao {

	
	@Autowired
	private AllowanceTypesDao allowanceTypesDao;
	
	@Override
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception {
		
		 return allowanceTypesDao.getAllowanceTypes();
	}

	
	@Override
	public void updateAllowanceTypes(AllowanceTypeDto allowanceTypeDto) throws Exception {
		allowanceTypesDao.updateAllowanceTypes(allowanceTypeDto);
		
	}

	@Override
	public void insertAllowanceType(AllowanceTypeDto allowanceTypeDto)
			throws Exception {
		allowanceTypesDao.insertAllowanceType(allowanceTypeDto);
		
	}





	public AllowanceTypesDao getAllowanceTypesDao() {
		return allowanceTypesDao;
	}





	public void setAllowanceTypesDao(AllowanceTypesDao allowanceTypesDao) {
		this.allowanceTypesDao = allowanceTypesDao;
	}


	@Override
	public List<SystemBean> getSystemsList() throws Exception {
		
		return allowanceTypesDao.getSystemsList();
	}






	

}
