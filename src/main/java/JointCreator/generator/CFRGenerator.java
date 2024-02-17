/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.CFRID;
import java.io.File;
import java.util.ArrayList;
import util.ArcInfo;
import util.LineInfo;
import util.coord.CoordXYZ;

/**
 *
 * @author keita
 */
public final class CFRGenerator implements Generator{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="dxf\\";
        String header="CFR400";
        double t=12;
        int b=216;
        double phi=36;
        double p=55;
        CFRID cfrid=new CFRID(CFRID.CFR400,t,b,phi,p);
        CFRGenerator a=new CFRGenerator("dxf/",cfrid);
        a.draw();
        
    }
    private String path;
    private final CFRID cfrid;
    
    
    
    public CFRGenerator(CFRID cfrid) {
        this("",cfrid);
    }
    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public CFRGenerator(String outputpath,CFRID cfrid) {
        this.path=outputpath;
        this.cfrid=cfrid;
    }
    
    
    public String getName(){return cfrid.getName();}
    
    public LineInfo[] getCFHoleLineInfo(double tcf){
        double p=cfrid.getLooseHolePitch();
        double phi=cfrid.getCFHolePhi();
        double t=tcf;
        CoordXYZ direc=new CoordXYZ(0,0,1);
        
        DXFDrawer dxf=new DXFDrawer(path+"cfr.dxf");
        dxf.setHeader();
        double[] a={1,-1};
        
        ArrayList<LineInfo> li=new ArrayList<LineInfo>();
        for (int i = 0; i < a.length; i++) {
            double a1 = a[i];
            li.add(new LineInfo("0", 
                    new CoordXYZ(a1*(p/2+phi/2), (75), 0),
                    new CoordXYZ(a1*(p/2+phi/2), (-75),0),direc,t));
            li.add(new LineInfo("0", 
                    new CoordXYZ(a1*(p/2-phi/2), (75),0), 
                    new CoordXYZ(a1*(p/2-phi/2), (-75),0),direc,t));            
        }
        return (LineInfo[])li.toArray(new LineInfo[0]);
    }
    
    public ArcInfo[] getCFHoleArcInfo(double tcf){
        double p=cfrid.getLooseHolePitch();
        double phi=cfrid.getCFHolePhi();
        double t=tcf;
        CoordXYZ direc=new CoordXYZ(0,0,1);
        
                
        ArrayList<ArcInfo> li=new ArrayList<ArcInfo>();
        li.add(new ArcInfo("0",new CoordXYZ( p / 2, 75,0),phi/2,0.0, 180.0,direc,t));
        li.add(new ArcInfo("0",new CoordXYZ( -p / 2, 75,0),phi/2,0, 180,direc,t));
        li.add(new ArcInfo("0",new CoordXYZ( p / 2, -75,0),phi/2,-180, 0,direc,t));
        li.add(new ArcInfo("0",new CoordXYZ( -p / 2, -75,0),phi/2,-180, 0,direc,t));
        return (ArcInfo[])li.toArray(new ArcInfo[0]);
    }
    
    public LineInfo[] getLineInfo(){
        double h=250;//シアプレート高さ
        double b=cfrid.getWidth();//シアプレート幅
        double p=cfrid.getLooseHolePitch();
        double phi=cfrid.getLooseHolePhi();
        double t=cfrid.getThickness();
        CoordXYZ direc=new CoordXYZ(0,0,1);
        
        DXFDrawer dxf=new DXFDrawer(path+"cfr.dxf");
        dxf.setHeader();
        double[] a={1,-1};
        
        ArrayList<LineInfo> li=new ArrayList<LineInfo>();
        li.add(new LineInfo("0", new CoordXYZ(-b/2, (h/2),0),new CoordXYZ(b/2, (h/2),0),direc,t));
        li.add(new LineInfo("0", new CoordXYZ(-b/2, -(h/2),0),new CoordXYZ(b/2, -(h/2),0),direc,t));
        
        
        for (int i = 0; i < a.length; i++) {
            double a1 = a[i];
            li.add(new LineInfo("0", 
                    new CoordXYZ(a1*(b/2), (-h/2), 0),
                    new CoordXYZ(a1*b/2, (h/2),0),direc,t));
            li.add(new LineInfo("0", 
                    new CoordXYZ(a1*(p/2+phi/2), (75), 0),
                    new CoordXYZ(a1*(p/2+phi/2), (-75),0),direc,t));
            li.add(new LineInfo("0", 
                    new CoordXYZ(a1*(p/2-phi/2), (75),0), 
                    new CoordXYZ(a1*(p/2-phi/2), (-75),0),direc,t));            
        }
        
        return (LineInfo[])li.toArray(new LineInfo[0]);
    }
    
    public ArcInfo[] getArcInfo(){
    
        ArrayList<ArcInfo> li=new ArrayList<ArcInfo>();
        double p=cfrid.getLooseHolePitch();
        double phi=cfrid.getLooseHolePhi();
        double t=cfrid.getThickness();
        CoordXYZ direc=new CoordXYZ(0,0,1);
                
        li.add(new ArcInfo("0",new CoordXYZ( p / 2, 75,0),phi/2,0.0, 180.0,direc,t));
        li.add(new ArcInfo("0",new CoordXYZ( -p / 2, 75,0),phi/2,0, 180,direc,t));
        li.add(new ArcInfo("0",new CoordXYZ( p / 2, -75,0),phi/2,-180, 0,direc,t));
        li.add(new ArcInfo("0",new CoordXYZ( -p / 2, -75,0),phi/2,-180, 0,direc,t));
        return (ArcInfo[])li.toArray(new ArcInfo[0]);
    }
    
    public void draw(){
        //head.txtの読み込み
        
        double h=250;//シアプレート高さ
        double b=cfrid.getWidth();//シアプレート幅
        double p=cfrid.getLooseHolePitch();
        double phi=cfrid.getLooseHolePhi();
        double t=cfrid.getThickness();
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        
        DXFDrawer dxf=new DXFDrawer(path+"cfr.dxf");
        dxf.setHeader();
        double[] a={1,-1};
        dxf.drawLine3D("0", new CoordXYZ(-b/2, (h/2),0),new CoordXYZ(b/2, (h/2),0),direc,t);
        dxf.drawLine3D("0", new CoordXYZ(-b/2, -(h/2),0),new CoordXYZ(b/2, -(h/2),0),direc,t);
        
        
        for (int i = 0; i < a.length; i++) {
            double a1 = a[i];
            dxf.drawLine3D("0", 
                    new CoordXYZ(a1*(b/2), (-h/2), 0),
                    new CoordXYZ(a1*b/2, (h/2),0),direc,t
            );
            dxf.drawLine3D("0", 
                    new CoordXYZ(a1*(p/2+phi/2), (75), 0),
                    new CoordXYZ(a1*(p/2+phi/2), (-75),0),direc,t
            );
            dxf.drawLine3D("0", 
                    new CoordXYZ(a1*(p/2-phi/2), (75),0), 
                    new CoordXYZ(a1*(p/2-phi/2), (-75),0),direc,t
            );
            
        }
        int n=8;
        dxf.drawArc3D("0",new CoordXYZ( p / 2, 75,0),phi/2,0, 180,n,direc,t);
        dxf.drawArc3D("0",new CoordXYZ( -p / 2, 75,0),phi/2,0, 180,n,direc,t);
        dxf.drawArc3D("0",new CoordXYZ( p / 2, -75,0),phi/2,-180, 0,n,direc,t);
        dxf.drawArc3D("0",new CoordXYZ( -p / 2, -75,0),phi/2,-180, 0,n,direc,t);
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    
    
    
}
