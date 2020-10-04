package com.SAPTCO.manageAllowance.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
import com.SAPTCO.manageAllowance.bao.TRTripsBao;
import com.SAPTCO.manageAllowance.dao.TRTripsDao;
import com.SAPTCO.manageAllowance.dto.NewAllowanceDto;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;

/**
 * @author Shady
*/

@Service("tRTripsBao")
public class TRTripsBaoImpl implements TRTripsBao {

	@Autowired
	private TRTripsDao tRTripsDao;

	@Override
	public TRTripsDto searchTRTrips(TRTripsDto trTripsDto) throws Exception {
		return tRTripsDao.searchTRTrips(trTripsDto);
	}

	@Override
	public TRTripsDto saveTRTrips(TRTripsDto trTripsDto) throws Exception {
		return tRTripsDao.saveTRTrips(trTripsDto);
	}

	@Override
	public TRTripsDto sendToHR(TRTripsDto trTripsDto) throws Exception {
		return tRTripsDao.sendToHR(trTripsDto);
	}

	public TRTripsDao gettRTripsDao() {
		return tRTripsDao;
	}

	public void settRTripsDao(TRTripsDao tRTripsDao) {
		this.tRTripsDao = tRTripsDao;
	}

	@Override
	public NewAllowanceDto saveNewAllowance(NewAllowanceDto NewAllowanceDto)
			throws Exception {
		NewAllowanceDto.setCreatedBy(BaseBB.getUserDetails().getId());
		return tRTripsDao.saveNewAllowance(NewAllowanceDto);
	}

	@Override
	public List<FinancialElementBean> getFinancialElementList(Long systemId) throws Exception {
		return tRTripsDao.getFinancialElementList(systemId);
	}

	@Override
	public String getUpdatorName(Long allowanceID) throws Exception {
		return tRTripsDao.getUpdatorName(allowanceID);
	}
	



}