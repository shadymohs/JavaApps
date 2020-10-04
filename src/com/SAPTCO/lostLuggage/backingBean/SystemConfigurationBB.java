package com.SAPTCO.lostLuggage.backingBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.SystemConfiguration;
import com.SAPTCO.lostLuggage.bao.LostLuggageBao;

@ManagedBean(name="systemConfigurationBB")
@ViewScoped
public class SystemConfigurationBB  extends BaseBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Log logger = LogFactory.getLog(getClass());
	private List<SystemConfiguration>  systemConfigurationList = new ArrayList<SystemConfiguration>();
	
	@ManagedProperty("#{lostLuggageBao}")
	private LostLuggageBao lostLuggageBao;
	
	public void showDetail(){
		
	}
	
	public void delete(){
		
	}

	public List<SystemConfiguration> getSystemConfigurationList() {
		try {
			this.systemConfigurationList = lostLuggageBao.getSystemConfigurationList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this.systemConfigurationList ;
	}

	public void setSystemConfigurationList(
			List<SystemConfiguration> systemConfigurationList) {
		this.systemConfigurationList = systemConfigurationList;
	
	}

	public LostLuggageBao getLostLuggageBao() {
		return lostLuggageBao;
	}

	public void setLostLuggageBao(LostLuggageBao lostLuggageBao) {
		this.lostLuggageBao = lostLuggageBao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Log getLogger() {
		return logger;
	}
	
	

}
