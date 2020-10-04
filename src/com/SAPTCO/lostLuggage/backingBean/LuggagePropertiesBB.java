package com.SAPTCO.lostLuggage.backingBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.GeneralDto;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageClass;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageColor;
import com.SAPTCO.common.ibatis.mapperBeans.LostLuggageSize;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;

@ManagedBean(name="luggagePropertiesBB")
@ViewScoped
public class LuggagePropertiesBB  extends BaseBB{
	
	
	
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private GeneralDto propertyDto = new GeneralDto();
	private Long propertyType;
	
	private List<LostLuggageSize>   sizeList = new  ArrayList<LostLuggageSize>();
	private List<LostLuggageClass>  luggageClassList = new  ArrayList<LostLuggageClass>();
	private List<LostLuggageColor>   colorList = new  ArrayList<LostLuggageColor>();
	
	
	
	@ManagedProperty("#{lostLuggageBao}")
	private LostLuggageBao lostLuggageBao;
	
	
	
	public void save(){
	  try {
		if (propertyType != null )
		{
				propertyDto = lostLuggageBao.manageLuggageProperties(propertyDto, propertyType.toString(), "A");
				
				if(propertyDto.getProcResult() != null && !propertyDto.getProcResult().equals("") && !propertyDto.getProcResult().equals("Y")){
					addErrorMessageByCode(
						SystemConstants.Error_RESOURCE_BUNDLE,
						propertyDto.getProcResult(),
						FacesMessage.SEVERITY_ERROR);
				}else
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							SystemConstants.Add_Success);
				
		}
		
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void update(){
		  try {
			if (propertyType != null )
			{
					propertyDto = lostLuggageBao.manageLuggageProperties(propertyDto, propertyType.toString(), "U");
					
					if(propertyDto.getProcResult() != null && !propertyDto.getProcResult().equals("") && !propertyDto.getProcResult().equals("Y")){
						addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							propertyDto.getProcResult(),
							FacesMessage.SEVERITY_ERROR);
					}else
						addMessage(
								SystemConstants.Error_RESOURCE_BUNDLE,
								SystemConstants.Edit_Success);
					
			}
			
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
			}
		}
		
	public void delete(){
		  try {
			if (propertyType != null )
			{
					propertyDto = lostLuggageBao.manageLuggageProperties(propertyDto, propertyType.toString(), "D");
					
				if(propertyDto.getProcResult() != null && !propertyDto.getProcResult().equals("") && !propertyDto.getProcResult().equals("Y")){
						addErrorMessageByCode(
							SystemConstants.Error_RESOURCE_BUNDLE,
							propertyDto.getProcResult(),
							FacesMessage.SEVERITY_ERROR);
					}else
						addMessage(
								SystemConstants.Error_RESOURCE_BUNDLE,
								SystemConstants.Delete_Success);
					
			}
			
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
			}
		}
	
	public Log getLogger() {
		return logger;
	}



	public LostLuggageBao getLostLuggageBao() {
		return lostLuggageBao;
	}



	public void setLostLuggageBao(LostLuggageBao lostLuggageBao) {
		this.lostLuggageBao = lostLuggageBao;
	}



	public GeneralDto getPropertyDto() {
		return propertyDto;
	}



	public void setPropertyDto(GeneralDto propertyDto) {
		this.propertyDto = propertyDto;
	}



	public Long getPropertyType() {
		return propertyType;
	}



	public void setPropertyType(Long propertyType) {
		this.propertyType = propertyType;
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
	

	

}
