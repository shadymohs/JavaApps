package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class KAUSTAllowanceTypeBean  extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private BranchesBean branch = new BranchesBean();


	public BranchesBean getBranch() {
		return branch;
	}


	public void setBranch(BranchesBean branch) {
		this.branch = branch;
	}
}
