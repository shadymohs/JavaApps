package com.SAPTCO.financialServices.dao;

import java.util.List;
import com.SAPTCO.common.ibatis.mapperBeans.OnlinePaymentsBean;

public interface OnlinePaymentsDao {
	
	public List<OnlinePaymentsBean> getOnlinePayments(String fromDate,String toDate,
			Long paymentMethodId,Long systemId) throws Exception;
	
}
