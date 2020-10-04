package com.SAPTCO.security.bao;

import com.SAPTCO.security.dto.ChangePasswordDto;

/**
 * @author Shady
*/

public interface ChangePasswordBao{
	
	public String changePasssword(ChangePasswordDto changePasswordDto) throws Exception;
	public Boolean checkOldPassword(ChangePasswordDto changePasswordDto) throws Exception;
	
}