package com.SAPTCO.hajLimo.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.EtimadBean;
import com.SAPTCO.common.ibatis.mapperBeans.LimoHajBean;
import com.SAPTCO.common.ibatis.mapperBeans.UserBean;
import com.SAPTCO.hajLimo.dao.HajLimoDao;
import com.SAPTCO.hajLimo.dto.HajLimoDto;
import com.SAPTCO.hajLimo.dto.HajLimoWSDto;

@Repository("hajLimoDao")
public class HajLimoDaoImpl implements HajLimoDao{

	@Autowired
    private SqlMapClientTemplate sqlMapClientTemplateHajLimo;
	
	private final Log logger = LogFactory.getLog(getClass());

	@SuppressWarnings("unchecked")
	@Override
	public HajLimoDto getLookups() throws Exception {
		HajLimoDto hajLimoDto = new HajLimoDto();
		hajLimoDto.setVehicleTypes(sqlMapClientTemplateHajLimo.queryForList("getvehiclsTypes"));
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("branchId",BaseBB.getUserDetails().getLoggedInBranch().getId());
		sqlMapClientTemplateHajLimo.queryForObject("getFromLocations",params);
		hajLimoDto.setFromStations((List<BaseDto>) params.get("resultList"));
		hajLimoDto.setPaymentMethods(sqlMapClientTemplateHajLimo.queryForList("getPaymentMethods"));
		return hajLimoDto;
	}


	@SuppressWarnings("unchecked")
	@Override
	public HajLimoDto getToStations(HajLimoDto hajLimoDto) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fromLocation",hajLimoDto.getFromStationId());
		sqlMapClientTemplateHajLimo.queryForObject("getToLocations",params);
		hajLimoDto.setToStations((List<BaseDto>) params.get("resultList"));
		return hajLimoDto;
	}


	@SuppressWarnings("unchecked")
	@Override
	public HajLimoDto getvehicls(HajLimoDto hajLimoDto) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fromLocation",hajLimoDto.getFromStationId());
		params.put("toLocation",hajLimoDto.getToStationId());
		params.put("vehicleType",hajLimoDto.getVehicleTypeId());
		sqlMapClientTemplateHajLimo.queryForObject("getVehicls",params);
		hajLimoDto.setVehicles((List<BaseDto>) params.get("resultList"));
		return hajLimoDto;
	}


	@Override
	public HajLimoDto calculatePrice(HajLimoDto hajLimoDto) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fromStationId",hajLimoDto.getFromStationId());
		params.put("toStationId",hajLimoDto.getToStationId());
		params.put("vehicleId",hajLimoDto.getVehicleId());
		params.put("etimadList",getEtimadList(hajLimoDto.getEtimadList()));
		sqlMapClientTemplateHajLimo.queryForObject("calculatePrice",params);
		hajLimoDto.setRemainingAmount((Float) params.get("remainingAmount"));
		hajLimoDto.setVatAmount((Float) params.get("vatAmount"));
		hajLimoDto.setVehiclePrice((Float) params.get("vehiclePrice"));
		hajLimoDto.setTotalEtimadAmount((Float) params.get("totalEtimadAmount"));
		hajLimoDto.setProcResult((String) params.get("procResult"));
		return hajLimoDto;
	}


	private String getEtimadList(List<EtimadBean> etimadList) {
		if(etimadList != null && !etimadList.isEmpty()){
			String etimadString = null;
			for(EtimadBean etimad : etimadList){
				if(etimadString != null)
					etimadString = etimadString + ":";
				else
					etimadString = "";
				etimadString = etimadString + etimad.getEtimadNumber() + "," +
					etimad.getAdultsCount() + "," + etimad.getChildrenCount();
			}
			return etimadString;
		}else
			return null;
	}


	@Override
	public HajLimoDto createTickets(HajLimoDto hajLimoDto) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("fromStationId",hajLimoDto.getFromStationId());
		params.put("toStationId",hajLimoDto.getToStationId());
		params.put("vehicleId",hajLimoDto.getVehicleId());
		params.put("isManual",hajLimoDto.getIsManual());
		params.put("manualAmount",hajLimoDto.getManualAmount());
		params.put("paymentMethodId",hajLimoDto.getPaymentMethodId());
		params.put("driverNumber",hajLimoDto.getDriverNumber());
		params.put("vehicleNumber",hajLimoDto.getVehicleNumber());
		params.put("officeNumber",hajLimoDto.getOfficeNumber());
		params.put("creator",BaseBB.getUserDetails().getId());
		params.put("etimadList",getEtimadList(hajLimoDto.getEtimadList()));
		params.put("season",hajLimoDto.getSeason());
		sqlMapClientTemplateHajLimo.queryForObject("createTickets",params);
		hajLimoDto.setTicketNumber((String) params.get("ticketNumber"));
		hajLimoDto.setRemainingAmount((Float) params.get("remainingAmount"));
		hajLimoDto.setVatAmount((Float) params.get("vatAmount"));
		hajLimoDto.setVehiclePrice((Float) params.get("vehiclePrice"));
		hajLimoDto.setTotalEtimadAmount((Float) params.get("totalEtimadAmount"));
		hajLimoDto.setProcResult((String) params.get("procResult"));
		return hajLimoDto;
	}


	@Override
	public Boolean validateEtimad(String etimadNumber) throws Exception {
		return (Boolean) sqlMapClientTemplateHajLimo.queryForObject("validateEtimad",etimadNumber);
	}
	
	@Override
	public HajLimoWSDto dispatchTicket(Long userId, String ticketNumber)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId",userId);
		params.put("ticketNumber",ticketNumber);
		sqlMapClientTemplateHajLimo.queryForObject("dispatchTicket",params);
		HajLimoWSDto hajLimoWSDto = new HajLimoWSDto();
		hajLimoWSDto.setProcResult((Integer) params.get("procResult"));
		hajLimoWSDto.setLastTransaction((String) params.get("lastTransaction"));
		hajLimoWSDto.setVehicle((String) params.get("vehicle"));
		hajLimoWSDto.setToLocation((String) params.get("toLocation"));
		return hajLimoWSDto;
	}


	@Override
	public HajLimoWSDto arrivalTicket(Long userId, String ticketNumber)
			throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("userId",userId);
		params.put("ticketNumber",ticketNumber);
		sqlMapClientTemplateHajLimo.queryForObject("arrivalTicket",params);
		HajLimoWSDto hajLimoWSDto = new HajLimoWSDto();
		hajLimoWSDto.setProcResult((Integer) params.get("procResult"));
		hajLimoWSDto.setLastTransaction((String) params.get("lastTransaction"));
		hajLimoWSDto.setVehicle((String) params.get("vehicle"));
		return hajLimoWSDto;
	}


	@Override
	public void printTicket(String ticketNumber) throws Exception {
		sqlMapClientTemplateHajLimo.update("printTicket",ticketNumber);
	}
	
	@Override
	public Long validateUser(String userName, String password) throws Exception {
		UserBean userBean = new UserBean();
		userBean.setUserName(userName);
		userBean.setPassword(password);
		logger.error("sqlMapClientTemplateHajLimo" + sqlMapClientTemplateHajLimo);
		logger.error("result " + sqlMapClientTemplateHajLimo.queryForObject("validateUser",userBean));
		return (Long) sqlMapClientTemplateHajLimo.queryForObject("validateUser",userBean);
	}

	public SqlMapClientTemplate getSqlMapClientTemplateHajLimo() {
		return sqlMapClientTemplateHajLimo;
	}


	public void setSqlMapClientTemplateHajLimo(
			SqlMapClientTemplate sqlMapClientTemplateHajLimo) {
		this.sqlMapClientTemplateHajLimo = sqlMapClientTemplateHajLimo;
	}

	@Override
	public String cancelTicket(String ticketNumber) throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ticketNumber",ticketNumber);
		params.put("userId",BaseBB.getUserDetails().getId());
		sqlMapClientTemplateHajLimo.queryForObject("cancelTicket",params);
		return (String) params.get("procResult");
	}

	@Override
	public HajLimoWSDto generateBatchNumber() throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		sqlMapClientTemplateHajLimo.queryForObject("generateBatchNumber",params);
		HajLimoWSDto hajLimoWSDto = new HajLimoWSDto();
		hajLimoWSDto.setBatchNumber((Long) params.get("batchNumber"));
		hajLimoWSDto.setIsNewService((Boolean) params.get("isNewService"));
		return hajLimoWSDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LimoHajBean> getFinIntegrationList() throws Exception {
		Map<String,Object> params = new HashMap<String, Object>();
		sqlMapClientTemplateHajLimo.queryForList("getFinIntegrationList",params);
		return (List<LimoHajBean>) params.get("resultList");
	}

	@Override
	public void updateTicketFinIntegration(String ticketNumber) throws Exception{
		Map<String,Object> params = new HashMap<String, Object>();
		params.put("ticketNumber",ticketNumber);
		sqlMapClientTemplateHajLimo.queryForObject("updateTicketFinIntegration",params);
	}

}
