package com.SAPTCO.security.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperInterface.Security;
import com.SAPTCO.security.dao.UsersDao;
import com.SAPTCO.security.dto.ChangePasswordDto;
import com.SAPTCO.security.dto.UsersDto;

/**
 * @author Shady
 */

@Repository("usersDao")
public class UsersDaoImpl  implements UsersDao{
	
	@Autowired
	SqlSession session;

	public UsersDto getUsersByName(UsersDto usersDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		usersDto.setUsersList(mapper.getUsersByName(usersDto));
		return usersDto;
	}

	public UsersDto insertUser(UsersDto usersDto) throws Exception {
		usersDto.setCreatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.insertUser(usersDto);
		return usersDto;
	}

	public void updateUser(UsersDto usersDto) throws Exception {
		usersDto.setUpdatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.updateUser(usersDto);
	}

	public void deleteUser(UsersDto usersDto) throws Exception {
		usersDto.setUpdatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.deleteUser(usersDto);
	}

	public UsersDto getUserBranches(UsersDto usersDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		usersDto.setUserBranches(mapper.getUserBranches(usersDto));
		return usersDto;
	}

	public void insertUserBranch(UsersDto usersDto) throws Exception {
		usersDto.setCreatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.insertUserBranch(usersDto);
	}

	public void editUserBranch(UsersDto usersDto) throws Exception {
		usersDto.setUpdatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.editUserBranch(usersDto);
	}

	public UsersDto getBranchesUser(UsersDto usersDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		usersDto.setBranchesList(mapper.getBranchesUser(usersDto));
		return usersDto;
	}

	public UsersDto getProgramsUser(UsersDto usersDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		usersDto.setProgramGroupsList(mapper.getProgramsUser(usersDto));
		return usersDto;
	}

	public UsersDto getEmployees(UsersDto usersDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		usersDto.setEmployeesList(mapper.getEmployees(usersDto));
		return usersDto;
	}
	
	public UsersDto getUserById(UsersDto usersDto) throws Exception{
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		mapper.getUserById(usersDto);
		return usersDto;
	}
	

	public void resetPassword(UsersDto usersDto) throws Exception{
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		ChangePasswordDto changePasswordDto = new ChangePasswordDto();
		changePasswordDto.setNewPassword("zVvrJncvDJI=");
		changePasswordDto.setUpdatedBy(BaseBB.getUserDetails().getId());
		changePasswordDto.setId(usersDto.getUserObj().getId());
		mapper.changePassword(changePasswordDto);
	}

	@Override
	public Boolean validateUserBranch(UsersDto usersDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		int countCheck = mapper.validateUserBranch(usersDto);
		if(countCheck == 0)
			return false;
		else
			return true;
	}
	
}
