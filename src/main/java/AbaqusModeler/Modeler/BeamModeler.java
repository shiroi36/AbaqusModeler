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
public class BeamModeler {
    
    public static void main(String[] args) {
        double bk=12+24+25;
        double lb=2000;
        KPID kpid=KPID.C400_N25_6M22;
        HSection bsec=new HSection(600,200,9,19);
        BeamModeler bm=new BeamModeler("test/",lb,bk,bsec,kpid);
        bm.draw();
        bm.outputPythonFile("sm490");
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
    private final bolthole basebolt;
    private double hs;

    public BeamModeler(String path,double lb,double bk,HSection bsec,KPID kp){
//        int h=550;
//        int h=(int)bsec.getH();
        this.path=path;
        this.bsec=bsec;
        tk1=kp.gettk1();
        this.gk=kp.getgk();
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
        this.basebolt=new bolthole(c,a,b,gk+tk1,d);
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
    
    public void draw(){
        String outputpath=path+"lib/b00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawH("0", dxf, bsec);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/b02.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        double y=0;
        double x=(bk-tk1)/2+gk-10;
        for (int i = 0; i < boltnum; i++) {
            y=-lb/2+40+i*60;
            dxf.drawCircle3D("0", new CoordXYZ(x,y,0), 
                    basebolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(x,y,0), 
                    basebolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-x,y,0), 
                    basebolt.getHoleSize()/2, new CoordXYZ(0,0,1), 0);
            dxf.drawCircle3D("0", new CoordXYZ(-x,y,0), 
                    basebolt.getNutSize()/2, new CoordXYZ(0,0,1), 0);
        }
        y=-lb/2+400;
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        y=-lb/2+400+1.1*bsec.getH();
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        y=-lb/2+lb-50;
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/b01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        y=0;
        x=(bk-tk1)/2+gk-10;
        for (int i = 0; i < boltnum; i++) {
            y=-lb/2+40+i*60;
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
        this.drawShearPlate1(false,-lb/2,0, dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        outputpath=path+"lib/b04.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate1(true,-lb/2,20, dxf, bsec.getH(), basebolt);
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
                + "h="+ bsec.getH()+ ";\n"
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
                + "tw="+ bsec.getWebThickness()+ "\n"
                + "tf="+bsec.getFlangeThickness()+ "\n"
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
