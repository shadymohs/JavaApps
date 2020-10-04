package com.SAPTCO.security.dto;


import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.AssetsBean;

public class AssetsDto extends BaseDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AssetsBean assetObject = new AssetsBean();
	
	
	public AssetsBean getAssetObject() {
		return assetObject;
	}
	public void setAssetObject(AssetsBean assetObject) {
		this.assetObject = assetObject;
	}
	

}
