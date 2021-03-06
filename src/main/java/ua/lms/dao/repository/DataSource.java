package ua.lms.dao.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource implements AutoCloseable {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL="jdbc:mysql://localhost/note" +
            "                   ?useTimezone=true&serverTimezone=UTC";

    private Connection connection = null;

    public DataSource() {
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Створює з'єднання із БД
     * @return об'єкт класу з'єднання з БД
     */
    public Connection getConnection()
    {
        try {
            if(connection == null) {
                connection = DriverManager.getConnection(DB_URL, "root", "12345678");
            }
        }
        catch( SQLException e ) {
            System.out.println("Error occured " + e.toString());
        }
        return connection;
    }

    @Override
    public void close() throws Exception {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
