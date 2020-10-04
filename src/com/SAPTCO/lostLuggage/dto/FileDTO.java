package com.SAPTCO.lostLuggage.dto;

public class FileDTO {

	
	
	private String fileName;
    private String mime;
    private long length;
    private byte[] fileData;
    
    
 
 
    public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	    int extDot = fileName.lastIndexOf('.');
	    if(extDot > 0){
	        String extension = fileName.substring(extDot +1);
	        if("png".equals(extension)){
	            mime="image/jpg";
	        }else {
	            mime = "img/unknown";
	        }
	    }
		
	}
	public byte[] getFileData() {
		return fileData;
	}
	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	public long getLength() {
        return length;
    }
    public void setLength(long length) {
        this.length = length;
    }

    public String getMime(){
        return mime;
    }
    
}
