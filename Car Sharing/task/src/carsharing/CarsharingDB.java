package carsharing;

import java.sql.Connection;
import java.sql.DriverManager;


public class CarsharingDB {
    private final String JDBC_DRIVER = "org.h2.Driver";
    private final String DB_URL = "jdbc:h2:./src/carsharing/db/";
    private Connection conn;
    private String databaseFileName;
    public CarsharingDB(String databaseFileName) {
        this.databaseFileName = databaseFileName;
    }
    public Connection getConnection() {
        String dbURL = DB_URL + databaseFileName;
        try {
            //Load H2 database driver
            Class.forName ( JDBC_DRIVER );
            //connect to database
            conn = DriverManager.getConnection ( dbURL );
            conn.setAutoCommit ( true );
        } catch (Exception e) {
            e.printStackTrace ();
        }
        return conn;
    }
}
