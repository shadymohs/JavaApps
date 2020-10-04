package com.SAPTCO.lostLuggage.backingBean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggage;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageClass;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageColor;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageSize;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageStatus;
import com.SAPTCO.common.ibatis.mapperBeans.LuggageComplaint;
import com.SAPTCO.common.ibatis.mapperBeans.StationBean;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;
import com.SAPTCO.lostLuggage.dto.LostLuggageDto;
import com.SAPTCO.security.bao.BranchesBao;
import com.SAPTCO.security.dto.BranchesDto;

@ManagedBean(name="lostLuggageBB")
@ViewScoped
public class LostLuggageBB  extends BaseBB{

	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	
	private LostLuggageDto lostLuggageDto = new LostLuggageDto();
	private List<LostLuggageStatus>   statusList = new  ArrayList<LostLuggageStatus>();
	private List<LostLuggageSize>   sizeList = new  ArrayList<LostLuggageSize>();
	private List<LostLuggageClass>  luggageClassList = new  ArrayList<LostLuggageClass>();
	private List<LostLuggageColor>   colorList = new  ArrayList<LostLuggageColor>();
	private List<StationBean>  stationList = new ArrayList<StationBean>();
	private List<LuggageComplaint>  matchedComplaints = new ArrayList<LuggageComplaint>();
	private LuggageComplaint  linkedComplaint = new LuggageComplaint();
	private List<LostLuggage>  luggageList = new ArrayList<LostLuggage>();
	private LostLuggage selectedLuggage = new LostLuggage();
	private BranchesDto branchesDto = new BranchesDto();
	private List<BranchesBean> branchesList = new ArrayList<BranchesBean>();
	private Boolean isSavedSuccessfully = false;
	private InputStream fileInputStream = null;	
	private File uploadedFile = null; 
	
	@ManagedProperty("#{lostLuggageBao}")
	private LostLuggageBao lostLuggageBao;
	@ManagedProperty("#{branchesBao}")
	private BranchesBao branchesBao;
		
	//upload image file
	public void listenerImage(FileUploadEvent event) throws Exception {
    	UploadedFile uploadedfile = event.getUploadedFile();
    	fileInputStream = new ByteArrayInputStream(uploadedfile.getData());
    	uploadedFile = null;
    }
	
	public void searchBySticker() {
		try {
			LostLuggage lostLuggageObj = new LostLuggage();
			lostLuggageDto.setLostLuggageObj(lostLuggageObj);
			lostLuggageDto = lostLuggageBao.getLuggageDataBySticker(lostLuggageDto);
			if (lostLuggageDto.getLostLuggageObj().getReservationNumber()== null)
					{
				    addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.No_data_found);
					}
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		
			//e.printStackTrace();
		}
		
	}
	public void searchByFeature() {
		
		try {
			 if (lostLuggageDto.getLostLuggageObj().getColor().getId()==null && 
					 lostLuggageDto.getLostLuggageObj().getSize().getId()==null)
			 {
				  lostLuggageDto.setLostLuggageObj(this.selectedLuggage);
			 }
			matchedComplaints = lostLuggageBao.searchMatchedComplaints(lostLuggageDto).getMatchedComplaintsList();
			
			if (matchedComplaints.size()==0)
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.No_data_found);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		
			//e.printStackTrace();
		}
		
	}
	
    public void saveLuggageData() {
    	try {//add operation
    		uploadedFile = null;
    		lostLuggageDto.setOperation("A");
    		lostLuggageDto.getLostLuggageObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
    		lostLuggageDto.getLostLuggageObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
			lostLuggageBao.manageLostLuggage(lostLuggageDto);			
			if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					lostLuggageDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
				if(fileInputStream != null){
					//save uploaded image
					OutputStream outputStream = null;
					try{
						File dir = new File("/u02/lostluggage");
						File file = new File("/u02/lostluggage/" + lostLuggageDto.getLostLuggageObj().getLostLuggageId() + ".pdf");
						logger.error("File crerated");
						if (!dir.exists())
							dir.mkdir();
						outputStream = new FileOutputStream(file);
						int read = 0;
						byte[] bytes = new byte[1024];	
						while ((read = fileInputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
					}catch(Exception ex){
						addMessage(
								SystemConstants.Error_RESOURCE_BUNDLE,
								"upload_failed");
						logger.error(ex.getMessage());
					}finally {
						try{
							if (fileInputStream != null) {
								try {
									fileInputStream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if (outputStream != null) {
								try {
									outputStream.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
	
							}
						}catch(Exception e){
							e.printStackTrace();
					}	}
					fileInputStream = null;
					
					//retrieve uploaded image
					try{
						File file = new File("/u02/lostluggage/" + lostLuggageDto.getLostLuggageObj().getLostLuggageId() + ".pdf");
						if(file.exists() && !file.isDirectory())
							uploadedFile = file;
						else
							uploadedFile = null;
					}catch(Exception ex){
						ex.printStackTrace();
						logger.error(ex.getMessage());
					}
				}
				this.setIsSavedSuccessfully(true);  isSavedSuccessfully = true;
			}			
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
    
    //download uploaded file
    public void downloadUploadedImage(){
    	try{
    		if(uploadedFile != null){
    			FacesContext fctx = FacesContext.getCurrentInstance();
    	        HttpServletResponse response = (HttpServletResponse) fctx.getExternalContext().getResponse();
    	        response.reset();
    	        response.setContentType("application/pdf");
    	        response.addHeader("Content-Disposition", "attachment; filename=" + uploadedFile.getName());
    	        //response.setContentLength((int) uploadedFile.length());
    	        OutputStream out = response.getOutputStream();
    	        try {  
    	            FileInputStream input = new FileInputStream(uploadedFile);
    	            byte[] buffer = new byte[1024]; 
    	            int i = 0;  
    	            while ((i = input.read(buffer)) != -1) {  
    	                out.write(buffer);  
    	                out.flush();  
    	            }
    	            out.flush();
    	            out.close();
    	            fctx.responseComplete(); 
    	        } catch (Exception ex) {  
    	            ex.printStackTrace();
    	            logger.error(ex.getMessage());
    	        }  	        
    		}
    	}catch(Exception ex){
    		logger.error(ex.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		
    		//ex.printStackTrace();
    	}
    }
    
    public void downloadImageOfSelected(){
    	try{
    		File file = new File("/u02/lostluggage/" + selectedLuggage.getLostLuggageId() + ".pdf");
			if(file.exists() && !file.isDirectory())
				uploadedFile = file;
			else
				uploadedFile = null;
			
    		if(uploadedFile != null){
    			FacesContext fctx = FacesContext.getCurrentInstance();
    	        HttpServletResponse response = (HttpServletResponse) fctx.getExternalContext().getResponse();
    	        response.reset();
    	        response.setContentType("application/pdf");
    	        response.addHeader("Content-Disposition", "attachment; filename=" + uploadedFile.getName());
    	        //response.setContentLength((int) uploadedFile.length());
    	        OutputStream out = response.getOutputStream();
    	        try {  
    	            FileInputStream input = new FileInputStream(uploadedFile);
    	            byte[] buffer = new byte[1024]; 
    	            int i = 0;  
    	            while ((i = input.read(buffer)) != -1) {  
    	                out.write(buffer);  
    	                out.flush();  
    	            }
    	            out.flush();
    	            out.close();
    	            fctx.responseComplete(); 
    	        } catch (Exception ex) {  
    	            ex.printStackTrace();
    	            logger.error(ex.getMessage());
    	        }  	        
    		}
    		else
    			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,"noUploaded_image",FacesMessage.SEVERITY_ERROR);
    		
    	}catch(Exception ex){
    		logger.error(ex.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		
    		//ex.printStackTrace();
    	}
    }
		
    public void updateLuggage(){
    	try {//add operation
    		lostLuggageDto.setOperation("U");
    		//lostLuggageDto.getLostLuggageObj().setBranch(getUserDetails().getLoggedInBranch().getLabel());
    		lostLuggageDto.getLostLuggageObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
    		lostLuggageDto.getLostLuggageObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
			
    		lostLuggageDto.setLostLuggageObj(selectedLuggage);
			lostLuggageBao.manageLostLuggage(lostLuggageDto);
			
			if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					lostLuggageDto.getProcResult(),
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
    
    public void deleteLuggage(){
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("lug") != null)
				lostLuggageDto.setLostLuggageObj((LostLuggage)params.get("lug"));
			
			try {//delete operation
				lostLuggageDto.setOperation("D");
				lostLuggageDto.getLostLuggageObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
	    		lostLuggageDto.getLostLuggageObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
				
				//lostLuggageDto.getLostLuggageObj().setBranch(getUserDetails().getLoggedInBranch().getLabel());
	    		
	    		 lostLuggageBao.manageLostLuggage(lostLuggageDto);
				
				if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						lostLuggageDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Delete_Success);
					
					//searchAllLuggage();
				}
				
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
			
				//e.printStackTrace();
			}
	 }
    
    public void  finishDeliverLuggage(){
    	try {
    		selectedLuggage.setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
    		selectedLuggage.setBranchId(getUserDetails().getLoggedInBranch().getId());
    		lostLuggageDto.setLostLuggageObj(selectedLuggage);
    		
			lostLuggageBao.finishDeliverLuggage(lostLuggageDto);
			
			if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					lostLuggageDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Deliver_Success);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		
			//e.printStackTrace();
		}
    }
    
    public void searchAllLuggage() {
		try {
			lostLuggageDto.getLostLuggageObj().setCreatedBy(getUserDetails().getUserInf().getUserFullName());
			this.luggageList = (lostLuggageBao.searchAllLuggage(lostLuggageDto));
			if (luggageList.size()==0 )
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.No_data_found);
					
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 
   }
    
    public void searchLuggageToDeliver(){
		try {
			
			this.luggageList = (lostLuggageBao.searchLuggageToDeliver(lostLuggageDto));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 
   }
    
    public void searchLuggageToStore(){
		try {
			
			this.luggageList = (lostLuggageBao.getLuggageToStore(lostLuggageDto));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 
   }
    
    public void searchLuggageToScrap(){
		try {
			
			this.luggageList = (lostLuggageBao.getLuggageToScrap(lostLuggageDto));
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 
   }
    
    public void showLuggage()
	 {
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("lug") != null)
				selectedLuggage = ((LostLuggage)params.get("lug"));
	 }
    
    public void showDetails ()
	 {
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("br") != null)
				linkedComplaint = ((LuggageComplaint)params.get("br"));
	 }
    public void linkLuggageToComplaint(){
		 
    	lostLuggageDto.getLostLuggageObj().setLinkedComplaintId(linkedComplaint.getLuggageComplaintId());
    	lostLuggageDto.getLostLuggageObj().setRemarks(linkedComplaint.getRemarks());
    	lostLuggageDto.getLostLuggageObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
		lostLuggageDto.getLostLuggageObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
		
	
		 try {
			 
			 lostLuggageDto =  lostLuggageBao.linkLuggageToComplaint(lostLuggageDto);
			 
			 if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					lostLuggageDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Link_Success);
			}
		} 
		 
		 catch (Exception e) {
			 logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
	 }
    
    public void removeLink(){
    	lostLuggageDto.setLostLuggageObj(selectedLuggage);
    	lostLuggageDto.getLostLuggageObj().setBranchId(getUserDetails().getLoggedInBranch().getId());
		lostLuggageDto.getLostLuggageObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
		
     try {
			 
			 lostLuggageDto =  lostLuggageBao.removeLinkLuggageToComplaint(lostLuggageDto);
			 
			 if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					lostLuggageDto.getProcResult(),
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
    public void  calcStoreFees(){
   	
				this.selectedLuggage.setStoreFee(0F);
		
   }
    
    public void  moveToMainStorePre(){
    	 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("lug") != null)
				selectedLuggage = ((LostLuggage)params.get("lug"));
		
    }
    public void moveToMainStore(){
    	try {
    		
    		selectedLuggage.setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
    		selectedLuggage.getCurrentStatus().setId((long) 2);
    		lostLuggageDto.setLostLuggageObj(selectedLuggage);
    		lostLuggageDto= lostLuggageBao.moveToMainStore(lostLuggageDto);
			
			if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
				addErrorMessageByCode(
					SystemConstants.Error_RESOURCE_BUNDLE,
					lostLuggageDto.getProcResult(),
					FacesMessage.SEVERITY_ERROR);
			}else{
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Edit_Success);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

		}
    }
    
    public void  transformToScrapPre(){
   	 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("lug") != null)
				selectedLuggage = ((LostLuggage)params.get("lug"));
		
   }
    
    public void  transformToScrap(){
		
			
			 try {
				 selectedLuggage.getCurrentStatus().setId((long) 4);
				 selectedLuggage.setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
				 lostLuggageDto.setLostLuggageObj(selectedLuggage);
				 lostLuggageDto =  lostLuggageBao.transformToScrap(lostLuggageDto);
				 
				 if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						lostLuggageDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Transformed_Success);
					//searchLuggageToScrap();
				}
			} 
			 catch (Exception e) {
				 logger.error(e.getMessage());
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
    }
    
    public void  transformToAuction(){
		 FacesContext fc = FacesContext.getCurrentInstance();
			Map<String,Object> params = fc.getExternalContext().getRequestMap();
			if(params.get("lug") != null)
				lostLuggageDto.setLostLuggageObj((LostLuggage)params.get("lug"));
			try {
				 lostLuggageDto.getLostLuggageObj().setUpdatedBy(getUserDetails().getUserInf().getUserFullName());
				 lostLuggageDto.getLostLuggageObj().getCurrentStatus().setId((long) 10);
				 lostLuggageDto =  lostLuggageBao.transformToAuction(lostLuggageDto);
				 
				 if(lostLuggageDto.getProcResult() != null && !lostLuggageDto.getProcResult().equals("") && !lostLuggageDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						lostLuggageDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else{
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Transformed_Success);
					//searchLuggageToScrap();
				}
			} 
			 catch (Exception e) {
				 logger.error(e.getMessage());
					addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);

			}
    }
    
    public void listener(FileUploadEvent event) throws Exception {
    	UploadedFile uploadedfile = event.getUploadedFile();
    	InputStream fileInputStream = new ByteArrayInputStream(uploadedfile.getData());
    	
    	 byte[] buffer = new byte[fileInputStream.available()];
    	 fileInputStream.read(buffer);
		   Long luggageId = lostLuggageDto.getLostLuggageObj().getLostLuggageId();
    	   String imageName = "lug"+luggageId.toString();
		   File targetFile = new File("C:/temp/lostLuggageImages/"+imageName+".jpg");
		   
		    OutputStream outStream = new FileOutputStream(targetFile);
		    outStream.write(buffer);
    }
    
    public void whenConvertingToFile_thenCorrect() 
    		  throws IOException {
    		  
    		    InputStream initialStream = new FileInputStream(new File("src/main/resources/sample.txt"));
    		    byte[] buffer = new byte[initialStream.available()];
    		    initialStream.read(buffer);
    		 
    		    File targetFile = new File("src/main/resources/targetFile.tmp");
    		    OutputStream outStream = new FileOutputStream(targetFile);
    		    outStream.write(buffer);
    		    ////
//    		    File imgFile = new File("d:/image.bmp");
//    		    BufferedImage image = ImageIO.read(imgFile);

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
	public List<LuggageComplaint> getMatchedComplaints() {
		return matchedComplaints;
	}
	public void setMatchedComplaints(List<LuggageComplaint> matchedComplaints) {
		this.matchedComplaints = matchedComplaints;
	}
	public LostLuggageDto getLostLuggageDto() {
		return lostLuggageDto;
	}
	public void setLostLuggageDto(LostLuggageDto lostLuggageDto) {
		this.lostLuggageDto = lostLuggageDto;
	}
	public LostLuggageBao getLostLuggageBao() {
		return lostLuggageBao;
	}
	public void setLostLuggageBao(LostLuggageBao lostLuggageBao) {
		this.lostLuggageBao = lostLuggageBao;
	}
	public LuggageComplaint getLinkedComplaint() {
		return linkedComplaint;
	}
	public void setLinkedComplaint(LuggageComplaint linkedComplaint) {
		this.linkedComplaint = linkedComplaint;
	}
	public List<LostLuggage> getLuggageList() {
		return luggageList;
	}
	public void setLuggageList(List<LostLuggage> luggageList) {
		this.luggageList = luggageList;
	}
	public LostLuggage getSelectedLuggage() {
		return selectedLuggage;
	}
	public void setSelectedLuggage(LostLuggage selectedLuggage) {
		this.selectedLuggage = selectedLuggage;
	}
	
	public List<LostLuggageStatus> getStatusList() {
		List<LostLuggageStatus> lugStatusList= new ArrayList<LostLuggageStatus>();
		try {
			statusList = lostLuggageBao.getLostLuggageStatusList();
			int  i = 0;
			while ( i< (statusList.size()) )
			{ if ( statusList.get(i).isShowInLuggage() )
				lugStatusList.add(statusList.get(i));
			  i++;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return  lugStatusList;
	}
	public void setStatusList(List<LostLuggageStatus> statusList) {
		this.statusList = statusList;
	}
	public BranchesBao getBranchesBao() {
		return branchesBao;
	}
	public void setBranchesBao(BranchesBao branchesBao) {
		this.branchesBao = branchesBao;
	}
	public BranchesDto getBranchesDto() {
		return branchesDto;
	}
	public void setBranchesDto(BranchesDto branchesDto) {
		this.branchesDto = branchesDto;
	}
	public List<BranchesBean> getBranchesList() {
		
		try {
			branchesDto.setSystemId("10");
			branchesBao.getBranchesByCode(branchesDto);
			branchesList = branchesDto.getBranchesList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchesList;
	}
	public void setBranchesList(List<BranchesBean> branchesList) {
		this.branchesList = branchesList;
	}
	public Boolean getIsSavedSuccessfully() {
		return isSavedSuccessfully;
	}
	public void setIsSavedSuccessfully(Boolean isSavedSuccessfully) {
		this.isSavedSuccessfully = isSavedSuccessfully;
	}

	public InputStream getFileInputStream() {
		return fileInputStream;
	}

	public void setFileInputStream(InputStream fileInputStream) {
		this.fileInputStream = fileInputStream;
	}

	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	
	

}
