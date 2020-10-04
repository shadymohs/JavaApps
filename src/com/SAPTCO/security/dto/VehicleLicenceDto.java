package com.SAPTCO.security.dto;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.VehicleLicenceBean;

public class VehicleLicenceDto extends BaseDto{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VehicleLicenceBean vehicleLicenceObj = new VehicleLicenceBean();
	private List<VehicleLicenceBean> vehicleLicenceList  = new ArrayList<VehicleLicenceBean>();
	
	private Long serialNo = null;
	private Long tagNumber = null;
	
	
	
	
	
	
	public Long getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(Long serialNo) {
		this.serialNo = serialNo;
	}
	public Long getTagNumber() {
		return tagNumber;
	}
	public void setTagNumber(Long tagNumber) {
		this.tagNumber = tagNumber;
	}
	public VehicleLicenceBean getVehicleLicenceObj() {
		return vehicleLicenceObj;
	}
	public void setVehicleLicenceObj(VehicleLicenceBean vehicleLicenceObj) {
		this.vehicleLicenceObj = vehicleLicenceObj;
	}
	public List<VehicleLicenceBean> getVehicleLicenceList() {
		return vehicleLicenceList;
	}
	public void setVehicleLicenceList(List<VehicleLicenceBean> vehicleLicenceList) {
		this.vehicleLicenceList = vehicleLicenceList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
