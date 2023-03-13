/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emp_data;

import DataHolder.BuildConnection;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMINISTOR
 */
public class empconnect implements BuildConnection{
    Connection con = null;
    String User;
    String Name,phone,email,address;
    String Dorigin,Ddesti,dcontent,ddate;
    public int connect(String u, String p){
        int count=0;
        try{
            
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select  count(*) from employees "
                    + "where  username='"
                    + u + "' and "
                    + "password ='"
                    + p +"'"
                    + "");
            rs.next();
            {   
                count = rs.getInt(1);
            }
            stmt.close();
            con.close();
            User =u;
            
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return count;
    }
    
    public void executeQry(String Qry){
         
     try{
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(Qry);
            rs.next();
            {   
                Name = rs.getString(2);
                phone = rs.getString(3);
                address = rs.getString(4);
                email = rs.getString(5);
                User = rs.getString(6);
            }
            stmt.close();
            con.close();
            //JOptionPane.showMessageDialog(null, Name + phone + address + email + User);
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
}
