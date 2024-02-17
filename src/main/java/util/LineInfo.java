/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;
import util.coord.CoordXYZ;

/**
 *
 * @author keita
 */
public class LineInfo{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final CoordXYZ s;
    private final CoordXYZ g;
    private CoordXYZ e;
    private double t;
    private String layer;
    public LineInfo(CoordXYZ s,CoordXYZ g){
        this.s=s;
        this.g=g;
        e=null;
        t=0;
    }
    public LineInfo(CoordXYZ s,CoordXYZ g,CoordXYZ extrude,double t){
        this.s=s;
        this.g=g;
        this.e=extrude;
        this.t=t;
    }
    public LineInfo(String layer,CoordXYZ s,CoordXYZ g,CoordXYZ extrude,double t){
        this.layer=layer;
        this.s=s;
        this.g=g;
        this.e=extrude;
        this.t=t;
    }
    public CoordXYZ getExtrudeDirection(){return e;};
    public double getThickness(){return t;}
    public CoordXYZ getStartPoint(){return s;}
    public CoordXYZ getEndPoint(){return g;}
    public String getLayerName(){return layer;}
    public double getLineLength(){
        double x=Math.pow(s.getX()-g.getX(), 2);
        double y=Math.pow(s.getY()-g.getY(), 2);
        double z=Math.pow(s.getZ()-g.getZ(), 2);
        return Math.sqrt(x+y+z);
    }
    public CoordXYZ getDirection(){
        double l=this.getLineLength();
        RealVector rv=new ArrayRealVector(3);
        rv.addToEntry(0, (g.getX()-s.getX())/l);
        rv.addToEntry(1, (g.getY()-s.getY())/l);
        rv.addToEntry(2, (g.getZ()-s.getZ())/l);
        return new CoordXYZ(rv.getEntry(0),rv.getEntry(1),rv.getEntry(2));
    }
    
    
}
