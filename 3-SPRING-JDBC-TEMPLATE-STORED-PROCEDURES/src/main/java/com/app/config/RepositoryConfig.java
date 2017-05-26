package com.app.config;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@PropertySource("classpath:application.properties")
public class RepositoryConfig {

	Logger logger = Logger.getLogger(getClass());

	public RepositoryConfig() {
		super();
		// TODO Auto-generated constructor stub
		logger.info("***RepositoryConfig()");
	}

	// ${jdbc.driverClassName}
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		logger.info("***Datasource as bean");
		return ds;
	}

	@Bean
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		logger.info("***JdbcTemplate as bean");
		return new JdbcTemplate(dataSource);
	}

	@Bean
	public NamedParameterJdbcTemplate geNamedParameterJdbcTemplate(
			DataSource dataSource) {
		logger.info("***NamedParameterJdbcTemplate as bean");
		return new NamedParameterJdbcTemplate(dataSource);
	}

}
