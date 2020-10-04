package com.SAPTCO.common.bao;

import java.io.Serializable;
import java.util.List;

import com.SAPTCO.common.dto.CommonDto;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;

public interface CommonBao extends Serializable{
	
	public CommonDto findUserbranchesByName(CommonDto commonDto) throws Exception;
	public CommonDto findUserSystemsByName(CommonDto commonDto) throws Exception;
	public CommonDto authenticationUser(CommonDto commonDto) throws Exception;
	public CommonDto retrieveAuthorizedPrograms(CommonDto commonDto) throws Exception;
	public List<CompanyBean> getCompaniesList() throws Exception;
	public WSConfigDTO getILSResourceModificationWSConfig();
	public WSConfigDTO getTripsScheduleWSConfig();

}
