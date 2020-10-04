package com.SAPTCO.security.backingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.DriversBean;
import com.SAPTCO.security.bao.DriversBao;
import com.SAPTCO.security.dto.DriversDto;
import javax.annotation.PostConstruct;

/**
*
* @author Shady
*/

@ManagedBean(name="driversBB")
@ViewScoped
public class DriversBB extends BaseBB{
	
	@ManagedProperty("#{driversBao}")
	private DriversBao driversBao;
	
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private DriversDto driversDto = new DriversDto();
	private List<DriversBean> driversTable = new ArrayList<DriversBean>();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	
	@PostConstruct
	public void init(){
		try{
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("driverid") != null && !params.get("driverid").equals("")){
			driversDto.getDriverObj().setId(Long.parseLong(params.get("driverid")));
			driversDto = driversBao.getDriverById(driversDto);
		}
		}
		catch (Exception e) {
			logger.error(e.getMessage()); 
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String cancel(){
		driversDto.setDriverObj(new DriversBean());
		return "drivers.xhtml";
	}
	
	public void driversSearchListner() {
		try {
			if(driversDto.getCodeDesc() == null || driversDto.getCodeDesc().equals("")){
				driversDto.setCodeDesc(null);
			}
			//return codes list              
			driversDto = driversBao.searchDrivers(driversDto);
			driversTable = driversDto.getDriveresList();
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	

	
	public String saveListner(){
		try{
			if(driversDto.getDriverObj().getId() != null || !driversDto.getDriverObj().getId().equals(0L)){
				//add code
				driversBao.saveDriver(driversDto);
				clearComponent(addEditPanel);				
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return null;
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		return null;
	}
	
	
	public DriversBao getDriversBao() {
		return driversBao;
	}

	public void setDriversBao(DriversBao driversBao) {
		this.driversBao = driversBao;
	}

	public DriversDto getdriversDto() {
		return driversDto;
	}

	public void setdriversDto(DriversDto driversDto) {
		this.driversDto = driversDto;
	}

	public List<DriversBean> getDriversTable() {
		return driversTable;
	}

	public void setDriversTable(List<DriversBean> driversTable) {
		this.driversTable = driversTable;
	}

	public HtmlPanelGrid getAddEditPanel() {
		return addEditPanel;
	}

	public void setAddEditPanel(HtmlPanelGrid addEditPanel) {
		this.addEditPanel = addEditPanel;
	}
}
