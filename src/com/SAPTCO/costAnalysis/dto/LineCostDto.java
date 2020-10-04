package com.SAPTCO.costAnalysis.dto;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.LineCostBean;



 /**
 * @author alqassemga
 *
 */
public class LineCostDto extends BaseDto{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LineCostBean lineCostBean = new LineCostBean();
	private LineCostBean lineCostDetailBean = new LineCostBean();
	
	private List<LineCostBean> lineCostList = new ArrayList<LineCostBean>();
	private List<LineCostBean> lineCostDetailList = new ArrayList<LineCostBean>();
	
	public LineCostBean getLineCostBean() {
		return lineCostBean;
	}
	public void setLineCostBean(LineCostBean lineCostBean) {
		this.lineCostBean = lineCostBean;
	}
	public List<LineCostBean> getLineCostList() {
		return lineCostList;
	}
	public void setLineCostList(List<LineCostBean> lineCostList) {
		this.lineCostList = lineCostList;
	}
	public List<LineCostBean> getLineCostDetailList() {
		return lineCostDetailList;
	}
	public void setLineCostDetailList(List<LineCostBean> lineCostDetailList) {
		this.lineCostDetailList = lineCostDetailList;
	}
	public LineCostBean getLineCostDetailBean() {
		return lineCostDetailBean;
	}
	public void setLineCostDetailBean(LineCostBean lineCostDetailBean) {
		this.lineCostDetailBean = lineCostDetailBean;
	}
	
	
}
