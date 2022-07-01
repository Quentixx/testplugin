package test.plugin.databse;

import test.plugin.util.SysLog;

import java.sql.*;

public class DatabaseService {

    public static DatabaseService SERVICE = new DatabaseService("jdbc:mysql://localhost:3306/srvdb","root","password");

    private final String URL, USER, PASSWORD;

    private Connection connection;

    DatabaseService(String url, String user, String password) {
        this.URL = url;
        this.USER = user;
        this.PASSWORD = password;
    }

    public void connect() throws SQLException {
        PingRecorder recorder = new PingRecorder();
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        SysLog.info(getClass(), "Database connected in " + recorder.end() + " ms.");
    }

    public ResultSet query(String query) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }

    public int update(String update, UpdateConsumer consumer) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(update);
        consumer.accept(statement);
        return statement.executeUpdate();
    }

    public static interface UpdateConsumer {
        void accept(PreparedStatement statement) throws SQLException;
    }

    private static class PingRecorder {
        long valStart, valEnd;
        PingRecorder() { valStart = System.currentTimeMillis(); }
        long end()  { return System.currentTimeMillis() - valStart; }
    }

}
