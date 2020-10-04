package com.SAPTCO.security.bao.impl;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.security.bao.ChangePasswordBao;
import com.SAPTCO.security.dao.ChangePasswordDao;
import com.SAPTCO.security.dto.ChangePasswordDto;

/**
 * @author Shady
*/

@Service("changePasswordBao")
public class ChangePasswordBaoImpl implements ChangePasswordBao {

	@Autowired
	private ChangePasswordDao changePasswordDao;

	@Autowired
	private StandardPBEStringEncryptor passwordEncryptor;

	public String changePasssword(ChangePasswordDto changePasswordDto) throws Exception {
		changePasswordDto.setNewPassword(passwordEncryptor.encrypt(changePasswordDto.getNewPassword()));
		changePasswordDao.changePasssword(changePasswordDto);
		return changePasswordDto.getNewPassword();
	}

	public Boolean checkOldPassword(ChangePasswordDto changePasswordDto)throws Exception {
		if(passwordEncryptor.encrypt(changePasswordDto.getOldPassword()).equals(BaseBB.getUserDetails().getPassword()))
			return true;
		return false;
	}

	public ChangePasswordDao getChangePasswordDao() {
		return changePasswordDao;
	}

	public void setChangePasswordDao(ChangePasswordDao changePasswordDao) {
		this.changePasswordDao = changePasswordDao;
	}

	public StandardPBEStringEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(StandardPBEStringEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}

}