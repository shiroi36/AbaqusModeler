/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.CWRID;
import JointCreator.JointState;
import java.io.File;
import util.coord.CoordXYZ;

/**
 *
 * @author keita
 */
public final class CWRGenerator implements Generator{
    public static final int TYPE4=4;
    public static final int TYPE6=6;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        String path="dxf\\";
        String header="CWR400";
        double t=12;
        double l=500;
        int boltnum=CWRGenerator.TYPE4;
        int bolthole=20;
        CWRID cwrid=new CWRID(CWRID.CWR400,t,l,CWRID.TYPE4,bolthole,JointState.INTERMEDIATE_X);
        CWRGenerator cwrg=new CWRGenerator("dxf/",cwrid);
        cwrg.draw();
    }
    
    private String path;
    private CWRID cwrid;
    
    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public CWRGenerator(CWRID cwrid) {
        this("",cwrid);
    }
    
    public CWRGenerator(String outputpath,CWRID cwrid) {
        this.path=outputpath;
        this.cwrid=cwrid;
//        debug=true;
//        debug2=true;
//        hundlenum=2000;//ハンドル番号、おそらく一個一個の要素につけていかないといけない気がする
//        this.header=cwrid.getHeader();
//        this.t=cwrid.getThickness();
//        this.l=cwrid.getLcfw();
//        this.boltnum=cwrid.getBoltHoleType();
//        this.bolthole=cwrid.getBoltHoleSize();
//        this.name=cwrid.getName();
    }
    
    
    public String getName(){return cwrid.getName();}
    
    
    public void draw(){
        //head.txtの読み込み
        
        double h=140;//シアプレート高さ
        double b=cwrid.getLcfw();//シアプレート幅
        double t=cwrid.getThickness();
        int boltnum=cwrid.getBoltHoleType();
        int bolthole=cwrid.getBoltHoleSize();
        
        double[] a=null;
        
        if(cwrid.getJointState().isXShape())a = new double[]{1, -1};
        else a = new double[]{1};
        
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        DXFDrawer dxf = new DXFDrawer(path + "cwr.dxf");
        dxf.setHeader();
        dxf.drawLine3D("0",
                new CoordXYZ(-b/2,  (h / 2), 0),new CoordXYZ( b/2,  (h / 2), 0),direc,t
        );
        dxf.drawLine3D("0",
                new CoordXYZ(-b/2,  -(h / 2), 0),new CoordXYZ( b/2,  -(h / 2), 0),direc,t
        );
        dxf.drawLine3D("0", new CoordXYZ((b/2), -h/2,0),new CoordXYZ(b/2, (h/2),0),direc,t);
        dxf.drawLine3D("0", new CoordXYZ(-1*(b/2), -h/2,0),new CoordXYZ(-1*b/2, (h/2),0),direc,t);
        for (int i = 0; i < a.length; i++) {
            double a1 = a[i];
            dxf.drawCircle3D("0",new CoordXYZ(a1 * (b / 2 - 40),(h / 2 - 40),0),bolthole / 2 + 1,direc,t);
            dxf.drawCircle3D("0",new CoordXYZ(a1 * (b / 2 - 40),-(h / 2 - 40),0),bolthole / 2 + 1,direc,t);
            dxf.drawCircle3D("0",new CoordXYZ(a1 * (b / 2 - 40 - 60), (h / 2 - 40),0),bolthole / 2 + 1,direc,t);
            dxf.drawCircle3D("0",new CoordXYZ(a1 * (b / 2 - 40 - 60), -(h / 2 - 40),0),bolthole / 2 + 1,direc,t);
            if (boltnum == 6) {
                dxf.drawCircle3D("0",new CoordXYZ(a1 * (b / 2 - 40 - 60 - 60),  (h / 2 - 40),0),bolthole / 2 + 1,direc,t);
                dxf.drawCircle3D("0",new CoordXYZ(a1 * (b / 2 - 40 - 60 - 60),  -(h / 2 - 40),0),bolthole / 2 + 1,direc,t);
            }
        }
        dxf.setEnd();
        dxf.Finish();
                
    }
    
}
