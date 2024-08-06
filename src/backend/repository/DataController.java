package backend.repository;

import backend.DBInitException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataController {
    private Connection conn;

    public void initializeDataBase() throws DBInitException {
        String driver = "org.postgresql.Driver";
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "root";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            throw new DBInitException(e);
        }
    }

    public Connection getConn() {
        return conn;
    }
}
