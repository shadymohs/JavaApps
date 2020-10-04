package com.SAPTCO.security.dto;

import com.SAPTCO.common.dto.BaseDto;
 
/**
 * @author Shady
*/

public class ChangePasswordDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
}