package com.SAPTCO.common.ibatis.mapperBeans;

import java.util.ArrayList;
import java.util.List;

import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.KAUSTAllowanceTypeBean;

/**
 * @author Shady
*/

public class DriversBean extends BaseDto{

	private static final long serialVersionUID = 1L;
	private BranchesBean branchId = new BranchesBean();
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
	private Float dailyRate;
	private List<KAUSTdriver> kaustExceptions = new ArrayList<KAUSTdriver>();
	private KAUSTdriver driverKAUST = new KAUSTdriver();

	private KAUSTAllowanceTypeBean KAUSTAllowanceType= new KAUSTAllowanceTypeBean();
	private KaustAdditionalAllow kaustAdditionalAllow = new KaustAdditionalAllow();
	private List<KaustAdditionalAllow> kaustAdditionals = new ArrayList<KaustAdditionalAllow>();
	
	
	public BranchesBean getBranchId() {
		return branchId;
	}
	public void setBranchId(BranchesBean branchId) {
		this.branchId = branchId;
	}
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
	public Float getDailyRate() {
		return dailyRate;
	}
	public void setDailyRate(Float dailyRate) {
		this.dailyRate = dailyRate;
	}
	public List<KAUSTdriver> getKaustExceptions() {
		return kaustExceptions;
	}
	public void setKaustExceptions(List<KAUSTdriver> kaustExceptions) {
		this.kaustExceptions = kaustExceptions;
	}
	public KAUSTdriver getDriverKAUST() {
		return driverKAUST;
	}
	public void setDriverKAUST(KAUSTdriver driverKAUST) {
		this.driverKAUST = driverKAUST;
	}
	
	public KAUSTAllowanceTypeBean getKAUSTAllowanceType() {
		return KAUSTAllowanceType;
	}
	public void setKAUSTAllowanceType(KAUSTAllowanceTypeBean kAUSTAllowanceType) {
		KAUSTAllowanceType = kAUSTAllowanceType;
	}
	public KaustAdditionalAllow getKaustAdditionalAllow() {
		return kaustAdditionalAllow;
	}
	public void setKaustAdditionalAllow(KaustAdditionalAllow kaustAdditionalAllow) {
		this.kaustAdditionalAllow = kaustAdditionalAllow;
	}
	public List<KaustAdditionalAllow> getKaustAdditionals() {
		return kaustAdditionals;
	}
	public void setKaustAdditionals(List<KaustAdditionalAllow> kaustAdditionals) {
		this.kaustAdditionals = kaustAdditionals;
	}
	
	
	
	

}
