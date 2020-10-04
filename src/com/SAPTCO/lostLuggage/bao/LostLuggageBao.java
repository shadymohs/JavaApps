package com.SAPTCO.lostLuggage.bao;

import java.util.List;

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
import com.SAPTCO.lostLuggage.dto.ComplaintDto;
import com.SAPTCO.lostLuggage.dto.LostLuggageDto;
import com.SAPTCO.lostLuggage.dto.TransferLugRequestDto;

public interface LostLuggageBao {

	
	public List<LostLuggageClass> getLostLuggageClassList() throws Exception;
	
    public List<LostLuggageSize> getLostLuggageSizeList() throws Exception ;
	
	public List<LostLuggageColor> getLostLuggageColorList() throws Exception ;
	
	public List<StationBean> getStationList() throws Exception;
	
	public List<LostLuggageStatus> getLostLuggageStatusList() throws Exception ;
	
	public List<SystemConfiguration> getSystemConfigurationList() throws Exception;
	
	public GeneralDto manageLuggageProperties(GeneralDto dto,String propertyType, String operation) throws Exception ;
	
	//lost luggage
	public LostLuggageDto getLuggageDataBySticker(LostLuggageDto lostLuggageDto) throws Exception ;
	
	public void manageLostLuggage(LostLuggageDto lugg) throws Exception ;
	
	public LostLuggageDto searchMatchedComplaints(LostLuggageDto lostLuggage) throws Exception ;
	
	public LostLuggageDto linkLuggageToComplaint(LostLuggageDto lostLuggage) throws Exception ;
	
	public List<LostLuggage> searchAllLuggage(LostLuggageDto lostLuggage) throws Exception ;
	
	public List<LostLuggage> searchLuggageToDeliver(LostLuggageDto lostLuggage)  throws Exception ;
	
	public LostLuggageDto finishDeliverLuggage(LostLuggageDto lostLuggage) throws Exception ;
	
	public LostLuggageDto transformToAuction(LostLuggageDto lostLuggage) throws Exception ;
	
	public LostLuggageDto transformToScrap(LostLuggageDto lostLuggage) throws Exception ;
	
	public List<LostLuggage> getLuggageToStore(LostLuggageDto lostLuggage) throws Exception ;
	
	public LostLuggageDto moveToMainStore(LostLuggageDto lostLuggage) throws Exception ;
	
	public List<LostLuggage> getLuggageToScrap(LostLuggageDto lostLuggage) throws Exception ;
	
	public LostLuggageDto removeLinkLuggageToComplaint(LostLuggageDto lostLuggage) throws Exception ;
	
	//complaint
	public ComplaintDto manageComplaint(ComplaintDto comp) throws Exception ;
	
	public ComplaintDto searchMatchedLostLuggage(ComplaintDto complaint) throws Exception ;
	
	public ComplaintDto linkComplaintToLuggage(ComplaintDto complaint) throws Exception;
	
	public ComplaintDto removeLinkComplaintToLuggage(ComplaintDto complaint) throws Exception;
	
	public ComplaintDto deliverAndCloseComplaint(ComplaintDto complaint)  throws Exception ;
	
	public List<LuggageComplaint>  searchAllComplaints(ComplaintDto complaint)  throws Exception ;
	
	public List<LuggageComplaint>  getComplaintsToRepayment(ComplaintDto complaint)  throws Exception ;
	
	public ComplaintDto moveToRepayment(ComplaintDto complaint)  throws Exception ;
	
	public ComplaintDto repaymentAndClose(ComplaintDto complaint)  throws Exception ;
	
	public ComplaintDto getCustomerDataBySticker(ComplaintDto comp) throws Exception ;
	
	
	public TransferLugRequestDto manageTransferLugRequest(TransferLugRequestDto requestDto
			) throws Exception ;
	
	public  List<TransferLugRequest> searchAllRequests(TransferLugRequestDto transferLugRequestDto)	throws Exception ;
}
