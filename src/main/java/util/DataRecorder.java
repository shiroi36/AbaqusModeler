/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.ArrayList;

/**
 *
 * @author keita
 */
public class DataRecorder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final ArrayList<String> val;
    
    public DataRecorder(){
        val=new ArrayList<String>();
    }
    
    public void setRecord(String input){val.add(input);}
    public String[] getRecord(){return (String[])val.toArray(new String[0]);}
}
