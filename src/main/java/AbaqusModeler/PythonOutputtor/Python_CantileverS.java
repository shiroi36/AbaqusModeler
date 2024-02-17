/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.PythonOutputtor;

import AbaqusModeler.CantileverModeler.CantileverJointModeler;
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
import util.coord.CoordXYZ;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class Python_CantileverS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double lb=1500;//梁のモデル化長さ
        double lb2=4000;//載荷点長さ
        double bk=24+25;
        KPID kp=KPID.C400_R25_6M22;
        HSection bsec=new HSection(600,300,12,20);
        double inip=329000;
        
        String path="D:/temp/00_soma/";
        CantileverJointModeler a=new CantileverJointModeler(
                path,lb,bk,bsec,kp);
        
        a.beam();
        a.endplate();
        Python_CantileverS b=new Python_CantileverS("abaqus/",lb,lb2,bk,bsec,kp);
        b.main();
        b.material();
        b.part_beam("sm400");
        b.part_ep();
        b.part_bolt();
        b.setAssembly();
        b.interaction();
        b.step();
        b.load(inip);
        b.set();
        
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
    private int e2;
    private int e1;
    private int p1;
    private boolean flag;
    
    public Python_CantileverS(String path,double lb,double lb2,double bk,HSection bsec,KPID kp){
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
                Logger.getLogger(Python_CantileverS.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (fos != null) try {
                fos.close();
            } catch (IOException ex) {
                Logger.getLogger(Python_CantileverS.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.lb2=lb2;
        this.path=path;
        this.bsec=bsec;
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
            d=20;
        }else if(kp.getD()==24){
            a=40;
            b=14;
            c=24;
            d=22;
        }else if(kp.getD()==26){
            a=44;
            b=15;
            c=26;
            d=24;
        }
        
        this.hs=0;
        this.ts=bsec.getWebThickness();
        double hb=bsec.getH();
        
        this.basebolt=new bolthole(c,a,b,bsec.getFlangeThickness()+tk1,d);
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
        if(tk1==19){
            this.pnkpl = "n19l";
            this.pnkpr = "n19r";
            if(hb==600){
                this.p1 = 196;
                this.e1 = 197;
                this.e2 = 198;
            }
            this.pnsp = "sp600_M20";
            hs = 455;
            spy = new double[]{40, 40, 40, 40, 40, 40};
            spx = new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
        }
        
        if(tk1==16||tk1==22 || tk1 == 25) {
            if (tk1 == 16) {
                this.pnkpl = "n16l";
                this.pnkpr = "n16r";
            }else if(tk1==22){
                this.pnkpl = "n22l";
                this.pnkpr = "n22r";
            }else if(tk1==25){
                this.pnkpl = "n25l";
                this.pnkpr = "n25r";
            }
            if (hb == 400) {
                this.p1 = 164;
                this.e1 = 165;
                this.e2 = 166;
                if (tk1 == 16) {
                    this.p1 = 140;
                    this.e1 = 141;
                    this.e2 = 142;
                }else if(tk1==22){
                    this.p1 = 172;
                    this.e1 = 173;
                    this.e2 = 174;
                }
                this.pnsp = "sp400_M22";
                hs = 290;
                flag=true;
                spy = new double[]{40, 40, 40, 40, 100, 100, 100, 100};
                spx = new double[]{105, 35, -35, -105, 105, 35, -35, -105};
            } else if (hb == 450) {
                this.p1 = 164;
                this.e1 = 165;
                this.e2 = 166;
                if (tk1 == 16) {
                    this.p1 = 140;
                    this.e1 = 141;
                    this.e2 = 142;
                }else if(tk1==22){
                    this.p1 = 172;
                    this.e1 = 173;
                    this.e2 = 174;
                }
                this.pnsp = "sp450_M22";
                hs = 305;
                flag=true;
                spy = new double[]{40, 40, 40, 40, 100, 100, 100, 100};
                spx = new double[]{112.5, 37.5, -37.5, -112.5, 112.5, 37.5, -37.5, -112.5};
            } else if ((hb>=490&&hb <= 500) || hb == 550) {
                this.p1 = 162;
                this.e1 = 163;
                this.e2 = 164;
                if (tk1 == 16) {
                    this.p1 = 138;
                    this.e1 = 139;
                    this.e2 = 140;
                }else if(tk1==22){
                    this.p1 = 172;
                    this.e1 = 173;
                    this.e2 = 174;
                }
                this.pnsp = "sp500_M22";
                hs = 380;
                spy = new double[]{40, 40, 40, 40, 40, 40};
                spx = new double[]{150,90,30,-30,-90,-150 };
            } else if (hb == 600 || hb == 650) {
                this.p1 = 160;
                this.e1 = 161;
                this.e2 = 162;
                if (tk1 == 16) {
                    this.p1 = 158;
                    this.e1 = 159;
                    this.e2 = 160;
                }else if(tk1==22){
                    this.p1 = 196;
                    this.e1 = 197;
                    this.e2 = 198;
                }
                this.pnsp = "sp600_M22";
                hs = 455;
                spy = new double[]{40, 40, 40, 40, 40, 40};
                spx = new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
            } else if (hb == 700 || hb == 750 || hb == 800) {
                this.p1 = 162;
                this.e1 = 163;
                this.e2 = 164;
                if (tk1 == 16) {
                    this.p1 = 138;
                    this.e1 = 139;
                    this.e2 = 140;
                }else if(tk1==22){
                    this.p1 = 196;
                    this.e1 = 197;
                    this.e2 = 198;
                }
                this.pnsp = "sp800_M22";
                hs = 530;
                spy = new double[]{40, 40, 40, 40, 40, 40, 40};
                spx = new double[]{225, 150, 75, 0, -75, -150, -225};
            } else if (hb == 900 || hb == 850) {
                this.p1 = 162;
                this.e1 = 163;
                this.e2 = 164;
                if (tk1 == 16) {
                    this.p1 = 138;
                    this.e1 = 139;
                    this.e2 = 140;
                }else if(tk1==22){
                    this.p1 = 196;
                    this.e1 = 197;
                    this.e2 = 198;
                }
                this.pnsp = "sp900_M22";
                hs = 620;
                spy = new double[]{40, 40, 40, 40, 40, 40, 40};
                spx = new double[]{270, 180, 90, 0, -90, -180, -270};
            }
        }else if(tk1==28){
            this.pnkpl = "r28l";
            this.pnkpr = "r28r";
            if (hb == 400) {
                this.p1 = 164;
                this.e1 = 165;
                this.e2 = 166;
                this.pnsp = "sp400_M24";
                hs = 290;
                flag=true;
                spy = new double[]{40, 40, 40, 40, 100, 100, 100, 100};
                spx = new double[]{105, 35, -35, -105, 105, 35, -35, -105};
            } else if (hb == 450) {
                this.p1 = 164;
                this.e1 = 165;
                this.e2 = 166;
                this.pnsp = "sp450_M24";
                hs = 305;
                flag=true;
                spy = new double[]{40, 40, 40, 40, 100, 100, 100, 100};
                spx = new double[]{112.5, 37.5, -37.5, -112.5, 112.5, 37.5, -37.5, -112.5};
            } else if (hb == 500 || hb == 550) {
                this.p1 = 162;
                this.e1 = 163;
                this.e2 = 164;
                this.pnsp = "sp550_M24";
                hs = 360;
                spy = new double[]{40, 40, 40, 40, 40, 100, 100};
                spx = new double[]{140, 70, 0, -70, -140, 140, -140};
            } else if (hb == 600 || hb == 650) {
                this.p1 = 196;
                this.e1 = 197;
                this.e2 = 198;
                this.pnsp = "sp600_M24";
                hs = 455;
                spy = new double[]{40, 40, 40, 40, 40, 40};
                spx = new double[]{150+37.5,75+37.5,37.5,-37.5,-75-37.5,-150-37.5};
            } else if (hb == 700 || hb == 750 || hb == 800) {
                this.p1 = 162;
                this.e1 = 163;
                this.e2 = 164;
                this.pnsp = "sp800_M24";
                hs = 530;
                spy = new double[]{40, 40, 40, 40, 40, 40, 40};
                spx = new double[]{225, 150, 75, 0, -75, -150, -225};
            } else if (hb == 900 || hb == 850) {
                this.p1 = 162;
                this.e1 = 163;
                this.e2 = 164;
                this.pnsp = "sp900_M24";
                hs = 620;
                spy = new double[]{40, 40, 40, 40, 40, 40, 40};
                spx = new double[]{270, 180, 90, 0, -90, -180, -270};
            }
        }
//        System.out.println(Arrays.toString(spx));
//        System.out.println(Arrays.toString(spy));
        
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
                + "ttp="+ kp.gettk1()+ ";\n"
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
        this.append(txt, "abaqus/core/tp.part");
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
//        this.append(txt, "abaqus/core/beamCS.py");
        this.append(txt, "abaqus/core/beam.part");
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
    
    public void part_sp(){
        String endplate = "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='"+ pnsp+ "'\n"
                + "#SPL厚の設定\n"
                + "ts="+ ts+ "\n"
                + "\n";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"sp.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/core/sp.part");
        txt.finish();
    }
    
    public void load(double pretension){
        double a=2*Math.sqrt(2)*Math.pow(wingbolt.getHoleSize()/2+1, 2);
        double b=2*Math.sqrt(2)*Math.pow(wingbolt.getNutSize()/2, 2);
        double inip=pretension/(b-a);
        System.out.println("wingbolt.getHoleSize() = " + wingbolt.getHoleSize());
        System.out.println("wingbolt.getNutSize() = " + wingbolt.getNutSize());
        System.out.println(inip);
        String endplate = "#------------------------------------------------------------------------------------\n"
                + "#入力情報\n"
                + "p1=int("+ p1+ ")\n"
                + "end1=int("+ e1+ ")\n"
                + "end2=int("+ e2+ ")\n"
                + "#初期張力と等価な圧力N/mm2\n"
                + "inip="+ inip+ ";"
                + "#強制変位量(mm)\n"
                + "dx="+ 0.02+ ";";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"load.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(endplate);
        this.append(txt, "abaqus/core/loadC.py");
        txt.finish();
    }

    public void step() {
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"step.py");
        this.append(txt, "abaqus/core/step.py");
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
        this.append(txt, "abaqus/core/setCS.py");
        txt.finish();
    }

    public void main(String path1) {
        String content = "## -*- coding: mbcs -*-\n"
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
                + "execfile(\"sp.py\", __main__.__dict__)\n"
                + "execfile(\"beam.py\", __main__.__dict__)\n"
                + "execfile(\"bolt1.py\", __main__.__dict__)\n"
                + "execfile(\"bolt2.py\", __main__.__dict__)\n"
                + "execfile(\"bolt3.py\", __main__.__dict__)\n"
                + "execfile(\"endplate.py\", __main__.__dict__)\n"
                + "\n"
                + "\n"
                + "execfile(\"assemble.py\", __main__.__dict__)\n"
                + "execfile(\"step.py\", __main__.__dict__)\n"
                + "execfile(\"interaction.py\", __main__.__dict__)\n"
                + "execfile(\"load.py\", __main__.__dict__)\n"
                + "\n"
                + "execfile(\"set.py\", __main__.__dict__)\n"
                + "\n"
                + "\n"
                + "#-------------------------------------------------------------------------------------------";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"main_rf.py");
//        this.append(txt, "abaqus/core/mainRF.py");
        txt.println(content);
        txt.finish();
    }

    public void main() {
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"main.py");
        this.append(txt, "abaqus/core/main.py");
        txt.finish();
    }

    public void material() {
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"material.py");
        this.append(txt, "abaqus/core/material.py");
        txt.finish();
    }
    
    public void interaction() {
        String interaction = "#------------------------------------------------------------------------------------\n"
                + "#入力情報\n"
                + "lb="+ lb+ ";\n"
                + "h="+ bsec.getH()+ ";\n"
                + "tk1="+ tk1+ ";\n"
                + "#シアプレートたかさ\n"
                + "hs="+ hs+ ";\n"
                + "ts="+ ts+ "\n"
                + "#シェル厚の設定\n"
                + "tw="+ bsec.getWebThickness()+ "\n"
                + "tf="+ bsec.getFlangeThickness()+ "\n"
                + "p1=int("+ p1+ ")\n"
                + "end1=int("+ e1+ ")\n"
                + "end2=int("+ e2+ ")\n"
                + "\n"
                + "bk="+ bk+ ";\n"
                + "dk=h+2*tk1+80;";
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(path+"interaction.py");
        this.append(txt, "abaqus/core/header.part");
        txt.println(interaction);
        this.append(txt, "abaqus/core/interactionC.py");
//        if(tk1==16){
//            if(flag){
//                this.append(txt,"abaqus/core/interaction_tk16_8sp.part");
//            }else{
//                this.append(txt,"abaqus/core/interaction_tk16.part");
//            }
//        }else{
//            if(flag){
//                 this.append(txt, "abaqus/core/interaction_8sp.part");
//            }else{ 
//                this.append(txt, "abaqus/core/interaction.part");
//            }
//        }
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
            this.append(txt, "abaqus/core/bolt.part");
            txt.finish();
        }
        
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
        
        txt.println(this.getOriginalPosition(pnb, "b", TYPE_BEAM));
        double a=0;
        if(tk1==16){a=60;}
        txt.println(this.getOriginalPosition(pnkpl, "kpl-d", TYPE_KP));
        txt.println(this.Move("kpl-d", new CoordXYZ(-bsec.getH()/2-40-tk1,-270+a,-te/2)));
        
        txt.println(this.getOriginalPosition(pnkpr, "kpr-d", TYPE_KP));
        txt.println(this.Move("kpr-d", new CoordXYZ(-bsec.getH()/2-40-tk1,-270+a,te/2)));
        
        txt.println(this.getOriginalPosition(pnkpl, "kpl-u", TYPE_KP));
        txt.println(this.Rotate("kpl-u", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        txt.println(this.Move("kpl-u", new CoordXYZ(bsec.getH()/2+40+tk1,-270+a,te/2)));
        
        txt.println(this.getOriginalPosition(pnkpr, "kpr-u", TYPE_KP));
        txt.println(this.Rotate("kpr-u", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        txt.println(this.Move("kpr-u", new CoordXYZ(bsec.getH()/2+40+tk1,-270+a,-te/2)));
        
        txt.println(this.getOriginalPosition(pnsp, "sp", TYPE_SP));
        txt.println(this.Move("sp", new CoordXYZ(0,-20,bsec.getWebThickness()/2)));
        
        
        txt.println(this.getOriginalPosition("endplate", "ep", TYPE_EP));
        txt.println(this.Move("ep", new CoordXYZ(0,-240-70,0)));
        
        CoordXYZ[] pos=new CoordXYZ[]{
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),40,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),100,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),160,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),220,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),280,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),340,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),40,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),100,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),160,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),220,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),280,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ((bsec.getH()/2+tk1-basebolt.getNutHeight()-basebolt.getAxisLength()),340,((bk-tk1)/2-10+kp.getgk())),
            
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),40,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),100,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),160,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),220,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),280,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),340,-((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),40,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),100,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),160,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),220,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH()/2-tk1-basebolt.getNutHeight(),280,((bk-tk1)/2-10+kp.getgk())),
            new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 340, ((bk - tk1) / 2 - 10 + kp.getgk())),};
        if (tk1 == 16) {
            pos = new CoordXYZ[]{
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 40, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 100, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 160, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 220, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 40, ((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 100, ((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 160, ((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ((bsec.getH() / 2 + tk1 - basebolt.getNutHeight() - basebolt.getAxisLength()), 220, ((bk - tk1) / 2 - 10 + kp.getgk())),
                
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 40, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 100, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 160, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 220, -((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 40, ((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 100, ((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 160, ((bk - tk1) / 2 - 10 + kp.getgk())),
                new CoordXYZ(-bsec.getH() / 2 - tk1 - basebolt.getNutHeight(), 220, ((bk - tk1) / 2 - 10 + kp.getgk())),};
        }
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt1", "bfbolt"+num, TYPE_BOLT));
            txt.println(this.Rotate("bfbolt"+num, new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
            txt.println(this.Move("bfbolt"+num, po));
        }
        
        
        pos=new CoordXYZ[]{
            new CoordXYZ(dk/2+30,-70-40,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(dk/2+30,-70-40-60,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(dk/2+30,-70-40-120,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(dk/2-30,-70-40,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(dk/2-30,-70-40-60,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(dk/2-30,-70-40-120,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            
            new CoordXYZ(-dk/2+30,-70-40,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2+30,-70-40-60,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2+30,-70-40-120,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2-30,-70-40,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2-30,-70-40-60,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
            new CoordXYZ(-dk/2-30,-70-40-120,-(wingbolt.getAxisLength()+wingbolt.getNutHeight()*2)/2),
        };
        if (tk1 == 16) {
            pos = new CoordXYZ[]{
                new CoordXYZ(dk / 2 + 30, -70 - 40, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                new CoordXYZ(dk / 2 + 30, -70 - 40 - 60, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                new CoordXYZ(dk / 2 - 30, -70 - 40, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                new CoordXYZ(dk / 2 - 30, -70 - 40 - 60, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                
                new CoordXYZ(-dk / 2 + 30, -70 - 40, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                new CoordXYZ(-dk / 2 + 30, -70 - 40 - 60, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                new CoordXYZ(-dk / 2 - 30, -70 - 40, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
                new CoordXYZ(-dk / 2 - 30, -70 - 40 - 60, -(wingbolt.getAxisLength() + wingbolt.getNutHeight() * 2) / 2),
            };
        }
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt2", "cwbolt"+num, TYPE_BOLT));
            txt.println(this.Move("cwbolt"+num, po));
        }
        pos=new CoordXYZ[spx.length];
        for (int i = 0; i < pos.length; i++) {
            pos[i]=new CoordXYZ(spx[i],spy[i],bsec.getWebThickness()/2+ts-spbolt.getNutHeight()-spbolt.getAxisLength());
        }
        
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            String num=String.format("%1$02d", i);
            txt.println(this.getOriginalPosition("bolt3", "bwbolt"+num, TYPE_BOLT));
            txt.println(this.Move("bwbolt"+num, po));
        }
        
        
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
        }
        return name;
    }
    
}
