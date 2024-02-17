/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.jcsr;

import AbaqusModeler.model.jsce3.*;
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
public class makefile_expmodel {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C490_R28_6M24,
        };
        HSection[] bsec={
            new HSection(588,300,22,40,13),
        };
        //金物羽根板間直交方向の距離
        double[] bk = {
            51  + kp[0].gettk1(),
        };
        String[] bmat={
            "sm490",
        };
        double[] inip={
            384000,//M24-349kNの1.1倍
        };
//        double[] shearspan={
//            8.8,
//        };
        double[] lb=new double[inip.length];
        double[] lb1=new double[inip.length];
        
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            System.out.println(dk);
            lb1[i]=3187;
            lb[i]=2946;
        };
        
        String path="D:\\Dropbox\\Dropbox (SSLUoT)\\"
                + "10_論文\\00_2018jcsr\\T実験結果\\fem/";
        
        String[] file={
            path+"expmodel",
        };
        
        path="E:/temp/";
        
        String[] main={
            path+"expmodel",
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
            b.add("add2.py",new double[]{150});//追加情報
        }
        
        
        
    }
    
}
