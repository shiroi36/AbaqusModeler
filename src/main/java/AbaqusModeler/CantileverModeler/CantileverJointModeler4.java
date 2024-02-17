/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.CantileverModeler;

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
public class CantileverJointModeler4 {
    
    public static void main(String[] args) {
        
        KPID[] kp={
            KPID.C400_N25_6M22,
        };
        HSection[] bsec={
            new HSection(850,300,14,16),
        };
        //金物羽根板間直交方向の距離
        double[] bk={
            30+kp[0].gettk1(),
        };
        String[] bmat={
            "ss400",
        };
        double[] inip={
            349000,
        };
        double[] shearspan={
            5,
        };
        double[] lb1=new double[inip.length];
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            lb1[i]=shearspan[i]*dk/2;
        };
        String[] file={
            "abaqus/",
        };
        double lb=1500;//梁のモデル化長さ
//        double bk=24+25;
//        KPID kp=KPID.C400_R28_6M24;
//        double inip=349000;
        
        for (int i = 0; i < lb1.length; i++) {
            System.out.println(file[i]);
            String core = file[i];//.partファイルの場所
            File newfile = new File(core);
            if (newfile.mkdir()) {
                System.out.println("ディレクトリの作成に成功しました");
            } else {
                System.out.println("ディレクトリの作成に失敗しました");
            }
            String path = file[i] + "/";
            CantileverJointModeler4 a
                    = new CantileverJointModeler4(path, lb, bk[i], bsec[i], kp[i]);
            a.ShearPlate();


        }
    }

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

    public CantileverJointModeler4(String path,double lb,double bk,HSection bsec,KPID kp){
//        int h=550;
//        int h=(int)bsec.getH();
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
    
    
    
    
    public void topplate(){
        String outputpath=path+"lib/tp00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double h=400;
        if(tk1==16)h=280;
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,0,0), new CoordXYZ(bsec.getB()/2,0,0));
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,h,0), new CoordXYZ(bsec.getB()/2,h,0));
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,0,0), new CoordXYZ(-bsec.getB()/2,h,0));
        dxf.drawLine3D("0", new CoordXYZ(bsec.getB()/2,0,0), new CoordXYZ(bsec.getB()/2,h,0));
        double a=((bk-tk1)/2-10+gk)*2;
        double[][] pos={{
            a/2,60},
            {a/2,120},
            {a/2,180},
            {a/2,240},
            {a/2,300},
            {a/2,360}
        };
        if(tk1==16)
            pos=new double[][]{{a/2,60},
            {a/2,120},
            {a/2,180},
            {a/2,240},
        };
        for (int i = 0; i < pos.length; i++) {
            double[] po = pos[i];
            dxf.drawCircle3D("0", new CoordXYZ(po[0],po[1],0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-po[0],po[1],0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        }
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/tp01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,0,0), new CoordXYZ(bsec.getB()/2,0,0));
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,20,0), new CoordXYZ(bsec.getB()/2,20,0));
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,h,0), new CoordXYZ(bsec.getB()/2,h,0));
        dxf.drawLine3D("0", new CoordXYZ(-bsec.getB()/2,0,0), new CoordXYZ(-bsec.getB()/2,h,0));
        dxf.drawLine3D("0", new CoordXYZ(bsec.getB()/2,0,0), new CoordXYZ(bsec.getB()/2,h,0));
        
        for (int i = 0; i < pos.length; i++) {
            double[] po = pos[i];
            dxf.drawCircle3D("0", new CoordXYZ(po[0],po[1],0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-po[0],po[1],0), wingbolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        }
        
        dxf.setEnd();
        dxf.Finish();
        
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
        y=-lb+400+1*bsec.getH();
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        y=-lb+400+1.5*bsec.getH();
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
        this.drawShearPlate1(true,-lb,20, dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        
        
    }
    
    public void endplate(){
        double[][] boltcord;
        if(kp.getBoltnum()==6){
            boltcord=new double[][]{
                {60,30},
                {60,-30},
                {0,30},
                {0,-30},
                {-60,30},
                {-60,-30},};
        }else if(kp.getBoltnum()==4){
            boltcord=new double[][]{
                {60,30},
                {60,-30},
                {0,30},
                {0,-30},
                {-60,30},
                {-60,-30},
            };
        } else
            return;
        
        DXFDrawer dxf = new DXFDrawer(path + "lib/endplate0.dxf");
        dxf.setHeader();
        dxf.drawLine3D("0",
                new CoordXYZ(0, -dk / 2 - 110, 0),
                new CoordXYZ(0, dk / 2 + 110, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, -dk / 2 - 110, 0),
                new CoordXYZ(240, dk / 2 + 110, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, dk/2 + 110, 0),
                new CoordXYZ(0, dk/2 + 110, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, -dk/2 - 110, 0),
                new CoordXYZ(0, -dk/2 - 110, 0));
        double r=wingbolt.getHoleSize()/2;
        double y=dk/2;
        double x=140;
        double a0=kp.gettk1()*2+kp.gethk()+bsec.getFlangeThickness();
        for (int i = 0; i < boltcord.length; i++) {
            double[] bd= boltcord[i];
            dxf.drawCircle3D("0", new CoordXYZ(x+bd[0],-y-bd[1],0), r, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(x+bd[0],y+bd[1]-a0,0), r, new CoordXYZ(0,0,1), 0);
        }
        dxf.setEnd();
        dxf.Finish();
        
        dxf = new DXFDrawer(path + "lib/endplate1.dxf");
        dxf.setHeader();
        dxf.drawLine3D("0",
                new CoordXYZ(0, -dk / 2 - 110, 0),
                new CoordXYZ(0, dk / 2 + 110, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(30, -dk / 2 - 110, 0),
                new CoordXYZ(30, dk / 2 + 110, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, -dk / 2 - 110, 0),
                new CoordXYZ(240, dk / 2 + 110, 0));
        
        dxf.drawLine3D("0",
                new CoordXYZ(240, dk/2 + 110, 0),
                new CoordXYZ(0, dk/2 + 110, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, dk/2-a0 + 110-30, 0),
                new CoordXYZ(0, dk/2-a0 + 110-30, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, dk/2-a0 -80, 0),
                new CoordXYZ(0, dk/2-a0 -80, 0));
        
        dxf.drawLine3D("0",
                new CoordXYZ(240, 0, 0),
                new CoordXYZ(0,0, 0));
        
        for (int i = 0; i < boltcord.length; i++) {
            double[] bd= boltcord[i];
            r=wingbolt.getNutSize()/2;
            dxf.drawCircle3D("0", new CoordXYZ(x+bd[0],y+bd[1]-a0,0), r, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(x+bd[0],-y-bd[1],0), r, new CoordXYZ(0,0,1), 0);
        }
        
        dxf.drawLine3D("0",
                new CoordXYZ(240, -dk/2 +80, 0),
                new CoordXYZ(0, -dk/2 +80, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, -dk/2 - 110+30, 0),
                new CoordXYZ(0, -dk/2 - 110+30, 0));
        dxf.drawLine3D("0",
                new CoordXYZ(240, -dk/2 - 110, 0),
                new CoordXYZ(0, -dk/2 - 110, 0));
        dxf.setEnd();
        dxf.Finish();
        


    }
    
    public void ShearPlate(){
        String outputpath=path+"lib/sh00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate2(dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        outputpath=path+"lib/sh01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate3(dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
    }
    
    public void drawShearPlate1(boolean flag,double offset1,double offset2,DXFDrawer dxf,double hb,bolthole bh){
        double hs=0;
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
        }else if((hb>=490&&hb<=500)||hb==550){
            hs=380;
            w=100;
            x=new double[]{40,40,40,40,40,40};
            y=new double[]{150,90,30,-30,-90,-150};
        }else if(hb==600||hb==650){
            hs=455;
            w=100;
            x=new double[]{40,40,40,40,40,40};
            y=new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
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
    
    public void drawShearPlate2(DXFDrawer dxf,double hb,bolthole bh){
        double hs=0;
        double w=0;
        double[] x=null;
        double[] y=null;
        if(hb==400){
            hs=290;
            w=160;
            x=new double[]{60,60,60,60,120,120,120,120};
            y=new double[]{105,35,-35,-105,105,35,-35,-105};
        }else if(hb==450){
            hs=305;
            w=160;
            x=new double[]{60,60,60,60,120,120,120,120};
            y=new double[]{112.5,37.5,-37.5,-112.5,112.5,37.5,-37.5,-112.5};
        }else if(hb==500||hb==550){
            hs=360;
            w=160;
            x=new double[]{60,60,60,60,60,120,120};
            y=new double[]{140,70,0,-70,-140,140,-140};
        }else if(hb==600||hb==650){
            hs=455;
            w=100;
            x=new double[]{60,60,60,60,60,60};
            y=new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
        }else if(hb==700||hb==750||hb==800){
            hs=530;
            w=100;
            x=new double[]{60,60,60,60,60,60,60};
            y=new double[]{225,150,75,0,-75,-150,-225};
        }else if(hb==900||hb==850){
            hs=620;
            w=100;
            x=new double[]{60,60,60,60,60,60,60};
            y=new double[]{270,180,90,0,-90,-180,-270};
        }
        
//        dxf.drawLine3D("0", new CoordXYZ(20, hs / 2, 0), new CoordXYZ(20, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(0, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(w, hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(w, hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, -hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));

        for (int i = 0; i < y.length; i++) {
            dxf.drawCircle3D("0", new CoordXYZ(x[i], y[i], 0),
                    bh.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
//            dxf.drawCircle3D("0", new CoordXYZ(x[i], y[i], 0),
//                    bh.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
        }
    }
    
    public void drawShearPlate3(DXFDrawer dxf,double hb,bolthole bh){
        double hs=0;
        double w=0;
        double[] x=null;
        double[] y=null;
        if(hb==400){
            hs=290;
            w=160;
            x=new double[]{60,60,60,60,120,120,120,120};
            y=new double[]{105,35,-35,-105,105,35,-35,-105};
        }else if(hb==450){
            hs=305;
            w=160;
            x=new double[]{60,60,60,60,120,120,120,120};
            y=new double[]{112.5,37.5,-37.5,-112.5,112.5,37.5,-37.5,-112.5};
        }else if(hb==500||hb==550){
            hs=360;
            w=160;
            x=new double[]{60,60,60,60,60,120,120};
            y=new double[]{140,70,0,-70,-140,140,-140};
        }else if(hb==600||hb==650){
            hs=455;
            w=100;
            x=new double[]{60,60,60,60,60,60};
            y=new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
        }else if(hb==700||hb==750||hb==800){
            hs=530;
            w=100;
            x=new double[]{60,60,60,60,60,60,60};
            y=new double[]{225,150,75,0,-75,-150,-225};
        }else if(hb==900||hb==850){
            hs=620;
            w=100;
            x=new double[]{60,60,60,60,60,60,60};
            y=new double[]{270,180,90,0,-90,-180,-270};
        }
        
        dxf.drawLine3D("0", new CoordXYZ(20, hs / 2, 0), new CoordXYZ(20, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(0, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(w, hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, hs / 2, 0), new CoordXYZ(w, hs / 2, 0));
        dxf.drawLine3D("0", new CoordXYZ(0, -hs / 2, 0), new CoordXYZ(w, -hs / 2, 0));

        for (int i = 0; i < y.length; i++) {
            dxf.drawCircle3D("0", new CoordXYZ(x[i], y[i], 0),
                    bh.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(x[i], y[i], 0),
                    bh.getNutSize() / 2, new CoordXYZ(0, 0, 1), 0);
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
    
}
