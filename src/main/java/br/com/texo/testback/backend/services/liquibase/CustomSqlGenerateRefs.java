package br.com.texo.testback.backend.services.liquibase;

import static br.com.texo.testback.backend.services.liquibase.LiquibaseUtils.splitStringList;
import static java.util.stream.Collectors.joining;

import java.sql.SQLException;
import java.util.ArrayList;
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

public class CustomSqlGenerateRefs implements CustomSqlChange {
	private final String regex = "(?=and|,)";
	
	private final String mainSql = ""
			+ "select MOVIE.ID,"
			+ "       case when PRODUCER.ID is null then ORIGIN.PRODUCERS else null end PRODUCERS,"
			+ "       case when STUDIO.ID is null then ORIGIN.STUDIOS else null end STUDIOS,"
			+ "       PRODUCER.ID ID_PRODUCER,"			
			+ "       STUDIO.ID ID_STUDIO"
			+ "  from ORIGIN"
			+ " inner join MOVIE on MOVIE.TITLE = origin.TITLE"
			+ "  left join PRODUCER on PRODUCER.NAME = ORIGIN.PRODUCERS"
			+ "  left join STUDIO on STUDIO.NAME = ORIGIN.STUDIOS";
	
	@Override
	public SqlStatement[] generateStatements(Database database) throws CustomChangeException {
		var connection = (JdbcConnection) database.getConnection();
		
		var queries = new ArrayList<String>();
		
		try (var stm = connection.createStatement()) {
			var rs = stm.executeQuery(mainSql);
			
			while (rs.next()) {
				var vlModieId = rs.getLong("ID");
				var vlIdProducer = rs.getLong("ID_PRODUCER");
				var vlIdStudio = rs.getLong("ID_STUDIO");
				var vlStudios = rs.getString("STUDIOS");
				var vlProducers = rs.getString("PRODUCERS");
				
				resolveMany(queries, connection, vlModieId, "STUDIO", vlStudios, vlIdStudio);
				
				resolveMany(queries, connection, vlModieId, "PRODUCER", vlProducers, vlIdProducer);
			}
		} catch (DatabaseException | SQLException e) {
			e.printStackTrace();
		}
		
		var statements = queries.stream().map(it -> new RawSqlStatement(it)).collect(Collectors.toList());
		
		return statements.toArray(new SqlStatement[] {});
		
//		return new SqlStatement[] {};
	}
	
	private void resolveMany(ArrayList<String> queries, JdbcConnection cc, Long movieId, String tableName, String allNames, Long id) throws SQLException, DatabaseException {
		try (var stm = cc.createStatement()) {
			var ids = new ArrayList<Long>();
			
			if (!id.equals(0L)) {
				ids.add(id);
			} else {
				var names = splitStringList(allNames).stream().collect(joining("','"));
				
				var rs = stm.executeQuery("select ID from " + tableName + " where NAME in ('" + names + "')");
				
				while (rs.next()) {
					ids.add(rs.getLong(1));
				}
			}
			
			ids.stream().forEach(it -> {
				queries.add("insert into " + tableName + "_MOVIE(ID_MOVIE,ID_" + tableName + ") values (" + movieId + "," + it + ")");
			});
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
