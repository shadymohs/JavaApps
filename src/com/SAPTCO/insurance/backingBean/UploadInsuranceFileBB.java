package com.SAPTCO.insurance.backingBean;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.io.FileInputStream;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.event.FileUploadEvent;
import org.richfaces.model.UploadedFile;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;

@ManagedBean(name="uploadInsuranceFileBB")
@ViewScoped
public class UploadInsuranceFileBB extends BaseBB{
	
	private static final long serialVersionUID = 1L;
	
	private Object data = null;
	private final Log logger = LogFactory.getLog(getClass());
	
	private String claimNumber;
	private String branch="";
	private String year;
	
	private InputStream fileInputStream = null;	
	private File uploadedFile = null; 
	
	
		
	//upload image file
	public void listenerImage(FileUploadEvent event) throws Exception {
    	UploadedFile uploadedfile = event.getUploadedFile();
    	setFileInputStream(new ByteArrayInputStream(uploadedfile.getData()));
    	setUploadedFile(null);
    }
	
	
	public void saveFile() {
		if(fileInputStream != null){
			//save uploaded image
			OutputStream outputStream = null;
			try{
				File dir = new File("Insurance");
				File file = new File("Insurance/" + year+  branch + claimNumber + ".pdf");
				logger.error("File crerated");
				if (!dir.exists())
					dir.mkdir();
				outputStream = new FileOutputStream(file);
				int read = 0;
				byte[] bytes = new byte[1024];	
				while ((read = fileInputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}
				
				addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Add_Success);
				
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
			
	   }
	}
	
	
 public void downloadUploadedImage(){
	//retrieve uploaded image
	try{
		File file = new File("Insurance/" + year+  branch + claimNumber + ".pdf");
		
		if(file.exists() && !file.isDirectory())
		{
			uploadedFile = file;
			 //download uploaded file
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
			
	    	}
		}
		
		else
		{
			uploadedFile = null;
			addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					"upload_file_ntExisted");
		}
		
	  }catch(Exception ex){
		ex.printStackTrace();
		logger.error(ex.getMessage());
	  }
	
	
    }
    
    
	 public UploadInsuranceFileBB() {
	    }
	 

/*public int getSize() {
	        if (getFiles().size()>0){
	            return getFiles().size();
	        }else 
	        {
	            return 0;
	        }
	    }




 
 
 public void paint(OutputStream stream, Object object) throws IOException {
	        stream.write(getFiles().get((Integer)object).getData());
	    }
	    
	    
 public void listener(FileUploadEvent event) throws Exception {
	        UploadedFile item = event.getUploadedFile();
	        File file = new File();
	        file.setLength(item.getData().length);
	        file.setName(item.getName());
	        file.setData(item.getData());
	        files.add(file);
	        uploadsAvailable--;
	    }  
	      
 
 public String clearUploadData() {
	        files.clear();
	        setUploadsAvailable(5);
	        return null;
	    }
	    
  public long getTimeStamp(){
	        return System.currentTimeMillis();
	    }
	    
 public ArrayList<File> getFiles() {
	        return files;
	    }

	    public void setFiles(ArrayList<File> files) { 
	        this.files = files;
	    }

	    public int getUploadsAvailable() {
	        return uploadsAvailable;
	    }

	    public void setUploadsAvailable(int uploadsAvailable) {
	        this.uploadsAvailable = uploadsAvailable;
	    }

	    public boolean isAutoUpload() {
	        return autoUpload;
	    }

	    public void setAutoUpload(boolean autoUpload) {
	        this.autoUpload = autoUpload;
	    }

	    public boolean isUseFlash() {
	        return useFlash;
	    }

	    public void setUseFlash(boolean useFlash) {
	        this.useFlash = useFlash;
	    }
	
	
	*/
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Log getLogger() {
		return logger;
	}

	public String getClaimNumber() {
		return claimNumber;
	}

	public void setClaimNumber(String claimNumber) {
		this.claimNumber = claimNumber;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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
