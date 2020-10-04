package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.ibatis.mapperBeans.ProgramGroupsBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemMenuBean;
import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class MenuProgramGroupBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private ProgramGroupsBean programGroups = new ProgramGroupsBean();
	private SystemMenuBean systemMenu = new SystemMenuBean();
	
	public ProgramGroupsBean getProgramGroups() {
		return programGroups;
	}
	public void setProgramGroups(ProgramGroupsBean programGroups) {
		this.programGroups = programGroups;
	}
	public SystemMenuBean getSystemMenu() {
		return systemMenu;
	}
	public void setSystemMenu(SystemMenuBean systemMenu) {
		this.systemMenu = systemMenu;
	}
	
}
