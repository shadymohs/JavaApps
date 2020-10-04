package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;

public class MenuBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private List<SystemMenuBean> pagesList = new ArrayList<SystemMenuBean>();
	
	public List<SystemMenuBean> getPagesList() {
		return pagesList;
	}
	public void setPagesList(List<SystemMenuBean> pagesList) {
		this.pagesList = pagesList;
	}

}