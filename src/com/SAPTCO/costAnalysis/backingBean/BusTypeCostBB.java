package com.SAPTCO.costAnalysis.backingBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.SAPTCO.common.ibatis.mapperBeans.BusTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.BusTypeCostBean;
import com.SAPTCO.costAnalysis.bao.BusTypeCostBao;
import com.SAPTCO.costAnalysis.dto.BusTypeCostDto;

/**
 * @author alqassemga
 *
 */

@ManagedBean(name="busTypeCostBB")
@ViewScoped
public class BusTypeCostBB extends BaseBB {

	
	private static final long serialVersionUID = 1L;
	
	private final Log logger = LogFactory.getLog(getClass());
	@ManagedProperty("#{busTypeCostBao}")
	private BusTypeCostBao busTypeCostBao;
	private BusTypeCostDto busTypeCostDto = new BusTypeCostDto();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private List<BusTypeBean> busTypeList = new ArrayList<BusTypeBean>();
	
	
	@PostConstruct
	public void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("busTypeCostId") != null && !params.get("busTypeCostId").equals(""))
		{  
		    SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
		   busTypeCostDto.getBusTypeCost().setBusTypeCostId(Long.parseLong(params.get("busTypeCostId"))) ; 
			try {
				busTypeCostDto.getBusTypeCost().setStartDate(df.parse(params.get("startDate")) ) ;
				busTypeCostDto.getBusTypeCost().setEndDate(df.parse(params.get("endDate") ));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			busTypeCostDto.getBusTypeCost().setBusTypeCostAmount(Long.parseLong(params.get("busTypeCostAmount")));  
			busTypeCostDto.getBusTypeCost().getBusType().setId(Long.parseLong(params.get("busTypeId")));
			
		}
		
		try {
			busTypeCostDto.setBusTypeCostList(busTypeCostBao.getBusTypeCost());
			busTypeList = busTypeCostBao.getBusTypesList();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addListner() {
		try {
			clearComponent(addEditPanel);
			busTypeCostDto.setBusTypeCost(new BusTypeCostBean()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	

	
	public void editListner() {
		try {
			clearComponent(addEditPanel);
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
			if(busTypeCostDto.getBusTypeCost().getBusTypeCostId() == null || busTypeCostDto.getBusTypeCost().getBusTypeCostId().equals(0L)){
				if (busTypeCostBao.checkOverlappingPeriod(busTypeCostDto)== 0 )
					{//add code
					busTypeCostDto.getBusTypeCost().setCreatedBy(getUserDetails().getId());
					busTypeCostBao.insertBusTypeCost(busTypeCostDto);
					clearComponent(addEditPanel);
					busTypeCostDto.setBusTypeCost(new BusTypeCostBean());
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Save_Success);
					return null;
					}
				else
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"Validate.overlapBusTypeCost");
			}else{
				//edit code
				busTypeCostBao.updateBusTypeCost(busTypeCostDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "busTypeCosts.xhtml";
			}
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		
		}
		return null;
	}
	
	
	
	
	
	
	public BusTypeCostDto getBusTypeCostDto() {
		return busTypeCostDto;
	}

	public void setBusTypeCostDto(BusTypeCostDto BusTypeCostDto) {
		this.busTypeCostDto = BusTypeCostDto;
	}

	public BusTypeCostBao getBusTypeCostsBao() {
		return busTypeCostBao;
	}

	public void setBusTypeCostsBao(BusTypeCostBao BusTypeCostsBao) {
		this.busTypeCostBao = BusTypeCostsBao;
	}

	public BusTypeCostBao getBusTypeCostBao() {
		return busTypeCostBao;
	}

	public void setBusTypeCostBao(BusTypeCostBao busTypeCostBao) {
		this.busTypeCostBao = busTypeCostBao;
	}

	public List<BusTypeBean> getBusTypeList() {
		return busTypeList;
	}

	public void setBusTypeList(List<BusTypeBean> busTypeList) {
		this.busTypeList = busTypeList;
	}


}
