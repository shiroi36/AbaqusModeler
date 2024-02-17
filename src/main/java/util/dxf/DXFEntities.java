/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.dxf;

import util.coord.CoordXYZ;

/**
 *
 * @author keita
 */
public class DXFEntities {

    public static final boolean ENTITIES_2D=true;
    public static final boolean ENTITIES_3D=false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private String entities;
    private double r;
    private double t;
    private boolean flag;
    private double dx;
    private double dy;
    private double dz;
    private double px1;
    private double py1;
    private double pz1;
    private double pz2;
    private double py2;
    private double px2;
    private String layer;
    
    public DXFEntities(){
        this.flag=DXFEntities.ENTITIES_2D;
    }
    
    public void setInfomation(DXFpiece p){
        int code=p.getGroupCode();
        String value=p.getValue();
        
        if(code==0)this.entities=value.toLowerCase();
        else if(code==8)this.layer=value;
        else if(code==10)this.px1=Double.parseDouble(value);
        else if(code==20)this.py1=Double.parseDouble(value);
        else if(code==30)this.pz1=Double.parseDouble(value);
        else if(code==11)this.px2=Double.parseDouble(value);
        else if(code==21)this.py2=Double.parseDouble(value);
        else if(code==31)this.pz2=Double.parseDouble(value);
        else if(code==39)this.t=Double.parseDouble(value);
        else if(code==40)this.r=Double.parseDouble(value);
        else if(code==210)this.dx=Double.parseDouble(value);
        else if(code==220)this.dy=Double.parseDouble(value);
        else if(code==230)this.dz=Double.parseDouble(value);
        
        if(code==39||code==210||code==220||code==230)
            this.flag=DXFEntities.ENTITIES_3D;
    }
    
    public CoordXYZ getExtrusionDirection(){return new CoordXYZ(dx,dy,dz);}
    public CoordXYZ getPosition1(){return new CoordXYZ(px1,py1,pz1);}
    public CoordXYZ getPosition2(){return new CoordXYZ(px2,py2,pz2);}
    public String getLayerName(){return layer;}
    public double getThickness(){return t;}
    public double getR(){return r;}
    public String getEntitiyTitle(){return entities;}
    public boolean getDimensionFlag(){return flag;}   
    public void print(){
        System.out.println("entities = " + entities);
        System.out.println("r = " + r);
        System.out.println("t = " + t);
        System.out.println("flag = " + flag);
        new CoordXYZ(dx,dy,dz).print();
        new CoordXYZ(px1,py1,pz1).print();
        new CoordXYZ(px2,py2,pz2).print();
    }
}
