package com.SAPTCO.reports.dao.daoImpl;

import java.sql.Connection;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.SAPTCO.reports.dao.ReportDao;

/**
 * @author Shady
 */

@Repository("reportDAO")
public class ReportDaoImpl implements ReportDao {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private DataSource dataSourceCostAnaysis;
	@Autowired
	private DataSource dataSourceTR;
	@Autowired
	private DataSource dataSourceLuggage;
	@Autowired
	private DataSource dataSourceFA;
	@Autowired
	private DataSource dataSourceUrbanFest;
	@Autowired
	private DataSource dataSourceHajLimo;

	public Connection getConnectionDetails(int systemId) throws Exception {
		if(systemId == 1)
			return dataSource.getConnection();
		else if(systemId == 2)
			return dataSourceCostAnaysis.getConnection();
		else if(systemId == 3)
			return dataSourceTR.getConnection();
		else if(systemId == 4)
			return dataSourceLuggage.getConnection();
		else if(systemId == 5)
			return dataSourceFA.getConnection();
		else if(systemId == 6)
			return dataSourceUrbanFest.getConnection();
		else
			return dataSourceHajLimo.getConnection();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public DataSource getDataSourceCostAnaysis() {
		return dataSourceCostAnaysis;
	}

	public void setDataSourceCostAnaysis(DataSource dataSourceCostAnaysis) {
		this.dataSourceCostAnaysis = dataSourceCostAnaysis;
	}

	public DataSource getDataSourceTR() {
		return dataSourceTR;
	}

	public void setDataSourceTR(DataSource dataSourceTR) {
		this.dataSourceTR = dataSourceTR;
	}

	public DataSource getDataSourceLuggage() {
		return dataSourceLuggage;
	}

	public void setDataSourceLuggage(DataSource dataSourceLuggage) {
		this.dataSourceLuggage = dataSourceLuggage;
	}

	public DataSource getDataSourceFA() {
		return dataSourceFA;
	}

	public void setDataSourceFA(DataSource dataSourceFA) {
		this.dataSourceFA = dataSourceFA;
	}

	public DataSource getDataSourceUrbanFest() {
		return dataSourceUrbanFest;
	}

	public void setDataSourceUrbanFest(DataSource dataSourceUrbanFest) {
		this.dataSourceUrbanFest = dataSourceUrbanFest;
	}

	public DataSource getDataSourceHajLimo() {
		return dataSourceHajLimo;
	}

	public void setDataSourceHajLimo(DataSource dataSourceHajLimo) {
		this.dataSourceHajLimo = dataSourceHajLimo;
	}
}
