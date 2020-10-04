package com.SAPTCO.security.backingBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.security.bao.AssetsBao;
import com.SAPTCO.security.dto.AssetsDto;

@ManagedBean(name="assetsBB")
@ViewScoped
public class AssetsBB extends BaseBB{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private AssetsDto assetsDto = new AssetsDto();
	
	@ManagedProperty("#{assetsBao}")
	private AssetsBao assetsBao;
	
	
	public String save(){
		
		try {
			
			assetsBao.insertAsset(assetsDto);
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
		
		return null;
	
	}

	public AssetsDto getAssetsDto() {
		return assetsDto;
	}

	public void setAssetsDto(AssetsDto assetsDto) {
		this.assetsDto = assetsDto;
	}

	public AssetsBao getAssetsBao() {
		return assetsBao;
	}

	public void setAssetsBao(AssetsBao assetsBao) {
		this.assetsBao = assetsBao;
	}

}
