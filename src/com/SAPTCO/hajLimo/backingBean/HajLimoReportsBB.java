/**
 * 
 */
package com.SAPTCO.hajLimo.backingBean;

import java.io.InputStream;
import java.sql.Connection;
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

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.reports.backingBean.ReportsBB;
import com.SAPTCO.security.bao.DriversBao;

/**
 * @author Sanadhr.acs
 *
 */
@ManagedBean(name="hajLimoReportBB")
@ViewScoped
public class HajLimoReportsBB extends ReportsBB {

	/**
	 *  
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private List<BaseDto> hrBranchesList = new ArrayList<BaseDto>();
	private Long branchId;
	private Date fromDate = new Date();
	private Date toDate = new Date();
	private String userNumber;
	private Long vehicleType;
	private int reportName;
	
	@ManagedProperty("#{driversBao}")
	private DriversBao driversBao;
	
	@PostConstruct
	public void init(){		
		try
		{
			branchId = BaseBB.getUserDetails().getLoggedInBranch().getId();
			hrBranchesList=driversBao.getHajBranches();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deletedReport(){
		try {
			reportName = 3;
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void viewReport(){
		try {
			reportName = 1;
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void viewReportExcel(){
		try {
			reportName = 1;
			generateExcelReport();
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void viewReportSummary(){
		try {
			reportName = 2;
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void viewReportSummaryExcel(){
		try {
			reportName = 2;
			generateExcelReport();
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	@Override
	protected ReportInfo getReportInfo() {
		if(reportName == 1)
			return  ReportInfo.Haj_Limo_Details_report;
		else if(reportName == 2)
			return  ReportInfo.Haj_Limo_Summary_report;
		else
			return  ReportInfo.Haj_Limo_Cancelled_report;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		map.put("fromDate", sdf.format(fromDate));
		map.put("toDate", sdf.format(toDate));
		map.put("branchId",branchId);
		map.put("userNumber",userNumber);
		map.put("vehicleType",vehicleType);
		InputStream subReportStream = getInputStream(ReportInfo.Haj_Limo_Details_sub_report);
		JasperReport subReport = (JasperReport) JRLoader.loadObject(subReportStream);
		map.put("Haj_Limo_Details_sub_report",subReport);
		return map;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {		
		return null;
	}

	/**
	 * @return the hrBranchesList
	 */
	public List<BaseDto> getHrBranchesList() {
		return hrBranchesList;
	}
	/**
	 * @param hrBranchesList the hrBranchesList to set
	 */
	public void setHrBranchesList(List<BaseDto> hrBranchesList) {
		this.hrBranchesList = hrBranchesList;
	}
	/**
	 * @return the driversBao
	 */
	public DriversBao getDriversBao() {
		return driversBao;
	}
	/**
	 * @param driversBao the driversBao to set
	 */
	public void setDriversBao(DriversBao driversBao) {
		this.driversBao = driversBao;
	}
	@Override
	protected Connection getConnection() throws Exception {
		return connectionHajLimo();
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
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
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public Long getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(Long vehicleType) {
		this.vehicleType = vehicleType;
	}
	public int getReportName() {
		return reportName;
	}
	public void setReportName(int reportName) {
		this.reportName = reportName;
	}

}
