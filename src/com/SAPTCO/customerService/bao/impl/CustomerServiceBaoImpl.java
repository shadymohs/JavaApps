package com.SAPTCO.customerService.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.common.ibatis.mapperBeans.ChannelBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.customerService.bao.CustomerServiceBao;
import com.SAPTCO.customerService.dao.CustomerServiceDao;


/**
 * @author Shady
 *
 */
@Service("customerServiceBao")
public class CustomerServiceBaoImpl implements CustomerServiceBao{
	
	@Autowired
	private CustomerServiceDao customerServiceDao;

	public CustomerServiceDao getCustomerServiceDao() {
		return customerServiceDao;
	}

	public void setCustomerServiceDao(CustomerServiceDao customerServiceDao) {
		this.customerServiceDao = customerServiceDao;
	}

	@Override
	public List<ChannelBean> getChannelsList() throws Exception {
		return customerServiceDao.getChannelsList();
	}

	@Override
	public List<StationBean> getStationsList(Boolean isVip) throws Exception {
		return customerServiceDao.getStationsList(isVip);
	}

}
