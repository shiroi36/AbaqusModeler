/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.ID;

import java.util.ArrayList;
import util.CircleInfo;
import util.coord.CoordXYZ;


/**
 *
 * @author keita
 */
public final class SHID {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SHID sh=new SHID("SH400-16-05-05-M20@75");
        CoordXYZ[] pos=sh.getBoltPosition();
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            po.print();
        }
    }
    public final static String ICON_SH="sh";
    private  double t;
//    private final int boltnum;
    private double pitch;
    private String header;
    private String name;
    
    public final static String SH400="SH400";
    public final static String SH490="SH490";
    private int bolttype;
    private int boltnum2;
    private int boltnum1;
    
    public SHID(
            String header,double t,int boltnum,int bolttype,double pitch) {
        this.header=header;
        this.t=t;
        boltnum1=boltnum/2;
        boltnum2=boltnum/2;
        this.bolttype=bolttype;
        this.pitch=pitch;
        this.name=header+"-"+(int)t+"-"+boltnum+"M"+(int)bolttype+"@"+(int)pitch;
    }
    public SHID(String name){
        //SH400-16-04-04-M20@75
        this.header=name.substring(0, 5).toUpperCase();
        this.t=Double.parseDouble(name.substring(6, 8));
        this.boltnum1=Integer.parseInt(name.substring(9, 11));
        this.boltnum2=Integer.parseInt(name.substring(12, 14));
        this.bolttype=Integer.parseInt(name.substring(16, 18));
        this.pitch=Double.parseDouble(name.substring(19, 21));
//        System.out.println(header);
//        System.out.println(t);
//        System.out.println(boltnum1);
//        System.out.println(boltnum2);
//        System.out.println(bolttype);
//        System.out.println(pitch);
        this.name=header+"-"+(int)t+"-"+boltnum1+"-"+boltnum2+"-"+"M"+(int)bolttype+"@"+(int)pitch;
    }
    
    public SHID(
            String header,double t,int boltnum1,int boltnum2,int bolttype,double pitch) {
        this.header=header;
        this.t=t;
        this.boltnum1=boltnum1;
        this.boltnum2=boltnum2;
        this.bolttype=bolttype;
        this.pitch=pitch;
        this.name=header+"-"+(int)t+"-"+boltnum1+"-"+boltnum2+"-"+"M"+(int)bolttype+"@"+(int)pitch;
    }
    
    public String getHeader(){return header;}
    public double getThickness(){return t;}
    public int getBoltnum(){return boltnum1+boltnum2;}
    public int getBoltType(){return bolttype;}
    public double getBoltPitch(){return pitch;}
    public String getName(){return name;}
    public double getWidth(){
        if(boltnum2==0)return 100;
        else return 160;}
    public CoordXYZ[] getBoltPosition(){
        int pnum=boltnum1-1;
        boolean flag=true;
        if(boltnum1%2==1)flag=false;
        ArrayList<CoordXYZ> pos=new ArrayList<CoordXYZ>();
        while(pnum>0){
            double h=pnum*pitch;
            pos.add(new CoordXYZ(0,h/2,40));
            pos.add(new CoordXYZ(0,-h/2,40));
            pnum--;
            pnum--;
        }
        if(!flag)pos.add(new CoordXYZ(0,0,40));
        for (int i = 0; i < boltnum2; i++) {
            CoordXYZ pos2=pos.get(i);
            pos.add(new CoordXYZ(pos2.getX(),pos2.getY(),100));
        }
        return (CoordXYZ[])pos.toArray(new CoordXYZ[0]);
    }
    public double getShearPlateHeight(){return (boltnum1-1)*pitch+80;}
}
