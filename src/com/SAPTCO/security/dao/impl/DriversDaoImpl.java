package com.SAPTCO.security.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.HrBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTAllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperInterface.General;
import com.SAPTCO.common.ibatis.mapperInterface.Security;
import com.SAPTCO.security.dao.DriversDao;
import com.SAPTCO.security.dto.BranchesDto;
import com.SAPTCO.security.dto.DriversDto;

/**
 * @author Shady
 */

@Repository("driversDao")
public class DriversDaoImpl  implements DriversDao{
	
	@Autowired
	SqlSession session;
	public DriversDto getDriverById(DriversDto driversDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.getDriverById(driversDto);
		return driversDto;
	}
	
	
	
	public DriversDto updateDriver(DriversDto driversDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.updateDriver(driversDto);
		return driversDto;
	}



	@Override
	public DriversDto searchDrivers(DriversDto driversDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.searchDrivers(driversDto);
		return driversDto;
	}



	@Override
	public DriversDto saveDriver(DriversDto driversDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.saveDriver(driversDto);
		return driversDto;
	}

	
	@Override
	public List<HrBranchesBean> getHrBranchesList() throws Exception
	{
		session.clearCache();
		General mapper = session.getMapper(General.class);
		return mapper.getHrBranchesList();
	}



	@Override
	public DriversDto saveKaustDriverAllow(DriversDto driverDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.saveKaustDriverAllow(driverDto);
		return driverDto;
	}



	@Override
	public DriversDto saveAllowException(DriversDto driverDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.saveAllowException(driverDto);
		return driverDto;
	}



	@Override
	public List<BaseDto> getHajBranches() throws Exception {
		session.clearCache();
		General mapper = session.getMapper(General.class);
		return mapper.getHajBranches();
	}



	@Override
	public DriversDto createMonthlyAllow(DriversDto driversDto)
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.createMonthlyAllow(driversDto);
		return driversDto;
	}

	@Override
	public DriversDto deleteMonthlyAllow(DriversDto driversDto)
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.deleteMonthlyAllow(driversDto);
		return driversDto;
	}



	@Override
	public DriversDto deleteAllowException(DriversDto driversDto)
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.deleteAllowException(driversDto);
		return driversDto;
	}



	@Override
	public DriversDto integrateMonthlyAllow(DriversDto driversDto)
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.integrateMonthlyAllow(driversDto);
		return driversDto;
	}



	@Override
	public DriversDto saveAdditionalAllow(DriversDto driverDto)
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.saveAdditionalAllow(driverDto);
		return driverDto;
	}



	@Override
	public List<KAUSTAllowanceTypeBean> getAllKaustAllowTypes()
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		return mapper.getAllKaustAllowTypes();
	}



	@Override
	public List<KAUSTAllowanceTypeBean> getKaustAllowTypeByBranch(
			BranchesDto branchesDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		return mapper.getKaustAllowTypeByBranch(branchesDto);
	}



	@Override
	public DriversDto deleteAdditionalAllow(DriversDto driversDto)
			throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.deleteAdditionalAllow(driversDto);
		return driversDto;
	}

	
}
