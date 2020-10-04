package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class SystemMenuBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private String menuUrl;
	private List<PrivilegeBean> privelegeList = new ArrayList<PrivilegeBean>();
	private Long menuId;
	private SystemMenuBean parentPage = null;
	
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public List<PrivilegeBean> getPrivelegeList() {
		return privelegeList;
	}
	public void setPrivelegeList(List<PrivilegeBean> privelegeList) {
		this.privelegeList = privelegeList;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public SystemMenuBean getParentPage() {
		return parentPage;
	}
	public void setParentPage(SystemMenuBean parentPage) {
		this.parentPage = parentPage;
	}
	
}
