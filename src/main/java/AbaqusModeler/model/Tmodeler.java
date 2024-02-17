/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.model;
import AbaqusModeler.Modeler.Assembler;
import AbaqusModeler.Modeler.BeamModeler;
import AbaqusModeler.Modeler.BoltModeler;
import AbaqusModeler.Modeler.KPModeler;
import AbaqusModeler.Modeler.RFColModeler;
import AbaqusModeler.Modeler.SPModeler;
import AbaqusModeler.Modeler.TopPlateModeler;
import AbaqusModeler.bolthole;
import JointCreator.ID.KPID;
import JointCreator.ID.PlateID;
import util.PATH;
import util.coord.CoordXYZ;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class Tmodeler {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        HSection bsec=new HSection(600,250,9,16);
        HSection colsec=new HSection(500,300,16,28);
//        HSection colsec2=new HSection(700,300-31*2,12,22);
//        System.out.println(colsec.getZex()*325/1000000/1.322);
//        System.out.println(colsec2.getZex()*325/1000000/1.322);
        double tctp=28;//column top plate
        double ttp=28;//top plate
        double lb=2000;//beam length
        double cl=20;//クリアランス
        double lc=2000;//梁長さ
        double bk=30+28;//
        KPID kpid=KPID.C400_N19_6M20;
        double ts=12;
        String mpath=PATH.PATH_DROPBOX+"00_16_webclamp/00_17dev/col/fem/a000/";
        
        
        RFColModeler rcm=new RFColModeler(
                mpath,lc,61,colsec,KPID.C400_N25_6M22,bsec,ttp,tctp,9,12
        );
        rcm.draw();
        rcm.ouputPythonfile("sm490");
        
        BeamModeler bm=new BeamModeler(mpath,lb,bk,bsec,kpid);
        bm.draw();
        bm.outputPythonFile("sm490");
        
        SPModeler spm=new SPModeler(
                mpath,kpid.gettk1(),bsec.getH(),ts);
        String spname=spm.getShearPlateName();
        spm.outputPythonFile("sm490");
        TopPlateModeler tpm=new TopPlateModeler(mpath,lb,bk,colsec,bsec,kpid,tctp,ttp);
        tpm.drawCTP();
        tpm.drawTP();
        tpm.outputCTPPythonFile("sm490");
        tpm.outputTPPythonFile("sm490");
        PlateID ctp=tpm.getCTPID();
        PlateID tp=tpm.getTPID();
        KPModeler kpm=new KPModeler(kpid);
        String kpld=kpm.getKPLname();
        String kprd=kpm.getKPRname();
        
        
        
        double tk1=kpid.gettk1();
        double a=0,b=0,c=0,d=0;
        if(kpid.getD()==22){
            a=36;
            b=13;
            c=22;
            d=22;
        }else if(kpid.getD()==24){
            a=40;
            b=14;
            c=24;
            d=22;
        }else if(kpid.getD()==26){
            a=44;
            b=15;
            c=26;
            d=22;
        }
        bolthole basebolt=new bolthole(c,a,b,bsec.getFlangeThickness()+tk1,d);
        bolthole ctpbolt=new bolthole(c,a,b,tctp+ttp,d);
        bolthole tpbolt=new bolthole(c,a,b,ttp+bsec.getFlangeThickness(),d);
        bolthole wingbolt=new bolthole(c,a,b,bk+tk1,d);
        bolthole spbolt=new bolthole(c,a,b,bsec.getWebThickness()+ts,d);
        
        BoltModeler bol=new BoltModeler(mpath,basebolt,"bolt1");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler(mpath,wingbolt,"bolt2");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler(mpath,spbolt,"bolt3");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler(mpath,ctpbolt,"bolt4");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler(mpath,tpbolt,"bolt5");
        bol.outputPythonFile("F14T");
        
        
        
        
        
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName(mpath+"assembly.py");
        txt.append( "abaqus/core/header.part");
        
        Assembler as=new Assembler();
        txt.println(as.getInstance("col", "c", "柱"));
        txt.println(as.Rotate("c", new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));//反時計回り
        txt.println(as.Move("c", new CoordXYZ(0,bsec.getH()/2-tctp/2,0)));
        
        txt.println(as.getInstance("b", "b0", "梁0"));
        txt.println(as.Rotate("b0", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("b0", new CoordXYZ(0,0,-lb-colsec.getH()/2-cl)));
        txt.println(as.Rotate("b0", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
        txt.println(as.getInstance("b", "b1", "梁1"));
        txt.println(as.Rotate("b1", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("b1", new CoordXYZ(0,0,-lb-colsec.getH()/2-cl)));
        txt.println(as.Rotate("b1", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), -90));
        
        
        txt.println(as.getInstance("ctp", "ctp", "柱トッププレート"));
        txt.println(as.Rotate("ctp", new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));
        txt.println(as.Rotate("ctp", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
        txt.println(as.Move("ctp", new CoordXYZ(-ctp.getLx()/2,bsec.getH()/2-tctp/2,0)));
        
        txt.println(as.getInstance("tp", "tp", "トッププレート"));
        txt.println(as.Rotate("tp", new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));
        txt.println(as.Rotate("tp", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 90));
        txt.println(as.Move("tp", new CoordXYZ(-tp.getLx()/2,bsec.getH()/2+ttp/2,0)));
        
        
        txt.println(as.getInstance(spname, "sp0", "シアプレート"));
        txt.println(as.Rotate("sp0", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("sp0", new CoordXYZ(-colsec.getH()/2,0,bsec.getWebThickness()/2)));
        txt.println(as.getInstance(spname, "sp1", "シアプレート"));
        txt.println(as.Rotate("sp1", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), -90));
        txt.println(as.Move("sp1", new CoordXYZ(colsec.getH()/2,0,bsec.getWebThickness()/2)));
        
        
        txt.println(as.getInstance(kpld, "kpl-d0", "接合金物0"));
        txt.println(as.Rotate("kpl-d0", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("kpl-d0", new CoordXYZ(-colsec.getH()/2+50+200,-bsec.getH()/2-kpid.gettk1()-kpid.gethk()/2,-bk/2+kpid.gettk1()/2)));
        txt.println(as.getInstance(kpld, "kpl-d1", "接合金物1"));
        txt.println(as.Rotate("kpl-d1", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("kpl-d1", new CoordXYZ(-colsec.getH()/2+50+200,-bsec.getH()/2-kpid.gettk1()-kpid.gethk()/2,-bk/2+kpid.gettk1()/2)));
        txt.println(as.Rotate("kpl-d1", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        txt.println(as.getInstance(kprd, "kpr-d0", "接合金物2"));
        txt.println(as.Rotate("kpr-d0", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("kpr-d0", new CoordXYZ(-colsec.getH()/2+50+200,-bsec.getH()/2-kpid.gettk1()-kpid.gethk()/2,bk/2-kpid.gettk1()/2)));
        txt.println(as.getInstance(kprd, "kpr-d1", "接合金物3"));
        txt.println(as.Rotate("kpr-d1", new CoordXYZ(0,0,0), new CoordXYZ(0,0,1), 90));
        txt.println(as.Move("kpr-d1", new CoordXYZ(-colsec.getH()/2+50+200,-bsec.getH()/2-kpid.gettk1()-kpid.gethk()/2,bk/2-kpid.gettk1()/2)));
        txt.println(as.Rotate("kpr-d1", new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        
        String[] name0={"bb000","bb001","bb002","bb003","bb004","bb005",};
        String[] name1={"bb100","bb101","bb102","bb103","bb104","bb105",};
        for (int i = 0; i < name0.length; i++) {
            String name00 = name0[i];
            String name11 = name1[i];
            txt.println(as.getInstance("bolt1",name00, "金物底板ボルト"));
            txt.println(as.Rotate(name00, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name00, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    -bsec.getH() / 2 + basebolt.getNutHeight() + bsec.getFlangeThickness(),
                    bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk())));
            
            txt.println(as.getInstance("bolt1",name11, "金物底板ボルト"));
            txt.println(as.Rotate(name11, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name11, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    -bsec.getH() / 2 + basebolt.getNutHeight() + bsec.getFlangeThickness(),
                    bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk())));
            txt.println(as.Rotate(name11, new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        }
        name0=new String[]{"bb006","bb007","bb008","bb009","bb010","bb011",};
        name1=new String[]{"bb106","bb107","bb108","bb109","bb110","bb111",};
        for (int i = 0; i < name0.length; i++) {
            String name00 = name0[i];
            String name11 = name1[i];
            txt.println(as.getInstance("bolt1",name00, "金物底板ボルト"));
            txt.println(as.Rotate(name00, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name00, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    -bsec.getH() / 2 + basebolt.getNutHeight() + bsec.getFlangeThickness(),
                    -(bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk()))));
            
            txt.println(as.getInstance("bolt1",name11, "金物底板ボルト"));
            txt.println(as.Rotate(name11, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name11, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    -bsec.getH() / 2 + basebolt.getNutHeight() + bsec.getFlangeThickness(),
                    -(bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk()))));
            txt.println(as.Rotate(name11, new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        }
        
        
        String[][] name2=new String[][]{{"wb000","wb001"},{"wb002","wb003"},{"wb004","wb005"}};
        String[][] name3=new String[][]{{"wb100","wb101"},{"wb102","wb103"},{"wb104","wb105"}};
        for (int i = 0; i < name2.length; i++) {
            String[] name21 = name2[i];
            txt.println(as.getInstance("bolt2", name21[0], "金物羽根板ボルト"));
            txt.println(as.Move(name21[0],
                    new CoordXYZ(colsec.getH() / 2 - 50 - 160+60*i,
                            -bsec.getH() / 2 - kpid.gettk1() - kpid.gethk() / 2 + 30,
                            -bk / 2 - kpid.gettk1() / 2-wingbolt.getNutHeight())));
            txt.println(as.getInstance("bolt2", name21[1], "金物羽根板ボルト"));
            txt.println(as.Move(name21[1],
                    new CoordXYZ(colsec.getH() / 2 - 50 - 160+60*i,
                            -bsec.getH() / 2 - kpid.gettk1() - kpid.gethk() / 2 - 30,
                            -bk / 2 - kpid.gettk1() / 2-wingbolt.getNutHeight())));
            
            String[] name31 = name3[i];
            txt.println(as.getInstance("bolt2", name31[0], "金物羽根板ボルト"));
            txt.println(as.Move(name31[0],
                    new CoordXYZ(colsec.getH() / 2 - 50 - 160+60*i,
                            -bsec.getH() / 2 - kpid.gettk1() - kpid.gethk() / 2 + 30,
                            -bk / 2 - kpid.gettk1() / 2-wingbolt.getNutHeight())));
            txt.println(as.Rotate(name31[0], new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
            txt.println(as.getInstance("bolt2", name31[1], "金物羽根板ボルト"));
            txt.println(as.Move(name31[1],
                    new CoordXYZ(colsec.getH() / 2 - 50 - 160+60*i,
                            -bsec.getH() / 2 - kpid.gettk1() - kpid.gethk() / 2 - 30,
                            -bk / 2 - kpid.gettk1() / 2-wingbolt.getNutHeight())));
            txt.println(as.Rotate(name31[1], new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        }
        
        CoordXYZ[] spbp=spm.getBoltPosition();
        
        for (int i = 0; i < spbp.length; i++) {
            CoordXYZ spbp1 = spbp[i];
            String spbname="spb00"+i;
            txt.println(as.getInstance("bolt3",spbname, "シアプレートボルト"));
            txt.println(as.Move(spbname, new CoordXYZ(
                    colsec.getH()/2+20+spbp1.getX(),
                    spbp1.getY(),
                    -spbolt.getNutHeight()-bsec.getWebThickness()/2)));
            spbname="spb10"+i;
            txt.println(as.getInstance("bolt3",spbname, "シアプレートボルト"));
            txt.println(as.Move(spbname, new CoordXYZ(
                    -colsec.getH()/2-20-spbp1.getX(),
                    spbp1.getY(),
                    -spbolt.getNutHeight()-bsec.getWebThickness()/2)));
        }
        
        
        
        name0=new String[]{"ctpb000","ctpb001","ctpb002","ctpb003","ctpb004","ctpb005",};
        name1=new String[]{"ctpb100","ctpb101","ctpb102","ctpb103","ctpb104","ctpb105",};
        for (int i = 0; i < name0.length; i++) {
            String name00 = name0[i];
            String name11 = name1[i];
            txt.println(as.getInstance("bolt4",name00, "金物底板ボルト"));
            txt.println(as.Rotate(name00, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name00, new CoordXYZ(-150+60*i,
                    bsec.getH() / 2 + basebolt.getNutHeight() + ttp,
                    bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk())));
            
            txt.println(as.getInstance("bolt4",name11, "金物底板ボルト"));
            txt.println(as.Rotate(name11, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name11, new CoordXYZ(-150+60*i,
                    bsec.getH() / 2 + basebolt.getNutHeight() + ttp,
                    bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk())));
            txt.println(as.Rotate(name11, new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        }
        
        name0=new String[]{"tpb000","tpb001","tpb002","tpb003","tpb004","tpb005",};
        name1=new String[]{"tpb100","tpb101","tpb102","tpb103","tpb104","tpb105",};
        for (int i = 0; i < name0.length; i++) {
            String name00 = name0[i];
            String name11 = name1[i];
            txt.println(as.getInstance("bolt5",name00, "金物底板ボルト"));
            txt.println(as.Rotate(name00, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name00, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    bsec.getH() / 2 + basebolt.getNutHeight() + ttp,
                    bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk())));
            
            txt.println(as.getInstance("bolt5",name11, "金物底板ボルト"));
            txt.println(as.Rotate(name11, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name11, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    bsec.getH() / 2 + basebolt.getNutHeight() + ttp,
                    bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk())));
            txt.println(as.Rotate(name11, new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        }
        name0=new String[]{"tpb006","tpb007","tpb008","tpb009","tpb010","tpb011",};
        name1=new String[]{"tpb106","tpb107","tpb108","tpb109","tpb110","tpb111",};
        for (int i = 0; i < name0.length; i++) {
            String name00 = name0[i];
            String name11 = name1[i];
            txt.println(as.getInstance("bolt5",name00, "金物底板ボルト"));
            txt.println(as.Rotate(name00, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name00, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    bsec.getH() / 2 + basebolt.getNutHeight() + ttp,
                    -(bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk()))));
            
            txt.println(as.getInstance("bolt5",name11, "金物底板ボルト"));
            txt.println(as.Rotate(name11, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
            txt.println(as.Move(name11, new CoordXYZ(
                    colsec.getH() / 2 + cl + 40+60*i,
                    bsec.getH() / 2 + basebolt.getNutHeight() + ttp,
                    -(bk / 2 - kpid.gettk1() / 2 - 10 + kpid.getgk()))));
            txt.println(as.Rotate(name11, new CoordXYZ(0,0,0), new CoordXYZ(0,1,0), 180));
        }
        
        txt.finish();
    }

    public Tmodeler(){

    }

}
