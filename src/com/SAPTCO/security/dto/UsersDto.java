package com.SAPTCO.security.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.ProgramGroupsBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserInfBean;

public class UsersDto extends BaseDto{
	
	private static final long serialVersionUID = 1L;
	private String userName;
	private List<UserBean> usersList = new ArrayList<UserBean>();
	private UserBean userObj = new UserBean();
	private List<UserBranchesBean> userBranches = new ArrayList<UserBranchesBean>();
	private UserBranchesBean userBranchObj = new UserBranchesBean();
	private List<ProgramGroupsBean> programGroupsList = new ArrayList<ProgramGroupsBean>();
	private List<BranchesBean> branchesList = new ArrayList<BranchesBean>();
	private Date fromDate;
	private Date toDate;
	private Date expireDate;
	private String procResult;
	private String userFullName;
	private List<UserInfBean> employeesList = new ArrayList<UserInfBean>();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<UserBean> getUsersList() {
		return usersList;
	}
	public void setUsersList(List<UserBean> usersList) {
		this.usersList = usersList;
	}
	public UserBean getUserObj() {
		return userObj;
	}
	public void setUserObj(UserBean userObj) {
		this.userObj = userObj;
	}
	public List<UserBranchesBean> getUserBranches() {
		return userBranches;
	}
	public void setUserBranches(List<UserBranchesBean> userBranches) {
		this.userBranches = userBranches;
	}
	public UserBranchesBean getUserBranchObj() {
		return userBranchObj;
	}
	public void setUserBranchObj(UserBranchesBean userBranchObj) {
		this.userBranchObj = userBranchObj;
	}
	public List<ProgramGroupsBean> getProgramGroupsList() {
		return programGroupsList;
	}
	public void setProgramGroupsList(List<ProgramGroupsBean> programGroupsList) {
		this.programGroupsList = programGroupsList;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public List<BranchesBean> getBranchesList() {
		return branchesList;
	}
	public void setBranchesList(List<BranchesBean> branchesList) {
		this.branchesList = branchesList;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public List<UserInfBean> getEmployeesList() {
		return employeesList;
	}
	public void setEmployeesList(List<UserInfBean> employeesList) {
		this.employeesList = employeesList;
	}
	
}
