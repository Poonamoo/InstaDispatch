/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHolder;

import static Sqlconnections.Sqlconns.*;
import java.util.Date;

/**
 *
 * @author ADMINISTOR
 */
public class Dstorage {
    Date date = new Date();
    public  String pname,paddress,pcity,pstate,dname,daddress,dcity,dstate,datess;
    public  String row,shiptype,stime,stype,content;
    public  String itemDetailQry, pickupQry, deliveryQry ;
    public  int length,breadth,height,pin1,pin2,samt;
    public float weight;
    public  long pphone,dphone;
    public  void pickupadd(String n,long p, String a, String c, String s, String qry1){
        pname = n;
        pphone = p;
        paddress = a;
        pcity = c;
        pstate = s;
        pickupQry = qry1;
    }
    public  void getdeladd(String n,long p, String a, String c, String s, String qry2){
        dname = n;
        dphone = p;
        daddress = a;
        dcity = c;
        dstate = s;
        deliveryQry = qry2;
    }
    
    public void itemdet(String r, String shipt,int l,int b,int h, float w,int p1, int p2,
            String ty, String ti, int amt, String cnt, String qry, String dat,String pc,String ps,String dc,String ds){
    row=r;
    shiptype =shipt;
    length = l;
    breadth = b;
    height =h;
    weight =w;
    pin1 =p1;
    pin2 =p2;
    stime = ti;
    stype = ty;
    samt = amt;
    content = cnt;
    itemDetailQry = qry;
    datess = dat;
    pcity =pc;
    pstate=ps;
    dcity=dc;
    dstate=ds;
//    JOptionPane.showMessageDialog(null, "heyyyy yo!!!" + "count " + row + " shiptype "+shiptype + " length " + length +
//            " breadth " + breadth + " height " + height + " weight " + weight + " pin1 " +pin1 + " pin2 "
//            + pin2 + " stime " + stime + " stype " +stype + " samt "+ samt + " content " + content
//    );
    }
    
    public void excqry(String qry){
        Updateqry(itemDetailQry);
    }
    public static void senddata(){
       // return(pname,paddress,dname,daddress);
                
    }
    
}
