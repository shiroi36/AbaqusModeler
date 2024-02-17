/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.Modeler;

import AbaqusModeler.*;
import JointCreator.ID.KPID;
import java.io.File;
import static org.mozilla.javascript.ScriptRuntime.name;
import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class SPModeler {
    
    public static void main(String[] args) {
        KPID kpid=KPID.C400_N25_6M22;
        HSection bsec=new HSection(600,200,9,19);
        double ts=12;
        SPModeler spm=new SPModeler(
                "test/",kpid.gettk1(),bsec.getH(),ts);
        spm.outputPythonFile("sm490");
    }

    /**
     * @param args the command line arguments
     */

    private final String path;
    private String pnsp;
    private final double ts;
    private final double hb;

    public SPModeler(String path,double tk1,double hb,double ts){
//        int h=550;
//        int h=(int)bsec.getH();
        this.path=path;
        this.pnsp="";
        this.ts=ts;
        this.hb=hb;
        if(tk1==16||tk1==22 || tk1 == 25) {
            if (hb == 400) {
                this.pnsp = "sp400_M22";
            } else if (hb == 450) {
                this.pnsp = "sp450_M22";
            } else if (hb == 500 || hb == 550) {
                this.pnsp = "sp550_M22";
            } else if (hb == 600 || hb == 650) {
                this.pnsp = "sp650_M22";
            } else if (hb == 700 || hb == 750 || hb == 800) {
                this.pnsp = "sp800_M22";
            } else if (hb == 900 || hb == 850) {
                this.pnsp = "sp900_M22";
            }
        }else if(tk1==28){
            if (hb == 400) {
                this.pnsp = "sp400_M24";
            } else if (hb == 450) {
                this.pnsp = "sp450_M24";
            } else if (hb == 500 || hb == 550) {
                this.pnsp = "sp550_M24";
            } else if (hb == 600 || hb == 650) {
                this.pnsp = "sp650_M24";
            } else if (hb == 700 || hb == 750 || hb == 800) {
                this.pnsp = "sp800_M24";
            } else if (hb == 900 || hb == 850) {
                this.pnsp = "sp900_M24";
            }
        }if(tk1==19) {
            if (hb == 600) {
                this.pnsp = "sp600_M20";
            } 
        }
    }
    
    public String getShearPlateName(){return pnsp;}
    
    public void ShearPlate(){
//        String outputpath=path+"lib/sh00.dxf";
//        DXFDrawer dxf=new DXFDrawer(outputpath);
//        dxf.setHeader();
//        this.drawShearPlate2(dxf, bsec.getH(), basebolt);
//        dxf.setEnd();
//        dxf.Finish();
//        outputpath=path+"lib/sh01.dxf";
//        dxf=new DXFDrawer(outputpath);
//        dxf.setHeader();
//        this.drawShearPlate3(dxf, bsec.getH(), basebolt);
//        dxf.setEnd();
//        dxf.Finish();
    }
    
    public CoordXYZ[] getBoltPosition(){
        double[] x,y;
        if(hb==400){
            x=new double[]{40,40,40,40,100,100,100,100};
            y=new double[]{105,35,-35,-105,105,35,-35,-105};
        }else if(hb==450){
            x=new double[]{40,40,40,40,100,100,100,100};
            y=new double[]{112.5,37.5,-37.5,-112.5,112.5,37.5,-37.5,-112.5};
        }else if(hb==500||hb==550){
            x=new double[]{40,40,40,40,40,100,100};
            y=new double[]{140,70,0,-70,-140,140,-140};
        }else if(hb==600){
            x=new double[]{40,40,40,40,40,40};
            y=new double[]{37.5+75*2,37.5+75,37.5,-37.5,-37.5-75,-37.5-75*2};
        }else if(hb==650){
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{180,120,60,0,-60,-120,-180};
        }else if(hb==700||hb==750||hb==800){
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{225,150,75,0,-75,-150,-225};
        }else if(hb==900||hb==850){
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{270,180,90,0,-90,-180,-270};
        }else{x=null;y=null;}
        
        CoordXYZ[] p=new CoordXYZ[x.length];
        for (int i = 0; i < p.length; i++) {
            p[i]=new CoordXYZ(x[i],y[i],0);
        }
        return p;
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
            hs=440;
            w=100;
            x=new double[]{60,60,60,60,60,60,60};
            y=new double[]{180,120,60,0,-60,-120,-180};
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
            hs=440;
            w=100;
            x=new double[]{60,60,60,60,60,60,60};
            y=new double[]{180,120,60,0,-60,-120,-180};
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
    
    
    
    public void outputPythonFile(String mat){
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='"+ pnsp+ "'\n"
                + "#SPL厚の設定\n"
                + "ts="+ ts+ "\n"
                + "\n";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"sp.py");
        txt.append("abaqus/core/header.part");
        txt.println(endplate);
        txt.append("abaqus/core/sp.part");
        txt.finish();
    }
    
    
}
