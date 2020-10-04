package com.SAPTCO.financialServices.backingBean;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.reports.backingBean.ReportsBB;
import com.SAPTCO.financialServices.bao.OnlinePaymentsBao;

/**
*
* @author Shady
*/

@ManagedBean(name="onlinePaymentsBB")
@ViewScoped
public class OnlinePaymentsBB extends ReportsBB{

	private static final long serialVersionUID = 1L;

	private final Log logger = LogFactory.getLog(getClass());
	
	@ManagedProperty("#{onlinePaymentsBao}")
	private OnlinePaymentsBao onlinePaymentsBao;
	
	private Long systemId = 1L;
	private Long paymentMethodId = 1L;
	private String fromDate;
	private String toDate;
	private Boolean isExcel = false;
	
	@PostConstruct
	public void init(){
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		fromDate = df.format(new Date());
		toDate = df.format(new Date());
	}
	
	public void viewReport(){
		try{
			if(!systemId.equals(4L) && paymentMethodId == null)
				addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						"paymentMethodRequired",
						FacesMessage.SEVERITY_ERROR);
			else{
				isExcel = false;
				generateReport(false,0);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void viewReportExcel(){
		try{
			if(!systemId.equals(4L) && paymentMethodId == null)
				addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						"paymentMethodRequired",
						FacesMessage.SEVERITY_ERROR);
			else{
				isExcel = true;
				generateExcelReport();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	protected ReportInfo getReportInfo() {
		if(isExcel){
			if(systemId.equals(4L))
				return ReportInfo.Online_Payments_TR_Excel;
			else{
				if(paymentMethodId.equals(1L))
					return ReportInfo.Online_Payments_Credit_Excel;
				else
					return ReportInfo.Online_Payments_SADAD_Excel;
			}
		}else{
			if(systemId.equals(4L))
				return ReportInfo.Online_Payments_TR;
			else{
				if(paymentMethodId.equals(1L))
					return ReportInfo.Online_Payments_Credit;
				else
					return ReportInfo.Online_Payments_SADAD;
			}
		}
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		map.put("fromDate",fromDate);
		map.put("toDate",toDate);
		map.put("systemId",systemId.toString());
		if(!systemId.equals(4L))
			map.put("paymentMethodId",paymentMethodId.toString());
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		try {
			if(systemId.equals(4L))
				return null;
			else
				return onlinePaymentsBao.getOnlinePayments(fromDate, toDate, paymentMethodId, systemId);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	protected Connection getConnection() throws Exception {
		if(systemId.equals(4L))
			return connectionTR();
		else
			return null;
	}

	public OnlinePaymentsBao getOnlinePaymentsBao() {
		return onlinePaymentsBao;
	}

	public void setOnlinePaymentsBao(OnlinePaymentsBao onlinePaymentsBao) {
		this.onlinePaymentsBao = onlinePaymentsBao;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public Long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public Boolean getIsExcel() {
		return isExcel;
	}

	public void setIsExcel(Boolean isExcel) {
		this.isExcel = isExcel;
	}
}
