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
public final class CFRID {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="dxf\\";
    }
    public static String ICON_CFR="cfr";
    private final double t;
    private final String header;
    private final String name;
    private final double b;
    private final double phi;
    private final double p;
    
    public static final String CFR400="CFR400";
    public static final String CFR490="CFR490";
    
    public CFRID(String header,double t,double b,double phi,double p) {
        this.header=header;
        this.t=t;
        this.b=b;
        this.phi=phi;
        this.p=p;
        this.name=header+"-"+(int)t+"-"+b+"-"+"Φ"+phi+"@"+p;
    }
    
    public String getHeader(){return header;}
    public double getThickness(){return t;}
    public double getWidth(){return b;}
    public double getLooseHolePhi(){return phi;}
    public double getCFHolePhi(){return phi-14;}
    public double getLooseHolePitch(){return p;}
    public String getName(){return name;}
    public double getLooseHoleLength(){return 150;}
    
}
