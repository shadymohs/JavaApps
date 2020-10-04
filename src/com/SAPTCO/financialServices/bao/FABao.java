package com.SAPTCO.financialServices.bao;

import java.util.List;

import com.SAPTCO.common.ibatis.mapperBeans.FixedAssets;
import com.SAPTCO.financialServices.dto.FADto;

/**
 * @author Shady
 *
 */
public interface FABao{
	
	public List<FixedAssets> getFixedAssets(FADto faDto) throws Exception;
	public void excuteReceipts(String receiptNo) throws Exception;
}
