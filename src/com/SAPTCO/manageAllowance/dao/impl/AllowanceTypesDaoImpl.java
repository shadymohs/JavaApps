package com.SAPTCO.manageAllowance.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.ibatis.mapperInterface.General;
import com.SAPTCO.common.ibatis.mapperInterface.ManageAllowance;
import com.SAPTCO.manageAllowance.dao.AllowanceTypesDao;
import com.SAPTCO.manageAllowance.dto.AllowanceTypeDto;


@Repository("allowanceTypesDao")
public class AllowanceTypesDaoImpl implements AllowanceTypesDao{

	
	@Autowired
	SqlSession session;
	
	
	@Override
	public void insertAllowanceType(AllowanceTypeDto allowanceTypeDto)
			throws Exception {
		
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.insertAllowanceType(allowanceTypeDto);
		
		
		
	}

	@Override
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		List<AllowanceTypeBean> allowanceTypes = mapper.getAllAllowanceTypes();
		return allowanceTypes;
	}

	@Override
	public void updateAllowanceTypes(AllowanceTypeDto allowanceTypeDto)
			throws Exception {
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.updateAllowanceTypes(allowanceTypeDto);
		
	}

	@Override
	public List<SystemBean> getSystemsList() throws Exception {
		session.clearCache();
		General mapper = session.getMapper(General.class);
		return mapper.getSystemsList(BaseBB.getUserDetails().getCompany().getId());
	}

}
