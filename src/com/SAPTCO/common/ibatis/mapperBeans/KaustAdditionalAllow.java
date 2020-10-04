package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class KaustAdditionalAllow extends BaseDto{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer month;
	private Integer year;
	private Long totalAddValue;
	private KAUSTAllowanceTypeBean KAUSTAllowanceType= new KAUSTAllowanceTypeBean();
	
	
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Long getTotalAddValue() {
		return totalAddValue;
	}
	public void setTotalAddValue(Long totalAddValue) {
		this.totalAddValue = totalAddValue;
	}
	public KAUSTAllowanceTypeBean getKAUSTAllowanceType() {
		return KAUSTAllowanceType;
	}
	public void setKAUSTAllowanceType(KAUSTAllowanceTypeBean kAUSTAllowanceType) {
		KAUSTAllowanceType = kAUSTAllowanceType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
