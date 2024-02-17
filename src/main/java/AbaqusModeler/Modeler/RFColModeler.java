/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.Modeler;

import AbaqusModeler.*;
import JointCreator.ID.KPID;
import java.io.File;
import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class RFColModeler {
    
    public static void main(String[] args) {
        double ttp=19;
        double tctp=19;
        RFColModeler a=new RFColModeler(
                "D:\\Dropbox\\Dropbox (SSLUoT)\\abaqus\\01_col/",3000,61,
                new HSection(600,250,12,19),
                KPID.C400_N25_6M22,
                new HSection(600,200,9,19),ttp,tctp,9,12
        );
        a.draw();
        a.ouputPythonfile("sm490");
        
    }

    /**
     * @param args the command line arguments
     */

    private final String path;
    private final HSection colsec;
    private final double lc;
    private final double dk;
    private final double tk1;
    private final double bk;
    private final double gk;
    private final int boltnum;
    private final bolthole basebolt;
    private final bolthole wingbolt;
    private final double ttp;
    private final double tcwr;
    private final double tcfr;
    private final double tctp;

    public RFColModeler(String path,double lc,double bk,
            HSection colsec,KPID kp,HSection bsec,
            double ttp,double tctp,double tcfr,double tcwr){
//        int h=550;
//        int h=(int)bsec.getH();
        this.path=path;
        this.colsec=colsec;
        tk1=kp.gettk1();
        this.gk=kp.getgk();
        boltnum=kp.getBoltnum();
        this.tcfr=tcfr;
        this.tcwr=tcwr;
        this.tctp=tctp;
        this.ttp=ttp;
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
        this.basebolt=new bolthole(c,a,b,gk+tk1,d);
        this.wingbolt=new bolthole(c,a,b,colsec.getFlangeThickness()+tk1,d);
        this.lc=lc;
        dk=kp.gethk()/2+tk1+bsec.getH()+ttp/2;
        this.bk=bk;//金物つかみ部距離
        String core=path+"lib";//.partファイルの場所
        File newfile = new File(core);
        if (newfile.mkdir()) {
            System.out.println("ディレクトリの作成に成功しました");
        } else {
            System.out.println("ディレクトリの作成に失敗しました");
        }
    }
    
    public void draw(){
        String outputpath=path+"lib/c00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawH("0", dxf, colsec);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c02.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=dk-(ttp+tctp)*0.5;
        double x=bk/2;
        double x1=(tk1+17)/2;
        int num=9;
        dxf.drawLine3D("0",new CoordXYZ(x+x1,y+75,0),new CoordXYZ(x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(x-x1,y+75,0),new CoordXYZ(x-x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x+x1,y+75,0),new CoordXYZ(-x+x1,y-75,0));
        dxf.drawLine3D("0",new CoordXYZ(-x-x1,y+75,0),new CoordXYZ(-x-x1,y-75,0));
        dxf.drawArc3D("0", new CoordXYZ(x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y+75,0), x1, 0, 180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawArc3D("0", new CoordXYZ(-x,y-75,0), x1, 0, -180, num,new CoordXYZ(0,0,1), 0);
        dxf.drawLine3D("0",new CoordXYZ(colsec.getB()/2-ttp-5,y+125,0),new CoordXYZ(colsec.getB()/2-ttp-5,y-125,0));
        dxf.drawLine3D("0",new CoordXYZ(-colsec.getB()/2+ttp+5,y+125,0),new CoordXYZ(-colsec.getB()/2+ttp+5,y-125,0));
        dxf.drawLine3D("0",new CoordXYZ(colsec.getB()/2,y+125,0),new CoordXYZ(-colsec.getB()/2,y+125,0));
        dxf.drawLine3D("0",new CoordXYZ(colsec.getB()/2,y-125,0),new CoordXYZ(-colsec.getB()/2,y-125,0));
        dxf.drawLine3D("0",new CoordXYZ(colsec.getB()/2,y+600,0),new CoordXYZ(-colsec.getB()/2,y+600,0));
        dxf.drawLine3D("0",new CoordXYZ(colsec.getB()/2,y+900,0),new CoordXYZ(-colsec.getB()/2,y+900,0));
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/c01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
//        y=dk;
        x=bk/2;
        x1=(tk1+3)/2;
        System.out.println(dk);
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
        x=colsec.getH()/2-50;
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(x-160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+40,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+100,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y+30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.drawCircle3D("0", new CoordXYZ(-x+160,y-30,0), wingbolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
        dxf.setEnd();
        dxf.Finish();
        outputpath=path+"lib/c04.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        dxf.drawLine3D("0",new CoordXYZ(x,y+70,0),new CoordXYZ(x,y-70,0));
        dxf.drawLine3D("0",new CoordXYZ(1000,y+70,0),new CoordXYZ(-1000,y+70,0));
        dxf.drawLine3D("0",new CoordXYZ(1000,y,0),new CoordXYZ(-1000,y,0));
        
        dxf.drawLine3D("0",new CoordXYZ(0,y+70,0),new CoordXYZ(0,y-70,0));
        
        dxf.drawLine3D("0",new CoordXYZ(-x,y+70,0),new CoordXYZ(-x,y-70,0));
        dxf.drawLine3D("0",new CoordXYZ(1000,y-70,0),new CoordXYZ(-1000,y-70,0));
        
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
        }else if(hb==500||hb==550){
            hs=360;
            w=140;
            x=new double[]{40,40,40,40,40,100,100};
            y=new double[]{140,70,0,-70,-140,140,-140};
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
    
    
    public void ouputPythonfile(String mat){
//        double l=-(lb2-lb);
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='col'\n"
                + "lc="+ lc+ ";\n"
                + "ttp="+ ttp+ ";\n"
                + "tctp="+ tctp+ ";\n"
                + "lcc=lc-ttp*0.5-tctp*0.5;\n"
                + "h="+ colsec.getH()+ ";\n"
                + "bc="+ colsec.getB()+ ";\n"
                + "#断面ファイル\n"
                + "s0='c00'\n"
                + "#フランジ面スケッチファイル\n"
                + "s1='c01'\n"
                + "#フランジ面スケッチファイル\n"
                + "s2='c02'\n"
                + "#ウェブ面スケッチファイル\n"
                + "s3='c03'\n"
                + "#ウェブ面スケッチファイル\n"
                + "s4='c04'\n"
                + "\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "\n"
                + "#シェル厚の設定\n"
                + "tw="+ colsec.getWebThickness()+ "\n"
                + "tcwr="+ tcwr+ "\n"
                + "tf="+colsec.getFlangeThickness()+ "\n"
                + "tcfr="+tcfr+ "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s0+'.dxf')\n"
                + "importdxf(fileName='lib/'+s1+'.dxf')\n"
                + "importdxf(fileName='lib/'+s2+'.dxf')\n"
                + "importdxf(fileName='lib/'+s3+'.dxf')\n"
                + "importdxf(fileName='lib/'+s4+'.dxf')";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"col.py");
        txt.append("abaqus/core/header.part");
        txt.println(beam);
        txt.append("abaqus/core/colRF.part");
        txt.finish();
    }
    
}
