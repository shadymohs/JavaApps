package com.SAPTCO.security.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.security.bao.PagesBao;
import com.SAPTCO.security.dao.PagesDao;
import com.SAPTCO.security.dto.PagesDto;

/**
 * @author Shady
*/

@Service("pagesBao")
public class PagesBaoImpl implements PagesBao {

	@Autowired
	private PagesDao pagesDao;

	public PagesDto getPagesByCode(PagesDto pagesDto) throws Exception {
		return pagesDao.getPagesByCode(pagesDto);
	}

	public void insertPage(PagesDto pagesDto) throws Exception {
		pagesDao.insertPage(pagesDto);		
	}

	public void updatePage(PagesDto pagesDto) throws Exception {
		pagesDao.updatePage(pagesDto);
	}

	public PagesDto deletePage(PagesDto pagesDto) throws Exception {
		return pagesDao.deletePage(pagesDto);		
	}

	public PagesDao getPagesDao() {
		return pagesDao;
	}

	public void setPagesDao(PagesDao pagesDao) {
		this.pagesDao = pagesDao;
	}

	public PagesDto getParentPage() throws Exception {
		return pagesDao.getParentPage();
	}

	public PagesDto getAllPrivelege(PagesDto pagesDto) throws Exception {
		return pagesDao.getAllPrivelege(pagesDto);
	}

	public PagesDto getCurrentPrivelege(PagesDto pagesDto) throws Exception {
		return pagesDao.getCurrentPrivelege(pagesDto);
	}

	public PagesDto updatePrivelege(PagesDto pagesDto) throws Exception {
		return pagesDao.updatePrivelege(pagesDto);
	}

	@Override
	public List<SystemBean> getSystemsList() throws Exception {
		return pagesDao.getSystemsList();
	}

}