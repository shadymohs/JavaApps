package com.SAPTCO.manageAllowance.backingBean;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.manageAllowance.bao.AllowanceTypesBao;
import com.SAPTCO.manageAllowance.dto.AllowanceTypeDto;


@ManagedBean(name="allowanceTypesBB")
@ViewScoped
public class AllowanceTypesBB extends BaseBB {

	
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	@ManagedProperty("#{allowanceTypesBao}")
	private AllowanceTypesBao allowanceTypesBao;
	private AllowanceTypeDto allowanceTypeDto = new AllowanceTypeDto();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private List<SystemBean> systemsList = new ArrayList<SystemBean>();
	
	
	@PostConstruct
	public void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("typeId") != null && !params.get("typeId").equals(""))
		{
			allowanceTypeDto.getAllowanceType().setId(Long.parseLong(params.get("typeId")));
			allowanceTypeDto.getAllowanceType().setCode(params.get("typeCode"));
			allowanceTypeDto.getAllowanceType().setLocaleName(params.get("typeArName"));
			allowanceTypeDto.getAllowanceType().setForeignName(params.get("typeEnName"));
			allowanceTypeDto.getAllowanceType().getSystem().setId(Long.parseLong(params.get("systemId")));
			allowanceTypeDto.getAllowanceType().setIsAuto(Boolean.parseBoolean(params.get("isAuto")));
		}
		
		try {
			allowanceTypeDto.setAllowanceTypeList(allowanceTypesBao.getAllowanceTypes());
			systemsList = allowanceTypesBao.getSystemsList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addListner() {
		try {
			clearComponent(addEditPanel);
			allowanceTypeDto.setAllowanceType(new AllowanceTypeBean()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	/*public void addCodeListner() {
		try {
			allowanceTypeDto.setCodeDesc(allowanceTypeDto.getAllowanceType().getCode());
			List<ProgramGroupsBean> branchesList = new ArrayList<ProgramGroupsBean>((programGroupsBao.getProgramGroupsByCode(allowanceTypeDto)).getProgramGroupsList());
			if(branchesList != null && !branchesList.isEmpty()){
				allowanceTypeDto.getAllowanceType().setCode(null);
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Ununique_Faild,
					FacesMessage.SEVERITY_ERROR);
			}
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		
		}
	}*/
	
	public void editListner() {
		try {
			clearComponent(addEditPanel);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String saveListner(){
		try{
			if(allowanceTypeDto.getAllowanceType().getId() == null || allowanceTypeDto.getAllowanceType().getId().equals(0L)){
				//add code
				allowanceTypeDto.getAllowanceType().setCreatedBy(getUserDetails().getId());
				allowanceTypesBao.insertAllowanceType(allowanceTypeDto);
				clearComponent(addEditPanel);
				allowanceTypeDto.setAllowanceType(new AllowanceTypeBean());
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return null;
			}else{
				//edit code
				allowanceTypesBao.updateAllowanceTypes(allowanceTypeDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "allowanceTypes.xhtml";
			}
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		
		}
		return null;
	}
	
	
	
	
	
	
	public AllowanceTypeDto getAllowanceTypeDto() {
		return allowanceTypeDto;
	}

	public void setAllowanceTypeDto(AllowanceTypeDto allowanceTypeDto) {
		this.allowanceTypeDto = allowanceTypeDto;
	}

	public AllowanceTypesBao getAllowanceTypesBao() {
		return allowanceTypesBao;
	}

	public void setAllowanceTypesBao(AllowanceTypesBao allowanceTypesBao) {
		this.allowanceTypesBao = allowanceTypesBao;
	}

	public List<SystemBean> getSystemsList() {
		return systemsList;
	}

	public void setSystemsList(List<SystemBean> systemsList) {
		this.systemsList = systemsList;
	}

}
