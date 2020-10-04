package com.SAPTCO.hajLimo.backingBean;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.config.ReportInfo;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.EtimadBean;
import com.SAPTCO.hajLimo.bao.HajLimoBao;
import com.SAPTCO.hajLimo.dto.HajLimoDto;
import com.SAPTCO.reports.backingBean.ReportsBB;


@ManagedBean(name="hajLimoBB")
@ViewScoped
public class HajLimoBB  extends ReportsBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
    private HajLimoDto  hajLimoDto = new HajLimoDto();
    private Boolean readyToCalculate = false;
    private Boolean readyToPay = false;
    private Boolean readyToPrint = false;
    private String ticketNumber;

	@ManagedProperty("#{hajLimoBao}")
	private HajLimoBao hajLimoBao ;
    
    @PostConstruct
	public void init(){
    	try {
			hajLimoDto = hajLimoBao.getLookups();
			resetValues();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
    
    public void resetValues(){
    	hajLimoDto.setDriverNumber(null);
    	hajLimoDto.setEtimadList(new ArrayList<EtimadBean>());
    	hajLimoDto.setEtimadObj(new EtimadBean());
    	hajLimoDto.setFromStationId(null);
    	hajLimoDto.setIsManual(false);
    	hajLimoDto.setManualAmount(null);
    	hajLimoDto.setOfficeNumber(null);
    	hajLimoDto.setPaymentMethodId(null);
    	hajLimoDto.setProcResult(null);
    	hajLimoDto.setRemainingAmount(null);
    	hajLimoDto.setTicketNumber(null);
    	hajLimoDto.setToStationId(null);
    	hajLimoDto.setToStations(new ArrayList<BaseDto>());
    	hajLimoDto.setTotalEtimadAmount(null);
    	hajLimoDto.setVatAmount(null);
    	hajLimoDto.setVehicleId(null);
    	hajLimoDto.setVehicleNumber(null);
    	hajLimoDto.setVehiclePrice(null);
    	hajLimoDto.setVehicles(new ArrayList<BaseDto>());
    	hajLimoDto.setTotalEtimad(null);
    	hajLimoDto.setVehicleTypeId(null);
    	readyToCalculate = false;
    	readyToPay = false;
    	readyToPrint = false;
    	ticketNumber = null;
    }
	
	public void getToStations(){
		try {
			hajLimoDto.setToStationId(null);
			hajLimoDto.setVehicleId(null);
			hajLimoDto.setVehicles(new ArrayList<BaseDto>());
			if(hajLimoDto.getFromStationId() != null)
				hajLimoDto = hajLimoBao.getToStations(hajLimoDto);
	    	readyToCalculate = false;
	    	readyToPay = false;
	    	readyToPrint = false;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void getvehicls(){
		try {
			hajLimoDto.setVehicleId(null);
			if(hajLimoDto.getToStationId() != null)
				hajLimoDto = hajLimoBao.getvehicls(hajLimoDto);
	    	readyToCalculate = false;
	    	readyToPay = false;
	    	readyToPrint = false;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void allowCalculate(){
		if(hajLimoDto.getVehicleId() != null)
	    	readyToCalculate = true;
		else
			readyToCalculate = false;
	    readyToPay = false;
	    readyToPrint = false;
	}
	
	public void addEtimad(){
		try {
			if(hajLimoDto.getEtimadObj().getEtimadNumber() != null && hajLimoDto.getEtimadObj().getAdultsCount() != null
						&& hajLimoDto.getEtimadObj().getChildrenCount() != null){
				int count = hajLimoDto.getEtimadObj().getAdultsCount() + hajLimoDto.getEtimadObj().getChildrenCount();
				if(count == 0)
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"etimad_not_valid",FacesMessage.SEVERITY_ERROR);
				else{
					Boolean validEtimad = true;
					for(EtimadBean etimadBean : hajLimoDto.getEtimadList()){
						if(etimadBean.getEtimadNumber().equals(hajLimoDto.getEtimadObj().getEtimadNumber()))
							validEtimad = false;
					}
					if(validEtimad){
						if(!hajLimoBao.validateEtimad(hajLimoDto.getEtimadObj().getEtimadNumber()))
							addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"used_etimad",FacesMessage.SEVERITY_WARN);
						hajLimoDto.getEtimadList().add(hajLimoDto.getEtimadObj());
						hajLimoDto.setEtimadObj(new EtimadBean());
					}else
						addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"etimad_not_valid",FacesMessage.SEVERITY_ERROR);
				}
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"etimad_req",FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteEtimad(){
		Map<String, Object> requestMap = FacesContext.getCurrentInstance().getExternalContext()
				.getRequestMap();
		EtimadBean etimadBean = (EtimadBean) requestMap.get("etimadRow");
		if (etimadBean != null)
			hajLimoDto.getEtimadList().remove(requestMap.get("etimadRow")); 
	}
	
	public void calculatePrice(){
		try {
			if(hajLimoDto.getEtimadObj().getEtimadNumber() == null){
				if(hajLimoDto.getVehicleNumber() != null){
					if(hajLimoDto.getDriverNumber() != null){
						if(((hajLimoDto.getIsManual() && hajLimoDto.getManualAmount() != null) || !hajLimoDto.getIsManual())
								&& hajLimoDto.getVehicleId() != null){
							hajLimoDto = hajLimoBao.calculatePrice(hajLimoDto);
							if(hajLimoDto.getProcResult() != null && !hajLimoDto.getProcResult().equals("Y"))
								addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,hajLimoDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
							else{
								readyToPay = true;
								readyToCalculate = false;
							}
							readyToPrint = false;
						}else
							addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"manual_req",FacesMessage.SEVERITY_ERROR);
					}else
						addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"driver_req",FacesMessage.SEVERITY_ERROR);
				}else
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"vehicle_req",FacesMessage.SEVERITY_ERROR);
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"etimad_not_saved",FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
	public void resetCalculation(){
		readyToCalculate = true;
    	readyToPay = false;
    	readyToPrint = false;
    	hajLimoDto.setIsManual(false);
    	hajLimoDto.setManualAmount(null);
    	hajLimoDto.setRemainingAmount(null);
    	hajLimoDto.setVatAmount(null);
    	hajLimoDto.setVehiclePrice(null);
    	hajLimoDto.setTotalEtimadAmount(null);
	}
	
	public void createTickets(){
		try {
			if(hajLimoDto.getVehicleNumber() != null){
				if(hajLimoDto.getDriverNumber() != null){
					if(hajLimoDto.getSeason() != null){
						hajLimoDto = hajLimoBao.createTickets(hajLimoDto);
						if(hajLimoDto.getProcResult() != null && hajLimoDto.getProcResult().equals("Y")){
							addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
							readyToPrint = true;
							readyToCalculate = false;
							readyToPay = false;
						}else
							addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,hajLimoDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
					}else
						addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"season_req",FacesMessage.SEVERITY_ERROR);
				}else
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"driver_req",FacesMessage.SEVERITY_ERROR);
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"vehicle_req",FacesMessage.SEVERITY_ERROR);	
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
	
    public void viewTicket(){
		try { 
			hajLimoBao.printTicket(hajLimoDto.getTicketNumber());
			generateReport(true,3);
			readyToPrint = false;
			readyToPay = false;
			readyToCalculate = false;
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}    
    
	@Override
	protected Connection getConnection() throws Exception {
		return connectionHajLimo();
	}

	@Override
	protected ReportInfo getReportInfo() {
		return  ReportInfo.Haj_Limo_ticket;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		map.put("systemId",getUserDetails().getLoggedInSystem().getId());
		map.put("branchId",getUserDetails().getLoggedInBranch().getId());
		map.put("ticketNumber",hajLimoDto.getTicketNumber());
		return map;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		return null;
	}

	public Log getLogger() {
		return logger;
	}
	public HajLimoBao getHajLimoBao() {
		return hajLimoBao;
	}
	public void setHajLimoBao(HajLimoBao hajLimoBao) {
		this.hajLimoBao = hajLimoBao;
	}
	public HajLimoDto getHajLimoDto() {
		return hajLimoDto;
	}
	public void setHajLimoDto(HajLimoDto hajLimoDto) {
		this.hajLimoDto = hajLimoDto;
	}

	public Boolean getReadyToCalculate() {
		return readyToCalculate;
	}

	public void setReadyToCalculate(Boolean readyToCalculate) {
		this.readyToCalculate = readyToCalculate;
	}

	public Boolean getReadyToPay() {
		return readyToPay;
	}

	public void setReadyToPay(Boolean readyToPay) {
		this.readyToPay = readyToPay;
	}

	public Boolean getReadyToPrint() {
		return readyToPrint;
	}

	public void setReadyToPrint(Boolean readyToPrint) {
		this.readyToPrint = readyToPrint;
	}
	
	public void cancelTicket(){
		try {
			String procResult = hajLimoBao.cancelTicket(ticketNumber);
			if(procResult != null && procResult.equals("Y")){
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
				ticketNumber = null;
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,procResult,FacesMessage.SEVERITY_ERROR);	
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    
    public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
}
