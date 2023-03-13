/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package All_Forms;

import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ADMINISTOR
 */
public class Settings extends javax.swing.JInternalFrame implements DataHolder.BuildConnection{
    Master mast;
    File f = new File("c:"+ File.separator + "programmmmm files" +File.separator + "RAPIDCLICKS");
    String clrs[][];//= new String[7][10]; //assuming that we are not using more than 10 themes.
    JPanel[] O = new JPanel[10];
    JPanel[] P = new JPanel[10];
    JPanel[] I = new JPanel[10];
    int themeVal = 0,id;
    /**
     * Creates new form Settings
     */
    public Settings() {
        initComponents();
    }

    
    public void getobject(Master m){
        mast = m;
        }
    
    void writeFiles() throws IOException{
        //JOptionPane.showMessageDialog(null, "Im here");
        File f = new File("c:"+ File.separator + "programmmmm files" +File.separator + "RAPIDCLICKS");
        
        if(!f.exists()){
            try{f.mkdirs();
                }catch(SecurityException se ){
                    JOptionPane.showMessageDialog(null,"yo, whats you doooin "+se);
                }
        }
    
    FileWriter fw = new FileWriter(f+"\\DatabaseConn.txt");
    fw.write("outline:\t"+jTextField1.getText() + "\r\n");
    fw.write("Panel:\t"+jTextField2.getText() + "\r\n");
    fw.write("Menu:\t"+jTextField3.getText() + "\r\n");
    fw.write("Selected:\t"+jTextField4.getText() + "\r\n");
    fw.write("InrFrame:\t"+jTextField5.getText() + "\r\n");
    fw.write("FrameOut:\t"+jTextField6.getText() + "\r\n");
    fw.write("FrameInr:\t"+jTextField7.getText() + "\r\n");
//    for( int i=0;i <s.length(); i++){
//        fw.write(s.charAt(i));
//    fw.write(s.charAt(i));}
    fw.close();
        existFile(true);
    }
    
    void existFile(boolean b){
        try{
        if(b){
            FileWriter fwr = new FileWriter(f+"\\DatabaseConn.txt");
            FileReader fr = new FileReader(f+"\\DatabaseConn.txt");
            
            char CurrentPos = 'a';
            Scanner sca = new Scanner(f+"\\DatabaseConn.txt");
            for(int i=1;i<=15;i++){
                int data= fr.read();
            }
            fwr.close();
        }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Unable to append data to file");
        }
    
    }
    
    void getColors(int themecol, boolean flag){
        //clrs=null;
        clrs= new String[9][8]; //assuming that we are not using more than 10 themes.
        int m=0,n=0;String qry="";ResultSet rs=null;
        try{
            Class.forName("java.sql.Driver");
            Connection conn= DriverManager.getConnection(CONNSTRING, USERNAME, PASS);
            Statement stmt = conn.createStatement();
            if(themecol==0){
                qry = "select * from colourmg;";}
            else{
                qry = "select * from colourmg where id="+themecol+ " ;";
            }
                rs = stmt.executeQuery(qry);
                
            for(int i=0;i<=9;i++){
                if(rs.isLast()){
                    System.out.println("end of result:"+i);
                    break;
                }
                    rs.next();
                for(int j=0;j<=7;j++){
                    clrs[i][j]=rs.getString(j+1);
                }
                System.out.println("continue result:"+i);
        }
            rs.close();
            stmt.close();
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + " inside get colours");
        }
        if(themecol!=0){
            PaneDisplay(clrs,themecol);
        }else{
            //JOptionPane.showMessageDialog(null, "exiting get colours");
        ConvBefore();
        setColors();}
        //ConvBefore();
    }
    
    void ConvBefore(){
        O[1] = o1;O[2] = o2;O[3] = o3;O[4] = o4;O[5] = o5;O[6] = o6;O[7] = o7;O[8] = o8;O[9] = o9;
        P[1] = p1;P[2] = p2;P[3] = p3;P[4] = p4;P[5] = p5;P[6] = p6;P[7] = p7;P[8] = p8;P[9] = p9;
        I[1] = i1;I[2] = i2;I[3] = i3;I[4] = i4;I[5] = i5;I[6] = i6;I[7] = i7;I[8] = i8;I[9] = i9;   
    }
    
//    void ConvAfter(){
//        o1=O[1];o2=O[2];o3=O[3];o4=O[4];o5=O[5];o6=O[6];o7=O[7];o8=O[8];o9=O[9];
//        p1=P[1];p2=P[2];p3=P[3];p4=P[4];p5=P[5];p6=P[6];p7=P[7];p8=P[8];p9=P[9];
//        i1=I[1];i2=I[2];i3=I[3];i4=I[4];i5=I[5];i6=I[6];i7=I[7];i8=I[8];i9=I[9];
//        this.repaint();
//    }
    
    void setColors(){
        //o p i
        try{
        for(int i=0;i<=9;i++){
            if(clrs[i][0].length()==0){
                System.out.print("exiting set colours " + clrs.length);
            break;
            }   //JOptionPane.showMessageDialog(null, clrs[i]);
                O[i+1].setBackground(Color.decode(String.valueOf(clrs[i][0])));
                P[i+1].setBackground(Color.decode(String.valueOf(clrs[i][1])));
                I[i+1].setBackground(Color.decode(String.valueOf(clrs[i][2])));
        }
        }catch(Exception e){System.out.print(" (error in getting colour pelatte)");}
    }
    
    void PaneDisplay(String[][] getTheme,int val){
        themeVal=val;
        panframeinn.setBackground(Color.decode(getTheme[0][6]));
        panframe.setBackground(Color.decode(getTheme[0][5]));
        paninn.setBackground(Color.decode(getTheme[0][4]));
        pensel.setBackground(Color.decode(getTheme[0][3]));
        panmenu.setBackground(Color.decode(getTheme[0][2]));
        panpan.setBackground(Color.decode(getTheme[0][1]));
        panout.setBackground(Color.decode(getTheme[0][0]));
    }
    
    void adddata(String o,String p,String m,String s,String i,String fo,String fi){
        try{
            Class.forName("java.sql.Driver");
            Connection conn= DriverManager.getConnection(CONNSTRING, USERNAME, PASS);
            Statement stmt = conn.createStatement();
            
               String qry = "insert into colourmg values("
                       + "\"" +o+ "\","
                       + "\"" +p+ "\","
                       + "\"" +m+ "\","
                       + "\"" +s+ "\","
                       + "\"" +i+ "\","
                       + "\"" +fo+"\","
                       + "\"" +fi+"\","
                       + id+ ");";
            
            stmt.executeUpdate(qry);
            
            stmt.close();
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + " inside add data");
        }
    }
    
    void CountData(){
        id=1;
    try{
            Class.forName("java.sql.Driver");
            Connection conn= DriverManager.getConnection(CONNSTRING, USERNAME, PASS);
            Statement stmt = conn.createStatement();
               String qry = "select * from colourmg;";
            
            ResultSet rs =stmt.executeQuery(qry);
            while(rs.next()){
                id++;
            }
            stmt.close();
            conn.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e + " inside countdata");
        }
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        panout = new javax.swing.JPanel();
        panpan = new javax.swing.JPanel();
        panmenu = new javax.swing.JPanel();
        pensel = new javax.swing.JPanel();
        paninn = new javax.swing.JPanel();
        panframe = new javax.swing.JPanel();
        panframeinn = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        o5 = new javax.swing.JPanel();
        p5 = new javax.swing.JPanel();
        i5 = new javax.swing.JPanel();
        o9 = new javax.swing.JPanel();
        p9 = new javax.swing.JPanel();
        i9 = new javax.swing.JPanel();
        o8 = new javax.swing.JPanel();
        p8 = new javax.swing.JPanel();
        i8 = new javax.swing.JPanel();
        o7 = new javax.swing.JPanel();
        p7 = new javax.swing.JPanel();
        i7 = new javax.swing.JPanel();
        o6 = new javax.swing.JPanel();
        p6 = new javax.swing.JPanel();
        i6 = new javax.swing.JPanel();
        o2 = new javax.swing.JPanel();
        p2 = new javax.swing.JPanel();
        i2 = new javax.swing.JPanel();
        o4 = new javax.swing.JPanel();
        p4 = new javax.swing.JPanel();
        i4 = new javax.swing.JPanel();
        o3 = new javax.swing.JPanel();
        p3 = new javax.swing.JPanel();
        i3 = new javax.swing.JPanel();
        o1 = new javax.swing.JPanel();
        p1 = new javax.swing.JPanel();
        i1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(79, 33));

        jPanel1.setBackground(new java.awt.Color(117, 148, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(938, 744));

        jLabel1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        jLabel1.setText("Apperence");

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel2.setText("Outline");

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel3.setText("Panel");

        jLabel4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel4.setText("Menu");

        jLabel5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel5.setText("Selected");

        jLabel6.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel6.setText("Inner Frames");

        jTextField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField1FocusLost(evt);
            }
        });

        jTextField2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField2FocusLost(evt);
            }
        });

        jTextField3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField3FocusLost(evt);
            }
        });

        jTextField4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField4FocusLost(evt);
            }
        });

        jTextField5.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField5FocusLost(evt);
            }
        });

        javax.swing.GroupLayout penselLayout = new javax.swing.GroupLayout(pensel);
        pensel.setLayout(penselLayout);
        penselLayout.setHorizontalGroup(
            penselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 64, Short.MAX_VALUE)
        );
        penselLayout.setVerticalGroup(
            penselLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 13, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panmenuLayout = new javax.swing.GroupLayout(panmenu);
        panmenu.setLayout(panmenuLayout);
        panmenuLayout.setHorizontalGroup(
            panmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panmenuLayout.createSequentialGroup()
                .addComponent(pensel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panmenuLayout.setVerticalGroup(
            panmenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panmenuLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pensel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panframeLayout = new javax.swing.GroupLayout(panframe);
        panframe.setLayout(panframeLayout);
        panframeLayout.setHorizontalGroup(
            panframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 29, Short.MAX_VALUE)
        );
        panframeLayout.setVerticalGroup(
            panframeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panframeinnLayout = new javax.swing.GroupLayout(panframeinn);
        panframeinn.setLayout(panframeinnLayout);
        panframeinnLayout.setHorizontalGroup(
            panframeinnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 26, Short.MAX_VALUE)
        );
        panframeinnLayout.setVerticalGroup(
            panframeinnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout paninnLayout = new javax.swing.GroupLayout(paninn);
        paninn.setLayout(paninnLayout);
        paninnLayout.setHorizontalGroup(
            paninnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, paninnLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panframeinn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panframe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        paninnLayout.setVerticalGroup(
            paninnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paninnLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(paninnLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panframe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panframeinn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panpanLayout = new javax.swing.GroupLayout(panpan);
        panpan.setLayout(panpanLayout);
        panpanLayout.setHorizontalGroup(
            panpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panpanLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(paninn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panpanLayout.setVerticalGroup(
            panpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panpanLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panpanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panpanLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panmenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(paninn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout panoutLayout = new javax.swing.GroupLayout(panout);
        panout.setLayout(panoutLayout);
        panoutLayout.setHorizontalGroup(
            panoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panoutLayout.setVerticalGroup(
            panoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panoutLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panpan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jButton2.setText("Reset To Defaults");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Retrive Data");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTextField6.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField6FocusLost(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel7.setText("Frame inner");

        jTextField7.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField7FocusLost(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jLabel8.setText("Frame Outer");

        jButton1.setText("Apply");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(117, 154, 228));

        o5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i5Layout = new javax.swing.GroupLayout(i5);
        i5.setLayout(i5Layout);
        i5Layout.setHorizontalGroup(
            i5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        i5Layout.setVerticalGroup(
            i5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p5Layout = new javax.swing.GroupLayout(p5);
        p5.setLayout(p5Layout);
        p5Layout.setHorizontalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p5Layout.setVerticalGroup(
            p5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o5Layout = new javax.swing.GroupLayout(o5);
        o5.setLayout(o5Layout);
        o5Layout.setHorizontalGroup(
            o5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o5Layout.setVerticalGroup(
            o5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i9Layout = new javax.swing.GroupLayout(i9);
        i9.setLayout(i9Layout);
        i9Layout.setHorizontalGroup(
            i9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        i9Layout.setVerticalGroup(
            i9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p9Layout = new javax.swing.GroupLayout(p9);
        p9.setLayout(p9Layout);
        p9Layout.setHorizontalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p9Layout.setVerticalGroup(
            p9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o9Layout = new javax.swing.GroupLayout(o9);
        o9.setLayout(o9Layout);
        o9Layout.setHorizontalGroup(
            o9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o9Layout.setVerticalGroup(
            o9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i8Layout = new javax.swing.GroupLayout(i8);
        i8.setLayout(i8Layout);
        i8Layout.setHorizontalGroup(
            i8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        i8Layout.setVerticalGroup(
            i8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p8Layout = new javax.swing.GroupLayout(p8);
        p8.setLayout(p8Layout);
        p8Layout.setHorizontalGroup(
            p8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p8Layout.setVerticalGroup(
            p8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o8Layout = new javax.swing.GroupLayout(o8);
        o8.setLayout(o8Layout);
        o8Layout.setHorizontalGroup(
            o8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o8Layout.setVerticalGroup(
            o8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i7Layout = new javax.swing.GroupLayout(i7);
        i7.setLayout(i7Layout);
        i7Layout.setHorizontalGroup(
            i7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        i7Layout.setVerticalGroup(
            i7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p7Layout = new javax.swing.GroupLayout(p7);
        p7.setLayout(p7Layout);
        p7Layout.setHorizontalGroup(
            p7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p7Layout.setVerticalGroup(
            p7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o7Layout = new javax.swing.GroupLayout(o7);
        o7.setLayout(o7Layout);
        o7Layout.setHorizontalGroup(
            o7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o7Layout.setVerticalGroup(
            o7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        o6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i6Layout = new javax.swing.GroupLayout(i6);
        i6.setLayout(i6Layout);
        i6Layout.setHorizontalGroup(
            i6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        i6Layout.setVerticalGroup(
            i6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p6Layout = new javax.swing.GroupLayout(p6);
        p6.setLayout(p6Layout);
        p6Layout.setHorizontalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p6Layout.setVerticalGroup(
            p6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o6Layout = new javax.swing.GroupLayout(o6);
        o6.setLayout(o6Layout);
        o6Layout.setHorizontalGroup(
            o6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o6Layout.setVerticalGroup(
            o6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i2Layout = new javax.swing.GroupLayout(i2);
        i2.setLayout(i2Layout);
        i2Layout.setHorizontalGroup(
            i2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        i2Layout.setVerticalGroup(
            i2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p2Layout = new javax.swing.GroupLayout(p2);
        p2.setLayout(p2Layout);
        p2Layout.setHorizontalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p2Layout.setVerticalGroup(
            p2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o2Layout = new javax.swing.GroupLayout(o2);
        o2.setLayout(o2Layout);
        o2Layout.setHorizontalGroup(
            o2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o2Layout.setVerticalGroup(
            o2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i4Layout = new javax.swing.GroupLayout(i4);
        i4.setLayout(i4Layout);
        i4Layout.setHorizontalGroup(
            i4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 44, Short.MAX_VALUE)
        );
        i4Layout.setVerticalGroup(
            i4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p4Layout = new javax.swing.GroupLayout(p4);
        p4.setLayout(p4Layout);
        p4Layout.setHorizontalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        p4Layout.setVerticalGroup(
            p4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o4Layout = new javax.swing.GroupLayout(o4);
        o4.setLayout(o4Layout);
        o4Layout.setHorizontalGroup(
            o4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o4Layout.setVerticalGroup(
            o4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i3Layout = new javax.swing.GroupLayout(i3);
        i3.setLayout(i3Layout);
        i3Layout.setHorizontalGroup(
            i3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        i3Layout.setVerticalGroup(
            i3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p3Layout = new javax.swing.GroupLayout(p3);
        p3.setLayout(p3Layout);
        p3Layout.setHorizontalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, p3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        p3Layout.setVerticalGroup(
            p3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o3Layout = new javax.swing.GroupLayout(o3);
        o3.setLayout(o3Layout);
        o3Layout.setHorizontalGroup(
            o3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o3Layout.setVerticalGroup(
            o3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        o1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                o1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout i1Layout = new javax.swing.GroupLayout(i1);
        i1.setLayout(i1Layout);
        i1Layout.setHorizontalGroup(
            i1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 46, Short.MAX_VALUE)
        );
        i1Layout.setVerticalGroup(
            i1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 37, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout p1Layout = new javax.swing.GroupLayout(p1);
        p1.setLayout(p1Layout);
        p1Layout.setHorizontalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(1, 1, 1))
        );
        p1Layout.setVerticalGroup(
            p1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(p1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(i1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout o1Layout = new javax.swing.GroupLayout(o1);
        o1.setLayout(o1Layout);
        o1Layout.setHorizontalGroup(
            o1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        o1Layout.setVerticalGroup(
            o1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(o1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(o7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(o4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(o1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(o2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(o8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(o3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(o9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(o6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(o3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(o6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(o9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(o1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(o4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(o7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(o2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(o5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(o8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jButton4.setText("Add new");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(73, 73, 73)
                                .addComponent(jButton2)
                                .addGap(147, 147, 147))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(panout, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(28, 28, 28)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(27, 27, 27)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))))
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(83, 83, 83)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField5))
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField6))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jButton3)))
                .addGap(228, 228, 228))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(jButton1))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton2))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 868, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(141, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String[] cols = mast.colours();
        jTextField1.setText(cols[0]);
        jTextField2.setText(cols[1]);
        jTextField3.setText(cols[2]);
        jTextField4.setText(cols[3]);
        jTextField5.setText(cols[4]);
        
        //request focus
        jTextField1.requestFocus();
        jTextField2.requestFocus();
        jTextField3.requestFocus();
        jTextField4.requestFocus();
        jTextField5.requestFocus();
        jTextField6.requestFocus();
        jTextField7.requestFocus();
        jTextField1.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
ColourMgmt c  = new ColourMgmt();
String s1,s2,s3,s4,s5,s6,s7;
s1 = jTextField1.getText();
s2=jTextField2.getText();
s3=jTextField3.getText();
s4=jTextField4.getText();
s5=jTextField5.getText();
s6=jTextField6.getText();
s7=jTextField7.getText();
if(s1.length()==0||s2.length()==0||s3.length()==0||s4.length()==0||s5.length()==0||s6.length()==0||s7.length()==0){ 
    getColors(0,false);}
else{
c.Callall(s1, s2, s3, s4, s5,s6,s7);
mast.getSettingobj(c);
mast.coloursOverride();}
//try{
//writeFiles();
//}catch(Exception e){
//    JOptionPane.showMessageDialog(null, e);
//}

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField1FocusLost
if (!jTextField1.getText().equals("")){
    panout.setBackground(Color.decode(jTextField1.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1FocusLost

    private void jTextField2FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField2FocusLost
if (!jTextField2.getText().equals("")){
    panpan.setBackground(Color.decode(jTextField2.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2FocusLost

    private void jTextField3FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField3FocusLost
if (!jTextField3.getText().equals("")){
    panmenu.setBackground(Color.decode(jTextField3.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3FocusLost

    private void jTextField4FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField4FocusLost
if (!jTextField4.getText().equals("")){
    pensel.setBackground(Color.decode(jTextField4.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4FocusLost

    private void jTextField5FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField5FocusLost
if (!jTextField5.getText().equals("")){
    paninn.setBackground(Color.decode(jTextField5.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5FocusLost

    private void jTextField6FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField6FocusLost
if (!jTextField6.getText().equals("")){
    panframe.setBackground(Color.decode(jTextField6.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6FocusLost

    private void jTextField7FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField7FocusLost
if (!jTextField7.getText().equals("")){
    panframeinn.setBackground(Color.decode(jTextField7.getText()));
}        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7FocusLost

    private void o1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o1MouseClicked
getColors(1,false);
    }//GEN-LAST:event_o1MouseClicked

    private void o2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o2MouseClicked
getColors(2,false);
    }//GEN-LAST:event_o2MouseClicked

    private void o3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o3MouseClicked

    getColors(3,false);
    }//GEN-LAST:event_o3MouseClicked

    private void o4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o4MouseClicked

    getColors(4,false);
    }//GEN-LAST:event_o4MouseClicked

    private void o5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o5MouseClicked

    getColors(5,false);
    }//GEN-LAST:event_o5MouseClicked

    private void o6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o6MouseClicked

    getColors(6,false);
    }//GEN-LAST:event_o6MouseClicked

    private void o7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o7MouseClicked

    getColors(7,false);

    }//GEN-LAST:event_o7MouseClicked

    private void o8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o8MouseClicked

    getColors(8,false);
          // TODO add your handling code here:
    }//GEN-LAST:event_o8MouseClicked

    private void o9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_o9MouseClicked

    getColors(9,false);
          // TODO add your handling code here:
    }//GEN-LAST:event_o9MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

      //  getColors(themeVal,true);

ColourMgmt c  = new ColourMgmt();
c.Callall(clrs[0][0],clrs[0][1], clrs[0][2],clrs[0][3], clrs[0][4],clrs[0][5],clrs[0][6]);
mast.getSettingobj(c);
mast.coloursOverride();
try{
writeFiles();
}catch(Exception e){
    JOptionPane.showMessageDialog(null, e);
}
//getColors(0,false);        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
CountData();
adddata(jTextField1.getText(),jTextField2.getText(),jTextField3.getText(),
        jTextField4.getText(),jTextField5.getText(),jTextField6.getText(),jTextField7.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel i1;
    private javax.swing.JPanel i2;
    private javax.swing.JPanel i3;
    private javax.swing.JPanel i4;
    private javax.swing.JPanel i5;
    private javax.swing.JPanel i6;
    private javax.swing.JPanel i7;
    private javax.swing.JPanel i8;
    private javax.swing.JPanel i9;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JPanel o1;
    private javax.swing.JPanel o2;
    private javax.swing.JPanel o3;
    private javax.swing.JPanel o4;
    private javax.swing.JPanel o5;
    private javax.swing.JPanel o6;
    private javax.swing.JPanel o7;
    private javax.swing.JPanel o8;
    private javax.swing.JPanel o9;
    private javax.swing.JPanel p1;
    private javax.swing.JPanel p2;
    private javax.swing.JPanel p3;
    private javax.swing.JPanel p4;
    private javax.swing.JPanel p5;
    private javax.swing.JPanel p6;
    private javax.swing.JPanel p7;
    private javax.swing.JPanel p8;
    private javax.swing.JPanel p9;
    private javax.swing.JPanel panframe;
    private javax.swing.JPanel panframeinn;
    private javax.swing.JPanel paninn;
    private javax.swing.JPanel panmenu;
    private javax.swing.JPanel panout;
    private javax.swing.JPanel panpan;
    private javax.swing.JPanel pensel;
    // End of variables declaration//GEN-END:variables
}

