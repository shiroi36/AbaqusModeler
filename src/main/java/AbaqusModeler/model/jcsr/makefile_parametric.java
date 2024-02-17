/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.jcsr;


import AbaqusModeler.CantileverModeler.CantileverJointModeler3;
import AbaqusModeler.PythonOutputtor.Python_CantileverN;
import JointCreator.ID.KPID;
import java.io.File;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class makefile_parametric {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
            KPID.C490_R28_6M24,
        };
        HSection[] bsec={
            new HSection(900,300,16,40,13),
            new HSection(900,300,16,40,13),
            new HSection(900,300,16,16,13),
            new HSection(900,300,16,16,13),
            new HSection(600,300,16,40,13),
            new HSection(600,300,16,40,13),
            new HSection(600,300,16,16,13),
            new HSection(600,300,16,16,13),
            new HSection(300,300,16,40,13),
            new HSection(300,300,16,40,13),
            new HSection(300,300,16,16,13),
            new HSection(300,300,16,16,13),
        };
        //金物羽根板間直交方向の距離
        double[] bk = {
            40  + kp[0].gettk1(),40  + kp[0].gettk1(),40  + kp[0].gettk1(),40  + kp[0].gettk1(),
            40  + kp[0].gettk1(),40  + kp[0].gettk1(),40  + kp[0].gettk1(),40  + kp[0].gettk1(),
            40  + kp[0].gettk1(),40  + kp[0].gettk1(),40  + kp[0].gettk1(),40  + kp[0].gettk1(),
        };
        String[] bmat={
            "sm490","sm490","sm490","sm490",
            "sm490","sm490","sm490","sm490",
            "sm490","sm490","sm490","sm490",
        };
        double[] inip={
            349000,349000,349000,349000,
            349000,349000,349000,349000,
            349000,349000,349000,349000,
        };
        double[] shearspan={
            6.0,3.0,6.0,3.0,
            6.0,3.0,6.0,3.0,
            6.0,3.0,6.0,3.0,
        };
        double[] lb=new double[inip.length];
        double[] lb1=new double[inip.length];
        
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            System.out.println(dk);
            lb1[i]=shearspan[i]*dk;
            lb[i]=shearspan[i]*dk;
        };
        
        String path="D:\\Dropbox\\Dropbox (SSLUoT)\\"
                + "10_論文\\00_2018jcsr\\analysis/";
        
        String[] file={
            path+"h900tf40lb6",path+"h900tf40lb3",path+"h900tf16lb6",path+"h900tf16lb3",
            path+"h600tf40lb6",path+"h600tf40lb3",path+"h600tf16lb6",path+"h600tf16lb3",
            path+"h300tf40lb6",path+"h300tf40lb3",path+"h300tf16lb6",path+"h300tf16lb3",
        };
        
        path="E:/temp/";
        
        String[] main={
            path+"h900tf40lb6",path+"h900tf40lb3",path+"h900tf16lb6",path+"h900tf16lb3",
            path+"h600tf40lb6",path+"h600tf40lb3",path+"h600tf16lb6",path+"h600tf16lb3",
            path+"h300tf40lb6",path+"h300tf40lb3",path+"h300tf16lb6",path+"h300tf16lb3",
        };
        
        for (int i = 0; i < lb1.length; i++) {
            System.out.println(file[i]);
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
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
            b.add("add2.py",new double[]{dk*shearspan[i]/50});//追加情報
        }
        
        
        
    }
    
}
