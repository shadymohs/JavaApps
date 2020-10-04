package com.SAPTCO.reports.backingBean;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectItems;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.ibatis.mapperBeans.HrBranchesBean;
import com.SAPTCO.reports.dto.IntegAllowanceDto;
import com.SAPTCO.security.bao.DriversBao;

@ManagedBean(name="integAllowanceBB")
@ViewScoped
public class IntegAllowanceBB extends ReportsBB{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IntegAllowanceDto integAllowanceDto = new IntegAllowanceDto() ;
	private final Log logger = LogFactory.getLog(getClass());
	private List<HrBranchesBean> hrBranchesList = new ArrayList<HrBranchesBean>();
	private UISelectItems branches = new UISelectItems();
	@ManagedProperty("#{driversBao}")
	private DriversBao driversBao;
	
	@PostConstruct
	public void init(){
		
		try
		{
			hrBranchesList=driversBao.getHrBranchesList();
			integAllowanceDto.setHrBranchesList(hrBranchesList);
			branches.setValue(hrBranchesList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void viewReport(){
		try {
			generateReport(false,0);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	public void viewReportExcel(){
		try {
			generateExcelReport();
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	@Override
	protected ReportInfo getReportInfo() {
		
		return  ReportInfo.Integ_allowance;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		map.put("fromDate", sdf.format(integAllowanceDto.getFromDate()));
		map.put("toDate", sdf.format(integAllowanceDto.getToDate()));
		map.put("driverId", integAllowanceDto.getDriverId());
		map.put("tripCode", integAllowanceDto.getTripCode());
		map.put("isIntegrated",integAllowanceDto.getIsFinIntegrated());
		map.put("systemId",getUserDetails().getLoggedInSystem().getId());
		map.put("branchId",getUserDetails().getLoggedInBranch().getId());
		map.put("hrBranchId",integAllowanceDto.getHrBranchesBean().getId());
		
		return map;
		
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		
		return null;
	}


	public IntegAllowanceDto getIntegAllowanceDto() {
		return integAllowanceDto;
	}


	public void setIntegAllowanceDto(IntegAllowanceDto integAllowanceDto) {
		this.integAllowanceDto = integAllowanceDto;
	}


	public List<HrBranchesBean> getHrBranchesList() {
		return hrBranchesList;
	}


	public void setHrBranchesList(List<HrBranchesBean> hrBranchesList) {
		this.hrBranchesList = hrBranchesList;
	}
	public DriversBao getDriversBao() {
		return driversBao;
	}
	/**
	 * @param driversBao the driversBao to set
	 */
	public void setDriversBao(DriversBao driversBao) {
		this.driversBao = driversBao;
	}
	/**
	 * @return the branches
	 */
	public UISelectItems getBranches() {
		return branches;
	}
	/**
	 * @param branches the branches to set
	 */
	public void setBranches(UISelectItems branches) {
		this.branches = branches;
	}


	@Override
	protected Connection getConnection() throws Exception {
		return connectionDA();
	}
}
