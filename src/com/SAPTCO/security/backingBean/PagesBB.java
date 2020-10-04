package com.SAPTCO.security.backingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemMenuBean;
import com.SAPTCO.security.bao.PagesBao;
import com.SAPTCO.security.dto.PagesDto;

/**
*
* @author Shady
*/

@ManagedBean(name="pagesBB")
@ViewScoped
public class PagesBB extends BaseBB{
	
	@ManagedProperty("#{pagesBao}")
	private PagesBao pagesBao;
	
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private PagesDto pagesDto = new PagesDto();
	private List<SystemMenuBean> pagesTable = new ArrayList<SystemMenuBean>();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private UISelectItems privilegesList = new UISelectItems();
	private List<SystemBean> systemsList = new ArrayList<SystemBean>();
	
	@PostConstruct
	public void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("id") != null && !params.get("id").equals("")){
			
		}
		try{
			systemsList = pagesBao.getSystemsList();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void pagesSearchListner() {
		try {
			if(pagesDto.getCodeDesc() == null || pagesDto.getCodeDesc().equals("")){
				pagesDto.setCodeDesc(null);
			}
			//return codes list
			List<SystemMenuBean> codesList = new ArrayList<SystemMenuBean>((pagesBao.getPagesByCode(pagesDto)).getSystemMenuList());
			if(codesList != null && !codesList.isEmpty())
				setPagesTable(codesList);
			else{
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Load_Data_Faild,
					FacesMessage.SEVERITY_ERROR);
				setPagesTable(null);
			}
			pagesDto.setCodeDesc(null);
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
			pagesDto.setPageObj(new SystemMenuBean());
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
			pagesDto.setCodeDesc(pagesDto.getPageObj().getCode());
			List<SystemMenuBean> pagesList = new ArrayList<SystemMenuBean>((pagesBao.getPagesByCode(pagesDto)).getSystemMenuList());
			if(pagesList != null && !pagesList.isEmpty()){
				pagesDto.getPageObj().setCode(null);
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
			if(pagesDto.getPageObj().getMenuId() == null || pagesDto.getPageObj().getMenuId().equals(0L)){
				if(pagesDto.getPageObj().getParentPage().getId() == null || pagesDto.getPageObj().getParentPage().getId().equals(0L))
					pagesDto.getPageObj().getParentPage().setId(null);
				//add code
				pagesBao.insertPage(pagesDto);
				clearComponent(addEditPanel);
				pagesDto.setPageObj(new SystemMenuBean());
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return null;
			}else{
				//edit code
				pagesBao.updatePage(pagesDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "pages.xhtml";
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
			pagesDto = pagesBao.deletePage(pagesDto);
			if(pagesDto.getProcResult() != null && !pagesDto.getProcResult().equals("Y") && !pagesDto.getProcResult().equals(""))
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					pagesDto.getProcResult());
			else
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Delete_Success);
			setPagesTable(null);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String privilegesListner() {
		try{
			privilegesList.setValue(pagesBao.getAllPrivelege(pagesDto).getPrivelegeList());
			pagesDto.setSelectedPrivilege(pagesBao.getCurrentPrivelege(pagesDto).getSelectedPrivilege());
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return "pagePermissions.xhtml";
	}
	
	public String updatePrivilegesListner(){
		try{
			pagesDto = pagesBao.updatePrivelege(pagesDto);
			if(pagesDto.getProcResult() != null && !pagesDto.getProcResult().equals("Y") && !pagesDto.getProcResult().equals(""))
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					pagesDto.getProcResult());
			else
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Save_Success);
			return "pages.xhtml";
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public List<SystemMenuBean> getPagesList() {
		List<SystemMenuBean> pagesList = new ArrayList<SystemMenuBean>();
		try{
			pagesList = pagesBao.getParentPage().getSystemMenuList();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return pagesList;
	}

	public PagesBao getPagesBao() {
		return pagesBao;
	}

	public void setPagesBao(PagesBao pagesBao) {
		this.pagesBao = pagesBao;
	}

	public PagesDto getPagesDto() {
		return pagesDto;
	}

	public void setPagesDto(PagesDto pagesDto) {
		this.pagesDto = pagesDto;
	}


	public HtmlPanelGrid getAddEditPanel() {
		return addEditPanel;
	}

	public void setAddEditPanel(HtmlPanelGrid addEditPanel) {
		this.addEditPanel = addEditPanel;
	}

	public UISelectItems getPrivilegesList() {
		return privilegesList;
	}

	public void setPrivilegesList(UISelectItems privilegesList) {
		this.privilegesList = privilegesList;
	}

	public List<SystemMenuBean> getPagesTable() {
		return pagesTable;
	}

	public void setPagesTable(List<SystemMenuBean> pagesTable) {
		this.pagesTable = pagesTable;
	}

	public List<SystemBean> getSystemsList() {
		return systemsList;
	}

	public void setSystemsList(List<SystemBean> systemsList) {
		this.systemsList = systemsList;
	}
}
