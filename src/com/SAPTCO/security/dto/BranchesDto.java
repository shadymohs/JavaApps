package com.SAPTCO.security.dto;

import java.util.ArrayList;
import java.util.List;
import com.SAPTCO.common.dto.BaseDto;
import com.SAPTCO.common.ibatis.mapperBeans.BranchesBean;
 
/**
 * @author Shady
*/

public class BranchesDto extends BaseDto{

	private static final long serialVersionUID = 1L;
	private List<BranchesBean> branchesList = new ArrayList<BranchesBean>();
	private BranchesBean branchObj = new BranchesBean();
	private String codeDesc;
	private String systemId;
	
	public List<BranchesBean> getBranchesList() {
		return branchesList;
	}
	public void setBranchesList(List<BranchesBean> branchesList) {
		this.branchesList = branchesList;
	}	
	public String getCodeDesc() {
		return codeDesc;
	}
	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}
	public BranchesBean getBranchObj() {
		return branchObj;
	}
	public void setBranchObj(BranchesBean branchObj) {
		this.branchObj = branchObj;
	}
	public String getSystemId() {
		return systemId;
	}
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}