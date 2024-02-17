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
public final class StepSeriesGraph implements GPInterface{
    XYGRAPH plt;
    JPanel panel;
    int length;
    ArrayList<double[][][]>ssvalue;
    ArrayList<Boolean> ssplotvisible;
    ArrayList<Boolean> sslinevisible;
    ArrayList<String> sskey;
    ArrayList<Color> sscolor;
    private String name;
    public StepSeriesGraph(String key,double[][][] value,boolean plotvisible,Color LineColor){
        this();
        this.setValue(key,value,plotvisible,LineColor);
    }
    public StepSeriesGraph(int mozisize){
        panel=new JPanel();
        plt=new XYGRAPH(mozisize);
        plt.setMouseVerify(false);
        this.ssvalue=new ArrayList<double[][][]>();   
        this.ssplotvisible=new ArrayList<Boolean>();
        this.sslinevisible=new ArrayList<Boolean>();
        this.sskey=new ArrayList<String>();
        this.sscolor=new ArrayList<Color>();
    }
    public StepSeriesGraph(){
        this(20);
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){return name;}
    public void setValue(String key,double[][][] value,boolean plotvisible,boolean linevisible,Color LineColor){
        this.sskey.add(key);
        this.ssvalue.add(value);
        this.ssplotvisible.add(plotvisible);
        this.sslinevisible.add(linevisible);
        this.sscolor.add(LineColor);
    }
    public void setValue(String key,double[][][] value,boolean plotvisible,Color LineColor){
        this.setValue(key, value, plotvisible, true, LineColor);
    }
    public JPanel getPanel(){
        return (JPanel)plt.getPanel();
    }
    public void setStatuslabel(JLabel label){
        //GraphPlotterに表示させたかったらここになんか書けば？
    }
    public final void update(int i){
        for(int s=0;s<ssvalue.size();s++){
            System.out.println(ssvalue.get(s).length);
            double[][] val=ssvalue.get(s)[i];
            String k=sskey.get(s);
            boolean pflag=ssplotvisible.get(s);
            boolean lflag=sslinevisible.get(s);
            plt.setValue(k, val,pflag,lflag);
            plt.setLineColor(s,sscolor.get(s));
        }
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
    public XYGRAPH getGraphAt(int step){
        this.update(step);
        return plt;
    }
}
