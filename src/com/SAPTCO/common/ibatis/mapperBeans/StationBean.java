package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class StationBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getLabel() {
		return getCode() + " - " + super.getLabel();
	}

}
