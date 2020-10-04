package com.SAPTCO.financialServices.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.ibatis.mapperBeans.OnlinePaymentsBean;
import com.SAPTCO.financialServices.dao.OnlinePaymentsDao;


@Repository("onlinePaymentsDao")
public class OnlinePaymentsDaoImpl implements OnlinePaymentsDao{
	
	@Autowired
	private DataSource dataSourceSadadPayment;
	@Autowired
	private DataSource dataSourceCCPayment;

	public DataSource getDataSourceCCPayment() {
		return dataSourceCCPayment;
	}

	public void setDataSourceCCPayment(DataSource dataSourceCCPayment) {
		this.dataSourceCCPayment = dataSourceCCPayment;
	}

	@Value("#{wsConfig['limo.credit']}")
	private String creditLimo;
	@Value("#{wsConfig['kentakart.credit']}")
	private String creditKentaKard;
	@Value("#{wsConfig['cnc.credit']}")
	private String creditCnc;
	
	@Value("#{wsConfig['limo.sadad']}")
	private String sadadLimo;
	@Value("#{wsConfig['kentakart.sadad']}")
	private String sadadKentaKard;
	@Value("#{wsConfig['cnc.sadad']}")
	private String sadadCnc;

	@Override
	public List<OnlinePaymentsBean> getOnlinePayments(String fromDate, String toDate, Long paymentMethodId,
			Long systemId) throws Exception {
		List<OnlinePaymentsBean> onlinePaymentsList = new ArrayList<OnlinePaymentsBean>();
		Connection con = paymentMethodId.equals(1L)? dataSourceCCPayment.getConnection() : dataSourceSadadPayment.getConnection();
		Statement stmt = con.createStatement();
        String creditSQL = "SELECT pr.ReservationNumber , pr.PaidAmount , pr.CreatedOn , pr.TransactionNumber " +
        		"FROM [SaptcoPaymentSystem].[dbo].[PaymentLog] pl inner join " +
        		"[SaptcoPaymentSystem].[dbo].[PaymentRequest] pr on pl.FK_PaymentRequestId = pr.Id " +
        		"Where pl.FK_PaymentMode = 11 and pl.FK_StatusId = 23 and pr.FK_SystemId = " +
        		(systemId.equals(1L)? creditLimo : systemId.equals(2L)? creditKentaKard : creditCnc) +
        		"and pl.CreatedOn >= '" + fromDate + " 00:00:00.000' and pl.CreatedOn < '" + toDate + " 00:00:00.000' order by 1 desc";
        String sadadSQL = "SELECT BillIDPerClient, BillID, BillAmount, CreationDt, ExpiryDt,SADADCreationDt " +
        		"FROM [SADADPaymentGateway].[dbo].[Bills] " +
				"where status = 3 and CreationDt > '" + fromDate +
				"' and CreationDt < '" + toDate + 
				"' and systemId = " + (systemId.equals(1L)? sadadLimo : systemId.equals(2L)? sadadKentaKard : sadadCnc) + " order by CreationDt";
        ResultSet rs = stmt.executeQuery(paymentMethodId.equals(1L)? creditSQL : sadadSQL);
        // Iterate through the data in the result set and display it.
        while (rs.next()) {
        	OnlinePaymentsBean myBean = new OnlinePaymentsBean();
        	if(paymentMethodId.equals(1L)){
        		myBean.setReservationNumber(rs.getString("ReservationNumber"));
        		myBean.setPaidAmount(rs.getString("PaidAmount"));
            	myBean.setCreationDate(rs.getString("CreatedOn"));
        		myBean.setTransactionNumber(rs.getString("TransactionNumber"));
        	}else{
        		myBean.setBillIdPerClient(rs.getString("BillIDPerClient"));
        		myBean.setBillID(rs.getString("BillID"));
        		myBean.setPaidAmount(rs.getString("BillAmount"));
        		myBean.setCreationDate(rs.getString("CreationDt"));
        		myBean.setExpiryDate(rs.getString("ExpiryDt"));
        		myBean.setSadadCreationDate(rs.getString("SADADCreationDt"));     		
        	}
            onlinePaymentsList.add(myBean);
        }
		return onlinePaymentsList;
	}
	
	public DataSource getDataSourceSadadPayment() {
		return dataSourceSadadPayment;
	}

	public void setDataSourceSadadPayment(DataSource dataSourceSadadPayment) {
		this.dataSourceSadadPayment = dataSourceSadadPayment;
	}
	
}
