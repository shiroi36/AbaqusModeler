/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.coltest;

import AbaqusModeler.CantileverModeler.CantileverJointModeler3;
import AbaqusModeler.Modeler.SolidColModeler2;
import JointCreator.ID.KPID;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class coltest01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double lb=2000;//梁のモデル化長さ
        double lb2=lb+35;//載荷点長さ
        double lc=2828;
        double lc2=3228;
        KPID kp=KPID.C400_N16_4M22;
        double tcfr=9;
        double tcwr=12;
        HSection bsec=new HSection(440,300,11,18,13);
        HSection csec=new HSection(400, 250, 19, 16,tcwr-1.5);
        String path="D:/Dropbox (SSLUoT)/00_19webclamp/柱実験/fem/col_test/";
        String path2="C:/temp/col_test/";
        
        
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
        String cmat="ss400flat";
        String bmat="sm490flat";
        
        
        CantileverJointModeler3 a=new CantileverJointModeler3(
                path,lb,bk,bsec,kp);
        a.beam();
        
        PythonOutputtor_x2 b=new PythonOutputtor_x2(
                path,lb,lb2,bk,bsec,csec,lc,kp,1.0,tp);
        b.main("E:\\temp\\col180926");
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
    
}
