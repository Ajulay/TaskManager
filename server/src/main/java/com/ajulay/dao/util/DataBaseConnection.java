package com.ajulay.dao.util;

import com.ajulay.constants.ServiceConstant;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DataBaseConnection {
    private Connection conn;

    public void connect() throws Exception {

        final FileInputStream fis = new FileInputStream(ServiceConstant.DATABASE_PROPERTY_ADDRESS);
        final Properties property = new Properties();
        property.load(fis);
        Class.forName(property.getProperty(ServiceConstant.DATABASE_DRIVER));
        conn = DriverManager.getConnection(ServiceConstant.DATABASE_ADDRESS);
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
