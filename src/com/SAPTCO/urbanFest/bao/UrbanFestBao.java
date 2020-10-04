package com.SAPTCO.urbanFest.bao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.StationBean;

public interface UrbanFestBao {

	
	public List<StationBean> getStationList() throws Exception;
	
}
