package com.SAPTCO.security.backingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import com.SAPTCO.common.ibatis.mapperBeans.PlateTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.VehicleLicenceBean;
import com.SAPTCO.security.bao.AssetsBao;
import com.SAPTCO.security.dto.VehicleLicenceDto;

@ManagedBean(name="vehicleLicenceBB")
@ViewScoped
public class VehicleLicenceBB  extends BaseBB{

	@ManagedProperty("#{assetsBao}")
	private AssetsBao assetsBao;
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private VehicleLicenceDto vehicleLicenceDto = new VehicleLicenceDto();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private List<VehicleLicenceBean> vehicleLicenseTable  = new ArrayList<VehicleLicenceBean>();
	private List<PlateTypeBean>  plateTypeList = new ArrayList<PlateTypeBean>();
	private Boolean addOperation = false;
	
	
	@PostConstruct
	public void init(){
		
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		
		
		if(params.get("isAddMode") != null && params.get("isAddMode").equals("0")){
		//if(params.get("serialNo") != null && !params.get("serialNo").equals("")){
			addOperation = false;
			vehicleLicenceDto.getVehicleLicenceObj().setSerialNo(Long.parseLong(params.get("serialNo")));
			vehicleLicenceDto.getVehicleLicenceObj().setTagNumber(Long.parseLong(params.get("tagNumber")));
			vehicleLicenceDto.getVehicleLicenceObj().setFXplateNo(params.get("fXplateNo"));
			vehicleLicenceDto.getVehicleLicenceObj().setSectorId(Long.parseLong(params.get("sectorId")));
			vehicleLicenceDto.getVehicleLicenceObj().setSectorName(params.get("sectorName"));
			vehicleLicenceDto.getVehicleLicenceObj().setPlateNumber(Long.parseLong(params.get("plateNumber")));
			vehicleLicenceDto.getVehicleLicenceObj().setPlateLetterLeft(params.get("plateLetterLeft"));
			vehicleLicenceDto.getVehicleLicenceObj().setPlateLetterMiddle(params.get("plateLetterMiddle"));
			vehicleLicenceDto.getVehicleLicenceObj().setPlateLetterRight(params.get("plateLetterRight"));
			//vehicleLicenceDto.getVehicleLicenceObj().setLicenseExpDate(  (params.get("licenseExpDate")));
			vehicleLicenceDto.getVehicleLicenceObj().setPlateType(Long.parseLong(params.get("plateType")));
			vehicleLicenceDto.getVehicleLicenceObj().setBusSeats(Long.parseLong(params.get("busSeats")));
			vehicleLicenceDto.getVehicleLicenceObj().setHijriExpDate(params.get("hijriExpDate"));
				
		}
		else
		{ 
			addOperation = true;  
		}
		
		try{
		
			vehicleLicenseTable = assetsBao.findAllVehicleLicence();
			plateTypeList = assetsBao.getPlateType();
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		
	}
	
	public void vLSearchListner() {
		try {
			   //if(branchesDto.getCodeDesc() == null || branchesDto.getCodeDesc().equals("")){
				//branchesDto.setCodeDesc(null);
			vehicleLicenseTable = assetsBao.searchVehicleLicence(vehicleLicenceDto);
			
			//return codes list
			//branchesTable = new ArrayList<BranchesBean>((branchesBao.getBranchesByCode(branchesDto)).getBranchesList());
			//branchesDto.setCodeDesc(null);
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addListner() {
		try {
			addOperation = true;
			clearComponent(addEditPanel);
			vehicleLicenceDto.setVehicleLicenceObj(new  VehicleLicenceBean());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public String saveListner(){
		try{    
			if(addOperation){
				
				//first check if vehicle existed in fixed asset
				Integer found = null;
				found = assetsBao.validateExistedAsset(vehicleLicenceDto.getVehicleLicenceObj().getTagNumber());
				if (found != null && found>0 )
					   {  //check if vehicle inserted before
					       vehicleLicenceDto.setTagNumber(vehicleLicenceDto.getVehicleLicenceObj().getTagNumber());
					       vehicleLicenceDto.setSerialNo(vehicleLicenceDto.getVehicleLicenceObj().getSerialNo());
					       List<VehicleLicenceBean> vehicleResult = assetsBao.searchVehicleLicence(vehicleLicenceDto);
					       if (vehicleResult.size()>0)
					       {
					    	    addMessage(
										SystemConstants.Error_RESOURCE_BUNDLE,
										SystemConstants.Ununique_Faild);
					       }
					       else
					       {
						       vehicleLicenceDto.getVehicleLicenceObj().setFXplateNo(null);
						       vehicleLicenceDto.getVehicleLicenceObj().setRegisterWASEL(false);
								assetsBao.insertVehicleLicense(vehicleLicenceDto.getVehicleLicenceObj());
						
							  clearComponent(addEditPanel);
							  vehicleLicenceDto.setVehicleLicenceObj(new VehicleLicenceBean());
							  addMessage(
								SystemConstants.Error_RESOURCE_BUNDLE,
								SystemConstants.Save_Success);
							  addOperation=false;
					       }
						}
						else
						{
							addMessage(
									SystemConstants.Error_RESOURCE_BUNDLE,
									"Validate.assets.assetNotExisted");
						}
				return null;
			}else{
				//edit 
				assetsBao.updateVehicleLicence(vehicleLicenceDto.getVehicleLicenceObj());
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "vehicleLicense.xhtml";
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
	
	public void deleteListner(){
		try {
			
			assetsBao.deleteVehicleLicence(vehicleLicenceDto.getVehicleLicenceObj());
			vehicleLicenseTable  = new ArrayList<VehicleLicenceBean>();
			addMessage(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Delete_Success);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	
	public VehicleLicenceDto getVehicleLicenceDto() {
		return vehicleLicenceDto;
	}

	public void setVehicleLicenceDto(VehicleLicenceDto vehicleLicenceDto) {
		this.vehicleLicenceDto = vehicleLicenceDto;
	}

	public AssetsBao getAssetsBao() {
		return assetsBao;
	}

	public void setAssetsBao(AssetsBao assetsBao) {
		this.assetsBao = assetsBao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}

	public HtmlPanelGrid getAddEditPanel() {
		return addEditPanel;
	}

	public void setAddEditPanel(HtmlPanelGrid addEditPanel) {
		this.addEditPanel = addEditPanel;
	}

	public List<VehicleLicenceBean> getVehicleLicenseTable() {
		return vehicleLicenseTable;
	}

	public void setVehicleLicenseTable(List<VehicleLicenceBean> vehicleLicenseTable) {
		this.vehicleLicenseTable = vehicleLicenseTable;
	}

	public List<PlateTypeBean> getPlateTypeList() {
		return plateTypeList;
	}

	public void setPlateTypeList(List<PlateTypeBean> plateTypeList) {
		this.plateTypeList = plateTypeList;
	}
	

}
