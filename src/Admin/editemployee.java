/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import DataHolder.BuildConnection;
import Reports.EmployeeReport;
import java.awt.Color;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMINISTOR
 */
public class editemployee extends javax.swing.JInternalFrame implements BuildConnection{
    String id="-1";
    int num=1;int dd=1;
    
    /**
     * Creates new form editemployee
     */
    public editemployee() {
        initComponents();
        getdata();
    }
    void paintPane(String clr1){
    jPanel1.setBackground(Color.decode(clr1));
    }
    private void getdata(){
        DefaultTableModel model = (DefaultTableModel)jTable1.getModel();
        model.setRowCount(0);
    try{
            Class.forName("java.sql.Driver");
            Connection con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="select * from employees ;";
            ResultSet  rs = stmt.executeQuery(qry);
            num=1;
            while(rs.next()){
            model.addRow(new Object[]{
                    rs.getInt(1),rs.getString(2),rs.getString(3)
                    ,rs.getString(4),rs.getString(5),rs.getString(6)
                    ,rs.getString(7)
            });
            num+=1;}
            rs.close();
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public void setdata(){
        try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="select * from employees where empid=" + id + ";";
            //JOptionPane.showMessageDialog(null,qry);
            ResultSet rs = stmt.executeQuery(qry);
            rs.next();
            {   
                idtf.setText(String.valueOf(rs.getInt(1)));nametf.setText(rs.getString(2));phonetf.setText(rs.getString(3));
                addtf.setText(rs.getString(4));mailtf.setText(rs.getString(5));usertf.setText(rs.getString(6));
                passtf.setText(rs.getString(7));
            }
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void insertdata(){
    try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="insert into employees values(" + idtf.getText() + ","
                    + "'" + nametf.getText() + "',"
                    + "'" + phonetf.getText() + "',"
                    + "'" + addtf.getText() + "',"
                    + "'" + mailtf.getText() + "',"
                    + "'" + usertf.getText() + "',"
                    + "'" + passtf.getText() + "'"
                    + ");";
            //JOptionPane.showMessageDialog(null,qry);
            stmt.executeUpdate(qry);
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    getdata();
    }
    
    private void removedata(){
        try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="delete from employees where empid=(" + id + ");";
            //JOptionPane.showMessageDialog(null,qry);
            stmt.executeUpdate(qry);
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    getdata();
    }
    
    private void Updates(){
    try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="Update employees set "
                    + "name='" + nametf.getText() + "',"
                    + "phone='" + phonetf.getText()+"',"
                    + "address='" + addtf.getText() + "',"
                    + "email='" + mailtf.getText() + "',"
                    + "Username='" + usertf.getText() + "',"
                    + "Password='" + passtf.getText() + "'"
                    + "where empid=(" + id + ");";
            //JOptionPane.showMessageDialog(null,qry);
            stmt.executeUpdate(qry);
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
    getdata();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        idtf = new javax.swing.JTextField();
        nametf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        phonetf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        addtf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        mailtf = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        usertf = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        passtf = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setClosable(true);

        jPanel1.setBackground(new java.awt.Color(117, 148, 204));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee Id", "Name", "Phone", "Address", "Email", "Username", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("ID");

        idtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        nametf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nametf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nametfFocusLost(evt);
            }
        });
        nametf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nametfActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Name");

        phonetf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        phonetf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                phonetfFocusLost(evt);
            }
        });
        phonetf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phonetfActionPerformed(evt);
            }
        });
        phonetf.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                phonetfPropertyChange(evt);
            }
        });
        phonetf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                phonetfKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                phonetfKeyTyped(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Phone");

        addtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addtf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                addtfFocusLost(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Address");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Email");

        mailtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Username");

        usertf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Password");

        passtf.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setText("New");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton3.setText("Remove");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setText("EMPLOYEE DATA");

        jButton4.setText("Go Back");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Print Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(46, 46, 46))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(idtf, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nametf, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(phonetf, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(94, 94, 94)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25)
                                        .addComponent(mailtf, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(usertf)
                                            .addComponent(passtf)))))
                            .addComponent(addtf))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(66, 66, 66)
                        .addComponent(jButton2)
                        .addGap(44, 44, 44)
                        .addComponent(jButton1)
                        .addGap(41, 41, 41)
                        .addComponent(jButton3)
                        .addGap(40, 40, 40)
                        .addComponent(jButton4)
                        .addGap(55, 55, 55)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(255, 255, 255)
                        .addComponent(jLabel8)))
                .addGap(0, 102, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mailtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nametf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(usertf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phonetf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        id= String.valueOf(jTable1.getValueAt(row, 0));
        setdata();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
idtf.setText(String.valueOf(num));
nametf.setText("");
phonetf.setText("");
addtf.setText("");
usertf.setText("");
passtf.setText("");
mailtf.setText("");

jButton1.setEnabled(true);
jButton2.setEnabled(false);
jButton3.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
if(nametf.getText().equals("") || addtf.getText().equals("") 
        || phonetf.getText().equals("") || usertf.getText().equals("") ||
        passtf.getText().equals("") || mailtf.getText().equals("")
   ){JOptionPane.showMessageDialog(null, "One Or more Field Is Empty");}
else{
        int sel=0;
        if(jButton2.isEnabled() == true){
            sel = JOptionPane.showConfirmDialog(null, "Update?");
                if (sel==0) {
                Updates();
                }
        }else{
        insertdata();
        JOptionPane.showMessageDialog(null, "Data Saved!");
        }
        jButton2.setEnabled(true);
        jButton3.setEnabled(true);
}    
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
int sel =0;
        sel = JOptionPane.showConfirmDialog(null, "Do You Want To remove employee:"+ id);
        if (sel==0){
            id=idtf.getText();
        removedata();}
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
JDesktopPane pane = getDesktopPane();
pane.remove(this);
pane.repaint();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void nametfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nametfActionPerformed

    }//GEN-LAST:event_nametfActionPerformed

    private void nametfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nametfFocusLost
if(!nametf.getText().matches("^[A-z]*$")){
nametf.setText("");
}      // TODO add your handling code here:
    }//GEN-LAST:event_nametfFocusLost

    private void phonetfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_phonetfFocusLost
String txt=phonetf.getText();
if(txt.matches("^[0-9]{10}$")){
}
else
    phonetf.setText("");
// TODO add your handling code here:
    }//GEN-LAST:event_phonetfFocusLost

    private void phonetfKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonetfKeyPressed
     
    }//GEN-LAST:event_phonetfKeyPressed

    private void phonetfKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phonetfKeyTyped

    }//GEN-LAST:event_phonetfKeyTyped

    private void phonetfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_phonetfActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_phonetfActionPerformed

    private void phonetfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_phonetfPropertyChange
       // TODO add your handling code here:
    }//GEN-LAST:event_phonetfPropertyChange

    private void addtfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_addtfFocusLost
    // TODO add your handling code here:
    }//GEN-LAST:event_addtfFocusLost

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
EmployeeReport er = new EmployeeReport();
er.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addtf;
    private javax.swing.JTextField idtf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField mailtf;
    private javax.swing.JTextField nametf;
    private javax.swing.JTextField passtf;
    private javax.swing.JTextField phonetf;
    private javax.swing.JTextField usertf;
    // End of variables declaration//GEN-END:variables
}
