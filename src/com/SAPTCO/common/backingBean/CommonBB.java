package com.SAPTCO.common.backingBean;

import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.CommonDto;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.bao.CommonBao;

/**
*
* @author Shady
*/

@ManagedBean(name="commonBB")
@ViewScoped
public class CommonBB extends BaseBB{

	@ManagedProperty("#{commonBao}")
	private CommonBao commonBao;
	private CommonDto commonDto = new CommonDto();
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private List<BranchesBean> branchesList = new ArrayList<BranchesBean>();
	private List<SystemBean> systemesList = new ArrayList<SystemBean>();
	private List<CompanyBean> companiesList = new ArrayList<CompanyBean>();
	
	public void updateUserBranches(AjaxBehaviorEvent evt) {
		try {
			branchesList = commonBao.findUserbranchesByName(commonDto).getBranchList();
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void updateUserSystemes(AjaxBehaviorEvent evt) {
		try {
			systemesList = commonBao.findUserSystemsByName(commonDto).getSystemsList();
			branchesList = new ArrayList<BranchesBean>();
			commonDto.getUserBean().getLoggedInBranch().setId(null);
			commonDto.getUserBean().getLoggedInSystem().setId(null);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}

	public String login() {
		try {
			//check user data
			commonDto = commonBao.authenticationUser(commonDto);
			if (commonDto.getProcResult() != null && !commonDto.getProcResult().equals("") && !commonDto.getProcResult().equals("Y")) {
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.USER_LOGIN_FAILURE,
					FacesMessage.SEVERITY_ERROR);
				return null;
			}
			HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
			request.getSession().setAttribute("userInf",commonDto.getUserBean());
			//retrieve the authorized programs.
			commonDto = commonBao.retrieveAuthorizedPrograms(commonDto);
			request.getSession().setAttribute("authorizedPrograms",fillSecurityHashMap(commonDto.getMenuList()));
			request.getSession().setAttribute("menuList",commonDto.getMenuList());
			return "homePage.xhtml";
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}

	public void logout(){
		try{
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			Object session = externalContext.getSession(false);
			HttpSession httpSession = (HttpSession) session;
			httpSession.invalidate();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public void changeCompany(){
		try {
			systemesList = commonBao.findUserSystemsByName(commonDto).getSystemsList();
			branchesList = new ArrayList<BranchesBean>();
			commonDto.getUserBean().getLoggedInBranch().setId(null);
			commonDto.getUserBean().getLoggedInSystem().setId(null);
		}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}

	public Log getLogger() {
		return logger;
	}

	public CommonBao getCommonBao() {
		return commonBao;
	}

	public void setCommonBao(CommonBao commonBao) {
		this.commonBao = commonBao;
	}

	public CommonDto getCommonDto() {
		return commonDto;
	}

	public void setCommonDto(CommonDto commonDto) {
		this.commonDto = commonDto;
	}

	public List<BranchesBean> getBranchesList() {
		return branchesList;
	}

	public void setBranchesList(List<BranchesBean> branchesList) {
		this.branchesList = branchesList;
	}

	public List<SystemBean> getSystemesList() {
		return systemesList;
	}

	public void setSystemesList(List<SystemBean> systemesList) {
		this.systemesList = systemesList;
	}

	public List<CompanyBean> getCompaniesList() {
		if(companiesList == null || companiesList.isEmpty()){
			try{
				return commonBao.getCompaniesList();
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return companiesList;
	}

	public void setCompaniesList(List<CompanyBean> companiesList) {
		this.companiesList = companiesList;
	}
	
}
