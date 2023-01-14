/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package policedepartment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gokul
 */
public class NewClass {
    public static void main(String arg[])
    {
     
    Connection con=null; 
         try{
           Class.forName("com.mysql.cj.jdbc.Driver"); 
         con=DriverManager.getConnection("jdbc:mysql://localhost:3306/police","root","");
              JOptionPane.showMessageDialog(null, "connection is successful crerated ");

                }
    catch(Exception e)
    {
       JOptionPane.showMessageDialog(null, "connection is unsuccessful");
            Logger.getLogger(" Get Connection -> " + loginconnection.class.getName()).log(Level.SEVERE, null, e); 
    
    
   
    }
    
    }
    
}
