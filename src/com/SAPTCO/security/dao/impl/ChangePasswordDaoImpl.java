package com.SAPTCO.security.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.ibatis.mapperInterface.Security;
import com.SAPTCO.security.dao.ChangePasswordDao;
import com.SAPTCO.security.dto.ChangePasswordDto;

/**
 * @author Shady
 */

@Repository("changePasswordDao")
public class ChangePasswordDaoImpl  implements ChangePasswordDao{
	
	@Autowired
	SqlSession session;

	//change user password
	public void changePasssword(ChangePasswordDto changePasswordDto) throws Exception {
		Security mapper = session.getMapper(Security.class);
		changePasswordDto.setUpdatedBy(changePasswordDto.getId());
		mapper.changePassword(changePasswordDto);
	}
	
}
