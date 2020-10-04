package com.SAPTCO.common.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;
import com.SAPTCO.common.ibatis.mapperBeans.MenuBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;

public class CommonDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private UserBean userBean = new UserBean();
	private List<BranchesBean> branchList = new ArrayList<BranchesBean>();
	private List<SystemBean> systemsList = new ArrayList<SystemBean>();
	private List<CompanyBean> companiesList = new ArrayList<CompanyBean>();
	private String procResult;
	private List<MenuBean> menuList = new ArrayList<MenuBean>();

	public List<BranchesBean> getBranchList() {
		return branchList;
	}
	public void setBranchList(List<BranchesBean> branchList) {
		this.branchList = branchList;
	}
	public UserBean getUserBean() {
		return userBean;
	}
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public List<MenuBean> getMenuList() {
		return menuList;
	}
	public void setMenuList(List<MenuBean> menuList) {
		this.menuList = menuList;
	}
	public List<SystemBean> getSystemsList() {
		return systemsList;
	}
	public void setSystemsList(List<SystemBean> systemsList) {
		this.systemsList = systemsList;
	}
	public List<CompanyBean> getCompaniesList() {
		return companiesList;
	}
	public void setCompaniesList(List<CompanyBean> companiesList) {
		this.companiesList = companiesList;
	}
}
