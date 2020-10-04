package com.SAPTCO.reports.bao;

import java.sql.Connection;

/**
 * @author Shady
 */

public interface ReportBao {
	public static final String JASPER_REPORT_EXT = ".jasper";

	public Connection getConnectionDetails(int systemId) throws Exception;

}
