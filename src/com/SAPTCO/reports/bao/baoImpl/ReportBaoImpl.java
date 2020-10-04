package com.SAPTCO.reports.bao.baoImpl;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SAPTCO.reports.bao.ReportBao;
import com.SAPTCO.reports.dao.ReportDao;

/**
 * @author Shady
 */

@Service("reportBao")
public class ReportBaoImpl implements ReportBao {

	@Autowired
	private ReportDao reportDao;

	public ReportDao getReportDao() {
		return reportDao;
	}

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}

	public Connection getConnectionDetails(int systemId) throws Exception {
		return reportDao.getConnectionDetails(systemId);
	}
}
