/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.jsce3.old;


import AbaqusModeler.CantileverModeler.CantileverJointModeler3;
import AbaqusModeler.CantileverModeler.CantileverJointModeler4;
import AbaqusModeler.PythonOutputtor.Python_CantileverN;
import AbaqusModeler.PythonOutputtor.Python_CantileverT;
import JointCreator.ID.KPID;
import java.io.File;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class makefile_jsce3T {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
        };
        HSection[] bsec={
            new HSection(600,300,12,14,13),
            new HSection(600,300,12,14,13),
        };
        //金物羽根板間直交方向の距離
        double[] bk={
            60+kp[0].gettk1(),
            90+kp[1].gettk1(),
        };
        String[] bmat={
            "ss400",
            "ss400",
        };
        double[] inip={
            299000,
            299000,
        };
        double[] shearspan={
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
            path+"tes00t",
            path+"tes01t",
        };
        
        path="E:/temp/";
        
        String[] main={
            path+"tes00t",
            path+"tes01t",
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
            CantileverJointModeler4 a
                    = new CantileverJointModeler4(path, lb[i], bk[i], bsec[i], kp[i]);
            a.beam();
            a.endplate();
            Python_CantileverT b = new Python_CantileverT(path, lb[i], lb1[i], bk[i], bsec[i], kp[i]);
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
        }
        
        
        
    }
    
}
