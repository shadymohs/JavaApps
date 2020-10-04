package com.SAPTCO.security.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.ibatis.mapperInterface.General;
import com.SAPTCO.common.ibatis.mapperInterface.Security;
import com.SAPTCO.security.dao.BranchesDao;
import com.SAPTCO.security.dto.BranchesDto;

/**
 * @author Shady
 */

@Repository("branchesDao")
public class BranchesDaoImpl  implements BranchesDao{
	
	@Autowired
	SqlSession session;

	public BranchesDto getBranchesByCode(BranchesDto branchesDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		branchesDto.setBranchesList(mapper.getBranchesByCode(branchesDto));
		return branchesDto;
	}
	
	public void insertBranch(BranchesDto branchesDto) throws Exception {
		branchesDto.getBranchObj().setCreatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.insertBranch(branchesDto);
	}
	
	public void updateBranch(BranchesDto branchesDto) throws Exception {
		branchesDto.getBranchObj().setUpdatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.updateBranch(branchesDto);
	}

	public void deleteBranch(BranchesDto branchesDto) throws Exception {
		Security mapper = session.getMapper(Security.class);
		mapper.deleteBranch(branchesDto);
	}

	@Override
	public List<SystemBean> getSystemsList() throws Exception {
		session.clearCache();
		General mapper = session.getMapper(General.class);
		return mapper.getSystemsList(BaseBB.getUserDetails().getCompany().getId());
	}
	
}
