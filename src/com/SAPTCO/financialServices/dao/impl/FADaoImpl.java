package com.SAPTCO.financialServices.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;
import com.SAPTCO.common.ibatis.mapperBeans.FixedAssets;
import com.SAPTCO.financialServices.dao.FADao;
import com.SAPTCO.financialServices.dto.FADto;


@Repository("fADao")
public class FADaoImpl implements FADao{
	
	@Autowired
    private SqlMapClientTemplate sqlMapClientTemplateFA;

	@SuppressWarnings("unchecked")
	@Override
	public List<FixedAssets> getFixedAssets(FADto faDto) throws Exception {
		return sqlMapClientTemplateFA.queryForList("getFixedAssets",faDto);
	}

	@Override
	public void excuteReceipts(String receiptNo) throws Exception {
		sqlMapClientTemplateFA.queryForObject("excuteReceipts",receiptNo);
	}

	public SqlMapClientTemplate getSqlMapClientTemplateFA() {
		return sqlMapClientTemplateFA;
	}

	public void setSqlMapClientTemplateFA(SqlMapClientTemplate sqlMapClientTemplateFA) {
		this.sqlMapClientTemplateFA = sqlMapClientTemplateFA;
	}
	
}
