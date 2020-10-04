package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class SystemMenuPrivilegeBean extends BaseDto{
	
	private static final long serialVersionUID = -4322098721080859592L;
	
	private SystemMenuBean menuId = new SystemMenuBean();
	private List<PrivilegeBean> priviliageList = new ArrayList<PrivilegeBean>();
	
	public SystemMenuBean getMenuId() {
		return menuId;
	}
	public void setMenuId(SystemMenuBean menuId) {
		this.menuId = menuId;
	}
	public List<PrivilegeBean> getPriviliageList() {
		return priviliageList;
	}
	public void setPriviliageList(List<PrivilegeBean> priviliageList) {
		this.priviliageList = priviliageList;
	}
	
}
