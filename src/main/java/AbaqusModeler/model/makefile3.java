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
public class makefile3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C400_N25_6M22,
            KPID.C400_N25_6M22,
            KPID.C400_N25_6M22,
            KPID.C400_N25_6M22,
            
            
            KPID.C400_R28_6M24,
            KPID.C400_R28_6M24,
            KPID.C400_R28_6M24,
            KPID.C400_R28_6M24,
        };
        HSection[] bsec={
            
            new HSection(800,200,15,11),
            new HSection(800,200,15,11),
            
            new HSection(900,200,17,11),
            new HSection(900,200,17,11),
            
            new HSection(800,300,15,16),
            new HSection(800,300,15,16),
            
            new HSection(900,300,17,16),
            new HSection(900,300,17,16),
        };
        //金物羽根板間直交方向の距離
        double[] bk={
            30+kp[0].gettk1(),
            30+kp[1].gettk1(),
            30+kp[2].gettk1(),
            30+kp[3].gettk1(),
            30+kp[4].gettk1(),
            30+kp[5].gettk1(),
            30+kp[6].gettk1(),
            30+kp[7].gettk1(),
        };
        String[] bmat={
            "sm490",
            "sm490",
            "sm490",
            "sm490",
            "sm490",
            "sm490",
            "ss400",
            "ss400",
        };
        double[] inip={
            299000,
            299000,
            299000,
            299000,
            
            349000,
            349000,
            349000,
            349000,
        };
        double[] shearspan={
            5,15,
            5,15,
            5,15,
            5,15,
        };
        double[] lb=new double[inip.length];
        double[] lb1=new double[inip.length];
        
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            lb1[i]=shearspan[i]*dk/2;
            lb[i]=5*dk/2;
        };
        
        String path="D:/Dropbox/Dropbox (SSLUoT)/abaqus/temp/";
        
        String[] file={
            path+"j69_05",
            path+"j69_15",
            path+"j66_05",
            path+"j66_15",
            path+"j67_05",
            path+"j67_15",
            path+"j68_05",
            path+"j68_15",
        };
        
        path="G:/stype1604/analysis160822x/cae/";
        
        String[] main={
            path+"j69_05",
            path+"j69_15",
            path+"j66_05",
            path+"j66_15",
            path+"j67_05",
            path+"j67_15",
            path+"j68_05",
            path+"j68_15",
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
            CantileverJointModeler a
                    = new CantileverJointModeler(path, lb[i], bk[i], bsec[i], kp[i]);
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
