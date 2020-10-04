package com.SAPTCO.reports.backingBean;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.reports.dto.CostAnalysisReportDto;
/**
 * @author alqassemga
 *
 */

@ManagedBean(name="lineDailyReportBB")
@ViewScoped
public class LineDailyReportBB extends ReportsBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private CostAnalysisReportDto costAnalysisReportDto = new CostAnalysisReportDto();
	
	
	
	@Override
	protected Connection getConnection() throws Exception {
		return connectionCostAnalysis();
	}

	@Override
	protected ReportInfo getReportInfo() {
		if (costAnalysisReportDto.getIsSummary()  )
			return  ReportInfo.Line_daily_summary; 
		else
			return  ReportInfo.Line_daily_detail;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		ServletContext context = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		map.put("P_FROM_DATE", sdf.format(costAnalysisReportDto.getFromDate()));
		map.put("P_TO_DATE", sdf.format(costAnalysisReportDto.getToDate()));
		map.put("P_LINE_CODE", costAnalysisReportDto.getLineCode());
		map.put("P_NOF_PASSENGERS", costAnalysisReportDto.getNoOfPassengers());
		map.put("P_GOING_RETURN",costAnalysisReportDto.getGoingReturn()==1 ? 1 : 2);
		
	
		if (costAnalysisReportDto.getIsSummary()  )
			map.put("SUBREPORT_DIR",costAnalysisReportDto.getGoingReturn()==1 ?
					context.getRealPath(SystemConstants.Line_Summary)  
				  : context.getRealPath(SystemConstants.Line_Summary2));
		else
			
		   map.put("SUBREPORT_DIR",costAnalysisReportDto.getGoingReturn()==1 ?
				   context.getRealPath(SystemConstants.Line_Daily )
				 : context.getRealPath(SystemConstants.Line_Daily2 ));
		
		
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

	public CostAnalysisReportDto getCostAnalysisReportDto() {
		return costAnalysisReportDto;
	}

	public void setCostAnalysisReportDto(CostAnalysisReportDto costAnalysisReportDto) {
		this.costAnalysisReportDto = costAnalysisReportDto;
	}

	

}
