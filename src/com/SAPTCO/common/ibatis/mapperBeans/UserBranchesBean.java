package com.SAPTCO.common.ibatis.mapperBeans;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.SAPTCO.common.config.SystemConstants;
import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class UserBranchesBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private UserBean userId = new UserBean();
	private BranchesBean branchId = new BranchesBean();
	private ProgramGroupsBean programId = new ProgramGroupsBean();
	private Date fromDate;
	private Date toDate;
	private String fromDateString;
	private String toDateString;
	
	public UserBean getUserId() {
		return userId;
	}
	public void setUserId(UserBean userId) {
		this.userId = userId;
	}
	public BranchesBean getBranchId() {
		return branchId;
	}
	public void setBranchId(BranchesBean branchId) {
		this.branchId = branchId;
	}
	public ProgramGroupsBean getProgramId() {
		return programId;
	}
	public void setProgramId(ProgramGroupsBean programId) {
		this.programId = programId;
	}
	public Date getFromDate() {
		return fromDate;
	}
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}
	public Date getToDate() {
		return toDate;
	}
	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
	public String getFromDateString() {
		return fromDateString;
	}
	public void setFromDateString(String fromDateString) {
		try {
			if(fromDateString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				fromDate = df.parse(fromDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.fromDateString = fromDateString;
	}
	public String getToDateString() {
		return toDateString;
	}
	public void setToDateString(String toDateString) {
		try {
			if(toDateString != null){
				SimpleDateFormat df = new SimpleDateFormat(SystemConstants.Date_Format);
				toDate = df.parse(toDateString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.toDateString = toDateString;
	}
	
}
