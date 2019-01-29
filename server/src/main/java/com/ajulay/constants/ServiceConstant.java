package com.ajulay.constants;

public class ServiceConstant {

    public static final int HIGH_PRIORITY = 1;
    public static final int MIDDLE_PRIORITY = 2;
    public static final int LOW_PRIORITY = 3;
    public static final String END_ENTER_ASSIGNER = "/end";
    public static final String LOG_OUT = "/out";
    public static final String START_LOGIN = "admin";
    public static final String START_PASSWORD_HASH = "admin".hashCode() + "";
    public static final String DATABASE_DRIVER = "driver";
    public static final String DATABASE_ADDRESS = "address";

    public static final String DATA_FILE = "data/AppData.txt";
    public static final String ADMIN = "admin";
    public static final int LOAD_TIME = 500;
    public static final int MAX_ATTEMPT = 3;
    public static final int SUBSTRING_INSTANT = 10;
    public static final boolean FAILED = false;
    public static final boolean SUCCESS = true;
    public static final long CONTROL_TIME = 1000 * 60 * 30;
    public static final String EMPTY = "";
    public static final String USER = "user";
    public static final String PASSWORD = "password";
    public static final String DATABASE_PROPERTY_ADDRESS = "server/src/main/database.properties";
    public static final String HIBERNATE_PROPERTY_ADDRESS = "server/src/main/hibernate.properties";
    public static final String HIBERNATE_CONNECT = "hibernate.connection.url";
    public static final String HIBERNATE_DRIVER = "hibernate.connection.driver_class";
    public static final String HIBERNATE_USER = "hibernate.connection.user";
    public static final String HIBERNATE_PASSWORD = "hibernate.connection.password";
    public static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    public static final String HIBERNATE_SHOW_SQL = "hibernate.sql";
    public static final String HIBERNATE_DIALECT = "hibernate.dialect";

}
