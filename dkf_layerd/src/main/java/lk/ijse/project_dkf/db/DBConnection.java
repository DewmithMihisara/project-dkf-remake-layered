package lk.ijse.project_dkf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private Connection con;
    private DBConnection() throws SQLException {
        con= DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dkf",
                "root",
                "Dew@2005"
        );
    }
    public static DBConnection getInstance() throws SQLException {
        return (null == dbConnection)? dbConnection=new DBConnection(): dbConnection;
    }
    public Connection getConnection(){return con;}
}
