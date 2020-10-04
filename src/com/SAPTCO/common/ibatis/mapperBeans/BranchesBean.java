package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class BranchesBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private Long systemId;
	
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
}
