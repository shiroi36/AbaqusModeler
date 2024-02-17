/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.model.coltest.model;

import AbaqusModeler.CantileverModeler.*;
import AbaqusModeler.bolthole;
import JointCreator.ID.KPID;
import java.io.File;
import util.LineInfo;
import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class CantileverJointModeler3 {
    
    /**
     * @param args the command line arguments
     */

    private final String path;
    private final HSection bsec;
    private final double lb;
    private final double dk;
    private final double tk1;
    private final double bk;
    private final double gk;
    private final int boltnum;
    private final bolthole wingbolt;
    private final bolthole basebolt;
    private final bolthole spbolt;
    private final KPID kp;
    private int hs;
    private int w;
    private double[] x;
    private double[] y;
    

    public CantileverJointModeler3(String path,double lb,double bk,HSection bsec,KPID kp){
//        int h=550;
//        int h=(int)bsec.getH();
        hs = 320;
        w = 100;
        x = new double[]{40, 40, 40};
        y = new double[]{60, 0, -60};
        this.path=path;
        this.bsec=bsec;
        tk1=kp.gettk1();
        this.gk=kp.getgk();
        this.kp=kp;
        boltnum=kp.getBoltnum();
        double a=0;
        double b=0;
        double c=0;
        double d=0;
        if(kp.getD()==22){
            a=36;
            b=13;
            c=22;
            d=20;
        }else if(kp.getD()==24){
            a=40;
            b=14;
            c=24;
            d=22;
        }else if(kp.getD()==26){
            a=44;
            b=15;
            c=26;
            d=24;
        }
        this.wingbolt=new bolthole(c,a,b,bsec.getFlangeThickness()+tk1,d);
        this.basebolt=new bolthole(c,a,b,gk+tk1,d);
        this.spbolt=new bolthole(c,a,b,gk+tk1,d);
        this.lb=lb;
        dk=bsec.getH()+80+tk1*2;
        this.bk=bk;//金物つかみ部距離
        String core=path+"lib";//.partファイルの場所
        File newfile = new File(core);
        if (newfile.mkdir()) {
            System.out.println("ディレクトリの作成に成功しました");
        } else {
            System.out.println("ディレクトリの作成に失敗しました");
        }
    }
    
    public void setSPInfo(int hs,int w,double[] spy,double[] spx){
        this.hs=hs;
        this.w=w;
        this.x=spx;
        this.y=spy;
    }
    
    public void beam(){
        String outputpath=path+"lib/b00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        LineInfo[] li=bsec.get2DShape(2);
        for (int i = 0; i < li.length; i++) {
            LineInfo li1 = li[i];
//            li1.getStartPoint().print();
//            li1.getEndPoint().print();
//            li1.getExtrudeDirection().print();
            dxf.drawLine3D(new LineInfo("0",li1.getStartPoint(),
                    li1.getEndPoint(),new CoordXYZ(0,0,1),0));
        }
//        this.drawH("0", dxf, bsec);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/b02.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=0;
        double x=(bk-tk1)/2+gk-10;
        for (int i = 0; i < boltnum; i++) {
            y=-lb+40+i*60;
            dxf.drawCircle3D("0", new CoordXYZ(x,y,0), 
                    basebolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(x,y,0), 
                    basebolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-x,y,0), 
                    basebolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-x,y,0), 
                    basebolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        }
        y=-lb+400;
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        y=-lb+400+0.5*bsec.getH();
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        y=-lb+400+1.0*bsec.getH();
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/b01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        y=0;
        x=(bk-tk1)/2+gk-10;
        for (int i = 0; i < boltnum; i++) {
            y=-lb+40+i*60;
            dxf.drawCircle3D("0", new CoordXYZ(x,y,0), 
                    basebolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-x,y,0), 
                    basebolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        }
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/b03.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate1(false,-lb,0, dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        outputpath=path+"lib/b04.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate1(true,-lb,2, dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        
        
    }
    
    
    public void ShearPlate(){
        String outputpath=path+"lib/sh10.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate2(dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        outputpath=path+"lib/sh11.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate3(dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
    }
    
    public void drawShearPlate1(boolean flag,double offset1,double offset2,DXFDrawer dxf,double hb,bolthole bh){
        
        if (flag) {
            dxf.drawLine3D("0", new CoordXYZ(w + offset1+offset2, hs / 2+offset2, 0), new CoordXYZ(w+offset2 + offset1, -hs / 2-offset2, 0));
            dxf.drawLine3D("0", new CoordXYZ(-20 + offset1, hs / 2+offset2, 0), new CoordXYZ(w +offset2+ offset1, hs / 2+offset2, 0));
            dxf.drawLine3D("0", new CoordXYZ(-20 + offset1, -hs / 2-offset2, 0), new CoordXYZ(w+offset2 + offset1, -hs / 2-offset2, 0));
        }

        
        for (int i = 0; i < y.length; i++) {
            dxf.drawCircle3D("0", new CoordXYZ(x[i]+offset1, y[i], 0),
                    bh.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            if (flag) {
                dxf.drawCircle3D("0", new CoordXYZ(x[i]+offset1, y[i], 0),
                        bh.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
            }
        }
    }
    
    public void drawShearPlate2(DXFDrawer dxf,double hb,bolthole bh){
        
//        dxf.drawLine3D("0", new CoordXYZ(20, hs / 2, 0), new CoordXYZ(20, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(0, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(w, hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(w, hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, -hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));

        for (int i = 0; i < y.length; i++) {
            dxf.drawCircle3D("0", new CoordXYZ(x[i]+20, y[i], 0),
                    bh.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
//            dxf.drawCircle3D("0", new CoordXYZ(x[i], y[i], 0),
//                    bh.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
        }
    }
    
    public void drawShearPlate3(DXFDrawer dxf,double hb,bolthole bh){
        
        dxf.drawLine3D("0", new CoordXYZ(20, hs / 2, 0), new CoordXYZ(20, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(0, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(w, hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(w, hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, -hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));

        for (int i = 0; i < y.length; i++) {
//            dxf.drawCircle3D("0", new CoordXYZ(x[i], y[i], 0),
//                    bh.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(x[i]+20, y[i], 0),
                    bh.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
        }
    }
    
    
    
    
}
