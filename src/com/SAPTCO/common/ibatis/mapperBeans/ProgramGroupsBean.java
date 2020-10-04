package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class ProgramGroupsBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private Boolean isActive;
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
}
