package com.SAPTCO.financialServices.bao;

import java.util.List;
import com.SAPTCO.common.ibatis.mapperBeans.OnlinePaymentsBean;

/**
 * @author Shady
 *
 */
public interface OnlinePaymentsBao{

	public List<OnlinePaymentsBean> getOnlinePayments(String fromDate,String toDate,
			Long paymentMethodId,Long systemId) throws Exception;
}
