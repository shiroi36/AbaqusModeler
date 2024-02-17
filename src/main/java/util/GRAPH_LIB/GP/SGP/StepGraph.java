/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP.SGP;

import util.GRAPH_LIB.GP.GPInterface;
import util.GRAPH_LIB.XY.XYGRAPH;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author ななか
 */
public class StepGraph implements GPInterface{
    XYGRAPH plt;
    JPanel panel;
    int length;
    boolean flag=false;
    private final ArrayList<double[][]> value;
    private final ArrayList<Boolean> updflag;
    public static final Color[] c={Color.blue,Color.red,Color.GREEN,Color.cyan,Color.magenta,
    Color.black,Color.orange,Color.yellow,Color.PINK,Color.gray,Color.blue,Color.red,Color.GREEN,Color.cyan,Color.magenta,
    Color.black,Color.orange,Color.yellow,Color.PINK,Color.gray};
    private String name;
    //<editor-fold defaultstate="collapsed" desc="stepsiriesの変数">
    private ArrayList<double[][][]>ssvalue;
    private ArrayList<Boolean> ssplotvisible;
    private ArrayList<Boolean> sslinevisible;
    private ArrayList<String> sskey;
    private ArrayList<Color> sscolor;
    //</editor-fold>
    
    public StepGraph(String key,double[][] value){
        this();
        this.value.add(value);
        length=value[0].length;
        plt.setValue(key, value,false,true);
        flag=true;
    }
    public StepGraph(int mozisize){
        panel=new JPanel();
        plt=new XYGRAPH(mozisize);
        plt.setMouseVerify(false);
        this.value=new ArrayList<double[][]>();
        this.updflag=new ArrayList<Boolean>();
        flag=false;
        ssvalue=new ArrayList<double[][][]>();
        ssplotvisible=new ArrayList<Boolean>();
        sslinevisible=new ArrayList<Boolean>();
        sskey=new ArrayList<String>();
        sscolor=new ArrayList<Color>();
    }
    public StepGraph(){
        this(20);
    }
    public void setValueUpdating(String key,double[][] value){
        plt.setLineColor(this.value.size(), c[this.value.size()]);
        if(flag==true&&length!=value[0].length){
            System.out.println(this.toString()+"\t長さが一致しません");
            return;
        }
        plt.setValue(key, value,false,true);
        this.value.add(value);
        updflag.add(true);
    }
    public void setValueUpdating(String key,double[][] value,Color c){
        plt.setLineColor(this.value.size(),c);
        if(flag==true&&length!=value[0].length){
            System.out.println(this.toString()+"\t長さが一致しません");
            return;
        }
        plt.setValue(key, value,false,true);
        this.value.add(value);
        updflag.add(true);
    }
    public void setValueNotUpdating(String key,double[][] value){
        plt.setLineColor(this.value.size(), c[this.value.size()]);
        plt.setValue(key, value,false,true);
        this.value.add(new double[][]{{0},{0}});//dammy
        updflag.add(false);
    }
    public void setStepSeriesValue(
            String key,
            double[][][] value,
            boolean plotvisible,
            boolean linevisible,
            Color LineColor){
        this.sskey.add(key);
        this.ssvalue.add(value);
        this.ssplotvisible.add(plotvisible);
        this.sslinevisible.add(linevisible);
        this.sscolor.add(LineColor);
    }
    public void setSetStepSeriesValue(
            String key,
            double[][][] value,
            boolean plotvisible,
            Color LineColor){
        this.setStepSeriesValue(key, value, plotvisible, true, LineColor);
    }
    public JPanel getPanel(){
//        System.out.println(value.size());
        for(int s = 0; s < ssvalue.size(); s++) {
            plt.setLineColor(value.size() + s, sscolor.get(s));
        }
        int i=0;
        for(int s=0;s<value.size();s++){
            if(updflag.get(s)){
//                System.out.println(value.size()+i+"\t"+ c[s].toString());
                plt.setLineColor(value.size()+ssvalue.size()+i, c[s]);
                i++;
            }
        }
        return (JPanel)plt.getPanel();
    }
    public void setStatuslabel(JLabel label){
        //GraphPlotterに表示させたかったらここになんか書けば？
    }
    public final void update(int i){
        
        //<editor-fold defaultstate="collapsed" desc="StepSeriesGraph機能分">
        for(int s=0;s<ssvalue.size();s++){
            System.out.println(ssvalue.get(s).length);
            double[][] val=ssvalue.get(s)[i];
            String k=sskey.get(s);
            boolean pflag=ssplotvisible.get(s);
            boolean lflag=sslinevisible.get(s);
            plt.setValue(k, val,pflag,lflag);
//            plt.setLineColor(value.size()+s,sscolor.get(s));
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="StepGraph機能分">
        for(int s=0;s<value.size();s++){
            if(updflag.get(s)){
//                System.out.println(value.size()+ssvalue.size()+s
//                        +"\t"+c[s].toString());
                plt.setValue(value.size()+ssvalue.size()+s, new double[][]{{value.get(s)[0][i]},
                    {value.get(s)[1][i]}},true,false,12);  
//                plt.setLineColor(value.size()+ssvalue.size()+s, Color.yellow);
            }     
        }
        System.out.println("");
        //</editor-fold>
        
    }
    public int getLength(){
        return this.length;
    }
    public void setXlabel(String label){
        plt.setXlabel(label);
    }
    public void setYlabel(String label){
        plt.setYlabel(label);
    }
    public void setTitle(String title){
        plt.settitle(title, true);
    }
    public XYGRAPH getGraph(){
        return plt;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
}
