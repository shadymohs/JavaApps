package com.SAPTCO.costAnalysis.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.ibatis.mapperBeans.BusTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.BusTypeCostBean;
import com.SAPTCO.common.ibatis.mapperBeans.DailySDPTargetBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineCostBean;
import com.SAPTCO.common.ibatis.mapperBeans.SDPBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.costAnalysis.dao.BusTypeCostDao;
import com.SAPTCO.costAnalysis.dto.BusTypeCostDto;
import com.SAPTCO.costAnalysis.dto.DailySDPTargetDto;
import com.SAPTCO.costAnalysis.dto.LineCostDto;


@Repository("busTypeCostDao")
public class BusTypeCostDaoImpl implements BusTypeCostDao{

	
	@Autowired
    private SqlMapClientTemplate sqlMapClientTemplateCostAnaysis;

	@Override
	public void insertBusTypeCost(BusTypeCostDto busTypeCostDto)
			throws Exception {
		
		//BusTypeCostBean busTypeCost = new BusTypeCostBean();
		//busTypeCost.setBusTypeCostAmount(busTypeCostDto.getBusTypeCost().getBusTypeCostAmount());
		this.sqlMapClientTemplateCostAnaysis.insert("insertBusTypeCost", busTypeCostDto.getBusTypeCost());

		
	}

	@Override
	public void updateBusTypeCost(BusTypeCostDto busTypeCostDto)
			throws Exception {
		
		this.sqlMapClientTemplateCostAnaysis.update("updateBusTypeCost", busTypeCostDto.getBusTypeCost());

	}

	@Override
	public List<BusTypeCostBean> getBusTypeCost() throws Exception {
		//session.clearCache();
		//CostAnalysis mapper = session.getMapper(CostAnalysis.class);
		List<BusTypeCostBean> busTypeCostList = new ArrayList<BusTypeCostBean>();
		busTypeCostList  =  sqlMapClientTemplateCostAnaysis.queryForList("findAllBusTypeCost");
		//busTypeCostList = mapper.getBusTypeCostList();
		return busTypeCostList;
	}

	@Override
	public List<BusTypeBean> getBusTypesList() throws Exception {
		
		List<BusTypeBean> busTypesList =  new ArrayList<BusTypeBean>() ;
		busTypesList =  sqlMapClientTemplateCostAnaysis.queryForList("findAllBusTypes");
		
		return busTypesList;
		
	}
	
	public Long checkOverlappingPeriod(BusTypeCostDto busTypeCostDto)
			throws Exception {
		Long cnt = (long) 0 ;
	
		cnt =(Long) this.sqlMapClientTemplateCostAnaysis.queryForObject("checkOverlapPeriod", busTypeCostDto.getBusTypeCost());
		return cnt;

	}


	@Override
	public void insertDailySDPTarget(DailySDPTargetDto dailySDPTargetDto)
			throws Exception {
		this.sqlMapClientTemplateCostAnaysis.insert("insertDailySDPTarget", dailySDPTargetDto.getDailySDPTargetBean());
		
	}


	@Override
	public void updateDailySDPTarget(DailySDPTargetDto dailySDPTargetDto)
			throws Exception {
		this.sqlMapClientTemplateCostAnaysis.update("updateDailySDPTarget", dailySDPTargetDto.getDailySDPTargetBean());
		
		
	}


	@Override
	public List<DailySDPTargetBean> getDailySDPTarget() throws Exception {
		List<DailySDPTargetBean> dailySDPTargetList = new ArrayList<DailySDPTargetBean>();
		dailySDPTargetList  =  sqlMapClientTemplateCostAnaysis.queryForList("findDailySDPTarget");
		return dailySDPTargetList;
	}


	@Override
	public List<SDPBean> getSDPList() throws Exception {
		List<SDPBean> SDPList =  new ArrayList<SDPBean>() ;
		SDPList =  sqlMapClientTemplateCostAnaysis.queryForList("findSDPs");
		
		return SDPList;
	}


	@Override
	public Long checkUniqueSDPTargetDate(DailySDPTargetDto dailySDPTargetDto)
			throws Exception {
		Long cnt = (long) 0 ;
		
		cnt =(Long) this.sqlMapClientTemplateCostAnaysis.queryForObject("checkUniqueSDPTargetDate",dailySDPTargetDto.getDailySDPTargetBean());
		return cnt;
	}

////////////////////////////////////////////////////////////////
	@Override
	public void insertLineCost(LineCostDto lineCostDto) throws Exception {
		this.sqlMapClientTemplateCostAnaysis.insert("insertLineCost", lineCostDto.getLineCostBean());
		
	}


	@Override
	public void updateLineCost(LineCostDto lineCostDto) throws Exception {
		this.sqlMapClientTemplateCostAnaysis.update("updateLineCost", lineCostDto.getLineCostBean());
		
	}


	@Override
	public List<LineCostBean> getLineCostList() throws Exception {
		List<LineCostBean> linesList =  new ArrayList<LineCostBean>() ;
		linesList =  sqlMapClientTemplateCostAnaysis.queryForList("findAllLinesCost");
		
		return linesList;
	}


	@Override
	public List<LineBean> getLinesList() throws Exception {
		List<LineBean> linesList =  new ArrayList<LineBean>() ;
		linesList =  sqlMapClientTemplateCostAnaysis.queryForList("findAllLines");
		
		return linesList;
	}


	@Override
	public Long checkLineCostOverlappPeriod(LineCostDto lineCostDto)
			throws Exception {
        Long cnt = (long) 0 ;
		
		cnt =(Long) this.sqlMapClientTemplateCostAnaysis.queryForObject("checkLineCostOverlappPeriod",lineCostDto.getLineCostBean());
		return cnt;
	}

//////////////////////////////////////////////////////////////
	@Override
	public void insertLineCostDetail(LineCostDto lineCostDto) throws Exception {
		this.sqlMapClientTemplateCostAnaysis.insert("insertLineCostDetail", lineCostDto.getLineCostDetailBean());
		
	}


	@Override
	public void updateLineCostDetail(LineCostDto lineCostDto) throws Exception {
		this.sqlMapClientTemplateCostAnaysis.update("updateLineCostDetail", lineCostDto.getLineCostDetailBean());
		
	}


	@Override
	public List<LineCostBean> getLineCostDetailList(Long lineCostDtlId ) throws Exception {
		List<LineCostBean> linesList =  new ArrayList<LineCostBean>() ;
		linesList =  sqlMapClientTemplateCostAnaysis.queryForList("findAllLinesCostDetail" , lineCostDtlId);
		
		return linesList;
	}


	@Override
	public Long checkLineCostDetailOverlappPeriod(LineCostDto lineCostDto)
			throws Exception {
        Long cnt = (long) 0 ;
		
		cnt =(Long) this.sqlMapClientTemplateCostAnaysis.queryForObject("checkLineCostDetailOverlappPeriod",lineCostDto.getLineCostDetailBean());
		return cnt;
	}

	
	@Override
	public List<StationBean> getStationList() throws Exception {
		List<StationBean> stationList =  new ArrayList<StationBean>() ;
		stationList =  sqlMapClientTemplateCostAnaysis.queryForList("getStationList");
		
		return stationList;
	}

	public SqlMapClientTemplate getSqlMapClientTemplateCostAnaysis() {
		return sqlMapClientTemplateCostAnaysis;
	}

	public void setSqlMapClientTemplateCostAnaysis(
			SqlMapClientTemplate sqlMapClientTemplateCostAnaysis) {
		this.sqlMapClientTemplateCostAnaysis = sqlMapClientTemplateCostAnaysis;
	}
}
