package com.SAPTCO.security.backingBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlPanelGrid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.component.UIDataTable;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.MenuProgramGroupBean;
import com.SAPTCO.common.ibatis.mapperBeans.ProgramGroupsBean;
import com.SAPTCO.security.bao.ProgramGroupsBao;
import com.SAPTCO.security.dto.ProgramGroupsDto;

/**
*
* @author Shady
*/

@ManagedBean(name="programGroupsBB")
@RequestScoped
public class ProgramGroupsBB extends BaseBB{
	
	@ManagedProperty("#{programGroupsBao}")
	private ProgramGroupsBao programGroupsBao;
	
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private ProgramGroupsDto programGroupsDto = new ProgramGroupsDto();
	private UIDataTable programGroupsTable = new UIDataTable();
	private UIDataTable programGroupPagesTable = new UIDataTable();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private UISelectItems pagesList = new UISelectItems();
	private UISelectItems privilegesList = new UISelectItems();

	public void programGroupsSearchListner() {
		try {
			if(programGroupsDto.getCodeDesc() == null || programGroupsDto.getCodeDesc().equals("")){
				programGroupsDto.setCodeDesc(null);
			}
			//return codes list
			List<ProgramGroupsBean> codesList = new ArrayList<ProgramGroupsBean>((programGroupsBao.getProgramGroupsByCode(programGroupsDto)).getProgramGroupsList());
			if(codesList != null && !codesList.isEmpty())
				programGroupsTable.setValue(codesList);
			else{
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Load_Data_Faild,
					FacesMessage.SEVERITY_ERROR);
				programGroupsTable.setValue(null);
			}
			programGroupsDto.setCodeDesc(null);
		} catch (Exception e) {
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
			programGroupsDto.setProgramGroupsObj(new ProgramGroupsBean());
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
			programGroupsDto.setCodeDesc(programGroupsDto.getProgramGroupsObj().getCode());
			List<ProgramGroupsBean> branchesList = new ArrayList<ProgramGroupsBean>((programGroupsBao.getProgramGroupsByCode(programGroupsDto)).getProgramGroupsList());
			if(branchesList != null && !branchesList.isEmpty()){
				programGroupsDto.getProgramGroupsObj().setCode(null);
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Ununique_Faild,
					FacesMessage.SEVERITY_ERROR);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
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
			if(programGroupsDto.getProgramGroupsObj().getId() == null || programGroupsDto.getProgramGroupsObj().getId().equals(0L)){
				//add code
				programGroupsBao.insertProgramGroups(programGroupsDto);
				clearComponent(addEditPanel);
				programGroupsDto.setProgramGroupsObj(new ProgramGroupsBean());
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return null;
			}else{
				//edit code
				programGroupsBao.updateProgramGroups(programGroupsDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "programGroups.xhtml";
			}
		} catch (Exception e) {
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
			programGroupsBao.deleteProgramGroups(programGroupsDto);
			programGroupsTable.setValue(null);
			addMessage(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Delete_Success);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}

	public void getProgramGroupsMenu() {
		try {
			if(programGroupsDto.getCode() == null || programGroupsDto.getCode().equals("")){
				programGroupsDto.setCode(null);
			}
			//return codes list
			List<MenuProgramGroupBean> codesList = new ArrayList<MenuProgramGroupBean>((programGroupsBao.getProgramGroupPages(programGroupsDto)).getMenuProgramGroups());
			if(codesList != null && !codesList.isEmpty())
				programGroupPagesTable.setValue(codesList);
			else{
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Load_Data_Faild,
					FacesMessage.SEVERITY_ERROR);
				programGroupPagesTable.setValue(null);
			}
			programGroupsDto.setCode(null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addPageListner() {
		try{
			pagesList.setValue(programGroupsBao.getProgramGroupUnselectedPages(programGroupsDto).getSystemMenuList());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void deletePageListner(){
		try{
			programGroupsDto = programGroupsBao.deleteProgramGroupMenu(programGroupsDto);
			if(programGroupsDto.getProcResult() != null && !programGroupsDto.getProcResult().equals("Y") && !programGroupsDto.getProcResult().equals(""))
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					programGroupsDto.getProcResult());
			else{
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Delete_Success);
				programGroupPagesTable.setValue(null);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String savePagesListner(){
		try{
			if(programGroupsDto.getSelectedPages() != null && programGroupsDto.getSelectedPages().length > 0){
				programGroupsDto = programGroupsBao.insertProgramGroupMenu(programGroupsDto);
				if(programGroupsDto.getProcResult() != null && !programGroupsDto.getProcResult().equals("Y") && !programGroupsDto.getProcResult().equals(""))
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						programGroupsDto.getProcResult());
				else
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "programGroupPages.xhtml";	
			}else
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Page_Required,
					FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public String addPagePrivilegeListner() {
		try{
			privilegesList.setValue(programGroupsBao.getAllProgramGroupPagePrivelege(programGroupsDto).getPrivilegesList());
			programGroupsDto.setSelectedPrivilege(programGroupsBao.getCurrentProgramGroupPagePrivelege(programGroupsDto).getSelectedPrivilege());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return "programPagePermissions.xhtml";
	}
	
	public String updatePrivilegesListner(){
		try{
			programGroupsDto = programGroupsBao.updateProgramGroupPagePrivelege(programGroupsDto);
			if(programGroupsDto.getProcResult() != null && !programGroupsDto.getProcResult().equals("Y") && !programGroupsDto.getProcResult().equals(""))
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					programGroupsDto.getProcResult());
			else
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Save_Success);
			return "programGroupPages.xhtml";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}

	public ProgramGroupsDto getProgramGroupsDto() {
		return programGroupsDto;
	}

	public void setProgramGroupsDto(ProgramGroupsDto programGroupsDto) {
		this.programGroupsDto = programGroupsDto;
	}

	public UIDataTable getProgramGroupsTable() {
		return programGroupsTable;
	}

	public void setProgramGroupsTable(UIDataTable programGroupsTable) {
		this.programGroupsTable = programGroupsTable;
	}

	public HtmlPanelGrid getAddEditPanel() {
		return addEditPanel;
	}

	public void setAddEditPanel(HtmlPanelGrid addEditPanel) {
		this.addEditPanel = addEditPanel;
	}
	public ProgramGroupsBao getProgramGroupsBao() {
		return programGroupsBao;
	}

	public void setProgramGroupsBao(ProgramGroupsBao programGroupsBao) {
		this.programGroupsBao = programGroupsBao;
	}

	public UIDataTable getProgramGroupPagesTable() {
		return programGroupPagesTable;
	}

	public void setProgramGroupPagesTable(UIDataTable programGroupPagesTable) {
		this.programGroupPagesTable = programGroupPagesTable;
	}

	public UISelectItems getPagesList() {
		return pagesList;
	}

	public void setPagesList(UISelectItems pagesList) {
		this.pagesList = pagesList;
	}

	public UISelectItems getPrivilegesList() {
		return privilegesList;
	}

	public void setPrivilegesList(UISelectItems privilegesList) {
		this.privilegesList = privilegesList;
	}
	
}
