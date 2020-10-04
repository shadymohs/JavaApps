package com.SAPTCO.hajLimo.endPoints;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.SAPTCO.common.dao.CommonDao;
import com.SAPTCO.common.dto.WSConfigDTO;
import com.SAPTCO.common.ibatis.mapperBeans.LimoHajBean;
import com.SAPTCO.hajLimo.dao.HajLimoDao;
import com.SAPTCO.hajLimo.dto.HajLimoWSDto;

@Component
public class LimoHajJob{

	private final Log logger = LogFactory.getLog(getClass());
	
	@Autowired
	private HajLimoDao hajLimoDao;
	
	@Autowired
	private CommonDao commonDao;
	
//	private Properties loadProperityFile(String fileName)throws Exception{
//		File baseDirectory = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toString()).getParentFile().getParentFile()
//				.getParentFile().getParentFile().getParentFile().getParentFile().getParentFile();
//		File configFile = new File(baseDirectory,"resources/configuration/" + fileName);
//		InputStream input = new FileInputStream(configFile.toString().substring(6));
//		Properties prop = new Properties();
//        prop.load(input);
//		return prop;
//	}
//	
//	private Connection getConnection() throws Exception{
//		Properties prop = loadProperityFile("db.properties");
//		Class.forName(prop.getProperty("db.driver_class"));
//        return DriverManager.getConnection(prop.getProperty("db.url7"), prop.getProperty("db.username7"), prop.getProperty("db.password7"));
//	}

	public void execute(){
		logger.error("Limo Haj job started");
		int totalRecords = 0;
		Double totalAmount = 0D;
		Boolean openedBatch = false;
		HajLimoWSDto hajLimoWSDto = new HajLimoWSDto();
		List<LimoHajBean> successedList = new ArrayList<LimoHajBean>();
		try{
			if(hajLimoDao != null)
				hajLimoWSDto = hajLimoDao.generateBatchNumber();
//			else{
//				Connection con = getConnection();
//				CallableStatement stmt = con.prepareCall("{call LIMO_HAJ_INTEG_PKG.GET_BATCH_NUMBER (?,?)}");
//				stmt.registerOutParameter(1,java.sql.Types.VARCHAR);
//				stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
//				stmt.executeUpdate();
//				hajLimoWSDto.setBatchNumber(stmt.getLong(1));
//				hajLimoWSDto.setIsNewService(stmt.getBoolean(2));
//				try{
//					con.close();
//				}catch(Exception ex){
//					ex.printStackTrace();
//				}
//			}
			List<LimoHajBean> limoHajList = new ArrayList<LimoHajBean>();
			if(hajLimoDao != null)
				limoHajList = hajLimoDao.getFinIntegrationList();
//			else{
//				Connection con = getConnection();
//				CallableStatement stmt = con.prepareCall("{LIMO_HAJ_INTEG_PKG.GET_TICKETS_LIST(?)}");
//				stmt.registerOutParameter(1,OracleTypes.CURSOR);
//				stmt.executeUpdate();
//				ResultSet result = (ResultSet) stmt.getObject(1);
//				while(result.next()){
//					LimoHajBean bean = new LimoHajBean();
//					bean.setTicketNumber(result.getString("INVOICE_NUMBER"));
//				    bean.setSourceName(result.getString("SOURCE_NAME"));
//					bean.setServiceCode(result.getString("SERVICE_CODE"));
//					bean.setServiceDesc(result.getString("SERVICE_DESCRIPTION"));
//					bean.setServiceRegion(result.getString("SERVICE_REGION_CODE"));
//					bean.setServiceDate(result.getString("SERVICE_DATE"));
//					bean.setTransactionType(result.getString("TRANSACTION_TYPE"));
//					bean.setTotalAmount(result.getDouble("TOTAL_AMOUNT"));
//					bean.setDiscountAmount(result.getString("DISCOUNT_AMOUNT"));
//					bean.setPenaltyAmount(result.getString("PENALTY_AMOUNT"));
//					bean.setCurrency(result.getString("CURRENCY"));
//					bean.setSourcePartyId(result.getString("SOURCE_PARTY_ID"));
//					bean.setSourcePartyName(result.getString("SOURCE_PARTY_NAME"));
//					bean.setCustomerCat(result.getString("CUSTOMER_CATEGORY"));
//					bean.setCustomerRegion(result.getString("CUSTOMER_REGION"));
//					bean.setIsMain(result.getString("IS_MAIN"));
//					bean.setSourceAddr(result.getString("SOURCE_ADDR_ID"));
//					bean.setLine1(result.getString("LINE1"));
//					bean.setLine2(result.getString("LINE2"));
//					bean.setCity(result.getString("CITY"));
//					bean.setCountry(result.getString("COUNTRY"));
//					bean.setSourceType(result.getString("SOURCE_TYPE"));
//					bean.setTermsType(result.getString("TERMS_TYPE"));
//					bean.setFreeAmount(result.getString("FREE_AMOUNT"));
//					bean.setAdditionalAmount(result.getDouble("ADDITIONAL_AMOUNT"));
//					bean.setAgentCommAmount(result.getString("AGENT_COMMISSION_AMOUNT"));
//					bean.setAgentCommTax(result.getString("AGENT_COMMISSION_TAX_AMOUNT"));
//					bean.setAdditionalTax(result.getString("ADDITIONAL_TAX_AMOUNT"));
//					bean.setTaxAmount(result.getString("TAX_AMOUNT"));
//					bean.setCustomerVat(result.getString("CUSTOMER_VAT_REG_NO"));
//					bean.setDeductionAmount(result.getString("DEDUCTION_AMOUNT"));
//					bean.setDeductionTax(result.getString("DEDUCTION_TAX_AMOUNT"));
//					bean.setReceiptsNo(result.getString("RECEIPTS_NO"));
//					bean.setInvoiceType(result.getString("INVOICE_TYPE"));
//					bean.setTaxCountry(result.getString("TAX_COUNTRY"));
//					bean.setContractStartDate(result.getString("CONTRACT_START_DATE"));
//					bean.setDiscountTax(result.getString("DISCOUNT_TAX_AMOUNT"));
//					limoHajList.add(bean);
//				}
//				try{
//					con.close();
//				}catch(Exception ex){
//					ex.printStackTrace();
//				}
//			}
			if(limoHajList != null && !limoHajList.isEmpty()){
				if(hajLimoWSDto.getBatchNumber() != null && !hajLimoWSDto.getBatchNumber().equals(0L)
					&& openBatch(hajLimoWSDto.getBatchNumber(),hajLimoWSDto.getIsNewService())){
					logger.error("Limo Haj job batch openned with number " + hajLimoWSDto.getBatchNumber());
					openedBatch = true;
					for(LimoHajBean limoHaj : limoHajList){
						if(insertFinWS(limoHaj,hajLimoWSDto.getBatchNumber(),hajLimoWSDto.getIsNewService())){
							successedList.add(limoHaj);
							totalRecords++;
							Double newAmount = (limoHaj.getTotalAmount() != null ? limoHaj.getTotalAmount() : 0D) +
									(limoHaj.getAdditionalAmount() != null ? limoHaj.getAdditionalAmount() : 0D);
							totalAmount = totalAmount + newAmount;
							logger.error("Limo Haj record inserted " + limoHaj.getTicketNumber() + " in finance");
						}else
							logger.error("Limo Haj failed to insert " + limoHaj.getTicketNumber() + " in finance ");
					}
					if(closeBatch(hajLimoWSDto.getBatchNumber(),hajLimoWSDto.getIsNewService(),totalRecords,totalAmount,successedList))
						logger.error("Limo Haj job batch closed with number " + hajLimoWSDto.getBatchNumber());
					else{
						logger.error("Limo Haj job batch " + hajLimoWSDto.getBatchNumber() + " rolled back");
						closeBatch(hajLimoWSDto.getBatchNumber(),hajLimoWSDto.getIsNewService(),0,0D,new ArrayList<LimoHajBean>());
					}
				}else
					logger.error("Limo Haj job no batch created or batch can't opened");
			}else
				logger.error("Limo Haj job no data to be integrated");
		}catch(Exception ex){
			logger.error("Limo Haj job exception " + ex);
			if(openedBatch){
				try {
					if(closeBatch(hajLimoWSDto.getBatchNumber(),hajLimoWSDto.getIsNewService(),totalRecords,totalAmount,successedList))
						logger.error("Limo Haj job batch closed with number " + hajLimoWSDto.getBatchNumber());
					else
						logger.error("Limo Haj job batch " + hajLimoWSDto.getBatchNumber() + " faild to be closed");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
//	private WSConfigDTO loadDto(Boolean isNewService,int service) throws Exception{
//		WSConfigDTO wsConfigDTO = new WSConfigDTO();
//		Properties prop = loadProperityFile("wsConfig.properties");
//		if(isNewService){
//			if(service == 1)
//				wsConfigDTO.setuRL(prop.getProperty("openBatchNewLimoHaj"));
//			else if(service == 2)
//				wsConfigDTO.setuRL(prop.getProperty("insertNewLimoHaj"));
//			else if(service == 3)
//				wsConfigDTO.setuRL(prop.getProperty("closeBatchNewLimoHaj"));
//			wsConfigDTO.setUserName(prop.getProperty("userNameNewLimoHaj"));
//			wsConfigDTO.setPassword(prop.getProperty("passwordNewLimoHaj"));
//		}else{
//			if(service == 1)
//				wsConfigDTO.setuRL(prop.getProperty("openBatchLimoHaj"));
//			else if(service == 2)
//				wsConfigDTO.setuRL(prop.getProperty("insertLimoHaj"));
//			else if(service == 3)
//				wsConfigDTO.setuRL(prop.getProperty("closeBatchLimoHaj"));
//			wsConfigDTO.setUserName(prop.getProperty("userNameLimoHaj"));
//			wsConfigDTO.setPassword(prop.getProperty("passwordLimoHaj"));
//		}
//		return wsConfigDTO;
//	}
	
	private Boolean openBatch(Long batchNumber, Boolean isNewService) throws Exception{
		Boolean returnStatus = false;
		WSConfigDTO wsConfigDTO = null;
		if(commonDao != null)
			wsConfigDTO = commonDao.getLimoHajFinIntegWSConfig(1, isNewService);
//		else{
//			wsConfigDTO = loadDto(isNewService,1);
//		}
		URL url = new URL(wsConfigDTO.getuRL() + "/" + batchNumber);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization","SAPTCO u=\"" + wsConfigDTO.getUserName() + "\",p=\"" + wsConfigDTO.getPassword() + "\"");
		OutputStream os = conn.getOutputStream();
		os.flush();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpURLConnection.HTTP_OK){
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String loopVar = null;
			StringBuilder sb = new StringBuilder();
	        while ((loopVar = br.readLine()) != null) {
	        	sb = sb.append(loopVar+"\n");
			}
			JSONObject resultList = new JSONObject(sb.toString());
			if(resultList != null){
				JSONArray resultArray = (JSONArray) resultList.get("Items");
				JSONObject resultObj = (JSONObject) resultArray.get(0);
				if(resultObj != null){
			        String status = resultObj.getString("StatusCode");
		        	if(status != null && status.equals("000000"))
		        		returnStatus = true;
				}
			}
		}
		conn.disconnect();
		return returnStatus;
	}
	
	private Boolean insertFinWS(LimoHajBean limoHaj,Long batchNumber, Boolean isNewService) throws Exception{
		Boolean returnStatus = false;
		WSConfigDTO wsConfigDTO = null;
		if(commonDao != null)
			wsConfigDTO = commonDao.getLimoHajFinIntegWSConfig(2, isNewService);
//		else{
//			wsConfigDTO = loadDto(isNewService,2);
//		}
		URL url = new URL(wsConfigDTO.getuRL());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//conn.setRequestProperty("Authorization", "Basic " + new String(Base64.encodeBase64((wsConfigDTO.getUserName() + ":" + wsConfigDTO.getPassword()).getBytes())));
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization","SAPTCO u=\"" + wsConfigDTO.getUserName() + "\",p=\"" + wsConfigDTO.getPassword() + "\"");
		String input = null;
		if(isNewService){
//			input = "{\"ContractType\": \"" + (limoHaj.getc +
//				"\",\"P_TRANSACTION_TYPE\": \"" + (limoHaj.get +
//				"\",\"P_ORDER_TYPE\": \"" + (limoHaj.get +
//				"\",\"P_CRM_SERVICE_CODE\": \"" + (limoHaj.get +
//				"\",\"P_ORDER_NUMBER\": \"" + (limoHaj.get +
//				"\",\"P_VERSION_NUMBER\": \"" + (limoHaj.get +
//				"\",\"P_CRM_REGION_CODE\": \"" + (limoHaj.get +
//				"\",\"P_SERVICE_DATE\": \"" + (limoHaj.get +
//				"\",\"P_TERMS_TYPE\": \"" + (limoHaj.get +
//				"\",\"P_SERVICE_DESCRIPTION\": \"" + (limoHaj.get +
//				"\",\"P_RENT_AMOUNT\": \"" + (limoHaj.get +
//				"\",\"P_DISCOUNT_AMOUNT\": \"" + (limoHaj.get +
//				"\",\"DeductionAmount\": \"" + (limoHaj.get +
//				"\",\"AdditionalAmount\": \"" + (limoHaj.get +
//				"\",\"FreeServiceAmount\": \"" + (limoHaj.get +
//				"\",\"AgentCommissionAmount\": \"" + (limoHaj.get +
//				"\",\"CustomerId\": \"" + (limoHaj.get +
//				"\",\"CustomerName\": \"" + (limoHaj.get +
//				"\",\"P_OU_TYPE_CD\": \"" + (limoHaj.get +
//				"\",\"P_CRM_REGION\": \"" + (limoHaj.get +
//				"\",\"P_IS_MAIN\": \"" + (limoHaj.get +
//				"\",\"CustomerBranchId\": \"" + (limoHaj.get +
//				"\",\"P_LINE1\": \"" + (limoHaj.get +
//				"\",\"P_LINE2\": \"" + (limoHaj.get +
//				"\",\"P_CITY\": \"" + (limoHaj.get +
//				"\",\"P_COUNTRY\": \"" + (limoHaj.get +
//				"\",\"BatchNumber\": \"" + batchNumber +
//				"\",\"CustomerVATRegistrationNo\": \"" + (limoHaj.get +
//				"\",\"DiscountVATAmount\": \"" + (limoHaj.get +
//				"\",\"RentVATAmount\": \"" + (limoHaj.get +
//				"\",\"AdditionalVATAmount\": \"" + (limoHaj.get +
//				"\",\"AgentCommissionVATAmount\": \"" + (limoHaj.get +
//				"\",\"DeductionVATAmount\": \"" + (limoHaj.get +
//				"\",\"ContractStartDate\": \"" + (limoHaj.get +
//				"\",\"TaxCountry\": \"" + (limoHaj.get +
//				"\",\"RefundType\": \"" + (limoHaj.get +
//				"\",\"ReceiptsNo\": [ \"" + (limoHaj.getReceiptsNo() != null? limoHaj.getReceiptsNo().split(",") : "") + "\"]}";
		}else{
			input = "{\"InvoiceNumber\": \"" + (limoHaj.getTicketNumber() != null? limoHaj.getTicketNumber() : "") +
					"\",\"Service\": \"" + (limoHaj.getServiceCode() != null? limoHaj.getServiceCode() : "") +
					"\",\"ServiceRegionCode\": \"" + (limoHaj.getServiceRegion() != null? limoHaj.getServiceRegion() : "") +
					"\",\"ServiceDate\": \"" + (limoHaj.getServiceDate() != null? limoHaj.getServiceDate() : "") +
					"\",\"TransacationType\": \"" + (limoHaj.getTransactionType() != null? limoHaj.getTransactionType() : "") +
					"\",\"TotalAmount\": \"" + (limoHaj.getTotalAmount() != null? limoHaj.getTotalAmount() : "") +
					"\",\"DiscountAmount\": \"" + (limoHaj.getDiscountAmount() != null? limoHaj.getDiscountAmount() : "") +
					"\",\"DeductionAmount\": \"" + (limoHaj.getDeductionAmount() != null? limoHaj.getDeductionAmount() : "") +
					"\",\"AdditionalAmount\": \"" + (limoHaj.getAdditionalAmount() != null? limoHaj.getAdditionalAmount() : "") +
					"\",\"AgentCommissionAmount\": \"" + (limoHaj.getAgentCommAmount() != null? limoHaj.getAgentCommAmount() : "") +
					"\",\"CustomerVATRegistrationNo\": \"" + (limoHaj.getCustomerVat() != null? limoHaj.getCustomerVat() : "") +
					"\",\"VATAmount\": \"" + (limoHaj.getTaxAmount() != null? limoHaj.getTaxAmount() : "") +
					"\",\"DiscountVATAmount\": \"" + (limoHaj.getDiscountTax() != null? limoHaj.getDiscountTax() : "") +
					"\",\"DeductionVATAmount\": \"" + (limoHaj.getDeductionTax() != null? limoHaj.getDeductionTax() : "") +
					"\",\"AdditionalVATAmount\": \"" + (limoHaj.getAdditionalTax() != null? limoHaj.getAdditionalTax() : "") +
					"\",\"AgentCommissionVATAmount\": \"" + (limoHaj.getAgentCommTax() != null? limoHaj.getAgentCommTax() : "") +
					"\",\"ContractStartDate\": \"" + (limoHaj.getContractStartDate() != null? limoHaj.getContractStartDate() : "") +
					"\",\"TaxCountry\": \"" + (limoHaj.getTaxCountry() != null? limoHaj.getTaxCountry() : "") +
					"\",\"RefundType\": \"" + (limoHaj.getInvoiceType() != null? limoHaj.getInvoiceType() : "") +
					"\",\"ReceiptsNo\": [ \"" + (limoHaj.getReceiptsNo() != null? limoHaj.getReceiptsNo() : "") +
					"\"],\"CustomerId\": \"" + (limoHaj.getSourcePartyId() != null? limoHaj.getSourcePartyId() : "") +
					"\",\"CustomerName\": \"" + (limoHaj.getSourcePartyName() != null? limoHaj.getSourcePartyName() : "") +
					"\",\"CustomerRegion\": \"" + (limoHaj.getCustomerRegion() != null? limoHaj.getCustomerRegion() : "") +
					"\",\"IsMain\": \"" + (limoHaj.getIsMain() != null && limoHaj.getIsMain().equals("1") ? "true" : "false") +
					"\",\"CustomerBranchId\": \"" + (limoHaj.getSourceAddr() != null? limoHaj.getSourceAddr() : "") +
					"\",\"Line1\": \"" + (limoHaj.getLine1() != null? limoHaj.getLine1() : "") +
					"\",\"Line2\": \"" + (limoHaj.getLine2() != null? limoHaj.getLine2() : "") +
					"\",\"City\": \"" + (limoHaj.getCity() != null? limoHaj.getCity() : "") +
					"\",\"CountryCode\": \"" + (limoHaj.getCountry() != null? limoHaj.getCountry() : "") +
					"\",\"OrderType\": \"" + (limoHaj.getSourceType() != null? limoHaj.getSourceType() : "") +
					"\",\"PaymentDueType\": \"" + (limoHaj.getTermsType() != null? limoHaj.getTermsType() : "") +
					"\",\"FreeAmount\": \"" + (limoHaj.getFreeAmount() != null? limoHaj.getFreeAmount() : "") +
					"\",\"BatchNumber\": \"" + (batchNumber != null? batchNumber : "") + "\"}";
		}
		OutputStream os = conn.getOutputStream();
		os.write(input.getBytes("UTF-8"));
		os.flush();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpURLConnection.HTTP_OK){
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String loopVar = null;
			StringBuilder sb = new StringBuilder();
	        while ((loopVar = br.readLine()) != null) {
	        	sb = sb.append(loopVar+"\n");
			}
			JSONObject resultList = new JSONObject(sb.toString());
			if(resultList != null){
				String status = resultList.getString("StatusCode");
	        	if(status != null && status.equals("000000"))
	        		returnStatus = true;
			}
		}
		conn.disconnect();
		return returnStatus;
	} 
	
	private Boolean closeBatch(Long batchNumber, Boolean isNewService, int totalRecords,Double totalAmount,List<LimoHajBean> integratedList) throws Exception{
		Boolean returnStatus = false;
		WSConfigDTO wsConfigDTO = null;
		if(commonDao != null)
			wsConfigDTO = commonDao.getLimoHajFinIntegWSConfig(3, isNewService);
//		else{
//			wsConfigDTO = loadDto(isNewService,3);
//		}
		DecimalFormat df = new DecimalFormat("#.##");
		URL url = new URL(wsConfigDTO.getuRL() + "/" + batchNumber + "/" + totalRecords + "/" + df.format(totalAmount) + "/");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json");
		conn.setRequestProperty("Authorization","SAPTCO u=\"" + wsConfigDTO.getUserName() + "\",p=\"" + wsConfigDTO.getPassword() + "\"");
		OutputStream os = conn.getOutputStream();
		os.flush();
		if (conn.getResponseCode() == HttpURLConnection.HTTP_CREATED || conn.getResponseCode() == HttpURLConnection.HTTP_OK){
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
			String loopVar = null;
			StringBuilder sb = new StringBuilder();
	        while ((loopVar = br.readLine()) != null) {
	        	sb = sb.append(loopVar+"\n");
			}
			JSONObject resultList = new JSONObject(sb.toString());
			if(resultList != null){
				JSONArray resultArray = (JSONArray) resultList.get("Items");
				JSONObject resultObj = (JSONObject) resultArray.get(0);
				if(resultObj != null){
			        String status = resultObj.getString("ErrorCode");
		        	if(status != null && status.equals("000000")){
		        		returnStatus = true;
		    			for(LimoHajBean limoHaj : integratedList){
		    				try{
		    					if(hajLimoDao != null)
		    						hajLimoDao.updateTicketFinIntegration(limoHaj.getTicketNumber());
//		    					else{
//		    						Connection con = getConnection();
//		    						CallableStatement stmt = con.prepareCall("{ call LIMO_HAJ_INTEG_PKG.UPDATE_TICKET_DATA (?,?,?) }");
//		    						stmt.setString(1,limoHaj.getTicketNumber());
//		    						stmt.registerOutParameter(2,java.sql.Types.VARCHAR);
//		    						stmt.registerOutParameter(3,java.sql.Types.VARCHAR);
//		    						stmt.executeUpdate();
//		    					}
			    				logger.error("Limo Haj job update ticket " + limoHaj.getTicketNumber());
		    				}catch(Exception e){
		    					logger.error("Limo Haj job update ticket " + limoHaj.getTicketNumber() + " has been failed");
		    				}
		    			}
		        	}
				}
			}
		}
		conn.disconnect();
		return returnStatus;
	}

	public HajLimoDao getHajLimoDao() {
		return hajLimoDao;
	}

	public void setHajLimoDao(HajLimoDao hajLimoDao) {
		this.hajLimoDao = hajLimoDao;
	}

	public CommonDao getCommonDao() {
		return commonDao;
	}

	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
}