package com.SAPTCO.security.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.MenuProgramGroupBean;
import com.SAPTCO.common.ibatis.mapperBeans.PrivilegeBean;
import com.SAPTCO.common.ibatis.mapperBeans.ProgramGroupsBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemMenuBean;

public class ProgramGroupsDto extends BaseDto{
	
	private static final long serialVersionUID = 1L;
	private List<ProgramGroupsBean> programGroupsList = new ArrayList<ProgramGroupsBean>();
	private ProgramGroupsBean programGroupsObj = new ProgramGroupsBean();
	private String codeDesc;
	private List<MenuProgramGroupBean> menuProgramGroups = new ArrayList<MenuProgramGroupBean>();
	private MenuProgramGroupBean menuProgramObj = new MenuProgramGroupBean();
	private List<SystemMenuBean> systemMenuList = new ArrayList<SystemMenuBean>();
	private Long[] selectedPages;
	private String procResult;
	private String selectedPagesString;
	private Long[] selectedPrivilege;
	private String selectedPrivilegeString;
	private List<PrivilegeBean> privilegesList = new ArrayList<PrivilegeBean>();
	
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public ProgramGroupsBean getProgramGroupsObj() {
		return programGroupsObj;
	}
	public void setProgramGroupsObj(ProgramGroupsBean programGroupsObj) {
		this.programGroupsObj = programGroupsObj;
	}
	public List<ProgramGroupsBean> getProgramGroupsList() {
		return programGroupsList;
	}
	public void setProgramGroupsList(List<ProgramGroupsBean> programGroupsList) {
		this.programGroupsList = programGroupsList;
	}
	public List<MenuProgramGroupBean> getMenuProgramGroups() {
		return menuProgramGroups;
	}
	public void setMenuProgramGroups(List<MenuProgramGroupBean> menuProgramGroups) {
		this.menuProgramGroups = menuProgramGroups;
	}
	public MenuProgramGroupBean getMenuProgramObj() {
		return menuProgramObj;
	}
	public void setMenuProgramObj(MenuProgramGroupBean menuProgramObj) {
		this.menuProgramObj = menuProgramObj;
	}
	public List<SystemMenuBean> getSystemMenuList() {
		return systemMenuList;
	}
	public void setSystemMenuList(List<SystemMenuBean> systemMenuList) {
		this.systemMenuList = systemMenuList;
	}
	public Long[] getSelectedPages() {
		return selectedPages;
	}
	public void setSelectedPages(Long[] selectedPages) {
		this.selectedPages = selectedPages;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}
	public String getSelectedPagesString() {
		return selectedPagesString;
	}
	public void setSelectedPagesString(String selectedPagesString) {
		this.selectedPagesString = selectedPagesString;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Arrays.hashCode(selectedPages);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProgramGroupsDto other = (ProgramGroupsDto) obj;
		if (!Arrays.equals(selectedPages, other.selectedPages))
			return false;
		return true;
	}
	public Long[] getSelectedPrivilege() {
		return selectedPrivilege;
	}
	public void setSelectedPrivilege(Long[] selectedPrivilege) {
		this.selectedPrivilege = selectedPrivilege;
	}
	public String getSelectedPrivilegeString() {
		return selectedPrivilegeString;
	}
	public void setSelectedPrivilegeString(String selectedPrivilegeString) {
		this.selectedPrivilegeString = selectedPrivilegeString;
	}
	public List<PrivilegeBean> getPrivilegesList() {
		return privilegesList;
	}
	public void setPrivilegesList(List<PrivilegeBean> privilegesList) {
		this.privilegesList = privilegesList;
	}

}
