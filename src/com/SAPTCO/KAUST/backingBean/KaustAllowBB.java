package com.SAPTCO.KAUST.backingBean;

import java.sql.Connection;
import java.util.ArrayList;
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
import com.SAPTCO.common.ibatis.mapperBeans.DriversBean;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTAllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTdriver;
import com.SAPTCO.common.ibatis.mapperBeans.KaustAdditionalAllow;
import com.SAPTCO.reports.backingBean.ReportsBB;
import com.SAPTCO.security.bao.DriversBao;
import com.SAPTCO.security.dto.BranchesDto;
import com.SAPTCO.security.dto.DriversDto;

@ManagedBean(name="kaustAllowBB")
@ViewScoped
public class KaustAllowBB extends ReportsBB{

	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private DriversDto driversDto  = new DriversDto();
	private Boolean addException = false;
	private Boolean addAdditional = false;
	private List<KAUSTAllowanceTypeBean> kaustAllowanceTypeList=  new  ArrayList<KAUSTAllowanceTypeBean>();
	private List<KAUSTAllowanceTypeBean> kaustAllowTypeByBranchList=  new  ArrayList<KAUSTAllowanceTypeBean>();
	private BranchesDto branchesDto = new BranchesDto();
	


	@ManagedProperty("#{driversBao}")
	private DriversBao driverBao;
	private Boolean displayException = false;
	private Boolean displayAdditional = false;

	@PostConstruct
	public void init(){
		displayException = false;
		addException = false;
		displayAdditional = false;
		addAdditional = false;
		try {
			
			branchesDto.getBranchObj().setId(getUserDetails().getLoggedInBranch().getId());
			kaustAllowTypeByBranchList = driverBao.getKaustAllowTypeByBranch(branchesDto);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void addException(){
		addException = true;
	}
	
	public void cancelAdd(){
		addException = false;
	}
	
	public void addAdditional(){
		addAdditional = true;
	}
	
	public void cancelAddAdditional(){
		addAdditional = false;
	}
	
	
	public void saveKaustDriverAllow()
	{
		try
		{
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto = driverBao.saveKaustDriverAllow(driversDto);
			
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y")){
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
			}else{
				addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						driversDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				
			}
			addException = false;
			addAdditional = false;
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void getDriverById() {		
		try{
			driversDto = driverBao.getDriverById(driversDto);
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y")){
				displayException = true;
				displayAdditional = true;
			}else{
				displayException = false;
				displayAdditional = false;
				driversDto.setDriverObj(new DriversBean());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"not_driver",FacesMessage.SEVERITY_ERROR);
			}
			addException = false; addAdditional = false;
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void saveAllowException(){
		try{
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto = driverBao.saveAllowException(driversDto);
			
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y")){
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
				addException = false;
				driversDto.getDriverObj().setDriverKAUST(new KAUSTdriver());
				getDriverById();
			}else{
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					driversDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void saveAllowAdditional(){
		try{
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto = driverBao.saveAdditionalAllow(driversDto);
			
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y")){
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
				 addAdditional = false;
				driversDto.getDriverObj().setKaustAdditionalAllow(new KaustAdditionalAllow());
				getDriverById();
			}else{
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					driversDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}

	public DriversBao getDriverBao() {
		return driverBao;
	}

	public void setDriverBao(DriversBao driverBao) {
		this.driverBao = driverBao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}

	public DriversDto getDriversDto() {
		return driversDto;
	}

	public void setDriversDto(DriversDto driversDto) {
		this.driversDto = driversDto;
	}

	public Boolean getDisplayException() {
		return displayException;
	}

	public void setDisplayException(Boolean displayException) {
		this.displayException = displayException;
	}

	
	public void createMonthlyAllow(){
		try {
			driversDto.setSystemId(getUserDetails().getLoggedInSystem().getId());
			driversDto.setBranchId(getUserDetails().getLoggedInBranch().getId());
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto = driverBao.createMonthlyAllow(driversDto);
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y"))
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,driversDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deleteMonthlyAllow(){
		try {
			driversDto.setSystemId(getUserDetails().getLoggedInSystem().getId());
			driversDto.setBranchId(getUserDetails().getLoggedInBranch().getId());
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto = driverBao.deleteMonthlyAllow(driversDto);
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y"))
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Delete_Success);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,driversDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void integrateMonthlyAllow(){
		try {
			driversDto.setSystemId(getUserDetails().getLoggedInSystem().getId());
			driversDto.setBranchId(getUserDetails().getLoggedInBranch().getId());
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto = driverBao.integrateMonthlyAllow(driversDto);
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y"))
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"sent_to_hr",FacesMessage.SEVERITY_INFO);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,driversDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deleteException(){
		try {
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto.setBranchId(getUserDetails().getLoggedInBranch().getId());
			driversDto = driverBao.deleteAllowException(driversDto);
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y"))
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Delete_Success);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,driversDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	public void deleteAdditional(){
		try {
			driversDto.setCreatedBy(getUserDetails().getId());
			driversDto.setBranchId(getUserDetails().getLoggedInBranch().getId());
			driversDto = driverBao.deleteAdditionalAllow(driversDto);
			if(driversDto.getProcResult() != null && driversDto.getProcResult().equals("Y"))
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Delete_Success);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,driversDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}

	public Boolean getAddException() {
		return addException;
	}

	public void setAddException(Boolean addException) {
		this.addException = addException;
	}
	
	public void printMonthlyAllow(){
		try { 
			generateReport(false,1);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}

	@Override
	protected Connection getConnection() throws Exception {
		return connectionDA();
	}

	@Override
	protected ReportInfo getReportInfo() {
		return ReportInfo.KAUST_Allowance_report;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		map.put("month",driversDto.getMonth());
		map.put("year",driversDto.getYear());
		map.put("branchId",getUserDetails().getLoggedInBranch().getId());
		map.put("systemId",getUserDetails().getLoggedInSystem().getId());
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		return null;
	}

	public List<KAUSTAllowanceTypeBean> getKaustAllowanceTypeList() {
		try {
			kaustAllowanceTypeList = driverBao.getAllKaustAllowTypes();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		
		return kaustAllowanceTypeList;
	}

	public void setKaustAllowanceTypeList(List<KAUSTAllowanceTypeBean> kaustAllowanceTypeList) {
		this.kaustAllowanceTypeList = kaustAllowanceTypeList;
	}
	
	public BranchesDto getBranchesDto() {
		return branchesDto;
	}

	public void setBranchesDto(BranchesDto branchesDto) {
		this.branchesDto = branchesDto;
	}

	public Boolean getDisplayAdditional() {
		return displayAdditional;
	}

	public void setDisplayAdditional(Boolean displayAdditional) {
		this.displayAdditional = displayAdditional;
	}

	public Boolean getAddAdditional() {
		return addAdditional;
	}

	public void setAddAdditional(Boolean addAdditional) {
		this.addAdditional = addAdditional;
	}

	public List<KAUSTAllowanceTypeBean> getKaustAllowTypeByBranchList() {
		return kaustAllowTypeByBranchList;
	}

	public void setKaustAllowTypeByBranchList(
			List<KAUSTAllowanceTypeBean> kaustAllowTypeByBranchList) {
		this.kaustAllowTypeByBranchList = kaustAllowTypeByBranchList;
	}
	
	
}
