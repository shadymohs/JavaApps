package com.SAPTCO.manageAllowance.backingBean;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.manageAllowance.bao.TRTripsBao;
import com.SAPTCO.manageAllowance.dto.NewAllowanceDto;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;

@ManagedBean(name="newManageAllowanceBB")
@ViewScoped
public class NewManageAllowanceBB extends BaseBB {
	
	
	
	@ManagedProperty("#{tRTripsBao}")
	private TRTripsBao tRTripsBao;
	
	/**
	 * @return the tRTripsBao
	 */
	public TRTripsBao gettRTripsBao() {
		return tRTripsBao;
	}


	/**
	 * @param tRTripsBao the tRTripsBao to set
	 */
	public void settRTripsBao(TRTripsBao tRTripsBao) {
		this.tRTripsBao = tRTripsBao;
	}


	/**
	 * @return the tRTripsDto
	 */
	public TRTripsDto gettRTripsDto() {
		return tRTripsDto;
	}


	/**
	 * @param tRTripsDto the tRTripsDto to set
	 */
	public void settRTripsDto(TRTripsDto tRTripsDto) {
		this.tRTripsDto = tRTripsDto;
	}


	/**
	 * @return the newAllowanceDto
	 */
	public NewAllowanceDto getNewAllowanceDto() {
		return newAllowanceDto;
	}


	/**
	 * @param newAllowanceDto the newAllowanceDto to set
	 */
	public void setNewAllowanceDto(NewAllowanceDto newAllowanceDto) {
		this.newAllowanceDto = newAllowanceDto;
	}


	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private TRTripsDto tRTripsDto = new TRTripsDto();
	private NewAllowanceDto newAllowanceDto =new NewAllowanceDto();
	@PostConstruct
	public void init(){
		
	}
	
	
	public void search(){
		
		try {
			tRTripsDto.setIsManageMode(false);
			if(tRTripsDto.getTripNumber() != null || (tRTripsDto.getTripCode() != null && tRTripsDto.getTripDate() != null))
		
				tRTripsDto = tRTripsBao.searchTRTrips(tRTripsDto);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Invalid_Search_Criteria,FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void edit(){
		newAllowanceDto.setNewDriver1Number(tRTripsDto.getAllowanceDetailBean().getDriver1Number());
		newAllowanceDto.setNewDriver2Number(tRTripsDto.getAllowanceDetailBean().getDriver2Number());
		newAllowanceDto.setNewBusNumber(tRTripsDto.getAllowanceDetailBean().getBusNumber());
		tRTripsDto.setIsManageMode(true);
	}

	public void saveNewAllowance()
	{
		try {
			newAllowanceDto.setOldDriverAllowance(tRTripsDto.getAllowanceDetailBean().getId());
			newAllowanceDto.setDriver1Number(tRTripsDto.getAllowanceDetailBean().getDriver1Number());
			newAllowanceDto.setDriver2Number(tRTripsDto.getAllowanceDetailBean().getDriver2Number());
			newAllowanceDto.setBusNumber(tRTripsDto.getAllowanceDetailBean().getBusNumber());
			newAllowanceDto = tRTripsBao.saveNewAllowance(newAllowanceDto);
			if(newAllowanceDto.getProcResult() != null && newAllowanceDto.getProcResult().equals("Y")){
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
				WSConfigDTO wsConfig = getILSResourceModificationWSConfig();
				URL url = new URL(wsConfig.getuRL());
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("Authorization", "Basic " + new String(Base64.encodeBase64((wsConfig.getUserName() + ":" + wsConfig.getPassword()).getBytes())));
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Type", "application/json");
				SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				String input = "{\"TripCode\": \"" + tRTripsDto.getAllowanceDetailBean().getTripCode() +
						"\",\"TripDate\": \"" + tRTripsDto.getAllowanceDetailBean().getTripDate() +
						"\",\"DispatchDateTime\": \"" + df.format(new Date()) +
						"\",\"VehicleCode\": \"" + newAllowanceDto.getNewBusNumber() +
						"\",\"DriverCodes\": [ \"" + newAllowanceDto.getNewDriver1Number() + "\" ,\"" +
						newAllowanceDto.getNewDriver2Number() + "\"]}";
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes());
				os.flush();
				conn.disconnect();
				newAllowanceDto=new NewAllowanceDto();
				tRTripsDto.setAllowanceDetailBean(new AllowanceDetailBean());
				tRTripsDto.setDetailsList(new ArrayList<AllowanceDetailBean>());
				tRTripsDto.setIsManageMode(false);
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,newAllowanceDto.getProcResult(),FacesMessage.SEVERITY_ERROR);	
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
		
	}
	public void cancel(){
		tRTripsDto.setAllowanceDetailBean(new AllowanceDetailBean());
		tRTripsDto.setDetailsList(new ArrayList<AllowanceDetailBean>());
		tRTripsDto.setIsManageMode(false);
	}

}
