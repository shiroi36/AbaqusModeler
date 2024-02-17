/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.dxf;

/**
 *
 * @author keita
 */
public class DXFpiece {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final int gc;
    private final String val;

    public DXFpiece(int groupcode,String value){
        this.gc=groupcode;
        this.val=value;
    }
    
    public int getGroupCode(){return gc;}
    
    public String getValue(){return val;}
    
    public void print(){
        System.out.println("code: "+gc+"\tvalue: "+val);
    }
    
}
