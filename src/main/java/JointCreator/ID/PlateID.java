/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.ID;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import util.section.HSection;
import util.io.TXT_OPE;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class PlateID {
    private final double[] p;
    private final double[] g;
    private final double d;
    private final double t;

    
    
    public PlateID(double[] pitch,double[] gage,double d,double t) {
       this.p=pitch;
       this.g=gage;
       this.d=d;
       this.t=t;
    }
    
    public double[] getPitch(){return p;}
    
    public double[] getAccumulatePitch(){
        double[] ap=new double[p.length+1];
        ap[0]=0;
        for (int i = 0; i < p.length; i++) {
            ap[i+1]=ap[i]+p[i];
        }
        return ap;
    }
    
    public double[] getAccumulateGage(){
        double[] ap=new double[g.length+1];
        ap[0]=0;
        for (int i = 0; i < g.length; i++) {
            ap[i+1]=ap[i]+g[i];
        }
        return ap;
    }
    
    public double getPlateThickness(){return t;}
    
    public double getLx(){
        double[] a=this.getAccumulatePitch();
        return a[a.length-1];
    }
    
    public double getLy(){
        double[] a=this.getAccumulateGage();
        return a[a.length-1];
    }
    
    public double getThickness(){return t;}
    
    public double[] getGage(){return g;}
    public double getDiameter(){return d;}
    
}
