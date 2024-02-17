/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.XY.WITHFFT;

import util.GRAPH_LIB.GP.GraphPlotter;

/**
 *
 * @author ななか
 */
public class sample {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[][] data=new double[2][1000];
        for(int i=0;i<1000;i++){
            data[0][i]=i;
            data[1][i]=Math.random()-0.5;
        }
        XY_FFT ag=new XY_FFT("test", data);
//        GraphPlotter gp=new GraphPlotter();
//        gp.setGraph("test", ag);
        ag.PLOT();
    }
}
