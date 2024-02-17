/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.ID;

import java.util.ArrayList;
import util.CircleInfo;
import util.coord.CoordXYZ;
import util.section.HSection;


/**
 *
 * @author keita
 */
public final class CGID {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CGID sh=new CGID("SH400-16-05-05-M20@75",new HSection(400,300,12,20,13));
        CoordXYZ[] pos=sh.getBoltPosition();
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            po.print();
        }
    }
    public final static String ICON_SH="cg";
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
    private final HSection colsec;
    
    public CGID(String name,HSection colsec){
        //SH400-16-04-04-M20@75
        this.colsec=colsec;
        this.header=name.substring(0, 5).toUpperCase();
        this.t=Double.parseDouble(name.substring(6, 8));
        this.boltnum1=Integer.parseInt(name.substring(9, 11));
        this.boltnum2=Integer.parseInt(name.substring(12, 14));
        this.bolttype=Integer.parseInt(name.substring(16, 18));
        this.pitch=Double.parseDouble(name.substring(19, 21));
        this.name=header+"-"+(int)t+"-"+boltnum1
                +"-"+boltnum2+"-"+"M"+(int)bolttype+"@"+(int)pitch+"-"+colsec.getName();
    }
    
    
    public String getHeader(){return header;}
    public double getThickness(){return t;}
    public int getBoltnum(){return boltnum1+boltnum2;}
    public int getBoltType(){return bolttype;}
    public double getBoltPitch(){return pitch;}
    public String getName(){return name;}
    public double getWidth(){
        double b=colsec.getB()/2-colsec.getWebThickness()/2;
        if(boltnum2==0)return b+100;
        else return b+160;}
    public CoordXYZ[] getCornerPoint(){
        double h=this.getHeight();
        double w=this.getWidth();
        double b=colsec.getB()/2-colsec.getWebThickness()/2+20;
        CoordXYZ p0=new CoordXYZ(0,h/2,-b);
        CoordXYZ p1=new CoordXYZ(0,-h/2,-b);
        CoordXYZ p2=new CoordXYZ(0,h/2,w-b);
        CoordXYZ p3=new CoordXYZ(0,-h/2,w-b);
        return new CoordXYZ[]{p0,p1,p2,p3};
    }
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
    public double getHeight(){return (boltnum1-1)*pitch+80;}
}
