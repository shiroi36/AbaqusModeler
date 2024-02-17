/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.math3.linear.RealMatrix;
import util.dxf.DXFpiece;

/**
 *
 * @author keita
 */
public class Util {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static final String OK=" 【OK】";
    public static final String NG="【!!!!!!NG!!!!!】";
    
    public double Round(double val, int num) {
        if(Math.abs(val)<0.000001)val=0.0;
        MathContext mc = new MathContext(num);
        BigDecimal decimal = new BigDecimal(val, mc);
        return decimal.doubleValue();
    }
    public double Round_Down(double val, int num) {
        if(Math.abs(val)<0.000001)val=0.0;
        MathContext mc = new MathContext(num,RoundingMode.DOWN);
        BigDecimal decimal = new BigDecimal(val, mc);
        return decimal.doubleValue();
    }
    public void PrintToConsole(String[] val){
        System.out.println("");
        for (int i = 0; i < val.length; i++) {
            String val4 = val[i];
            System.out.println(val4);
        }
    }
    public String marume(double val,int effectivelength) {
    //精度4桁 四捨五入で丸めるMathContextを作る
        BigDecimal x = new BigDecimal(val);
        x = x.setScale(effectivelength, BigDecimal.ROUND_HALF_UP);

//「12.35」と出力される
//        return result.toEngineeringString();
        return Double.toString(x.doubleValue());
//        return result.toEngineeringString();
        
    }
    
    public String Judge(double margin) {
        String j=Double.toString(this.Round(margin, 3));
        if(margin<=1){j+=OK;}
        else{j+=NG;}
        return j;
    }
    
    public String JudgeSurpass(double target,double[] req){
        for (int i = 0; i < req.length; i++) {
            double s = req[i];
            if(target<s)return NG;
        }
        return OK;
    }
    public String JudgeBelow(double target,double[] req){
        for (int i = 0; i < req.length; i++) {
            double s = req[i];
            if(target>s)return NG;
        }
        return OK;
    }
    public void printMatrix(RealMatrix m){
        double[][] val=m.getData();
        for (int i = 0; i < val.length; i++) {
            double[] val1 = val[i];
            for (int j = 0; j < val1.length; j++) {
                double w = this.Round(val1[j], 4);
//                System.out.println(String.format("%1$5d", this.Round(w, 3)));
                System.out.print(String.format("%1$20.3f ,", w));
            }
            System.out.println("");
        }
        System.out.println("--------------------------------------------------------------------------");
    }
    
    public String[] clean(String[] val){
        ArrayList<String> val2=new ArrayList<String>();
        for (int i = 0; i < val.length; i++) {
            String val1 = val[i].trim();
            if(!val1.isEmpty())val2.add(val1);
                
        }
        return (String[])val2.toArray(new String[0]);
    }
    
    public String getOK(){return OK;}
    public String getNG(){return NG;}
}
