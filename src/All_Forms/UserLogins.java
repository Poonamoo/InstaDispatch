package All_Forms;

import DataHolder.masterconfig;
import java.awt.Color;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import All_Forms.Master;
import DataHolder.BuildConnection;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMINISTOR
 */
public class UserLogins extends javax.swing.JInternalFrame implements BuildConnection{
   
    boolean flag = true;
    Master mas = new Master();
    masterconfig mast = new masterconfig();
    private class ItemDetails{};
int custId; 
String clr1="";
//static DataHolder.masterconfig mas = new masterconfig("someone");
    /**
     * Creates new form UserLogins
     */
    public UserLogins() {
        initComponents();
    }
    private void rowCount(){
    try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="select count(*) from customers;";
            ResultSet rs =stmt.executeQuery(qry);
            rs.next();{
                custId=rs.getInt(1);
            }
            
            stmt.close();
            con.close();
            custId++;
        }
        catch(java.sql.SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "username already exist");
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    void paintframe(String clr){
        clr1=clr;
    jPanel1.setBackground(Color.decode(clr));
    jPanel2.setBackground(Color.decode(clr));
    }
    
    private void button3click(){
    String Loginbtn = jButton3.getText();
        if(Loginbtn.equals("Login")){
            if(tf1.getText().equals("") || tf2.getText().equals("")){
                  JOptionPane.showMessageDialog(null, "Fill All Details!");
            }
            else
                 Login();
        }
        else
            jLabel1.setText("Login");
        tf3.setVisible(false);
        tf4.setVisible(false);
        tf5.setVisible(false);
        lbl3.setVisible(false);
        lbl4.setVisible(false);
        lbl5.setVisible(false);
        tf6.setVisible(false);
        lbl6.setVisible(false);
    }
    
    private void register(){
        rowCount();
    try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="insert into customers values("
                    + custId +",'" + tf6.getText() + "','" + tf5.getText() + "','" + tf4.getText() + "','" + tf3.getText() + "','"
                    + tf1.getText() + "','"+ tf2.getText() + "'"
                    + ");";
            stmt.executeUpdate(qry);
            
            stmt.close();
            con.close();
            JOptionPane.showMessageDialog(null, "Successifully Registered");
        }
        catch(java.sql.SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "username already exist " + e);
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void Login(){
    try{    int logVal=0;
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="select count(*) from customers where username='"
                    + tf1.getText() + "' and password='" + tf2.getText()
                    + "';";
            ResultSet rs =stmt.executeQuery(qry);
            rs.next();{
                logVal=rs.getInt(1);
            }
            //------------------------------
            if(logVal == 1){
            JDesktopPane pane = getDesktopPane();
//            pickup_delivery pd = new pickup_delivery();
//            pane.add(pd);
//            pd.show();
//            pd.paintframe(clr1);
//                 try {
//                      pd.setMaximum(true);
//                   } catch (PropertyVetoException ex) {
//                        Logger.getLogger(UserLogins.class.getName()).log(Level.SEVERE, null, ex);
//                        }
                 JOptionPane.showMessageDialog(null, "successfully logged In!");
                 sendname(tf1.getText());
                 checkexit();
            }else JOptionPane.showMessageDialog(null, "Incorrect Username/Password!");
        //---------------------------------------
            
            stmt.close();
            con.close();
            custId++;
        }
        catch(java.sql.SQLIntegrityConstraintViolationException e){
            JOptionPane.showMessageDialog(null, "username already exist");
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void sendname(String n){
        mast.getname(n);
        mas.getCust(n,mast);
    }
    
    void flagValue(boolean b){
    flag=b;
    }
    
    void checkexit(){
     if (flag==false){
     pickup_delivery pd  = new pickup_delivery();
        JDesktopPane panes = getDesktopPane();
        panes.add(pd);
        pd.show();
        pd.paintframe(clr1);
        try {
            pd.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(All_Forms.ItemDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.dispose();
     }else{
     this.dispose();
     }
    }
    public void getObject(Master m){
    mas =m;
    }
//    public static masterconfig SendObj(){
//       // return mas;
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tf1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tf2 = new javax.swing.JTextField();
        tf3 = new javax.swing.JTextField();
        lbl3 = new javax.swing.JLabel();
        lbl4 = new javax.swing.JLabel();
        tf4 = new javax.swing.JTextField();
        lbl5 = new javax.swing.JLabel();
        tf5 = new javax.swing.JTextField();
        tf6 = new javax.swing.JTextField();
        lbl6 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("User Login/Register");

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 48)); // NOI18N
        jLabel1.setText("Register");
        jLabel1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jLabel1PropertyChange(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton1.setText("register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton2.setText("clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton3.setText("already have an account");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(102, 102, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("User Name");

        tf1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("password");

        tf2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tf2.setMinimumSize(new java.awt.Dimension(6, 95));
        tf2.setPreferredSize(new java.awt.Dimension(8, 34));

        tf3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tf3.setAlignmentX(0.9F);
        tf3.setAlignmentY(5.0F);

        lbl3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl3.setText("email address");

        lbl4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl4.setText("address");

        tf4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        lbl5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl5.setText("Phone number");

        tf5.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tf5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf5FocusLost(evt);
            }
        });

        tf6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        tf6.setAlignmentX(0.9F);
        tf6.setAlignmentY(5.0F);

        lbl6.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lbl6.setText("Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                        .addGap(76, 76, 76)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf1)
                            .addComponent(tf2, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(lbl6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbl3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tf3, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                            .addComponent(tf4)
                            .addComponent(tf5)
                            .addComponent(tf6))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(315, 315, 315)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(165, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
String label =jLabel1.getText();
        if(label.equals("Register")){
            if(tf1.getText().equals("") || tf2.getText().equals("") || tf3.getText().equals("") || tf4.getText().equals("") 
                    || tf5.getText().equals("") || tf6.getText().equals("")){
                  JOptionPane.showMessageDialog(null, "Fill All Details!");
            }
             else
                     register();
            }
        else
        
        jLabel1.setText("Register");
        tf3.setVisible(true);
        tf4.setVisible(true);
        tf5.setVisible(true);
        lbl3.setVisible(true);
        lbl4.setVisible(true);
        lbl5.setVisible(true);
        tf6.setVisible(true);
        lbl6.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
button3click();        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
 tf1.setText("");
 tf2.setText("");
 tf3.setText("");
 tf4.setText("");
 tf5.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jLabel1PropertyChange
if(jLabel1.getText()== "Login"){
    jButton3.setText("Login");
}  
else
{
    jButton3.setText("already have an account");
}
// TODO add your handling code here:
    }//GEN-LAST:event_jLabel1PropertyChange

    private void tf5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf5FocusLost
if(!tf5.getText().matches("^[0-9]{10}$")){
tf5.setText("");
}        // TODO add your handling code here:
    }//GEN-LAST:event_tf5FocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbl3;
    private javax.swing.JLabel lbl4;
    private javax.swing.JLabel lbl5;
    private javax.swing.JLabel lbl6;
    private javax.swing.JTextField tf1;
    private javax.swing.JTextField tf2;
    private javax.swing.JTextField tf3;
    private javax.swing.JTextField tf4;
    private javax.swing.JTextField tf5;
    private javax.swing.JTextField tf6;
    // End of variables declaration//GEN-END:variables

    
}
