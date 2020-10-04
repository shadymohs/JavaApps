package com.SAPTCO.security.dao;

import com.SAPTCO.security.dto.ChangePasswordDto;

/**
 * @author Shady
*/

public interface ChangePasswordDao{
	
	public void changePasssword(ChangePasswordDto changePasswordDto) throws Exception;
	
}