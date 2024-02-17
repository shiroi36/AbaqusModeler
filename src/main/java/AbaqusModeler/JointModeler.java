/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler;

import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class JointModeler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JointModeler a=new JointModeler();
//        a.beam();
        a.ShearPlate();
    }

    private final String path;
    private final HSection csec;
    private final HSection bsec;
    private final double lb;
    private final double dk;
    private final int tk1;
    private final double bk;
    private final int gk;
    private final int boltnum;
    private final int boltsize;
    private final bolthole wingbolt;
    private final bolthole basebolt;

    public JointModeler(){
        int h=600;
        path="dxf/"+ h+ "/";
        csec=new HSection(500,300,16,13);
        bsec=new HSection(h,200,16,13);
        lb=2000.0;
        
        tk1=25;
        gk=49;
        boltnum=6;
        boltsize=22;
//        else if(kp.getD()==24){
//            double a=40;
//            double b=14;
//            double c=24;
//            double d=22;
//        }else if(kp.getD()==26){
            double a=44;
            double b=15;
            double c=26;
            double d=22;
//        }
        this.wingbolt=new bolthole(c,a,b,100);
        this.basebolt=new bolthole(c,a,b,100);
        
//        gk=78;
        
        
        dk=bsec.getH()+80+tk1*2;
        bk=30.0;
    }
    
    
    public void beam(){
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
        double x=bk/2+gk;
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
        y=-lb/2+800;
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        y=-lb/2+1200;
        dxf.drawLine3D("0",new CoordXYZ(-bsec.getB()/2,y,0),new CoordXYZ(bsec.getB()/2,y,0));
        dxf.setEnd();
        dxf.Finish();
        
        outputpath=path+"lib/b01.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        y=0;
        x=bk/2+gk;
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
        this.drawShearPlate1(false,-lb/2, dxf, bsec.getH(), basebolt);
        dxf.setEnd();
        dxf.Finish();
        outputpath=path+"lib/b04.dxf";
        dxf=new DXFDrawer(outputpath);
        dxf.setHeader();
        this.drawShearPlate1(true,-lb/2, dxf, bsec.getH(), basebolt);
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
    
    public void drawShearPlate1(boolean flag,double offset,DXFDrawer dxf,double hb,bolthole bh){
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
            hs=455;
            w=100;
            x=new double[]{60,60,60,60,60,60};
            y=new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
        }else if(hb==700||hb==750||hb==800){
            hs=530;
            w=80;
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{225,150,75,0,-75,-150,-225};
        }else if(hb==850||hb==900){
            hs=620;
            w=80;
            x=new double[]{40,40,40,40,40,40,40};
            y=new double[]{270,180,90,0,-90,-180,-270};
        }
        
        if (flag) {
            dxf.drawLine3D("0", new CoordXYZ(w + offset, hs / 2, 0), new CoordXYZ(w + offset, -hs / 2, 0));
            dxf.drawLine3D("0", new CoordXYZ(-20 + offset, hs / 2, 0), new CoordXYZ(w + offset, hs / 2, 0));
            dxf.drawLine3D("0", new CoordXYZ(-20 + offset, -hs / 2, 0), new CoordXYZ(w + offset, -hs / 2, 0));
        }

        
        for (int i = 0; i < y.length; i++) {
            dxf.drawCircle3D("0", new CoordXYZ(x[i]+offset, y[i], 0),
                    bh.getHoleSize() / 2, new CoordXYZ(0, 0, 1), 0);
            if (flag) {
                dxf.drawCircle3D("0", new CoordXYZ(x[i]+offset, y[i], 0),
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
        }else if(hb==850||hb==900){
            hs=620;
            w=100;
            x=new double[]{40,40,40,40,40,40,40};
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
        }else if(hb==850||hb==900){
            hs=620;
            w=100;
            x=new double[]{40,40,40,40,40,40,40};
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
    
    private class bolthole{
        private final double holesize;
        private final double nutsize;
        private final double nutheight;
        private final double axislength;
        public bolthole(double holesize,double nutsize,double nutheight,double axislength){
            this.holesize=holesize;
            this.nutsize=nutsize;
            this.nutheight=nutheight;
            this.axislength=axislength;
        }
        public double getHoleSize(){return holesize;}
        public double getNutSize(){return nutsize;}
        public double getNutHeight(){return nutheight;}
        public double getAxisLength(){return axislength;}
    }
}
