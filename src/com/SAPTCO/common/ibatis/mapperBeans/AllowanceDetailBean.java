package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class AllowanceDetailBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private Integer seq;
	private Long tripNumber;
	private Long stationId;
	private String tripCode;
	private String tripDate;
	private String driver1Name;
	private String driver2Name;
	private Long driver1Number;
	private Long driver2Number;
	private Double driver1Allowance;
	private Double driver2Allowance;
	private String busNumber;
	private String plateNumber;
	private Double diesel;
	private Double housing;
	private Double staying;
	private Double visa;
	private Double borderInsurance;
	private Double borderStaying;
	private Double weeklyVacation;
	private Double internalStaying;
	private Double internationalStaying;
	private Double eventVacation;
	private Double ramadan;
	private Double other;
	private Double diesel2;
	private Double housing2;
	private Double staying2;
	private Double visa2;
	private Double borderInsurance2;
	private Double borderStaying2;
	private Double weeklyVacation2;
	private Double internalStaying2;
	private Double internationalStaying2;
	private Double eventVacation2;
	private Double ramadan2;
	private Double other2;
	private Boolean driver1IsMuslim = false;
	private Boolean driver2IsMuslim = false;
	private Boolean isIntegrated = false;
	private Long systemId;
	private AllowanceTypeBean allowanceTypeBean = new AllowanceTypeBean();
	//private Boolean isInternational = false;	
	private FinancialElementBean financialElementBean = new FinancialElementBean();
	private Long branchId;
	private String trailerNumber;
	private String description;
	private Boolean isSelected = false;
	private String errorReason;
	
	private String arrivalTime;
	private Integer percentage = 100;
	private Integer percentage2 = 100;
	
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public String getTrailerNumber() {
		return trailerNumber;
	}
	public void setTrailerNumber(String trailerNumber) {
		this.trailerNumber = trailerNumber;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Double getDiesel2() {
		return diesel2;
	}
	public void setDiesel2(Double diesel2) {
		this.diesel2 = diesel2;
	}
	public Double getHousing2() {
		return housing2;
	}
	public void setHousing2(Double housing2) {
		this.housing2 = housing2;
	}
	public Double getStaying2() {
		return staying2;
	}
	public void setStaying2(Double staying2) {
		this.staying2 = staying2;
	}
	public Double getVisa2() {
		return visa2;
	}
	public void setVisa2(Double visa2) {
		this.visa2 = visa2;
	}
	public Double getBorderInsurance2() {
		return borderInsurance2;
	}
	public void setBorderInsurance2(Double borderInsurance2) {
		this.borderInsurance2 = borderInsurance2;
	}
	public Double getBorderStaying2() {
		return borderStaying2;
	}
	public void setBorderStaying2(Double borderStaying2) {
		this.borderStaying2 = borderStaying2;
	}
	public Double getWeeklyVacation2() {
		return weeklyVacation2;
	}
	public void setWeeklyVacation2(Double weeklyVacation2) {
		this.weeklyVacation2 = weeklyVacation2;
	}
	public Double getInternalStaying2() {
		return internalStaying2;
	}
	public void setInternalStaying2(Double internalStaying2) {
		this.internalStaying2 = internalStaying2;
	}
	public Double getInternationalStaying2() {
		return internationalStaying2;
	}
	public void setInternationalStaying2(Double internationalStaying2) {
		this.internationalStaying2 = internationalStaying2;
	}
	public Double getEventVacation2() {
		return eventVacation2;
	}
	public void setEventVacation2(Double eventVacation2) {
		this.eventVacation2 = eventVacation2;
	}
	public Double getRamadan2() {
		return ramadan2;
	}
	public void setRamadan2(Double ramadan2) {
		this.ramadan2 = ramadan2;
	}
	public Double getOther2() {
		return other2;
	}
	public void setOther2(Double other2) {
		this.other2 = other2;
	}
	
	public Long getTripNumber() {
		return tripNumber;
	}
	public void setTripNumber(Long tripNumber) {
		this.tripNumber = tripNumber;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getTripCode() {
		return tripCode;
	}
	public void setTripCode(String tripCode) {
		this.tripCode = tripCode;
	}
	public String getTripDate() {
		return tripDate;
	}
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	public String getDriver1Name() {
		return driver1Name;
	}
	public void setDriver1Name(String driver1Name) {
		this.driver1Name = driver1Name;
	}
	public String getDriver2Name() {
		return driver2Name;
	}
	public void setDriver2Name(String driver2Name) {
		this.driver2Name = driver2Name;
	}
	public Long getDriver1Number() {
		return driver1Number;
	}
	public void setDriver1Number(Long driver1Number) {
		this.driver1Number = driver1Number;
	}
	public Long getDriver2Number() {
		return driver2Number;
	}
	public void setDriver2Number(Long driver2Number) {
		this.driver2Number = driver2Number;
	}
	public Double getDriver1Allowance() {
		return driver1Allowance;
	}
	public void setDriver1Allowance(Double driver1Allowance) {
		this.driver1Allowance = driver1Allowance;
	}
	public Double getDriver2Allowance() {
		return driver2Allowance;
	}
	public void setDriver2Allowance(Double driver2Allowance) {
		this.driver2Allowance = driver2Allowance;
	}
	public String getBusNumber() {
		return busNumber;
	}
	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public Double getDiesel() {
		return diesel;
	}
	public void setDiesel(Double diesel) {
		this.diesel = diesel;
	}
	public Double getHousing() {
		return housing;
	}
	public void setHousing(Double housing) {
		this.housing = housing;
	}
	public Double getStaying() {
		return staying;
	}
	public void setStaying(Double staying) {
		this.staying = staying;
	}
	public Double getVisa() {
		return visa;
	}
	public void setVisa(Double visa) {
		this.visa = visa;
	}
	public Double getBorderInsurance() {
		return borderInsurance;
	}
	public void setBorderInsurance(Double borderInsurance) {
		this.borderInsurance = borderInsurance;
	}
	public Double getBorderStaying() {
		return borderStaying;
	}
	public void setBorderStaying(Double borderStaying) {
		this.borderStaying = borderStaying;
	}
	public Double getWeeklyVacation() {
		return weeklyVacation;
	}
	public void setWeeklyVacation(Double weeklyVacation) {
		this.weeklyVacation = weeklyVacation;
	}
	public Double getInternalStaying() {
		return internalStaying;
	}
	public void setInternalStaying(Double internalStaying) {
		this.internalStaying = internalStaying;
	}
	public Double getInternationalStaying() {
		return internationalStaying;
	}
	public void setInternationalStaying(Double internationalStaying) {
		this.internationalStaying = internationalStaying;
	}
	public Double getEventVacation() {
		return eventVacation;
	}
	public void setEventVacation(Double eventVacation) {
		this.eventVacation = eventVacation;
	}
	public Double getRamadan() {
		return ramadan;
	}
	public void setRamadan(Double ramadan) {
		this.ramadan = ramadan;
	}
	public Double getOther() {
		return other;
	}
	public void setOther(Double other) {
		this.other = other;
	}
	public Boolean getIsIntegrated() {
		return isIntegrated;
	}
	public void setIsIntegrated(Boolean isIntegrated) {
		this.isIntegrated = isIntegrated;
	}
	public Boolean getDriver1IsMuslim() {
		return driver1IsMuslim;
	}
	public void setDriver1IsMuslim(Boolean driver1IsMuslim) {
		this.driver1IsMuslim = driver1IsMuslim;
	}
	public Boolean getDriver2IsMuslim() {
		return driver2IsMuslim;
	}
	public void setDriver2IsMuslim(Boolean driver2IsMuslim) {
		this.driver2IsMuslim = driver2IsMuslim;
	}
	public Long getSystemId() {
		return systemId;
	}
	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}
	public AllowanceTypeBean getAllowanceTypeBean() {
		return allowanceTypeBean;
	}
	public void setAllowanceTypeBean(AllowanceTypeBean allowanceTypeBean) {
		this.allowanceTypeBean = allowanceTypeBean;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	/*public Boolean getIsInternational() {
		return isInternational;
	}
	public void setIsInternational(Boolean isInternational) {
		this.isInternational = isInternational;
	}*/
	/**
	 * @return the financialElementBean
	 */
	public FinancialElementBean getFinancialElementBean() {
		return financialElementBean;
	}
	/**
	 * @param financialElementBean the financialElementBean to set
	 */
	public void setFinancialElementBean(FinancialElementBean financialElementBean) {
		this.financialElementBean = financialElementBean;
	}
	public Boolean getIsSelected() {
		return isSelected;
	}
	public void setIsSelected(Boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String getErrorReason() {
		return errorReason;
	}
	public void setErrorReason(String errorReason) {
		this.errorReason = errorReason;
	}
	
	public String getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Integer getPercentage() {
		return percentage;
	}
	public void setPercentage(Integer percentage) {
		this.percentage = percentage;
	}
	public Integer getPercentage2() {
		return percentage2;
	}
	public void setPercentage2(Integer percentage2) {
		this.percentage2 = percentage2;
	}

}
