package com.SAPTCO.costAnalysis.backingBean;


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
import com.SAPTCO.common.ibatis.mapperBeans.LineBean;
import com.SAPTCO.common.ibatis.mapperBeans.LineCostBean;
import com.SAPTCO.costAnalysis.bao.BusTypeCostBao;
import com.SAPTCO.costAnalysis.dto.LineCostDto;

/**
 * @author alqassemga
 *
 */
@ManagedBean(name="lineCostBB")
@ViewScoped
public class LineCostBB extends BaseBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Log logger = LogFactory.getLog(getClass());
	@ManagedProperty("#{busTypeCostBao}")
	private BusTypeCostBao busTypeCostBao;
	private LineCostDto lineCostDto = new LineCostDto();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private List<LineBean> lineList = new ArrayList<LineBean>();
	
	
	
	@PostConstruct
	public void init(){
		lineCostDto.setIsManageMode(false);
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("lineCostId") != null && !params.get("lineCostId").equals("")){   
		    SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
		    lineCostDto.getLineCostBean().setLineCostId(Long.parseLong(params.get("lineCostId"))) ; 
		    lineCostDto.getLineCostDetailBean().setLineCostId(Long.parseLong(params.get("lineCostId"))) ;
			try {
				lineCostDto.getLineCostBean().setStartDate(df.parse(params.get("startDate")) ) ;
				lineCostDto.getLineCostBean().setEndDate(df.parse(params.get("endDate") ));
				lineCostDto.setLineCostDetailList(busTypeCostBao.getLineCostDetailList(Long.parseLong(params.get("lineCostId"))));
			} catch (Exception e) {
				e.printStackTrace();
			}
			lineCostDto.getLineCostBean().getLine().setId(Long.parseLong(params.get("lineId")));
			lineCostDto.getLineCostBean().setPerHourCost(Float.parseFloat(params.get("perHourCost")));  
			lineCostDto.getLineCostBean().setPerKMCost(Float.parseFloat(params.get("perKMCost")));  
			
		}
		try {
			lineCostDto.setLineCostList(busTypeCostBao.getLineCostList());
			setLineList(busTypeCostBao.getLinesList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addListner() {
		try {
			clearComponent(addEditPanel);
			//lineCostDto.setIsManageMode(true);
			lineCostDto.setLineCostBean(new LineCostBean()); 
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
			lineCostDto.setLineCostDetailList(busTypeCostBao.getLineCostDetailList(lineCostDto.getLineCostBean().getLineCostId())); 
			clearComponent(addEditPanel);
			//lineCostDto.setIsManageMode(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	public void addListnerDetail() {
		try {
			//clearComponent(addEditPanel);
			lineCostDto.setIsManageMode(true);
			lineCostDto.setLineCostDetailBean(new LineCostBean()); 
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		}
	}
	
	
	public void editListnerDetail() {
		try {
			//clearComponent(addEditPanel);
			lineCostDto.setIsManageMode(true);
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
			if(lineCostDto.getLineCostBean().getLineCostId() == null || lineCostDto.getLineCostBean().getLineCostId().equals(0L)){
				if (busTypeCostBao.checkLineCostOverlappPeriod(lineCostDto)== 0 )
					{//add code
					lineCostDto.getLineCostBean().setCreatedBy(getUserDetails().getId());
					busTypeCostBao.insertLineCost(lineCostDto);
					clearComponent(addEditPanel);
					lineCostDto.setLineCostBean(new LineCostBean());
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Save_Success);
					return null;
					}
				else
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"Validate.overlapLineCost");
			}else{
				//edit code
				busTypeCostBao.updateLineCost(lineCostDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "lineCost.xhtml";
			}
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		
		}
		return null;
	}
	
	public String saveDetailListner(){
		try{
			if(lineCostDto.getLineCostDetailBean().getLineCostDtlId() == null || lineCostDto.getLineCostDetailBean().getLineCostDtlId().equals(0L)){
				if (busTypeCostBao.checkLineCostDetailOverlappPeriod(lineCostDto)== 0 )
					{//add code
					lineCostDto.getLineCostDetailBean().setLineCostId(lineCostDto.getLineCostBean().getLineCostId()) ;
					
					lineCostDto.getLineCostDetailBean().setCreatedBy(getUserDetails().getId());
					busTypeCostBao.insertLineCostDetail(lineCostDto);
					clearComponent(addEditPanel);
					lineCostDto.setLineCostDetailBean(new LineCostBean());
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Save_Success);
					return null;
					}
				else
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"Validate.overlapTripCodeCost");
			}else{
				//edit code
				busTypeCostBao.updateLineCostDetail(lineCostDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return null;
				//return "manageLineCost.xhtml";
			}
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		
		}
		return null;
	}

	public BusTypeCostBao getBusTypeCostBao() {
		return busTypeCostBao;
	}

	public void setBusTypeCostBao(BusTypeCostBao busTypeCostBao) {
		this.busTypeCostBao = busTypeCostBao;
	}

	public LineCostDto getLineCostDto() {
		return lineCostDto;
	}

	public void setLineCostDto(LineCostDto lineCostDto) {
		this.lineCostDto = lineCostDto;
	}

	public HtmlPanelGrid getAddEditPanel() {
		return addEditPanel;
	}

	public void setAddEditPanel(HtmlPanelGrid addEditPanel) {
		this.addEditPanel = addEditPanel;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}

	public List<LineBean> getLineList() {
		return lineList;
	}

	public void setLineList(List<LineBean> lineList) {
		this.lineList = lineList;
	}
		
	
}
