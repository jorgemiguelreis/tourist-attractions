package objects;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class FactoryDAO {

	private static String databasePath ="atracoes.db";
	private static Connection connection = null;
	private static String dbCreationSQLpath = "./atracoes.sql";

	public FactoryDAO() {
		// verify if the database exists
		File db = new File(databasePath);
		if (!db.exists() || db.isDirectory()) {
			System.err.println("Database '" + databasePath + "' not found!");
			connection = getConnection();
			createDatabase(connection);
		}
		else
			connection = getConnection();
	}

	public static void createDatabase(Connection c) {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(getDBCreationSQL());
			stmt.close();
			System.out.println("New database created.");

		} catch ( Exception e ) {
			e.printStackTrace();
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException e1) {
				e.printStackTrace();
			}
			System.err.println( e.getClass().getName() + ": " + e.getMessage() );
			System.exit(0);
		}
	}

	public static String getDBCreationSQL() {
		String content;
		try {
			content = new Scanner(new File(dbCreationSQLpath)).useDelimiter("\\Z").next();
			return content;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.println("Ficheiro SQL nao encontrado! ("+dbCreationSQLpath+")");
			System.exit(1);
		}
		return null;
	}


	//create a database connection
	public static Connection getConnection() {
		if(connection != null)
			return connection;
		else {
			try {
				// load the sqlite-JDBC driver using the current class loader
				Class.forName("org.sqlite.JDBC");
				// create a database connection to our .db file
				connection = DriverManager.getConnection("jdbc:sqlite:"+databasePath);
				//connection.setAutoCommit(false);
				return connection;

			} catch (Exception e) {
				e.printStackTrace();
				// if the error message is "out of memory", 
				// it probably means no database file is found
				System.err.println(e.getMessage());
				closeConnection();
				System.exit(-4);
			}
		}
		return null;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			// connection close failed.
			System.err.println(e1);
		}
	}


}
