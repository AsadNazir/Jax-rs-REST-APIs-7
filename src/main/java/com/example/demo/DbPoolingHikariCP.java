package com.example.demo;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DbPoolingHikariCP {
    static final String Url ="jdbc:mysql://localhost:3306/";
    static final String username = "root";
    static final String password = "root";

    public DbPoolingHikariCP()
    {
        System.out.println("Initiated");
    }


    public static DataSource getDataSource() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(Url + "web");
        config.setUsername(username);
        config.setPassword(password);
        return new HikariDataSource(config);
    }




}
