package com.SAPTCO.security.bao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.security.bao.UsersBao;
import com.SAPTCO.security.dao.UsersDao;
import com.SAPTCO.security.dto.UsersDto;

/**
 * @author Shady
*/

@Service("usersBao")
public class UsersBaoImpl implements UsersBao {

	@Autowired
	private UsersDao usersDao;

	public UsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public UsersDto getUsersByName(UsersDto usersDto) throws Exception {
		return usersDao.getUsersByName(usersDto);
	}

	public UsersDto insertUser(UsersDto usersDto) throws Exception {
		return usersDao.insertUser(usersDto);
	}

	public void updateUser(UsersDto usersDto) throws Exception {
		usersDao.updateUser(usersDto);
	}

	public void deleteUser(UsersDto usersDto) throws Exception {
		usersDao.deleteUser(usersDto);
	}

	public UsersDto getUserBranches(UsersDto usersDto) throws Exception {
		return usersDao.getUserBranches(usersDto);
	}

	public void insertUserBranch(UsersDto usersDto) throws Exception {
		usersDao.insertUserBranch(usersDto);
	}

	public void editUserBranch(UsersDto usersDto) throws Exception {
		usersDao.editUserBranch(usersDto);
	}

	public UsersDto getBranchesUser(UsersDto usersDto) throws Exception {
		return usersDao.getBranchesUser(usersDto);
	}

	public UsersDto getProgramsUser(UsersDto usersDto) throws Exception {
		return usersDao.getProgramsUser(usersDto);
	}

	public UsersDto getEmployees(UsersDto usersDto) throws Exception {
		return usersDao.getEmployees(usersDto);
	}
	
	public UsersDto getUserById(UsersDto usersDto) throws Exception{
		return usersDao.getUserById(usersDto);
	}

	public void resetPassword(UsersDto usersDto) throws Exception{
		usersDao.resetPassword(usersDto);
	}

	@Override
	public Boolean validateUserBranch(UsersDto usersDto) throws Exception {
		return usersDao.validateUserBranch(usersDto);
	}

}