/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHolder;
/**
 *
 * @author ADMINISTOR
 */
public class masterconfig {
    String name;
        String id;
    public masterconfig(String n){
        name=n;
    }
    public void getname(String nam){
    name=nam;
    }
    public masterconfig(){
        name="UNKNOWN";
    }
    public String fun(){
    return name;
    }
}
