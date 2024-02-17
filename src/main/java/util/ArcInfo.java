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
public class ArcInfo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final CoordXYZ c;
    private final double r;
    private CoordXYZ e;
    private double t;
    private String layer;
    private final double s;
    private final double g;
    
    public ArcInfo(String layer,CoordXYZ c,double r,double s,double g,CoordXYZ extrude,double t){
        this.c=c;
        this.layer=layer;
        this.r=r;
        this.e=extrude;
        this.t=t;
        this.s=s;
        this.g=g;
    }
    public CoordXYZ getExtrudeDirection(){return e;};
    public double getThickness(){return t;}
    public CoordXYZ getCenterPoint(){return c;}
    public double getRadius(){return r;}
    public double getStartDeg(){return s;}
    public double getEndDeg(){return g;}
    public String getLayerName(){return layer;}
    
}
