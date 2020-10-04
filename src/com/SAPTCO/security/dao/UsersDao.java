package com.SAPTCO.security.dao;

import com.SAPTCO.security.dto.UsersDto;
/**
 * @author Shady
*/

public interface UsersDao{
	
	public UsersDto getUsersByName(UsersDto usersDto) throws Exception;
	public UsersDto insertUser(UsersDto usersDto) throws Exception;
	public void updateUser(UsersDto usersDto) throws Exception;
	public void deleteUser(UsersDto usersDto) throws Exception;
	public UsersDto getUserBranches(UsersDto usersDto) throws Exception;
	public void insertUserBranch(UsersDto usersDto) throws Exception;
	public void editUserBranch(UsersDto usersDto) throws Exception;
	public UsersDto getBranchesUser(UsersDto usersDto) throws Exception;
	public UsersDto getProgramsUser(UsersDto usersDto) throws Exception;
	public UsersDto getEmployees(UsersDto usersDto) throws Exception;
	public UsersDto getUserById(UsersDto usersDto) throws Exception;
	public void resetPassword(UsersDto usersDto) throws Exception;
	public Boolean validateUserBranch(UsersDto usersDto) throws Exception;
	
}
