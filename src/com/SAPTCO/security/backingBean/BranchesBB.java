package com.SAPTCO.security.backingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.security.bao.BranchesBao;
import com.SAPTCO.security.dto.BranchesDto;
import javax.annotation.PostConstruct;

/**
*
* @author Shady
*/

@ManagedBean(name="branchesBB")
@ViewScoped
public class BranchesBB extends BaseBB{
	
	@ManagedProperty("#{branchesBao}")
	private BranchesBao branchesBao;
	
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private BranchesDto branchesDto = new BranchesDto();
	private List<BranchesBean> branchesTable = new ArrayList<BranchesBean>();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private List<SystemBean> systemsList = new ArrayList<SystemBean>();
	
	@PostConstruct
	public void init(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("id") != null && !params.get("id").equals("")){
			branchesDto.getBranchObj().setId(Long.parseLong(params.get("id")));
			branchesDto.getBranchObj().setCode(params.get("code"));
			branchesDto.getBranchObj().setLocaleName(params.get("arName"));
			branchesDto.getBranchObj().setForeignName(params.get("enName"));
			branchesDto.getBranchObj().setIsActive(Boolean.parseBoolean(params.get("active")));
			branchesDto.getBranchObj().setSystemId(Long.parseLong(params.get("systemId")));
		}
		try{
			systemsList = branchesBao.getSystemsList();
			branchesSearchListner();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void branchesSearchListner() {
		try {
			if(branchesDto.getCodeDesc() == null || branchesDto.getCodeDesc().equals("")){
				branchesDto.setCodeDesc(null);
			}
			//return codes list
			branchesTable = new ArrayList<BranchesBean>((branchesBao.getBranchesByCode(branchesDto)).getBranchesList());
			branchesDto.setCodeDesc(null);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addListner() {
		try {
			clearComponent(addEditPanel);
			branchesDto.setBranchObj(new BranchesBean());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}

	public void addCodeListner() {
		try {
			branchesDto.setCodeDesc(branchesDto.getBranchObj().getCode());
			List<BranchesBean> branchesList = new ArrayList<BranchesBean>((branchesBao.getBranchesByCode(branchesDto)).getBranchesList());
			if(branchesList != null && !branchesList.isEmpty()){
				branchesDto.getBranchObj().setCode(null);
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Ununique_Faild,
					FacesMessage.SEVERITY_ERROR);
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String saveListner(){
		try{
			if(branchesDto.getBranchObj().getId() == null || branchesDto.getBranchObj().getId().equals(0L)){
				//add code
				branchesBao.insertBranch(branchesDto);
				clearComponent(addEditPanel);
				branchesDto.setBranchObj(new BranchesBean());
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return null;
			}else{
				//edit code
				branchesBao.updateBranch(branchesDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "branches.xhtml";
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public void deleteListner(){
		try {
			//delete definition
			branchesBao.deleteBranch(branchesDto);
			branchesTable = new ArrayList<BranchesBean>();
			addMessage(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Delete_Success);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	public BranchesBao getBranchesBao() {
		return branchesBao;
	}

	public void setBranchesBao(BranchesBao branchesBao) {
		this.branchesBao = branchesBao;
	}

	public BranchesDto getBranchesDto() {
		return branchesDto;
	}

	public void setBranchesDto(BranchesDto branchesDto) {
		this.branchesDto = branchesDto;
	}

	public List<BranchesBean> getBranchesTable() {
		return branchesTable;
	}

	public void setBranchesTable(List<BranchesBean> branchesTable) {
		this.branchesTable = branchesTable;
	}

	public HtmlPanelGrid getAddEditPanel() {
		return addEditPanel;
	}

	public void setAddEditPanel(HtmlPanelGrid addEditPanel) {
		this.addEditPanel = addEditPanel;
	}

	public List<SystemBean> getSystemsList() {
		return systemsList;
	}

	public void setSystemsList(List<SystemBean> systemsList) {
		this.systemsList = systemsList;
	}
}
