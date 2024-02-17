/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.jsce3.old;

import AbaqusModeler.CantileverModeler.CantileverJointModeler;
import AbaqusModeler.CantileverModeler.CantileverJointModeler3;
import AbaqusModeler.PythonOutputtor.Python_CantileverN;
import AbaqusModeler.PythonOutputtor.Python_CantileverS;
import JointCreator.ID.KPID;
import JointCreator.JointState;
import java.io.File;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class makefile_jsce3N {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
        };
        HSection[] bsec={
            new HSection(600,300,16,9,13),
            new HSection(600,300,16,9,13),
            new HSection(600,300,16,9,13),
            new HSection(600,300,16,12,13),
            new HSection(600,300,16,12,13),
            new HSection(600,300,16,12,13),
            new HSection(600,300,18,18,13),
            new HSection(600,300,18,18,13),
            new HSection(600,300,18,18,13),
            new HSection(600,300,16,28,13),
            new HSection(600,300,16,28,13),
            new HSection(600,300,16,28,13),
            new HSection(400,300,12,9,13),
            new HSection(400,300,12,9,13),
            new HSection(400,300,12,9,13),
            new HSection(400,300,12,12,13),
            new HSection(400,300,12,12,13),
            new HSection(400,300,12,12,13),
            new HSection(400,300,12,18,13),
            new HSection(400,300,12,18,13),
            new HSection(400,300,12,18,13),
            new HSection(400,300,12,28,13),
            new HSection(400,300,12,28,13),
            new HSection(400,300,12,28,13),
        };
        //金物羽根板間直交方向の距離
        double[] bk = {
            30  + kp[0].gettk1(),
            70  + kp[1].gettk1(),
            110 + kp[2].gettk1(),
            30  + kp[0].gettk1(),
            70  + kp[1].gettk1(),
            110 + kp[2].gettk1(),
            30  + kp[3].gettk1(),
            70  + kp[4].gettk1(),
            110 + kp[5].gettk1(),
            30  + kp[6].gettk1(),
            70  + kp[7].gettk1(),
            110 + kp[8].gettk1(),
            30  + kp[0].gettk1(),
            70  + kp[1].gettk1(),
            110 + kp[2].gettk1(),
            30  + kp[0].gettk1(),
            70  + kp[1].gettk1(),
            110 + kp[2].gettk1(),
            30  + kp[3].gettk1(),
            70  + kp[4].gettk1(),
            110 + kp[5].gettk1(),
            30  + kp[6].gettk1(),
            70  + kp[7].gettk1(),
            110 + kp[8].gettk1(),
        };
        String[] bmat={
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
            "steel",
        };
        double[] inip={
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
            205000,
        };
        double[] shearspan={
            5,5,
            5,5,
            5,5,
            5,5,
            5,5,
            5,5,
            
            5,5,
            5,5,
            5,5,
            5,5,
            5,5,
            5,5,
        };
        double[] lb=new double[inip.length];
        double[] lb1=new double[inip.length];
        
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            System.out.println(dk);
            lb1[i]=shearspan[i]*dk/2;
            lb[i]=shearspan[i]*dk/2;
        };
        
        String path="D:/Dropbox/Dropbox (SSLUoT)/10_論文/"
                + "JSCE/00_201603分担評価/model3/";
        
        String[] file={
            path+"t09w100",
            path+"t09w140",
            path+"t09w180",
            path+"t12w100",
            path+"t12w140",
            path+"t12w180",
            path+"t18w100",
            path+"t18w140",
            path+"t18w180",
            path+"t28w100",
            path+"t28w140",
            path+"t28w180",
            path+"h400t09w100",
            path+"h400t09w140",
            path+"h400t09w180",
            path+"h400t12w100",
            path+"h400t12w140",
            path+"h400t12w180",
            path+"h400t18w100",
            path+"h400t18w140",
            path+"h400t18w180",
            path+"h400t28w100",
            path+"h400t28w140",
            path+"h400t28w180",
        };
        
        path="E:/temp/";
        
        String[] main={
            path+"t09w100",
            path+"t09w140",
            path+"t09w180",
            path+"t12w100",
            path+"t12w140",
            path+"t12w180",
            path+"t18w100",
            path+"t18w140",
            path+"t18w180",
            path+"t28w100",
            path+"t28w140",
            path+"t28w180",
            path+"h400t09w100",
            path+"h400t09w140",
            path+"h400t09w180",
            path+"h400t12w100",
            path+"h400t12w140",
            path+"h400t12w180",
            path+"h400t18w100",
            path+"h400t18w140",
            path+"h400t18w180",
            path+"h400t28w100",
            path+"h400t28w140",
            path+"h400t28w180",
        };
        
        for (int i = 0; i < lb1.length; i++) {
            System.out.println(file[i]);
            String core = file[i];//.partファイルの場所
            File newfile = new File(core);
            if (newfile.mkdir()) {
                System.out.println("ディレクトリの作成に成功しました");
            } else {
                System.out.println("ディレクトリの作成に失敗しました");
            }
            path = file[i] + "/";
            CantileverJointModeler3 a
                    = new CantileverJointModeler3(path, lb[i], bk[i], bsec[i], kp[i]);
            a.beam();
            a.endplate();
            Python_CantileverN b = new Python_CantileverN(path, lb[i], lb1[i], bk[i], bsec[i], kp[i]);
            b.main(main[i]);
            b.material();
//            b.part_sp();
            b.part_beam(bmat[i]);
            b.part_ep();
            b.part_bolt();
            b.setAssembly();
            b.interaction();
            b.step();
            b.load(inip[i]);
            b.set();
            b.add();//追加情報
        }
        
        
        
    }
    
}
