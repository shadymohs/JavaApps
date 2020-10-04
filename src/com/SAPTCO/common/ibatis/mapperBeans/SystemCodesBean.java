package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class SystemCodesBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private SystemTypesBean systemtypes = new SystemTypesBean();
	
	public SystemTypesBean getSystemtypes() {
		return systemtypes;
	}
	public void setSystemtypes(SystemTypesBean systemtypes) {
		this.systemtypes = systemtypes;
	}

}
