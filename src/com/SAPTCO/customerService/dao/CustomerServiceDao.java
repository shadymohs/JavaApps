package com.SAPTCO.customerService.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.ChannelBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;

public interface CustomerServiceDao {

	public List<ChannelBean> getChannelsList() throws Exception ;
	public List<StationBean> getStationsList(Boolean isVip) throws Exception ;
	
}
