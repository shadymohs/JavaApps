package com.SAPTCO.customerService.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.ibatis.mapperBeans.ChannelBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.customerService.dao.CustomerServiceDao;


@Repository("customerServiceDao")
public class CustomerServiceDaoImpl implements CustomerServiceDao{

	
	@Autowired
    private SqlMapClientTemplate sqlMapClientTemplateTR;

	public SqlMapClientTemplate getSqlMapClientTemplateTR() {
		return sqlMapClientTemplateTR;
	}

	public void setSqlMapClientTemplateTR(
			SqlMapClientTemplate sqlMapClientTemplateTR) {
		this.sqlMapClientTemplateTR = sqlMapClientTemplateTR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ChannelBean> getChannelsList() throws Exception {
		return sqlMapClientTemplateTR.queryForList("getChannelsList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationBean> getStationsList(Boolean isVip) throws Exception {
		return sqlMapClientTemplateTR.queryForList("getStations",isVip);
	}
}
