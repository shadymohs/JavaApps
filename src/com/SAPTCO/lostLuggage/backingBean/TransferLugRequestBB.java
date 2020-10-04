package com.SAPTCO.lostLuggage.backingBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.common.ibatis.mapperBeans.TransferLugRequest;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;
import com.SAPTCO.lostLuggage.dto.TransferLugRequestDto;


@ManagedBean(name="transferLugRequestBB")
@ViewScoped
public class TransferLugRequestBB   extends BaseBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private TransferLugRequestDto transferLugRequestDto = new TransferLugRequestDto();
	private List<StationBean>  stationList = new ArrayList<StationBean>();
	private List<TransferLugRequest> transferLugRequests = new ArrayList<TransferLugRequest>();
	
	
	@ManagedProperty("#{lostLuggageBao}")
	private LostLuggageBao lostLuggageBao;
	
	
	 public void searchAllRequests(){
			try {
				
				this.transferLugRequests = (lostLuggageBao.searchAllRequests(transferLugRequestDto));
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
		 
	   }
	 
	 public void approveRequest(){
		 
	 }
	 
    public void rejectRequest(){
		 
	 }
    
    
	public TransferLugRequestDto getTransferLugRequestDto() {
		return transferLugRequestDto;
	}
	public void setTransferLugRequestDto(TransferLugRequestDto transferLugRequestDto) {
		this.transferLugRequestDto = transferLugRequestDto;
	}
	public List<StationBean> getStationList() {
		return stationList;
	}
	public void setStationList(List<StationBean> stationList) {
		this.stationList = stationList;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Log getLogger() {
		return logger;
	}

	public List<TransferLugRequest> getTransferLugRequests() {
		return transferLugRequests;
	}

	public void setTransferLugRequests(List<TransferLugRequest> transferLugRequests) {
		this.transferLugRequests = transferLugRequests;
	}

	public LostLuggageBao getLostLuggageBao() {
		return lostLuggageBao;
	}

	public void setLostLuggageBao(LostLuggageBao lostLuggageBao) {
		this.lostLuggageBao = lostLuggageBao;
	}
	
	
}
