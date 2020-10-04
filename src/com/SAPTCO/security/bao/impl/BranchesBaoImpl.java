package com.SAPTCO.security.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.security.bao.BranchesBao;
import com.SAPTCO.security.dao.BranchesDao;
import com.SAPTCO.security.dto.BranchesDto;

/**
 * @author Shady
*/

@Service("branchesBao")
public class BranchesBaoImpl implements BranchesBao {

	@Autowired
	private BranchesDao branchesDao;

	public BranchesDto getBranchesByCode(BranchesDto branchesDto) throws Exception {
		return branchesDao.getBranchesByCode(branchesDto);
	}

	public void insertBranch(BranchesDto branchesDto) throws Exception {
		branchesDao.insertBranch(branchesDto);		
	}

	public void updateBranch(BranchesDto branchesDto) throws Exception {
		branchesDao.updateBranch(branchesDto);
	}

	public void deleteBranch(BranchesDto branchesDto) throws Exception {
		branchesDao.deleteBranch(branchesDto);		
	}

	public BranchesDao getBranchesDao() {
		return branchesDao;
	}

	public void setBranchesDao(BranchesDao branchesDao) {
		this.branchesDao = branchesDao;
	}

	@Override
	public List<SystemBean> getSystemsList() throws Exception {
		return branchesDao.getSystemsList();
	}
	


}