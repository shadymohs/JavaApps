package com.SAPTCO.lostLuggage.backingBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggage;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageClass;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageColor;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageSize;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageStatus;
import com.SAPTCO.common.ibatis.mapperBeans.LuggageComplaint;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;
import com.SAPTCO.lostLuggage.dto.ComplaintDto;


@ManagedBean(name="complaintBB")
@ViewScoped
public class ComplaintBB   extends BaseBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private ComplaintDto complaintDto = new ComplaintDto();
	private List<LostLuggageStatus>   statusList = new  ArrayList<LostLuggageStatus>();
	private List<LostLuggageSize>   sizeList = new  ArrayList<LostLuggageSize>();
	private List<LostLuggageClass>  luggageClassList = new  ArrayList<LostLuggageClass>();
	private List<LostLuggageColor>   colorList = new  ArrayList<LostLuggageColor>();
	private List<StationBean>  stationList = new ArrayList<StationBean>();
	private List<LostLuggage> matchedLostLuggage = new ArrayList<LostLuggage>();
	private LostLuggage linkedLuggage = new LostLuggage();
	private List<LuggageComplaint>  complaintsList = new ArrayList<LuggageComplaint>();
	private  LuggageComplaint  selectedComp = new LuggageComplaint();
	@ManagedProperty("#{lostLuggageBao}")
	private LostLuggageBao lostLuggageBao;
	private Boolean isSavedSuccessfully = false;
	private Boolean linkedSuccessfully = false;
	
	public void searchBySticker() {
		try {
			complaintDto = lostLuggageBao.getCustomerDataBySticker(complaintDto);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
			//e.printStackTrace();
		}
		
	}
	
	 public void saveComplaintData() {
	    	try {//add operation
	    		complaintDto.setOperation("A");
	    		complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
	    		complaintDto.getComplaintObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
	    		complaintDto = lostLuggageBao.manageComplaint(complaintDto);
				
				if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						complaintDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Add_Success);
					
					 isSavedSuccessfully = true;
				}
				
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
				//e.printStackTrace();
			}
	}
	 public void updateComplaintData() {
	    	try {//update operation
	    		complaintDto.setOperation("U");
	    		
	    		complaintDto.setComplaintObj(selectedComp);
	    		complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
	    		complaintDto.getComplaintObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
	    		complaintDto = lostLuggageBao.manageComplaint(complaintDto);
				
				if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						complaintDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Edit_Success);
				}
				
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
				//e.printStackTrace();
			}
	}
	 
	 public void deleteComplaint(){
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("cm") != null)
				complaintDto.setComplaintObj((LuggageComplaint)params.get("cm"));
			
			try {//delete operation
	    		complaintDto.setOperation("D");
	    		//complaintDto.getComplaintObj().setBranch(getUserDetails().getLoggedInBranch().getLabel());
	    		complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
	    		complaintDto.getComplaintObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
	    		
	    		complaintDto = lostLuggageBao.manageComplaint(complaintDto);
				
				if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						complaintDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Delete_Success);
					
					searchAllComplaints();
				}
				
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
				//e.printStackTrace();
			}
	 }
	 
	 public void searchAllComplaints() {
			try {
				
				complaintDto.getComplaintObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
				
				setComplaintsList(lostLuggageBao.searchAllComplaints(complaintDto));
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
		 
	 }
	 public void showRequestsOfRepayment(){
		 
		 complaintDto.getComplaintObj().getCurrentStatus().setId(Long.valueOf(7));
		 try {
			complaintsList = lostLuggageBao.searchAllComplaints(complaintDto);
			
			if (complaintsList.size()==0 )
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.No_data_found);
		} 
		 catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
	 public void getComplaintById() {
			try {
				this.selectedComp = new LuggageComplaint();
				
				if (complaintDto.getComplaintObj().getLuggageComplaintId() != null)
					if (lostLuggageBao.searchAllComplaints(complaintDto).size() >0)
				       this.selectedComp = (lostLuggageBao.searchAllComplaints(complaintDto).get(0));
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
	 } 
	 
	 public void searchComplaintsToRepayment()
	 {
		 try {
			this.complaintsList = lostLuggageBao.getComplaintsToRepayment(complaintDto);
		
		 } catch (Exception e) {
			 logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
	 public void search() {
		 
		 try {
			   if (complaintDto.getComplaintObj().getColor().getId()==null &&
					   complaintDto.getComplaintObj().getSize().getId()==null &&
					   complaintDto.getComplaintObj().getLuggageClass().getId()==null )
			   {
				   complaintDto.setComplaintObj(this.selectedComp);
			   }
			 
			   complaintDto =  lostLuggageBao.searchMatchedLostLuggage( complaintDto );
			   matchedLostLuggage =  complaintDto.getMatchedLostLuggage();
			   
			   if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						complaintDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}
		} 
		 catch (Exception e) {
			 logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
	 public void showComplaint ()
	 {
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("cm") != null)
				selectedComp = ((LuggageComplaint)params.get("cm"));
	 }
	 public void showDetails ()
	 {
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("br") != null)
				linkedLuggage = ((LostLuggage)params.get("br"));
	 }
	 
	 public void linkComplaintToLuggage(){
		 
		 complaintDto.getComplaintObj().setLinkedLuggageId(linkedLuggage.getLostLuggageId());
		 complaintDto.getComplaintObj().setRemarks(linkedLuggage.getRemarks());
		 complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
		 complaintDto.getComplaintObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
	
		 try {
			 
			 complaintDto =  lostLuggageBao.linkComplaintToLuggage(complaintDto);
			 
			 if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					complaintDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Link_Success);
				
				linkedSuccessfully = true;
			}
		} 
		 
		 catch (Exception e) {
			 logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
	 
	 public void removeLink(){
		 try {
	    	complaintDto.setComplaintObj(selectedComp);
    		complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
    		complaintDto.getComplaintObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
    		
    		complaintDto = lostLuggageBao.removeLinkComplaintToLuggage(complaintDto);
			
			if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					complaintDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.removeLink_Success);
			  }
	     
			} 
			 
			 catch (Exception e) {
				 logger.error(e.getMessage());
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
	    	
	    }
	 public void deliverAndClose(){
		 
		 complaintDto.getComplaintObj().setLinkedLuggageId(linkedLuggage.getLostLuggageId());
		 complaintDto.getComplaintObj().setRemarks(linkedLuggage.getRemarks());
		 complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
		 complaintDto.getComplaintObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
	
		 try {
			 
			 complaintDto =  lostLuggageBao.deliverAndCloseComplaint(complaintDto);
			 
			 if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					complaintDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Close_Success);
			}
		} 
		 
		 catch (Exception e) {
			 logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
	 
	 public void repaymentAndClose(){
		 complaintDto.setComplaintObj(this.selectedComp);
		 complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
		 complaintDto.getComplaintObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
	
		 try {
			complaintDto =  lostLuggageBao.repaymentAndClose(complaintDto);
			 if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						complaintDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Close_Success);
					
					
				}
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
	 
	 public void  moveToRepayment(){
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("cm") != null)
				complaintDto.setComplaintObj((LuggageComplaint) params.get("cm"));
			
			 try {
				 
				 complaintDto.getComplaintObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
				 complaintDto.getComplaintObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
				 complaintDto =  lostLuggageBao.moveToRepayment(complaintDto);
				 
				 if(complaintDto.getProcResult() != null && !complaintDto.getProcResult().equals("") && !complaintDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						complaintDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Transformed_Success);
					
					searchComplaintsToRepayment();
				}
			} 
			 catch (Exception e) {
				 logger.error(e.getMessage());
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
	 }
	 
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}


	public List<LostLuggageSize> getSizeList() {
		try {
			sizeList = lostLuggageBao.getLostLuggageSizeList();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return sizeList;
	}


	public void setSizeList(List<LostLuggageSize> sizeList) {
		this.sizeList = sizeList;
	}


	public List<LostLuggageClass> getLuggageClassList() {
		try {
			luggageClassList = lostLuggageBao.getLostLuggageClassList();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return luggageClassList;
	}


	public void setLuggageClassList(List<LostLuggageClass> luggageClassList) {
		this.luggageClassList = luggageClassList;
	}


	public List<LostLuggageColor> getColorList() {
		try {
			colorList = lostLuggageBao.getLostLuggageColorList();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return colorList;
	}


	public void setColorList(List<LostLuggageColor> colorList) {
		this.colorList = colorList;
	}


	public List<StationBean> getStationList() {
		try {
			stationList = lostLuggageBao.getStationList();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return stationList;
	}


	public void setStationList(List<StationBean> stationList) {
		this.stationList = stationList;
	}
	
	public LostLuggageBao getLostLuggageBao() {
		return lostLuggageBao;
	}
	public void setLostLuggageBao(LostLuggageBao lostLuggageBao) {
		this.lostLuggageBao = lostLuggageBao;
	}

	public ComplaintDto getComplaintDto() {
		return complaintDto;
	}

	public void setComplaintDto(ComplaintDto complaintDto) {
		this.complaintDto = complaintDto;
	}

	public List<LostLuggage> getMatchedLostLuggage() {
		return matchedLostLuggage;
	}

	public void setMatchedLostLuggage(List<LostLuggage> matchedLostLuggage) {
		this.matchedLostLuggage = matchedLostLuggage;
	}

	public LostLuggage getLinkedLuggage() {
		return linkedLuggage;
	}

	public void setLinkedLuggage(LostLuggage linkedLuggage) {
		this.linkedLuggage = linkedLuggage;
	}
	
	public int getSize(){
		return matchedLostLuggage.size();
	}
	public List<LuggageComplaint> getComplaintsList() {
		return complaintsList;
	}
	public void setComplaintsList(List<LuggageComplaint> complaintsList) {
		this.complaintsList = complaintsList;
	}
	public LuggageComplaint getselectedComp() {
		return selectedComp;
	}
	public void setselectedComp(LuggageComplaint selectedComp) {
		this.selectedComp = selectedComp;
	}
	
	public List<LostLuggageStatus> getStatusList() {
		List<LostLuggageStatus> compStatusList= new ArrayList<LostLuggageStatus>();
		try {
			statusList = lostLuggageBao.getLostLuggageStatusList();
			int  i = 0;
			while ( i< (statusList.size()) )
			{ if ( statusList.get(i).isShowInComplaint() )
				compStatusList.add(statusList.get(i));
			  i++;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return  compStatusList;
	}
	public void setStatusList(List<LostLuggageStatus> statusList) {
		this.statusList = statusList;
	}

	public Boolean getIsSavedSuccessfully() {
		return isSavedSuccessfully;
	}

	public void setIsSavedSuccessfully(Boolean isSavedSuccessfully) {
		this.isSavedSuccessfully = isSavedSuccessfully;
	}

	public Boolean getLinkedSuccessfully() {
		return linkedSuccessfully;
	}

	public void setLinkedSuccessfully(Boolean linkedSuccessfully) {
		this.linkedSuccessfully = linkedSuccessfully;
	}
	
}
