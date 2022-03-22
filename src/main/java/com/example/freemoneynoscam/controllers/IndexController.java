package com.example.freemoneynoscam.controllers;

import com.example.freemoneynoscam.services.ValidateEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

@Controller
public class IndexController {
    static Statement stmt;
    static ResultSet rs;
    static String sqlString;
    static Connection con;
    @GetMapping("/")
    public String index(){
        return "index";
    }
    @GetMapping("/failure")
    public String failure(){
        return "failure";
    }

    @PostMapping("/test")
    public String test(WebRequest dataFromForm){
        ValidateEmailService validator = new ValidateEmailService();
        System.out.println(validator.isEmailValid(dataFromForm.getParameter("email")));
        System.out.println(dataFromForm.getParameter("email"));
        if (validator.isEmailValid(dataFromForm.getParameter("email")) == false){
            return "redirect:/failure";
        }else{
            //Send data til database
            connect();
            insert(dataFromForm.getParameter("email"));
        }

        return "redirect:/";
    }
    public static void insert(String mail){
        try{
            //Get another statement object initialized as shown.
            stmt = con.createStatement();

            //Query the database, storing the result in an object of type ResultSet
            sqlString = "INSERT INTO emails  (emails_list) VALUES ('"+ mail + "')";
            stmt.executeUpdate(sqlString);

        }catch (Exception e){
            System.out.println("An error has occured during insert");
            System.out.println(e);
        }

    }
    public static void connect(){
        //Define URL of database server for database named no_money
        //on the localhost with the default port number 3306.
        String url = "jdbc:mysql://localhost:3306/free_money";

        //Get a connection to the database for a user named root with password admin
        try{
            con = DriverManager.getConnection(url,"root","77tgbv77");

        }catch (Exception e){
            System.out.println("Connection failed");
        }

        //Display the URL and connection information
        System.out.println("URL: " + url);
        System.out.println("Connection: " + con);
    }
}
