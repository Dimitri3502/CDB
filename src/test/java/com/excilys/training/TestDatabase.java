package com.excilys.training;


import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import com.excilys.training.dao.DbConn;

public class TestDatabase {

    private static TestDatabase instance = new TestDatabase();

    private TestDatabase() {
    }

    public static TestDatabase getInstance() {
        return instance;
    }

    private static void executeScript(String filename) throws SQLException, IOException {
        try (final Connection connection = DbConn.getConnection();
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
    }

    public void reload() throws IOException, SQLException {
        executeScript("schema.sql");
        executeScript("entries.sql");
    }

}
