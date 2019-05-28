package com.excilys.training.persistance.h2database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;


@Component
public class TestDatabase {
	private final DataSource dataSource;
	
	public TestDatabase(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void executeScript(String filename) throws SQLException, IOException {
		try (final Connection connection = dataSource.getConnection();
				final Statement statement = connection.createStatement();
				final InputStream resourceAsStream = TestDatabase.class.getClassLoader().getResourceAsStream(filename);
				final Scanner scanner = new Scanner(resourceAsStream)) {

			StringBuilder sb = new StringBuilder();
			while (scanner.hasNextLine()) {
				final String nextLine = scanner.nextLine();
				sb.append(nextLine);
			}
			final StringTokenizer stringTokenizer = new StringTokenizer(sb.toString(), ";");

			while (stringTokenizer.hasMoreTokens()) {
				final String nextToken = stringTokenizer.nextToken();
				statement.execute(nextToken);
			}
		}
		catch (Throwable e){
			e.printStackTrace();
		}
	}

	public void reload() throws IOException, SQLException {
		executeScript("schema.sql");
		executeScript("entries.sql");
	}

}
