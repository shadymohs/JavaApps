package com.SAPTCO.common.backingBean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.SAPTCO.common.bao.CommonBao;
import com.SAPTCO.common.dto.ProgramPrivelegeEnum;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.MenuBean;
import com.SAPTCO.common.ibatis.mapperBeans.PrivilegeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemMenuBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;

/**
 * @author Shady
 */

@ManagedBean(name="baseBB")
@ViewScoped
public class BaseBB implements Serializable{

	private static final long serialVersionUID = 1L;
	private final static String arabic = "ar";
	private final static String english = "en";
	
	@ManagedProperty("#{commonBao}")
	private CommonBao commonBao;
	
	public CommonBao getCommonBao() {
		return commonBao;
	}

	public void setCommonBao(CommonBao commonBao) {
		this.commonBao = commonBao;
	}

	public static UserBean getUserDetails(){
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		UserBean user = (UserBean) request.getSession().getAttribute("userInf");
		return user;
	}
	
	public static Boolean isARLang(){
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		if(request.getSession().getAttribute("locale") == null || request.getSession().getAttribute("locale").equals(""))
			request.getSession().setAttribute("locale",arabic);
		if(request.getSession().getAttribute("locale").equals(arabic))
			return true;
		else
			return false;
	}
	
	public void changeLanguage(){
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		if(isARLang()){
			request.getSession().setAttribute("locale",english);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("en"));
		}else{
			request.getSession().setAttribute("locale",arabic);
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale("ar"));
		}
	}
	
	public String getPageDirection(){
		if(isARLang())
			return "rtl";
		else
			return "ltr";
	}
	
	public String getLocale() {
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		String locale = arabic;
		request.getSession(true).setAttribute("locale",locale);
		UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
		viewRoot.setLocale(new Locale(locale));
		return locale;
	}
	
	public String getCurrentURL() {
		String pageUrl = FacesContext.getCurrentInstance().getViewRoot().getViewId();
		pageUrl.trim();
		return pageUrl;
	}
	
	public Boolean getAuthorizeView() {
		if(getCurrentURL().equals("/pages/common/homePage.xhtml")){
			return true;
		}else{
			return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.VIEW);
		}
	}
	
	public Boolean getAuthorizeAdd() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.ADD);
	}

	public Boolean getAuthorizeUpdate() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.UPDATE);
	}

	public Boolean getAuthorizeDelete() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.DELETE);
	}
	
	public Boolean getAuthorizeSearchAll() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.SEARCH_ALL);
	}

	public Boolean getAuthorizePrint() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.PRINT);
	}
	
	public Boolean getAuthorizedDetail() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.Detail);
	}
	
	public Boolean getAuthorizeSendToHR() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.Send_to_HR);
	}
	
	public Boolean getAuthorizeChangePrice() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.Change_price);
	}
	
	public Boolean getAuthorizeRemoveLink() {
		return authorizeUser(this.getCurrentURL(), ProgramPrivelegeEnum.REMOVE_LINK);
	}
	
	/** check if the current user authorized for this programPrivilage on this program or not
	 * @param programUrl
	 * @param programPrivelegeEnum
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	private Boolean authorizeUser(String programUrl,ProgramPrivelegeEnum programPrivelegeEnum){
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		if(request.getSession().getAttribute("authorizedPrograms") != null){
			Map<String,SystemMenuBean> programs = (Map<String, SystemMenuBean>) request.getSession().getAttribute("authorizedPrograms");
			Boolean isAuthorized = false;
			if(programs.get(programUrl.trim()) != null){
				for(PrivilegeBean pv : programs.get(programUrl.trim()).getPrivelegeList()){
					if(pv.getId().equals(programPrivelegeEnum.getId())){
						isAuthorized = true;
						break;
					}
				}
			}
			return isAuthorized;
		}
		return false;
	}
	
	public SystemMenuBean getTitle(){
		HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
		if(request.getSession().getAttribute("authorizedPrograms") != null){
			@SuppressWarnings("unchecked")
			Map<String,SystemMenuBean> programs = (Map<String, SystemMenuBean>) request.getSession().getAttribute("authorizedPrograms");
			if(this.getCurrentURL().trim().equals("/pages/common/homePage.xhtml"))
				return programs.get(getUserDetails().getLoggedInSystem());
			else if(programs.get(this.getCurrentURL().trim()) != null){
				return programs.get(this.getCurrentURL().trim());
			}
		}
		return null;
	}
	
	
	/** fill a hashMap for all the programs and privilages, the hashMap is programURL concatenated with programPrivilageId
	 * @param authorizedPrograms
	 */
	protected Map<String,SystemMenuBean> fillSecurityHashMap(List<MenuBean> authorizedPrograms) {
		Map<String,SystemMenuBean> authorizedProgramsMap = new HashMap<String,SystemMenuBean>();
		if (authorizedPrograms != null) {
			for(MenuBean menu : authorizedPrograms) {
				for (SystemMenuBean page : menu.getPagesList()){
						authorizedProgramsMap.put(page.getMenuUrl(),page);
				}
			}
		}
		
		return authorizedProgramsMap;
	}
	
	public static String getResourceBundleString(String resourceBundleName,String resourceBundleKey){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle(resourceBundleName
				, facesContext.getViewRoot().getLocale() 
				,Thread.currentThread().getContextClassLoader());
	    return bundle.getString(resourceBundleKey);
	}
	
	public static String getFacesBundleString(String resourceBundleName,String resourceBundleKey) throws Exception{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle bundle = facesContext.getApplication().getResourceBundle(facesContext, resourceBundleName);
	    return bundle.getString(resourceBundleKey);
	}
	
	public static void addMessage(String resourceBundle, String messageKey){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle(resourceBundle
				, facesContext.getViewRoot().getLocale()
				, Thread.currentThread().getContextClassLoader());
		String message = bundle.getString(messageKey);
		facesContext.addMessage(null, new FacesMessage(message , message));			
	}
	
	public static void addErrorMessageByCode(String errorResourceBundle, String errorMessageKey, Severity severity){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle(errorResourceBundle, 
				facesContext.getViewRoot().getLocale()
				, Thread.currentThread().getContextClassLoader());
		String errorMessage = bundle.getString(errorMessageKey);
		facesContext.addMessage(null, new FacesMessage(severity, errorMessage , errorMessage));			
	}
	
	public static void addErrorMessageByCode(String errorResourceBundle, String errorMessageKey, String[] params,  Severity severity){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		PropertyResourceBundle bundle = (PropertyResourceBundle) ResourceBundle.getBundle(errorResourceBundle
				, facesContext.getViewRoot().getLocale() 
				,Thread.currentThread().getContextClassLoader());
		if(params!=null)
			for(int i=0; i<params.length ; i++)
				if (params[i].startsWith("res_"))
					params[i] = bundle.getString(params[i].substring(4,params[i].length()));
		String errorMessage = MessageFormat.format(bundle.getString(errorMessageKey), (Object[])params);
		facesContext.addMessage(null, new FacesMessage(severity, errorMessage , errorMessage));			
	}
	
	public static void addErrorMessage(String errorMessage, Severity severity){
		try{			
			FacesContext facesContext = FacesContext.getCurrentInstance();
			String[] errorsList = errorMessage.split("###");
			for(int i = 0; i<errorsList.length;i++){
				facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, errorsList[i] ,  errorsList[i]));
			}
		} catch (Exception e) 
		{ }
	}
	
	protected static void clearComponent(UIComponent uIComponent)
    {
         if ( uIComponent instanceof EditableValueHolder )
         {
              EditableValueHolder editableValueHolder = (EditableValueHolder) uIComponent;
              editableValueHolder.setSubmittedValue( null );
              editableValueHolder.setValue( null );
              editableValueHolder.setValid( true );
         }
         for(UIComponent child : uIComponent.getChildren())
         {
              clearComponent( child );
         }
    }
	
	public WSConfigDTO getILSResourceModificationWSConfig(){
		return commonBao.getILSResourceModificationWSConfig();
	}
	public WSConfigDTO getTripsScheduleWSConfig(){
		return commonBao.getTripsScheduleWSConfig();
	}
	
}
