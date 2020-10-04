package com.SAPTCO.common.ibatis.mapperInterface;

import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.dto.GeneralDto;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;
import com.SAPTCO.common.ibatis.mapperBeans.HrBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemCodesBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserInfBean;

/**
 * @author Shady
 */

public interface General {
	
	public List<BranchesBean> getBranchesList();
	public List<BranchesBean> getBranchesByUser(GeneralDto generalDto);
	public UserInfBean getDispatcherDetails(GeneralDto generalDto);
	public UserInfBean getDriverDetails(GeneralDto generalDto);
	public GeneralDto deleteMethod(GeneralDto generalDto);
	public List<SystemCodesBean> getVeichleTypesList() throws Exception;
	public List<SystemBean> getSystemsList(Long companyId) throws Exception;
	public List<CompanyBean> getCompaniesList() throws Exception;
	public List<HrBranchesBean> getHrBranchesList() throws Exception;
	public List<BaseDto> getHajBranches() throws Exception;
}
