package com.SAPTCO.reports.backingBean;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.SAPTCO.reports.backingBean.ReportsBB;
import com.SAPTCO.common.config.ReportInfo;

public class tripSheet extends ReportsBB{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*@Override
	protected ReportInfo getReportInfo() {
		return null;
	}

	@Override
	protected Map<String, Object> getReportParameterMap(Map<String, Object> map)
			throws Exception {
		return null;
	}
*/
	@SuppressWarnings("rawtypes")
	@Override
	protected List getServiceResult() {
		
		return null;
	}
	
	
	@Override
	protected ReportInfo getReportInfo() {
		return ReportInfo.Trip_sheet;
	}
	
	@Override
	protected Map<String, Object> getReportParameterMap(Map<String,Object> map) throws Exception {
 		
		//map.put("allowanceDetailId", allowanceDetailDto.getAllowanceDetail().getId());
	
		return map;
	}


	@Override
	protected Connection getConnection() throws Exception {
		return connectionDA();
	}
	
}