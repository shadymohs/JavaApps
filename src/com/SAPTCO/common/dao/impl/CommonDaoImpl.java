package com.SAPTCO.common.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.dao.CommonDao;
import com.SAPTCO.common.dto.CommonDto;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;
import com.SAPTCO.common.ibatis.mapperInterface.Common;
import com.SAPTCO.common.ibatis.mapperInterface.General;

/**
 * @author Shady
 */

@Repository("commonDao")
public class CommonDaoImpl implements CommonDao{
	
	@Autowired
	SqlSession session;
	
	@Value("#{versionConfig['ver.major']}")
	private String versionMajor;
	@Value("#{versionConfig['ver.minor']}")
	private String versionMinor;
	@Value("#{versionConfig['ver.type']}")
	private String versionType;
	@Value("#{versionConfig['ver.day']}")
	private String versionDay;
	@Value("#{versionConfig['ver.month']}")
	private String versionMonth;
	@Value("#{versionConfig['ver.year']}")
	private String versionYear;
	@Value("#{wsConfig['tripsScheduleURL']}")
	private String tripsScheduleURL;
	@Value("#{wsConfig['ilsIntegURL']}")
	private String ilsIntegURL;
	@Value("#{wsConfig['ilsIntegUserName']}")
	private String ilsIntegUserName;
	@Value("#{wsConfig['ilsIntegPassword']}")
	private String ilsIntegPassword;
	@Value("#{wsConfig['openBatchLimoHaj']}")
	private String openBatchLimoHaj;
	@Value("#{wsConfig['insertLimoHaj']}")
	private String insertLimoHaj;
	@Value("#{wsConfig['closeBatchLimoHaj']}")
	private String closeBatchLimoHaj;
	@Value("#{wsConfig['userNameLimoHaj']}")
	private String userNameLimoHaj;
	@Value("#{wsConfig['passwordLimoHaj']}")
	private String passwordLimoHaj;
	@Value("#{wsConfig['openBatchNewLimoHaj']}")
	private String openBatchNewLimoHaj;
	@Value("#{wsConfig['insertNewLimoHaj']}")
	private String insertNewLimoHaj;
	@Value("#{wsConfig['closeBatchNewLimoHaj']}")
	private String closeBatchNewLimoHaj;
	@Value("#{wsConfig['userNameNewLimoHaj']}")
	private String userNameNewLimoHaj;
	@Value("#{wsConfig['passwordNewLimoHaj']}")
	private String passwordNewLimoHaj;

	//return user branches list
	public CommonDto findUserbranchesByName(CommonDto commonDto)
			throws Exception {
		if(commonDto.getUserBean().getLoggedInSystem().getId() != null){
			session.clearCache();
			Common mapper = session.getMapper(Common.class);
			commonDto.setBranchList(mapper.findUserbranchesByName(commonDto));
		}else
			commonDto.setBranchList(new ArrayList<BranchesBean>());
		return commonDto;
	}
	

	public CommonDto findUserSystemsByName(CommonDto commonDto) throws Exception{
		if(commonDto.getUserBean().getUserName()!=null && !commonDto.getUserBean().getUserName().equals("")){
			session.clearCache();
			Common mapper = session.getMapper(Common.class);
			commonDto.setSystemsList(mapper.findUserSystemsByName(commonDto));
		}else
			commonDto.setBranchList(new ArrayList<BranchesBean>());
		return commonDto;
	}

	//check user data
	public CommonDto authenticationUser(CommonDto commonDto) throws Exception {
		session.clearCache();
		Common mapper = session.getMapper(Common.class);
		mapper.authenticationUser(commonDto);
		return commonDto;
	}

	//get user programs
	public CommonDto retrieveAuthorizedPrograms(CommonDto commonDto) throws Exception {
		session.clearCache();
		Common mapper = session.getMapper(Common.class);
		commonDto.setMenuList(mapper.retrieveAuthorizedPrograms(commonDto));
		return commonDto;
	}
	
	public List<CompanyBean> getCompaniesList() throws Exception{
		session.clearCache();
		General mapper = session.getMapper(General.class);
		return mapper.getCompaniesList();
	}

	public String getAppVersion() {
		return versionMajor + "." + versionMinor + "_"
				+ versionType;
	}

	public String getAppVersionDate() {
		return versionDay + "_" + versionMonth + "_" + versionYear;
	}

	@Override
	public WSConfigDTO getILSResourceModificationWSConfig() {
		WSConfigDTO wsConfigDTO = new WSConfigDTO();
		wsConfigDTO.setuRL(ilsIntegURL);
		wsConfigDTO.setUserName(ilsIntegUserName);
		wsConfigDTO.setPassword(ilsIntegPassword);
		return wsConfigDTO;
	}

	@Override
	public WSConfigDTO getTripsScheduleWSConfig() {
		WSConfigDTO wsConfigDTO = new WSConfigDTO();
		wsConfigDTO.setuRL(tripsScheduleURL);
		return wsConfigDTO;
	}

	@Override
	public WSConfigDTO getLimoHajFinIntegWSConfig(int serviceConfig, boolean isNewService) {
		WSConfigDTO wsConfigDTO = new WSConfigDTO();
		//newService
		if(isNewService){
			//open batch
			if(serviceConfig == 1)
				wsConfigDTO.setuRL(openBatchNewLimoHaj);
			//insert
			else if(serviceConfig == 2)
				wsConfigDTO.setuRL(insertNewLimoHaj);
			//close batch
			else if(serviceConfig == 3)
				wsConfigDTO.setuRL(closeBatchNewLimoHaj);
			wsConfigDTO.setUserName(userNameNewLimoHaj);
			wsConfigDTO.setPassword(passwordNewLimoHaj);
		//old service
		}else{
			//open batch
			if(serviceConfig == 1)
				wsConfigDTO.setuRL(openBatchLimoHaj);
			//insert
			else if(serviceConfig == 2)
				wsConfigDTO.setuRL(insertLimoHaj);
			//close batch
			else if(serviceConfig == 3)
				wsConfigDTO.setuRL(closeBatchLimoHaj);
			wsConfigDTO.setUserName(userNameLimoHaj);
			wsConfigDTO.setPassword(passwordLimoHaj);
		}
		return wsConfigDTO;
	}
	
}
