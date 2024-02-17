/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DetailplanCreator;


import JointCreator.ID.CFRID;
import JointCreator.ID.CGID;
import JointCreator.ID.KPID;
import JointCreator.ID.SHID;
import JointCreator.WCSJointID;
import JointCreator.generator.CFRGenerator;
import JointCreator.generator.CGGenerator;
import JointCreator.generator.KPGenerator;
import JointCreator.generator.SHGenerator;
import java.util.ArrayList;
import java.util.Arrays;
import util.ArcInfo;
import util.CircleInfo;
import util.DataRecorder;
import util.LineInfo;
import util.PATH;
import util.compare.CompareInterface;
import util.compare.ListProducer;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.SQL_OPE;
import util.matrix.Calc;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class JoistCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SQL_OPE sql=new SQL_OPE(PATH.DB_DROPBOX_DB+"webclamp_stm/test;ifexists=true","junapp","");
        
        JoistCreator b2c=new JoistCreator(
                "D:/Dropbox/Dropbox (SSLUoT)/00_16_webclamp/080_駐車場開発/00_埼玉駐車場/detailplan/joistset/",
                sql        );
//        b2c.Draw();
//        b2c.drawJointList();
    }
    private final SQL_OPE sql;
    private final double lb=1800;//仕口の水平考慮長さ。これ以上の幅をもつ仕口は、ここを変更する必要あり
    private final double cr=20;//柱梁仕口のクリアランス 
    private Calc c=new Calc();
    private final String path;
    private final int[] nodeid;
    private final JoistGroup[] jg;
    private final String[] filename;
    
    public JoistCreator(String path,SQL_OPE sql){
        this.sql=sql;
        this.setDB();
        this.path = path;
        //detailplan.sectにのみある小梁節点番号を検索
        this.nodeid = sql.getQueryDataInt(
                "select node from DETAILPLAN.JOISTGROUP a \n"
                + "where a.\"SECTION\" in(select id from detailplan.sect) "
                + "group by node;")[0];
        this.jg=new JoistGroup[nodeid.length];
        String nodeid_s[]=new String[nodeid.length];
        filename=new String[nodeid.length];
        
        for (int i = 0; i < nodeid.length; i++) {
            int o = nodeid[i];
            nodeid_s[i]=""+o;
            jg[i]=this.getJoistGroup(o);
            filename[i]=path+"node"+nodeid[i];
        }
        
        ListProducer lp=new ListProducer(nodeid_s,jg);
        CompareInterface[] ci=lp.getList();
        String[][] idset=lp.getIDSet();
        
        System.out.println("ci.length = " + ci.length);
        System.out.println("nodeid.length = " + nodeid.length);
        JoistGroup[] jg2=new JoistGroup[ci.length];
        for (int i = 0; i < ci.length; i++) {
            CompareInterface ci1 = ci[i];
            jg2[i]=(JoistGroup)ci1;
            System.out.println("--------------JoistListGroup-----------");
            jg2[i].print();
        }
        
        for (int i = 0; i < idset.length; i++) {
            String[] idset1 = idset[i];
            System.out.println(Arrays.toString(idset1));
        }
        
        
//
////梁天端の計算
////        System.out.println("--------------------梁天端");
//        this.hariten=new double[b2cval.length];
//        for (int i = 0; i < b2cval.length; i++) {
//            for (int j = 0; j < bsec[i].length; j++) {
//                HSection bsec1 = bsec[i][j];
////                System.out.println("\t"+bsec1.getH());
//                if(hariten[i]<bsec1.getH()/2)hariten[i]=bsec1.getH()/2;
//            }
////            System.out.println(hariten[i]);
//        }
//        this.Draw();
    }
    
    
    public void Draw(){
        CoordXYZ gap=new CoordXYZ(0,0,0);
        for (int i = 0; i < jg.length; i++) {
            System.out.println(filename[i]+".dxf");
            JoistGroup jg1=jg[i];
            DXFDrawer dxf=new DXFDrawer(filename[i]+".dxf");
            dxf.setHeader();
            HSection b1=jg1.getMainBeam();
            CoordXYZ bd1=jg1.getMainBeamDirection();
            double lb1=1800;
            LineInfo[] li=b1.get2DShape(3);
            
            for (int j = 0; j < li.length; j++) {
                LineInfo li1 = li[j];
                LineInfo li2=this.getTransformedLine(
                        new LineInfo(b1.getName(),li1.getStartPoint(),li1.getEndPoint(),new CoordXYZ(0,0,1),lb1),
                        b1.getName(), new CoordXYZ(0,0,-lb1/2),bd1, new CoordXYZ(1,1,1), gap);
                dxf.drawLine3D(li2);
            }
            
            HSection[] b2=jg1.getJoist();
            CoordXYZ[] bd2=jg1.getJoistDirection();
            for (int j = 0; j < bd2.length; j++) {
                LineInfo[] li1=b2[j].get2DShape(3);
                CoordXYZ bd21 = bd2[j];
                double lb2 = 900 - b1.getB() / 2 - 20;
                for (int k = 0; k < li1.length; k++) {
                    LineInfo li11 = li1[k];
                    LineInfo li2 = this.getTransformedLine(
                            new LineInfo(b1.getName(), li11.getStartPoint(), li11.getEndPoint(), new CoordXYZ(0, 0, 1), lb2),
                            b1.getName(), new CoordXYZ(0, 0, b1.getB() / 2 + 20), bd21, new CoordXYZ(1, 1, 1), gap);
                    dxf.drawLine3D(li2);
                }
            }
            
            dxf.setEnd();
            dxf.Finish();
        }
    }
    
    public JoistGroup getJoistGroup(int nodeid){
        System.out.println("node: "+nodeid);
        sql.executeUpdate("set @node="+ nodeid+ ";");
        String[] v = sql.transpose(sql.getQueryDataString("select "
                + "c.CX,c.CY,c.CZ \n"
                + "from detailplan.NODE c \n"
                + "where c.ID=@node;"))[0];
        CoordXYZ p0=new CoordXYZ(
                Double.parseDouble(v[0]),
                Double.parseDouble(v[1]),
                Double.parseDouble(v[2])
        );
        
        //接合部材の検索（joistgroupは小梁を有する節点）
        String[][] val1 = sql.transpose(
                sql.getQueryDataString("select \n"
                + "a.DX,a.DY,a.DZ,\n"
                + "b.DIREC,\n"
                + "b.SIZE0,\n"
                + "b.SIZE1,\n"
                + "b.SIZE2,\n"
                + "b.SIZE3,\n"
                + "b.SIZE4 \n"
                + "from detailplan.JOIstGROUP a, \n"
                + "detailplan.SECT b, \n"
                + "detailplan.NODE c  \n"
                + "where a.NODE=@node and \n"
                + "lower(a.\"END\")='p' and\n"
                + "a.NODE=c.ID and\n"
                + " b.ID=a.\"SECTION\"\n"
                + ";"));
        CoordXYZ[] jst_d=new CoordXYZ[val1.length];
        HSection[] jstsec=new HSection[val1.length];
        for (int i = 0; i < val1.length; i++) {
            String[] val11 = val1[i];
            jst_d[i]=new CoordXYZ(
                    Double.parseDouble(val11[0]),
                    Double.parseDouble(val11[1]),
                    Double.parseDouble(val11[2])
            );
            jstsec[i]=new HSection(
                    Double.parseDouble(val11[4]),
                    Double.parseDouble(val11[5]),
                    Double.parseDouble(val11[6]),
                    Double.parseDouble(val11[7]),
                    Double.parseDouble(val11[8])
            );
        }
        
        //接合先の梁の検索
        //接合先の梁の検索
        
        String[][] val2 = sql.transpose(
                sql.getQueryDataString("select \n"
                + "b1.CX,b1.CY,b1.CZ, \n"
                + "b2.CX,b2.CY,b2.CZ, \n"
                + "c.DIREC,\n"
                + "c.SIZE0,\n"
                + "c.SIZE1,\n"
                + "c.SIZE2,\n"
                + "c.SIZE3,\n"
                + "c.SIZE4\n"
                + "from detailplan.ELEM a,\n"
                + "detailplan.NODE b1,\n"
                + "detailplan.NODE b2,\n"
                + "detailplan.SECT c \n"
                + "where \n"
                + "a.NODE1=b1.ID and \n"
                + "a.NODE2=b2.ID and \n"
                + "a.NODE1 not in (@node) and \n"
                + "a.NODE2 not in (@node)  and \n"
                + "lower(a.\"TYPE\")='girder' and \n"
                + "a.\"SECTION\" =c.ID\n"
                + ";"));
        
        LineInfo[] girli=new LineInfo[val2.length];
        HSection[] girsec=new HSection[val2.length];
        for (int i = 0; i < val2.length; i++) {
            String[] val21 = val2[i];
            CoordXYZ s=new CoordXYZ(
                    Double.parseDouble(val21[0]),
                    Double.parseDouble(val21[1]),
                    Double.parseDouble(val21[2])
            );
            CoordXYZ g=new CoordXYZ(
                    Double.parseDouble(val21[3]),
                    Double.parseDouble(val21[4]),
                    Double.parseDouble(val21[5])
            );
            girli[i]=new LineInfo(s,g);
            girsec[i]=new HSection(
                    Double.parseDouble(val21[7]),
                    Double.parseDouble(val21[8]),
                    Double.parseDouble(val21[9]),
                    Double.parseDouble(val21[10]),
                    Double.parseDouble(val21[11])
            );
        }
        
        LineInfo gline = null;
        HSection gsec = null;
        for (int i = 0; i < girsec.length; i++) {
            HSection girsec1 = girsec[i];
            LineInfo girli1 = girli[i];
            CoordXYZ d1 = girli1.getDirection();
            CoordXYZ s = girli1.getStartPoint();
            CoordXYZ g = girli1.getEndPoint();
            CoordXYZ d2 = new CoordXYZ(
                    p0.getX() - s.getX(),
                    p0.getY() - s.getY(),
                    p0.getZ() - s.getZ()
            );
            //d1とd2が平行かどうかをチェックして、接合先の梁を検索する。※割り算はあまりよくない
            double cx=d1.getY()*d2.getZ()-d1.getZ()*d2.getY();
            double cy=d1.getZ()*d2.getX()-d1.getX()*d2.getZ();
            double cz=d1.getX()*d2.getY()-d1.getY()*d2.getX();
            double c2=Math.sqrt(Math.pow(cx, 2)+Math.pow(cy, 2)+Math.pow(cz, 2));
            
            //中間節点かどうかチェック
            if(c2<0.001&&this.checkIntermediatepoint(p0, s, g)){
//                System.out.println("c = " + c2);
//                System.out.println(girsec1.print()[2]);;
//                System.out.println("---------------");
                gline=girli1;
                gsec=girsec1;
            }
            
        }
        JoistGroup jg=new JoistGroup(p0,gsec, gline.getDirection(), jstsec, jst_d);
//        jg.print();
        return jg;
    }
    
    private boolean checkIntermediatepoint(CoordXYZ p0,CoordXYZ s,CoordXYZ g){
        double a1=(s.getX()-p0.getX())*(g.getX()-p0.getX());
        double a2=(s.getY()-p0.getY())*(g.getY()-p0.getY());
        double a3=(s.getZ()-p0.getZ())*(g.getZ()-p0.getZ());
        if(a1+a2+a3<=0)return true;
        else return false;
    }
    
    
    public void setDB(){
        sql.executeUpdate("drop table if exists detailplan.joistrecord;");
        sql.executeUpdate("create table if not exists detailplan.joistrecord "
                + "( rownumber int identity(1,1), "
                + " b2cid int,"
                + " type varchar,"
                + " content varchar "
                + ");");
    }
    
    public void drawJointList(){
//        double a=2200;
//        DXFDrawer dxf=new DXFDrawer(listpath);
//        dxf.setHeader();
//        int num1=5;
//        int num2=filenames.length/num1;
//        int i2=0;
//        
//        for (int j = 0; j < num2; j++) {
//            double x=5.5*a*j;
//            for (int i = 0; i < num1; i++) {
//                i2++;
//                if (i2 == filenames.length) {
//                    break;
//                }
//                String filename = filenames[i2] + ".dxf";
//                String text = "B2CID:" + b2cval[i2][0];
//                String[] val = dr[i2].getRecord();
//
//                dxf.drawTEXT3D("text", new CoordXYZ(-a+x, -a * i, 0), text, new CoordXYZ(0, 0, 1), 300);
//                dxf.Rotate3D(filename, new CoordXYZ[]{new CoordXYZ(1, 0, 0)}, new double[]{0}, new CoordXYZ(0+x, -a * i, 0));
//                dxf.Rotate3D(filename, new CoordXYZ[]{new CoordXYZ(1, 0, 0)}, new double[]{-90}, new CoordXYZ(a+x, -a * i, 0));
//                dxf.Rotate3D(filename,
//                        new CoordXYZ[]{new CoordXYZ(1, 0, 0), new CoordXYZ(0, 1, 0)},
//                        new double[]{-90, 90}, new CoordXYZ(2 * a+x, -a * i, 0));
//                for (int k = 0; k < val.length; k++) {
//                    String val1 = val[k];
//                    dxf.drawTEXT3D("text", new CoordXYZ(3 * a+x, (0.5 - i) * a - 100 * (k + 3), 0), val1, new CoordXYZ(0, 0, 1), 50);
//
//                }
//                dxf.drawLine3D("text", new CoordXYZ(-1.5 * a+x, -a * i - a / 2, 0), new CoordXYZ(3.5 * a+x, -a * i - a / 2, 0));
//
//            }
//        }
//        dxf.setEnd();
//        dxf.Finish();
        
    }
    
    
    private CircleInfo getTransformedCircle(CircleInfo ci1,String layer,CoordXYZ move,CoordXYZ direc,CoordXYZ mag,CoordXYZ gap){
            //directionに沿って円を変換
            CoordXYZ cen1=ci1.getCenterPoint();
            CoordXYZ e1=ci1.getExtrudeDirection();
            CoordXYZ cen=new CoordXYZ(cen1.getX(),cen1.getY(),cen1.getZ());
            CoordXYZ e=new CoordXYZ(e1.getX(),e1.getY(),e1.getZ());
            double t=ci1.getThickness();
            double r=ci1.getRadius();
            
            cen.add(move);
            cen.multiply(mag);
            cen.add(gap);
            
            
            e.multiply(mag);
            cen=c.getCoordInWCS(cen, direc);
            e=c.getCoordInWCS(e, direc);
            return new CircleInfo(layer,cen,r,e,t);
    }
    
    
    private LineInfo getTransformedLine(LineInfo li1,String layer,CoordXYZ move,CoordXYZ direc,CoordXYZ mag,CoordXYZ gap){
            //directionに沿って線を変換
            CoordXYZ s1=li1.getStartPoint();
            CoordXYZ g1=li1.getEndPoint();
            CoordXYZ e1=li1.getExtrudeDirection();
            CoordXYZ s=new CoordXYZ(s1.getX(),s1.getY(),s1.getZ());
            CoordXYZ g=new CoordXYZ(g1.getX(),g1.getY(),g1.getZ());
            CoordXYZ e=new CoordXYZ(e1.getX(),e1.getY(),e1.getZ());
            double t=li1.getThickness();
            s.add(move);
            g.add(move);
            s.multiply(mag);
            g.multiply(mag);
            e.multiply(mag);
            s.add(gap);
            g.add(gap);
            s=c.getCoordInWCS(s, direc);
            g=c.getCoordInWCS(g, direc);
            e=c.getCoordInWCS(e, direc);
            return new LineInfo(layer,s,g,e,t);
    }
      
    private class JoistGroup implements CompareInterface{
        private final HSection[] j;
        private final HSection b;
        private final CoordXYZ[] jd;
        private final CoordXYZ bd;
        private final CoordXYZ p;
        private final JoistEnd[] jl;
        public JoistGroup(CoordXYZ p,HSection b,CoordXYZ bd,HSection[] Joists,CoordXYZ[] joist_d){
            this.b=b;
            this.p=p;
            this.j=Joists;
            this.jd=joist_d;
            this.bd=bd;
            ArrayList<JoistEnd> jl1=new ArrayList<JoistEnd>();
            for (int i = 0; i < j.length; i++) {
                jl1.add(new JoistEnd(j[i],jd[i]));
            }
            this.jl=(JoistEnd[])jl1.toArray(new JoistEnd[0]);
        }
        public HSection getMainBeam(){return b;}
        public CoordXYZ getMainBeamDirection(){return bd;}
        public HSection[] getJoist(){return j;}
        public CoordXYZ getJoistPoint(){return p;}
        public CoordXYZ[] getJoistDirection(){return jd;}
        public JoistEnd[] getJoistEnd(){return jl;}
        public void print(){
            System.out.println("--------joistgroup---------------");
//            System.out.println("joist_group");
            System.out.println("接合点: "+p.getName());
            System.out.println("接合元: "+b.getName()+"\t"+bd.getName());
            System.out.println("接合部材");
            for (int i = 0; i < j.length; i++) {
                System.out.println(j[i].getName()+"\t"+jd[i].getName());
            }
            System.out.println(b);
        }
        public boolean compare(CompareInterface jg1){
            JoistGroup jg=(JoistGroup)jg1;
            if(b.compare(jg.getMainBeam())&&bd.compare(jg.getMainBeamDirection())){}
            else return false;
            ArrayList<JoistEnd> jl2=new ArrayList<>();
            for (int i = 0; i < j.length; i++) {
                jl2.add(new JoistEnd(j[i],jd[i]));
            }
            
            
            JoistEnd[] jl3=jg.getJoistEnd();
            if((jl2.isEmpty())&&(jl3.length==0))return true;
            for (int i = 0; i < jl3.length; i++) {
                JoistEnd jl31 = jl3[i];
                for (int k = 0; k < jl2.size(); k++) {
                    boolean flag1=jl2.get(k).compare(jl31);
                    if(flag1){
                        jl2.remove(k);
                        break;
                    }
                }
            }
            if(jl2.isEmpty())return true;
            else return false;
        }
    }
    
    private class JoistEnd{
        private final HSection sec;
        private final CoordXYZ d;
        public JoistEnd(HSection sec,CoordXYZ d){
            this.sec=sec;
            this.d=d;
        }
        public HSection getSection(){return sec;}
        public CoordXYZ getDirection(){return d;}
        public boolean compare(JoistEnd j){
            boolean flag1=sec.compare(j.getSection());
            boolean flag2=d.compare(j.getDirection());
            if(flag1&&flag2)return true;
            else return false;
        }
    }
    
}
