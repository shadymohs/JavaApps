package com.SAPTCO.manageAllowance.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
import com.SAPTCO.common.ibatis.mapperInterface.ManageAllowance;
import com.SAPTCO.manageAllowance.dao.TRTripsDao;
import com.SAPTCO.manageAllowance.dto.NewAllowanceDto;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;

/**
 * @author Shady
 */

@Repository("tRTripsDao")
public class TRTripsDaoImpl  implements TRTripsDao{
	
	@Autowired
	SqlSession session;

	@Override
	public TRTripsDto searchTRTrips(TRTripsDto trTripsDto) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.searchTRTrips(trTripsDto);
		return trTripsDto;
	}

	@Override
	public TRTripsDto saveTRTrips(TRTripsDto trTripsDto) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.saveTRTrips(trTripsDto);
		return trTripsDto;
	}

	@Override
	public TRTripsDto sendToHR(TRTripsDto trTripsDto) throws Exception {
		session.clearCache();
		trTripsDto.setUpdatedBy(BaseBB.getUserDetails().getId());
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.sendToHR(trTripsDto);
		return trTripsDto;
	}

	@Override
	public NewAllowanceDto saveNewAllowance(NewAllowanceDto NewAllowanceDto)
			throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.saveNewAllowance(NewAllowanceDto);
		return NewAllowanceDto;
		
	}

	@Override
	public List<FinancialElementBean> getFinancialElementList(Long systemId)throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		return (List<FinancialElementBean>) mapper.getFinancialElementList(systemId);
	}

	@Override
	public String getUpdatorName(Long allowanceID) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		return mapper.getUpdatorName(allowanceID);
	}
	
}
