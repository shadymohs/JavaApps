package com.SAPTCO.costAnalysis.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.BusTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.BusTypeCostBean;
import com.SAPTCO.common.ibatis.mapperBeans.DailySDPTargetBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineCostBean;
import com.SAPTCO.common.ibatis.mapperBeans.SDPBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.costAnalysis.dto.BusTypeCostDto;
import com.SAPTCO.costAnalysis.dto.DailySDPTargetDto;
import com.SAPTCO.costAnalysis.dto.LineCostDto;

public interface BusTypeCostDao {

	
	public void insertBusTypeCost(BusTypeCostDto busTypeCostDto)  throws Exception ;
	public void updateBusTypeCost(BusTypeCostDto busTypeCostDto)  throws Exception ;
	public List<BusTypeCostBean> getBusTypeCost() throws Exception ;
	public List<BusTypeBean> getBusTypesList() throws Exception ;
	public Long checkOverlappingPeriod(BusTypeCostDto busTypeCostDto) throws Exception;
	
	
	//////////////////////////////////////////////
	public void insertDailySDPTarget(DailySDPTargetDto dailySDPTargetDto)  throws Exception ;
	public void updateDailySDPTarget(DailySDPTargetDto dailySDPTargetDto)  throws Exception ;
	public List<DailySDPTargetBean> getDailySDPTarget() throws Exception ;
	public List<SDPBean> getSDPList() throws Exception ;
	public Long checkUniqueSDPTargetDate(DailySDPTargetDto dailySDPTargetDto) throws Exception;
	
	//////////////////////////////////////////////////
	public void insertLineCost(LineCostDto lineCostDto)  throws Exception ;
	public void updateLineCost(LineCostDto lineCostDto)  throws Exception ;
	public List<LineCostBean> getLineCostList() throws Exception ;
	public List<LineBean> getLinesList() throws Exception ;
	public Long checkLineCostOverlappPeriod(LineCostDto lineCostDto) throws Exception;
	
	
	//////////////////////////////////////////////////
	public void insertLineCostDetail(LineCostDto lineCostDto)  throws Exception ;
	public void updateLineCostDetail(LineCostDto lineCostDto)  throws Exception ;
	public List<LineCostBean> getLineCostDetailList(Long lineCostDtlId) throws Exception ;
	public Long checkLineCostDetailOverlappPeriod(LineCostDto lineCostDto) throws Exception;
	
	
	public List<StationBean> getStationList() throws Exception;
	
}
