package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class ChannelBean extends BaseDto{
	
	private static final long serialVersionUID = 1L;

	@Override
	public Object getValue() {
		return getCode();
	}
}
