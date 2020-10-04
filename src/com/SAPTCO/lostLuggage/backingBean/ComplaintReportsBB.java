package com.SAPTCO.lostLuggage.backingBean;

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
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageStatus;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;
import com.SAPTCO.lostLuggage.dto.ComplaintDto;
import com.SAPTCO.reports.backingBean.ReportsBB;

@ManagedBean(name="complaintReportsBB")
@ViewScoped
public class ComplaintReportsBB  extends ReportsBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private List<LostLuggageStatus>   statusList = new  ArrayList<LostLuggageStatus>();
	private List<StationBean>  stationList = new ArrayList<StationBean>();
	private ComplaintDto complaintDto = new ComplaintDto();
	private boolean isHistory = false;
	
	@ManagedProperty("#{lostLuggageBao}")
	private LostLuggageBao lostLuggageBao;

	@Override
	protected Connection getConnection() throws Exception {
		return connectionLostLuggage();
	}

	@Override
	protected ReportInfo getReportInfo() {
		if (isHistory == false)
		   return  ReportInfo.complaints_report;
		else
			return  ReportInfo.complaint_history_report;
	}
	
	public void viewReport(){
		try {isHistory = false;
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	public void viewHistoryReport(){
		try {isHistory = true;
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		map.put("fromDate",complaintDto.getComplaintObj().getFromDateAsString() );
		map.put("toDate",complaintDto.getComplaintObj().getToDateAsString());
		
		map.put("statusId", complaintDto.getComplaintObj().getCurrentStatus().getId());
		map.put("stationId", complaintDto.getComplaintObj().getStationId());
		
		map.put("systemId",getUserDetails().getLoggedInSystem().getId());
		map.put("branchId",getUserDetails().getLoggedInBranch().getId());
		map.put("compId", complaintDto.getComplaintObj().getLuggageComplaintId());
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		
		return null;
	}

	public List<LostLuggageStatus> getStatusList() {
		List<LostLuggageStatus> compStatusList= new ArrayList<LostLuggageStatus>();
		try {
			statusList = lostLuggageBao.getLostLuggageStatusList();
			int  i = 0;
			while ( i< (statusList.size()) )
			{ if ( statusList.get(i).isShowInComplaint() )
				compStatusList.add(statusList.get(i));
			  i++;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return  compStatusList;
	}
	public void setStatusList(List<LostLuggageStatus> statusList) {
		this.statusList = statusList;
	}

	public List<StationBean> getStationList() {
		try {
			stationList = lostLuggageBao.getStationList();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return stationList;
	}


	public void setStationList(List<StationBean> stationList) {
		this.stationList = stationList;
	}

	public ComplaintDto getComplaintDto() {
		return complaintDto;
	}

	public void setComplaintDto(ComplaintDto complaintDto) {
		this.complaintDto = complaintDto;
	}

	public LostLuggageBao getLostLuggageBao() {
		return lostLuggageBao;
	}

	public void setLostLuggageBao(LostLuggageBao lostLuggageBao) {
		this.lostLuggageBao = lostLuggageBao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}
	
	
	
}
