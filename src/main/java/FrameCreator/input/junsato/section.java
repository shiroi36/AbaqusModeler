/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameCreator.input.junsato;

import java.util.Arrays;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class section {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private int i;
    private int id;
    private String mtype=null;
    private String membname=null;
    private String direc=null;
    private HSection hsec=null;
    private double[] xface=null;
    private double[] yface=null;
    private double[] bbfac=null;
    private String btype;
    private String sectype;
    
    public section(String[] a){
//        a[0];//node
        i=0;
        while(i<a.length) {
            if("code".equals(a[i].toLowerCase())){
                this.setCode(a);
                i++;
            }else if("hweak".equals(a[i].toLowerCase())||"hkyou".equals(a[i].toLowerCase())){
                this.sectype=a[i];
                this.setSectionDirection(a);
                i++;
            }else if("xface".equals(a[i].toLowerCase())){
                this.setXFace(a);
                i++;
            }else if("yface".equals(a[i].toLowerCase())){
                this.setYFace(a);
                i++;
            }else if("bbfac".equals(a[i].toLowerCase())){
                this.setBBFac(a);
                i++;
            }else{
                i++;
            }
        }
        
        
        
    }
    
    
    
    public void setCode(String[] a){
        i++;
        id=Integer.parseInt(a[i]);
        i++;
        mtype=a[i];
        i++;
        btype=a[i];
        i++;
        membname=a[i].replaceAll("\"", "").replaceAll("=", "");
//        .split("\"")[2].split("=")[0];
    }
    public int getID(){return id;}
    public String getMemberType(){return btype;}
    public String getMemberName(){return membname;}
    public String getSecType(){return sectype;}
    public HSection getHSection(){return hsec;}
    
    public void setSectionDirection(String[] a){
        direc=a[i];
        i++;
        double h=Double.parseDouble(a[i])*10;
        i++;
        double b=Double.parseDouble(a[i])*10;
        i++;
        double tw=Double.parseDouble(a[i])*10;
        i++;
        double tf=Double.parseDouble(a[i])*10;
        hsec=new HSection(h,b,tw,tf);
        
    }
    
    public void setXFace(String[] a){
        i++;
        double v1=Double.parseDouble(a[i]);
        i++;
        double v2=Double.parseDouble(a[i]);
        xface=new double[]{v1,v2};
    }
    public void setYFace(String[] a){
        i++;
        double v1=Double.parseDouble(a[i]);
        i++;
        double v2=Double.parseDouble(a[i]);
        yface=new double[]{v1,v2};
    }
    public void setBBFac(String[] a){
        i++;
        double v1=Double.parseDouble(a[i]);
        i++;
        double v2=Double.parseDouble(a[i]);
        bbfac=new double[]{v1,v2};
    }
    
    public void print(){
        System.out.println("id = " + id);
        System.out.println("mtype = " + mtype);
        System.out.println("btype = " + btype);
        System.out.println("membname = " + membname);
        if(hsec!=null)System.out.println(Arrays.deepToString(hsec.print()));
        if(xface!=null)System.out.println(Arrays.toString(xface));
        if(yface!=null)System.out.println(Arrays.toString(yface));
        if(bbfac!=null)System.out.println(Arrays.toString(bbfac));
    }
    
    
}
