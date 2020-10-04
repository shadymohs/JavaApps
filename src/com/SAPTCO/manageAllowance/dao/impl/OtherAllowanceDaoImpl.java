package com.SAPTCO.manageAllowance.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceDetailBean;
import com.SAPTCO.common.ibatis.mapperBeans.AllowanceTypeBean;
import com.SAPTCO.common.ibatis.mapperInterface.ManageAllowance;
import com.SAPTCO.manageAllowance.dao.OtherAllowanceDao;
import com.SAPTCO.manageAllowance.dto.OtherAllowanceDto;
import com.SAPTCO.manageAllowance.dto.TRTripsDto;


/**
 * @author alqassemga
 *
 */

@Repository("otherAllowanceDao")
public class OtherAllowanceDaoImpl implements OtherAllowanceDao{

	
	@Autowired
	SqlSession session;
		
	public OtherAllowanceDto saveAllowanceDetail(OtherAllowanceDto allowanceDetailDto)
			throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.saveAllowanceDetail(allowanceDetailDto);
		return allowanceDetailDto;
	}

	@Override
	public OtherAllowanceDto editAllowanceDetail(OtherAllowanceDto allowanceDetailDto)
			throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.editAllowanceDetail(allowanceDetailDto);
	    return allowanceDetailDto;
		
		
	}

	@Override
	public OtherAllowanceDto deleteAllowanceDetail(OtherAllowanceDto allowanceDetailDto)
			throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.deleteAllowanceDetail(allowanceDetailDto);
		return allowanceDetailDto;
		
	}

	@Override
	public OtherAllowanceDto getDriverAllowance(
			OtherAllowanceDto allowanceDetailDto) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.getDriverAllowance(allowanceDetailDto);
		return allowanceDetailDto;
	}

	@Override
	public List<AllowanceTypeBean> getAllowanceTypes() throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		List<AllowanceTypeBean> allowanceTypes = mapper.findAllowanceTypes(BaseBB.getUserDetails().getLoggedInSystem().getId());
		return allowanceTypes;
	}

	@Override
	public OtherAllowanceDto sendToHR(OtherAllowanceDto otherAllowanceDto)
			throws Exception {
		TRTripsDto trTripsDto = new TRTripsDto();
		trTripsDto.setAllowanceDetailBean(otherAllowanceDto.getAllowanceDetailBean());
		trTripsDto.setUpdatedBy(BaseBB.getUserDetails().getId());
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.sendToHR(trTripsDto);
		otherAllowanceDto.setProcResult(trTripsDto.getProcResult());
		return otherAllowanceDto;
	}

	@Override
	public String updatePlateNumber(String assetNum) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		return mapper.updatePlateNumber(assetNum);
	}

	@Override
	public String updateDriver1(String driver1id) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		return mapper.updateDriver1(driver1id);
	}

	@Override
	public String updateDriver2(String driver2id) throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		return mapper.updateDriver2(driver2id);
	}
		
	public OtherAllowanceDto saveAllowanceDetailExcel(OtherAllowanceDto allowanceDetailDto)
			throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		mapper.saveAllowanceDetailExcel(allowanceDetailDto);
		return allowanceDetailDto;
	}

	@Override
	public List<AllowanceDetailBean> getUnIntegratedAllowances(Long branchId)
			throws Exception {
		session.clearCache();
		ManageAllowance mapper = session.getMapper(ManageAllowance.class);
		return mapper.getUnIntegratedAllowances(branchId);
	}

}
