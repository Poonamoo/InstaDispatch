/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reports;

import DataHolder.BuildConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 ** @author ADMINISTOR
 */

public class ReportScript implements BuildConnection{
    
    public String[] RetriveData(String q,int n){
        String data[] = new String[n];
    try{
    Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(q);
            rs.next();
            {
                for(int a=0;a<n;a++){
                data[a]=rs.getString(a+1);
                }
            }
            stmt.close();
            con.close();
    }
    catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error In Updation!" + e);
    }
    return data;
    }
}
