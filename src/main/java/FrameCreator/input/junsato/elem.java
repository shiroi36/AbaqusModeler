/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameCreator.input.junsato;

/**
 *
 * @author keita
 */
public class elem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private int i;
    private int esect;
    private int id;
    private double cang;
    private int enods;
    private String type;
    private int[][] bonds;
    private double[][] cmq;
    private int[] enod;
    
    public elem(String[] a){
//        a[0];//node
        i=0;
        while(i<a.length) {
            if("elem".equals(a[i].toLowerCase())){
                this.setElem(a);
                i++;
            }else if("esect".equals(a[i].toLowerCase())){
                this.setEsect(a);
                i++;
            }else if("cang".equals(a[i].toLowerCase())){
                this.setCang(a);
                i++;
            }else if("enods".equals(a[i].toLowerCase())){
                this.setENODS(a);
                i++;
            }else if("enod".equals(a[i].toLowerCase())){
                this.setENOD(a);
                i++;
            }else if("bonds".equals(a[i].toLowerCase())){
                this.setBONDS(a);
                i++;
            }else if("cmq".equals(a[i].toLowerCase())){
                this.setCMQ(a);
                i++;
            }else if("type".equals(a[i].toLowerCase())){
                this.setType(a);
                i++;
            }else{
                i++;
            }
        }
        
        
        
    }
    
    public void setBONDS(String[] a){
        bonds=new int[enods][6];
        for (int j = 0; j < bonds.length; j++) {
            for (int k = 0; k < 6; k++) {
                i++;
                bonds[j][k]=Integer.parseInt(a[i]);
            }
        }
    }
    
    public int[][] getBond(){return bonds;}
    
    public void setCMQ(String[] a){
        cmq=new double[enods][6];
        for (int j = 0; j < cmq.length; j++) {
            for (int k = 0; k < 6; k++) {
                i++;
                cmq[j][k]=Double.parseDouble(a[i]);
            }
        }
    }
    public void setENOD(String[] a){
        enod=new int[enods];
//        System.out.println(enods);
        for (int j = 0; j < enod.length; j++) {
            i++;
//            System.out.println(a[i]);
            enod[j]=Integer.parseInt(a[i]);
        }
    }
    public int[] getENode(){return enod;}
    
    public void setType(String[] a){
        i++;
        type=a[i];
    }
    public String getType(){return type;}
    public void setENODS(String[] a){
        i++;
        enods=Integer.parseInt(a[i]);
    }
    
    public void setElem(String[] a){
        i++;
        id=Integer.parseInt(a[i]);
    }
    public int getID(){return id;}
    
    public void setEsect(String[] a){
        i++;
        esect=Integer.parseInt(a[i]);
    }
    public int getSectID(){return esect;}
    
    public void setCang(String[] a){
        i++;
        cang=Double.parseDouble(a[i]);
    }
    
    
}
