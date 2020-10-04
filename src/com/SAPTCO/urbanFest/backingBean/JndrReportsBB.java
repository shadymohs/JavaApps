package com.SAPTCO.urbanFest.backingBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.reports.backingBean.ReportsBB;
import com.SAPTCO.urbanFest.bao.UrbanFestBao;
import com.SAPTCO.urbanFest.dto.JndrReportDto;


@ManagedBean(name="jndrReportsBB")
@ViewScoped
public class JndrReportsBB  extends ReportsBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private List<StationBean>  stationList = new ArrayList<StationBean>();
    private JndrReportDto  jndrReportDto = new JndrReportDto();
    private String reportName="";
    private boolean isDetail = false;
    
    @ManagedProperty("#{urbanFestBao}")
	private UrbanFestBao urbanFestBao ;
	
    
    public void viewTripsReport(){
		try { 
			isDetail = false;
			reportName="TRIPS";
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    public void viewTicketsReport(){
		try { 
			isDetail = false;
			reportName="TICKETS";
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    public void viewTripsDetailReport(){
		try { 
			isDetail = true;
			reportName="TRIPS";
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    public void viewTicketsDetailReport(){
		try { 
			isDetail = true;
			reportName="TICKETS";
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    
    
	@Override
	protected Connection getConnection() throws Exception {
		
			 return connectionUrbanfest();
	}

	@Override
	protected ReportInfo getReportInfo() {
		if (isDetail == false)
		{
			if (reportName=="TRIPS")
			    return  ReportInfo.jndr_trips_report;
			else if (reportName=="TICKETS")
				 return  ReportInfo.jndr_tickets_report;
			else
				 return null;
		}
		else
		{
			if (reportName=="TRIPS")
			    return  ReportInfo.jndr_trips_detail_report;
			else if (reportName=="TICKETS")
				 return  ReportInfo.jndr_tickets_detail_report;
			else
				 return null;
		}
		
		
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		
		map.put("fromDate",jndrReportDto.getFromDateAsString() );
		map.put("toDate",jndrReportDto.getToDateAsString());
		
		map.put("fromStation", jndrReportDto.getFromStationId());
		map.put("toStation", jndrReportDto.getToStationId());
		
		map.put("systemId",getUserDetails().getLoggedInSystem().getId());
		map.put("branchId",getUserDetails().getLoggedInBranch().getId());
		
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {		
		return null;
	}
	
	public List<StationBean> getStationList() {
		try {
			stationList = urbanFestBao.getStationList();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return stationList;
	}


	public void setStationList(List<StationBean> stationList) {
		this.stationList = stationList;
	}

	public JndrReportDto getJndrReportDto() {
		return jndrReportDto;
	}

	public void setJndrReportDto(JndrReportDto jndrReportDto) {
		this.jndrReportDto = jndrReportDto;
	}

	public UrbanFestBao getUrbanFestBao() {
		return urbanFestBao;
	}

	public void setUrbanFestBao(UrbanFestBao urbanFestBao) {
		this.urbanFestBao = urbanFestBao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}
	
	
}
