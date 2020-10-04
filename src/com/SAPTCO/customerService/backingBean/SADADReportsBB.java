package com.SAPTCO.customerService.backingBean;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import com.SAPTCO.common.ibatis.mapperBeans.ChannelBean;
import com.SAPTCO.customerService.bao.CustomerServiceBao;
import com.SAPTCO.reports.backingBean.ReportsBB;

/**
 * @author Shady
 *
 */

@ManagedBean(name="sADADReportsBB")
@ViewScoped
public class SADADReportsBB extends ReportsBB {

	
	private static final long serialVersionUID = 1L;	
	private final Log logger = LogFactory.getLog(getClass());
	
	private List<ChannelBean> channels = new ArrayList<ChannelBean>();
	private String channelCode;
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private Long isPaid  = (long) 1;
	
	
	@ManagedProperty("#{customerServiceBao}")
	private CustomerServiceBao customerServiceBao;
	
	public CustomerServiceBao getCustomerServiceBao() {
		return customerServiceBao;
	}
	public void setCustomerServiceBao(CustomerServiceBao customerServiceBao) {
		this.customerServiceBao = customerServiceBao;
	}
	
	@PostConstruct
	public void init(){
		try {
			channels = customerServiceBao.getChannelsList();
			channelCode = "000";
			fromDate = new Date();
			toDate = new Date();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void viewReport(){
		try {
			generateReport(false,0);
		} catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
			logger.error(e.getMessage());	
		}
	}
	
	public void viewReportExcel(){
		try {
			generateExcelReport();
		} catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
			logger.error(e.getMessage());	
		}
	}
	
	@Override
	protected Connection getConnection() throws Exception {
		return connectionTR();
	}
	@Override
	protected ReportInfo getReportInfo() {
		if(isPaid==1)
			return ReportInfo.Paid_Sadad;
		 else if (isPaid==2)
			 return ReportInfo.Cancelled_sadad;
		  else  if (isPaid==3)
			   return ReportInfo.Un_Paid_Sadad;
		
		return ReportInfo.Paid_Sadad;}
	
	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		map.put("channelCode",channelCode);
		map.put("fromDate",df.format(fromDate));
		map.put("toDate",df.format(toDate));
		return map;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		return null;
	}
	public List<ChannelBean> getChannels() {
		return channels;
	}
	public void setChannels(List<ChannelBean> channels) {
		this.channels = channels;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getChannelCode() {
		return channelCode;
	}
	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
	}
	public Long getIsPaid() {
		return isPaid;
	}
	public void setIsPaid(Long isPaid) {
		this.isPaid = isPaid;
	}
}
