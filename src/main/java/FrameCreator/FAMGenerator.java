/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameCreator;

import FrameCreator.FAMID;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import util.LineInfo;
import util.Util;
import util.coord.CoordXYZ;
import util.coord.XYZSW;
import util.dxf.DXFDrawer;
import util.dxf.DXFSettings;
import util.matrix.Calc;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class FAMGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        CoordXYZ p1=new CoordXYZ(1000,1000,0);
        CoordXYZ p2=new CoordXYZ(1000,4000,0);
        FAMID famid=new FAMID(
                new LineInfo(p1,p2),
                new HSection(600,200,12,20,13),
                FAMID.TYPE_BEAM,
                "C00",
                0,new String[]{"p","p"}
        );
        FAMGenerator famg=new FAMGenerator(famid);
        DXFDrawer dxf=new DXFDrawer("dxf/test3.dxf");
        dxf.setHeader(new String[]{"0","1","x","y","z"}, 
                new int[]{DXFSettings.DXF_YELLOW,DXFSettings.DXF_WHITE,DXFSettings.DXF_RED,
                    DXFSettings.DXF_GREEN,DXFSettings.DXF_BLUE}, 
                new String[]{DXFSettings.DXF_CONTINUOUS,DXFSettings.DXF_CONTINUOUS,DXFSettings.DXF_CONTINUOUS,
                    DXFSettings.DXF_CONTINUOUS,DXFSettings.DXF_CONTINUOUS});
        dxf.drawLine3D("0", p1, p2);
        famg.draw(dxf);
        dxf.setEnd();
        dxf.Finish();
    }
    private final FAMID famid;
    private final XYZSW d;
    private String layername;
    private boolean flag;
    private CoordXYZ woffset;
    public FAMGenerator(FAMID famid,XYZSW d,String layername,CoordXYZ offset){
        this.famid=famid;
        this.d=d;
        flag=true;
        this.layername=layername;
        this.woffset=offset;
    }
    public FAMGenerator(FAMID famid,XYZSW d,String layername){
        this(famid,d,layername,new CoordXYZ(0,0,0));
    }
    public FAMGenerator(FAMID famid){
        this.famid=famid;
        this.d=XYZSW.S;
        flag=false;
        this.woffset=new CoordXYZ(0,0,0);
        this.layername="";
    }
    
    public void drawOnlyMember(DXFDrawer dxf){
        /**
         * ①OCS座標系で断面を作成・回転
         * ②WCS座標系で断面を平行移動
         * ③drawLine3Dで押し出し
         */
        Calc calc=new Calc();
        LineInfo line=famid.getLineInfo();
        CoordXYZ o1=line.getStartPoint();
        CoordXYZ direc=line.getDirection();
//        direc.print();
        LineInfo[] shape=famid.getSectionInfo().get2DShape(3);
        double offset0=famid.getOffset()[0];//0端のオフセット距離
        double offset1=famid.getOffset()[1];//1端のオフセット距離
        double l=line.getLineLength()-offset0-offset1;
        CoordXYZ sp=new CoordXYZ(
                o1.getX()+offset0*direc.getX(),
                o1.getY()+offset0*direc.getY(),
                o1.getZ()+offset0*direc.getZ()
        );
        String label=famid.getLabel();
        
//        if(flag)label=layername;
//        else label=famid.getLabel();
        
        for (int i = 0; i < shape.length; i++) {
            LineInfo shape1 = shape[i];
            CoordXYZ s1=calc.getRotate(shape1.getStartPoint(), new CoordXYZ(0,0,1), famid.getTheta());
            CoordXYZ e1=calc.getRotate(shape1.getEndPoint(), new CoordXYZ(0,0,1), famid.getTheta());
            CoordXYZ s2=calc.getCoordInWCS(s1, direc);
            CoordXYZ e2=calc.getCoordInWCS(e1, direc);
            s2.add(sp);
            e2.add(sp);
            //全体的なoffset処理
            s2.add(woffset);
            e2.add(woffset);
            dxf.drawLine3D(label, s2, e2,direc,l);
        }
        
    }
    
    public void draw(DXFDrawer dxf){
        /**
         * ①OCS座標系で断面を作成・回転
         * ②WCS座標系で断面を平行移動
         * ③drawLine3Dで押し出し
         */
        Calc calc=new Calc();
        LineInfo line=famid.getLineInfo();
        CoordXYZ o1=line.getStartPoint();
        CoordXYZ direc=line.getDirection();
//        direc.print();
        LineInfo[] shape=famid.getSectionInfo().get2DShape(3);
        double offset0=famid.getOffset()[0];//0端のオフセット距離
        double offset1=famid.getOffset()[1];//1端のオフセット距離
        double l=line.getLineLength()-offset0-offset1;
        CoordXYZ sp=new CoordXYZ(
                o1.getX()+offset0*direc.getX(),
                o1.getY()+offset0*direc.getY(),
                o1.getZ()+offset0*direc.getZ()
        );
        String label=famid.getLabel();
        
//        if(flag)label=layername;
//        else label=famid.getLabel();
        
        for (int i = 0; i < shape.length; i++) {
            LineInfo shape1 = shape[i];
            CoordXYZ s1=calc.getRotate(shape1.getStartPoint(), new CoordXYZ(0,0,1), famid.getTheta());
            CoordXYZ e1=calc.getRotate(shape1.getEndPoint(), new CoordXYZ(0,0,1), famid.getTheta());
            CoordXYZ s2=calc.getCoordInWCS(s1, direc);
            CoordXYZ e2=calc.getCoordInWCS(e1, direc);
            s2.add(sp);
            e2.add(sp);
            //全体的なoffset処理
            s2.add(woffset);
            e2.add(woffset);
            dxf.drawLine3D(label, s2, e2,direc,l);
        }
        
        int type =famid.getMemberType();
        CoordXYZ[] tli=famid.getTextPosition(type);
        CoordXYZ x=new CoordXYZ(-1,0,0);
        CoordXYZ y=new CoordXYZ(0,-1,0);
        CoordXYZ z=new CoordXYZ(0,0,1);
        
        if(type==FAMID.TYPE_COLUMN||type==FAMID.TYPE_BEAM){
            CoordXYZ ls1 = line.getStartPoint();
            CoordXYZ le1 = line.getEndPoint();
            CoordXYZ center = new CoordXYZ(0, 0, 0);
            center.add(ls1);
            center.add(le1);
            center.multiply(0.5);
            double l2=line.getLineLength();
//            center.multiply(l/l2);
            tli[0].add(center);
            tli[1].add(center);
            //全体的なoffset処理
            tli[0].add(woffset);
            tli[1].add(woffset);
            tli[2].add(woffset);
            if (d == XYZSW.X || d == XYZSW.S) {
                String tlabel="x";
                if(flag)tlabel=label;
                dxf.drawTEXT3D(tlabel, tli[0], famid.getLabel(), x);
            }
            if (d == XYZSW.Y || d == XYZSW.S) {
                String tlabel="y";
                if(flag)tlabel=label;
                dxf.drawTEXT3D(tlabel, tli[0], famid.getLabel(), y);
            }
            if (d == XYZSW.Z || d == XYZSW.S) {
                String tlabel="z";
                if(flag)tlabel=label;
                if (type == FAMID.TYPE_COLUMN) {
                    tli[2].add(le1);
                    dxf.drawTEXT3D(tlabel, tli[2], famid.getLabel(), z);
                } else if (type == FAMID.TYPE_BEAM) {
                    tli[2].add(center);
                    dxf.drawTEXT3D(tlabel, tli[2], famid.getLabel(), z);
                }
            }
        }
        
        
    }
    
    
}
