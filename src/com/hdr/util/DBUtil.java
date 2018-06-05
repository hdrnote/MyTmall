package com.hdr.util;

import java.sql.*;

public class DBUtil {
    static String ip = "127.0.0.1";
    static int port = 3306;
    static String database = "tmall";
    static String encoding = "UTF-8";
    static String loginName = "root";
    static String password = "admin";

    static{
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException{
        String url = String.format("jdbc:mysql://%s:%d/%s?characterEncoding=%s&serverTimezone=GMT&useSSL=false", ip, port, database, encoding);
        return DriverManager.getConnection(url,loginName,password);
    }

    public static void main(String[] args) throws SQLException{
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String sql = "show tables;";
        ResultSet resultSet = statement.executeQuery(sql);
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }

}


