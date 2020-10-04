package com.SAPTCO.customerService.bao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.ChannelBean;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;

/**
 * @author Shady
 *
 */
public interface CustomerServiceBao {

	public List<ChannelBean> getChannelsList() throws Exception ;
	public List<StationBean> getStationsList(Boolean isVip) throws Exception ;

}
