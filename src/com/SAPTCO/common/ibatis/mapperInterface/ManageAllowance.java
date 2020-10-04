package com.SAPTCO.common.ibatis.mapperInterface;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
import com.SAPTCO.manageAllowance.dto.AllowanceTypeDto;
import com.SAPTCO.manageAllowance.dto.NewAllowanceDto;
import com.SAPTCO.manageAllowance.dto.OtherAllowanceDto;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;
import org.apache.ibatis.annotations.Select;

/**
 * @author Shady
 */

public interface ManageAllowance {
	
	//TR trips
	public TRTripsDto searchTRTrips(TRTripsDto trTripsDto) throws Exception;
	public TRTripsDto saveTRTrips(TRTripsDto trTripsDto) throws Exception;
	public TRTripsDto sendToHR(TRTripsDto trTripsDto) throws Exception;
	public NewAllowanceDto saveNewAllowance(NewAllowanceDto NewAllowanceDto) throws Exception;
	public List<FinancialElementBean> getFinancialElementList(Long systemId) throws Exception;
	@Select(value={"select to_char(d.UPDATED_DATE,'dd-mm-yyyy hh24:mi') || ' - ' || uf.USER_INF_ID || ' - ' || uf.USER_FULL_NAME from ALLOWANCE_DETAILS d join SAP_USERS u on d.SENDER_ID = u.USER_ID join SAP_USER_INF uf on u.USER_INF_ID = uf.USER_INF_ID where d.ALLOWANCE_DETAIL_ID = #{allowanceID}"})
	public String getUpdatorName(Long allowanceID) throws Exception;
	//other allowance
	public OtherAllowanceDto saveAllowanceDetail(OtherAllowanceDto allowanceDetailDto);
	public OtherAllowanceDto saveAllowanceDetailExcel(OtherAllowanceDto allowanceDetailDto);
	public OtherAllowanceDto editAllowanceDetail(OtherAllowanceDto allowanceDetailDto);
	public OtherAllowanceDto deleteAllowanceDetail(OtherAllowanceDto allowanceDetailDto);
	public List<AllowanceDetailBean> getDriverAllowance(OtherAllowanceDto allowanceDetailDto);
	public List<AllowanceDetailBean> getUnIntegratedAllowances(Long branchId) throws Exception;
	
	@Select(value={"SELECT PLATE_NUMBER FROM ASSETS where ASSET_NUMBER = #{assetNum}"})
	public String updatePlateNumber(String assetNum) throws Exception;
	@Select(value={"SELECT USER_FULL_NAME FROM SAP_USER_INF where USER_INF_ID = #{driver1ID}"})
	public String updateDriver1(String driver1ID) throws Exception;
	@Select(value={"SELECT USER_FULL_NAME FROM SAP_USER_INF where USER_INF_ID = #{driver2ID}"})
	public String updateDriver2(String driver2ID) throws Exception;
	
	//allowance types
	public void insertAllowanceType(AllowanceTypeDto allowanceTypeDto) throws Exception;
	public void updateAllowanceTypes(AllowanceTypeDto allowanceTypeDto)throws Exception ;
	public List<AllowanceTypeBean> findAllowanceTypes(Long systemId);
	public List<AllowanceTypeBean> getAllAllowanceTypes();
	
	
}
