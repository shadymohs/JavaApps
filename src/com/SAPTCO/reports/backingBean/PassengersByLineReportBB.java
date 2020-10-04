package com.SAPTCO.reports.backingBean;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.reports.dto.PassengersByLineDto;

@ManagedBean(name="passengersByLineReportBB")
@ViewScoped
public class PassengersByLineReportBB extends ReportsBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private PassengersByLineDto passengersByLineDto = new PassengersByLineDto();
	

	@Override
	protected Connection getConnection() throws Exception {
		return connectionCostAnalysis();
	}

	@Override
	protected ReportInfo getReportInfo() {
		if (passengersByLineDto.getIsSummary()  )
			return  ReportInfo.Passengers_summary_by_line; 
		else
			return  ReportInfo.Passengers_detail_by_line;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		map.put("P_FROM_DATE", sdf.format(passengersByLineDto.getFromDate()));
		map.put("P_TO_DATE", sdf.format(passengersByLineDto.getToDate()));
		map.put("P_LINE_CODE", passengersByLineDto.getLineCode());
		
		map.put("systemId",getUserDetails().getLoggedInSystem().getId());
		map.put("branchId",getUserDetails().getLoggedInBranch().getId());
		
		return map;
	}

	public void viewReport(){
		try {
			generateReportFromSP();
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		
		return null;
	}

	public PassengersByLineDto getPassengersByLineDto() {
		return passengersByLineDto;
	}

	public void setPassengersByLineDto(PassengersByLineDto passengersByLineDto) {
		this.passengersByLineDto = passengersByLineDto;
	}

}
