package com.SAPTCO.manageAllowance.bao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperBeans.FinancialElementBean;
import com.SAPTCO.manageAllowance.bao.OtherAllowanceBao;
import com.SAPTCO.manageAllowance.dao.OtherAllowanceDao;
import com.SAPTCO.manageAllowance.dto.FileUploadDto;
import com.SAPTCO.manageAllowance.dto.OtherAllowanceDto;



/**
 * @author alqassemga
 *
 */
@Service("otherAllowanceBao")
public class OtherAllowanceBaoImpl implements OtherAllowanceBao{

	
	@Autowired
	private OtherAllowanceDao otherAllowanceDao;

	@Override
	public OtherAllowanceDto saveAllowanceDetail(
			OtherAllowanceDto allowanceDetailDto) throws Exception {
		
		return otherAllowanceDao.saveAllowanceDetail(allowanceDetailDto);
	}

	@Override
	public OtherAllowanceDto editAllowanceDetail(
			OtherAllowanceDto allowanceDetailDto) throws Exception {
	
		return otherAllowanceDao.editAllowanceDetail(allowanceDetailDto);
	}

	@Override
	public OtherAllowanceDto deleteAllowanceDetail(
			OtherAllowanceDto allowanceDetailDto) throws Exception {
		
		return otherAllowanceDao.deleteAllowanceDetail(allowanceDetailDto);
	}

	@Override
	public OtherAllowanceDto getDriverAllowance(
			OtherAllowanceDto allowanceDetailDto) throws Exception {
		
		return otherAllowanceDao.getDriverAllowance(allowanceDetailDto);
	}

	@Override
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception {
		
		return otherAllowanceDao.getAllowanceTypes();
	}

	public OtherAllowanceDao getOtherAllowanceDao() {
		return otherAllowanceDao;
	}

	public void setOtherAllowanceDao(OtherAllowanceDao otherAllowanceDao) {
		this.otherAllowanceDao = otherAllowanceDao;
	}

	@Override
	public OtherAllowanceDto sendToHR(OtherAllowanceDto trTripsDto) throws Exception {
		return otherAllowanceDao.sendToHR(trTripsDto);
	}

	@Override
	public String updatePlateNumber(String assetNum) throws Exception {
		return otherAllowanceDao.updatePlateNumber(assetNum);
	}

	@Override
	public String updateDriver1(String driver1id) throws Exception {
		return otherAllowanceDao.updateDriver1(driver1id);
	}

	@Override
	public String updateDriver2(String driver2id) throws Exception {
		return otherAllowanceDao.updateDriver2(driver2id);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public FileUploadDto readExcel(Workbook data) throws Exception {
		FileUploadDto fileUploadDto = new FileUploadDto();
		List<AllowanceDetailBean> rejectedList = new ArrayList<AllowanceDetailBean>();
		List<AllowanceDetailBean> succedAllowance = new ArrayList<AllowanceDetailBean>();
		Integer totalRows = 0;
		Integer succedRows = 0;
		Integer rejectedRows = 0;
		Integer rowNum = 0;
		Sheet sheet = data.getSheetAt(0);
		Iterator rows = sheet.rowIterator();
		while (rows.hasNext()) {
			AllowanceDetailBean allowanceBean = new AllowanceDetailBean();
			XSSFRow row = (XSSFRow) rows.next();
			String driver1Num = null;
			if(row.getCell(0) != null)
				driver1Num = row.getCell(0).toString();
			String tripDate = null;
			if(row.getCell(1) != null)
				tripDate = row.getCell(1).toString();
			String allowanceCode = null;
			if(row.getCell(2) != null)
				allowanceCode = row.getCell(2).toString();
			String allowanceType = null;
			if(row.getCell(3) != null)
				allowanceType = row.getCell(3).toString();
			String driver1Allowance = null;
			if(row.getCell(4) != null)
				driver1Allowance = row.getCell(4).toString();
			String finElel = null;
			if(row.getCell(5) != null)
				finElel=row.getCell(5).toString();
//			String diesel = null;
//			if(row.getCell(6) != null)
//				diesel = row.getCell(6).toString();
//			String housing = null;
//			if(row.getCell(7) != null)
//				housing = row.getCell(7).toString();
//			String staying = null;
//			if(row.getCell(8) != null)
//				staying = row.getCell(8).toString();
//			String visa = null;
//			if(row.getCell(9) != null)
//				visa = row.getCell(9).toString();
//			String borderInsurance = null;
//			if(row.getCell(10) != null)
//				borderInsurance = row.getCell(10).toString();
//			String borderStaying = null;
//			if(row.getCell(11) != null)
//				borderStaying = row.getCell(11).toString();
//			String weeklyVacation = null;
//			if(row.getCell(12) != null)
//				weeklyVacation = row.getCell(12).toString();
//			String internalStaying = null;
//			if(row.getCell(13) != null)
//				internalStaying = row.getCell(13).toString();
//			String internationalStaying = null;
//			if(row.getCell(14) != null)
//				internationalStaying = row.getCell(14).toString();
//			String eventVacation = null;
//			if(row.getCell(15) != null)
//				eventVacation = row.getCell(15).toString();
//			String ramadan = null;
//			if(row.getCell(16) != null)
//				ramadan = row.getCell(16).toString();
//			String driver1Other = null;
//			if(row.getCell(17) != null)
//				driver1Other = row.getCell(17).toString();
			String desc = "upload";
//			if(row.getCell(18) != null)
//				desc = row.getCell(18).toString();
			String truck = null;
			if(row.getCell(7) != null)
				truck = row.getCell(7).toString();
	        try{
	        	++rowNum;
	        	allowanceBean.setSeq(rowNum);
	        	if(driver1Num == null || driver1Num.equals("")){
	        		continue;
	        	}else
	        		try{
	        			allowanceBean.setDriver1Number((long) Double.parseDouble(driver1Num));
	        			++totalRows;
	        		}catch(Exception e){
	        			continue;
	        		}
				String assetNum = null;
				if(row.getCell(6) != null){
					try{
						assetNum = row.getCell(6).toString();
					}catch(Exception e){
							continue;
						}
	        		}
	        	if(driver1Allowance == null || driver1Allowance.equals("") || driver1Allowance.equals("0")){
	        		rejectedList.add(allowanceBean);
	        		++rejectedRows;
	        		continue;
	        	}else{
	        		allowanceBean.setDriver1Allowance(Double.parseDouble(driver1Allowance));
//	        		if(driver1Other != null && !driver1Other.equals(""))
//	        			allowanceBean.setOther(Double.parseDouble(driver1Other));
	        	}
	        	if(allowanceType == null || allowanceType.equals("")){
	        		rejectedList.add(allowanceBean);
	        		++rejectedRows;
	        		continue;
	        	}else
	        		allowanceBean.getAllowanceTypeBean().setId((long) Double.parseDouble(allowanceType));
	        	if(assetNum == null || assetNum.equals("")){
	        		rejectedList.add(allowanceBean);
	        		++rejectedRows;
	        		continue;
	        	}else
	        		allowanceBean.setBusNumber(assetNum);
	        	if(tripDate == null || tripDate.equals("")){
	        		rejectedList.add(allowanceBean);
	        		++rejectedRows;
	        		continue;
	        	}else{
	        		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
	        		try{
	        			df.parse(tripDate);
	        		}catch(Exception e){
	        			allowanceBean.setErrorReason(BaseBB.getResourceBundleString(SystemConstants.Error_RESOURCE_BUNDLE,"wrong_date"));
	        			rejectedList.add(allowanceBean);
		        		++rejectedRows;
		        		continue;
	        		}
	        		Calendar c=new GregorianCalendar();
	        		c.add(Calendar.DATE, 30);
	        		Date today = c.getTime();
	        		if(df.parse(tripDate).before(today))
	        			allowanceBean.setTripDate(tripDate);
	        		else{
	        			allowanceBean.setErrorReason(BaseBB.getResourceBundleString(SystemConstants.Error_RESOURCE_BUNDLE,"wrong_date"));
	        			rejectedList.add(allowanceBean);
		        		++rejectedRows;
		        		continue;
	        		}
	        	}
	        	if(allowanceCode != null && !allowanceCode.equals(""))
	        		allowanceBean.setTripCode(allowanceCode);
	        	
	        	if(desc != null && !desc.equals(""))
	        		allowanceBean.setDescription(desc);
	        	if(truck != null && !truck.equals(""))
	        		allowanceBean.setTrailerNumber(truck);
//	        	if(diesel != null && !diesel.equals(""))
//	        		allowanceBean.setDiesel(Double.parseDouble(diesel));
//	        	if(housing != null && !housing.equals(""))
//	        		allowanceBean.setHousing(Double.parseDouble(housing));
//	        	if(staying != null && !staying.equals(""))
//	        		allowanceBean.setStaying(Double.parseDouble(staying));
//	        	if(visa != null && !visa.equals(""))
//	        		allowanceBean.setVisa(Double.parseDouble(visa));
//	        	if(borderInsurance != null && !borderInsurance.equals(""))
//	        		allowanceBean.setBorderInsurance(Double.parseDouble(borderInsurance));
//	        	if(borderStaying != null && !borderStaying.equals(""))
//	        		allowanceBean.setBorderStaying(Double.parseDouble(borderStaying));
//	        	if(weeklyVacation != null && !weeklyVacation.equals(""))
//	        		allowanceBean.setWeeklyVacation(Double.parseDouble(weeklyVacation));
//	        	if(internalStaying != null && !internalStaying.equals(""))
//	        		allowanceBean.setInternalStaying(Double.parseDouble(internalStaying));
//	        	if(internationalStaying != null && !internationalStaying.equals(""))
//	        		allowanceBean.setInternationalStaying(Double.parseDouble(internationalStaying));
//	        	if(eventVacation != null && !eventVacation.equals(""))
//	        		allowanceBean.setEventVacation(Double.parseDouble(eventVacation));
//	        	if(ramadan != null && !ramadan.equals(""))
//	        		allowanceBean.setRamadan(Double.parseDouble(ramadan));
	        	if(finElel != null && !finElel.equals(""))
	        	{
	        		FinancialElementBean financialElementBean = new FinancialElementBean();
	        		financialElementBean.setId((long) Double.parseDouble(finElel));
	        		allowanceBean.setFinancialElementBean(financialElementBean);
	        	}
	        	OtherAllowanceDto allowanceDetailDto = new OtherAllowanceDto();
	        	allowanceBean.setCreatedBy(BaseBB.getUserDetails().getId());
	        	allowanceBean.setBranchId(BaseBB.getUserDetails().getLoggedInBranch().getId());
	        	allowanceBean.setSystemId(BaseBB.getUserDetails().getLoggedInSystem().getId());
	        	allowanceDetailDto.setAllowanceDetailBean(allowanceBean);
	        	allowanceDetailDto = otherAllowanceDao.saveAllowanceDetailExcel(allowanceDetailDto);
	        	if(allowanceDetailDto.getProcResult() == null || !allowanceDetailDto.getProcResult().equals("Y")){
	        		try{
	        			allowanceBean.setErrorReason(BaseBB.getResourceBundleString(SystemConstants.Error_RESOURCE_BUNDLE,allowanceDetailDto.getProcResult()));
	        		}catch(Exception e){
	        			e.printStackTrace();
	        		}
	        		rejectedList.add(allowanceBean);
	        		++rejectedRows;
	        	}
	        	else{
	        		++ succedRows;
		        	succedAllowance.add(allowanceDetailDto.getAllowanceDetailBean());
	        	}
	        }catch(Exception e){
        		rejectedList.add(allowanceBean);
        		++rejectedRows;
	        	continue;
	        }
		}
		fileUploadDto.setTotalRows(totalRows);
		fileUploadDto.setSuccedRows(succedRows);
		fileUploadDto.setRejectedAllowance(rejectedList);
		fileUploadDto.setSuccedAllowance(succedAllowance);
		fileUploadDto.setRejectedRows(rejectedRows);
	    return fileUploadDto;
	}

	@Override
	public List<AllowanceDetailBean> getUnIntegratedAllowances(Long branchId)
			throws Exception {
		return otherAllowanceDao.getUnIntegratedAllowances(branchId);
	}
	
}
