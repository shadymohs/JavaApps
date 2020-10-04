package com.SAPTCO.hajLimo.endPoints.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.annotation.PostConstruct;
import javax.jws.WebService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.salt.FixedStringSaltGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import com.SAPTCO.hajLimo.dao.HajLimoDao;
import com.SAPTCO.hajLimo.dto.HajLimoWSDto;
import com.SAPTCO.hajLimo.endPoints.LimoHajWS;

@WebService(endpointInterface = "com.SAPTCO.hajLimo.endPoints.LimoHajWS")
@Service
public class LimoHajWSImpl extends SpringBeanAutowiringSupport implements LimoHajWS {

	@Autowired
	private HajLimoDao hajLimoDao;
	
	@Autowired
	private StandardPBEStringEncryptor passwordEncryptor;

	private final Log logger = LogFactory.getLog(getClass());
	
	@PostConstruct
	public void init() {
	    SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
	}
	
	private Connection getConnection() throws Exception{
		 Class.forName("oracle.jdbc.driver.OracleDriver");
         String url = "jdbc:oracle:thin:@exdb-scan.SAPTCO.local:1521/fsdb";
         return DriverManager.getConnection(url, "limo_haj", "limo_haj");
	}
	
	@Override
	public Long authorizeUser(String userName, String password) {		
		try {
			Long userId = 0L;
			if(hajLimoDao != null)
				userId = hajLimoDao.validateUser(userName, passwordEncryptor.encrypt(password));
			else{
				StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
				encryptor.setAlgorithm("PBEWithMD5AndDES");
				encryptor.setPassword("EJADA_SAPTCO_1112009");
				FixedStringSaltGenerator saltGenerator = new FixedStringSaltGenerator();
				saltGenerator.setSalt("saltGenerator");
				encryptor.setSaltGenerator(saltGenerator);
				Connection con = getConnection();
				PreparedStatement stmt=con.prepareStatement("select user_id from drv_allow.sap_users " +
						"where user_name = ? and user_password = ? and user_is_active = 1" + 
						"and user_is_deleted = 0");
		        stmt.setString(1,userName);
		        stmt.setString(2,encryptor.encrypt(password));
				ResultSet rs=stmt.executeQuery();  
				while(rs.next())  
					userId = rs.getLong(1);
			}
			return userId != null? userId : 0L;
		} catch (Exception e) {
			logger.error("exception " + e);
		}
		return 0L;
	}

	@Override
	public HajLimoWSDto dispatch(Long userId, String ticketNumber) {
		try {
			HajLimoWSDto hajLimoWSDto = new HajLimoWSDto();
			if(hajLimoDao != null)
				hajLimoWSDto = hajLimoDao.dispatchTicket(userId, ticketNumber);
			else{
				Connection con = getConnection();
				CallableStatement stmt = con.prepareCall("{call LIMO_HAJ_PKG.RECORD_DEPARTURE (?,?,?,?,?,?,?)}");
				stmt.setString(1,ticketNumber);
				stmt.setLong(2,userId);
				stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(7,java.sql.Types.VARCHAR);
				stmt.executeUpdate();
				hajLimoWSDto.setVehicle(stmt.getString(3));
				hajLimoWSDto.setToLocation(stmt.getString(4));
				hajLimoWSDto.setLastTransaction(stmt.getString(5));
				hajLimoWSDto.setProcResult(stmt.getInt(6));
				try{
					con.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			return hajLimoWSDto == null ? new HajLimoWSDto() : hajLimoWSDto;
		} catch (Exception e) {
			logger.error(e);
		}
		return new HajLimoWSDto();
	}

	@Override
	public HajLimoWSDto arrival(Long userId, String ticketNumber) {
		try {
			HajLimoWSDto hajLimoWSDto = new HajLimoWSDto();
			if(hajLimoDao != null)
				hajLimoWSDto = hajLimoDao.arrivalTicket(userId, ticketNumber);
			else{
				Connection con = getConnection();
				CallableStatement stmt = con.prepareCall("{call LIMO_HAJ_PKG.RECORD_ARRIVAL(?,?,?,?,?,?)}");
				stmt.setString(1,ticketNumber);
				stmt.setLong(2,userId);
				stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(4,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(5,java.sql.Types.VARCHAR);
				stmt.registerOutParameter(6,java.sql.Types.VARCHAR);
				stmt.executeUpdate();
				hajLimoWSDto.setVehicle(stmt.getString(3));
				hajLimoWSDto.setLastTransaction(stmt.getString(4));
				hajLimoWSDto.setProcResult(stmt.getInt(5));
				try{
					con.close();
				}catch(Exception ex){
					ex.printStackTrace();
				}
			}
			return hajLimoWSDto == null ? new HajLimoWSDto() : hajLimoWSDto;
		} catch (Exception e) {
			logger.error(e);
		}
		return new HajLimoWSDto();
	}

	public HajLimoDao getHajLimoDao() {
		return hajLimoDao;
	}

	public void setHajLimoDao(HajLimoDao hajLimoDao) {
		this.hajLimoDao = hajLimoDao;
	}

	public StandardPBEStringEncryptor getPasswordEncryptor() {
		return passwordEncryptor;
	}

	public void setPasswordEncryptor(StandardPBEStringEncryptor passwordEncryptor) {
		this.passwordEncryptor = passwordEncryptor;
	}
}