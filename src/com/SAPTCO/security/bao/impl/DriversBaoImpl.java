package com.SAPTCO.security.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.HrBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTAllowanceTypeBean;
import com.SAPTCO.security.bao.DriversBao;
import com.SAPTCO.security.dao.DriversDao;
import com.SAPTCO.security.dto.BranchesDto;
import com.SAPTCO.security.dto.DriversDto;

/**
 * @author Shady
*/

@Service("driversBao")
public class DriversBaoImpl implements DriversBao {

	@Autowired
	private DriversDao driversDao;

	public DriversDto getDriverById(DriversDto driversDto) throws Exception {
		return driversDao.getDriverById(driversDto);
	}

	
	public DriversDto updateDriver(DriversDto driversDto) throws Exception {
		return driversDao.updateDriver(driversDto);
	}
 
	

	public DriversDao getDriversDao() {
		return driversDao;
	}

	public void setDriversDao(DriversDao driversDao) {
		this.driversDao = driversDao;
	}


	@Override
	public DriversDto searchDrivers(DriversDto driversDto) throws Exception {
		return driversDao.searchDrivers(driversDto);
	}


	@Override
	public DriversDto saveDriver(DriversDto driversDto) throws Exception {
		return driversDao.saveDriver(driversDto);
	}
	
	@Override
	public List<HrBranchesBean> getHrBranchesList() throws Exception
	{
		return driversDao.getHrBranchesList();
	}


	@Override
	public DriversDto saveKaustDriverAllow(DriversDto driverDto) throws Exception {
		return driversDao.saveKaustDriverAllow(driverDto);
	}


	@Override
	public DriversDto saveAllowException(DriversDto driverDto) throws Exception {
		
		 return driversDao.saveAllowException(driverDto);
	}


	@Override
	public List<BaseDto> getHajBranches() throws Exception {
		return driversDao.getHajBranches();
	}


	@Override
	public DriversDto createMonthlyAllow(DriversDto driversDto)
			throws Exception {
		return driversDao.createMonthlyAllow(driversDto);
	}


	@Override
	public DriversDto deleteMonthlyAllow(DriversDto driversDto)throws Exception {
		return driversDao.deleteMonthlyAllow(driversDto);
	}


	@Override
	public DriversDto deleteAllowException(DriversDto driversDto)
			throws Exception {
		return driversDao.deleteAllowException(driversDto);
	}


	@Override
	public DriversDto integrateMonthlyAllow(DriversDto driversDto)
			throws Exception {
		return driversDao.integrateMonthlyAllow(driversDto);
	}


	@Override
	public DriversDto saveAdditionalAllow(DriversDto driverDto)
			throws Exception {
		
		return driversDao.saveAdditionalAllow(driverDto);
	}


	@Override
	public List<KAUSTAllowanceTypeBean> getAllKaustAllowTypes()
			throws Exception {
		
		return driversDao.getAllKaustAllowTypes();
	}


	@Override
	public List<KAUSTAllowanceTypeBean> getKaustAllowTypeByBranch(
			BranchesDto branchesDto) throws Exception {
		
		return driversDao.getKaustAllowTypeByBranch(branchesDto);
	}


	@Override
	public DriversDto deleteAdditionalAllow(DriversDto driversDto)
			throws Exception {
	
		return driversDao.deleteAdditionalAllow(driversDto);
	}

}