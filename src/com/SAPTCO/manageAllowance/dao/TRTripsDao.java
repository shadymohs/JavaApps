package com.SAPTCO.manageAllowance.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
import com.SAPTCO.manageAllowance.dto.NewAllowanceDto;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;

/**
 * @author Shady
*/

public interface TRTripsDao{
	
	public TRTripsDto searchTRTrips(TRTripsDto trTripsDto) throws Exception;
	public TRTripsDto saveTRTrips(TRTripsDto trTripsDto) throws Exception;
	public TRTripsDto sendToHR(TRTripsDto trTripsDto) throws Exception;
	public NewAllowanceDto saveNewAllowance(NewAllowanceDto NewAllowanceDto) throws Exception;
	public List<FinancialElementBean> getFinancialElementList(Long systemId) throws Exception;
	public String getUpdatorName(Long allowanceID) throws Exception;
}
