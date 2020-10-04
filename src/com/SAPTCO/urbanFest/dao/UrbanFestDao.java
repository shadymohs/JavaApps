package com.SAPTCO.urbanFest.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.StationBean;

public interface UrbanFestDao {
	
	public List<StationBean> getStationList() throws Exception;

}
