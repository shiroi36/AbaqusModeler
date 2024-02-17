/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DetailplanCreator;

import FrameCreator.FAMGenerator;
import FrameCreator.FAMID;
import util.CircleInfo;
import util.LineInfo;
import util.PATH;
import util.coord.CoordXYZ;
import util.coord.XYZSW;
import util.dxf.DXFDrawer;
import util.io.SQL_OPE;
import util.matrix.Calc;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class DetailPlanDrawer {
    
    private final double lb=1800;//仕口の水平考慮長さ。これ以上の幅をもつ仕口は、ここを変更する必要あり
    private final double lc=1800;//仕口の鉛直考慮長さ。これ以上の幅をもつ仕口は、ここを変更する必要あり
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DetailPlanDrawer dpd=new DetailPlanDrawer(
                "D:/Dropbox/Dropbox (SSLUoT)/00_16_webclamp/080_駐車場開発/00_埼玉駐車場/detailplan/jointset/",
                new SQL_OPE(PATH.DB_DROPBOX_DB+"test;ifexists=true","junapp",""));
        String path="D:/Dropbox/Dropbox (SSLUoT)/00_16_webclamp/080_駐車場開発/00_埼玉駐車場/detailplan/";
//        dpd.drawWholeFrame(path+"detailplan.dxf");
        
        
        XYZSW[] d={XYZSW.X,XYZSW.Y,XYZSW.Z};
        double[][] span={
            {0,5000,7500,7500,7500,7500,5100,7500,7500,7500,5000},
            {0,4000,8000,4000,4000,8000,4000},
            {0,3330,3130,3130}
        };
        String[][] sname={
            {"X1","X2","X3","X4","X5","X6","X7","X8","X9","X10","X11"},
            {"Y1","Y2","Y3","Y4","Y5","Y6","Y7"},
            {"F0","F1","F2","F3"}
        };
        CoordXYZ[] tdirec={
            new CoordXYZ(0,1,0),new CoordXYZ(1,0,0),new CoordXYZ(1,0,0)};
        double search=500;
        double space=80000;
        
        for (int i = 0; i < span.length; i++) {
            DXFDrawer dxf=new DXFDrawer(path+d[i].getName()+".dxf");
            dxf.setHeader();
            double[] span1 = span[i];
            double s=0;
            CoordXYZ direc=tdirec[i];
            for (int j = 0; j < span1.length; j++) {
                s += span1[j];
                double m=space*j;
                System.out.println("outputting "+ sname[i][j]+".....");
                dpd.drawPartialFrame(
                        dxf,d[i], s-search, s+search,
                        new CoordXYZ(direc.getX()*m,direc.getY()*m,direc.getZ()*m));
            }
            dxf.setEnd();
            dxf.Finish();
        }
        
        
        
        
    }
    private final String jpath;
    private final SQL_OPE sql;
    
    public DetailPlanDrawer(String jsetpath,SQL_OPE sql){
        this.jpath=jsetpath;
        this.sql=sql;
    }
    
    public void drawPartialFrame(DXFDrawer dxf,XYZSW d,double min,double max,CoordXYZ move) {
        int[] num = sql.getQueryDataInt("select group1 "
                + "from detailplan.COLGROUP2 "
                + "group by group1 order by group1;")[0];
        String[][] b2c = sql.transpose(
                sql.getQueryDataString("select a.B2CID,b.CX,b.CY,b.CZ \n"
                        + "from DETAILPLAN.B2CGROUP a,detailplan.NODE b \n"
                        + "where a.NODE=b.ID;"));
        String[][] beam = sql.transpose(sql.getQueryDataString("select \n"
                + "c1.CX,c1.CY,c1.CZ,\n"
                + "c2.CX,c2.CY,c2.CZ,\n"
                + "b.DIREC,b.SIZE0,b.SIZE1,b.SIZE2,b.SIZE3,b.SIZE4\n"
                + "from DETAILPLAN.ELEM a,\n"
                + "detailplan.SECT b,\n"
                + "detailplan.NODE c1,\n"
                + "detailplan.NODE c2 \n"
                + "where \n"
                + "lower(a.\"TYPE\")='girder' and \n"
                + "b.ID=a.\"SECTION\" and \n"
                + "a.NODE1 =c1.ID and \n"
                + "a.NODE2 =c2.ID \n"
                + ";"));
                

//        DXFDrawer dxf = new DXFDrawer(path);
//        dxf.setHeader();
        //柱の描画
        for (int i = 0; i < num.length; i++) {
            LineInfo[] c=this.getColumn(num[i]);
            LineInfo check=c[0];
            if(d==XYZSW.X){
                double r1=check.getStartPoint().getX();
                double r2=check.getEndPoint().getX();
                if((r1<min||max<r1)||(r2<min||max<r2))continue;
            }else if(d==XYZSW.Y){
                double r1=check.getStartPoint().getY();
                double r2=check.getEndPoint().getY();
                if((r1<min||max<r1)||(r2<min||max<r2))continue;
            }
            for (int j = 0; j < c.length; j++) {
                LineInfo c1 = c[j];
                CoordXYZ s=c1.getStartPoint();
                CoordXYZ g=c1.getEndPoint();
                s.add(move);
                g.add(move);
                dxf.drawLine3D(c1.getLayerName(),s,g,c1.getExtrudeDirection(),c1.getThickness());
            }
        }
        
        //柱梁接合部の描画
        for (int i = 0; i < b2c.length; i++) {
            String[] b2c1 = b2c[i];
            int b2cid=Integer.parseInt(b2c1[0]);
            CoordXYZ point=new CoordXYZ(
                    Double.parseDouble(b2c1[1]),
                    Double.parseDouble(b2c1[2]),
                    Double.parseDouble(b2c1[3])
            );
            point.add(move);
//            point.print();
            if(d==XYZSW.X){
                double r1=point.getX();
                if((r1<min||max<r1))continue;
            }else if(d==XYZSW.Y){
                double r1=point.getY();
                if((r1<min||max<r1))continue;
            }else if(d==XYZSW.Z){
                double r1=point.getZ();
                if((r1<min||max<r1))continue;
            }
            this.setB2C(dxf, b2cid, point);
        }
        //梁の描画
        for (int i = 0; i < beam.length; i++) {
            String[] beam1 = beam[i];
            CoordXYZ s=new CoordXYZ(
                    Double.parseDouble(beam1[0]),
                    Double.parseDouble(beam1[1]),
                    Double.parseDouble(beam1[2])
            );
            CoordXYZ g=new CoordXYZ(
                    Double.parseDouble(beam1[3]),
                    Double.parseDouble(beam1[4]),
                    Double.parseDouble(beam1[5])
            );
            double a = 0;
            HSection bsec = new HSection(
                    Double.parseDouble(beam1[7]),
                    Double.parseDouble(beam1[8]),
                    Double.parseDouble(beam1[9]),
                    Double.parseDouble(beam1[10]),
                    Double.parseDouble(beam1[11])
            );
            if(d==XYZSW.X){
                double r1=s.getX();
                double r2=g.getX();
                if((r1<min||max<r1)||(r2<min||max<r2))continue;
            }else if(d==XYZSW.Y){
                double r1=s.getY();
                double r2=g.getY();
                if((r1<min||max<r1)||(r2<min||max<r2))continue;
            }else if(d==XYZSW.Z){
                double r1=s.getZ();
                double r2=g.getZ();
                if((r1<min||max<r1)||(r2<min||max<r2))continue;
            }
            s.add(move);
            g.add(move);
            FAMID famid=new FAMID(
                    new LineInfo(s,g),
                    bsec,FAMID.TYPE_BEAM,
                    bsec.getName(),
                    a,
                    new String[]{"r900","r900"});
            FAMGenerator fg=new FAMGenerator(famid,XYZSW.Z,"BEAM_" + bsec.getName());
//            fg.draw(dxf);
            fg.drawOnlyMember(dxf);
        }
//        dxf.setEnd();
//        dxf.Finish();
        
    }
    public void drawWholeFrame(String path) {
        int[] num = sql.getQueryDataInt("select group1 "
                + "from detailplan.COLGROUP2 "
                + "group by group1 order by group1;")[0];
        String[][] b2c = sql.transpose(
                sql.getQueryDataString("select a.B2CID,b.CX,b.CY,b.CZ \n"
                        + "from DETAILPLAN.B2CGROUP a,detailplan.NODE b \n"
                        + "where a.NODE=b.ID;"));
        String[][] beam = sql.transpose(sql.getQueryDataString("select \n"
                + "c1.CX,c1.CY,c1.CZ,\n"
                + "c2.CX,c2.CY,c2.CZ,\n"
                + "b.DIREC,b.SIZE0,b.SIZE1,b.SIZE2,b.SIZE3,b.SIZE4\n"
                + "from DETAILPLAN.ELEM a,\n"
                + "detailplan.SECT b,\n"
                + "detailplan.NODE c1,\n"
                + "detailplan.NODE c2 \n"
                + "where \n"
                + "lower(a.\"TYPE\")='girder' and \n"
                + "b.ID=a.\"SECTION\" and \n"
                + "a.NODE1 =c1.ID and \n"
                + "a.NODE2 =c2.ID \n"
                + ";"));
                

        DXFDrawer dxf = new DXFDrawer(path);
        dxf.setHeader();
        //柱の描画
        for (int i = 0; i < num.length; i++) {
            LineInfo[] c=this.getColumn(num[i]);
            for (int j = 0; j < c.length; j++) {
                LineInfo c1 = c[j];
                dxf.drawLine3D(c1);
            }
        }
        //柱梁接合部の描画
        for (int i = 0; i < b2c.length; i++) {
            String[] b2c1 = b2c[i];
            int b2cid=Integer.parseInt(b2c1[0]);
            CoordXYZ point=new CoordXYZ(
                    Double.parseDouble(b2c1[1]),
                    Double.parseDouble(b2c1[2]),
                    Double.parseDouble(b2c1[3])
            );
            this.setB2C(dxf, b2cid, point);
        }
        //梁の描画
        for (int i = 0; i < beam.length; i++) {
            String[] beam1 = beam[i];
            CoordXYZ s=new CoordXYZ(
                    Double.parseDouble(beam1[0]),
                    Double.parseDouble(beam1[1]),
                    Double.parseDouble(beam1[2])
            );
            CoordXYZ g=new CoordXYZ(
                    Double.parseDouble(beam1[3]),
                    Double.parseDouble(beam1[4]),
                    Double.parseDouble(beam1[5])
            );
            double a = 0;
            HSection bsec = new HSection(
                    Double.parseDouble(beam1[7]),
                    Double.parseDouble(beam1[8]),
                    Double.parseDouble(beam1[9]),
                    Double.parseDouble(beam1[10]),
                    Double.parseDouble(beam1[11])
            );
            FAMID famid=new FAMID(
                    new LineInfo(s,g),
                    bsec,FAMID.TYPE_BEAM,
                    bsec.getName(),
                    a,
                    new String[]{"r900","r900"});
            FAMGenerator fg=new FAMGenerator(famid,XYZSW.Z,"BEAM_" + bsec.getName());
            fg.draw(dxf);
        }
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    private void setB2C(DXFDrawer dxf,int b2cid,CoordXYZ point){
//            dxf.drawTEXT3D("", point, "b2c_"+b2cid, new CoordXYZ(0,0,1));
            LineInfo[] li=dxf.getLineInfoFromFile(jpath+"b2c_"+b2cid+".dxf");
            for (int j = 0; j < li.length; j++) {
                LineInfo li1 = li[j];
                if(li1.getLayerName().toLowerCase().contains("col"))continue;
                CoordXYZ s=li1.getStartPoint();
                CoordXYZ g=li1.getEndPoint();
                s.add(point);
                g.add(point);
                dxf.drawLine3D(li1.getLayerName(), s, g, 
                        li1.getExtrudeDirection(), li1.getThickness());
            }
            CircleInfo[] ci=dxf.getCircleInfoFromFile(jpath+"b2c_"+b2cid+".dxf");
            Calc calc= new Calc();
            for (int j = 0; j < ci.length; j++) {
                CircleInfo ci1 = ci[j];
                CoordXYZ e = ci1.getExtrudeDirection();
                CoordXYZ cen = ci1.getCenterPoint();
                CoordXYZ p1 = calc.getCoordInWCS(cen, e);
                p1.add(point);
                dxf.drawCircle3D(
                        ci1.getLayerName(), p1, ci1.getRadius(),
                        e, ci1.getThickness());
            }
    }
    
    
    private LineInfo[] getColumn(int gid1){
            sql.executeUpdate("set @g1=" + gid1 + ";");
            String[] s = sql.transpose(
                    sql.getQueryDataString("select \n"
                            + "b.CX,b.CY,b.CZ,\n"
                            + "c.DIREC,c.SIZE0,c.SIZE1,c.SIZE2,c.SIZE3,c.SIZE4 \n"
                            + "from \n"
                            + "DETAILPLAN.COLGROUP2 a,\n"
                            + "detailplan.NODE b, \n"
                            + "detailplan.SECT c \n"
                            + "where \n"
                            + "a.NODE=b.ID and \n"
                            + "a.\"SECTION\"=c.ID \n"
                            + "and a.GROUP2=0 \n"
                            + "and a.GROUP1=@g1 \n"
                            + "order by group1,group2;"))[0];
            CoordXYZ sp = new CoordXYZ(
                    Double.parseDouble(s[0]),
                    Double.parseDouble(s[1]),
                    Double.parseDouble(s[2])
            );
            String direc = s[3];
            HSection csec = new HSection(
                    Double.parseDouble(s[4]),
                    Double.parseDouble(s[5]),
                    Double.parseDouble(s[6]),
                    Double.parseDouble(s[7]),
                    Double.parseDouble(s[8])
            );
            double a = 0;
            if ("hkyou".equals(direc.toLowerCase())) {
                a = 90;
            }

            String[] g = sql.transpose(
                sql.getQueryDataString("select  b.CX,b.CY,b.CZ,e.CONTENT\n"
                        + "from \n"
                        + "DETAILPLAN.COLGROUP2 a,\n"
                        + "detailplan.NODE b, \n"
                        + "detailplan.SECT c,\n"
                        + "detailplan.B2CGROUP d,\n"
                        + "detailplan.B2CRECORD e  \n"
                        + "where \n"
                        + "a.NODE=b.ID and \n"
                        + "a.\"SECTION\"=c.ID and \n"
                        + "d.NODE=a.NODE and \n"
                        + "d.B2CID=e.B2CID and\n"
                        + "lower(e.\"TYPE\")='col_length' and \n"
                        + "lower(a.\"STATE\")='rf' and \n"
                        + "a.GROUP1=@g1 \n"
                        + "order by group1,group2;"))[0];
            CoordXYZ gp = new CoordXYZ(
                    Double.parseDouble(g[0]),
                    Double.parseDouble(g[1]),
                    Double.parseDouble(g[2])
            );
            double lc_end=Double.parseDouble(g[3]);
            LineInfo li = new LineInfo(sp, gp);
            CoordXYZ e = li.getDirection();
            double l = li.getLineLength();
            l += lc_end-lc / 2;

            LineInfo[] csecli = csec.get2DShape(2);
            LineInfo[] csecli2=new LineInfo[csecli.length];
            Calc c = new Calc();

            for (int j = 0; j < csecli.length; j++) {
                LineInfo csecli1 = csecli[j];
                CoordXYZ s1 = c.getRotate(csecli1.getStartPoint(), e, a);
                CoordXYZ e1 = c.getRotate(csecli1.getEndPoint(), e, a);
                s1.add(sp);
                e1.add(sp);
                csecli2[j]=new LineInfo("COL_" + csec.getName(), s1, e1, e, l);
            }
            return csecli2;
    }
    
}
