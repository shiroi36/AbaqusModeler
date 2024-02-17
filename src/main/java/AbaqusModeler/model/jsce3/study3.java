/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.jsce3;

import JointCreator.ID.KPID;
import util.GRAPH_LIB.XY.XYGRAPH;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class study3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double tfs=9;
        double tf=60;//梁フランジ厚
        double tk=22;//金物の板厚
        double hb=100;
        double lf1=180;
//        double lf=120;//金物フランジボルト間距離
        
        int num=100;
        double[] x1=new double[num];//フランジ厚
        double[] y1=new double[num];//金物低下率(lf=100)
        double[] y2=new double[num];//剛性低下率(lf=100)
        
        for (int i = 0; i < num; i++) {
            double t=(tf-tfs)/num*i+tfs;
            Propose1 p1=new Propose1(t,tk,lf1,hb);
            x1[i]=p1.getLf_by_tf();
            y1[i]=p1.getYieldStrengthDegrationRatio();
            y2[i]=p1.getTotalStiffnessDegrationRatio();
        }
        
        XYGRAPH plt=new XYGRAPH();
        plt.setValue("降伏耐力低下率", new double[][]{x1,y1});
        plt.setValue("剛性低下率", new double[][]{x1,y2});
        plt.setXlabel("lf/tf");
        plt.setYlabel("金物降伏耐力低下率");
        plt.setXrange(lf1/tf, lf1/tfs);
        plt.setYrange(0.4, 1.1);
        plt.PLOT();
       
        
        
        
    }
    
    public static class Propose1 {
        private final double a;
        private final double k;
        private final double k1;
        private final double k2;
        private final double lftf;
        private final double Ka;
        private final double Kl;

        public Propose1(double tf, double tk, double lf,double hb) {

            //ほぼ決まっている数
            double lk = 70;//金物の変形部長さ
            double b = 380;//金物の縦長さ
            double E = 205000;//ヤング率
            double ef = 40 + 22;//金物の偏心距離
            double hk = 80;//接合金物高さ

            double dk=hb+ef*2;
            double Ak = 2*hk * tk;
            double Ik = (2 * tk * Math.pow(hk, 3)) / 12.0;
            double Zk = (2 * tk * Math.pow(hk, 2)) / 6.0;
            
            
            this.k1=E*Ik/lk;
            this.k2=16.0*E/3.0*Math.pow(tf*b/lf, 3);
            
            this.Ka=Math.pow(dk, 2)*E*Ak/2.0/lk;
            this.Kl=dk*hb*(k1+k2)/2.0/Math.pow(ef, 2);
            
            this.k=k1+k2;
            
            this.a=(k*Zk)/(k*Zk+k1*ef*Ak);
            
            this.lftf=lf/tf;
            
//            double a0=(ef / lk) * (hk / 2) / (Ik / lk + 16.0 / 3.0 * Math.pow(tf * b / lf, 3)) * Ak;
//            this.a=1.0/(1.0+a0);

//            this.k = (E / ef) * (Ik / lk + 16.0 / 3.0 * Math.pow(tf * b / lf, 3));
        }
        
        public double getYieldStrengthDegrationRatio(){
            return a;
        }
        
        public double getAttachmentRotationStiffness(){
            return k;
        }
        
        public double getTotalStiffness(){
            return Ka*Kl/(Ka+Kl);
        }
        
        public double getTotalStiffnessDegrationRatio(){
            return Kl/(Ka+Kl);
        }
        
        public double getWingPlateStiffness(){
            return k1;
        }
        
        public double getBeamFlangeStiffness(){
            return k2;
        }
        
        public double getLf_by_tf(){
            return lftf;
        }

        
    }
    
}
