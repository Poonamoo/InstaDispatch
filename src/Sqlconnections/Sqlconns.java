package Sqlconnections;

import DataHolder.BuildConnection;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Sqlconns implements BuildConnection{
   static String query;
   static Connection con = null;
   static int rowCount;
   
    public static int getRowcount(String query1){
       try{
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            rs.next();
            {   
                rowCount = rs.getInt(1);
            }
            stmt.close();
            con.close();
            rowCount  ++ ;
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return rowCount;
    }
    
    public static void Updateqry(String qry){
    try{
            Class.forName("java.sql.Driver");
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(qry);
            stmt.close();
            con.close();
            //JOptionPane.showMessageDialog(null,"inside updateqry (sqlconns)");
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
