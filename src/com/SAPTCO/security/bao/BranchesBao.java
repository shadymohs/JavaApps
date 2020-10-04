package com.SAPTCO.security.bao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.security.dto.BranchesDto;

/**
 * @author Shady
*/

public interface BranchesBao{
	
	public BranchesDto getBranchesByCode(BranchesDto branchesDto) throws Exception;
	public void insertBranch(BranchesDto branchesDto) throws Exception;
	public void updateBranch(BranchesDto branchesDto) throws Exception;
	public void deleteBranch(BranchesDto branchesDto) throws Exception;
	public List<SystemBean> getSystemsList() throws Exception;
	
}