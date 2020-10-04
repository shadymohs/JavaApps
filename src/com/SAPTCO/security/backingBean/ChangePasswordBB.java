package com.SAPTCO.security.backingBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;
import com.SAPTCO.security.bao.ChangePasswordBao;
import com.SAPTCO.security.dto.ChangePasswordDto;

/**
*
* @author Shady
*/

@ManagedBean(name="changePasswordBB")
@ViewScoped
public class ChangePasswordBB extends BaseBB{
	
	@ManagedProperty("#{changePasswordBao}")
	private ChangePasswordBao changePasswordBao;
	
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private ChangePasswordDto changePasswordDto = new ChangePasswordDto();
	
	public String changePassword() {
		try{
			if(changePasswordBao.checkOldPassword(changePasswordDto)){
				if(changePasswordDto.getNewPassword().equals(changePasswordDto.getConfirmPassword())){
					if(!changePasswordDto.getOldPassword().equals(changePasswordDto.getNewPassword())){
						changePasswordDto.setId(getUserDetails().getId());
						String password = changePasswordBao.changePasssword(changePasswordDto);
						HttpServletRequest request = (HttpServletRequest) (FacesContext.getCurrentInstance().getExternalContext().getRequest());
						UserBean userInf = (UserBean) request.getSession().getAttribute("userInf");
						userInf.setPassword(password);
						request.getSession().setAttribute("userInf",userInf);
						addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.USER_CHANGE_PASSWORD_SUCCESS,FacesMessage.SEVERITY_INFO);
					}else
						addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"Change_password.oldnew_match",FacesMessage.SEVERITY_ERROR);
				}else
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"Change_password.new_not_match",FacesMessage.SEVERITY_ERROR);
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"Change_password.old_wrong",FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	public ChangePasswordBao getChangePasswordBao() {
		return changePasswordBao;
	}
	public void setChangePasswordBao(ChangePasswordBao changePasswordBao) {
		this.changePasswordBao = changePasswordBao;
	}
	public ChangePasswordDto getChangePasswordDto() {
		return changePasswordDto;
	}
	public void setChangePasswordDto(ChangePasswordDto changePasswordDto) {
		this.changePasswordDto = changePasswordDto;
	}
	
}
