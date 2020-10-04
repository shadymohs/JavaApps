package com.SAPTCO.urbanFest.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.urbanFest.bao.UrbanFestBao;
import com.SAPTCO.urbanFest.dao.UrbanFestDao;

@Service("urbanFestBao")
public class UrbanFestBaoImpl implements UrbanFestBao{

	
	@Autowired
	private UrbanFestDao urbanFestDao;
	
	
	@Override
	public List<StationBean> getStationList() throws Exception {
		
		return urbanFestDao.getStationList() ;
	}


	public UrbanFestDao getUrbanFestDao() {
		return urbanFestDao;
	}


	public void setUrbanFestDao(UrbanFestDao urbanFestDao) {
		this.urbanFestDao = urbanFestDao;
	}

}
