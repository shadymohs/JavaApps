package com.SAPTCO.financialServices.dao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.FixedAssets;
import com.SAPTCO.financialServices.dto.FADto;

public interface FADao {
	
	public List<FixedAssets> getFixedAssets(FADto faDto) throws Exception;
	public void excuteReceipts(String receiptNo) throws Exception;
	
}
