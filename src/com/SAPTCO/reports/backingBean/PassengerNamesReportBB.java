package com.SAPTCO.reports.backingBean;

import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;

/**
 * @author alqassemga
 *
 */
@ManagedBean(name="passengerNamesReportBB")
@ViewScoped
public class PassengerNamesReportBB extends ReportsBB{


	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private String tripCode;
	private Date tripDate = new Date();
	
	
	

	@Override
	protected Connection getConnection() throws Exception {
		
		return connectionTR();
	}

	@Override
	protected ReportInfo getReportInfo() {
		return  ReportInfo.Passenger_Names_report;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		map.put("tripCode",tripCode);
		map.put("tripDate",df.format(tripDate));
		
		return map;
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

	@Override
	protected List getServiceResult() {
		
		return null;
	}

	public String getTripCode() {
		return tripCode;
	}

	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}

	public Date getTripDate() {
		return tripDate;
	}

	public void setTripDate(Date tripDate) {
		this.tripDate = tripDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}
}
