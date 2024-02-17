/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.BID;
import JointCreator.ID.PlateID;
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
import java.util.Arrays;
import util.coord.CoordXYZ;
import util.section.HSection;
import util.io.TXT_OPE;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class PlateGenerator{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        double[] p={40,60,85};
        double[] g={40,70,40};
        double d=24;
        double t=16;
        
        PlateID pid=new PlateID(p,g,d,t);
        PlateGenerator gp=new PlateGenerator(pid);
        DXFDrawer dxf =new DXFDrawer("test.dxf");
        dxf.setHeader();
        gp.draw(dxf,false);
        dxf.setEnd();
        dxf.Finish();
        
//        gp.setHeader();
//        gp.Draw1("");
//        gp.Draw2("");
//        gp.Draw3("");
//        gp.setEnd();
//        gp.Finish();
        
        System.out.println(gp.getname());
    }
    
    private PlateID pid;
    
    public PlateGenerator(PlateID pid) {
        this.pid=pid;
    }
    
    
    
    public String getname() {
        return "H-" + (int) pid.getLx() + "×" + (int) pid.getLy() + "×" + (int) pid.getThickness();
    }
    
    
    public void draw(DXFDrawer dxf,boolean flag){
        //head.txtの読み込み
        double[] p=pid.getAccumulatePitch();
        double[] g=pid.getAccumulateGage();
        double lx=pid.getLx();
        double ly=pid.getLy();
        double d=pid.getDiameter();
        double t=pid.getThickness();
        if(!flag)t=0;
        
        dxf.drawLine3D("0", new CoordXYZ(0, 0-0.5*ly,0),new CoordXYZ(lx, 0-0.5*ly,0),new CoordXYZ(0,0,1),t);
        dxf.drawLine3D("0", new CoordXYZ(0, ly-0.5*ly,0),new CoordXYZ(lx, ly-0.5*ly,0),new CoordXYZ(0,0,1),t);
     
        dxf.drawLine3D("0",new CoordXYZ( 0,0-0.5*ly,0),new CoordXYZ(0, ly-0.5*ly,0),new CoordXYZ(0,0,1),t);
        dxf.drawLine3D("0", new CoordXYZ(lx,0-0.5*ly,0),new CoordXYZ(lx, ly-0.5*ly,0),new CoordXYZ(0,0,1),t);
        
        for (int i = 1; i < g.length; i++) {
            double g1 = g[i];
            if(i==g.length-1)continue;
            for (int j = 1; j < p.length; j++) {
                double p1 = p[j];
                if(j==p.length-1)continue;
                dxf.drawCircle3D("0", new CoordXYZ(p1, g1-0.5*ly,0), d/2,new CoordXYZ(0,0,1),t);
            }
        }
        
    }
    
    public void Draw1_OnlyBolthole(DXFDrawer dxf){
        //head.txtの読み込み
        double[] p=pid.getAccumulatePitch();
        double[] g=pid.getAccumulateGage();
        double lx=pid.getLx();
        double ly=pid.getLy();
        double d=pid.getDiameter();
        double t=pid.getThickness();
        
        for (int i = 1; i < g.length; i++) {
            double g1 = g[i];
            if(i==g.length-1)continue;
            for (int j = 1; j < p.length; j++) {
                double p1 = p[j];
                if(j==p.length-1)continue;
                dxf.drawCircle3D("0", new CoordXYZ(p1, g1-0.5*ly,0), d/2,new CoordXYZ(0,0,1),t);
            }
        }
        
    }
    
    
}
