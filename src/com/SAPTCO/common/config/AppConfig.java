package com.SAPTCO.common.config;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;

@Configuration
public class AppConfig {
	
	@Autowired
	DataSource dataSource;

	@Autowired
	Reader sqlMapConfigReader;

	@Bean
	public SqlSessionFactory sqlSessionFactoryBuilder() throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		return builder.build(sqlMapConfigReader);
	}

	@Bean
	@Scope("prototype")
	public SqlSession sqlSession() throws DataAccessException {
		try {
			return sqlSessionFactoryBuilder().openSession(dataSource.getConnection());
		} catch (SQLException e) {
			throw new DataSourceLookupFailureException("Unable to open session");
		} catch (IOException e) {
			throw new DataSourceLookupFailureException("Error opening sqlconfig");
		}
	}
}
