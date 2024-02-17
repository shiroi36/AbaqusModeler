/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import JointCreator.ID.CGID;
import util.dxf.DXFDrawer;
import JointCreator.ID.SHID;
import java.io.File;
import util.CircleInfo;
import util.LineInfo;
import util.coord.CoordXYZ;
import util.section.HSection;

/**
 *
 * @author keita
 */
public final class CGGenerator implements Generator{

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
        CGID sh=new CGID("SH400-16-05-05-M20@75",new HSection(400,300,12,20,13));
        CGGenerator shg=new CGGenerator("dxf/",sh);
        shg.draw();
    }
    
    private String path;
    private final CGID cgid;

    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public CGGenerator(CGID cgid) {
        this("",cgid);
    }
    public CGGenerator(String outputpath,CGID cgid) {
        this.path=outputpath;
        this.cgid=cgid;
    }
    
    
    public String getName(){return cgid.getName();}
    
    public LineInfo[] getLineInfo(){
        CoordXYZ[] p=cgid.getCornerPoint();
        CoordXYZ p0=p[0];
        CoordXYZ p1=p[1];
        CoordXYZ p2=p[2];
        CoordXYZ p3=p[3];
        double t=cgid.getThickness();
        CoordXYZ e=new CoordXYZ(-1,0,0);
        return new LineInfo[]{
            new LineInfo(p0,p1,e,t),
            new LineInfo(p2,p3,e,t),
            new LineInfo(p0,p2,e,t),
            new LineInfo(p1,p3,e,t),
        };
    }
    
    public CircleInfo[] getCircleInfo(){
        CoordXYZ[] cen=cgid.getBoltPosition();
        double bolthole=cgid.getBoltType();
        double phi=bolthole+2;
        double r=phi/2;
        double t=cgid.getThickness();
        CoordXYZ e=new CoordXYZ(-1,0,0);
        CircleInfo[] ci=new CircleInfo[cen.length];
        for (int i = 0; i < ci.length; i++) {
            ci[i]=new CircleInfo(cen[i],r,e,t);
        }
        return ci;
    }
    
    
    public void draw(){
        //head.txtの読み込み
        double boltnum=cgid.getBoltnum();
        double pitch=cgid.getBoltPitch();
        double t=cgid.getThickness();
        double bolthole=cgid.getBoltType();
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
