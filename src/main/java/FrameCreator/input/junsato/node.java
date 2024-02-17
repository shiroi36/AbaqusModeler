/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameCreator.input.junsato;

import java.util.Arrays;

/**
 *
 * @author keita
 */
public class node {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final int id;
    private final double[] cord;
    private final int[] icon;
    private final double[] vcon;
    
    public node(String[] a){
//        a[0];//node
        id=Integer.parseInt(a[1]);
        cord=new double[]{
            Double.parseDouble(a[3])*1000,
            Double.parseDouble(a[4])*1000,
            Double.parseDouble(a[5])*1000,
        };
        icon=new int[]{
            Integer.parseInt(a[7]),
            Integer.parseInt(a[8]),
            Integer.parseInt(a[9]),
            Integer.parseInt(a[10]),
            Integer.parseInt(a[11]),
            Integer.parseInt(a[12])
        };
        vcon=new double[]{
            Double.parseDouble(a[14]),
            Double.parseDouble(a[15]),
            Double.parseDouble(a[16]),
            Double.parseDouble(a[17]),
            Double.parseDouble(a[18]),
            Double.parseDouble(a[19]),
        };
        
    }
    
    public void print(){
        System.out.println(id);
        System.out.println(Arrays.toString(cord));
        System.out.println(Arrays.toString(icon));
        System.out.println(Arrays.toString(vcon));
    }
    public int getID(){return id;}
    public double[] getCoord(){return cord;}
    
    
    
}
