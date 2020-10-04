package com.SAPTCO.manageAllowance.backingBean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.manageAllowance.bao.OtherAllowanceBao;
import com.SAPTCO.manageAllowance.dto.FileUploadDto;
import com.SAPTCO.manageAllowance.dto.OtherAllowanceDto;

@ManagedBean(name="fileUploadBB")
@ViewScoped
public class FileUploadBB extends BaseBB{
	
	private static final long serialVersionUID = 1L;
	private Workbook data = null;
	private final Log logger = LogFactory.getLog(getClass());
	private FileUploadDto fileUploadDto = new FileUploadDto();
	private Boolean integratedAllowances = false;
	private List<AllowanceDetailBean> unIntegratedList = new ArrayList<AllowanceDetailBean>();
	private Boolean isCheckAll = false;
	private List<AllowanceTypeBean> allowanceTypeList = new ArrayList<AllowanceTypeBean>();

	@ManagedProperty("#{otherAllowanceBao}")
	private OtherAllowanceBao otherAllowanceBao;
 
    public void listener(FileUploadEvent event) throws Exception {
    	UploadedFile uploadedfile = event.getUploadedFile();
    	InputStream fileInputStream = new ByteArrayInputStream(uploadedfile.getData());
    	data = WorkbookFactory.create(fileInputStream);
    	fileUploadDto = new FileUploadDto();
    }
    
    public void readExcel(){
    	try{
    		fileUploadDto = otherAllowanceBao.readExcel(data);
    		data = null;
    		integratedAllowances = false;
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
    	}catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
    }
    
    public void sendToHR(){
		try {
			Integer succedIntegreted = 0;
			Integer rejectedIntegreted = 0;
			for(AllowanceDetailBean allowance : fileUploadDto.getSuccedAllowance()){
				OtherAllowanceDto allowanceDetailDto = new OtherAllowanceDto();
				allowanceDetailDto.setAllowanceDetailBean(allowance);
				allowanceDetailDto = otherAllowanceBao.sendToHR(allowanceDetailDto);
				if(allowanceDetailDto.getProcResult() != null && allowanceDetailDto.getProcResult().equals("Y")){
					++succedIntegreted;
				}else
					++rejectedIntegreted;
			}
			fileUploadDto.setSuccedIntegreted(succedIntegreted);
			fileUploadDto.setRejectedIntegreted(rejectedIntegreted);
			integratedAllowances = true;
			fileUploadDto.setSuccedAllowance(new ArrayList<AllowanceDetailBean>());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    
    public void getUnIntegratedAllowances(){
    	try{
    		isCheckAll = false;
    		unIntegratedList = otherAllowanceBao.getUnIntegratedAllowances(getUserDetails().getLoggedInBranch().getId());
    	} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
    }
    
    public void sendUnIntegratedToHR(){
    	try{
    		List<AllowanceDetailBean> selectedList = new ArrayList<AllowanceDetailBean>();
    		for(AllowanceDetailBean allowanceBean : unIntegratedList){
    			if(allowanceBean.getIsSelected())
    				selectedList.add(allowanceBean);
    		}
			for(AllowanceDetailBean allowance : selectedList){
				OtherAllowanceDto allowanceDetailDto = new OtherAllowanceDto();
				allowanceDetailDto.setAllowanceDetailBean(allowance);
				allowanceDetailDto = otherAllowanceBao.sendToHR(allowanceDetailDto);
			}
			getUnIntegratedAllowances();
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success ,FacesMessage.SEVERITY_INFO);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
    }
    
    public void checkAll(){
    	List<AllowanceDetailBean> newList = new ArrayList<AllowanceDetailBean>();
    	for(AllowanceDetailBean allowance : unIntegratedList){
    		allowance.setIsSelected(isCheckAll);
    		newList.add(allowance);
    	}
    	unIntegratedList = newList;
    }
    
    public void delete(){
    	try{
	    	List<AllowanceDetailBean> selectedList = new ArrayList<AllowanceDetailBean>();
			for(AllowanceDetailBean allowanceBean : unIntegratedList){
				if(allowanceBean.getIsSelected())
					selectedList.add(allowanceBean);
			}
			for(AllowanceDetailBean allowance : selectedList){
				OtherAllowanceDto allowanceDetailDto = new OtherAllowanceDto();
				allowanceDetailDto.setAllowanceDetailBean(allowance);
				allowanceDetailDto = otherAllowanceBao.deleteAllowanceDetail(allowanceDetailDto);
			}
			getUnIntegratedAllowances();
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Delete_Success ,FacesMessage.SEVERITY_INFO);
    	} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
    }

	public Workbook getData() {
		return data;
	}

	public void setData(Workbook data) {
		this.data = data;
	}

	public OtherAllowanceBao getOtherAllowanceBao() {
		return otherAllowanceBao;
	}

	public void setOtherAllowanceBao(OtherAllowanceBao otherAllowanceBao) {
		this.otherAllowanceBao = otherAllowanceBao;
	}

	public FileUploadDto getFileUploadDto() {
		return fileUploadDto;
	}

	public void setFileUploadDto(FileUploadDto fileUploadDto) {
		this.fileUploadDto = fileUploadDto;
	}

	public Boolean getIntegratedAllowances() {
		return integratedAllowances;
	}

	public void setIntegratedAllowances(Boolean integratedAllowances) {
		this.integratedAllowances = integratedAllowances;
	}

	public List<AllowanceDetailBean> getUnIntegratedList() {
		return unIntegratedList;
	}

	public void setUnIntegratedList(List<AllowanceDetailBean> unIntegratedList) {
		this.unIntegratedList = unIntegratedList;
	}

	public Boolean getIsCheckAll() {
		return isCheckAll;
	}

	public void setIsCheckAll(Boolean isCheckAll) {
		this.isCheckAll = isCheckAll;
	}

	public List<AllowanceTypeBean> getAllowanceTypeList() {
		if(allowanceTypeList == null || allowanceTypeList.isEmpty()){
			try {
				allowanceTypeList = otherAllowanceBao.getAllowanceTypes();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return allowanceTypeList;
	}

	public void setAllowanceTypeList(List<AllowanceTypeBean> allowanceTypeList) {
		this.allowanceTypeList = allowanceTypeList;
	}
}