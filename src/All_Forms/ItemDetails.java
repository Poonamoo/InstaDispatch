package All_Forms;

//
import DataHolder.BuildConnection;
import DataHolder.Dstorage;
import java.awt.Color;
import javax.swing.JOptionPane;
import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
import static Sqlconnections.Sqlconns.*;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import DataHolder.masterconfig;
//import static mytestfiles.
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADMINISTOR
 */
public class ItemDetails extends javax.swing.JInternalFrame implements BuildConnection{
//   String CONNSTRING="jdbc:mysql://localhost/courier",USERNAME="root",PASS="nilesh";
   
   Master m1 = new Master();
   masterconfig m2 = new masterconfig();
    String serviceType,serviceTime,serviceAmt,query,p1city,p1state,p2city,p2state;
    int countrow,rowCount,sTime,overheadAmt,overheadTime;
    static Dstorage ds = new Dstorage();
    String clr1="";
    /**
     * Creates new form ItemDetails
     */
    
    void sendObj(Master m,masterconfig ma){
        m1=m;
        m2=ma;
    }
//    public void mainConn(String c,String u, String p){
//        CONNSTRING=c;
//        USERNAME=u;
//        PASS=p;
//    }
    public ItemDetails() {
        initComponents();
        jPanel1.setFocusable(true);
        rowCount=getRowcount("select count(*) from productdetails;");
       // JOptionPane.showMessageDialog(null, rowCount);
        jButton2.setEnabled(false);
        jPanel4.setVisible(false);
        jPanel5.setVisible(false);
        jPanel6.setVisible(false);
        //-----------------------close event
        addInternalFrameListener(new InternalFrameAdapter() {
            public void internalFrameClosing(InternalFrameEvent e){
                
                int sel = JOptionPane.showConfirmDialog(null, "Do You Want to close");
                if(sel==0){ JDesktopPane pane = getDesktopPane();
                            pane.removeAll();
                            pane.repaint();
                        }
                else setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);
            }
            
        });
        
    }
    
  public void paintpane(String clr){
      clr1=clr;
      //JOptionPane.showMessageDialog(null, clr1);
  jPanel1.setBackground(Color.decode(clr));
  }
   
   public void Getfocus(JTextField jtf,String s){
        if (jtf.getText().equals(s)){
        jtf.setText("");
        jtf.setForeground(Color.BLACK);
        }
    }
    public void LostFocus(JTextField jtf,String s){
        if (jtf.getText().equals("")){
            jtf.setText(s);
            jtf.setForeground(Color.LIGHT_GRAY);
        }
    }
    public static Dstorage SendObj(){
        return ds;
    }
    
    private void fetchdata(int i,String s){
        if(!s.equals("Pincode")){
    try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="select city_name,state_name from locations where pincode ="+s+ ";";
            ResultSet rs = stmt.executeQuery(qry);
            rs.next();
            {   if(i==1){
                p1city = rs.getString(1);p1state= rs.getString(2);}
                else  {
                p2city = rs.getString(1);p2state= rs.getString(2);}
            }
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Enter a Valid Pincode!");
            p1.setText("Pincode");
            p1.setForeground(Color.LIGHT_GRAY);
            p2.setText("Pincode");
            p2.setForeground(Color.LIGHT_GRAY);
        }
        }else{
            lblorigin.setText("");
            lbldesti.setText("");}
    }
    
    private void calculations(String pin1, String pin2){
        String s1=null,c1=null,s2=null,c2=null;
        overheadTime=0;overheadAmt=0;
        try{
            Class.forName("java.sql.Driver");
            Connection con;
            con = DriverManager.getConnection(CONNSTRING,USERNAME,PASS);
            Statement stmt = con.createStatement();
            String qry ="select city_name,state_name from locations where pincode =" + pin1 +";";
            ResultSet rs = stmt.executeQuery(qry);
            rs.next();{
               c1=rs.getString(1);s1=rs.getString(2);
            }
            qry ="select city_name,state_name from locations where pincode ="+pin2+ ";";
            rs = stmt.executeQuery(qry);
            rs.next();
            {   c2=rs.getString(1);s2=rs.getString(2);
            }
            stmt.close();
            con.close();
        }
        catch(HeadlessException | ClassNotFoundException | SQLException e){
            JOptionPane.showMessageDialog(null, "Enter a Valid Pincode!");
            p1.setText("Pincode");
            p1.setForeground(Color.LIGHT_GRAY);
            p2.setText("Pincode");
            p2.setForeground(Color.LIGHT_GRAY);
        }
        
        if( (Integer.parseInt(wtf.getText()) >= 2 && cbweight.getSelectedItem()=="Kg") || (Integer.parseInt(wtf.getText()) >= 2000 && cbweight.getSelectedItem()=="Gram") ){
            overheadAmt=20;
        }else if((Integer.parseInt(wtf.getText()) >= 5 && cbweight.getSelectedItem()=="Kg") || (Integer.parseInt(wtf.getText()) >= 5000 && cbweight.getSelectedItem()=="Gram") ){
            overheadAmt=40;
            overheadTime=1;
        }else if((Integer.parseInt(wtf.getText()) >= 9 && cbweight.getSelectedItem()=="Kg") || (Integer.parseInt(wtf.getText()) >= 9000 && cbweight.getSelectedItem()=="Gram") ){
            overheadAmt=80;
            overheadTime=3;
        }
        
        if(s1.equals(s2)){
            overheadAmt+=200;
            overheadTime=2;
            if(c1.equals(c2)){
            overheadAmt-=100;
            overheadTime=1;
            }
        }else{
            overheadTime=5;
            overheadAmt=1000;
        }
        preTime.setText(String.valueOf(overheadTime) + " days");
        preAmt.setText(String.valueOf(overheadAmt+20));
        goldTime.setText(String.valueOf(overheadTime+1) + " days");
        goldAmt.setText(String.valueOf(overheadAmt+10));
        silTime.setText(String.valueOf(overheadTime+2) + " days");
        silAmt.setText(String.valueOf(overheadAmt));
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel18 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        p1 = new javax.swing.JTextField();
        p2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        shiptype = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        wtf = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbContent = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        ltf = new javax.swing.JTextField();
        btf = new javax.swing.JTextField();
        htf = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        goldAmt = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        goldTime = new javax.swing.JLabel();
        jRadioButton6 = new javax.swing.JRadioButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        silAmt = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        silTime = new javax.swing.JLabel();
        jRadioButton5 = new javax.swing.JRadioButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        preAmt = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        preTime = new javax.swing.JLabel();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton2 = new javax.swing.JButton();
        cbweight = new javax.swing.JComboBox<>();
        lblorigin = new javax.swing.JLabel();
        lbldesti = new javax.swing.JLabel();

        jLabel18.setText("0 INR");

        jLabel14.setText("Platinum");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        setClosable(true);
        setTitle("Item Details");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(117, 148, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setText("Origin");

        p1.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        p1.setForeground(java.awt.SystemColor.controlShadow);
        p1.setText("Pincode");
        p1.setToolTipText("Origin Pincode");
        p1.setDoubleBuffered(true);
        p1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                p1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                p1FocusLost(evt);
            }
        });
        p1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                p1ActionPerformed(evt);
            }
        });
        p1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                p1KeyPressed(evt);
            }
        });

        p2.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        p2.setForeground(new java.awt.Color(153, 153, 153));
        p2.setText("Pincode");
        p2.setToolTipText("Destination Pincode");
        p2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                p2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                p2FocusLost(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel2.setText("Destination");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setText("Shipment Type");

        shiptype.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        shiptype.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "document", "Non-document" }));
        shiptype.setSelectedIndex(1);
        shiptype.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shiptypeActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel4.setText("Weight");

        wtf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        wtf.setForeground(java.awt.SystemColor.controlShadow);
        wtf.setText("Weight");
        wtf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                wtfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                wtfFocusLost(evt);
            }
        });
        wtf.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                wtfPropertyChange(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel5.setText("Content");

        cbContent.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbContent.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Artificial Items", "Auto Parts", "Back Up Battery", "Bags", "Battery", "Body Cover(E.G Mobile,Car)", "Books", "Box", "Bunch Of Documents", "Camera", "Carry Bag", "Cds", "Charger", "Chocolates", "Cloth", "Clothing", "Computer Parts", "Cover", "Documents", "Dry Fruits", "Earphone", "Ecommerce Content", "Electronic Item", "Electronic Item(E.G Laptop, Led)", "Food Item (All Item)", "Furniture", "Garments Clothes", "Gift Items", "Gift Pack", "Greetings Card", "Home Applience", "International Inbound Commodity", "Laptop", "Led Lcd Tv", "Led Lights", "Liquid", "Manual", "Masala Powder", "Medical Equipment", "Medicine", "Mobile", "Mobile(Without Battery)", "Mouse", "Mp3player", "Others", "Passport", "Pendrive", "Photo Frame", "Plastic Items", "Powder", "Power Cord", "Rakhi", "Sd Card", "Seeds", "Shoes", "Stationery", "Surgical Parts", "Sweets", "Tablet", "Toys", "Usb Cable" }));

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel7.setText("Length (Cm)");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setText("Breadth (Cm)");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setText("Height (Cm)");

        ltf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ltf.setForeground(java.awt.SystemColor.controlShadow);
        ltf.setText("Length");
        ltf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ltfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                ltfFocusLost(evt);
            }
        });

        btf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btf.setForeground(java.awt.SystemColor.controlShadow);
        btf.setText("Breadth");
        btf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                btfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                btfFocusLost(evt);
            }
        });

        htf.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        htf.setForeground(java.awt.SystemColor.controlShadow);
        htf.setText("Height");
        htf.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                htfFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                htfFocusLost(evt);
            }
        });

        jButton1.setText("GET QUOTES");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel16.setText("Service Type: GOLD");

        goldAmt.setText("2000");

        jLabel21.setText("Delivery Time:");

        goldTime.setText("4-6 days");

        buttonGroup2.add(jRadioButton6);
        jRadioButton6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton6ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(goldAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(goldTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(jRadioButton6))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16))
                    .addComponent(jRadioButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(goldTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goldAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel17.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel17.setText("Service Type: SILVER");

        silAmt.setText("1000");

        jLabel22.setText("Delivery Time:");

        silTime.setText("7-10 days");

        buttonGroup2.add(jRadioButton5);
        jRadioButton5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton5ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(silAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(silTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addComponent(jRadioButton5))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel17))
                    .addComponent(jRadioButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(silTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(silAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel11.setText("Service Type: PREMIUM");

        preAmt.setText("4000");

        jLabel13.setText("Delivery Time:");

        preTime.setText("2-3 days");

        buttonGroup2.add(jRadioButton4);
        jRadioButton4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jRadioButton4ItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator4)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(preTime, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(preAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                        .addComponent(jRadioButton4))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jRadioButton4)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(preTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preAmt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton2.setText("NEXT");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        cbweight.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        cbweight.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Kg", "Gram" }));
        cbweight.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbweightItemStateChanged(evt);
            }
        });
        cbweight.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbweightPropertyChange(evt);
            }
        });
        cbweight.addVetoableChangeListener(new java.beans.VetoableChangeListener() {
            public void vetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {
                cbweightVetoableChange(evt);
            }
        });

        lblorigin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        lbldesti.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(shiptype, 0, 252, Short.MAX_VALUE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbContent, 0, 0, Short.MAX_VALUE)
                                    .addComponent(p1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblorigin, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(ltf, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(24, 24, 24)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btf, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel8))
                                        .addGap(32, 32, 32)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(htf)))
                                    .addComponent(p2)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(wtf, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbweight, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbldesti, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(97, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(p2)
                            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                            .addComponent(lbldesti, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbweight, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(shiptype, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                            .addComponent(wtf))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbContent, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                            .addComponent(ltf)
                            .addComponent(htf)
                            .addComponent(btf))
                        .addGap(122, 122, 122))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblorigin, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 309, Short.MAX_VALUE)))
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        p1.getAccessibleContext().setAccessibleName("guyguyguyguy");

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 527, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void p1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_p1FocusGained
Getfocus(p1,"Pincode");
    }//GEN-LAST:event_p1FocusGained

    private void p1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_p1FocusLost
LostFocus(p1,"Pincode");
fetchdata(1,p1.getText());
lblorigin.setText(p1city);

    }//GEN-LAST:event_p1FocusLost

    private void p2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_p2FocusGained
Getfocus(p2,"Pincode");    // TODO add your handling code here:
    }//GEN-LAST:event_p2FocusGained

    private void p2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_p2FocusLost
LostFocus(p2,"Pincode");
        fetchdata(2,p2.getText());
        lbldesti.setText(p2city);

    }//GEN-LAST:event_p2FocusLost

    private void wtfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wtfFocusGained
Getfocus(wtf,"Weight");
    }//GEN-LAST:event_wtfFocusGained

    private void wtfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_wtfFocusLost
try{
    int value = Integer.parseInt(wtf.getText());
    if(cbweight.getSelectedIndex()==0 & value > 10){
    cbweight.setSelectedIndex(1);}
    if(cbweight.getSelectedIndex()==1 & value > 10000){
        JOptionPane.showMessageDialog(null, "Too much Weight for delivery!");
        wtf.setText("");
    }
}catch( HeadlessException | NumberFormatException e){JOptionPane.showMessageDialog(null, e);}
LostFocus(wtf,"Weight");
    }//GEN-LAST:event_wtfFocusLost

    private void ltfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ltfFocusGained
Getfocus(ltf,"Length");       // TODO add your handling code here:
    }//GEN-LAST:event_ltfFocusGained

    private void ltfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ltfFocusLost


if(! ltf.getText().equals("")){
    try{
        int value = Integer.parseInt(ltf.getText());
    if(value > 100)
//    JOptionPane.showMessageDialog(null, ltf.getText());
    ltf.setText("");}
    catch(NumberFormatException e){}
}
LostFocus(ltf,"Length"); 
    }//GEN-LAST:event_ltfFocusLost

    private void btfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btfFocusLost
if(! btf.getText().equals("")){
    try{
        int value = Integer.parseInt(btf.getText());
    if(value > 100)
//    JOptionPane.showMessageDialog(null, ltf.getText());
    btf.setText("");}
    catch(NumberFormatException e){}
}
LostFocus(btf,"Breadth");
    }//GEN-LAST:event_btfFocusLost

    private void btfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_btfFocusGained
Getfocus(btf,"Breadth");
    }//GEN-LAST:event_btfFocusGained

    private void htfFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_htfFocusLost
if(! htf.getText().equals("")){
    try{
        int value = Integer.parseInt(htf.getText());
    if(value > 100)
//    JOptionPane.showMessageDialog(null, ltf.getText());
    htf.setText("");}
    catch(NumberFormatException e){}
}
LostFocus(htf,"Height");
    }//GEN-LAST:event_htfFocusLost

    private void htfFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_htfFocusGained
Getfocus(htf,"Height");
    }//GEN-LAST:event_htfFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
  
if (p1.getText().equals("Pincode") || p2.getText().equals("Pincode") || wtf.getText().equals("Weight")
        || ltf.getText().equals("Length") 
        || btf.getText().equals("Breadth")|| htf.getText().equals("Height")){
    JOptionPane.showMessageDialog(null, "Please Fill All Details!!");
}

else{
        jScrollPane1.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_AS_NEEDED);

JScrollBar vertical = jScrollPane1.getVerticalScrollBar();
vertical.setValue( vertical.getMaximum());
vertical.setValue( vertical.getMaximum());
//jButton2.setEnabled(true);
jPanel4.setVisible(true);
jPanel5.setVisible(true);
jPanel6.setVisible(true);

    calculations(p1.getText(), p2.getText());
}
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
String lengthtf = ltf.getText(),breadthtf=btf.getText(),heighttf =htf.getText();
        if (ltf.getText().equals("--"))
            lengthtf = "0";
        if (btf.getText().equals("--"))
            heighttf = "0";
        if (htf.getText().equals("--"))
            breadthtf = "0";
        
        
String weight_measure = wtf.getText();
if(cbweight.getSelectedItem()=="Gram"){
    
    weight_measure = "0." + wtf.getText();
    
}
DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
Calendar c = Calendar.getInstance();
c.add(Calendar.DATE,sTime);
Date date = new Date();
String Tdate = dateformat.format(date);
String dates = dateformat.format(c.getTime());


//date = date + 6;
//JOptionPane.showMessageDialog(null, Tdate);


query=" insert into productdetails values( "
        +       rowCount + "," 
        + "'" + shiptype.getSelectedItem() + "'" + ","
        +       lengthtf     + ","
        +       breadthtf    + ","
        +       heighttf     + ","
        +       Float.valueOf(weight_measure)   + ","
        +       p1.getText()    + ","
        +       p2.getText()    + ","
        + "'" + serviceType     + "',"
        + "'" + serviceTime     + "',"
        +       serviceAmt      + ","
        + "'" + cbContent.getSelectedItem() + "'"
        + ",null,null"
        + ",'" + dates + "'"
        + ",'" +Tdate
        + "');";
//JOptionPane.showMessageDialog(null, query);

ds.itemdet(String.valueOf(rowCount), String.valueOf(shiptype.getSelectedItem()), Integer.parseInt(lengthtf),
                 Integer.parseInt(breadthtf), Integer.parseInt(heighttf), Float.valueOf(weight_measure),
                 Integer.parseInt(p1.getText()), Integer.parseInt(p2.getText()),
                 serviceType, serviceTime, Integer.parseInt(serviceAmt), String.valueOf(cbContent.getSelectedItem()), 
                 query,dates,p1city,p1state,p2city,p2state);


if(m2.fun()=="UNKNOWN" || m2.fun()=="LOGIN / SIGNUP"){
    UserLogins ul = new UserLogins();
        JDesktopPane panes = getDesktopPane();
        panes.add(ul);
        ul.show();
        ul.paintframe(clr1);
        ul.getObject(m1);
        ul.flag=false;
        try {
            ul.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ItemDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
}else{
        pickup_delivery pd  = new pickup_delivery();
        JDesktopPane panes = getDesktopPane();
        panes.add(pd);
        pd.show();
        pd.paintframe(clr1);
        try {
            pd.setMaximum(true);
        } catch (PropertyVetoException ex) {
            Logger.getLogger(ItemDetails.class.getName()).log(Level.SEVERE, null, ex);
        }
}

        

        
//if(g)
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jRadioButton4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton4ItemStateChanged
serviceType = "premium";
sTime = overheadTime;
serviceTime= preTime.getText();
serviceAmt = preAmt.getText();
jButton2.setEnabled(true);// TODO add our handling code here:
    }//GEN-LAST:event_jRadioButton4ItemStateChanged

    private void jRadioButton6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton6ItemStateChanged
serviceType = "gold";   
sTime = overheadTime+1;
serviceTime = goldTime.getText();
serviceAmt = goldAmt.getText();
jButton2.setEnabled(true);
// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton6ItemStateChanged

    private void jRadioButton5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jRadioButton5ItemStateChanged
serviceType = "silver";  
sTime = overheadTime + 3 ;
serviceTime = silTime.getText();
serviceAmt = silAmt.getText();
jButton2.setEnabled(true);// TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton5ItemStateChanged

    private void shiptypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shiptypeActionPerformed
if(shiptype.getSelectedIndex() == 0){
        cbContent.setSelectedIndex(18); 
        cbContent.setEnabled(false);
        ltf.setEnabled(false);
        ltf.setText("--");
        btf.setEnabled(false);
        btf.setText("--");
        htf.setEnabled(false);
        htf.setText("--");}
else{
    cbContent.setEnabled(true);
    cbContent.setSelectedIndex(0);
    ltf.setEnabled(true);
    ltf.setText("Length");
    btf.setEnabled(true);
    btf.setText("Breadth");
    htf.setEnabled(true);
    htf.setText("Height");}
    
    }//GEN-LAST:event_shiptypeActionPerformed

    private void p1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p1KeyPressed

    }//GEN-LAST:event_p1KeyPressed

    private void p1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_p1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_p1ActionPerformed

    private void wtfPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_wtfPropertyChange
       // TODO add your handling code here:
    }//GEN-LAST:event_wtfPropertyChange

    private void cbweightPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbweightPropertyChange
      // TODO add your handling code here:
    }//GEN-LAST:event_cbweightPropertyChange

    private void cbweightVetoableChange(java.beans.PropertyChangeEvent evt)throws java.beans.PropertyVetoException {//GEN-FIRST:event_cbweightVetoableChange
  
    }//GEN-LAST:event_cbweightVetoableChange

    private void cbweightItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbweightItemStateChanged
wtf.setText("");
        LostFocus(wtf,"Weight");        // TODO add your handling code here:
    }//GEN-LAST:event_cbweightItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField btf;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cbContent;
    private javax.swing.JComboBox<String> cbweight;
    private javax.swing.JLabel goldAmt;
    private javax.swing.JLabel goldTime;
    private javax.swing.JTextField htf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lbldesti;
    private javax.swing.JLabel lblorigin;
    private javax.swing.JTextField ltf;
    private javax.swing.JTextField p1;
    private javax.swing.JTextField p2;
    private javax.swing.JLabel preAmt;
    private javax.swing.JLabel preTime;
    private javax.swing.JComboBox<String> shiptype;
    private javax.swing.JLabel silAmt;
    private javax.swing.JLabel silTime;
    private javax.swing.JTextField wtf;
    // End of variables declaration//GEN-END:variables
}
