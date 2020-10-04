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
import com.SAPTCO.common.ibatis.mapperBeans.DailySDPTargetBean;
import com.SAPTCO.common.ibatis.mapperBeans.SDPBean;
import com.SAPTCO.costAnalysis.bao.BusTypeCostBao;
import com.SAPTCO.costAnalysis.dto.DailySDPTargetDto;

/**
 * @author alqassemga
 *
 */

@ManagedBean(name="dailySDPTargetBB")
@ViewScoped
public class DailySDPTargetBB extends BaseBB {

	
	private static final long serialVersionUID = 1L;
	
	private final Log logger = LogFactory.getLog(getClass());
	@ManagedProperty("#{busTypeCostBao}")
	private BusTypeCostBao busTypeCostBao;
	private DailySDPTargetDto dailySDPTargetDto = new DailySDPTargetDto();
	private HtmlPanelGrid addEditPanel = new HtmlPanelGrid();
	private List<SDPBean> sdpList = new ArrayList<SDPBean>();
	
	
	@PostConstruct
	public void init(){
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
		if(params.get("targetId") != null && !params.get("targetId").equals(""))
		{  
		    SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
		   dailySDPTargetDto.getDailySDPTargetBean().setTargetId(Long.parseLong(params.get("targetId"))) ; 
			try {
				dailySDPTargetDto.getDailySDPTargetBean().setTargetDate(df.parse(params.get("targetDate")) ) ;
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dailySDPTargetDto.getDailySDPTargetBean().setMainSDPAmount(Long.parseLong(params.get("mainSDPAmount")));  
			dailySDPTargetDto.getDailySDPTargetBean().setAgentsAmount(Long.parseLong(params.get("agentsAmount")));  
			dailySDPTargetDto.getDailySDPTargetBean().getMainSDP().setId(Long.parseLong(params.get("mainSDPId")));
			
		}
		
		try {
			dailySDPTargetDto.setDailySDPTargetList(busTypeCostBao.getDailySDPTarget()); 
			setSdpList(busTypeCostBao.getSDPList());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addListner() {
		try {
			clearComponent(addEditPanel);
			dailySDPTargetDto.setDailySDPTargetBean(new DailySDPTargetBean()); 
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
			if(dailySDPTargetDto.getDailySDPTargetBean().getTargetId() == null || dailySDPTargetDto.getDailySDPTargetBean().getTargetId().equals(0L)){
				if (busTypeCostBao.checkUniqueSDPTargetDate(dailySDPTargetDto)== 0 )
				{//add code
			
					dailySDPTargetDto.getDailySDPTargetBean().setCreatedBy(getUserDetails().getId());
					busTypeCostBao.insertDailySDPTarget(dailySDPTargetDto);
					clearComponent(addEditPanel);
					dailySDPTargetDto.setDailySDPTargetBean(new DailySDPTargetBean());
					addMessage(
						SystemConstants.Error_RESOURCE_BUNDLE,
						SystemConstants.Save_Success);
					return null;
				}
				else
					addMessage(
							SystemConstants.Error_RESOURCE_BUNDLE,
							"Validate.checkUniqueSDPTargetDate");
					
			}else{
				//edit code
				busTypeCostBao.updateDailySDPTarget(dailySDPTargetDto);
				addMessage(
					SystemConstants.Error_RESOURCE_BUNDLE,
					SystemConstants.Save_Success);
				return "dailySdpTarget.xhtml";
			}
		}catch (Exception e) {
			addErrorMessageByCode(
				SystemConstants.Error_RESOURCE_BUNDLE,
				SystemConstants.Process_Exception,
				FacesMessage.SEVERITY_ERROR);
		
		}
		return null;
	}
	
	
	
	
	
	
	public DailySDPTargetDto getdailySDPTargetDto() {
		return dailySDPTargetDto;
	}

	public void setdailySDPTargetDto(DailySDPTargetDto dailySDPTargetDto) {
		this.dailySDPTargetDto = dailySDPTargetDto;
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

	public List<SDPBean> getSdpList() {
		return sdpList;
	}

	public void setSdpList(List<SDPBean> sdpList) {
		this.sdpList = sdpList;
	}

	


}
