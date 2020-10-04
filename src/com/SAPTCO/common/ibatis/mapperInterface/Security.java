package com.SAPTCO.common.ibatis.mapperInterface;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTAllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.MenuProgramGroupBean;
import com.SAPTCO.common.ibatis.mapperBeans.PrivilegeBean;
import com.SAPTCO.common.ibatis.mapperBeans.ProgramGroupsBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemMenuBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserInfBean;
import com.SAPTCO.security.dto.BranchesDto;
import com.SAPTCO.security.dto.ChangePasswordDto;
import com.SAPTCO.security.dto.DriversDto;
import com.SAPTCO.security.dto.PagesDto;
import com.SAPTCO.security.dto.ProgramGroupsDto;
import com.SAPTCO.security.dto.UsersDto;

/**
 * @author Shady
 */

public interface Security {
	

	@Update(value={"UPDATE SAP_USERS SET USER_PASSWORD = #{newPassword},UPDATE_DATE = SYSDATE,UPDATE_BY = #{updatedBy} WHERE USER_ID = #{id}"})
	public void changePassword(ChangePasswordDto changePasswordDto);
	
	//branches
	public List<BranchesBean> getBranchesByCode(BranchesDto branchesDto);
	public void insertBranch(BranchesDto branchesDto);
	public void updateBranch(BranchesDto branchesDto);
	@Delete(value={"delete from SAP_branches where branch_id = #{branchObj.id}"})
	public void deleteBranch(BranchesDto branchesDto);
	
	//pages
	public List<SystemMenuBean> getPagesByCode(PagesDto pagesDto);
	public void insertPage(PagesDto PagesDto);
	public void updatePage(PagesDto PagesDto);
	public PagesDto deletePage(PagesDto pagesDto);
	public List<SystemMenuBean> getParentPage();
	public List<PrivilegeBean> getAllPagePrivelege(PagesDto pagesDto);
	public List<PrivilegeBean> getCurrentPagePrivelege(PagesDto pagesDto);
	public PagesDto updatePagePrivelege(PagesDto pagesDto);
	
	//program groups
	public List<ProgramGroupsBean> searchProgramGroups(ProgramGroupsDto programGroupsDto);
	//@Delete(value={"delete from SAP_PROGRAM_GROUP where PROGRAM_GROUP_ID = #{programGroupsObj.id}"})
	public void deleteProgramGroups(ProgramGroupsDto programGroupsDto);
	public void insertProgramGroups(ProgramGroupsDto programGroupsDto);
	public void updateProgramGroups(ProgramGroupsDto programGroupsDto);
	public List<MenuProgramGroupBean> getProgramGroupPages(ProgramGroupsDto programGroupsDto);
	public List<SystemMenuBean> getProgramGroupUnselectedPages(ProgramGroupsDto programGroupsDto);
	public ProgramGroupsDto insertProgramGroupMenu(ProgramGroupsDto programGroupsDto);
	public ProgramGroupsDto deleteProgramGroupMenu(ProgramGroupsDto programGroupsDto);
	public List<PrivilegeBean> getAllProgramGroupPagePrivelege(ProgramGroupsDto programGroupsDto);
	public List<PrivilegeBean> getCurrentProgramGroupPagePrivelege(ProgramGroupsDto programGroupsDto);
	public ProgramGroupsDto updateProgramGroupPagePrivelege(ProgramGroupsDto programGroupsDto);
	//drivers
	public DriversDto searchDrivers(DriversDto usersDto);
	public DriversDto updateDriver(DriversDto usersDto);
	public DriversDto getDriverById(DriversDto usersDto);
	public UserInfBean saveDriver(DriversDto usersDto);	
	//KAUST drivers
	public DriversDto saveKaustDriverAllow(DriversDto driverDto);
	public DriversDto saveAllowException(DriversDto driverDto);
	public DriversDto createMonthlyAllow(DriversDto driversDto);
	public DriversDto deleteMonthlyAllow(DriversDto driversDto);
	public DriversDto integrateMonthlyAllow(DriversDto driversDto);
	public DriversDto deleteAllowException(DriversDto driversDto);
	
	public DriversDto saveAdditionalAllow(DriversDto driverDto);
	public List<KAUSTAllowanceTypeBean> getAllKaustAllowTypes();
	public List<KAUSTAllowanceTypeBean> getKaustAllowTypeByBranch(BranchesDto branchesDto);
	public DriversDto deleteAdditionalAllow(DriversDto driversDto);
	
	//users
	public List<UserBean> getUsersByName(UsersDto usersDto);
	public UsersDto insertUser(UsersDto usersDto);
	public void updateUser(UsersDto usersDto);
	public void deleteUser(UsersDto usersDto);
	public List<UserBranchesBean> getUserBranches(UsersDto usersDto);
	public void insertUserBranch(UsersDto usersDto);
	public void editUserBranch(UsersDto usersDto);
	public List<BranchesBean> getBranchesUser(UsersDto usersDto);
	public List<ProgramGroupsBean> getProgramsUser(UsersDto usersDto);
	public List<UserInfBean> getEmployees(UsersDto usersDto);
	public UsersDto getUserById(UsersDto usersDto);
	@Select(value={"select count(*) from SAP_USER_BRANCHES where USER_ID = #{userObj.id} and BRANCH_ID = #{userBranchObj.branchId.id} and (to_date is null or TO_DATE between to_date(#{userBranchObj.fromDate},'dd-mm-yyyy') and to_date(#{userBranchObj.toDateString,jdbcType=DATE},'dd-mm-yyyy'))"})
	public int validateUserBranch(UsersDto usersDto) throws Exception;
	
}
