package com.SAPTCO.security.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.AssetsBean;
import com.SAPTCO.common.ibatis.mapperBeans.PlateTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.VehicleLicenceBean;
import com.SAPTCO.security.bao.AssetsBao;
import com.SAPTCO.security.dao.AssetsDao;
import com.SAPTCO.security.dto.AssetsDto;
import com.SAPTCO.security.dto.VehicleLicenceDto;


@Service("assetsBao")
public class AssetsBaoImpl implements AssetsBao{

	
	@Autowired
	private AssetsDao assetsDao;
	
	
	@Override
	public void insertAsset(AssetsDto assetDto) throws Exception {
		AssetsBean existedAsset = new AssetsBean();
		existedAsset = assetsDao.findAssetById(assetDto.getAssetObject());
		
		if (existedAsset == null || (existedAsset.getPlateNumber() == null && existedAsset.getAssetNumber() == null))
		  assetsDao.insertAsset(assetDto.getAssetObject());
		
		else
			assetsDao.updateAsset(assetDto.getAssetObject());
		
	}

	@Override
	public List<VehicleLicenceBean> findAllVehicleLicence() throws Exception {
	
		return assetsDao.findAllVehicleLicence();
	}


	@Override
	public void updateVehicleLicence(VehicleLicenceBean vehicleLicence)
			throws Exception {
		assetsDao.updateVehicleLicence(vehicleLicence);
		
	}

	public AssetsDao getAssetsDao() {
		return assetsDao;
	}


	public void setAssetsDao(AssetsDao assetsDao) {
		this.assetsDao = assetsDao;
	}

	

	@Override
	public void deleteVehicleLicence(VehicleLicenceBean vehicleLicence)
			throws Exception {
		assetsDao.deleteVehicleLicence(vehicleLicence);
		
	}

	@Override
	public void insertVehicleLicense(VehicleLicenceBean vehicleLicence)
			throws Exception {
		assetsDao.insertVehicleLicense(vehicleLicence);
		
	}

	@Override
	public VehicleLicenceBean findVehicleLicenceByID(
			VehicleLicenceBean vehicleLicence) throws Exception {
	
		return assetsDao.findVehicleLicenceByID(vehicleLicence);
	}

	@Override
	public List<PlateTypeBean> getPlateType() throws Exception {
		
		return assetsDao.getPlateType();
	}

	@Override
	public Integer validateExistedAsset(Long assetNumber) throws Exception {
		
		return assetsDao.validateExistedAsset(assetNumber.toString());
	}

	@Override
	public List<VehicleLicenceBean> searchVehicleLicence(
			VehicleLicenceDto vehicleLicenceDto) throws Exception {
		
		return assetsDao.searchVehicleLicence(vehicleLicenceDto);
	}
}
