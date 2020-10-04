package com.SAPTCO.lostLuggage.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.dto.GeneralDto;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggage;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageClass;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageColor;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageSize;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageStatus;
import com.SAPTCO.common.ibatis.mapperBeans.LuggageComplaint;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemConfiguration;
import com.SAPTCO.common.ibatis.mapperBeans.TransferLugRequest;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;
import com.SAPTCO.lostLuggage.dao.LostLuggageDao;
import com.SAPTCO.lostLuggage.dto.ComplaintDto;
import com.SAPTCO.lostLuggage.dto.LostLuggageDto;
import com.SAPTCO.lostLuggage.dto.TransferLugRequestDto;


@Service("lostLuggageBao")
public class LostLuggageBaoImpl implements LostLuggageBao{

	@Autowired
	private LostLuggageDao lostLuggageDao;
	
	

	public LostLuggageDao getLostLuggageDao() {
		return lostLuggageDao;
	}



	public void setLostLuggageDao(LostLuggageDao lostLuggageDao) {
		this.lostLuggageDao = lostLuggageDao;
	}



	@Override
	public List<LostLuggageClass> getLostLuggageClassList() throws Exception {
		
		return lostLuggageDao.getLostLuggageClassList();
	}



	@Override
	public List<LostLuggageSize> getLostLuggageSizeList() throws Exception {
		return lostLuggageDao.getLostLuggageSizeList();
	}



	@Override
	public List<LostLuggageColor> getLostLuggageColorList() throws Exception {
		return lostLuggageDao.getLostLuggageColorList();
	}



	@Override
	public List<StationBean> getStationList() throws Exception {
		
		return lostLuggageDao.getStationList();
	}



	@Override
	public LostLuggageDto getLuggageDataBySticker(LostLuggageDto lostLuggageDto)
			throws Exception {
		
		return lostLuggageDao.getLuggageDataBySticker( lostLuggageDto);
		
	}



	@Override
	public void manageLostLuggage(LostLuggageDto lugg) throws Exception {
		lostLuggageDao.manageLostLuggage(lugg);
		
	}



	@Override
	public LostLuggageDto searchMatchedComplaints(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.searchMatchedComplaints(lostLuggage);
	}



	@Override
	public ComplaintDto manageComplaint(ComplaintDto comp) throws Exception {
		return lostLuggageDao.manageComplaint(comp);
		
	}



	@Override
	public ComplaintDto searchMatchedLostLuggage(ComplaintDto complaint)
			throws Exception {
		
		return lostLuggageDao.searchMatchedLostLuggage(complaint);
	}



	@Override
	public ComplaintDto linkComplaintToLuggage(ComplaintDto complaint)
			throws Exception {
		
		return lostLuggageDao.linkComplaintToLuggage(complaint);
	}



	@Override
	public ComplaintDto deliverAndCloseComplaint(ComplaintDto complaint)
			throws Exception {
	
		return lostLuggageDao.deliverAndCloseComplaint(complaint);
		
	}



	@Override
	public LostLuggageDto linkLuggageToComplaint(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.linkLuggageToComplaint( lostLuggage);
	}



	@Override
	public List<LuggageComplaint> searchAllComplaints(ComplaintDto complaint)
			throws Exception {
		
		return lostLuggageDao.searchAllComplaints( complaint);
	}



	@Override
	public List<LostLuggage> searchAllLuggage(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.searchAllLuggage(lostLuggage);
		
	}



	@Override
	public List<LostLuggage> searchLuggageToDeliver(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.searchLuggageToDeliver( lostLuggage) ;
	}



	@Override
	public LostLuggageDto finishDeliverLuggage(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.finishDeliverLuggage(lostLuggage);
	}



	@Override
	public LostLuggageDto transformToAuction(LostLuggageDto lostLuggage)
			throws Exception {
	
		return lostLuggageDao.transformToAuction( lostLuggage);
	}



	@Override
	public LostLuggageDto transformToScrap(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.transformToScrap( lostLuggage);
	}



	@Override
	public List<LuggageComplaint> getComplaintsToRepayment(
			ComplaintDto complaint) throws Exception {
		
		return lostLuggageDao.getComplaintsToRepayment(complaint);
	}



	@Override
	public ComplaintDto moveToRepayment(ComplaintDto complaint)
			throws Exception {
		
		return lostLuggageDao.moveToRepayment(complaint);
	}



	@Override
	public ComplaintDto repaymentAndClose(ComplaintDto complaint)
			throws Exception {
		
		return lostLuggageDao.repaymentAndClose(complaint);
		
	}



	@Override
	public List<LostLuggageStatus> getLostLuggageStatusList() throws Exception {
		
		return lostLuggageDao.getLostLuggageStatusList() ;
	}



	@Override
	public List<SystemConfiguration> getSystemConfigurationList()
			throws Exception {
	
		return lostLuggageDao.getSystemConfigurationList();
	}



	@Override
	public ComplaintDto getCustomerDataBySticker(ComplaintDto comp)
			throws Exception {
		
		return lostLuggageDao.getCustomerDataBySticker( comp);
	}



	@Override
	public List<LostLuggage> getLuggageToStore(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.getLuggageToStore(lostLuggage); 
	}



	@Override
	public LostLuggageDto moveToMainStore(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.moveToMainStore(lostLuggage); 
	}



	@Override
	public List<LostLuggage> getLuggageToScrap(LostLuggageDto lostLuggage)
			throws Exception {
		
		return lostLuggageDao.getLuggageToScrap(lostLuggage);
	}



	@Override
	public LostLuggageDto removeLinkLuggageToComplaint(
			LostLuggageDto lostLuggage) throws Exception {
		
		return lostLuggageDao.removeLinkLuggageToComplaint(lostLuggage);
		
	}



	@Override
	public ComplaintDto removeLinkComplaintToLuggage(ComplaintDto complaint)
			throws Exception {
		
		return lostLuggageDao.removeLinkComplaintToLuggage(complaint); 
	}



	@Override
	public GeneralDto manageLuggageProperties(GeneralDto dto,
			String propertyType, String operation) throws Exception {
		
		return lostLuggageDao.manageLuggageProperties( dto , propertyType, operation);
		
	}



	@Override
	public TransferLugRequestDto manageTransferLugRequest(
			TransferLugRequestDto requestDto) throws Exception {
		
		return lostLuggageDao.manageTransferLugRequest(requestDto);
		
	}



	@Override
	public List<TransferLugRequest> searchAllRequests(
			TransferLugRequestDto transferLugRequestDto) throws Exception {
		
		return lostLuggageDao.searchAllRequests(
				 transferLugRequestDto);
	}
	
	
	
}
