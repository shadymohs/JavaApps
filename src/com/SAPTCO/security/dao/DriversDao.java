package com.SAPTCO.security.dao;

import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.HrBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTAllowanceTypeBean;
import com.SAPTCO.security.dto.BranchesDto;
import com.SAPTCO.security.dto.DriversDto;
/**
 * @author Shady
*/

public interface DriversDao{
	
	public DriversDto getDriverById(DriversDto driversDto) throws Exception;
	public DriversDto updateDriver(DriversDto driversDto) throws Exception;
	public DriversDto searchDrivers(DriversDto driversDto) throws Exception;
	public DriversDto saveDriver(DriversDto driversDto) throws Exception;
	public List<HrBranchesBean> getHrBranchesList() throws Exception;
	public List<BaseDto> getHajBranches() throws Exception;
	
	
//////////////////KAUST drivers allowance/////////////////
	
	public DriversDto saveKaustDriverAllow(DriversDto driverDto) throws Exception ;
	
	public DriversDto saveAllowException(DriversDto driverDto) throws Exception ;
	public DriversDto createMonthlyAllow(DriversDto driversDto) throws Exception;
	public DriversDto deleteMonthlyAllow(DriversDto driversDto) throws Exception;
	public DriversDto integrateMonthlyAllow(DriversDto driversDto) throws Exception;
	public DriversDto deleteAllowException(DriversDto driversDto) throws Exception;
	
	public DriversDto saveAdditionalAllow(DriversDto driverDto) throws Exception;
	public List<KAUSTAllowanceTypeBean> getAllKaustAllowTypes() throws Exception;
	public List<KAUSTAllowanceTypeBean> getKaustAllowTypeByBranch(BranchesDto branchesDto) throws Exception;
	
	 public DriversDto deleteAdditionalAllow(DriversDto driversDto) throws Exception;
}
