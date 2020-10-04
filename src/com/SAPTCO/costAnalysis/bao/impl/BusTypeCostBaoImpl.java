package com.SAPTCO.costAnalysis.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.BusTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.BusTypeCostBean;
import com.SAPTCO.common.ibatis.mapperBeans.DailySDPTargetBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineCostBean;
import com.SAPTCO.common.ibatis.mapperBeans.SDPBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.costAnalysis.bao.BusTypeCostBao;
import com.SAPTCO.costAnalysis.dao.BusTypeCostDao;
import com.SAPTCO.costAnalysis.dto.BusTypeCostDto;
import com.SAPTCO.costAnalysis.dto.DailySDPTargetDto;
import com.SAPTCO.costAnalysis.dto.LineCostDto;


/**
 * @author alqassemga
 *
 */
@Service("busTypeCostBao")
public class BusTypeCostBaoImpl implements BusTypeCostBao{
	
	@Autowired
	private BusTypeCostDao busTypeCostDao;

	@Override
	public void insertBusTypeCost(BusTypeCostDto busTypeCostDto)
			throws Exception {
		
		busTypeCostDao.insertBusTypeCost(busTypeCostDto);
		
	}

	@Override
	public void updateBusTypeCost(BusTypeCostDto busTypeCostDto)
			throws Exception {
		
		busTypeCostDao.updateBusTypeCost(busTypeCostDto);
	}

	@Override
	public List<BusTypeCostBean> getBusTypeCost() throws Exception {
		
		return busTypeCostDao.getBusTypeCost();
	}

	
	
	public BusTypeCostDao getBusTypeCostDao() {
		return busTypeCostDao;
	}

	public void setBusTypeCostDao(BusTypeCostDao busTypeCostDao) {
		this.busTypeCostDao = busTypeCostDao;
	}

	@Override
	public List<BusTypeBean> getBusTypesList() throws Exception {
		
		return busTypeCostDao.getBusTypesList();
	}

	@Override
	public Long checkOverlappingPeriod(BusTypeCostDto busTypeCostDto)
			throws Exception {
		
		return busTypeCostDao.checkOverlappingPeriod(busTypeCostDto);
	}

	//////////////////////////////////////////////////////////////////////
	@Override
	public void insertDailySDPTarget(DailySDPTargetDto dailySDPTargetDto)
			throws Exception {
		busTypeCostDao.insertDailySDPTarget(dailySDPTargetDto);
		
	}

	@Override
	public void updateDailySDPTarget(DailySDPTargetDto dailySDPTargetDto)
			throws Exception {
		
		busTypeCostDao.updateDailySDPTarget(dailySDPTargetDto);
	}

	@Override
	public List<DailySDPTargetBean> getDailySDPTarget() throws Exception {
	
		return busTypeCostDao.getDailySDPTarget();
	}

	@Override
	public List<SDPBean> getSDPList() throws Exception {
		
		return busTypeCostDao.getSDPList();
	}

	@Override
	public Long checkUniqueSDPTargetDate(DailySDPTargetDto dailySDPTargetDto)
			throws Exception {
		
		return busTypeCostDao.checkUniqueSDPTargetDate(dailySDPTargetDto); 
	}

	////////////////////////////////////////////////////////////
	@Override
	public void insertLineCost(LineCostDto lineCostDto) throws Exception {
		busTypeCostDao.insertLineCost(lineCostDto);
		
	}

	@Override
	public void updateLineCost(LineCostDto lineCostDto) throws Exception {
		busTypeCostDao.updateLineCost(lineCostDto);
		
	}

	@Override
	public List<LineCostBean> getLineCostList() throws Exception {
		
		return busTypeCostDao.getLineCostList(); 
	}

	@Override
	public List<LineBean> getLinesList() throws Exception {
		
		return busTypeCostDao.getLinesList(); 
	}

	@Override
	public Long checkLineCostOverlappPeriod(LineCostDto lineCostDto)
			throws Exception {
		
		return busTypeCostDao.checkLineCostOverlappPeriod(lineCostDto); 
	}

	@Override
	public void insertLineCostDetail(LineCostDto lineCostDto) throws Exception {
		busTypeCostDao.insertLineCostDetail(lineCostDto);
		
	}

	@Override
	public void updateLineCostDetail(LineCostDto lineCostDto) throws Exception {
		busTypeCostDao.updateLineCostDetail(lineCostDto);
		
	}

	@Override
	public List<LineCostBean> getLineCostDetailList(Long lineCostDtlId) throws Exception {
		
		return busTypeCostDao.getLineCostDetailList( lineCostDtlId);
	}

	@Override
	public Long checkLineCostDetailOverlappPeriod(LineCostDto lineCostDto)
			throws Exception {
		
		return busTypeCostDao.checkLineCostDetailOverlappPeriod(lineCostDto);
		
	}

	@Override
	public List<StationBean> getStationList() throws Exception {
		
		return busTypeCostDao.getStationList();
	}

}
