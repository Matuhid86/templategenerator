package com.api.readdatabase.configs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.api.readdatabase.enums.DataBase;

@Configuration
@SuppressWarnings("incomplete-switch")
public class DataSourceConfig {

	@Value("${com.api.database}")
	private DataBase database;

	@Value("${com.api.datasource.host}")
	private String host;

	@Value("${com.api.datasource.port}")
	private String port;

	@Value("${com.api.datasource.username}")
	private String username;

	@Value("${com.api.datasource.password}")
	private String password;

	@Value("${com.api.database.mysql.driverClassName}")
	private String driverClassNameMySQL;

	@Value("${com.api.database.sqlite.driverClassName}")
	private String driverClassNameSQLite;

	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(this.getUrl());
		dataSource.setDriverClassName(this.getDriverClassName());

		if (!this.username.isEmpty() && !this.password.isEmpty()) {
			dataSource.setUsername(this.username);
			dataSource.setPassword(this.password);
		}
		return dataSource;
	}

	private String getDriverClassName() {
		switch (this.database) {
		case MYSQL:
			return this.driverClassNameMySQL;
		case SQLITE:
			return this.driverClassNameSQLite;
		}
		return null;
	}

	private String getUrl() {
		switch (this.database) {
		case MYSQL:
			return "jdbc:mysql://" + this.host + ((!this.port.isEmpty()) ? ":" + this.port : "");
		case SQLITE:
			return "jdbc:sqlite:" + this.host + ((!this.port.isEmpty()) ? ":" + this.port : "");
		}
		return null;
	}
}
