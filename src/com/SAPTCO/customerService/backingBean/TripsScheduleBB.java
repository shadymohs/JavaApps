package com.SAPTCO.customerService.backingBean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.json.JSONObject;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.customerService.bao.CustomerServiceBao;

/**
 * @author Shady
 *
 */

@ManagedBean(name="tripsScheduleBB")
@ViewScoped
public class TripsScheduleBB extends BaseBB {

	private List<StationBean> stationsList = new ArrayList<StationBean>();
	private Long fromStation;
	private Long toStation;
	private String tripType;
	private String mobile;
	
	private static final long serialVersionUID = 1L;
	
	private final Log logger = LogFactory.getLog(getClass());
	@ManagedProperty("#{customerServiceBao}")
	private CustomerServiceBao customerServiceBao;
	
	public CustomerServiceBao getCustomerServiceBao() {
		return customerServiceBao;
	}

	public void setCustomerServiceBao(CustomerServiceBao customerServiceBao) {
		this.customerServiceBao = customerServiceBao;
	}	
	
	@PostConstruct
	public void init(){
		mobile = null;
		tripType = null;
		fromStation = null;
		toStation = null;
		stationsList = new ArrayList<StationBean>();
	}
	
	public void getStationByTripType(){
		try {
			if(tripType != null && !tripType.equals("")){
				if(tripType.equals("2"))
					stationsList = customerServiceBao.getStationsList(true);
				else
					stationsList = customerServiceBao.getStationsList(false);
			}else
				stationsList = new ArrayList<StationBean>();
			fromStation = null;
			toStation = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(){
		HttpURLConnection conn = null;
		try{
			if(tripType != null && !tripType.equals("")){
				if(fromStation != null && toStation != null){
					if(!fromStation.equals(toStation)){
						System.out.println("<------------------------Start WS");
						URL url = new URL(getTripsScheduleWSConfig().getuRL());
						conn = (HttpURLConnection) url.openConnection();conn.setDoOutput(true);
						conn.setRequestMethod("POST");
						conn.setRequestProperty("Content-Type", "application/json");
						if(mobile.length() > 5 && ("+966").equals(mobile.substring(0,4)) && mobile.charAt(4) == '0')
							mobile = mobile.substring(0,4) + mobile.substring(5);
						String input = "{\"MobileNo\": \"" + mobile +
								"\",\"FromStation\": \"" + fromStation.toString() +
								"\",\"ToStation\": \"" + toStation.toString() +
								"\",\"Lang\": \"" + "ar" + 
								"\",\"TripCode\": \"" + tripType +  "\"}";
						OutputStream os = conn.getOutputStream();
						os.write(input.getBytes());
						os.flush();
						System.out.println(conn.getResponseCode() + "End WS ----------------->");
						if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpURLConnection.HTTP_OK){
							BufferedReader br = new BufferedReader(new InputStreamReader(
									(conn.getInputStream())));
							String loopVar = null;
							StringBuilder sb = new StringBuilder();
					        while ((loopVar = br.readLine()) != null) {
					        	sb = sb.append(loopVar+"\n");
							}
							JSONObject resultList = new JSONObject(sb.toString());
							if(resultList != null){
						        String status = (String) resultList.get("Status");
					        	if(status != null && status.equals("1")){
					        		mobile = null;
					        		fromStation = null;
					        		toStation = null;
					        		tripType = null;
					        		stationsList = new ArrayList<StationBean>();
									addMessage(
										SystemConstants.Error_RESOURCE_BUNDLE,
										"send_success");
					        	}else
					        		addErrorMessageByCode(
					        				SystemConstants.Error_RESOURCE_BUNDLE,
					        				SystemConstants.Process_Exception,
					        				FacesMessage.SEVERITY_ERROR);
							}
						}else
							addErrorMessageByCode(
								SystemConstants.Error_RESOURCE_BUNDLE,
								SystemConstants.Process_Exception,
								FacesMessage.SEVERITY_ERROR);
					}else
						addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"station_same",
							FacesMessage.SEVERITY_ERROR);
				}else
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						"station_required",
						FacesMessage.SEVERITY_ERROR);
			}else
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					"trip_type_required",
					FacesMessage.SEVERITY_ERROR);
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
			logger.error(e.getMessage());		
		}finally{
			try{
				if(conn != null)
					conn.disconnect();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	public String getTripType() {
		return tripType;
	}

	public void setTripType(String tripType) {
		this.tripType = tripType;
	}

	public List<StationBean> getStationsList() {
		return stationsList;
	}

	public void setStationsList(List<StationBean> stationsList) {
		this.stationsList = stationsList;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getFromStation() {
		return fromStation;
	}

	public void setFromStation(Long fromStation) {
		this.fromStation = fromStation;
	}

	public Long getToStation() {
		return toStation;
	}

	public void setToStation(Long toStation) {
		this.toStation = toStation;
	}
}
