package com.SAPTCO.security.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.AssetsBean;
import com.SAPTCO.common.ibatis.mapperBeans.PlateTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.VehicleLicenceBean;
import com.SAPTCO.common.ibatis.mapperInterface.Assets;
import com.SAPTCO.security.dao.AssetsDao;
import com.SAPTCO.security.dto.VehicleLicenceDto;

@Repository("assetsDao")
public class AssetsDaoImpl implements AssetsDao {

	@Autowired
	SqlSession session;

	
	
	@Override
	public void insertAsset(AssetsBean asset) throws Exception{
		asset.setCreatedBy(BaseBB.getUserDetails().getId());
		asset.setSystemId(BaseBB.getUserDetails().getLoggedInSystem().getId());
		Assets mapper =  session.getMapper(Assets.class);
		mapper.insertAsset(asset);
		
	}



	@Override
	public AssetsBean findAssetById(AssetsBean asset) throws Exception {
		session.clearCache();
		AssetsBean result = new AssetsBean();
		Assets mapper =  session.getMapper(Assets.class);
		result = mapper.findAssetById(asset);
		return result;
	}



	@Override
	public void updateAsset(AssetsBean asset) throws Exception {
		asset.setUpdatedBy(BaseBB.getUserDetails().getId());
		Assets mapper =  session.getMapper(Assets.class);
		mapper.updateAsset(asset);
		
	}



	@Override
	public List<VehicleLicenceBean> findAllVehicleLicence() throws Exception {
		 List<VehicleLicenceBean>   result = new  ArrayList<VehicleLicenceBean>();
			Assets mapper =  session.getMapper(Assets.class);
			result= mapper.findAllVehicleLicence();
		 return result;
	}



	@Override
	public void updateVehicleLicence(VehicleLicenceBean vehicleLicence)
			throws Exception {
		
		Assets mapper =  session.getMapper(Assets.class);
		mapper.updateVehicleLicence(vehicleLicence);
		
	}



	@Override
	public void deleteVehicleLicence(VehicleLicenceBean vehicleLicence)
			throws Exception {
		Assets mapper =  session.getMapper(Assets.class);
		mapper.deleteVehicleLicence(vehicleLicence);
		
	}



	@Override
	public void insertVehicleLicense(VehicleLicenceBean vehicleLicence)
			throws Exception {
		Assets mapper =  session.getMapper(Assets.class);
		mapper.insertVehicleLicense(vehicleLicence);
		
	}



	@Override
	public VehicleLicenceBean findVehicleLicenceByID(VehicleLicenceBean vehicleLicence) throws Exception {
		session.clearCache();
		VehicleLicenceBean result = new VehicleLicenceBean();
		Assets mapper =  session.getMapper(Assets.class);
		result = mapper.findVehicleLicenceByID(vehicleLicence);
		return result;
	}



	@Override
	public List<PlateTypeBean> getPlateType() throws Exception {
		session.clearCache();
		 List<PlateTypeBean> result = new ArrayList<PlateTypeBean>();
		 Assets mapper =  session.getMapper(Assets.class);
		result = mapper.getPlateType();
		return result;
	}



	@Override
	public Integer validateExistedAsset(String assetNumber) throws Exception {
		session.clearCache();
		Assets mapper =  session.getMapper(Assets.class);
		Integer result = 0;
		result= mapper.validateExistedAsset(assetNumber);
		return result;
	}



	@Override
	public List<VehicleLicenceBean> searchVehicleLicence(
			VehicleLicenceDto vehicleLicenceDto) throws Exception {
		
		 session.clearCache();
		 List<VehicleLicenceBean>   result = new  ArrayList<VehicleLicenceBean>();
			Assets mapper =  session.getMapper(Assets.class);
			result= mapper.searchVehicleLicence(vehicleLicenceDto);
		 return result;
	}

}
