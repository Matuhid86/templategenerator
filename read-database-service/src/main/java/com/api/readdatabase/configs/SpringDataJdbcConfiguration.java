package com.api.readdatabase.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;
import org.springframework.data.relational.core.dialect.AbstractDialect;
import org.springframework.data.relational.core.dialect.Dialect;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;

import com.api.readdatabase.enums.DataBase;

@Configuration
public class SpringDataJdbcConfiguration extends AbstractJdbcConfiguration {

	@Value("${com.api.database}")
	private DataBase database;

	@Override
	public Dialect jdbcDialect(NamedParameterJdbcOperations operations) {
		AbstractDialect dialect = this.getDialect();

		if (dialect == null)
			return super.jdbcDialect(operations);

		return dialect;
	}

	private AbstractDialect getDialect() {
		if (this.database == DataBase.SQLITE)
			return SQLiteDialect.INSTANCE;
		return null;
	}
}
