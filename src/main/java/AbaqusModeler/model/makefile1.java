/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model;

import AbaqusModeler.CantileverModeler.CantileverJointModeler;
import AbaqusModeler.PythonOutputtor.Python_CantileverS;
import JointCreator.ID.KPID;
import JointCreator.JointState;
import java.io.File;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class makefile1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C400_N25_6M22,
            KPID.C400_N16_4M22,
        };
        HSection[] bsec={
            new HSection(400,200,12,28),
            new HSection(400,200,12,28),
        };
        //金物羽根板間直交方向の距離
        double[] bk={
            30+kp[0].gettk1(),
            30+kp[1].gettk1(),
        };
        String[] bmat={
            "sm400",
            "sm400",
        };
        double[] inip={
            299000,
            299000,
        };
        double[] shearspan={
            5,15,
        };
        double[] lb1=new double[inip.length];
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            lb1[i]=shearspan[i]*dk/2;
        };
        String[] file={
            "abaqus/j00_05",
            "abaqus/j00_15",
        };
        double lb=1250;//梁のモデル化長さ
//        double bk=24+25;
//        KPID kp=KPID.C400_R28_6M24;
//        double inip=349000;
        
        for (int i = 0; i < lb1.length; i++) {
            System.out.println(file[i]);
            String core = file[i];//.partファイルの場所
            File newfile = new File(core);
            if (newfile.mkdir()) {
                System.out.println("ディレクトリの作成に成功しました");
            } else {
                System.out.println("ディレクトリの作成に失敗しました");
            }
            String path = file[i] + "/";
            CantileverJointModeler a
                    = new CantileverJointModeler(path, lb, bk[i], bsec[i], kp[i]);
            a.beam();
            a.endplate();
            Python_CantileverS b = new Python_CantileverS(path, lb, lb1[i], bk[i], bsec[i], kp[i]);
            b.main();
            b.material();
            b.part_sp();
            b.part_beam(bmat[i]);
            b.part_ep();
            b.part_bolt();
            b.setAssembly();
            b.interaction();
            b.step();
            b.load(inip[i]);
            b.set();


        }
        
        
        
    }
    
}
