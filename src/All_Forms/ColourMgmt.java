/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package All_Forms;

import java.awt.Color;

/**
 *
 * @author ADMINISTOR
 */
public class ColourMgmt {
    String c1="",c2="",c3="",c4="",c5="",c6="",c7="";
    String allclr[];
    
    public void Callall(){
    MainColour();IC();IC1();IC2();OF();OF1();OF2();
    }
    public void Callall(String s1,String s2,String s3,String s4,String s5,String s6,String s7){
    MainColour(s1);IC(s2);IC1(s3);IC2(s4);OF(s5);OF1(s6);OF2(s7);
    }
    //for outer colour
    public String MainColour(){
    c1 = "#67B16C";
    return c1;
    }
    public String MainColour(String c){
    //Color col=Color.blue ;
    c1 = c;
    return c1;
    }
    
    //for inner colour
    public String IC(){
    c2 = "#84AEDB";
    return c2;
    }
    public String IC(String c){
    c2 = c;
    return c2;
    }
    
    //for selections
    public String IC1(){
    c3 = "#71A2FF";
    return c3;
    }
    public  String IC1(String c){
    c3 = c;
    return c3;
    }
    
    //for selected
    public String IC2(){
    c4 = "#415391";
    return c4;
    }
    public String IC2(String c){
    c4 = c;
    return c4;
    }
    
    //for other frames
    public String OF(){
    c5 = "#7594CC";
    return c5;
    }
    public String OF(String c){
    c5 = c;
    return c5;
    }
    
    public String OF1(){
    c6 = "#3784FF";
    return c6;
    }
    public String OF1(String c){
    c6 = c;
    return c6;
    }
    
    public String OF2(){
    c7 = "#74BCFF";
    return c7;
    }
    public String OF2(String c){
    c7 = c;
    return c7;
    }
    
    public String[]  sendClrs(){
        allclr = new String[7];
        allclr[0] = c1;
        allclr[1] = c2;
        allclr[2] = c3;
        allclr[3] = c4;
        allclr[4] = c5;
        allclr[5] = c6;
        allclr[6] = c7;
    return allclr;
    }
}
