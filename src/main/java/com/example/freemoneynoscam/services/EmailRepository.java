package com.example.freemoneynoscam.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmailRepository {
       static Statement stmt;
        static ResultSet rs;
        static String sqlString;
        static Connection con;
    public static String fetchSingleEmail() {
        String mail = "";
        try {
        connect();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlString = "SELECT * FROM emails LIMIT 1";
            rs = stmt.executeQuery(sqlString);

            while (rs.next()) {
                String col1 = rs.getString("emails_list");
                mail = col1;
            }
        } catch (Exception e) {
            System.out.println("Der er sket en kæmpe fejl under select");
        }
        return mail;
    }
    public static ArrayList<String> fetchAllEmails(){
        ArrayList<String> mails = new ArrayList<String>();
        try {
            connect();
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            sqlString = "SELECT * FROM emails LIMIT 4";

            rs = stmt.executeQuery(sqlString);
            while (rs.next()) {
                String col1 = rs.getString("emails_list");
                mails.add(col1);

            }
        } catch (Exception e) {
            System.out.println("Der er sket en kæmpe fejl under select");
        }
        return mails;
    }
    public static void connect(){
        //Define URL of database server for database named test_hotel
        //on the localhost with the default port number 3306.
        String url = "jdbc:mysql://localhost:3306/free_money";

        //Get a connection to the database for a user named root with password admin
        try{
            con = DriverManager.getConnection(url,"root","77tgbv77");

        }catch (Exception e){
            System.out.println("Der er sket en fejl!");
        }

        //Display the URL and connection information
        System.out.println("URL: " + url);
        System.out.println("Connection: " + con);
    }
}
