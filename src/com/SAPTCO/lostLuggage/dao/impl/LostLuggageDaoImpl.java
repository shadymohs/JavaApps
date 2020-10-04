package com.SAPTCO.lostLuggage.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

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
import com.SAPTCO.lostLuggage.dao.LostLuggageDao;
import com.SAPTCO.lostLuggage.dto.ComplaintDto;
import com.SAPTCO.lostLuggage.dto.LostLuggageDto;
import com.SAPTCO.lostLuggage.dto.TransferLugRequestDto;

@Repository("lostLuggageDao")
public class LostLuggageDaoImpl implements LostLuggageDao{

	

	@Autowired
    private SqlMapClientTemplate sqlMapClientTemplateTR;
	@Autowired
	private SqlMapClientTemplate sqlMapClientTemplateLuggage;
	
	public SqlMapClientTemplate getSqlMapClientTemplateLuggage() {
		return sqlMapClientTemplateLuggage;
	}
	public void setSqlMapClientTemplateLuggage(
			SqlMapClientTemplate sqlMapClientTemplateLuggage) {
		this.sqlMapClientTemplateLuggage = sqlMapClientTemplateLuggage;
	}
	public SqlMapClientTemplate getSqlMapClientTemplateTR() {
		return sqlMapClientTemplateTR;
	}
	public void setSqlMapClientTemplateTR(
			SqlMapClientTemplate sqlMapClientTemplateTR) {
		this.sqlMapClientTemplateTR = sqlMapClientTemplateTR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LostLuggageClass> getLostLuggageClassList() throws Exception {
		return sqlMapClientTemplateLuggage.queryForList("getLostLuggageClassList");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LostLuggageColor> getLostLuggageColorList() throws Exception {
		return sqlMapClientTemplateLuggage.queryForList("getLostLuggageColorList");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LostLuggageSize> getLostLuggageSizeList() throws Exception {
		return sqlMapClientTemplateLuggage.queryForList("getLostLuggageSizeList");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LostLuggageStatus> getLostLuggageStatusList() throws Exception {
		return sqlMapClientTemplateLuggage.queryForList("getLostLuggageStatusList");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<SystemConfiguration> getSystemConfigurationList() throws Exception {
		return sqlMapClientTemplateLuggage.queryForList("getSystemConfigList");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StationBean> getStationList() throws Exception {
		List<StationBean> stationList =  new ArrayList<StationBean>() ;
		stationList =  sqlMapClientTemplateTR.queryForList("getStationList");
		
		return stationList;
	}

	@Override
	public LostLuggageDto getLuggageDataBySticker(LostLuggageDto lostLuggageDto)
			throws Exception {
	    List<LostLuggage>	result = new ArrayList<LostLuggage>();
	    
	   // Map<String, Object> params = new HashMap<String, Object>();
	    //params.put("stickerNumber",(lostLuggageDto.getStickerNumber() !=null && !lostLuggageDto.getStickerNumber() .equals(""))? lostLuggageDto.getStickerNumber()  :null);
		//params.put("reservationNumber",(lostLuggageDto.getLostLuggageObj().getReservationNumber() !=null && !lostLuggageDto.getLostLuggageObj().getReservationNumber().equals(""))? lostLuggageDto.getLostLuggageObj().getReservationNumber()  :null);
		//sqlMapClientTemplateLuggage.queryForObject("getLuggageDataBySticker",params);
		//result = ((List<LostLuggage>) params.get("result"));
		//lostLuggageDto.setProcResult((String) params.get("procResult"));
		
	    if (lostLuggageDto.getStickerNumber()!= null && !lostLuggageDto.getStickerNumber().equals(""))
		result =  sqlMapClientTemplateTR.queryForList("getLuggageDataByStickerQuery",lostLuggageDto.getStickerNumber());
	    else
	      if (lostLuggageDto.getReservationNumber()!=null && !lostLuggageDto.getReservationNumber().equals(""))
		  result =  sqlMapClientTemplateTR.queryForList("getLuggageDataByReservationQuery",lostLuggageDto.getReservationNumber());
		
	    if (result.size()>0)
		  lostLuggageDto.setLostLuggageObj(result.get(0));
		return lostLuggageDto;
	}
	
	@Override
	public void manageLostLuggage(LostLuggageDto lugg) throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("operation",(lugg.getOperation() !=null && !lugg.getOperation() .equals(""))? lugg.getOperation()  :null);
			params.put("stickerNumber",(lugg.getLostLuggageObj().getStickerNumber() !=null && !lugg.getLostLuggageObj().getStickerNumber() .equals(""))? lugg.getLostLuggageObj().getStickerNumber()  :null);
			params.put("contactNumber",(lugg.getLostLuggageObj().getContacteNumber() !=null && !lugg.getLostLuggageObj().getContacteNumber() .equals(""))? lugg.getLostLuggageObj().getContacteNumber()  :null);
			params.put("foundDate",(lugg.getLostLuggageObj().getFoundDateAsString() !=null && !lugg.getLostLuggageObj().getFoundDateAsString().equals(""))? lugg.getLostLuggageObj().getFoundDateAsString()  :null);
			params.put("currentLocation",(lugg.getLostLuggageObj().getCurrentLocation() !=null && !lugg.getLostLuggageObj().getCurrentLocation() .equals(""))? lugg.getLostLuggageObj().getCurrentLocation()  :null);
			params.put("specialMark",(lugg.getLostLuggageObj().getSpecialMark() !=null && !lugg.getLostLuggageObj().getSpecialMark() .equals(""))? lugg.getLostLuggageObj().getSpecialMark()  :null);
			params.put("contentOfLuggage",(lugg.getLostLuggageObj().getLuggageContent() !=null && !lugg.getLostLuggageObj().getLuggageContent() .equals(""))? lugg.getLostLuggageObj().getLuggageContent()  :null);
			params.put("referenceNumber",(lugg.getLostLuggageObj().getRefNumber() !=null && !lugg.getLostLuggageObj().getRefNumber() .equals(""))? lugg.getLostLuggageObj().getRefNumber()  :null);
			params.put("line",(lugg.getLostLuggageObj().getLine() !=null && !lugg.getLostLuggageObj().getLine() .equals(""))? lugg.getLostLuggageObj().getLine()  :null);
			params.put("remarks",(lugg.getLostLuggageObj().getRemarks() !=null && !lugg.getLostLuggageObj().getRemarks() .equals(""))? lugg.getLostLuggageObj().getRemarks()  :null);
			//params.put("branch",(lugg.getLostLuggageObj().getBranch() !=null && !lugg.getLostLuggageObj().getBranch() .equals(""))? lugg.getLostLuggageObj().getBranch()  :null);
			params.put("branchId",(lugg.getLostLuggageObj().getBranchId() !=null)? lugg.getLostLuggageObj().getBranchId()  :null);
			
			params.put("lostLuggageId",(lugg.getLostLuggageObj().getLostLuggageId() !=null)? lugg.getLostLuggageObj().getLostLuggageId()  :null);
			params.put("lostLuggageClassId",(lugg.getLostLuggageObj().getLuggageClass().getId() !=null)? lugg.getLostLuggageObj().getLuggageClass().getId()  :null);
			params.put("lostLuggageColorId",(lugg.getLostLuggageObj().getColor().getId() !=null)? lugg.getLostLuggageObj().getColor().getId()  :null);
			params.put("lostLuggageSizeId",(lugg.getLostLuggageObj().getSize().getId() !=null)? lugg.getLostLuggageObj().getSize().getId()  :null);
			//params.put("lostLuggageStatusId",(lugg.getLostLuggageObj().getCurrentStatus().getId() !=null)? lugg.getLostLuggageObj().getCurrentStatus().getId()  :null);
			params.put("foundStationId",(lugg.getLostLuggageObj().getStationId() !=null)? lugg.getLostLuggageObj().getStationId()  :null);
			params.put("linkedComplaintId",(lugg.getLostLuggageObj().getLinkedComplaintId() !=null)? lugg.getLostLuggageObj().getLinkedComplaintId()  :null);
			params.put("userId",(lugg.getLostLuggageObj().getCreatedBy() !=null)? lugg.getLostLuggageObj().getCreatedBy()  :null);
			
			///
			params.put("ticketNumber",(lugg.getLostLuggageObj().getTicketNumber()!=null)? lugg.getLostLuggageObj().getTicketNumber() :null);
			params.put("tripNumber",(lugg.getLostLuggageObj().getTripNumber() !=null)? lugg.getLostLuggageObj().getTripNumber() :null);
			params.put("tripCode",(lugg.getLostLuggageObj().getTripCode() !=null)? lugg.getLostLuggageObj().getTripCode() :null);
			params.put("tripDate",(lugg.getLostLuggageObj().getTripDateAsString() !=null)? lugg.getLostLuggageObj().getTripDateAsString() :null);
			params.put("passengerName",(lugg.getLostLuggageObj().getPassengerName() !=null)? lugg.getLostLuggageObj().getPassengerName() :null);
			
			sqlMapClientTemplateLuggage.queryForObject("manageLostLuggage",params);
			lugg.getLostLuggageObj().setLostLuggageId((Long) params.get("lostLuggageId"));
			lugg.setProcResult((String) params.get("procResult"));
		
	}
	
	@Override
	public LostLuggageDto searchMatchedComplaints(LostLuggageDto lostLuggage)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<LuggageComplaint> result = new ArrayList<LuggageComplaint>();
		
		    params.put("foundDate",(lostLuggage.getLostLuggageObj().getFoundDateAsString() !=null && !lostLuggage.getLostLuggageObj().getFoundDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getFoundDateAsString()  :null);
			params.put("specialMark",(lostLuggage.getLostLuggageObj().getSpecialMark() !=null && !lostLuggage.getLostLuggageObj().getSpecialMark() .equals(""))? lostLuggage.getLostLuggageObj().getSpecialMark()  :null);
			params.put("luggageContent",(lostLuggage.getLostLuggageObj().getLuggageContent() !=null && !lostLuggage.getLostLuggageObj().getLuggageContent() .equals(""))? lostLuggage.getLostLuggageObj().getLuggageContent()  :null);
			params.put("line",(lostLuggage.getLostLuggageObj().getLine() !=null && !lostLuggage.getLostLuggageObj().getLine() .equals(""))? lostLuggage.getLostLuggageObj().getLine()  :null);
			params.put("branch",(lostLuggage.getLostLuggageObj().getBranch() !=null && !lostLuggage.getLostLuggageObj().getBranch() .equals(""))? lostLuggage.getLostLuggageObj().getBranch()  :null);
			
				
			params.put("luggageClassId",(lostLuggage.getLostLuggageObj().getLuggageClass().getId() !=null)? lostLuggage.getLostLuggageObj().getLuggageClass().getId()  :null);
			params.put("colorId",(lostLuggage.getLostLuggageObj().getColor().getId() !=null)? lostLuggage.getLostLuggageObj().getColor().getId()  :null);
			params.put("sizeId",(lostLuggage.getLostLuggageObj().getSize().getId() !=null)? lostLuggage.getLostLuggageObj().getSize().getId()  :null);
			params.put("foundStationId",(lostLuggage.getLostLuggageObj().getStationId() !=null)? lostLuggage.getLostLuggageObj().getStationId()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("getMatchedComplaint",params);
			result = ((List<LuggageComplaint>) params.get("result"));
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getRegisteredInStation()) ))
					   result.get(i).setRegisteredInStation((stationList.get(j).getLocaleName()) );
				}
			}
			lostLuggage.setProcResult((String) params.get("procResult"));
			lostLuggage.setMatchedComplaintsList(result);
		return lostLuggage;
	}
	
	
	@Override
	public ComplaintDto manageComplaint(ComplaintDto comp) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("operation",(comp.getOperation() !=null && !comp.getOperation() .equals(""))? comp.getOperation()  :null);
		params.put("contactNumber",(comp.getComplaintObj().getContacteNumber() !=null && !comp.getComplaintObj().getContacteNumber() .equals(""))? comp.getComplaintObj().getContacteNumber()  :null);
		params.put("ticketNumber",(comp.getComplaintObj().getTicketNumber() !=null && !comp.getComplaintObj().getTicketNumber() .equals(""))? comp.getComplaintObj().getTicketNumber()  :null);
		params.put("stickerNumber",(comp.getComplaintObj().getStickerNumber() !=null && !comp.getComplaintObj().getStickerNumber() .equals(""))? comp.getComplaintObj().getStickerNumber()  :null);
		params.put("customerName",(comp.getComplaintObj().getCustomerName() !=null && !comp.getComplaintObj().getCustomerName() .equals(""))? comp.getComplaintObj().getCustomerName()  :null);
		
		params.put("contentOfLuggage",(comp.getComplaintObj().getLuggageContent() !=null && !comp.getComplaintObj().getLuggageContent().equals(""))? comp.getComplaintObj().getLuggageContent()  :null);
		params.put("tripCode",(comp.getComplaintObj().getTripCode() !=null && !comp.getComplaintObj().getTripCode() .equals(""))? comp.getComplaintObj().getTripCode()  :null);
		params.put("tripDate",(comp.getComplaintObj().getTripDateAsString() !=null && !comp.getComplaintObj().getTripDateAsString().equals(""))? comp.getComplaintObj().getTripDateAsString()  :null);
		params.put("specialMark",(comp.getComplaintObj().getSpecialMark() !=null && !comp.getComplaintObj().getSpecialMark() .equals(""))? comp.getComplaintObj().getSpecialMark()  :null);
		
		params.put("line",(comp.getComplaintObj().getLine() !=null && !comp.getComplaintObj().equals(""))? comp.getComplaintObj().getLine()  :null);
		params.put("remarks",(comp.getComplaintObj().getRemarks() !=null && !comp.getComplaintObj().getRemarks() .equals(""))? comp.getComplaintObj().getRemarks()  :null);
		//params.put("branch",(comp.getComplaintObj().getBranch() !=null && !comp.getComplaintObj().getBranch() .equals(""))? comp.getComplaintObj().getBranch()  :null);
		params.put("branchId",(comp.getComplaintObj().getBranchId() !=null )? comp.getComplaintObj().getBranchId()  :null);
		
		params.put("luggageComplaintId",(comp.getComplaintObj().getLuggageComplaintId() !=null)? comp.getComplaintObj().getLuggageComplaintId()  :null);
		params.put("lostLuggageClassId",(comp.getComplaintObj().getLuggageClass().getId() !=null)? comp.getComplaintObj().getLuggageClass().getId()  :null);
		params.put("lostLuggageColorId",(comp.getComplaintObj().getColor().getId() !=null)? comp.getComplaintObj().getColor().getId()  :null);
		params.put("lostLuggageSizeId",(comp.getComplaintObj().getSize().getId() !=null)? comp.getComplaintObj().getSize().getId()  :null);
		params.put("lostLuggageStatusId",(comp.getComplaintObj().getCurrentStatus().getId() !=null)? comp.getComplaintObj().getCurrentStatus().getId()  :null);
		
		params.put("stationId",(comp.getComplaintObj().getStationId() !=null)? comp.getComplaintObj().getStationId()  :null);
		params.put("linkedLuggageId",(comp.getComplaintObj().getLinkedLuggageId() !=null)? comp.getComplaintObj().getLinkedLuggageId()  :null);
		params.put("userId",(comp.getComplaintObj().getCreatedBy() !=null)? comp.getComplaintObj().getCreatedBy() :null);
		
		sqlMapClientTemplateLuggage.queryForObject("manageComplaint",params);
		comp.getComplaintObj().setLuggageComplaintId((Long) params.get("luggageComplaintId"));
		comp.setProcResult((String) params.get("procResult"));
		return comp;
	}
	@Override
	public ComplaintDto searchMatchedLostLuggage(ComplaintDto complaint)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<LostLuggage> result = new ArrayList<LostLuggage>();
		   
			params.put("date",(complaint.getComplaintObj().getTripDateAsString() !=null && !complaint.getComplaintObj().getTripDateAsString().equals(""))? complaint.getComplaintObj().getTripDateAsString()  :null);
			params.put("specialMark",(complaint.getComplaintObj().getSpecialMark() !=null && !complaint.getComplaintObj().getSpecialMark() .equals(""))? complaint.getComplaintObj().getSpecialMark()  :null);
			params.put("luggageContent",(complaint.getComplaintObj().getLuggageContent() !=null && !complaint.getComplaintObj().getLuggageContent() .equals(""))? complaint.getComplaintObj().getLuggageContent()  :null);
			params.put("line",(complaint.getComplaintObj().getLine() !=null && !complaint.getComplaintObj().getLine() .equals(""))? complaint.getComplaintObj().getLine()  :null);
			params.put("branch",(complaint.getComplaintObj().getBranch() !=null && !complaint.getComplaintObj().getBranch() .equals(""))? complaint.getComplaintObj().getBranch()  :null);
			
			params.put("luggageClassId",(complaint.getComplaintObj().getLuggageClass().getId() !=null)? complaint.getComplaintObj().getLuggageClass().getId()  :null);
			params.put("colorId",(complaint.getComplaintObj().getColor().getId() !=null)? complaint.getComplaintObj().getColor().getId()  :null);
			params.put("sizeId",(complaint.getComplaintObj().getSize().getId() !=null)? complaint.getComplaintObj().getSize().getId()  :null);
			params.put("stationId",(complaint.getComplaintObj().getStationId() !=null)? complaint.getComplaintObj().getStationId()  :null);
			params.put("sticker",(complaint.getComplaintObj().getStickerNumber() !=null && !complaint.getComplaintObj().getStickerNumber() .equals(""))? complaint.getComplaintObj().getStickerNumber()  :null);
			params.put("ticket",(complaint.getComplaintObj().getTicketNumber() !=null && !complaint.getComplaintObj().getTicketNumber().equals(""))? complaint.getComplaintObj().getTicketNumber()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("getMatchedLuggage",params);
			result = ((List<LostLuggage>) params.get("result"));
			
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getFoundInStation()) ))
					   result.get(i).setFoundInStation((stationList.get(j).getLocaleName()) );
				}
			}
			
			complaint.setProcResult((String) params.get("procResult"));
			complaint.setMatchedLostLuggage(result);
		return complaint;
	}
	@Override
	public ComplaintDto linkComplaintToLuggage(ComplaintDto complaint)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
		
		    params.put("complaintId",(complaint.getComplaintObj().getLuggageComplaintId() !=null)? complaint.getComplaintObj().getLuggageComplaintId() :null);
			params.put("lostLuggageId",(complaint.getComplaintObj().getLinkedLuggageId() !=null)? complaint.getComplaintObj().getLinkedLuggageId()  :null);
			params.put("userId",(complaint.getComplaintObj().getUpdatedBy() !=null)? complaint.getComplaintObj().getUpdatedBy()  :null);
			params.put("remarks",(complaint.getComplaintObj().getRemarks() !=null && !complaint.getComplaintObj().getRemarks().equals(""))? complaint.getComplaintObj().getRemarks()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("linkComplaintToLuggage",params);
			
			complaint.setProcResult((String) params.get("procResult"));
			
		return complaint;
	}
	@Override
	public ComplaintDto deliverAndCloseComplaint(ComplaintDto complaint)
			throws Exception {
		
		 Map<String, Object> params = new HashMap<String, Object>();
			
		    params.put("complaintId",(complaint.getComplaintObj().getLuggageComplaintId() !=null)? complaint.getComplaintObj().getLuggageComplaintId() :null);
			params.put("lostLuggageId",(complaint.getComplaintObj().getLinkedLuggageId() !=null)? complaint.getComplaintObj().getLinkedLuggageId()  :null);
			params.put("userId",(complaint.getComplaintObj().getUpdatedBy() !=null)? complaint.getComplaintObj().getUpdatedBy()  :null);
			params.put("remarks",(complaint.getComplaintObj().getRemarks() !=null && !complaint.getComplaintObj().getRemarks().equals(""))? complaint.getComplaintObj().getRemarks()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("deliverAndCloseComplaint",params);
			
			complaint.setProcResult((String) params.get("procResult"));
			
		return complaint;
	}
	@Override
	public LostLuggageDto linkLuggageToComplaint(LostLuggageDto lostLuggage)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
	    params.put("complaintId",(lostLuggage.getLostLuggageObj().getLinkedComplaintId() !=null)? lostLuggage.getLostLuggageObj().getLinkedComplaintId() :null);
		params.put("lostLuggageId",(lostLuggage.getLostLuggageObj().getLostLuggageId() !=null)? lostLuggage.getLostLuggageObj().getLostLuggageId():null);
		params.put("userId",(lostLuggage.getLostLuggageObj().getUpdatedBy() !=null)? lostLuggage.getLostLuggageObj().getUpdatedBy()  :null);
		params.put("remarks",(lostLuggage.getLostLuggageObj().getRemarks() !=null && !lostLuggage.getLostLuggageObj().getRemarks().equals(""))? lostLuggage.getLostLuggageObj().getRemarks()  :null);
		
		sqlMapClientTemplateLuggage.queryForObject("linkLuggageToComplaint",params);
		
		lostLuggage.setProcResult((String) params.get("procResult"));
		
	    return lostLuggage;
	}
	@Override
	public List<LuggageComplaint> searchAllComplaints(ComplaintDto complaint)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<LuggageComplaint> result = new ArrayList<LuggageComplaint>();
		
		    params.put("complaintId",(complaint.getComplaintObj().getLuggageComplaintId()!=null)? complaint.getComplaintObj().getLuggageComplaintId():null);
			params.put("statusId",(complaint.getComplaintObj().getCurrentStatus().getId()!=null)? complaint.getComplaintObj().getCurrentStatus().getId():null);
			params.put("mobile",(complaint.getComplaintObj().getContacteNumber() !=null && !complaint.getComplaintObj().getContacteNumber().equals(""))? complaint.getComplaintObj().getContacteNumber() :null);
			params.put("fromDate",(complaint.getComplaintObj().getFromDateAsString() !=null && !complaint.getComplaintObj().getFromDateAsString().equals(""))? complaint.getComplaintObj().getFromDateAsString() :null);
			params.put("toDate",(complaint.getComplaintObj().getToDateAsString() !=null && !complaint.getComplaintObj().getToDateAsString().equals(""))? complaint.getComplaintObj().getToDateAsString() :null);
			params.put("stationId",(complaint.getComplaintObj().getStationId()!=null)? complaint.getComplaintObj().getStationId():null);
			
			params.put("onlyMyLuggage",(complaint.getComplaintObj().getOnlyMyLuggage() !=null)? (complaint.getComplaintObj().getOnlyMyLuggage()==true?(Long.valueOf(1)):Long.valueOf(0) )  :null);
			params.put("userId",(complaint.getComplaintObj().getCreatedBy()!=null)? complaint.getComplaintObj().getCreatedBy()  :null);
			
			params.put("luggageClassId",(complaint.getComplaintObj().getLuggageClass().getId() !=null)? complaint.getComplaintObj().getLuggageClass().getId()  :null);
			params.put("colorId",(complaint.getComplaintObj().getColor().getId() !=null)? complaint.getComplaintObj().getColor().getId()  :null);
			params.put("sizeId",(complaint.getComplaintObj().getSize().getId() !=null)? complaint.getComplaintObj().getSize().getId()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("searchAllComplaints",params);
			result = ((List<LuggageComplaint>) params.get("result"));
			complaint.setProcResult((String) params.get("procResult"));
		
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getRegisteredInStation()) ))
					   result.get(i).setRegisteredInStation((stationList.get(j).getLocaleName()) );
				}
			}
			return result;
	}
	@Override
	public List<LostLuggage> searchAllLuggage(LostLuggageDto lostLuggage)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		 List<LostLuggage> result = new ArrayList<LostLuggage>();
		
		    params.put("luggageId",(lostLuggage.getLostLuggageObj().getLostLuggageId()!=null)? lostLuggage.getLostLuggageObj().getLostLuggageId():null);
			params.put("statusId",(lostLuggage.getLostLuggageObj().getCurrentStatus().getId()!=null)? lostLuggage.getLostLuggageObj().getCurrentStatus().getId():null);
			params.put("branch",(lostLuggage.getLostLuggageObj().getBranchId() !=null )? lostLuggage.getLostLuggageObj().getBranchId() :null);
			params.put("fromDate",(lostLuggage.getLostLuggageObj().getFromDateAsString() !=null && !lostLuggage.getLostLuggageObj().getFromDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getFromDateAsString() :null);
			params.put("toDate",(lostLuggage.getLostLuggageObj().getToDateAsString() !=null && !lostLuggage.getLostLuggageObj().getToDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getToDateAsString() :null);
			params.put("stationId",(lostLuggage.getLostLuggageObj().getStationId()!=null)? lostLuggage.getLostLuggageObj().getStationId():null);
			
			params.put("onlyMyLuggage",(lostLuggage.getLostLuggageObj().getOnlyMyLuggage()!=null)?(lostLuggage.getLostLuggageObj().getOnlyMyLuggage()==true?(Long.valueOf(1) ):Long.valueOf(0)):null);
			params.put("userId",(lostLuggage.getLostLuggageObj().getCreatedBy() !=null)? lostLuggage.getLostLuggageObj().getCreatedBy()  :null);
			params.put("luggageClassId",(lostLuggage.getLostLuggageObj().getLuggageClass().getId() !=null)? lostLuggage.getLostLuggageObj().getLuggageClass().getId()  :null);
			params.put("colorId",(lostLuggage.getLostLuggageObj().getColor().getId() !=null)? lostLuggage.getLostLuggageObj().getColor().getId()  :null);
			params.put("sizeId",(lostLuggage.getLostLuggageObj().getSize().getId() !=null)? lostLuggage.getLostLuggageObj().getSize().getId()  :null);
			params.put("sticker",(lostLuggage.getLostLuggageObj().getStickerNumber() !=null && !lostLuggage.getLostLuggageObj().getStickerNumber().equals(""))? lostLuggage.getLostLuggageObj().getStickerNumber() :null);
			
			sqlMapClientTemplateLuggage.queryForObject("searchAllLuggage",params);
			result = ((List<LostLuggage>) params.get("result"));
			lostLuggage.setProcResult((String) params.get("procResult"));
		
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getFoundInStation()) ))
					   result.get(i).setFoundInStation((stationList.get(j).getLocaleName()) );
				}
			}
			return result;
	}
	@Override
	public List<LostLuggage> searchLuggageToDeliver(LostLuggageDto lostLuggage)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		 List<LostLuggage> result = new ArrayList<LostLuggage>();
		
		    params.put("luggageId",(lostLuggage.getLostLuggageObj().getLostLuggageId()!=null)? lostLuggage.getLostLuggageObj().getLostLuggageId():null);
			params.put("contactNum",(lostLuggage.getLostLuggageObj().getContacteNumber()!=null)? lostLuggage.getLostLuggageObj().getContacteNumber():null);
			params.put("tripCode",(lostLuggage.getLostLuggageObj().getTripCode() !=null && !lostLuggage.getLostLuggageObj().getTripCode().equals(""))? lostLuggage.getLostLuggageObj().getTripCode() :null);
			params.put("fromDate",(lostLuggage.getLostLuggageObj().getFromDateAsString() !=null && !lostLuggage.getLostLuggageObj().getFromDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getFromDateAsString() :null);
			params.put("toDate",(lostLuggage.getLostLuggageObj().getToDateAsString() !=null && !lostLuggage.getLostLuggageObj().getToDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getToDateAsString() :null);
			params.put("stationId",(lostLuggage.getLostLuggageObj().getStationId()!=null)? lostLuggage.getLostLuggageObj().getStationId():null);
			
			sqlMapClientTemplateLuggage.queryForObject("searchLuggageToDeliver",params);
			result = ((List<LostLuggage>) params.get("result"));
			lostLuggage.setProcResult((String) params.get("procResult"));
		
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getFoundInStation()) ))
					   result.get(i).setFoundInStation((stationList.get(j).getLocaleName()) );
				}
			}
			return result;
	}
	@Override
	public LostLuggageDto finishDeliverLuggage(LostLuggageDto lostLuggage)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		
		params.put("recipientName",(lostLuggage.getLostLuggageObj().getRecipientName()!=null && !lostLuggage.getLostLuggageObj().getRecipientName() .equals(""))? lostLuggage.getLostLuggageObj().getRecipientName()  :null);
		params.put("recipientId",(lostLuggage.getLostLuggageObj().getRecipientId()!=null && !lostLuggage.getLostLuggageObj().getRecipientId() .equals(""))? lostLuggage.getLostLuggageObj().getRecipientId()  :null);
		params.put("storeFee",(lostLuggage.getLostLuggageObj().getStoreFee() !=null)? lostLuggage.getLostLuggageObj().getStoreFee()  : 0F );
		params.put("remarks",(lostLuggage.getLostLuggageObj().getRemarks() !=null && !lostLuggage.getLostLuggageObj().getRemarks().equals(""))? lostLuggage.getLostLuggageObj().getRemarks()  :null);
		params.put("recipientMobile",(lostLuggage.getLostLuggageObj().getRecipientMobile()!=null && !lostLuggage.getLostLuggageObj().getRecipientMobile().equals(""))? lostLuggage.getLostLuggageObj().getRecipientMobile()  :null);
		
		params.put("lostLuggageId",(lostLuggage.getLostLuggageObj().getLostLuggageId() !=null)? lostLuggage.getLostLuggageObj().getLostLuggageId()  :null);
		params.put("userId",(lostLuggage.getLostLuggageObj().getUpdatedBy() !=null)? lostLuggage.getLostLuggageObj().getUpdatedBy()  :null);
		params.put("branchId",(lostLuggage.getLostLuggageObj().getBranchId() !=null)? lostLuggage.getLostLuggageObj().getBranchId()  :null);
		
		
		sqlMapClientTemplateLuggage.queryForObject("finishDeliverLuggage",params);
		
		lostLuggage.setProcResult((String) params.get("procResult"));
		return lostLuggage;
	}
	@Override
	public LostLuggageDto transformToAuction(LostLuggageDto lostLuggage)
			throws Exception {
        Map<String, Object> params = new HashMap<String, Object>();
		params.put("lostLuggageId",lostLuggage.getLostLuggageObj().getLostLuggageId() ) ;
		params.put("userId",(lostLuggage.getLostLuggageObj().getUpdatedBy() !=null)? lostLuggage.getLostLuggageObj().getUpdatedBy()  :null);
		
		sqlMapClientTemplateLuggage.queryForObject("transformToAuction",params);
		
		lostLuggage.setProcResult((String) params.get("procResult"));
		return lostLuggage;
	}
	@Override
	public LostLuggageDto transformToScrap(LostLuggageDto lostLuggage)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
			params.put("lostLuggageId",lostLuggage.getLostLuggageObj().getLostLuggageId());
			params.put("userId",(lostLuggage.getLostLuggageObj().getUpdatedBy() !=null)? lostLuggage.getLostLuggageObj().getUpdatedBy()  :null);
			params.put("remarks",(lostLuggage.getLostLuggageObj().getRemarks() !=null && !lostLuggage.getLostLuggageObj().getRemarks().equals(""))? lostLuggage.getLostLuggageObj().getRemarks()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("transformToScrap",params);
			
			lostLuggage.setProcResult((String) params.get("procResult"));
			return lostLuggage;
	}
	@Override
	public List<LuggageComplaint> getComplaintsToRepayment( ComplaintDto complaint) throws Exception {
		
		Map<String, Object> params = new HashMap<String, Object>();
		 List<LuggageComplaint> result = new ArrayList<LuggageComplaint>();
		
		    params.put("fromDate",(complaint.getComplaintObj().getFromDateAsString() !=null && !complaint.getComplaintObj().getFromDateAsString().equals(""))? complaint.getComplaintObj().getFromDateAsString() :null);
			params.put("toDate",(complaint.getComplaintObj().getToDateAsString() !=null && !complaint.getComplaintObj().getToDateAsString().equals(""))? complaint.getComplaintObj().getToDateAsString() :null);
			params.put("stationId",(complaint.getComplaintObj().getStationId()!=null)? complaint.getComplaintObj().getStationId():null);
			
			sqlMapClientTemplateLuggage.queryForObject("getComplaintsToRepayment",params);
			result = ((List<LuggageComplaint>) params.get("result"));
			complaint.setProcResult((String) params.get("procResult"));
			
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getRegisteredInStation()) ))
					   result.get(i).setRegisteredInStation((stationList.get(j).getLocaleName()) );
				}
			}
			return result;
	}
	@Override
	public ComplaintDto moveToRepayment(ComplaintDto complaint)
			throws Exception {
		
		 Map<String, Object> params = new HashMap<String, Object>();
			
		    params.put("complaintId",(complaint.getComplaintObj().getLuggageComplaintId() !=null)? complaint.getComplaintObj().getLuggageComplaintId() :null);
			params.put("userId",(complaint.getComplaintObj().getUpdatedBy() !=null)? complaint.getComplaintObj().getUpdatedBy()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("moveToRepayment",params);
			
			complaint.setProcResult((String) params.get("procResult"));
			
		return complaint;
	}
	@Override
	public ComplaintDto repaymentAndClose(ComplaintDto complaint)
			throws Exception {
	
		 Map<String, Object> params = new HashMap<String, Object>();
			
		    params.put("complaintId",(complaint.getComplaintObj().getLuggageComplaintId() !=null)? complaint.getComplaintObj().getLuggageComplaintId() :null);
			params.put("userId",(complaint.getComplaintObj().getUpdatedBy() !=null)? complaint.getComplaintObj().getUpdatedBy()  :null);
			params.put("remarks",(complaint.getComplaintObj().getRemarks() !=null && !complaint.getComplaintObj().getRemarks().equals(""))? complaint.getComplaintObj().getRemarks()  :null);
			params.put("receiptNumber",(complaint.getComplaintObj().getReceiptNumber() !=null && !complaint.getComplaintObj().getReceiptNumber().equals(""))? complaint.getComplaintObj().getReceiptNumber()  :null);
			params.put("repaymentValue",(complaint.getComplaintObj().getRepaymentValue() !=null)? complaint.getComplaintObj().getRepaymentValue()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("repaymentAndClose",params);
			
			complaint.setProcResult((String) params.get("procResult"));
			
		return complaint;
	}
	@Override
	public ComplaintDto getCustomerDataBySticker(ComplaintDto comp)
			throws Exception {
		 List<LostLuggage>	result = new ArrayList<LostLuggage>();
		 LostLuggage  lug = new LostLuggage();
		    
		    /*Map<String, Object> params = new HashMap<String, Object>();
			params.put("stickerNumber",(comp.getComplaintObj().getStickerNumber() !=null && !comp.getComplaintObj().getStickerNumber().equals(""))? comp.getComplaintObj().getStickerNumber() :null);
			sqlMapClientTemplateLuggage.queryForObject("getLuggageDataBySticker",params);
			result = ((List<LostLuggage>) params.get("result"));
			comp.setProcResult((String) params.get("procResult"));*/
			
			result =  sqlMapClientTemplateTR.queryForList("getLuggageDataByStickerQuery",comp.getComplaintObj().getStickerNumber());
			if (result.size()>0)
				lug =(result.get(0));
			
			//set the data
			comp.getComplaintObj().setContacteNumber(lug.getContacteNumber());
			comp.getComplaintObj().setCustomerName(lug.getPassengerName());
			comp.getComplaintObj().setTicketNumber(lug.getTicketNumber());
			comp.getComplaintObj().setTripCode(lug.getTripCode());
			comp.getComplaintObj().setTripDate(lug.getTripDate());
			comp.getComplaintObj().setTripNumber(lug.getTripNumber());
			return comp;
	}
	@Override
	public List<LostLuggage> getLuggageToStore(LostLuggageDto lostLuggage)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		 List<LostLuggage> result = new ArrayList<LostLuggage>();
		
		  	params.put("fromDate",(lostLuggage.getLostLuggageObj().getFromDateAsString() !=null && !lostLuggage.getLostLuggageObj().getFromDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getFromDateAsString() :null);
			params.put("toDate",(lostLuggage.getLostLuggageObj().getToDateAsString() !=null && !lostLuggage.getLostLuggageObj().getToDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getToDateAsString() :null);
			params.put("stationId",(lostLuggage.getLostLuggageObj().getStationId()!=null)? lostLuggage.getLostLuggageObj().getStationId():null);
			params.put("branch",(lostLuggage.getLostLuggageObj().getBranch() !=null && !lostLuggage.getLostLuggageObj().getBranch().equals(""))? lostLuggage.getLostLuggageObj().getBranch() :null);
			
			sqlMapClientTemplateLuggage.queryForObject("getLuggageToStore",params);
			result = ((List<LostLuggage>) params.get("result"));
			lostLuggage.setProcResult((String) params.get("procResult"));
		 ///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getFoundInStation()) ))
					   result.get(i).setFoundInStation((stationList.get(j).getLocaleName()) );
				}
			}
		///
			return result;
	}
	
	@Override
	public List<LostLuggage> getLuggageToScrap(LostLuggageDto lostLuggage)
			throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		 List<LostLuggage> result = new ArrayList<LostLuggage>();
		
		  	params.put("fromDate",(lostLuggage.getLostLuggageObj().getFromDateAsString() !=null && !lostLuggage.getLostLuggageObj().getFromDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getFromDateAsString() :null);
			params.put("toDate",(lostLuggage.getLostLuggageObj().getToDateAsString() !=null && !lostLuggage.getLostLuggageObj().getToDateAsString().equals(""))? lostLuggage.getLostLuggageObj().getToDateAsString() :null);
			params.put("stationId",(lostLuggage.getLostLuggageObj().getStationId()!=null)? lostLuggage.getLostLuggageObj().getStationId():null);
			params.put("branch",(lostLuggage.getLostLuggageObj().getBranch() !=null && !lostLuggage.getLostLuggageObj().getBranch().equals(""))? lostLuggage.getLostLuggageObj().getBranch() :null);
			
			sqlMapClientTemplateLuggage.queryForObject("getLuggageToScrap",params);
			result = ((List<LostLuggage>) params.get("result"));
			lostLuggage.setProcResult((String) params.get("procResult"));
		
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().toString().equals((result.get(i).getFoundInStation()) ))
					   result.get(i).setFoundInStation((stationList.get(j).getLocaleName()) );
				}
			}
			return result;
	}
	@Override
	public LostLuggageDto moveToMainStore(LostLuggageDto lostLuggage)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
			params.put("lostLuggageId",lostLuggage.getLostLuggageObj().getLostLuggageId() ) ;
			params.put("userId",( lostLuggage.getLostLuggageObj().getUpdatedBy() !=null)? lostLuggage.getLostLuggageObj().getUpdatedBy()  :null);
			params.put("branchId",(lostLuggage.getLostLuggageObj().getBranchId() !=null)? lostLuggage.getLostLuggageObj().getBranchId()  :null);
			params.put("currentLocation",(lostLuggage.getLostLuggageObj().getCurrentLocation() !=null && !lostLuggage.getLostLuggageObj().getCurrentLocation().equals(""))? lostLuggage.getLostLuggageObj().getCurrentLocation() :null);
			
			sqlMapClientTemplateLuggage.queryForObject("moveToMainStore",params);
			
			lostLuggage.setProcResult((String) params.get("procResult"));
			return lostLuggage;
	}
	@Override
	public LostLuggageDto removeLinkLuggageToComplaint(
			LostLuggageDto lostLuggage) throws Exception {
		
        Map<String, Object> params = new HashMap<String, Object>();
		
	    params.put("complaintId",(lostLuggage.getLostLuggageObj().getLinkedComplaintId() !=null)? lostLuggage.getLostLuggageObj().getLinkedComplaintId() :null);
		params.put("lostLuggageId",(lostLuggage.getLostLuggageObj().getLostLuggageId() !=null)? lostLuggage.getLostLuggageObj().getLostLuggageId():null);
		params.put("userId",(lostLuggage.getLostLuggageObj().getUpdatedBy() !=null)? lostLuggage.getLostLuggageObj().getUpdatedBy()  :null);
		params.put("remarks",(lostLuggage.getLostLuggageObj().getRemarks() !=null && !lostLuggage.getLostLuggageObj().getRemarks().equals(""))? lostLuggage.getLostLuggageObj().getRemarks()  :null);
		
		sqlMapClientTemplateLuggage.queryForObject("removeLinkLuggageToComplaint",params);
		
		lostLuggage.setProcResult((String) params.get("procResult"));
		
	    return lostLuggage;
	}
	@Override
	public ComplaintDto removeLinkComplaintToLuggage(ComplaintDto complaint)
			throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
			
		    params.put("complaintId",(complaint.getComplaintObj().getLuggageComplaintId() !=null)? complaint.getComplaintObj().getLuggageComplaintId() :null);
			params.put("lostLuggageId",(complaint.getComplaintObj().getLinkedLuggageId() !=null)? complaint.getComplaintObj().getLinkedLuggageId()  :null);
			params.put("userId",(complaint.getComplaintObj().getUpdatedBy() !=null)? complaint.getComplaintObj().getUpdatedBy()  :null);
			params.put("remarks",(complaint.getComplaintObj().getRemarks() !=null && !complaint.getComplaintObj().getRemarks().equals(""))? complaint.getComplaintObj().getRemarks()  :null);
			
			sqlMapClientTemplateLuggage.queryForObject("removeLinkLuggageToComplaint",params);
			
			complaint.setProcResult((String) params.get("procResult"));
			
		return complaint;
	}
	@Override
	public GeneralDto manageLuggageProperties(GeneralDto dto,
			String propertyType, String operation) throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
			
		    params.put("type",propertyType);
			params.put("operation",operation);
			params.put("Id",(dto.getId() !=null)? dto.getId() :null);
			params.put("localName",(dto.getLocaleName()!=null && !dto.getLocaleName().equals(""))? dto.getLocaleName():null);
			params.put("foreignName",(dto.getForeignName() !=null && !dto.getForeignName().equals(""))? dto.getForeignName():null);
			
			
			sqlMapClientTemplateLuggage.queryForObject("manageLuggageProperties",params);
			
			dto.setProcResult((String) params.get("procResult"));
			
		return dto;
	}
	
	
	@Override
	public TransferLugRequestDto manageTransferLugRequest(TransferLugRequestDto requestDto
			) throws Exception {
		 Map<String, Object> params = new HashMap<String, Object>();
			
		   
			params.put("operation",requestDto.getOperation());
			params.put("requestId",(requestDto.getTransferLugRequest().getRequestId() !=null)? requestDto.getTransferLugRequest().getRequestId() :null);
			params.put("luggageId",(requestDto.getTransferLugRequest().getLuggage().getLostLuggageId()!=null)? requestDto.getTransferLugRequest().getLuggage().getLostLuggageId():null);
			params.put("userId",(requestDto.getTransferLugRequest().getUser()!=null)? requestDto.getTransferLugRequest().getUser():null);
			params.put("newStationId",(requestDto.getTransferLugRequest().getNewStationId()!=null)? requestDto.getTransferLugRequest().getNewStationId():null);
			params.put("newLocation",(requestDto.getTransferLugRequest().getNewLocation() !=null && !requestDto.getTransferLugRequest().getNewLocation().equals(""))? requestDto.getTransferLugRequest().getNewLocation():null);
			
			
			sqlMapClientTemplateLuggage.queryForObject("manageTransferRequest",params);
			
			requestDto.setProcResult((String) params.get("procResult"));
			
		return requestDto;
	}
	@Override
	public List<TransferLugRequest> searchAllRequests(
			TransferLugRequestDto transferLugRequestDto) throws Exception {
		
		 Map<String, Object> params = new HashMap<String, Object>();
		 List<TransferLugRequest> result = new ArrayList<TransferLugRequest>();
		
		  	params.put("fromDate",(transferLugRequestDto.getFromDateAsString() !=null && !transferLugRequestDto.getFromDateAsString().equals(""))? transferLugRequestDto.getFromDateAsString() :null);
			params.put("toDate",(transferLugRequestDto.getToDateAsString() !=null && !transferLugRequestDto.getToDateAsString().equals(""))? transferLugRequestDto.getToDateAsString() :null);
			
			sqlMapClientTemplateLuggage.queryForObject("getLuggageTransferRequest",params);
			
			result = ((List<TransferLugRequest>) params.get("result"));
			transferLugRequestDto.setProcResult((String) params.get("procResult"));
		
			
			///
			List<StationBean> stationList =  new ArrayList<StationBean>() ;
			stationList =  getStationList();
			for (int i=0;  i<result.size() ; i++)  
			{
				for (int j=0;  j<stationList.size() ; j++)  
				{
				  if (stationList.get(j).getId().equals((result.get(i).getNewStationId()) ))
					   result.get(i).setNewStationLN((stationList.get(j).getLocaleName()) );
				}
			}
		    ///
			
			return result;
	}
}
