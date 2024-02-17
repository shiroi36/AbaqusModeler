/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator;
import JointCreator.generator.Generator;
import JointCreator.generator.SHGenerator;
import JointCreator.generator.ColumnGenerator;
import JointCreator.generator.CFRGenerator;
import JointCreator.generator.KPGenerator;
import JointCreator.generator.CWRGenerator;
import JointCreator.generator.BeamGenerator;
import JointCreator.ID.CWRID;
import JointCreator.ID.KPID;
import JointCreator.ID.SHID;
import JointCreator.ID.CFRID;
import JointCreator.ID.CID;
import JointCreator.ID.BID;
import JointCreator.ID.CTPID;
import JointCreator.ID.TPID;
import JointCreator.generator.CTPGenerator;
import JointCreator.generator.TPGenerator;
import java.io.File;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;

/**
 *
 * @author keita
 */
public final class JointComposer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    }
    private final boolean debug;
    private final boolean debug2;
    private static String path;
    private int hundlenum;
    private String kp;
    private String c;
    private String b;
    private String cfr;
    private String cwr;
    private String sh;
    private String ipath;
    private CID cid;
    private BID bid;
    private CFRID cfrid;
    private CWRID cwrid;
    private CTPID ctpid;
    private SHID shid;
    private KPID aid;
    private final WCSJointID jid;
    private final int n;
    private final JointState js;
    private final TPID tpid;
    
    
    public JointComposer(String outputpath,WCSJointID jid,JointState js) {
        if (outputpath != "") {
            this.path=outputpath;
        }
        debug=false;
        debug2=false;
        hundlenum=2000;//ハンドル番号、おそらく一個一個の要素につけていかないといけない気がする
        kp=KPID.ICON_KP;
        c=CID.ICON_C;
        b=BID.ICON_B;
        cfr=CFRID.ICON_CFR;
        cwr=CWRID.ICON_CWR;
        sh=SHID.ICON_SH;
        this.jid=jid;
        this.js=js;
        
        aid=jid.getKPID();
        cid=jid.getCID();
        bid=jid.getBID();
        cfrid=jid.getCFRID();
        cwrid=jid.getCWRID();
        shid=jid.getSHID();
        ctpid=jid.getCTPID();
        tpid=jid.getTPID();
//        tpid=jid.get
        ipath=jid.getIconPath();
        this.n=12;
    }
    
    public JointComposer(String outputpath,WCSJointID jid) {
        this(outputpath,jid,JointState.INTERMEDIATE_X);
    }
    public JointComposer(WCSJointID jid) {
        this("",jid,JointState.INTERMEDIATE_X);
    }
    
    public void setOutputPath(String outputpath){
            this.path=outputpath;
    }
    
    public void createPartFile(){
        Generator[] gene={
            new KPGenerator(path,aid),
            new BeamGenerator(path,bid),
            new ColumnGenerator(path,cid,bid,ctpid,js),
            new CWRGenerator(path,cwrid),
            new CFRGenerator(path,cfrid),
            new SHGenerator(path,shid),
            new CTPGenerator(path,ctpid),
            new TPGenerator(path,tpid)
        };
        String[] icon={
            kp,
            b,
            c,
            cwr,
            cfr,
            sh
        };
        
        for (int i = 0; i < gene.length; i++) {
            Generator gene1 = gene[i];
            gene1.draw();
        }
        
    }
    
    public void assemble(){
        double dk=bid.getSection().getH()+aid.gettk1()*2+aid.gethk();
        DXFDrawer dxf=new DXFDrawer(path+"j00.dxf");
        dxf.setHeader(jid.getLayerNameArray(), jid.getLayerColorArray(), jid.getLayerLineArray());
        
        
        dxf.Move3D(path+CID.ICON_C+"_p.dxf", c,
                new CoordXYZ(0, 0, cid.getLength()/2),
                new CoordXYZ(1,1,-1)
        );
        
        
        CoordXYZ[] direc;
        if (js.isRF()) {//最上階のパーツを描画するかどうか?
            direc =new CoordXYZ[] { new CoordXYZ(1, -1, -1),new CoordXYZ(1, 1, -1)};
            dxf.Move3D(path + "ctp.dxf", c,
                    new CoordXYZ(0,0,bid.getSection().getH()/2),
                    new CoordXYZ(1, 1, 1)
            );
            dxf.Move3D(path + "tp.dxf", c,
                    new CoordXYZ(0,0,bid.getSection().getH()/2+tpid.getThickness()),
                    new CoordXYZ(1, 1, 1)
            );
        }else{
            direc = new CoordXYZ[]{
                new CoordXYZ(1, 1, 1), new CoordXYZ(1, 1, -1),
                new CoordXYZ(1, -1, 1), new CoordXYZ(1, -1, -1),};
            dxf.Move3D(path + "cw_hole.dxf", c,
                    new CoordXYZ(cwrid.getLcfw() / 2 - aid.getWingPlateLength(), -cid.getSection().getWebThickness() * 0.5, dk / 2),
                    new CoordXYZ(1, 1, 1)
            );
            dxf.Move3D(path + "cwr_p.dxf", cwr, new CoordXYZ(0, cid.getSection().getWebThickness() / 2, dk / 2), new CoordXYZ(1, 1, 1));
            dxf.Move3D(path + "cwr_p.dxf", cwr, new CoordXYZ(0, cid.getSection().getWebThickness() / 2, dk / 2), new CoordXYZ(1, -1, 1));
            dxf.Move3D(path + "cf_hole.dxf", c, new CoordXYZ(cid.getSection().getH() / 2, 0, bid.getSection().getH() / 2 + 75), new CoordXYZ(1, 1, 1));
            dxf.Move3D(path + "cfr_p.dxf", cfr, new CoordXYZ(cid.getSection().getH() / 2, 0, bid.getSection().getH() / 2 + 75), new CoordXYZ(1, 1, 1));
        }
        for (int i = 0; i < direc.length; i++) {
            CoordXYZ c1 = direc[i];
            dxf.Move3D(path + "kpl_p.dxf", kp, new CoordXYZ(cwrid.getLcfw() / 2 - aid.getWingPlateLength(),
                    cid.getSection().getWebThickness() / 2 + cwrid.getThickness(), dk / 2), c1);
        }
        
        //梁フランジのボルト穴の描画。これはあRF如何に関わらず、上下は同じ位置に開ける。
        double y=cid.getSection().getWebThickness()/2+cwrid.getThickness();
        y+=aid.getgk()-10;
        direc = new CoordXYZ[]{
            new CoordXYZ(1, 1, 1), new CoordXYZ(1, 1, -1),
            new CoordXYZ(1, -1, 1), new CoordXYZ(1, -1, -1),};
        for (int i = 0; i < direc.length; i++) {
            CoordXYZ c1 = direc[i];
            dxf.Move3D(path + "bf_hole.dxf", b, new CoordXYZ(
                    cwrid.getLcfw() / 2 + 70, y, bid.getSection().getH() / 2), c1);
        }
        
        dxf.Move3D(path+"cw_hole.dxf", c, 
                new CoordXYZ(cwrid.getLcfw()/2-aid.getWingPlateLength(), -cid.getSection().getWebThickness()*0.5, dk/2),
                new CoordXYZ(1,1,-1)
                );
        dxf.Move3D(path+"b_p.dxf", b, new CoordXYZ(cid.getSection().getH()/2+20,0,0), new CoordXYZ(1,1,1));
        
        dxf.Move3D(path+"cwr_p.dxf", cwr, new CoordXYZ(0,cid.getSection().getWebThickness()/2,-dk/2), new CoordXYZ(1,1,1));
        dxf.Move3D(path+"cwr_p.dxf", cwr, new CoordXYZ(0,cid.getSection().getWebThickness()/2,-dk/2), new CoordXYZ(1,-1,1));
        
        dxf.Move3D(path+"cf_hole.dxf", c, new CoordXYZ(cid.getSection().getH()/2,0,bid.getSection().getH()/2+75), new CoordXYZ(1,1,-1));
        dxf.Move3D(path+"cfr_p.dxf", cfr, new CoordXYZ(cid.getSection().getH()/2,0,bid.getSection().getH()/2+75), new CoordXYZ(1,1,-1));
        dxf.Move3D(path+"sh_p.dxf", sh, new CoordXYZ(shid.getWidth()/2+cid.getSection().getH()/2,-bid.getSection().getWebThickness()/2-shid.getThickness(),0), new CoordXYZ(1,1,1));
        dxf.Move3D(path+"bw_hole.dxf", b, new CoordXYZ(shid.getWidth()/2+cid.getSection().getH()/2,-bid.getSection().getWebThickness()/2,0), new CoordXYZ(1,1,1));
        
        
        

        if (js.isXShape()) {
            if (js.isRF()) {//最上階のパーツを描画するかどうか?
                direc = new CoordXYZ[]{ new CoordXYZ(-1, -1, -1),new CoordXYZ(-1, 1, -1)};
            }else{
                direc = new CoordXYZ[]{
                    new CoordXYZ(-1, 1, 1), new CoordXYZ(-1, 1, -1),
                    new CoordXYZ(-1, -1, 1), new CoordXYZ(-1, -1, -1),};
                dxf.Move3D(path + "cw_hole.dxf", c,
                        new CoordXYZ(cwrid.getLcfw() / 2 - aid.getWingPlateLength(), -cid.getSection().getWebThickness() * 0.5, dk / 2),
                        new CoordXYZ(-1, 1, 1)
                );
                dxf.Move3D(path + "cf_hole.dxf", c, new CoordXYZ(cid.getSection().getH() / 2, 0, bid.getSection().getH() / 2 + 75), new CoordXYZ(-1, 1, 1));
                dxf.Move3D(path + "cfr_p.dxf", cfr, new CoordXYZ(cid.getSection().getH() / 2, 0, bid.getSection().getH() / 2 + 75), new CoordXYZ(-1, 1, 1));
            }
            for (int i = 0; i < direc.length; i++) {
                CoordXYZ c1 = direc[i];
                dxf.Move3D(path + "kpl_p.dxf", kp, new CoordXYZ(cwrid.getLcfw() / 2 - aid.getWingPlateLength(),
                        cid.getSection().getWebThickness() / 2 + cwrid.getThickness(), dk / 2), c1);
            }
            dxf.Move3D(path + "cw_hole.dxf", c,
                    new CoordXYZ(cwrid.getLcfw() / 2 - aid.getWingPlateLength(), -cid.getSection().getWebThickness() * 0.5, dk / 2),
                    new CoordXYZ(-1, 1, -1)
            );
            dxf.Move3D(path + "b_p.dxf", b, new CoordXYZ(cid.getSection().getH() / 2 + 20, 0, 0), new CoordXYZ(-1, 1, 1));
            dxf.Move3D(path + "cf_hole.dxf", c, new CoordXYZ(cid.getSection().getH() / 2, 0, bid.getSection().getH() / 2 + 75), new CoordXYZ(-1, 1, -1));
            dxf.Move3D(path + "cfr_p.dxf", cfr, new CoordXYZ(cid.getSection().getH() / 2, 0, bid.getSection().getH() / 2 + 75), new CoordXYZ(-1, 1, -1));
            dxf.Move3D(path + "sh_p.dxf", sh, new CoordXYZ(shid.getWidth() / 2 + cid.getSection().getH() / 2, -bid.getSection().getWebThickness() / 2 - shid.getThickness(), 0), new CoordXYZ(-1, 1, 1));
            dxf.Move3D(path + "bw_hole.dxf", b, new CoordXYZ(shid.getWidth() / 2 + cid.getSection().getH() / 2, -bid.getSection().getWebThickness() / 2, 0), new CoordXYZ(-1, 1, 1));

            direc = new CoordXYZ[]{
                new CoordXYZ(-1, 1, 1), new CoordXYZ(-1, 1, -1),
                new CoordXYZ(-1, -1, 1), new CoordXYZ(-1, -1, -1),};
            for (int i = 0; i < direc.length; i++) {
                CoordXYZ c1 = direc[i];
                dxf.Move3D(path + "bf_hole.dxf", b, new CoordXYZ(
                        cwrid.getLcfw() / 2 + 70, y, bid.getSection().getH() / 2), c1);
            }

        }
        
        dxf.setEnd();
        dxf.Finish();
        
        String[] delete={
            path+"b_p.dxf",
            path+"bf_hole.dxf",
            path+"bw_hole.dxf",
            path+"c_p.dxf",
            path+"cf_hole.dxf",
            path+"cfr_p.dxf",
            path+"cw_hole.dxf",
            path+"cwr_p.dxf",
            path+"kpl_p.dxf",
            path+"kpr_p.dxf",
            path+"sh_p.dxf",
        };
        for (String delete1 : delete) {
            File file = new File(delete1);
            if (file.exists()) file.delete();
        }
        
    }
    
    public void prepare2(){
        double bk=cid.getSection().getWebThickness()+cwrid.getThickness()*2+aid.gettk1();
        
        DXFDrawer dxf=new DXFDrawer(path+"cf_hole0.dxf");
        dxf.setHeader();
        double[] a={1,-1};
        double p=bk;
        double phi=aid.gettk1()+3;
        
        for (int i = 0; i < a.length; i++) {
            double a1 = a[i];
            dxf.drawLine3D("0", new CoordXYZ(a1*(p/2+phi/2), (75), 0),
                    new CoordXYZ(a1*(p/2+phi/2), (-75),0));
            dxf.drawLine3D("0", new CoordXYZ(a1*(p/2-phi/2), (75),0),
                    new CoordXYZ(a1*(p/2-phi/2), (-75),0));
            
        }
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        dxf.drawArc3D("0",new CoordXYZ( p / 2, 75,0),phi/2,0, 180,8,
                direc, cid.getSection().getFlangeThickness());
        dxf.drawArc3D("0",new CoordXYZ( -p / 2, 75,0),phi/2,0, 180,8,
                direc, cid.getSection().getFlangeThickness());
        dxf.drawArc3D("0",new CoordXYZ( p / 2, -75,0),phi/2,-180, 0,8,
                direc, cid.getSection().getFlangeThickness());
        dxf.drawArc3D("0",new CoordXYZ( -p / 2, -75,0),phi/2,-180, 0,8,
                direc, cid.getSection().getFlangeThickness());
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+"cf_hole1.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+"cf_hole0.dxf", "0", new CoordXYZ(0,0,1), 90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+"cf_hole.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+"cf_hole1.dxf", "0", new CoordXYZ(0,1,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        
        dxf=new DXFDrawer(path+"cw_hole0.dxf");
        dxf.setHeader();
        dxf.drawCircle3D("0", new CoordXYZ(40, 30, 0), aid.getD()/2
                ,direc,cid.getSection().getWebThickness());
        dxf.drawCircle3D("0", new CoordXYZ(40, -30, 0), aid.getD()/2,
                direc,cid.getSection().getWebThickness());
        dxf.drawCircle3D("0", new CoordXYZ(100, 30, 0), aid.getD()/2
                ,direc,cid.getSection().getWebThickness());
        dxf.drawCircle3D("0", new CoordXYZ(100, -30, 0), aid.getD()/2
                ,direc,cid.getSection().getWebThickness());
        if (aid.getMode() == KPID.MODE_6) {
            dxf.drawCircle3D("0", new CoordXYZ(160, 30, 0), aid.getD()/2
                    ,direc,cid.getSection().getWebThickness());
            dxf.drawCircle3D("0", new CoordXYZ(160, -30, 0), aid.getD()/2
                    ,direc,cid.getSection().getWebThickness());
        }
        dxf.setEnd();
        dxf.Finish();
                
        dxf=new DXFDrawer(path+"cw_hole.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+"cw_hole0.dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+"bf_hole.dxf");
        dxf.setHeader();
        dxf.drawCircle3D("0", new CoordXYZ(40, 0, 0), aid.getD()/2,
                direc,bid.getSection().getFlangeThickness());
        dxf.drawCircle3D("0", new CoordXYZ(100, 0, 0), aid.getD()/2,
                direc,bid.getSection().getFlangeThickness());
        dxf.drawCircle3D("0", new CoordXYZ(160, 0, 0), aid.getD()/2,
                direc,bid.getSection().getFlangeThickness());
        dxf.drawCircle3D("0", new CoordXYZ(220, 0, 0), aid.getD()/2,
                direc,bid.getSection().getFlangeThickness());
        if (aid.getMode() == KPID.MODE_6) {
            dxf.drawCircle3D("0", new CoordXYZ(280, 0, 0), aid.getD()/2,
                direc,bid.getSection().getFlangeThickness());
            dxf.drawCircle3D("0", new CoordXYZ(340, 0, 0), aid.getD()/2,
                direc,bid.getSection().getFlangeThickness());
        }
        dxf.setEnd();
        dxf.Finish();
        
        
        double boltnum=shid.getBoltnum();
        double pitch=shid.getBoltPitch();
        double t=shid.getThickness();
        double bolthole=shid.getBoltType();
        double h=(boltnum/2-1)*pitch+80;//シアプレート高さ
        double b=160;//シアプレート幅
        
        
        dxf=new DXFDrawer(path+"bw_hole0.dxf");
        dxf.setHeader();
        phi=bolthole+2;
        for (int i = 0; i < boltnum/2; i++) {
            double y=h/2-40-i*pitch;
//            double x=30;
            dxf.drawCircle3D("0", new CoordXYZ(40, y, 0), phi / 2,
                    direc,bid.getSection().getWebThickness());
            dxf.drawCircle3D("0", new CoordXYZ(-20, y, 0), phi / 2,
                    direc,bid.getSection().getWebThickness());
        }
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+"bw_hole.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+"bw_hole0.dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        //不要なファイルを削除
        String[] delete={
            path+"cf_hole0.dxf",
            path+"cf_hole1.dxf",
            path+"cw_hole0.dxf",
            path+"bf_hole0.dxf",
            path+"bw_hole0.dxf",
        };
        for (String delete1 : delete) {
            File file = new File(delete1);
            if (file.exists()) file.delete();
        }
        
    }
    
    public void Prepare(){
        DXFDrawer dxf=new DXFDrawer(path+c+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+c+".dxf", "0", new CoordXYZ(0,0,1), -90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+b+"0.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+b+".dxf", "0", new CoordXYZ(0,1,0), -90);
        dxf.setEnd();
        dxf.Finish();
        dxf=new DXFDrawer(path+b+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+b+"0.dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+cfr+"0.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+cfr+".dxf", "0", new CoordXYZ(0,0,1), 90);
        dxf.setEnd();
        dxf.Finish();
        dxf=new DXFDrawer(path+cfr+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+cfr+"0.dxf", "0", new CoordXYZ(0,1,0), -90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+cwr+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+cwr+".dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+sh+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+sh+".dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        
        dxf=new DXFDrawer(path+KPID.ICON_KPL+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+KPID.ICON_KPL+".dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        dxf=new DXFDrawer(path+KPID.ICON_KPR+"_p.dxf");
        dxf.setHeader();
        dxf.Rotate3D(path+KPID.ICON_KPR+".dxf", "0", new CoordXYZ(1,0,0), 90);
        dxf.setEnd();
        dxf.Finish();
        
        //不要なファイルを削除
        String[] delete={
            path+cfr+"0.dxf",
            path+b+"0.dxf",
        };
        for (String delete1 : delete) {
            File file = new File(delete1);
            if (file.exists()) file.delete();
        }
        
        
    }
    
    
}
