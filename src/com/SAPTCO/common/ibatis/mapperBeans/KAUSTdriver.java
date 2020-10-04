package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

public class KAUSTdriver extends BaseDto{
    
	private static final long serialVersionUID = 1L;
	private String exceptionFromDate;
	private String exceptionToDate;
	private Float dailyRate;

	private KAUSTAllowanceTypeBean KAUSTAllowanceType= new KAUSTAllowanceTypeBean();
	
	public String getExceptionFromDate() {
		return exceptionFromDate;
	}

	public void setExceptionFromDate(String exceptionFromDate) {
		this.exceptionFromDate = exceptionFromDate;
	}

	public String getExceptionToDate() {
		return exceptionToDate;
	}

	public void setExceptionToDate(String exceptionToDate) {
		this.exceptionToDate = exceptionToDate;
	}

	public Float getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(Float dailyRate) {
		this.dailyRate = dailyRate;
	}

	public KAUSTAllowanceTypeBean getKAUSTAllowanceType() {
		return KAUSTAllowanceType;
	}

	public void setKAUSTAllowanceType(KAUSTAllowanceTypeBean kAUSTAllowanceType) {
		KAUSTAllowanceType = kAUSTAllowanceType;
	}
	
	
}
