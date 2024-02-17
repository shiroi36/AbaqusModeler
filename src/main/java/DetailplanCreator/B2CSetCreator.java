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
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.SQL_OPE;
import util.matrix.Calc;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class B2CSetCreator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        Calc c=new Calc();
//        HSection sec=new HSection(400,200,12,22);
//        LineInfo[] li=sec.get2DShape(4);
//        double l=2000;
//        CoordXYZ direc=new CoordXYZ(1,1,0);
//        
//        
//        DXFDrawer dxf=new DXFDrawer("dxf/test.dxf");
//        dxf.setHeader();
//        for (int i = 0; i < li.length; i++) {
//            LineInfo li1 = li[i];
//            CoordXYZ s=c.getCoordInWCS(li1.getStartPoint(), direc);
//            CoordXYZ e=c.getCoordInWCS(li1.getEndPoint(), direc);
//            dxf.drawLine3D("0", s, e, direc, l);
//        }
//        dxf.setEnd();
//        dxf.Finish();
        B2CSetCreator b2c=new B2CSetCreator(
                "D:/Dropbox/Dropbox (SSLUoT)/00_16_webclamp/080_駐車場開発/00_埼玉駐車場/detailplan/jointset/",
                new SQL_OPE(PATH.DB_DROPBOX_DB+"webclamp_stm/test;ifexists=true","junapp","")
        );
        b2c.Draw();
        b2c.drawJointList();
    }
    private final SQL_OPE sql;
    private final double lb=1800;//仕口の水平考慮長さ。これ以上の幅をもつ仕口は、ここを変更する必要あり
    private final double lc=1800;//仕口の鉛直考慮長さ。これ以上の幅をもつ仕口は、ここを変更する必要あり
    private final double cr=20;//柱梁仕口のクリアランス 
    private String[][] b2cval;
    private final String[] filenames;
    private double[] hariten;
    
    private final String[] cname;
    private final String[] direc;
    private final HSection[] csec;
    private final CoordXYZ co;
    
    private final String[][] bend;
    private final HSection[][] bsec;
    private final CoordXYZ[][] bdirec;
    private final Calc c;
    private final String[] csecid;
    private final String[][] bsecid;
    private final String[] jid;
    private final String listpath;
    private final DataRecorder[] dr;
    
    public B2CSetCreator(String path,SQL_OPE sql){
        this.sql=sql;
        this.b2cval=sql.getQueryDataString(
                "select id,jointid,csec,state from DETAILPLAN.B2CLIST order by id;");
        b2cval=sql.transpose(b2cval);
        filenames=new String[b2cval.length];
        this.dr=new DataRecorder[b2cval.length];
        for (int i = 0; i < filenames.length; i++) {
            filenames[i]=path+"b2c_"+b2cval[i][0];
            dr[i]=new DataRecorder();
        }
        this.listpath=path+"jointlist.dxf";
        //柱の情報を管理
        this.c=new Calc();
        
        this.cname=new String[b2cval.length];
        this.jid=new String[b2cval.length];
        this.csecid=new String[b2cval.length];
        direc=new String[b2cval.length];
        this.csec=new HSection[b2cval.length];
        this.co=new CoordXYZ(0,0,-lc/2);
        //梁の情報を管理
        this.bend =new String[b2cval.length][];
        this.bsecid =new String[b2cval.length][];
        this.bsec=new HSection[b2cval.length][];
        this.bdirec=new CoordXYZ[b2cval.length][];
        for (int i = 0; i < b2cval.length; i++) {
//            String bcid = b2cval[i][0];
            String jid2 = b2cval[i][1];
            String csecid2 = b2cval[i][2];
            sql.executeUpdate("set @jid="+jid2);
            sql.executeUpdate("set @csec="+csecid2);
            
            //柱情報
            String[] val2 = sql.transpose(sql.getQueryDataString(
                    "select name,direc,size0,size1,size2,size3,size4 "
                            + "from detailplan.SECT where id=@csec;"))[0];
            jid[i]=jid2;
            csecid[i]=csecid2;
            cname[i]=val2[0];
            direc[i]=val2[1];
            csec[i]=new HSection(
                    Double.parseDouble(val2[2]),
                    Double.parseDouble(val2[3]),
                    Double.parseDouble(val2[4]),
                    Double.parseDouble(val2[5]),
                    Double.parseDouble(val2[6])
            );
            //梁情報
            String[][] val3 = sql.transpose(sql.getQueryDataString("select \n"
                    + "a.end,a.dx,a.dy,a.dz,\n"
                    + "b.SIZE0,b.size1,b.size2,b.size3,b.SIZE4,b.id  \n"
                    + "from detailplan.JOINTLIST a, detailplan.SECT b \n"
                    + "where a.\"SECTION\"=b.ID and a.id=@jid order by a.ROWNUMBER;"));
            bend[i] = new String[val3.length];
            bsec[i] = new HSection[val3.length];
            bdirec[i] = new CoordXYZ[val3.length];
            bsecid[i] = new String[val3.length];
            for (int j = 0; j < val3.length; j++) {
                String[] val31 = val3[j];
                bend[i][j]=val31[0];
                bdirec[i][j]=new CoordXYZ(
                        Double.parseDouble(val31[1]),
                        Double.parseDouble(val31[2]),
                        Double.parseDouble(val31[3])
                );
                bsec[i][j]=new HSection(
                        Double.parseDouble(val31[4]),
                        Double.parseDouble(val31[5]),
                        Double.parseDouble(val31[6]),
                        Double.parseDouble(val31[7]),
                        Double.parseDouble(val31[8])
                );
                bsecid[i][j]=val31[9];
            }
            
            
        }
        
        //梁天端の計算
//        System.out.println("--------------------梁天端");
        this.hariten=new double[b2cval.length];
        for (int i = 0; i < b2cval.length; i++) {
            for (int j = 0; j < bsec[i].length; j++) {
                HSection bsec1 = bsec[i][j];
//                System.out.println("\t"+bsec1.getH());
                if(hariten[i]<bsec1.getH()/2)hariten[i]=bsec1.getH()/2;
            }
//            System.out.println(hariten[i]);
        }
        
    }
    
    public void setDB(){
        sql.executeUpdate("drop table if exists detailplan.b2crecord;");
        sql.executeUpdate("create table if not exists detailplan.b2crecord "
                + "( rownumber int identity(1,1), "
                + " b2cid int,"
                + " type varchar,"
                + " content varchar "
                + ");");
    }
    
    public void drawJointList(){
        double a=2200;
        DXFDrawer dxf=new DXFDrawer(listpath);
        dxf.setHeader();
        int num1=5;
        int num2=filenames.length/num1;
        int i2=0;
        
        for (int j = 0; j < num2; j++) {
            double x=5.5*a*j;
            for (int i = 0; i < num1; i++) {
                i2++;
                if (i2 == filenames.length) {
                    break;
                }
                String filename = filenames[i2] + ".dxf";
                String text = "B2CID:" + b2cval[i2][0];
                String[] val = dr[i2].getRecord();

                dxf.drawTEXT3D("text", new CoordXYZ(-a+x, -a * i, 0), text, new CoordXYZ(0, 0, 1), 300);
                dxf.Rotate3D(filename, new CoordXYZ[]{new CoordXYZ(1, 0, 0)}, new double[]{0}, new CoordXYZ(0+x, -a * i, 0));
                dxf.Rotate3D(filename, new CoordXYZ[]{new CoordXYZ(1, 0, 0)}, new double[]{-90}, new CoordXYZ(a+x, -a * i, 0));
                dxf.Rotate3D(filename,
                        new CoordXYZ[]{new CoordXYZ(1, 0, 0), new CoordXYZ(0, 1, 0)},
                        new double[]{-90, 90}, new CoordXYZ(2 * a+x, -a * i, 0));
                for (int k = 0; k < val.length; k++) {
                    String val1 = val[k];
                    dxf.drawTEXT3D("text", new CoordXYZ(3 * a+x, (0.5 - i) * a - 100 * (k + 3), 0), val1, new CoordXYZ(0, 0, 1), 50);

                }
                dxf.drawLine3D("text", new CoordXYZ(-1.5 * a+x, -a * i - a / 2, 0), new CoordXYZ(3.5 * a+x, -a * i - a / 2, 0));

            }
        }
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    public void Draw(){
        this.setDB();
        for (int i = 0; i < b2cval.length; i++) {
//            String[] b2cval1 = b2cval[i];
            DXFDrawer dxf=new DXFDrawer(filenames[i]+".dxf");
            dxf.setHeader();
            this.setB2CSetBase(dxf, i);
            this.addWCS(dxf, i);
            dxf.setEnd();
            dxf.Finish();
        }
    }
    
    
    private void addWCS(DXFDrawer dxf,int i){
        
        HSection col = csec[i];
        String cid = csecid[i];
        double hariten2=hariten[i];
        sql.executeUpdate("set @cid=" + cid);
        String[] val = sql.transpose(sql.getQueryDataString("select tcfr,tcwr "
                + "from DETAILPLAN.WCSCOLUMN where section=@cid;"))[0];
        double tcfr = Double.parseDouble(val[0]);
        double tcwr = Double.parseDouble(val[1]);
        String state=b2cval[i][3];

        for (int j = 0; j < bsec[i].length; j++) {
            HSection b = bsec[i][j];
            String bid = bsecid[i][j];
            CoordXYZ d = bdirec[i][j];
            sql.executeUpdate("set @bid=" + bid);
            if ("p".equals(bend[i][j].toLowerCase())) {
                String val2 = sql.getQueryDataString(""
                        + "select gasset "
                        + "from DETAILPLAN.colgasset where section=@bid;")[0][0];
                CGID cgid=new CGID(val2,col);
                this.drawColumnGasseetPlate(dxf, d, cgid, col, b, hariten2);
                dr[i].setRecord(j + ": "+cgid.getName());
            } else if ("r".equals(bend[i][j].toLowerCase())) {
//                System.out.println(bid);
                String[] val2 = sql.transpose(sql.getQueryDataString(""
                        + "select kp,crealance,sp "
                        + "from DETAILPLAN.WCSBEAM where section=@bid;"))[0];
                String kp = val2[0];
                String sp = val2[2];
                double clear = Double.parseDouble(val2[1]);
                KPID kpid = this.drawWCSAttachment(dxf, d, col, tcwr, b, kp, clear, hariten2, state);
                this.drawAttachmentBoltHole(dxf, d, col, tcwr, b, kpid, clear, hariten2, state);
                SHID shid = new SHID(sp);
                this.drawShearPlate(dxf, d, shid, col, b, hariten2);
                this.drawColumnPlate(dxf, d, kpid, col, tcwr, tcfr, b, clear, hariten2, state);
                dr[i].setRecord(j + ": " + kp);
                dr[i].setRecord(j + ": " + shid.getName());
                dr[i].setRecord(j + ": CWR" + tcwr + "mm");
                dr[i].setRecord(j + ": CFR" + tcfr + "mm");
            }
        }


    }
    
    private void drawColumnGasseetPlate(DXFDrawer dxf, CoordXYZ d,CGID cgid,HSection col,HSection b, double hariten){
        double gap=hariten-b.getH()/2;
        CoordXYZ move2=new CoordXYZ(0,gap,0);//部材座標系の時点でgap分を補正する。
        
        CGGenerator shg=new CGGenerator(cgid);
        double dy=d.getY();
        double dx=d.getX();
        CoordXYZ move;
        if((dy>=0&&dx>=0)||(dx>0&&dy<0&&dy/dx<1.0)||(dx<0&&dy>0&&dy/dx>1.0)){
            move=new CoordXYZ(-b.getWebThickness()/2,0,col.getB()/2+20);
        }else{
            move=new CoordXYZ(b.getWebThickness()/2+cgid.getThickness(),0,col.getB()/2+20);
        }
        LineInfo[] li=shg.getLineInfo();
        CircleInfo[] ci=shg.getCircleInfo();
        for (int j = 0; j < li.length; j++) {
            LineInfo li1 = this.getTransformedLine(li[j], 
                    cgid.getName(), move, d, new CoordXYZ(1,1,1), move2);
            dxf.drawLine3D(li1);
        }
        for (int j = 0; j < ci.length; j++) {
            CircleInfo ci1 = this.getTransformedCircle(ci[j], 
                    cgid.getName(), move, d, new CoordXYZ(1,1,1), move2);
            dxf.drawCircle3D(ci1);
        }
        move=new CoordXYZ(b.getWebThickness()/2,0,col.getB()/2+20);
        for (int j = 0; j < ci.length; j++) {
            CircleInfo ci1 = this.getTransformedCircle(ci[j], 
                    cgid.getName(), move, d, new CoordXYZ(1,1,1), move2);
            dxf.drawCircle3D("bhole",ci1.getCenterPoint(),ci1.getRadius(),ci1.getExtrudeDirection(),b.getWebThickness());
        }
    }
    
    
    private void drawColumnPlate(DXFDrawer dxf,CoordXYZ d,KPID kpid,HSection col,double tcwr,double tcfr,HSection b,double clear,double hariten,String state){
        double gap=hariten-b.getH()/2;
        CoordXYZ move2=new CoordXYZ(0,gap,0);//部材座標系の時点でgap分を補正する。
        double w=col.getH()-50*2+clear*2;
        double h=140;
        double t=tcwr;
        CoordXYZ p0=new CoordXYZ(0,h/2,w/2);
        CoordXYZ p1=new CoordXYZ(0,-h/2,w/2);
        CoordXYZ p2=new CoordXYZ(0,h/2,-w/2);
        CoordXYZ p3=new CoordXYZ(0,-h/2,-w/2);
        CoordXYZ e=new CoordXYZ(-1,0,0);
        double dk=b.getH()+kpid.gethk()+kpid.gettk2()*2;
        double a=tcwr*2+col.getWebThickness();
        CoordXYZ move=new CoordXYZ(-col.getWebThickness()/2,dk/2,0);
        LineInfo[] li=new LineInfo[]{
            new LineInfo(p0,p1,e,t),
            new LineInfo(p2,p3,e,t),
            new LineInfo(p0,p2,e,t),
            new LineInfo(p1,p3,e,t),
        };
        CoordXYZ[] mags=new CoordXYZ[]{
            new CoordXYZ(1,1,1),
            new CoordXYZ(-1,1,1),
            new CoordXYZ(1,-1,1),
            new CoordXYZ(-1,-1,1),
        };
        if("rf".equals(state.toLowerCase()))
            mags=new CoordXYZ[]{
//            new CoordXYZ(1,1,1),
//            new CoordXYZ(-1,1,1),
            new CoordXYZ(1,-1,1),
            new CoordXYZ(-1,-1,1),
        };
        for (int i = 0; i < mags.length; i++) {
            CoordXYZ mag = mags[i];
            for (int j = 0; j < li.length; j++) {
//                LineInfo li1 = li[j];
                LineInfo li1 = this.getTransformedLine(li[j],
                        "cwr", move, d, mag, move2);
                dxf.drawLine3D(li1);
            }
        }
        
        //フランジ補強板の描画
        CFRID cfrid=new CFRID(
                CFRID.CFR400,
                9,
                col.getB()-17*2,
                kpid.gettk1()+17,
                kpid.gettk1()+2*tcwr+col.getWebThickness()   
        );
        CFRGenerator cfrg=new CFRGenerator(cfrid);
        li=cfrg.getLineInfo();
        ArcInfo[] ai=cfrg.getArcInfo();
        move=new CoordXYZ(0,(b.getH()+150)/2,col.getH()/2);
        for (int i = 0; i < mags.length; i++) {
            CoordXYZ mag = mags[i];
            for (int j = 0; j < li.length; j++) {
                LineInfo li1=this.getTransformedLine(li[j], "cfr", move, d,mag,move2);
                dxf.drawLine3D(li1);
            }
            for (int j = 0; j < ai.length; j++) {
                LineInfo[] li2=dxf.drawArc3D(ai[j]);
                for (int k = 0; k < li2.length; k++) {
                    LineInfo li3=this.getTransformedLine(li2[k], "cfr", move, d,mag,move2);
                    dxf.drawLine3D(li3);
                }
            }
        }
        
        //柱フランジ貫通孔の描画
        li=cfrg.getCFHoleLineInfo(col.getFlangeThickness());
        ai=cfrg.getCFHoleArcInfo(col.getFlangeThickness());
        move=new CoordXYZ(0,(b.getH()+150)/2,col.getH()/2-col.getFlangeThickness());
        for (int i = 0; i < mags.length; i++) {
            CoordXYZ mag = mags[i];
            for (int j = 0; j < li.length; j++) {
                LineInfo li1=this.getTransformedLine(li[j], "cfhole", move, d,mag,move2);
                dxf.drawLine3D(li1);
            }
            for (int j = 0; j < ai.length; j++) {
                LineInfo[] li2=dxf.drawArc3D(ai[j]);
                for (int k = 0; k < li2.length; k++) {
                    LineInfo li3=this.getTransformedLine(li2[k], "cfr", move, d,mag,move2);
                    dxf.drawLine3D(li3);
                }
            }
        }
        
    }
    
    
    private void drawShearPlate(DXFDrawer dxf, CoordXYZ d,SHID shid,HSection col,HSection b, double hariten){
        double gap=hariten-b.getH()/2;
        CoordXYZ move2=new CoordXYZ(0,gap,0);//部材座標系の時点でgap分を補正する。
        
        SHGenerator shg=new SHGenerator(shid);
        double dy=d.getY();
        double dx=d.getX();
        CoordXYZ move;
        if((dy>=0&&dx>=0)||(dx>0&&dy<0&&dy/dx<1.0)||(dx<0&&dy>0&&dy/dx>1.0)){
            move=new CoordXYZ(-b.getWebThickness()/2,0,col.getH()/2+20);
        }else{
            move=new CoordXYZ(b.getWebThickness()/2+shid.getThickness(),0,col.getH()/2+20);
        }
        LineInfo[] li=shg.getLineInfo();
        CircleInfo[] ci=shg.getCircleInfo();
        for (int j = 0; j < li.length; j++) {
            LineInfo li1 = this.getTransformedLine(li[j], 
                    shid.getName(), move, d, new CoordXYZ(1,1,1), move2);
            dxf.drawLine3D(li1);
        }
        for (int j = 0; j < ci.length; j++) {
            CircleInfo ci1 = this.getTransformedCircle(ci[j], 
                    shid.getName(), move, d, new CoordXYZ(1,1,1), move2);
            dxf.drawCircle3D(ci1);
        }
        move=new CoordXYZ(b.getWebThickness()/2,0,col.getH()/2+20);
        for (int j = 0; j < ci.length; j++) {
            CircleInfo ci1 = this.getTransformedCircle(ci[j], 
                    shid.getName(), move, d, new CoordXYZ(1,1,1), move2);
            dxf.drawCircle3D("bhole",ci1.getCenterPoint(),ci1.getRadius(),ci1.getExtrudeDirection(),b.getWebThickness());
        }
    }
    
    private KPID drawWCSAttachment(DXFDrawer dxf, CoordXYZ d,HSection col,double tcwr,HSection b, String kp,double clear,double hariten,String state){
        Calc c=new Calc();
        KPID[] kpset=KPID.KPSET;
        KPID kpid=null;
        for (int i = 0; i < kpset.length; i++) {
            KPID kpset1 = kpset[i];
            if(kp.toLowerCase().equals(kpset1.getAttachmentName().toLowerCase())){
                kpid=kpset1;
            }
        }
        double dk=b.getH()+kpid.gethk()+kpid.gettk2()*2;
        double a=tcwr*2+col.getWebThickness();
        double gap=hariten-b.getH()/2;
        CoordXYZ move2=new CoordXYZ(0,gap,0);//部材座標系の時点でgap分を補正する。
        CoordXYZ move=new CoordXYZ(-a/2,dk/2,col.getH()/2+20+clear);
        KPGenerator kpg=new KPGenerator(kpid);
        CoordXYZ[] mags=new CoordXYZ[]{
            new CoordXYZ(1,1,1),
            new CoordXYZ(-1,1,1),
            new CoordXYZ(1,-1,1),
            new CoordXYZ(-1,-1,1),
        };
        if("rf".equals(state.toLowerCase()))
            mags=new CoordXYZ[]{
//            new CoordXYZ(1,1,1),
//            new CoordXYZ(-1,1,1),
            new CoordXYZ(1,-1,1),
            new CoordXYZ(-1,-1,1),
        };
        LineInfo[] li = kpg.getLine();
        CircleInfo[] ci = kpg.getCircleInfo();
        for (int i = 0; i < mags.length; i++) {
            CoordXYZ mag = mags[i];
            for (int j = 0; j < li.length; j++) {
                LineInfo li1=this.getTransformedLine(li[j], kpid.getAttachmentName(), move, d,mag,move2);
                dxf.drawLine3D(li1);
            }
            for (int j = 0; j < ci.length; j++) {
                CircleInfo ci1 = this.getTransformedCircle(ci[j], kpid.getAttachmentName(), move, d,mag,move2);
                dxf.drawCircle3D(ci1);
            }
            
        }
        System.out.println(kpid.getAttachmentName());
        //金物の設定
        return kpid;
    }
    
    private void drawAttachmentBoltHole(DXFDrawer dxf, CoordXYZ d,HSection col,double tcwr,HSection b, KPID kp,double clear,double hariten,String state){
        double dk=b.getH()+kp.gethk()+kp.gettk2()*2;
        double a=tcwr*2+col.getWebThickness();
        KPGenerator kpg=new KPGenerator(kp);
        double gap=hariten-b.getH()/2;
        CoordXYZ move2=new CoordXYZ(0,gap,0);
        CoordXYZ move=new CoordXYZ(-a/2,dk/2,col.getH()/2+20+clear);
        CoordXYZ bpoffset=new CoordXYZ(0,-kp.gettk2(),0);
        CoordXYZ wpoffset=new CoordXYZ(tcwr,0,0);
        CoordXYZ wpoffset2=new CoordXYZ(tcwr+col.getWebThickness(),0,0);
        CircleInfo[] bp=kpg.getBasePlateBolt(b.getFlangeThickness());
        CircleInfo[] wp1=kpg.getWingPlateBolt(tcwr);
        CircleInfo[] wp2=kpg.getWingPlateBolt(col.getWebThickness());
        CoordXYZ[] mags=new CoordXYZ[]{
            new CoordXYZ(1,1,1),
            new CoordXYZ(-1,1,1),
            new CoordXYZ(1,-1,1),
            new CoordXYZ(-1,-1,1),
        };
        if("rf".equals(state.toLowerCase()))
            mags=new CoordXYZ[]{
//            new CoordXYZ(1,1,1),
//            new CoordXYZ(-1,1,1),
            new CoordXYZ(1,-1,1),
            new CoordXYZ(-1,-1,1),
        };
        for (int i = 0; i < mags.length; i++) {
            CoordXYZ mag = mags[i];
            for (int j = 0; j < bp.length; j++) {
                CoordXYZ o=new CoordXYZ(0,0,0);
                o.add(move);
                o.add(bpoffset);
                CircleInfo bp1 = this.getTransformedCircle(bp[j], "bhole", o, d, mag,move2);
                dxf.drawCircle3D(bp1);
            }
            for (int j = 0; j < wp1.length; j++) {
                CoordXYZ o=new CoordXYZ(0,0,0);
                o.add(wpoffset);
                o.add(move);
                CircleInfo wp11 = this.getTransformedCircle(wp1[j], "chole", o, d, mag,move2);
                dxf.drawCircle3D(wp11);
            }
        }
        for (int i = 0; i < wp2.length; i++) {
            CoordXYZ o = new CoordXYZ(0, 0, 0);
            o.add(wpoffset2);
            o.add(move);
            CircleInfo wp11 = this.getTransformedCircle(wp2[i], "chole", o, d, new CoordXYZ(1,-1,1),move2);
            dxf.drawCircle3D(wp11);
            if(!"rf".equals(state.toLowerCase()))
            wp11 = this.getTransformedCircle(wp2[i], "chole", o, d, new CoordXYZ(1,1,1),move2);
            dxf.drawCircle3D(wp11);
        }    
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
    
    private void setB2CSetBase(DXFDrawer dxf, int i) {
        //描画
//            System.out.println(filenames[i]);
        //柱の描画
        LineInfo[] csecli = csec[i].get2DShape(2);
        double a = 0;
        String state = b2cval[i][3];
        String b2cid = b2cval[i][0];
        dr[i].setRecord("CONNECTION_STATE: "+state.toUpperCase());
        dr[i].setRecord(csec[i].getName());
        if ("hkyou".equals(direc[i].toLowerCase())) {
            a = 90;
        }
        
        double lc1 = 0;
        if ("rf".equals(state.toLowerCase())) {
            lc1 = lc - lc / 2 + hariten[i];
        } else {
            lc1 = lc;
        }
        sql.executeUpdate("insert into "
                + "detailplan.b2crecord(b2cid,type,content)"
                + "values("
                + b2cid + ","
                + "'col_length',"
                + "'" + lc1 + "'"
                + ")");

        //柱の描画
        for (int j = 0; j < csecli.length; j++) {
            LineInfo csecli1 = csecli[j];
            CoordXYZ s1 = c.getRotate(csecli1.getStartPoint(), new CoordXYZ(0, 0, 1), a);
            CoordXYZ e1 = c.getRotate(csecli1.getEndPoint(), new CoordXYZ(0, 0, 1), a);
            s1 = c.getCoordInWCS(s1, new CoordXYZ(0, 0, 1));
            e1 = c.getCoordInWCS(e1, new CoordXYZ(0, 0, 1));
            s1.add(co);
            e1.add(co);
            dxf.drawLine3D("COL_" + csec[i].getName(), s1, e1, new CoordXYZ(0, 0, 1), lc1);
        }

        //梁の描画
        for (int j = 0; j < bsec[i].length; j++) {
            HSection bsec11 = bsec[i][j];
            String end11 = bend[i][j];
            CoordXYZ bdirec11 = bdirec[i][j];
            dr[i].setRecord(j+": "+bsec11.getName()+"("+ end11+ "): DIREC"+bdirec11.getName());

            CoordXYZ bo = new CoordXYZ(0, 0, 0);
            double lb1 = 0;
            double bo_y = hariten[i] - bsec11.getH() / 2;
            if ("p".equals(end11.toLowerCase())) {
                bo = new CoordXYZ(0, bo_y, csec[i].getB() / 2 + 20);
                lb1 = lb / 2 - (csec[i].getB() / 2 + 20);
            } else if ("r".equals(end11.toLowerCase())) {
                bo = new CoordXYZ(0, bo_y, csec[i].getH() / 2 + 20);
                lb1 = lb / 2 - (csec[i].getH() / 2 + 20);
            }
            LineInfo[] bsecli = bsec11.get2DShape(2);
            for (int k = 0; k < bsecli.length; k++) {
                LineInfo bsecli1 = bsecli[k];
                CoordXYZ s1 = bsecli1.getStartPoint();
                s1.add(bo);
                s1 = c.getCoordInWCS(s1, bdirec11);
                CoordXYZ e1 = bsecli1.getEndPoint();
                e1.add(bo);
                e1 = c.getCoordInWCS(e1, bdirec11);
//                dxf.drawLine3D("BEAM(" + end11 + ")_" + bsec11.getName(), s1, e1, bdirec11, lb1);
                dxf.drawLine3D("BEAM_" + bsec11.getName(), s1, e1, bdirec11, lb1);
            }

        }
        
    }

    private SHGenerator SHGenerator(SHID shid) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
