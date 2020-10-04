package com.SAPTCO.common.ibatis.mapperBeans;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class UserBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private Boolean deleted;
	private UserInfBean userInf = new UserInfBean();
	private BranchesBean loggedInBranch = new BranchesBean();
	private SystemBean loggedInSystem = new SystemBean();
	private Date expireDate;
	private String expireDateString;
	private CompanyBean company = new CompanyBean();
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}
	public UserInfBean getUserInf() {
		return userInf;
	}
	public void setUserInf(UserInfBean userInf) {
		this.userInf = userInf;
	}
	public BranchesBean getLoggedInBranch() {
		return loggedInBranch;
	}
	public void setLoggedInBranch(BranchesBean loggedInBranch) {
		this.loggedInBranch = loggedInBranch;
	}
	public SystemBean getLoggedInSystem() {
		return loggedInSystem;
	}
	public void setLoggedInSystem(SystemBean loggedInSystem) {
		this.loggedInSystem = loggedInSystem;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getExpireDateString() {
		return expireDateString;
	}
	public void setExpireDateString(String expireDateString) {
		try {
			if(expireDateString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				expireDate = df.parse(expireDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.expireDateString = expireDateString;
	}
	public CompanyBean getCompany() {
		return company;
	}
	public void setCompany(CompanyBean company) {
		this.company = company;
	}
	
}
