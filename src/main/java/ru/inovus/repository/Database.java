package ru.inovus.repository;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Vlad.M on 27.10.2016.
 */
public class Database {
    private Connection conn;
    private static Database database;

    private static final String URL           = "jdbc:hsqldb:mem:";
    private static final String DATABASE_NAME = "testdb";
    private static final String USER_NAME     = "SA";
    private static final String PASSWORD      = "";


    private Database() {
        try {
            Class.forName("org.hsqldb.jdbc.JDBCDriver").newInstance();
            this.conn = DriverManager.getConnection(URL+DATABASE_NAME,USER_NAME,PASSWORD);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Database getInstance() {

        if(database == null) {
            synchronized (Database.class) {
                if(database == null) {
                    database = new Database();
                }
            }
        }
        return database;
    }

    public static Connection getConnection(){

        return Database.getInstance().conn;
    }

}
