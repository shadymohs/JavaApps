package com.SAPTCO.financialServices.bao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SAPTCO.common.ibatis.mapperBeans.FixedAssets;
import com.SAPTCO.financialServices.bao.FABao;
import com.SAPTCO.financialServices.dao.FADao;
import com.SAPTCO.financialServices.dto.FADto;


/**
 * @author Shady
 *
 */
@Service("fABao")
public class FABaoImpl implements FABao{
	
	@Autowired
	private FADao faDao;

	@Override
	public List<FixedAssets> getFixedAssets(FADto faDto) throws Exception {
		return faDao.getFixedAssets(faDto);
	}

	@Override
	public void excuteReceipts(String receiptNo) throws Exception {
		faDao.excuteReceipts(receiptNo);
	}

	public FADao getFaDao() {
		return faDao;
	}

	public void setFaDao(FADao faDao) {
		this.faDao = faDao;
	}
	
}