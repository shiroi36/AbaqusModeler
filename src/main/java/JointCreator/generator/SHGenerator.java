/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.SHID;
import java.io.File;
import util.CircleInfo;
import util.LineInfo;
import util.coord.CoordXYZ;

/**
 *
 * @author keita
 */
public final class SHGenerator implements Generator{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String path="dxf\\";
        String header="SH400";
        double t=16;
        int boltnum=8;
        int bolthole=24;
        double p=75;
        SHID sh=new SHID(SHID.SH400,t,boltnum,bolthole,p);
        SHGenerator shg=new SHGenerator("dxf/",sh);
        shg.draw();
    }
    
    private String path;
    private final SHID shid;

    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public SHGenerator(SHID shid) {
        this("",shid);
    }
    public SHGenerator(String outputpath,SHID shid) {
        this.path=outputpath;
        this.shid=shid;
    }
    
    
    public String getName(){return shid.getName();}
    
    public LineInfo[] getLineInfo(){
        double h=shid.getShearPlateHeight();
        double w=shid.getWidth();
        double t=shid.getThickness();
        CoordXYZ p0=new CoordXYZ(0,h/2,-20);
        CoordXYZ p1=new CoordXYZ(0,-h/2,-20);
        CoordXYZ p2=new CoordXYZ(0,h/2,w-20);
        CoordXYZ p3=new CoordXYZ(0,-h/2,w-20);
        CoordXYZ e=new CoordXYZ(-1,0,0);
        return new LineInfo[]{
            new LineInfo(p0,p1,e,t),
            new LineInfo(p2,p3,e,t),
            new LineInfo(p0,p2,e,t),
            new LineInfo(p1,p3,e,t),
        };
    }
    
    public CircleInfo[] getCircleInfo(){
        CoordXYZ[] cen=shid.getBoltPosition();
        double bolthole=shid.getBoltType();
        double phi=bolthole+2;
        double r=phi/2;
        double t=shid.getThickness();
        CoordXYZ e=new CoordXYZ(-1,0,0);
        CircleInfo[] ci=new CircleInfo[cen.length];
        for (int i = 0; i < ci.length; i++) {
            ci[i]=new CircleInfo(cen[i],r,e,t);
        }
        return ci;
    }
    
    
    public void draw(){
        //head.txtの読み込み
        double boltnum=shid.getBoltnum();
        double pitch=shid.getBoltPitch();
        double t=shid.getThickness();
        double bolthole=shid.getBoltType();
        double h=(boltnum/2-1)*pitch+80;//シアプレート高さ
        double b=160;//シアプレート幅
        
        double[] a={1,-1};
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        
        DXFDrawer dxf=new DXFDrawer(path+"sh.dxf");
        dxf.setHeader();
        for (int i = 0; i < a.length; i++) {
            double a1 = a[i];
            dxf.drawLine3D("0", new CoordXYZ((-b/2), a1*(h/2),0), new CoordXYZ(b/2, a1*(h/2),0),direc,t);
            dxf.drawLine3D("0", new CoordXYZ(a1*(b/2), (-h/2),0), new CoordXYZ(a1*b/2, (h/2),0),direc,t);
        }
        double phi=bolthole+2;
        for (int i = 0; i < boltnum/2; i++) {
            double y=h/2-40-i*pitch;
//            double x=30;
            dxf.drawCircle3D("0", new CoordXYZ(40, y, 0), phi / 2,direc,t);
            dxf.drawCircle3D("0", new CoordXYZ(-20, y, 0), phi / 2,direc,t);
        }
        dxf.setEnd();
        dxf.Finish();
        
    }
    
}
