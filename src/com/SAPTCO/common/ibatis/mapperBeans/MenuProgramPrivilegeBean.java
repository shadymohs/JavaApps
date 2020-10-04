package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class MenuProgramPrivilegeBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private MenuProgramGroupBean menuProgramId = new MenuProgramGroupBean();
	private List<PrivilegeBean> priviliageList = new ArrayList<PrivilegeBean>();
	
	public MenuProgramGroupBean getMenuProgramId() {
		return menuProgramId;
	}
	public void setMenuProgramId(MenuProgramGroupBean menuProgramId) {
		this.menuProgramId = menuProgramId;
	}
	public List<PrivilegeBean> getPriviliageList() {
		return priviliageList;
	}
	public void setPriviliageList(List<PrivilegeBean> priviliageList) {
		this.priviliageList = priviliageList;
	}
	
}
