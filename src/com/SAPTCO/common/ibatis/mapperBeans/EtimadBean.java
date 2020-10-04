package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author ibrahimsm
 *
 */
public class EtimadBean extends BaseDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer adultsCount = 0;
	private Integer childrenCount = 0;
	private String etimadNumber;
	
	public Integer getAdultsCount() {
		return adultsCount;
	}
	public void setAdultsCount(Integer adultsCount) {
		this.adultsCount = adultsCount;
	}
	public Integer getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}
	public String getEtimadNumber() {
		return etimadNumber;
	}
	public void setEtimadNumber(String etimadNumber) {
		this.etimadNumber = etimadNumber;
	}

}
