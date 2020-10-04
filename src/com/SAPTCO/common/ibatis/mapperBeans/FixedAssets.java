package com.SAPTCO.common.ibatis.mapperBeans;

import com.SAPTCO.common.dto.BaseDto;

/**
 * @author Shady
*/

public class FixedAssets extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	private String userFullName;
	private String userType;
	private Long empPosition;
	private String hireDate;
	private String terminationDate;
	private String userMail;
	private String mobNum;
	private String mobNum2;
	private String hrBranch;
	private String hrPosition;	
	private String hrDepartment;	
	private String hrReligion;
	private String hrBranchCode;
	private String hrJobCode;	
	private String hrJobDesc;	
	private String hrSectorCode;
	private String hrSectorDesc;
	private String posCode;

	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Long getEmpPosition() {
		return empPosition;
	}
	public void setEmpPosition(Long empPosition) {
		this.empPosition = empPosition;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public String getMobNum() {
		return mobNum;
	}
	public void setMobNum(String mobNum) {
		this.mobNum = mobNum;
	}
	public String getMobNum2() {
		return mobNum2;
	}
	public void setMobNum2(String mobNum2) {
		this.mobNum2 = mobNum2;
	}
	public String getHrBranch() {
		return hrBranch;
	}
	public void setHrBranch(String hrBranch) {
		this.hrBranch = hrBranch;
	}
	
	@Override
	public String getLabel() {
		return getId() + "-" + userFullName;
	}
	
	public String getUserTypeString(){
		if(userType != null && userType.equals("E"))
			return "";
		else
			return "";
	}
	public String getPosCode() {
		return posCode;
	}
	public void setPosCode(String posCode) {
		this.posCode = posCode;
	}
	public String getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	public String getHrPosition() {
		return hrPosition;
	}
	public void setHrPosition(String hrPosition) {
		this.hrPosition = hrPosition;
	}
	public String getHrDepartment() {
		return hrDepartment;
	}
	public void setHrDepartment(String hrDepartment) {
		this.hrDepartment = hrDepartment;
	}
	public String getHrReligion() {
		return hrReligion;
	}
	public void setHrReligion(String hrReligion) {
		this.hrReligion = hrReligion;
	}
	public String getHrBranchCode() {
		return hrBranchCode;
	}
	public void setHrBranchCode(String hrBranchCode) {
		this.hrBranchCode = hrBranchCode;
	}
	public String getHrJobCode() {
		return hrJobCode;
	}
	public void setHrJobCode(String hrJobCode) {
		this.hrJobCode = hrJobCode;
	}
	public String getHrJobDesc() {
		return hrJobDesc;
	}
	public void setHrJobDesc(String hrJobDesc) {
		this.hrJobDesc = hrJobDesc;
	}
	public String getHrSectorCode() {
		return hrSectorCode;
	}
	public void setHrSectorCode(String hrSectorCode) {
		this.hrSectorCode = hrSectorCode;
	}
	public String getHrSectorDesc() {
		return hrSectorDesc;
	}
	public void setHrSectorDesc(String hrSectorDesc) {
		this.hrSectorDesc = hrSectorDesc;
	}
}
