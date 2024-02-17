/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.coltest.model;


import AbaqusModeler.Modeler.SolidColModeler2;
import AbaqusModeler.bolthole;
import JointCreator.ID.KPID;
import JointCreator.JointState;
import java.io.BufferedReader;
import java.io.File; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Util;
import util.coord.CoordXYZ;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class PythonOutputtor_x_spbolt5 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                
//        String folder="sp5_55";
//        double p=55;
        String folder="sp5_70";
        double p=70;


        // TODO code application logic here
        double lb=2000;//梁のモデル化長さ
        double lb2=lb+35;//載荷点長さ
        double lc=2828;
        double lc2=3228;
        KPID kp=KPID.C400_N16_4M22;
        double tcfr=9;
        double tcwr=12;
        HSection bsec=new HSection(440,300,11,18,13);
        HSection csec=new HSection(450, 300, 22, 28,tcwr-1.5);
        String path="D:/Dropbox (SSLUoT)/temp2/"+ folder+ "/";
        String path2="C:/Temp/"+ folder+ "/";
        //55-80
        int hs=(int)p*4+80;
        int w=100;
        double[] spy = new double[]{40,40, 40, 40, 40};
        double[] spx = new double[]{2*p, p,0,-p,-2*p};
        
        
        double inip=256000;
        double lw1=csec.getB()-34;
        double lw2=250;
        double N=100;       
        double bk=tcwr*2+csec.getWebThickness()+kp.gettk1();
        SolidColModeler2 sc = new SolidColModeler2(path, tcfr, tcwr,csec,bsec,kp,lc);
        sc.draw_4type();
        sc.drawCFR(-1);
        sc.drawCWR_sep4();
        double tp=16;
        double BP=317;//N/mm2
        sc.drawPanel(tp);
        String cmat="sm490flat";
        String bmat="sm490flat";
        
        
        CantileverJointModeler3 a=new CantileverJointModeler3(
                path,lb,bk,bsec,kp);
        a.setSPInfo(hs, w, spx, spy);
        a.beam();
        a.ShearPlate();
        
        PythonOutputtor_x_spbolt5 b=new PythonOutputtor_x_spbolt5(
                path,lb,lb2,bk,bsec,csec,lc,kp,1.0,tp);
        b.setSPInfo(spy, spx);
        b.main("E:\\temp\\"+folder);
        b.material();
        b.part_col(cmat, tcwr, tcfr);
        b.part_beam(bmat);
        b.part_sp(cmat);
        b.part_bolt();
        b.part_cfr(tcfr, cmat);
        b.part_cwr(tcwr, cmat);
        b.interaction(lc2, lb2, N, BP);
        b.load(inip);
        b.step(0.6, 0.6, 6.0, 100, 10);
        b.setAssembly();
        b.main(path2);
        
        
        
        
    }
    
    private final String path;
    private final HSection bsec;
    private final double tk1;
    private final double gk;
    private final KPID kp;
    private final int boltnum;
    private final bolthole wingbolt;
    private final bolthole basebolt;
    private final bolthole spbolt;
    private final double lb;
    private final double dk;
    private final double bk;
    private final String pnb;
    private double hs;
    private final String pnep;
    private final double te;
    private static final int TYPE_BEAM=0;
    private static final int TYPE_BOLT=1;
    private static final int TYPE_KP=2;
    private static final int TYPE_EP=3;
    private static final int TYPE_SP=4;
    private  String pnkpl;
    private  String pnkpr;
    private String pnsp;
    private double[] spx;
    private double[] spy;
    private  double ts;
    private double lb2;
    private boolean flag;
    private double p1;
    private double e1;
    private double e2;
    private final double ttp;
    private final bolthole ttpbolt;
    private final HSection csec;
    private final double lc;
    private final double tp;
    
    public PythonOutputtor_x_spbolt5(String path,double lb,double lb2,
            double bk,HSection bsec,HSection csec,
            double lc,KPID kp,double mag,double tp){
//        int h=550;
//        int h=(int)bsec.getH();
        FileInputStream fis=null;
        FileOutputStream fos=null;
        try {
            fis  = new FileInputStream(new File("abaqus/core/kp_sp_set2.cae"));
            fos = new FileOutputStream(new File(path+"/kp_sp_set2.cae"));
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }
        catch (Exception e) {
        }
        finally {
            if (fis != null) try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
//                Logger.getLogger(Python_CantileverS.class.getName()).log(Level.SEVERE, null, ex);

            }
            if (fos != null) try {
                fos.close();
            } catch (IOException ex) {
                ex.printStackTrace();
//                Logger.getLogger(Python_CantileverS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.lb2=lb2;
        this.tp=tp;
        this.lc=lc;
        this.path=path;
        this.bsec=bsec;
        this.csec=csec;
        tk1=kp.gettk1();
        this.gk=kp.getgk();//羽根板端部とボルト中心間距離
        this.kp=kp;
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
        
        this.hs=0;
        this.ts=bsec.getWebThickness();
        double hb=bsec.getH();
        this.ttp=new Util().Round(mag*kp.gettk1()*kp.gethk()*2/bsec.getB(), 3);
        
        this.basebolt=new bolthole(c,a,b,bsec.getFlangeThickness()+tk1,d);
        this.ttpbolt=new bolthole(c,a,b,bsec.getFlangeThickness()+ttp,d);
        this.wingbolt=new bolthole(c,a,b,bk+tk1,d);
        this.spbolt=new bolthole(c,a,b,bsec.getWebThickness()+ts,d);
        
        this.lb=lb;
        dk=bsec.getH()+80+tk1*2;
        this.bk=bk;//金物つかみ部距離
        this.pnb="beam";//partname_beam
        this.pnep="endplate";
        this.te=bk-tk1;
        this.pnkpl="";
        this.pnkpr="";
        this.spx=null;
        this.spy=null;
        this.flag=false;
        
        
        this.pnkpl = "n16l";
        this.pnkpr = "n16r";
        this.p1 = 130;
        this.e1 = 131;
        this.e2 = 132;
        this.pnsp = "sp0";
        hs = 320;
        flag = true;
        spy = new double[]{40, 40, 40, 40, 40};
        spx = new double[]{120, 60, 0, -60, -120};

        System.out.println(flag);
//        System.out.println(Arrays.toString(spx));
//        System.out.println(Arrays.toString(spy));
        
    }
    
    public void setSPInfo(double[] spy,double[] spx){
        this.spy=spy;
        this.spx=spx;
    
    }
    
    public void append(TXT_OPE txt,String path){
        //head.txtの読み込み
        try {
            File file = new File(path);
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            String s;
            while ((s = b_reader.readLine()) != null) {
                txt.println(s);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    public void part_topplate(){
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='tp'\n"
                + "ttp="+ttp + ";\n"
                + "\n"
                + "#断面ファイル\n"
                + "s00='tp00';\n"
                + "#フランジ面スケッチファイル\n"
                + "s11='tp01';\n"
                + "\n"
                + "#材料\n"
                + "mat='ss400';\n"
                + "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s00+'.dxf')\n"
                + "importdxf(fileName='lib/'+s11+'.dxf')\n"
                + "#-------------------------------------------------------------------------------------------";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"tp.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(beam);
        if(tk1==16)this.append(txt, "abaqus/core/tp_tk16.part");
        else this.append(txt, "abaqus/core/tp.part");
        txt.finish();
    }
    public void part_col(String mat,double tcwr,double tcfr){
        double l=-(lb2-lb);
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='col'\n"
                + "lc="+ lc+ ";\n"
                + "h="+ csec.getH()+ ";\n"
                + "bc="+ csec.getB()+ ";\n"
                + "#断面ファイル\n"
                + "s0='c00'\n"
                + "#フランジ面スケッチファイル\n"
                + "s1='c01'\n"
                + "#フランジ面スケッチファイル\n"
                + "s2='c02'\n"
                + "#ウェブ面スケッチファイル\n"
                + "s3='c03'\n"
                + "#ウェブ面スケッチファイル\n"
                + "s4='c04'\n"
                + "\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "\n"
                + "#シェル厚の設定\n"
                + "tw="+ csec.getWebThickness()+ "\n"
                + "tcwr="+ tcwr+ "\n"
                + "tf="+ csec.getFlangeThickness()+ "\n"
                + "tcfr="+ tcfr+ "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s0+'.dxf')\n"
                + "importdxf(fileName='lib/'+s1+'.dxf')\n"
                + "importdxf(fileName='lib/'+s2+'.dxf')\n"
                + "importdxf(fileName='lib/'+s3+'.dxf')\n"
                + "importdxf(fileName='lib/'+s4+'.dxf')";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"col.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(beam);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/col.py");
        txt.finish();
    }
    
    public void part_weld(double lw1,double lw2){
        String beam = "lw="+ lw1+ "";
        String beam2 = "lw="+ lw2+ "";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"weld1.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(beam);
        this.append(txt, "abaqus/core/weld1.py");
        txt.finish();
        txt=new TXT_OPE();
        txt.setFileName(path+"weld2.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(beam2);
        this.append(txt, "abaqus/core/weld2.py");
        txt.finish();
    }
    
    public void part_ep(){
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='"+ pnep+ "'\n"
                + "#断面ファイル\n"
                + "s0='endplate0'\n"
                + "#フランジ面スケッチファイル\n"
                + "s1='endplate1'\n"
                + "\n"
                + "#シェル厚の設定\n"
                + "te="+ te+ "\n"
                + "\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s0+'.dxf')\n"
                + "importdxf(fileName='lib/'+s1+'.dxf')";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"endplate.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/core/endplate.part");
        txt.finish();
    }
    
    
    
    public void load(double pretension){
        double a=2*Math.sqrt(2)*Math.pow(wingbolt.getHoleSize()/2, 2);
        double b=2*Math.sqrt(2)*Math.pow(wingbolt.getNutSize()/2, 2);
        double inip=pretension/(b-a);
        String endplate = "#------------------------------------------------------------------------------------\n"
                + "#入力情報\n"
                + "p1=int("+ p1+ ")\n"
                + "end1=int("+ e1+ ")\n"
                + "end2=int("+ e2+ ")\n"
                + "#初期張力と等価な圧力N/mm2\n"
                + "inip="+ inip+ ";"
                + "#強制変位量(mm)\n"
                + "dx="+ lb2*0.1+ ";";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"load.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/load.py");
        txt.finish();
    }

    public void step(double time1,double time2,double time3,int num,int num2) {
        TXT_OPE txt=new TXT_OPE();
        
        String v = "time1="+ time1+ "\n"
                + "time2="+ time2+ "\n"
                + "time3="+ time3+ "\n"
                + "num="+ num+ "\n"
                + "num2="+ num2+ "";
        
        txt.setFileName(path+"step.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(v);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/step.py");
        txt.finish();
    }

    public void set() {
        double a=2*Math.sqrt(2)*Math.pow(wingbolt.getHoleSize()/2, 2);
        double b=2*Math.sqrt(2)*Math.pow(wingbolt.getNutSize()/2, 2);
        String endplate = "#------------------------------------------------------------------------------------\n"
                + "#入力情報\n"
                + "p1=int("+ p1+ ")\n"
                + "end1=int("+ e1+ ")\n"
                + "end2=int("+ e2+ ")\n";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"set.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/core/setRF.part");
        txt.finish();
    }
    

    public void main() {
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"main.py");
        this.append(txt, "abaqus/core/mainRF.py");
//        txt.println(content);
        txt.finish();
    }

    public void main(String path1) {
        String content = "# -*- coding: mbcs -*-\n"
                + "# Do not delete the following import lines\n"
                + "from abaqus import *\n"
                + "from abaqusConstants import *\n"
                + "import __main__\n"
                + "\n"
                + "import section\n"
                + "import regionToolset\n"
                + "import displayGroupMdbToolset as dgm\n"
                + "import part\n"
                + "import material\n"
                + "import assembly\n"
                + "import step\n"
                + "import interaction\n"
                + "import load\n"
                + "import mesh\n"
                + "import optimization\n"
                + "import job\n"
                + "import sketch\n"
                + "import visualization\n"
                + "import xyPlot\n"
                + "import displayGroupOdbToolset as dgo\n"
                + "import connectorBehavior\n"
                + "import os\n"
                + "\n"
                + "os.chdir(r\""+ path1+ "\")\n"
                + "mdb.openAuxMdb(pathName='kp_sp_set2.cae')\n"
                + "mdb.copyAuxMdbModel(fromName='Model-1', toName='Model-1')\n"
                + "mdb.closeAuxMdb()\n"
                + "execfile(\"material.py\", __main__.__dict__)\n"
                + "execfile(\"beam.py\", __main__.__dict__)\n"
                + "execfile(\"col.py\", __main__.__dict__)\n"
                + "execfile(\"sp.py\", __main__.__dict__)\n"
                + "execfile(\"bolt1.py\", __main__.__dict__)\n"
                + "execfile(\"bolt2.py\", __main__.__dict__)\n"
                + "execfile(\"bolt3.py\", __main__.__dict__)\n"
                + "execfile(\"cfr.py\", __main__.__dict__)\n"
                + "execfile(\"cwr.py\", __main__.__dict__)\n"
                + "execfile(\"assemble.py\", __main__.__dict__)\n"
                + "execfile(\"step.py\", __main__.__dict__)\n"
                + "execfile(\"interaction.py\", __main__.__dict__)\n"
                + "execfile(\"load.py\", __main__.__dict__)"
                + "#----------------------------------------------------"
                + "---------------------------------------";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"main_rf.py");
//        this.append(txt, "abaqus/core/mainRF.py");
        txt.println(content);
        txt.finish();
    }

    public void material() {
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"material.py");
        this.append(txt, "abaqus/core/material.py");
        txt.finish();
    }
    
    public void interaction(double lc,double lb,double N,double BP) {
        String interaction = "#------------------------------------------------------------------------------------\n"
                + "lc="+ lc+ "\n"
                + "lb="+ lb+ "\n"
                + "hc="+ csec.getH()+ "\n"
                + "lb2=2*lb+hc+40\n"
                + "pc="+ N+ "\n"
                + "bp="+ BP+ "";
        TXT_OPE txt = new TXT_OPE();
        txt.setFileName(path+"interaction.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(interaction);
        this.append(txt,"abaqus/Xconfig_type4/spbolt5/interaction.py");
        txt.finish();
    }
    
    public void part_bolt(){
        bolthole[] bh={basebolt,wingbolt,spbolt};
        String[] name={"bolt1","bolt2","bolt3"};
        
        for (int i = 0; i < name.length; i++) {
            TXT_OPE txt = new TXT_OPE();
            txt.setFileName(path + name[i]+".py");
            this.append(txt, "abaqus/core/header.part");
            txt.println(this.boltinfo(name[i], 
                    bh[i].getNutSize()/2, 
                    bh[i].getNutHeight(), 
                    bh[i].getAxisSize()/2, 
                    bh[i].getAxisLength()));
            this.append(txt, "abaqus/Xconfig_type4/spbolt5/bolt.py");
            txt.finish();
        }
        
    }
    
    public void part_panel(double tp, String mat) {
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='panel'\n"
                + "#断面ファイル\n"
                + "s0='panel'\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "#厚の設定\n"
                + "tp="+ tp+ "";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"panel.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/core/panel.py");
        txt.finish();
    }
    public void part_cwr(double tcwr,String mat){
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='cwr'\n"
                + "#断面ファイル\n"
                + "s0='cwr4-1'\n"
                + "s1='cwr4-2'\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "#厚の設定\n"
                + "tcwr="+ tcwr+ ""
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s0+'.dxf')\n"
                + "importdxf(fileName='lib/'+s1+'.dxf')\n";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"cwr.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/cwr.py");
        txt.finish();
    }
    public void part_cfr(double tcfr,String mat){
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='cfr'\n"
                + "#断面ファイル\n"
                + "s0='cfr'\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "#厚の設定\n"
                + "tcfr="+ tcfr+ ""
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/cfr.dxf')\n";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"cfr.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/cfr.py");
        txt.finish();
    }
    
    public void part_beam(String mat){
        double l=-(lb2-lb);
        String beam = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='"+ pnb+ "'\n"
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
        this.append(txt, "abaqus/core/header.part");
        txt.println(beam);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/beam.py");
        txt.finish();
    }
    
    public void part_sp(String mat){
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='sp0'\n"
                + "#SPL厚の設定\n"
                + "ts="+ ts+ "\n"
                + "#材料\n"
                + "mat='"+ mat+ "';\n"
                + "#断面ファイル\n"
                + "s0='sh10'\n"
                + "#フランジ面スケッチファイル\n"
                + "s1='sh11'\n"
                + "#スケッチの単なる読み込み\n"
                + "from dxf2abq import importdxf\n"
                + "importdxf(fileName='lib/'+s0+'.dxf')\n"
                + "importdxf(fileName='lib/'+s1+'.dxf')\n"
                + "\n";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"sp.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/Xconfig_type4/spbolt5/sp.py");
        txt.finish();
    }
    
    public String boltinfo(String partname,
            double d1,double t1,double d2,double t2){
        return "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='"+ partname+ "'\n"
                + "d1="+ d1+ ";\n"
                + "t1="+ t1+ "\n"
                + "d2="+ d2+ ";\n"
                + "t2="+ t2+ ";\n"
                + "";
    }
    
    public void setAssembly(){
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"assemble.py");
        this.append(txt, "abaqus/core/header.part");
        
        double a=15;
        
        //<editor-fold defaultstate="collapsed" desc="L梁">
        txt.println(this.getOriginalPosition(pnb, "b0", TYPE_BEAM));
        txt.println(this.getOriginalPosition(pnkpl, "kpl-d0", TYPE_KP));
        txt.println(this.Move("kpl-d0", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,-te/2)));
                
        txt.println(this.getOriginalPosition(pnkpr, "kpr-d0", TYPE_KP));
        txt.println(this.Move("kpr-d0", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,te/2)));
        
        txt.println(this.getOriginalPosition(pnkpl, "kpl-u0", TYPE_KP));
        txt.println(this.Move("kpl-u0", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,-te/2)));
        txt.println(this.Rotate("kpl-u0", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(this.getOriginalPosition(pnkpr, "kpr-u0", TYPE_KP));
        txt.println(this.Move("kpr-u0", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,te/2)));
        txt.println(this.Rotate("kpr-u0", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(this.getOriginalPosition(pnsp, "sp0", TYPE_SP));
        txt.println(this.Move("sp0", new CoordXYZ(0,-20,bsec.getWebThickness()/2)));
        
        String[] na0={"b0","kpl-d0","kpr-d0","kpl-u0","kpr-u0","sp0"};
        for (int i = 0; i < na0.length; i++) {
            String na01 = na0[i];
            txt.println(this.Move(na01, new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate(na01, new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),90));
        }        
        
        CoordXYZ[] pos=new CoordXYZ[]{
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),40,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),100,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),160,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),220,-((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),40,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),100,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),160,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),220,((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),40,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),100,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),160,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),220,-((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),40,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),100,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),160,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),220,((bk-tk1)/2-10+kp.getgk())),
        };
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt1", "bfbolt"+num+"L", TYPE_BOLT));
            txt.println(this.Rotate("bfbolt"+num+"L", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
            txt.println(this.Move("bfbolt"+num+"L", po));
            
            txt.println(this.Move("bfbolt"+num+"L", new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate("bfbolt"+num+"L", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),90));
        }
        
        pos=new CoordXYZ[]{
            new CoordXYZ(-dk/2+30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2+30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(-dk/2-30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2-30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(+dk/2+30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(+dk/2+30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(+dk/2-30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(+dk/2-30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
        };
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt2", "cwbolt"+num+"L", TYPE_BOLT));
            txt.println(this.Move("cwbolt"+num+"L", po));
            
//            txt.println(this.Move("cwbolt"+num+"L", new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate("cwbolt"+num+"L", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),90));
        }
        pos=new CoordXYZ[spx.length];
        for (int i = 0; i < pos.length; i++) {
            pos[i]=new CoordXYZ(spx[i],spy[i],bsec.getWebThickness()/2+ts-spbolt.getNutHeight()-spbolt.getAxisLength());
        }
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt3", "bwbolt"+num+"L", TYPE_BOLT));
            txt.println(this.Move("bwbolt"+num+"L", po));
            txt.println(this.Move("bwbolt"+num+"L", new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate("bwbolt"+num+"L", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),90));
        }
                
//</editor-fold>
        
        a=15;
        
        //<editor-fold defaultstate="collapsed" desc="R梁">
        txt.println(this.getOriginalPosition(pnb, "b1", TYPE_BEAM));
        txt.println(this.getOriginalPosition(pnkpl, "kpl-d1", TYPE_KP));
        txt.println(this.Move("kpl-d1", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,-te/2)));
                
        txt.println(this.getOriginalPosition(pnkpr, "kpr-d1", TYPE_KP));
        txt.println(this.Move("kpr-d1", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,te/2)));
        
        txt.println(this.getOriginalPosition(pnkpl, "kpl-u1", TYPE_KP));
        txt.println(this.Move("kpl-u1", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,-te/2)));
        txt.println(this.Rotate("kpl-u1", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(this.getOriginalPosition(pnkpr, "kpr-u1", TYPE_KP));
        txt.println(this.Move("kpr-u1", new CoordXYZ(-bsec.getH()/2-40-tk1,-csec.getH()/2.0+a,te/2)));
        txt.println(this.Rotate("kpr-u1", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(this.getOriginalPosition(pnsp, "sp1", TYPE_SP));
        txt.println(this.Move("sp1", new CoordXYZ(0,-20,bsec.getWebThickness()/2)));
        
        na0=new String[]{"b1","kpl-d1","kpr-d1","kpl-u1","kpr-u1","sp1"};
        for (int i = 0; i < na0.length; i++) {
            String na01 = na0[i];
            txt.println(this.Move(na01, new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate(na01, new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),-90));
        }
        
        pos=new CoordXYZ[]{
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),40,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),100,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),160,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),220,-((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),40,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),100,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),160,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),220,((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),40,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),100,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),160,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),220,-((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),40,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),100,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),160,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(bsec.getH()/2-basebolt.getNutHeight()-bsec.getFlangeThickness(),220,((bk-tk1)/2-10+kp.getgk())),
        };
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt1", "bfbolt"+num+"R", TYPE_BOLT));
            txt.println(this.Rotate("bfbolt"+num+"R", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
            txt.println(this.Move("bfbolt"+num+"R", po));
            
            txt.println(this.Move("bfbolt"+num+"R", new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate("bfbolt"+num+"R", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),-90));
        }
        
        pos=new CoordXYZ[]{
            new CoordXYZ(-dk/2+30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2+30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(-dk/2-30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2-30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(+dk/2+30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(+dk/2+30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(+dk/2-30,-csec.getH()/2+90,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(+dk/2-30,-csec.getH()/2+150,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
        };
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt2", "cwbolt"+num+"R", TYPE_BOLT));
            txt.println(this.Move("cwbolt"+num+"R", po));
            
//            txt.println(this.Move("cwbolt"+num+"R", new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate("cwbolt"+num+"R", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),-90));
        }
        pos=new CoordXYZ[spx.length];
        for (int i = 0; i < pos.length; i++) {
            pos[i]=new CoordXYZ(spx[i],spy[i],bsec.getWebThickness()/2+ts-spbolt.getNutHeight()-spbolt.getAxisLength());
        }
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt3", "bwbolt"+num+"R", TYPE_BOLT));
            txt.println(this.Move("bwbolt"+num+"R", po));
            txt.println(this.Move("bwbolt"+num+"R", new CoordXYZ(0,csec.getH()/2+20,0)));
            txt.println(this.Rotate("bwbolt"+num+"R", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1),-90));
        }
                
//</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="補強類">
//        txt.println(this.setInstance("cfr", "cfr0"));
//        txt.println(this.Rotate("cfr0", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
//        txt.println(this.Move("cfr0", new CoordXYZ(csec.getH()/2,bsec.getH()/2+75,0)));
//        txt.println(this.setInstance("cfr", "cfr1"));
//        txt.println(this.Rotate("cfr1", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
//        txt.println(this.Move("cfr1", new CoordXYZ(csec.getH()/2,-bsec.getH()/2-75,0)));
//        txt.println(this.setInstance("cfr", "cfr2"));
//        txt.println(this.Rotate("cfr2", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
//        txt.println(this.Move("cfr2", new CoordXYZ(csec.getH()/2,bsec.getH()/2+75,0)));
//        txt.println(this.Rotate("cfr2", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
//        txt.println(this.setInstance("cfr", "cfr3"));
//        txt.println(this.Rotate("cfr3", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
//        txt.println(this.Move("cfr3", new CoordXYZ(csec.getH()/2,-bsec.getH()/2-75,0)));
//        txt.println(this.Rotate("cfr3", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(this.setInstance("cwr", "cwr0"));
        txt.println(this.Move("cwr0", new CoordXYZ(csec.getH()/2-120,dk/2,csec.getWebThickness()/2)));
        txt.println(this.setInstance("cwr", "cwr1"));
        txt.println(this.Move("cwr1", new CoordXYZ(csec.getH()/2-120,-dk/2,csec.getWebThickness()/2)));
        txt.println(this.setInstance("cwr", "cwr2"));
        txt.println(this.Move("cwr2", new CoordXYZ(-csec.getH()/2+120,dk/2,csec.getWebThickness()/2)));
        txt.println(this.Rotate("cwr2", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        txt.println(this.setInstance("cwr", "cwr3"));
        txt.println(this.Move("cwr3", new CoordXYZ(-csec.getH()/2+120,-dk/2,csec.getWebThickness()/2)));
        txt.println(this.Rotate("cwr3", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(this.setInstance("cwr", "cwr4"));
        txt.println(this.Move("cwr4", new CoordXYZ(-csec.getH()/2+120,dk/2,csec.getWebThickness()/2)));
        txt.println(this.setInstance("cwr", "cwr5"));
        txt.println(this.Move("cwr5", new CoordXYZ(-csec.getH()/2+120,-dk/2,csec.getWebThickness()/2)));
        txt.println(this.setInstance("cwr", "cwr6"));
        txt.println(this.Move("cwr6", new CoordXYZ(csec.getH()/2-120,dk/2,csec.getWebThickness()/2)));
        txt.println(this.Rotate("cwr6", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        txt.println(this.setInstance("cwr", "cwr7"));
        txt.println(this.Move("cwr7", new CoordXYZ(csec.getH()/2-120,-dk/2,csec.getWebThickness()/2)));
        txt.println(this.Rotate("cwr7", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
//</editor-fold>
        
        txt.println(this.getOriginalPosition("col", "col", TYPE_KP));
        txt.println(this.Move("col", new CoordXYZ(0,0,-lc/2)));
        txt.println(this.Rotate("col", new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));
//        
//        txt.println(this.setInstance("panel", "panel1"));
//        txt.println(this.Move("panel1",
//                new CoordXYZ(0,0,csec.getWebThickness()/2)));
//        
//        txt.println(this.setInstance("panel", "panel2"));
//        txt.println(this.Move("panel2",
//                new CoordXYZ(0,0,-tp-csec.getWebThickness()/2)));
//        
        txt.finish();
        
    }
    
    
    public void setAssembly2(double lw1, double lw2,double tw){
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"assemble2.py");
        this.append(txt, "abaqus/core/header.part");
        
        txt.println(this.setInstance("weld1", "weld10"));
        txt.println(this.Rotate("weld10", 
                new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(this.Move("weld10",
                new CoordXYZ(0,-tw,-lw1/2)));
        txt.println(this.Move("weld10",
                new CoordXYZ(-csec.getH()/2,bsec.getH()/2-50,0)));
        
        txt.println(this.setInstance("weld1", "weld12"));
        txt.println(this.Rotate("weld12", 
                new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(this.Move("weld12",
                new CoordXYZ(0,-tw,-lw1/2)));
        txt.println(this.Rotate("weld12", 
                new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 180));
        txt.println(this.Move("weld12",
                new CoordXYZ(-csec.getH()/2,bsec.getH()/2+200,0)));
        
        txt.println(this.setInstance("weld2", "weld21"));
        txt.println(this.Rotate("weld21", 
                new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));
        txt.println(this.Move("weld21",
                new CoordXYZ(-tw,lw2/2,lw1/2)));
        txt.println(this.Move("weld21",
                new CoordXYZ(-csec.getH()/2,bsec.getH()/2+75,0)));
        
        txt.println(this.setInstance("weld2", "weld22"));
        txt.println(this.Rotate("weld22", 
                new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));
        txt.println(this.Move("weld22",
                new CoordXYZ(-tw,lw2/2,lw1/2)));
        txt.println(this.Rotate("weld22", 
                new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 180));
        txt.println(this.Move("weld22",
                new CoordXYZ(-csec.getH()/2,bsec.getH()/2+75,0)));
        
        txt.finish();
        
    }
    
    public String Move(String instancename,CoordXYZ xyz) {
        return "a = mdb.models['Model-1'].rootAssembly\n"
                + "a.translate(instanceList=('" 
                + instancename + "', ), vector=("
                + xyz.getX()+ ", "+ xyz.getY()+ ", "+ xyz.getZ()+ "))\n";
    }
    public String Rotate(String instancename,CoordXYZ s,CoordXYZ g,double angle) {
        return "a = mdb.models['Model-1'].rootAssembly\n"
                    + "a.rotate(instanceList=('"+ instancename+ "', ), "
                + "axisPoint=("+ s.getX()+ ", "+ s.getY()+ ", "+ s.getZ()+ "), axisDirection=(\n"
                    + g.getX()+", "+ g.getY()+ ", "+ g.getZ()+ "), angle="+ angle+ ")\n";
    }
    
    public String getOriginalPosition(String partname,String instancename,int type){
        String name="";
        if(type==TYPE_BEAM) {
            name = "#梁\n"
                    + "a = mdb.models['Model-1'].rootAssembly\n"
                    + "p = mdb.models['Model-1'].parts['"+ partname+ "']\n"
                    + "a.Instance(name='"+ instancename+ "', part=p, dependent=ON)\n"
                    + "a = mdb.models['Model-1'].rootAssembly\n"
                    + "a.translate(instanceList=('"+ instancename+ "', ), vector=(0.0, 0.0, -"+ lb+ "))\n"
                    + "a = mdb.models['Model-1'].rootAssembly\n"
                    + "a.rotate(instanceList=('"+ instancename+ "', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(\n"
                    + "0.0, 0.0, 1.0), angle=90.0)\n"
                    + "a = mdb.models['Model-1'].rootAssembly\n"
                    + "a.rotate(instanceList=('"+ instancename+ "', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(\n"
                    + "1.0, 0.0, 0.0), angle=90.0)";
        } else if (type == TYPE_KP) {
            name = "#金物\n"
                    + "a1 = mdb.models['Model-1'].rootAssembly\n"
                    + "p = mdb.models['Model-1'].parts['" + partname + "']\n"
                    + "a1.Instance(name='" + instancename + "', part=p, dependent=ON)\n"
                    + "a1 = mdb.models['Model-1'].rootAssembly\n"
                    + "a1.rotate(instanceList=('" + instancename + "', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(\n"
                    + "0.0, 0.0, 1.0), angle=90.0)\n"
                    + "\n";
        } else if (type == TYPE_BOLT) {
            name = "#ボルト\n"
                    + "a1 = mdb.models['Model-1'].rootAssembly\n"
                    + "p = mdb.models['Model-1'].parts['"+ partname+ "']\n"
                    + "a1.Instance(name='"+ instancename+ "', part=p, dependent=ON)";
        }else if (type==TYPE_EP){
            name= "#エンドプレート\n"
                + "a1 = mdb.models['Model-1'].rootAssembly\n"
                + "p = mdb.models['Model-1'].parts['"+ partname+ "']\n"
                + "a1.Instance(name='"+ instancename+ "', part=p, dependent=ON)\n"
                + "a1.rotate(instanceList=('"+ instancename+ "', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(\n"
                + "0.0, 0.0, 1.0), angle=90.0)";
        }else if (type==TYPE_SP){
            name= "#シアプレート\n"
                + "a1 = mdb.models['Model-1'].rootAssembly\n"
                + "p = mdb.models['Model-1'].parts['"+ partname+ "']\n"
                + "a1.Instance(name='"+ instancename+ "', part=p, dependent=ON)\n"
                + "a1.rotate(instanceList=('"+ instancename+ "', ), axisPoint=(0.0, 0.0, 0.0), axisDirection=(\n"
                + "0.0, 0.0, 1.0), angle=90.0)";
        }else {
            name= "#その他\n"
                + "a1 = mdb.models['Model-1'].rootAssembly\n"
                + "p = mdb.models['Model-1'].parts['"+ partname+ "']\n"
                + "a1.Instance(name='"+ instancename+ "', part=p, dependent=ON)\n";
        }
        return name;
    }
    
    public String setInstance(String partname,String instancename){
        String name="";
            name = "#梁\n"
                    + "a = mdb.models['Model-1'].rootAssembly\n"
                    + "p = mdb.models['Model-1'].parts['"+ partname+ "']\n"
                    + "a.Instance(name='"+ instancename+ "', part=p, dependent=ON)\n";
        return name;
    }
    
}
