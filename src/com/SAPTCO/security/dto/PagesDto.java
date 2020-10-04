package com.SAPTCO.security.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.PrivilegeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemMenuBean;
 
/**
 * @author Shady
*/

public class PagesDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private List<SystemMenuBean> systemMenuList = new ArrayList<SystemMenuBean>();
	private SystemMenuBean pageObj = new SystemMenuBean();
	private String codeDesc;
	private List<PrivilegeBean> privelegeList = new ArrayList<PrivilegeBean>();
	private String procResult;
	private Long[] selectedPrivilege;
	private String selectedPrivilegeString;
	private Long systemId;
	
	public List<SystemMenuBean> getSystemMenuList() {
		return systemMenuList;
	}
	public void setSystemMenuList(List<SystemMenuBean> systemMenuList) {
		this.systemMenuList = systemMenuList;
	}	
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public SystemMenuBean getPageObj() {
		return pageObj;
	}
	public void setPageObj(SystemMenuBean pageObj) {
		this.pageObj = pageObj;
	}
	public List<PrivilegeBean> getPrivelegeList() {
		return privelegeList;
	}
	public void setPrivelegeList(List<PrivilegeBean> privelegeList) {
		this.privelegeList = privelegeList;
	}
	public String getProcResult() {
		return procResult;
	}
	public void setProcResult(String procResult) {
		this.procResult = procResult;
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
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
}