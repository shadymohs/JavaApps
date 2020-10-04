package com.SAPTCO.urbanFest.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.urbanFest.dao.UrbanFestDao;

@Repository("urbanFestDao")
public class UrbanFestDaoImpl implements UrbanFestDao{

	@Autowired
    private SqlMapClientTemplate sqlMapClientTemplateUrbanFest;
	
	
	public SqlMapClientTemplate getSqlMapClientTemplateUrbanFest() {
		return sqlMapClientTemplateUrbanFest;
	}


	public void setSqlMapClientTemplateUrbanFest(
			SqlMapClientTemplate sqlMapClientTemplateUrbanFest) {
		this.sqlMapClientTemplateUrbanFest = sqlMapClientTemplateUrbanFest;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<StationBean> getStationList() throws Exception {
		List<StationBean> stationList =  new ArrayList<StationBean>() ;
		stationList =  sqlMapClientTemplateUrbanFest.queryForList("getStationList");
		
		return stationList;
	}

}
