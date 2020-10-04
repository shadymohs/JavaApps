package com.SAPTCO.costAnalysis.dto;

import java.util.List;
import java.util.ArrayList;

import com.SAPTCO.common.ibatis.mapperBeans.BusTypeCostBean;

/**
 * @author alqassemga
 *
 */
public class BusTypeCostDto {
	
	
	private BusTypeCostBean busTypeCost = new BusTypeCostBean();
	private List<BusTypeCostBean> busTypeCostList = new ArrayList<BusTypeCostBean>();

	
	public BusTypeCostBean getBusTypeCost() {
		return busTypeCost;
	}

	public void setBusTypeCost(BusTypeCostBean busTypeCost) {
		this.busTypeCost = busTypeCost;
	}

	public List<BusTypeCostBean> getBusTypeCostList() {
		return busTypeCostList;
	}

	public void setBusTypeCostList(List<BusTypeCostBean> busTypeCostList) {
		this.busTypeCostList = busTypeCostList;
	}

}
