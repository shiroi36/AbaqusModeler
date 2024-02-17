/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model;

import AbaqusModeler.CantileverModeler.CantileverJointModeler;
import AbaqusModeler.CantileverModeler.CantileverJointModeler3;
import AbaqusModeler.PythonOutputtor.Python_CantileverS;
import JointCreator.ID.KPID;
import JointCreator.JointState;
import java.io.File;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class makefile_funuc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C400_N16_4M22,
            KPID.C400_N16_4M22,
        };
        HSection[] bsec={
            new HSection(496,199,9,14,13),
            new HSection(496,199,9,14,13),
        };
        //金物羽根板間直交方向の距離
        double[] bk={
            30+kp[0].gettk1(),
            30+kp[0].gettk1(),
        };
        String[] bmat={
            "ss400",
            "ss400",
        };
        double[] inip={//設計用ボルト張力
            256000,
            205000,
        };
        double[] shearspan={
            8,
            8,
        };
        double[] lb=new double[inip.length];
        double[] lb1=new double[inip.length];
        
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            System.out.println(dk);
            lb1[i]=shearspan[i]*dk/2;
            lb[i]=shearspan[i]*dk/2;
        };
        
        String path="E:/";
        
        String[] file={
            path+"funuc_mu40",
            path+"funuc_mu45",
        };
        
        path="E:/temp/";
        
        String[] main={
            path+"funuc_mu40",
            path+"funuc_mu45",
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
            Python_CantileverS b = new Python_CantileverS(path, lb[i], lb1[i], bk[i], bsec[i], kp[i]);
            b.main(main[i]);
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
