/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model;

import AbaqusModeler.CantileverModeler.CantileverJointModeler;
import AbaqusModeler.PythonOutputtor.Python_CantileverS;
import AbaqusModeler.PythonOutputtor.PythonOutputtor_RF;
import JointCreator.ID.KPID;
import JointCreator.JointState;
import java.io.File;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class makefile_RF20 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID[] kp={
            KPID.C400_N25_6M22,
            KPID.C400_N25_6M22,
            KPID.C400_N16_4M22,
            KPID.C400_N16_4M22,
            
            KPID.C400_R28_6M24,
            KPID.C400_R28_6M24,
            KPID.C400_R22_6M22,
            KPID.C400_R22_6M22,
            
            KPID.C400_N25_6M22,
            KPID.C400_N25_6M22,
            KPID.C400_N16_4M22,
            KPID.C400_N16_4M22,
            
            KPID.C400_R28_6M24,
            KPID.C400_R28_6M24,
            KPID.C400_R22_6M22,
            KPID.C400_R22_6M22,
        };
        HSection[] bsec={
            new HSection(400,200,12,28),
            new HSection(400,200,12,28),
            new HSection(400,200,12,19),
            new HSection(400,200,12,19),
            
            new HSection(400,300,14,28),
            new HSection(400,300,14,28),
            new HSection(400,300,12,19),
            new HSection(400,300,12,19),
            
            new HSection(900,200,14,11),
            new HSection(900,200,14,11),
            new HSection(800,200,12,11),
            new HSection(800,200,12,11),
            
            new HSection(900,300,14,16),
            new HSection(900,300,14,16),
            new HSection(700,300,12,16),
            new HSection(700,300,12,16),
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
            30+kp[8].gettk1(),
            30+kp[9].gettk1(),
            30+kp[10].gettk1(),
            30+kp[11].gettk1(),
            30+kp[12].gettk1(),
            30+kp[13].gettk1(),
            30+kp[14].gettk1(),
            30+kp[15].gettk1(),
        };
        String[] bmat={
            "sm490",
            "sm490",
            "ss400",
            "ss400",
            
            "ss400",
            "ss400",
            "ss400",
            "ss400",
            
            "sm490",
            "sm490",
            "ss400",
            "ss400",
            
            "ss400",
            "ss400",
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
            299000,
            299000,
            
            299000,
            299000,
            299000,
            299000,
            
            349000,
            349000,
            299000,
            299000,
        };
        double[] shearspan={
            5,15,
            5,15,
            5,15,
            5,15,
            5,15,
            5,15,
            5,15,
            5,15
        };
        double[] lb=new double[inip.length];
        double[] lb1=new double[inip.length];
        for (int i = 0; i < lb1.length; i++) {
            double dk = bsec[i].getH()+kp[i].gettk1()*2+kp[i].gethk();
            lb1[i]=shearspan[i]*dk/2;
            lb[i]=5*dk/2;
        };
        String path="abaqus/";
        String[] file={
            path+"j00_05p",
            path+"j00_15p",
            path+"j01_05p",
            path+"j01_15p",
            path+"j02_05p",
            path+"j02_15p",
            path+"j03_05p",
            path+"j03_15p",
            
            path+"j04_05p",
            path+"j04_15p",
            path+"j05_05p",
            path+"j05_15p",
            path+"j06_05p",
            path+"j06_15p",
            path+"j07_05p",
            path+"j07_15p",
        };
        path="H:/stype1604/analysis160518rf20/cae/";
        String[] main={
            path+"j00_05p",
            path+"j00_15p",
            path+"j01_05p",
            path+"j01_15p",
            path+"j02_05p",
            path+"j02_15p",
            path+"j03_05p",
            path+"j03_15p",
            
            path+"j04_05p",
            path+"j04_15p",
            path+"j05_05p",
            path+"j05_15p",
            path+"j06_05p",
            path+"j06_15p",
            path+"j07_05p",
            path+"j07_15p",
        };
        
        
        
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
            path = file[i] + "/";
            CantileverJointModeler a
                    = new CantileverJointModeler(path, lb[i], bk[i], bsec[i], kp[i]);
            a.beam();
            a.endplate();
            a.topplate();
            double mag=2.0;
            PythonOutputtor_RF b = new PythonOutputtor_RF(path, lb[i], lb1[i], bk[i], bsec[i], kp[i],mag);
            b.main(main[i]);
            b.material();
            b.part_sp();
            b.part_beam(bmat[i]);
            b.part_topplate();
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
