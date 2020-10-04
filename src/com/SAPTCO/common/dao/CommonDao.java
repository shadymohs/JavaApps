package com.SAPTCO.common.dao;

import java.util.List;

import com.SAPTCO.common.dto.CommonDto;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;

public interface CommonDao {
	
	public CommonDto findUserbranchesByName(CommonDto commonDto) throws Exception;
	public CommonDto authenticationUser(CommonDto commonDto) throws Exception;
	public CommonDto retrieveAuthorizedPrograms(CommonDto commonDto) throws Exception;
	public CommonDto findUserSystemsByName(CommonDto commonDto) throws Exception;
	public List<CompanyBean> getCompaniesList() throws Exception;
	public String getAppVersion();
	public String getAppVersionDate();
	public WSConfigDTO getILSResourceModificationWSConfig();
	public WSConfigDTO getTripsScheduleWSConfig();
	public WSConfigDTO getLimoHajFinIntegWSConfig(int serviceConfig, boolean isNewService);

}
