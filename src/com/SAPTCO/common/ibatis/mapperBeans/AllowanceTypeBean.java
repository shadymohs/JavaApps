package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;



/**
 * @author alqassemga
 *
 */

public class AllowanceTypeBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private SystemBean system = new SystemBean();
	private Boolean isAuto = true;
	
	
	public SystemBean getSystem() {
		return system;
	}
	public void setSystem(SystemBean system) {
		this.system = system;
	}
	public Boolean getIsAuto() {
		return isAuto;
	}
	public void setIsAuto(Boolean isAuto) {
		this.isAuto = isAuto;
	}

}