package com.SAPTCO.common.bao.impl;
 
import java.util.List;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.common.bao.CommonBao;
import com.SAPTCO.common.dao.CommonDao;
import com.SAPTCO.common.dto.CommonDto;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.CompanyBean;

/**
 * @author Shady
*/

@Service("commonBao")
public class CommonBaoImpl implements CommonBao {

	private static final long serialVersionUID = 1L;

	@Autowired
	private CommonDao commonDao;

	@Autowired
	private StandardPBEStringEncryptor passwordEncryptor;

	//return branch list
	public CommonDto findUserbranchesByName(CommonDto commonDto)
			throws Exception {
		return commonDao.findUserbranchesByName(commonDto);
	}
	

	public CommonDto findUserSystemsByName(CommonDto commonDto) throws Exception{
		return commonDao.findUserSystemsByName(commonDto);
	}

	//check user data
	public CommonDto authenticationUser(CommonDto commonDto) throws Exception {
		commonDto.getUserBean().setPassword(passwordEncryptor.encrypt(commonDto.getUserBean().getPassword()));
		return commonDao.authenticationUser(commonDto);
	}

	//get user programs
	public CommonDto retrieveAuthorizedPrograms(CommonDto commonDto)
			throws Exception {
		return commonDao.retrieveAuthorizedPrograms(commonDto);
	}
	
	public List<CompanyBean> getCompaniesList() throws Exception{
		return commonDao.getCompaniesList();
	}
	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}

	public StandardPBEStringEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(StandardPBEStringEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}

	public String getAppVersion() {
		return commonDao.getAppVersion();
	}

	public String getAppVersionDate() {
		return commonDao.getAppVersionDate();
	}

	@Override
	public WSConfigDTO getILSResourceModificationWSConfig() {
		return commonDao.getILSResourceModificationWSConfig();
	}

	@Override
	public WSConfigDTO getTripsScheduleWSConfig() {
		return commonDao.getTripsScheduleWSConfig();
	}

}