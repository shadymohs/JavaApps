package com.SAPTCO.manageAllowance.backingBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.manageAllowance.bao.OtherAllowanceBao;
import com.SAPTCO.manageAllowance.dto.OtherAllowanceDto;
import com.SAPTCO.reports.backingBean.ReportsBB;


/**
 * @author alqassemga
 *
 */

@ManagedBean(name="otherAllowancelBB")
@ViewScoped
public class OtherAllowancelBB extends ReportsBB{
	
	@ManagedProperty("#{otherAllowanceBao}")
	private OtherAllowanceBao otherAllowanceBao;
	private OtherAllowanceDto allowanceDetailDto = new OtherAllowanceDto();
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
   	
	@PostConstruct
	public void init(){
		allowanceDetailDto.setAllowanceDetailBean(new AllowanceDetailBean());
		allowanceDetailDto.setDriversAllowance(new ArrayList<AllowanceDetailBean>());
		allowanceDetailDto.setIsManageMode(false);
		try {
			allowanceDetailDto.setAllowanceTypeList(otherAllowanceBao.getAllowanceTypes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void save(){
		try {
			allowanceDetailDto.getAllowanceDetailBean().setSystemId(BaseBB.getUserDetails().getLoggedInSystem().getId());
			allowanceDetailDto.getAllowanceDetailBean().setBranchId(BaseBB.getUserDetails().getLoggedInBranch().getId());
			allowanceDetailDto.getAllowanceDetailBean().setCreatedBy(BaseBB.getUserDetails().getId());
			
			if(allowanceDetailDto.getAllowanceDetailBean().getId() != null){
				update();
			}else{
				allowanceDetailDto = otherAllowanceBao.saveAllowanceDetail(allowanceDetailDto);
				if(allowanceDetailDto.getProcResult() != null && allowanceDetailDto.getProcResult().equals("Y")){
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
					allowanceDetailDto.setIsManageMode(true);
				}else
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,allowanceDetailDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void sendToHR(){
		try {
			allowanceDetailDto = otherAllowanceBao.sendToHR(allowanceDetailDto);
			if(allowanceDetailDto.getProcResult() != null && allowanceDetailDto.getProcResult().equals("Y")){
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
				allowanceDetailDto.setAllowanceDetailBean(new AllowanceDetailBean());
				allowanceDetailDto.setDriversAllowance(new ArrayList<AllowanceDetailBean>());
				allowanceDetailDto.setIsManageMode(false);
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,allowanceDetailDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void edit(){
		allowanceDetailDto.setIsManageMode(true);
	}
	
	public void add(){
		allowanceDetailDto.setIsManageMode(true);
		allowanceDetailDto.setAllowanceDetailBean(new AllowanceDetailBean());
		allowanceDetailDto.getAllowanceDetailBean().setSystemId(getUserDetails().getLoggedInSystem().getId()) ;  
		
	}
	
	public void cancel(){
		allowanceDetailDto.setAllowanceDetailBean(new AllowanceDetailBean());
		allowanceDetailDto.setDriversAllowance(new ArrayList<AllowanceDetailBean>());
		allowanceDetailDto.setIsManageMode(false);
	}
	 //|| (allowanceDetailDto.getAllowanceDetail().getAllowanceType().getId() != null
	public void search(){   
		try {
			allowanceDetailDto.getAllowanceDetailBean().setSystemId(BaseBB.getUserDetails().getLoggedInSystem().getId());
			if(allowanceDetailDto.getAllowanceNumber() != null   || allowanceDetailDto.getAllowanceType() != null || allowanceDetailDto.getDate() != null
					|| allowanceDetailDto.getDriverNumber() != null || allowanceDetailDto.getTripCode() != null)
					
				/*(allowanceDetailDto.getDate() != null && allowanceDetailDto.getAllowanceType() != null) ||
					(allowanceDetailDto.getDate() != null && allowanceDetailDto.getDriverNumber() != null ) )
					*/
				allowanceDetailDto = otherAllowanceBao.getDriverAllowance(allowanceDetailDto);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Invalid_Search_Criteria,FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void update(){
		try {
			if(allowanceDetailDto.getAllowanceDetailBean().getId() != null )
			{	allowanceDetailDto = otherAllowanceBao.editAllowanceDetail(allowanceDetailDto);
			  if(allowanceDetailDto.getProcResult() != null && allowanceDetailDto.getProcResult().equals("Y")){
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Edit_Success,FacesMessage.SEVERITY_INFO);
				allowanceDetailDto.setIsManageMode(true);
			}}
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,allowanceDetailDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void delete(){
		try {
			if(allowanceDetailDto.getAllowanceDetailBean().getId() != null )
			{	allowanceDetailDto = otherAllowanceBao.deleteAllowanceDetail(allowanceDetailDto);
				if(allowanceDetailDto.getProcResult() != null && allowanceDetailDto.getProcResult().equals("Y")){
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Delete_Success,FacesMessage.SEVERITY_INFO);
					allowanceDetailDto.setAllowanceDetailBean(new AllowanceDetailBean());
					allowanceDetailDto.setDriversAllowance(new ArrayList<AllowanceDetailBean>());
					allowanceDetailDto.setIsManageMode(false);
			}}
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,allowanceDetailDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
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

	public OtherAllowanceDto getAllowanceDetailDto() {
		return allowanceDetailDto;
	}

	public void setAllowanceDetailDto(OtherAllowanceDto allowanceDetailDto) {
		this.allowanceDetailDto = allowanceDetailDto;
	}


	@Override
	protected ReportInfo getReportInfo() {
		
		return ReportInfo.Trip_sheet;
	}


	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		map.put("allowanceDetailId", allowanceDetailDto.getAllowanceDetailBean().getId());
		return map;
	}


	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		return null;
	}


	public OtherAllowanceBao getOtherAllowanceBao() {
		return otherAllowanceBao;
	}


	public void setOtherAllowanceBao(OtherAllowanceBao otherAllowanceBao) {
		this.otherAllowanceBao = otherAllowanceBao;
	}
	
	public void updatePlateNumber(AjaxBehaviorEvent evt) {
		try {
			allowanceDetailDto.getAllowanceDetailBean().setPlateNumber(otherAllowanceBao.updatePlateNumber(allowanceDetailDto.getAllowanceDetailBean().getBusNumber().toString()));
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void updateDriver1(AjaxBehaviorEvent evt) {
		try {
			allowanceDetailDto.getAllowanceDetailBean().setDriver1Name(otherAllowanceBao.updateDriver1(allowanceDetailDto.getAllowanceDetailBean().getDriver1Number().toString()));
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void updateDriver2(AjaxBehaviorEvent evt) {
		try {
			allowanceDetailDto.getAllowanceDetailBean().setDriver2Name(otherAllowanceBao.updateDriver2(allowanceDetailDto.getAllowanceDetailBean().getDriver2Number().toString()));
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}


	@Override
	protected Connection getConnection() throws Exception {
		return connectionDA();
	}

}
