package com.SAPTCO.costAnalysis.dto;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.DailySDPTargetBean;

/**
 * @author alqassemga
 *
 */
public class DailySDPTargetDto {
	
	private DailySDPTargetBean  dailySDPTargetBean = new DailySDPTargetBean();
	
	private List<DailySDPTargetBean> dailySDPTargetList = new ArrayList<DailySDPTargetBean>();

	public DailySDPTargetBean getDailySDPTargetBean() {
		return dailySDPTargetBean;
	}

	public void setDailySDPTargetBean(DailySDPTargetBean dailySDPTargetBean) {
		this.dailySDPTargetBean = dailySDPTargetBean;
	}

	public List<DailySDPTargetBean> getDailySDPTargetList() {
		return dailySDPTargetList;
	}

	public void setDailySDPTargetList(List<DailySDPTargetBean> dailySDPTargetList) {
		this.dailySDPTargetList = dailySDPTargetList;
	}
	
	

}
