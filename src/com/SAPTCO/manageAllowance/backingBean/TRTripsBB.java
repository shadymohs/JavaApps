package com.SAPTCO.manageAllowance.backingBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.manageAllowance.bao.TRTripsBao;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;

/**
*
* @author Shady
*/

@ManagedBean(name="tRTripsBB")
@ViewScoped
public class TRTripsBB extends BaseBB{
	
	@ManagedProperty("#{tRTripsBao}")
	private TRTripsBao tRTripsBao;
	
	private final Log logger = LogFactory.getLog(getClass());
	private static final long serialVersionUID = 1L;
	private TRTripsDto tRTripsDto = new TRTripsDto();
	private List<FinancialElementBean> financialElList = new ArrayList<FinancialElementBean>();
	private String updator;

	@PostConstruct
	public void init(){
		tRTripsDto.setAllowanceDetailBean(new AllowanceDetailBean());
		tRTripsDto.setDetailsList(new ArrayList<AllowanceDetailBean>());
		tRTripsDto.setIsManageMode(false);
			try
			{
				financialElList =tRTripsBao.getFinancialElementList(BaseBB.getUserDetails().getLoggedInSystem().getId());
				tRTripsDto.setFinancialElementList(financialElList);
			} catch (Exception e) {
				logger.error(e.getMessage());
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
			}
		}
	
	public void search(){
		try {
			updator = null;
			if(tRTripsDto.getTripNumber() != null || (tRTripsDto.getTripCode() != null && tRTripsDto.getTripDate() != null))
				tRTripsDto = tRTripsBao.searchTRTrips(tRTripsDto);
			else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Invalid_Search_Criteria,FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void edit(){
		try{
			tRTripsDto.setIsManageMode(true);
			updator = tRTripsBao.getUpdatorName(tRTripsDto.getAllowanceDetailBean().getId());
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void save(){
		try {
			tRTripsDto = tRTripsBao.saveTRTrips(tRTripsDto);
			if(tRTripsDto.getProcResult() != null && tRTripsDto.getProcResult().equals("Y")){
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
				tRTripsDto.setAllowanceDetailBean(new AllowanceDetailBean());
				tRTripsDto.setDetailsList(new ArrayList<AllowanceDetailBean>());
				tRTripsDto.setIsManageMode(false);
				updator = null;
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,tRTripsDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void sendToHR(){
		try {
			tRTripsDto = tRTripsBao.sendToHR(tRTripsDto);
			if(tRTripsDto.getProcResult() != null && tRTripsDto.getProcResult().equals("Y")){
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Save_Success,FacesMessage.SEVERITY_INFO);
				tRTripsDto.setAllowanceDetailBean(new AllowanceDetailBean());
				tRTripsDto.setDetailsList(new ArrayList<AllowanceDetailBean>());
				tRTripsDto.setIsManageMode(false);
				updator = null;
			}else
				addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,tRTripsDto.getProcResult(),FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			logger.error(e.getMessage());
			addErrorMessageByCode(SystemConstants.Error_RESOURCE_BUNDLE,SystemConstants.Process_Exception,FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void cancel(){
		tRTripsDto.setAllowanceDetailBean(new AllowanceDetailBean());
		tRTripsDto.setDetailsList(new ArrayList<AllowanceDetailBean>());
		tRTripsDto.setIsManageMode(false);
		updator = null;
	}
	
	public TRTripsDto gettRTripsDto() {
		return tRTripsDto;
	}

	public void settRTripsDto(TRTripsDto tRTripsDto) {
		this.tRTripsDto = tRTripsDto;
	}

	public TRTripsBao gettRTripsBao() {
		return tRTripsBao;
	}

	public void settRTripsBao(TRTripsBao tRTripsBao) {
		this.tRTripsBao = tRTripsBao;
	}

	public String getUpdator() {
		return updator;
	}

	public void setUpdator(String updator) {
		this.updator = updator;
	}
	
	/**
	 * @return the financialElList
	 */
	public List<FinancialElementBean> getFinancialElList() {
		return financialElList;
	}

	/**
	 * @param financialElList the financialElList to set
	 */
	public void setFinancialElList(List<FinancialElementBean> financialElList) {
		this.financialElList = financialElList;
	}


}