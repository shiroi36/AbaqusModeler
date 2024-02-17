/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.Modeler;

import AbaqusModeler.*;
import JointCreator.ID.KPID;
import java.io.File;
import util.LineInfo;
import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class SolidColModeler3 {
    
    public static void main(String[] args) {
//        double bk=12+24+25;
//        double lb=2000;
//        KPID kpid=KPID.C400_N25_6M22;
//        SolidColModeler2 bm = new SolidColModeler2("colmodel/", 12, 12,
//                new HSection(500, 300, 12, 22,10.5),
//                new HSection(600, 200, 9, 16,13),
//                KPID.C400_N16_4M22);
//        bm.draw();
//        bm.outputPythonFile("sm490");
    }

    /**
     * @param args the command line arguments
     */

    private final String path;
    private final HSection csec;
    private final HSection bsec;
    private final double lb;
    private final double dk;
    private final double tk1;
    private final double bk;
    private final int boltnum;
    private final bolthole wingbolt;
    private double hs;
    private final double tcfr;

    public SolidColModeler3(String path,double tcfr,double tcwr,
            HSection csec,HSection bsec,KPID kp,double lb){
//        int h=550;
//        int h=(int)bsec.getH();
        this.bsec=bsec;
        this.path=path;
        this.csec=csec;
        tk1=kp.gettk1();
        this.tcfr=tcfr;
        boltnum=kp.getBoltnum();
        double a=0;
        double b=0;
        double c=0;
        double d=0;
        if(kp.getD()==22){
            a=36;
            b=13;
            c=22;
            d=22;
        }else if(kp.getD()==24){
            a=40;
            b=14;
            c=24;
            d=22;
        }else if(kp.getD()==26){
            a=44;
            b=15;
            c=26;
            d=22;
        }
        this.wingbolt=new bolthole(c,a,b,csec.getFlangeThickness()+tk1,d);
        dk=bsec.getH()+80+tk1*2;
        this.lb=lb;
        this.bk=tcwr*2+csec.getWebThickness()+tk1;//金物つかみ部距離
        String core=path+"lib";//.partファイルの場所
        File newfile = new File(core);
        if (newfile.mkdir()) {
            System.out.println("ディレクトリの作成に成功しました");
        } else {
            System.out.println("ディレクトリの作成に失敗しました");
        }
    }
    
    public void drawPanel(double tp){
        String outputpath=path+"lib/panel.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=dk+140;
        double x=csec.getH()-100;
        double x1=(tk1+17)/2;
        int num=4;
        
        dxf.drawLine3D("0", new CoordXYZ(-x/2,-y/2,0), new CoordXYZ(x/2,-y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(-x/2,y/2,0), new CoordXYZ(x/2,y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(-x/2,-y/2,0), new CoordXYZ(-x/2,y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(x/2,-y/2,0), new CoordXYZ(x/2,y/2,0));
        
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/panel0.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        y=dk/2-70;
        x=csec.getH()-100;
        x1=(tk1+17)/2;
        num=4;
        
        dxf.drawLine3D("0", new CoordXYZ(-x/2,-y,0), new CoordXYZ(x/2,-y,0));
        dxf.drawLine3D("0", new CoordXYZ(-x/2,y,0), new CoordXYZ(x/2,y,0));
        
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/panel1.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        y=dk/2;
        x=csec.getH()-100;
        
        double[][] c={
            {1,1},
            {1,-1},
            {-1,1},
            {-1,-1},
        };

        for (int i = 0; i < c.length; i++) {
            double[] c0 = c[i];
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 40), c0[1]*(y + 30), 0), wingbolt.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 100), c0[1]*(y + 30), 0), wingbolt.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 160), c0[1]*(y + 30), 0), wingbolt.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 40), c0[1]*(y - 30), 0), wingbolt.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 100), c0[1]*(y - 30), 0), wingbolt.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 160), c0[1]*(y - 30), 0), wingbolt.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
        }
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/panel2.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
       
        for (int i = 0; i < c.length; i++) {
            double[] c0 = c[i];
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 40), c0[1]*(y + 30), 0), wingbolt.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 100), c0[1]*(y + 30), 0), wingbolt.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 160), c0[1]*(y + 30), 0), wingbolt.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 40), c0[1]*(y - 30), 0), wingbolt.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 100), c0[1]*(y - 30), 0), wingbolt.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(c0[0]*(x / 2 - 160), c0[1]*(y - 30), 0), wingbolt.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
        }
        
        dxf.setEnd();
        dxf.Finish();
    }
    
    
    public void drawCFR(double len){
        String outputpath=path+"lib/cfr.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=0;
        double x=bk/2;
        double x1=(tk1+17)/2;
        
        if (len<0){
            len=csec.getB()/2-tcfr-5;
        }
        
        int num=4;
        dxf.drawArc3D("0", new CoordXYZ(x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawLine3D("0", new CoordXYZ(x+x1,y+75,0), new CoordXYZ(x+x1,y-75,0));
        dxf.drawLine3D("0", new CoordXYZ(x-x1,y+75,0), new CoordXYZ(x-x1,y-75,0));
        dxf.drawLine3D("0", new CoordXYZ(-x+x1,y+75,0), new CoordXYZ(-x+x1,y-75,0));
        dxf.drawLine3D("0", new CoordXYZ(-x-x1,y+75,0), new CoordXYZ(-x-x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(len,y+125,0),new CoordXYZ(len,y-125,0));
        dxf.drawLine3D("0",new CoordXYZ(-len,y+125,0),new CoordXYZ(-len,y-125,0));
        dxf.drawLine3D("0",new CoordXYZ(len,y+125,0),new CoordXYZ(-len,y+125,0));
        dxf.drawLine3D("0",new CoordXYZ(len,y-125,0),new CoordXYZ(-len,y-125,0));
        
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    public void drawCWR(){
        String outputpath=path+"lib/cwr.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double x=190;
//        double x=csec.getH()-100;
        double y=140;
        int num=4;
        dxf.drawLine3D("0", new CoordXYZ(x/2,y/2,0), new CoordXYZ(-x/2,y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(x/2,-y/2,0), new CoordXYZ(-x/2,-y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(x/2,-y/2,0), new CoordXYZ(x/2,y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(-x/2,-y/2,0), new CoordXYZ(-x/2,y/2,0));
        dxf.drawCircle3D("0", new CoordXYZ(x/2-35,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-95,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-155,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-35,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-95,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-155,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+40,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+100,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+160,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+40,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+100,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+160,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/cwr2.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        dxf.drawCircle3D("0", new CoordXYZ(x/2-35,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-95,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-155,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-35,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-95,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x/2-155,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+40,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+100,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+160,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+40,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+100,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x/2+160,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    public void drawCWR_sep4(){
        String outputpath=path+"lib/cwr4-1.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double x=140;
        double y=140;
        int num=4;
        dxf.drawLine3D("0", new CoordXYZ(x/2,y/2,0), new CoordXYZ(-x/2,y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(x/2,-y/2,0), new CoordXYZ(-x/2,-y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(x/2,-y/2,0), new CoordXYZ(x/2,y/2,0));
        dxf.drawLine3D("0", new CoordXYZ(-x/2,-y/2,0), new CoordXYZ(-x/2,y/2,0));
        dxf.drawCircle3D("0", new CoordXYZ(-30,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(30,30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-30,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(30,-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/cwr4-2.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        dxf.drawCircle3D("0", new CoordXYZ(-30,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(30,30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-30,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(30,-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    
    public void draw_4type(){
        String outputpath=path+"lib/c00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        LineInfo[] li=csec.get2DShape(2);
        for (int i = 0; i < li.length; i++) {
            LineInfo li1 = li[i];
            dxf.drawLine3D("0", li1.getStartPoint(), 
                    li1.getEndPoint(), new CoordXYZ(0,0,0), 0);
        }
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c02.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=bsec.getH()/2+75;
        double x=bk/2;
        double x1=(tk1+17)/2;
        int num=4;
        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2,y+140,0),
                new CoordXYZ(-csec.getB()/2,y+140,0));
        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2,-y-140,0),
                new CoordXYZ(-csec.getB()/2,-y-140,0));
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        x=bk/2;
        x1=(tk1+3)/2;
        dxf.drawLine3D("0",new CoordXYZ(x+x1,y+75,0),new CoordXYZ(x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(x-x1,y+75,0),new CoordXYZ(x-x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x+x1,y+75,0),new CoordXYZ(-x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x-x1,y+75,0),new CoordXYZ(-x-x1,y-75,0));
        dxf.drawArc3D("0", new CoordXYZ(x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        y*=-1;
        dxf.drawLine3D("0",new CoordXYZ(x+x1,y+75,0),new CoordXYZ(x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(x-x1,y+75,0),new CoordXYZ(x-x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x+x1,y+75,0),new CoordXYZ(-x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x-x1,y+75,0),new CoordXYZ(-x-x1,y-75,0));
        dxf.drawArc3D("0", new CoordXYZ(x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c03.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        x=csec.getH()/2-50;
        y=dk/2;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        
        x=csec.getH()/2-50;
        y*=-1;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
                
        dxf.setEnd();
        dxf.Finish();
        
        
        outputpath=path+"lib/c04.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        dxf.drawLine3D("0", new CoordXYZ(0,-lb/2,0), new CoordXYZ(0,lb/2,0));
        dxf.drawLine3D("0", new CoordXYZ(-csec.getH(),0,0), new CoordXYZ(csec.getH(),0,0));
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        
        y*=-1;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
                
        
        dxf.setEnd();
        dxf.Finish();
    }
    
    
    public void draw(){
        String outputpath=path+"lib/c00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        LineInfo[] li=csec.get2DShape(2);
        for (int i = 0; i < li.length; i++) {
            LineInfo li1 = li[i];
            dxf.drawLine3D("0", li1.getStartPoint(), 
                    li1.getEndPoint(), new CoordXYZ(0,0,0), 0);
        }
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c02.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=bsec.getH()/2+75;
        double x=bk/2;
        double x1=(tk1+17)/2;
        int num=4;
//        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2-tcfr-5,y+140,0),new CoordXYZ(csec.getB()/2-tcfr-5,y-130,0));
//        dxf.drawLine3D("0",new CoordXYZ(-csec.getB()/2+tcfr+5,y+140,0),new CoordXYZ(-csec.getB()/2+tcfr+5,y-130,0));
        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2,y+340,0),new CoordXYZ(-csec.getB()/2,y+340,0));
//        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2,y-130,0),new CoordXYZ(-csec.getB()/2,y-130,0));

        
//        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2-tcfr-5,-y-140,0),new CoordXYZ(csec.getB()/2-tcfr-5,-y+130,0));
//        dxf.drawLine3D("0",new CoordXYZ(-csec.getB()/2+tcfr+5,-y-140,0),new CoordXYZ(-csec.getB()/2+tcfr+5,-y+130,0));
        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2,-y-340,0),new CoordXYZ(-csec.getB()/2,-y-340,0));
//        dxf.drawLine3D("0",new CoordXYZ(csec.getB()/2,-y+130,0),new CoordXYZ(-csec.getB()/2,-y+130,0));
        
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        x=bk/2;
        x1=(tk1+3)/2;
        dxf.drawLine3D("0",new CoordXYZ(x+x1,y+75,0),new CoordXYZ(x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(x-x1,y+75,0),new CoordXYZ(x-x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x+x1,y+75,0),new CoordXYZ(-x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x-x1,y+75,0),new CoordXYZ(-x-x1,y-75,0));
        dxf.drawArc3D("0", new CoordXYZ(x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        y*=-1;
        dxf.drawLine3D("0",new CoordXYZ(x+x1,y+75,0),new CoordXYZ(x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(x-x1,y+75,0),new CoordXYZ(x-x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x+x1,y+75,0),new CoordXYZ(-x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x-x1,y+75,0),new CoordXYZ(-x-x1,y-75,0));
        dxf.drawArc3D("0", new CoordXYZ(x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c03.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        x=csec.getH()/2-24;
        y=dk/2;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        
        x=csec.getH()/2-24;
        y*=-1;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
                
        dxf.setEnd();
        dxf.Finish();
        
        
        outputpath=path+"lib/c04.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
//        dxf.drawLine3D("0", new CoordXYZ(0,-lb/2,0), new CoordXYZ(0,lb/2,0));
        dxf.drawLine3D("0", new CoordXYZ(-csec.getH(),0,0), new CoordXYZ(csec.getH(),0,0));
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        
        y*=-1;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
//        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
                
        
        dxf.setEnd();
        dxf.Finish();
        
        
    }
    
    
    public void drawShearPlate1(boolean flag,double offset1,double offset2,DXFDrawer dxf,double hb,bolthole bh){
        this.hs=0.0;
        double w=0;
        double[] x=null;
        double[] y=null;
        if(hb==400){
            hs=290;
            w=140;
            x=new double[]{40,40,40,40,100,100,100,100};
            y=new double[]{105,35,-35,-105,105,35,-35,-105};
        }else if(hb==450){
            hs=305;
            w=140;
            x=new double[]{40,40,40,40,100,100,100,100};
            y=new double[]{112.5,37.5,-37.5,-112.5,112.5,37.5,-37.5,-112.5};
        }else if(hb==500||hb==550){
            hs=360;
            w=140;
            x=new double[]{40,40,40,40,40,100,100};
            y=new double[]{140,70,0,-70,-140,140,-140};
        }else if(hb==600){
            hs=455;
            w=80;
            x=new double[]{40,40,40,40,40,40};
            y=new double[]{37.5+75*2,37.5+75,37.5,-37.5,-37.5-75,-37.5-75*2};
        }else if(hb==650){
            hs=440;
            w=80;
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{180,120,60,0,-60,-120,-180};
        }else if(hb==600||hb==650){
            hs=440;
            w=80;
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{180,120,60,0,-60,-120,-180};
        }else if(hb==700||hb==750||hb==800){
            hs=530;
            w=80;
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{225,150,75,0,-75,-150,-225};
        }else if(hb==900||hb==850){
            hs=620;
            w=80;
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{270,180,90,0,-90,-180,-270};
        }
        
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
    
    
    
    public void drawH(String layer,DXFDrawer dxf,HSection sec){
        double h=(sec.getH()-sec.getFlangeThickness())/2;
        dxf.drawLine3D(layer, new CoordXYZ(0, 0,0), new CoordXYZ(0,h,0));
        dxf.drawLine3D(layer, new CoordXYZ(0, h,0),new CoordXYZ(sec.getB()/2, h,0));
        dxf.drawLine3D(layer, new CoordXYZ(0, h,0), new CoordXYZ(-sec.getB()/2, h,0));
        dxf.drawLine3D(layer, new CoordXYZ(0, 0,0), new CoordXYZ(0, -h,0));
        dxf.drawLine3D(layer, new CoordXYZ(0, -h,0),new CoordXYZ(sec.getB()/2, -h,0));
        dxf.drawLine3D(layer, new CoordXYZ(0, -h,0), new CoordXYZ(-sec.getB()/2, -h,0));
    }
    
    public void outputPythonFile(String mat){
        double l=0;
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='b'\n"
                + "lb="+ lb+ ";\n"
                + "lb2="+ l+ ";\n"
                + "h="+ csec.getH()+ ";\n"
                + "#断面ファイル\n"
                + "s0='b00'\n"
                + "#フランジ面スケッチファイル\n"
                + "s1='b01'\n"
                + "#フランジ面スケッチファイル\n"
                + "s2='b02'\n"
                + "#ウェブ面スケッチファイル\n"
                + "s3='b03'\n"
                + "#ウェブ面スケッチファイル\n"
                + "s4='b04'\n"
                + "\n"
                + "#シアプレートたかさ\n"
                + "hs="+ hs+ ";\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "\n"
                + "#シェル厚の設定\n"
                + "tw="+ csec.getWebThickness()+ "\n"
                + "tf="+csec.getFlangeThickness()+ "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s0+'.dxf')\n"
                + "importdxf(fileName='lib/'+s1+'.dxf')\n"
                + "importdxf(fileName='lib/'+s2+'.dxf')\n"
                + "importdxf(fileName='lib/'+s3+'.dxf')\n"
                + "importdxf(fileName='lib/'+s4+'.dxf')";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"beam.py");
        txt.append( "abaqus/core/header.part");
        txt.println(beam);
        txt.append( "abaqus/core/beam2.part");
        txt.finish();
    }
    
    
}
