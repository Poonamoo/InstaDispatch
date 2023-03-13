/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

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
public class AdmLogin implements BuildConnection {
    String User="";
    public int connect(String u, String p){
        int count=0;
        try{
            
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select  count(*) from admin "
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
    

}
