package policedepartment;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Gokul
 *///import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.Toolkit;
import javax.swing.*;
public class loginconnection {
    
    public static Connection connection(){
    Connection con=null; 
         try{
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
              //JOptionPane.showMessageDialog(null, "connection is created in connectition class ");

                }
    catch(Exception e)
    {
    
            Logger.getLogger(" Get Connection -> " + loginconnection.class.getName()).log(Level.SEVERE, null, e);    }
    
        return con;
    }
    
}
