/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.KPID;
import java.io.File;
import java.util.ArrayList;
import util.CircleInfo;
import util.LineInfo;
import util.coord.CoordXYZ;
import util.dxf.DXFEntities;
import util.matrix.Calc;
import util.section.HSection;

/**
 *
 * @author keita
 */
public final class KPGenerator implements Generator{
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        KPID[] aids=new KPID[]{
//            KPID.C400_N16_4M22,
//            KPID.C400_N19_6M20,
            KPID.C400_N22_6M22,
//            KPID.C400_N25_6M22,
//            KPID.C400_R22_6M22,
//            KPID.C400_R25_6M22,
//            KPID.C400_R28_6M24,
        };
        
        for (int i = 0; i < aids.length; i++) {
            KPID aid = aids[i];
            KPGenerator kpg = new KPGenerator("dxf/", aid);
            kpg.draw();
//
//            DXFDrawer dxf = new DXFDrawer("wcscore/"
//                    + aid.getAttachmentName() + "_0"
//                    + ".dxf");
//            dxf.setHeader();
//            LineInfo[] line = kpg.getLine();
//            for (int j = 0; j < line.length; j++) {
//                LineInfo line1 = line[j];
//                dxf.drawLine3D("k", line1.getStartPoint(),
//                        line1.getEndPoint(),
//                        line1.getExtrudeDirection(),
//                        line1.getThickness());
//            }
//            CircleInfo[] cir = kpg.getCircleInfo();
//            for (int j = 0; j < cir.length; j++) {
//                CircleInfo cir1 = cir[j];
//                dxf.drawCircle3D("", cir1.getCenterPoint(),
//                        cir1.getRadius(),
//                        cir1.getExtrudeDirection(),
//                        cir1.getThickness());
//            }
//            dxf.setEnd();
//            dxf.Finish();
//
//            dxf = new DXFDrawer("wcscore/"
//                    + aid.getAttachmentName() + "_1"
//                    + ".dxf");
//            dxf.setHeader();
//            dxf.Move3D("dxf/kl.dxf", "k", new CoordXYZ(0, 0, 0), new CoordXYZ(-1, 1, 1));
//            dxf.setEnd();
//            dxf.Finish();
        }
        
        
//        DXFDrawer dxf=new DXFDrawer("dxf/testattachment2.dxf");
//        LineInfo[] li=dxf.getLineInfoFromFile("wcscore/"
//                    + aids[0].getAttachmentName() + "_1"
//                    + ".dxf");
//        CoordXYZ direc=new CoordXYZ(0,1,0);
//        Calc c=new Calc();
//        dxf.setHeader();
//        for (int i = 0; i < li.length; i++) {
////            System.out.println(i);
//            LineInfo li1 = li[i];
//            CoordXYZ s=li1.getStartPoint();
//            CoordXYZ g=li1.getEndPoint();
//            CoordXYZ e=li1.getExtrudeDirection();
//            double t=li1.getThickness();
//            s=c.getCoordInWCS(s, direc);
//            g=c.getCoordInWCS(g, direc);
//            e=c.getCoordInWCS(e, direc);
//            dxf.drawLine3D("k", s, g, e, t);
//            
//        }
//        dxf.setEnd();
//        dxf.Finish();
        
        

    }
//    private  TXT_OPE to;
    private final String name;
    private final KPID aid;
    private String path;
    
    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public KPGenerator(KPID aid) {
//        this.to=new TXT_OPE();
//        to.setFileName("");
        this.aid=aid;
        this.name=aid.getAttachmentName();
    }
    
    public KPGenerator(String outputpath,KPID aid) {
        this.path=outputpath;
        this.aid=aid;
        this.name=aid.getAttachmentName();
    }
    
    public LineInfo[] getLine(){
        String b=aid.getBaseCoreShape();
        String w =aid.getWingCoreShape();
        double tk1=aid.gettk1();
        double tk2=aid.gettk2();
        double hk=aid.gethk();
        double D=aid.getD();
        String mode=aid.getMode();
        String series=aid.getSeries();
        Calc c=new Calc();
        
        DXFDrawer dxf=new DXFDrawer();
        ArrayList<LineInfo> li=new ArrayList<LineInfo>();
        LineInfo[] wline=dxf.getLineInfoFromFile(w);
        CoordXYZ woffset=new CoordXYZ(-(aid.getWingBoltConnectionLength()+70),0,0);
        for (int i = 0; i < wline.length; i++) {
            LineInfo wline1 = wline[i];
            CoordXYZ s=wline1.getStartPoint();
            CoordXYZ g=wline1.getEndPoint();
            s.add(woffset);
            g.add(woffset);
            s=c.getRotate(s, new CoordXYZ(0,1,0), -90);
            g=c.getRotate(g, new CoordXYZ(0,1,0), -90);
            CoordXYZ e=new CoordXYZ(-1,0,0);
            li.add(new LineInfo(s,g,e,tk1));
        }
        
//        DXFDrawer baseplate=new DXFDrawer(b);
        //底板
        LineInfo[] bline=dxf.getLineInfoFromFile(b);
        CoordXYZ boffset=new CoordXYZ(0,-hk/2,-10);
        for (int i = 0; i < bline.length; i++) {
            LineInfo bline1 = bline[i];
            CoordXYZ s=bline1.getStartPoint();
            s=c.getRotate(s, new CoordXYZ(1,0,0), 90);
            s.add(boffset);
            s=c.getRotate(s, new CoordXYZ(0,1,0), -90);
            CoordXYZ g=bline1.getEndPoint();
            g=c.getRotate(g, new CoordXYZ(1,0,0), 90);
            g.add(boffset);
            g=c.getRotate(g, new CoordXYZ(0,1,0), -90);
            CoordXYZ e=new CoordXYZ(0,-1,0);
            li.add(new LineInfo(s,g,e,tk2));            
        }
        return (LineInfo[])li.toArray(new LineInfo[0]);
    }
    
    public CircleInfo[] getCircleInfo(){
        ArrayList<CircleInfo> li=new ArrayList<CircleInfo>();
        CircleInfo[] wp=this.getWingPlateBolt(aid.gettk1());
        for (int i = 0; i < wp.length; i++) {
            li.add(wp[i]);
        }
        
        CircleInfo[] bp=this.getBasePlateBolt(aid.gettk2());
        for (int i = 0; i < bp.length; i++) {
            li.add(bp[i]);
        }
        return (CircleInfo[])li.toArray(new CircleInfo[0]);
    }
    
    public CircleInfo[] getBasePlateBolt(double t){
        ArrayList<CircleInfo> li=new ArrayList<CircleInfo>();
        double hk=aid.gethk();
        double D=aid.getD();
        String mode=aid.getMode();
        String series=aid.getSeries();
        Calc c=new Calc();
        
        CoordXYZ[] bp=null;
        if (mode == KPID.MODE_4&&series==KPID.SERIES_N) {
            double g=65;
            bp=new CoordXYZ[]{
                new CoordXYZ(40,g,0),
                new CoordXYZ(100,g,0),
                new CoordXYZ(160,g,0),
                new CoordXYZ(220,g,0),
            };
        }else if (mode == KPID.MODE_6&&series==KPID.SERIES_N) {
            double g=65;
            bp=new CoordXYZ[]{
                new CoordXYZ(40,g,0),
                new CoordXYZ(100,g,0),
                new CoordXYZ(160,g,0),
                new CoordXYZ(220,g,0),
                new CoordXYZ(280,g,0),
                new CoordXYZ(340,g,0),
            };
        }else if (mode == KPID.MODE_6&&series==KPID.SERIES_R) {
            double g=78;
            bp=new CoordXYZ[]{
                new CoordXYZ(40,g,0),
                new CoordXYZ(100,g,0),
                new CoordXYZ(160,g,0),
                new CoordXYZ(220,g,0),
                new CoordXYZ(280,g,0),
                new CoordXYZ(340,g,0),
            };
        }
        
        CoordXYZ boffset=new CoordXYZ(0,-hk/2,-10);
        for (int i = 0; i < bp.length; i++) {
            CoordXYZ bp1 = bp[i];
            bp1=c.getRotate(bp1, new CoordXYZ(1,0,0), 90);
            bp1.add(boffset);
            bp1=c.getRotate(bp1, new CoordXYZ(0,1,0), -90);
            CoordXYZ e=new CoordXYZ(0,-1,0);
            li.add(new CircleInfo(bp1,D/2,e,t));
        }
        return (CircleInfo[])li.toArray(new CircleInfo[0]);
    }
    
    public CircleInfo[] getWingPlateBolt(double t){
        ArrayList<CircleInfo> li=new ArrayList<CircleInfo>();
        double D=aid.getD();
        String mode=aid.getMode();
        String series=aid.getSeries();
        Calc c=new Calc();
        
        CoordXYZ[] wp=null;
        if (mode == KPID.MODE_4&&series==KPID.SERIES_N) {
            double g=65;
            wp=new CoordXYZ[]{
                new CoordXYZ(40,40,0),
                new CoordXYZ(40,100,0),
                new CoordXYZ(100,40,0),
                new CoordXYZ(100,100,0),
            };
        }else if (mode == KPID.MODE_6&&series==KPID.SERIES_N) {
            double g=65;
            wp=new CoordXYZ[]{
                new CoordXYZ(40,40,0),
                new CoordXYZ(40,100,0),
                new CoordXYZ(100,40,0),
                new CoordXYZ(100,100,0),
                new CoordXYZ(160,40,0),
                new CoordXYZ(160,100,0),
            };
        }else if (mode == KPID.MODE_6&&series==KPID.SERIES_R) {
            double g=78;
            wp=new CoordXYZ[]{
                new CoordXYZ(40,40,0),
                new CoordXYZ(40,100,0),
                new CoordXYZ(100,40,0),
                new CoordXYZ(100,100,0),
                new CoordXYZ(160,40,0),
                new CoordXYZ(160,100,0),
            };
        }
        
        CoordXYZ woffset=new CoordXYZ(-(aid.getWingBoltConnectionLength()+70),-70,0);
        for (int i = 0; i < wp.length; i++) {
            CoordXYZ wp1=wp[i];
            wp1.add(woffset);
            wp1=c.getRotate(wp1, new CoordXYZ(0,1,0), -90);
            CoordXYZ e=new CoordXYZ(-1,0,0);
            li.add(new CircleInfo(wp1,D/2,e,t));
        }
        
        return (CircleInfo[])li.toArray(new CircleInfo[0]);
    }
    
    public void draw(){
    
        String b=aid.getBaseCoreShape();
        String w =aid.getWingCoreShape();
        double tk1=aid.gettk1();
        double tk2=aid.gettk2();
        double D=aid.getD();
        String mode=aid.getMode();
        String series=aid.getSeries();
        
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        DXFDrawer dxf=new DXFDrawer(path+"baseplate.dxf");
        dxf.setHeader();
        dxf.extrusion(b, "0", direc, tk2);
        double[] p={40,100,160,220,280,340};
        if (mode == KPID.MODE_4&&series==KPID.SERIES_N) {
            double g=65;
            for (int i = 0; i < 4; i++) {
                double p1 = p[i];
                dxf.drawCircle3D("0", new CoordXYZ(p1, g, 0), D / 2,direc,tk2);
            }
        }else if (mode == KPID.MODE_6&&series==KPID.SERIES_N) {
            double g=65;
            for (int i = 0; i < p.length; i++) {
                double p1 = p[i];
                dxf.drawCircle3D("0", new CoordXYZ(p1, g, 0), D / 2,direc,tk2);
            }
        }else if (mode == KPID.MODE_6&&series==KPID.SERIES_R) {
            double g=78;
            for (int i = 0; i < p.length; i++) {
                double p1 = p[i];
                dxf.drawCircle3D("0", new CoordXYZ(p1, g, 0), D / 2,direc,tk2);
            }
        }
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+"wingplate.dxf");
        dxf.setHeader();
        dxf.extrusion(w, "0", direc, tk1);
        dxf.drawCircle3D("0", new CoordXYZ(40, 30, 0), D / 2, direc, tk1);
        dxf.drawCircle3D("0", new CoordXYZ(40, -30, 0), D / 2, direc, tk1);
        dxf.drawCircle3D("0", new CoordXYZ(100, 30, 0), D / 2, direc, tk1);
        dxf.drawCircle3D("0", new CoordXYZ(100, -30, 0), D / 2, direc, tk1);
        
        if (mode == KPID.MODE_6) {
            dxf.drawCircle3D("0", new CoordXYZ(160, 30, 0), D / 2,direc,tk1);
             dxf.drawCircle3D("0",new CoordXYZ(160, -30, 0), D / 2,direc,tk1);
        }
        dxf.setEnd();
        dxf.Finish();
        
        //assembly
        dxf=new DXFDrawer(path+"baseplate1.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+"baseplate.dxf", "0", new CoordXYZ(1,0,0), -90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+"kpl.dxf");
        dxf.setHeader();
        if (mode == KPID.MODE_4) {
            dxf.Move3D(path+"baseplate1.dxf", "0", new CoordXYZ(210, -40, 10),new CoordXYZ(1,1,1));
        }else if (mode == KPID.MODE_6) {
            dxf.Move3D(path+"baseplate1.dxf", "0", new CoordXYZ(270, -40, 10),new CoordXYZ(1,1,1));
        }
        dxf.Move3D(path+"wingplate.dxf", "0", new CoordXYZ(0, 0, 0),new CoordXYZ(1,1,1));
        dxf.setEnd();
        dxf.Finish();
        dxf=new DXFDrawer(path+"kpr.dxf");
        dxf.setHeader();
        dxf.Move3D(path+"kpl.dxf", "0",new CoordXYZ(0,0,0) ,new CoordXYZ(1, 1, -1));
        dxf.setEnd();
        dxf.Finish();
        
//        不要なファイルを削除
        String[] delete={
            path+"baseplate1.dxf",
//            path+"wingplate.dxf",
//            path+"baseplate.dxf",
        };
        for (String delete1 : delete) {
            File file = new File(delete1);
            if (file.exists()) file.delete();
        }
    }
    
    public String getName(){return name;}
     
}
