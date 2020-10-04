package com.SAPTCO.manageAllowance.bao;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;

import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.manageAllowance.dto.FileUploadDto;
import com.SAPTCO.manageAllowance.dto.OtherAllowanceDto;

/**
 * @author alqassemga
 *
 */
public interface OtherAllowanceBao {

	
	public OtherAllowanceDto saveAllowanceDetail(OtherAllowanceDto allowanceDetailDto)throws Exception;
	public OtherAllowanceDto editAllowanceDetail(OtherAllowanceDto allowanceDetailDto)throws Exception;
	public OtherAllowanceDto deleteAllowanceDetail(OtherAllowanceDto allowanceDetailDto)throws Exception;
	public OtherAllowanceDto getDriverAllowance(OtherAllowanceDto allowanceDetailDto)throws Exception;
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception ;
	public OtherAllowanceDto sendToHR(OtherAllowanceDto trTripsDto) throws Exception;
	public String updatePlateNumber(String assetNum) throws Exception;
	public String updateDriver1(String driver1ID) throws Exception;
	public String updateDriver2(String driver2ID) throws Exception;
	public FileUploadDto readExcel(Workbook data) throws Exception;
	public List<AllowanceDetailBean> getUnIntegratedAllowances(Long branchId) throws Exception;
	
}
