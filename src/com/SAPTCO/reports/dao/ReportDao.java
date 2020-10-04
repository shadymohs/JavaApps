package com.SAPTCO.reports.dao;

import java.sql.Connection;

public interface ReportDao {

	public Connection getConnectionDetails(int systemId) throws Exception ;
	
}
