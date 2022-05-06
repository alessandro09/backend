package br.com.texo.testback.backend.services.liquibase;

import static br.com.texo.testback.backend.services.liquibase.LiquibaseUtils.splitStringList;
import static java.util.stream.Collectors.joining;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.stream.Collectors;

import liquibase.change.custom.CustomSqlChange;
import liquibase.database.Database;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.CustomChangeException;
import liquibase.exception.DatabaseException;
import liquibase.exception.SetupException;
import liquibase.exception.ValidationErrors;
import liquibase.resource.ResourceAccessor;
import liquibase.statement.SqlStatement;
import liquibase.statement.core.RawSqlStatement;

public class CustomLoadStudiosAndProducers implements CustomSqlChange {
	private final String bundler = "'),('";
	
	@Override
	public SqlStatement[] generateStatements(Database database) throws CustomChangeException {
		var connection = (JdbcConnection) database.getConnection();
		
		var valuesStudio = processItems(connection, "STUDIOS");
		var valuesProducers = processItems(connection, "PRODUCERS");
		
		return new SqlStatement[] {
				new RawSqlStatement("insert into STUDIO(NAME) values ('" + valuesStudio + "')"),
				new RawSqlStatement("insert into PRODUCER(NAME) values ('" + valuesProducers + "')")
		};
	}
	
	private String processItems(JdbcConnection cc, String columnName) {
		try (var stm = cc.createStatement()) {
			var rs = stm.executeQuery("select distinct " + columnName + " from origin group by " + columnName);
			
			var list = new HashSet<String>();
			
			while (rs.next()) {
				var values = splitStringList(rs.getString(1));
				
				list.addAll(values);
			}
			
			return list.stream().collect(joining(bundler));
		} catch (DatabaseException | SQLException e) {
			throw new RuntimeException(e);
		}
	}
	@Override
	public String getConfirmationMessage() {
		return "";
	}

	@Override
	public void setUp() throws SetupException {
	}

	@Override
	public void setFileOpener(ResourceAccessor resourceAccessor) { }

	@Override
	public ValidationErrors validate(Database database) {
		return new ValidationErrors();
	}
}
