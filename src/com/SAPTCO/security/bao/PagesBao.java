package com.SAPTCO.security.bao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.SystemBean;
import com.SAPTCO.security.dto.PagesDto;

/**
 * @author Shady
*/

public interface PagesBao{
	
	public PagesDto getPagesByCode(PagesDto pagesDto) throws Exception;
	public void insertPage(PagesDto pagesDto) throws Exception;
	public void updatePage(PagesDto pagesDto) throws Exception;
	public PagesDto deletePage(PagesDto pagesDto) throws Exception;
	public PagesDto getParentPage() throws Exception;
	public PagesDto getAllPrivelege(PagesDto pagesDto) throws Exception;
	public PagesDto getCurrentPrivelege(PagesDto pagesDto) throws Exception;
	public PagesDto updatePrivelege(PagesDto pagesDto) throws Exception;
	public List<SystemBean> getSystemsList() throws Exception;
	
}