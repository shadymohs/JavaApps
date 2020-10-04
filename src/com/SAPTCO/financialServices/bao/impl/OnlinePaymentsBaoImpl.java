package com.SAPTCO.financialServices.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.common.ibatis.mapperBeans.OnlinePaymentsBean;
import com.SAPTCO.financialServices.bao.OnlinePaymentsBao;
import com.SAPTCO.financialServices.dao.OnlinePaymentsDao;

/**
 * @author Shady
 *
 */
@Service("onlinePaymentsBao")
public class OnlinePaymentsBaoImpl implements OnlinePaymentsBao{
	
	@Autowired
	private OnlinePaymentsDao onlinePaymentsDao;

	@Override	
	public List<OnlinePaymentsBean> getOnlinePayments(String fromDate,String toDate,
			Long paymentMethodId,Long systemId) throws Exception{
		return onlinePaymentsDao.getOnlinePayments(fromDate,toDate,paymentMethodId,systemId);
	}

	public OnlinePaymentsDao getOnlinePaymentsDao() {
		return onlinePaymentsDao;
	}

	public void setOnlinePaymentsDao(OnlinePaymentsDao onlinePaymentsDao) {
		this.onlinePaymentsDao = onlinePaymentsDao;
	}
	
}