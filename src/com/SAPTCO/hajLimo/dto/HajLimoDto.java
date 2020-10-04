package com.SAPTCO.hajLimo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.EtimadBean;

public class HajLimoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long vehicleTypeId;
	private Long fromStationId;
	private Long toStationId;
	private Long vehicleId;
	private Long paymentMethodId;
	private Boolean isManual;
	private String driverNumber;
	private String vehicleNumber;
	private String officeNumber;
	private String ticketNumber;
	private EtimadBean etimadObj = new EtimadBean();
	private List<EtimadBean> etimadList = new ArrayList<EtimadBean>();
	private Float remainingAmount;
	private Float vatAmount;
	private Float vehiclePrice;
	private Float totalEtimadAmount;
	private Float manualAmount;
	private List<BaseDto> vehicleTypes = new ArrayList<BaseDto>();
	private List<BaseDto> fromStations = new ArrayList<BaseDto>();
	private List<BaseDto> toStations = new ArrayList<BaseDto>();
	private List<BaseDto> vehicles = new ArrayList<BaseDto>();
	private List<BaseDto> paymentMethods = new ArrayList<BaseDto>();
	private String procResult;
	private Integer totalEtimad;
	private Integer season;
	
	public Long getFromStationId() {
		return fromStationId;
	}

	public void setFromStationId(Long fromStationId) {
		this.fromStationId = fromStationId;
	}

	public Long getToStationId() {
		return toStationId;
	}

	public void setToStationId(Long toStationId) {
		this.toStationId = toStationId;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public Long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Boolean getIsManual() {
		return isManual;
	}

	public void setIsManual(Boolean isManual) {
		this.isManual = isManual;
	}

	public String getDriverNumber() {
		return driverNumber;
	}

	public void setDriverNumber(String driverNumber) {
		this.driverNumber = driverNumber;
	}

	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public EtimadBean getEtimadObj() {
		return etimadObj;
	}

	public void setEtimadObj(EtimadBean etimadObj) {
		this.etimadObj = etimadObj;
	}

	public List<EtimadBean> getEtimadList() {
		return etimadList;
	}

	public void setEtimadList(List<EtimadBean> etimadList) {
		this.etimadList = etimadList;
	}

	public Float getRemainingAmount() {
		return remainingAmount;
	}

	public void setRemainingAmount(Float remainingAmount) {
		this.remainingAmount = remainingAmount;
	}

	public Float getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(Float vatAmount) {
		this.vatAmount = vatAmount;
	}

	public Float getVehiclePrice() {
		return vehiclePrice;
	}

	public void setVehiclePrice(Float vehiclePrice) {
		this.vehiclePrice = vehiclePrice;
	}

	public Float getTotalEtimadAmount() {
		return totalEtimadAmount;
	}

	public void setTotalEtimadAmount(Float totalEtimadAmount) {
		this.totalEtimadAmount = totalEtimadAmount;
	}

	public Float getManualAmount() {
		return manualAmount;
	}

	public void setManualAmount(Float manualAmount) {
		this.manualAmount = manualAmount;
	}

	public List<BaseDto> getVehicleTypes() {
		return vehicleTypes;
	}

	public void setVehicleTypes(List<BaseDto> vehicleTypes) {
		this.vehicleTypes = vehicleTypes;
	}

	public List<BaseDto> getFromStations() {
		return fromStations;
	}

	public void setFromStations(List<BaseDto> fromStations) {
		this.fromStations = fromStations;
	}

	public List<BaseDto> getToStations() {
		return toStations;
	}

	public void setToStations(List<BaseDto> toStations) {
		this.toStations = toStations;
	}

	public List<BaseDto> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<BaseDto> vehicles) {
		this.vehicles = vehicles;
	}

	public List<BaseDto> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<BaseDto> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public String getProcResult() {
		return procResult;
	}

	public void setProcResult(String procResult) {
		this.procResult = procResult;
	}

	public Integer getTotalEtimad() {
		totalEtimad = 0;
		for(EtimadBean etimad: getEtimadList()){
			totalEtimad = totalEtimad + etimad.getAdultsCount() + etimad.getChildrenCount();
		}
		return totalEtimad;
	}

	public void setTotalEtimad(Integer totalEtimad) {
		this.totalEtimad = totalEtimad;
	}

	public Integer getSeason() {
		return season;
	}

	public void setSeason(Integer season) {
		this.season = season;
	}

}
