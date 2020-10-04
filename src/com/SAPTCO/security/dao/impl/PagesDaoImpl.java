package com.SAPTCO.security.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.SAPTCO.common.backingBean.BaseBB;
import com.SAPTCO.common.ibatis.mapperBeans.PrivilegeBean;
import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.common.ibatis.mapperInterface.General;
import com.SAPTCO.common.ibatis.mapperInterface.Security;
import com.SAPTCO.security.dao.PagesDao;
import com.SAPTCO.security.dto.PagesDto;

/**
 * @author Shady
 */

@Repository("pagesDao")
public class PagesDaoImpl  implements PagesDao{
	
	@Autowired
	SqlSession session;

	public PagesDto getPagesByCode(PagesDto pagesDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		pagesDto.setSystemMenuList(mapper.getPagesByCode(pagesDto));
		return pagesDto;
	}
	
	public void insertPage(PagesDto pagesDto) throws Exception {
			pagesDto.getPageObj().setCreatedBy(BaseBB.getUserDetails().getId());
			Security mapper = session.getMapper(Security.class);
			mapper.insertPage(pagesDto);
	}
	
	public void updatePage(PagesDto pagesDto) throws Exception {
		pagesDto.getPageObj().setUpdatedBy(BaseBB.getUserDetails().getId());
		Security mapper = session.getMapper(Security.class);
		mapper.updatePage(pagesDto);
	}

	public PagesDto deletePage(PagesDto pagesDto) throws Exception {
		Security mapper = session.getMapper(Security.class);
		mapper.deletePage(pagesDto);
		return pagesDto;
	}

	public PagesDto getParentPage() throws Exception {
		PagesDto pagesDto = new PagesDto();
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		pagesDto.setSystemMenuList(mapper.getParentPage());
		return pagesDto;
	}

	public PagesDto getAllPrivelege(PagesDto pagesDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		pagesDto.setPrivelegeList((mapper.getAllPagePrivelege(pagesDto)));
		return pagesDto;
	}

	public PagesDto getCurrentPrivelege(PagesDto pagesDto) throws Exception {
		session.clearCache();
		Security mapper = session.getMapper(Security.class);
		List<PrivilegeBean> currentPrivileges = mapper.getCurrentPagePrivelege(pagesDto);
		Long[] privilegesList = new Long[currentPrivileges.size()];
		for(int i = 0;i < currentPrivileges.size();i++)
			privilegesList[i] = currentPrivileges.get(i).getId();
		pagesDto.setSelectedPrivilege(privilegesList);
		return pagesDto;
	}

	public PagesDto updatePrivelege(PagesDto pagesDto) throws Exception {
		pagesDto.setCreatedBy(BaseBB.getUserDetails().getId());
		for(Long privilegeId:pagesDto.getSelectedPrivilege()){
			if(pagesDto.getSelectedPrivilegeString() != null && !pagesDto.getSelectedPrivilegeString().equals(""))
				pagesDto.setSelectedPrivilegeString(pagesDto.getSelectedPrivilegeString() +"," + privilegeId.toString());
			else
				pagesDto.setSelectedPrivilegeString(privilegeId.toString());
		}
		Security mapper = session.getMapper(Security.class);
		mapper.updatePagePrivelege(pagesDto);
		return pagesDto;
	}

	@Override
	public List<SystemBean> getSystemsList() throws Exception {
		session.clearCache();
		General mapper = session.getMapper(General.class);
		return mapper.getSystemsList(BaseBB.getUserDetails().getCompany().getId());
	}



	
}
