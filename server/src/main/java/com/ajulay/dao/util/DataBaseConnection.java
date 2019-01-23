package com.ajulay.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private Connection conn;
    private final String JDBC_DRIVER = "org.postgresql.Driver";
    private final String DB_URL = "jdbc:postgresql://localhost:5432/task-manager";

    public void connect() throws SQLException, ClassNotFoundException {
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
