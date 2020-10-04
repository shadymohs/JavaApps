package com.SAPTCO.security.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.AssetsBean;
import com.SAPTCO.common.ibatis.mapperBeans.PlateTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.VehicleLicenceBean;
import com.SAPTCO.security.dto.VehicleLicenceDto;

public interface AssetsDao {

	
	public void insertAsset(AssetsBean asset) throws Exception;
	public AssetsBean findAssetById (AssetsBean asset) throws Exception;
	public void updateAsset(AssetsBean asset) throws Exception ;
	
	//vehicle license 
	public List<VehicleLicenceBean> findAllVehicleLicence () throws Exception;
	public void updateVehicleLicence(VehicleLicenceBean vehicleLicence) throws Exception ;
	public void deleteVehicleLicence(VehicleLicenceBean vehicleLicence) throws Exception ;
	public void insertVehicleLicense(VehicleLicenceBean vehicleLicence) throws Exception ;
	public VehicleLicenceBean findVehicleLicenceByID(VehicleLicenceBean vehicleLicence) throws Exception ;
	
	public List<PlateTypeBean> getPlateType()  throws Exception ;
	public Integer validateExistedAsset(String assetNumber) throws Exception ;
	
	public List<VehicleLicenceBean> searchVehicleLicence ( VehicleLicenceDto vehicleLicenceDto) throws Exception;
}
