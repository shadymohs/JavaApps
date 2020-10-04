package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class SystemConfiguration extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String constantValue;


	public String getConstantValue() {
		return constantValue;
	}


	public void setConstantValue(String constantValue) {
		this.constantValue = constantValue;
	}
	
	

}
