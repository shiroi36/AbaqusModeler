/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.Modeler;

import AbaqusModeler.*;
import JointCreator.ID.KPID;
import JointCreator.ID.PlateID;
import JointCreator.generator.PlateGenerator;
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
public class TopPlateModeler {
    
    public static void main(String[] args) {
        double bk=24+25;
        double lb=2000;
        KPID kpid=KPID.C400_N25_6M22;
        HSection colsec=new HSection(600,250,12,19);
        HSection bsec=new HSection(600,200,9,19);
        TopPlateModeler bm=new TopPlateModeler("test/",lb,bk,colsec,bsec,kpid,25,25);
        bm.drawCTP();
        bm.drawTP();
//        bm.outputPythonFile("sm490");
    }

    /**
     * @param args the command line arguments
     */

    private final String path;
    private final double lb;
    private final double tk1;
    private final double bk;
    private final double gk;
    private final int boltnum;
    private final bolthole basebolt;
    private double hs;
    private final HSection colsec;
    private final HSection bsec;
    private PlateID ctpid;
    private PlateID tpid;
    private final double tctp;
    private final double ttp;

    public TopPlateModeler(String path,double lb,double bk,HSection colsec,HSection bsec,KPID kp,double tctp,double ttp){
//        int h=550;
//        int h=(int)bsec.getH();
        this.path=path;
        this.colsec=colsec;
        this.bsec=bsec;
        tk1=kp.gettk1();
        this.gk=kp.getgk();
        boltnum=kp.getBoltnum();
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
        this.lb=lb;
        this.bk=bk;//金物つかみ部距離
        String core=path+"lib";//.partファイルの場所
        File newfile = new File(core);
        if (newfile.mkdir()) {
            System.out.println("ディレクトリの作成に成功しました");
        } else {
            System.out.println("ディレクトリの作成に失敗しました");
        }
    }
    
    public PlateID getCTPID(){return ctpid;}
    
    public void drawCTP(){
        double p0=60;
        double c=(colsec.getH()-5*p0)/2;
        double c01=(bk-tk1)/2+gk-10;
        double c02=(colsec.getB()-2*c01)/2.0;
        double[] p={c,p0,p0,p0,p0,p0,c};
        double[] g={c02,2*c01,c02};
        this.ctpid=new PlateID(p,g,basebolt.getHoleSize(),0);
        
        String outputpath=path+"lib/ctp00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        PlateGenerator pg=new PlateGenerator(ctpid);
        dxf.setHeader();
        pg.draw(dxf, true);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/ctp01.dxf";
        ctpid=new PlateID(p,g,basebolt.getNutSize(),0);
        dxf=new DXFDrawer(outputpath);
        pg=new PlateGenerator(ctpid);
        dxf.setHeader();
        pg.Draw1_OnlyBolthole(dxf);
        dxf.drawLine3D("0", new CoordXYZ(-100,0,0), new CoordXYZ(ctpid.getLx()+100,0,0));
        dxf.setEnd();
        dxf.Finish();
        
        
    }
    
    public PlateID getTPID(){return tpid;}
    
    public void drawTP(){
        double p0=60;
        double c=(colsec.getH()-5*p0)/2;
        double c01=(bk-tk1)/2+gk-10;
        double c02=(bsec.getB()-2*c01)/2.0;
        double c03=c+20+40;
        double[] p={40,60,60,60,60,60,c03,p0,p0,p0,p0,p0,c03,60,60,60,60,60,40};
        double[] g={c02,2*c01,c02};
        this.tpid=new PlateID(p,g,basebolt.getHoleSize(),0);
        
        String outputpath=path+"lib/tp00.dxf";
        DXFDrawer dxf=new DXFDrawer(outputpath);
        PlateGenerator pg=new PlateGenerator(tpid);
        dxf.setHeader();
        pg.draw(dxf, true);
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/tp01.dxf";
        tpid=new PlateID(p,g,basebolt.getNutSize(),0);
        dxf=new DXFDrawer(outputpath);
        pg=new PlateGenerator(tpid);
        dxf.setHeader();
        dxf.drawLine3D("0", new CoordXYZ(-100,0,0), new CoordXYZ(tpid.getLx()+100,0,0));
        dxf.drawLine3D("0", new CoordXYZ(380,-tpid.getLy(),0), new CoordXYZ(380,tpid.getLy(),0));
        dxf.drawLine3D("0", new CoordXYZ(380+20,-tpid.getLy(),0), new CoordXYZ(380+20,tpid.getLy(),0));
        dxf.drawLine3D("0", new CoordXYZ(tpid.getLx()-380,-tpid.getLy(),0), new CoordXYZ(tpid.getLx()-380,tpid.getLy(),0));
        dxf.drawLine3D("0", new CoordXYZ(tpid.getLx()-380-20,-tpid.getLy(),0), new CoordXYZ(tpid.getLx()-380-20,tpid.getLy(),0));
        pg.Draw1_OnlyBolthole(dxf);
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
    
    public void outputCTPPythonFile(String mat){
        double l=0;
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='ctp'\n"
                + "lctp="+ctpid.getLx()+ ";\n"
                + "#断面ファイル\n"
                + "sk0='ctp00'\n"
                + "#フランジ面スケッチファイル\n"
                + "sk1='ctp01'\n"
                + "\n"
                + "\n"
                + "#シェル厚の設定\n"
                + "tctp="+tctp+ "\n"
                + "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+sk0+'.dxf')\n"
                + "importdxf(fileName='lib/'+sk1+'.dxf')";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"ctp.py");
        txt.append( "abaqus/core/header.part");
        txt.println(beam);
        txt.append( "abaqus/core/ctp2.part");
        txt.finish();
    }
    
    public void outputTPPythonFile(String mat){
        double l=0;
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='tp'\n"
                + "ltp="+ tpid.getLx()+ ";\n"
                + "#断面ファイル\n"
                + "sk0='tp00'\n"
                + "#フランジ面スケッチファイル\n"
                + "sk1='tp01'\n"
                + "\n"
                + "#シェル厚の設定\n"
                + "ttp="+ ttp+ "\n"
                + "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+sk0+'.dxf')\n"
                + "importdxf(fileName='lib/'+sk1+'.dxf')";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"tp.py");
        txt.append( "abaqus/core/header.part");
        txt.println(beam);
        txt.append( "abaqus/core/tp2.part");
        txt.finish();
    }
    
}
