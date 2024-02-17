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
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author ななか
 */
public class StepCriterionGraph extends JFrame implements AdjustmentListener,GPInterface{
    XYGRAPH plt;
    JScrollBar js;
    JLabel l,l2,l3;
    JPanel panel;
    int length;
    private final ArrayList<double[][]> value;
    private SGPEventListener updlistener=null;
    public static final Color[] c={Color.blue,Color.red,Color.orange,Color.cyan,Color.magenta,
    Color.black,Color.GREEN,Color.gray,Color.yellow,Color.PINK};
    private final DecimalFormat df;
    
    public StepCriterionGraph(String key,double[][] value){
        System.out.println(0);
        panel=new JPanel();
        plt=new XYGRAPH();
        plt.setMouseVerify(false);
        this.value=new ArrayList<double[][]>();
        this.value.add(value);
        length=value[0].length;
        l=new JLabel();
        l2=new JLabel();
        l3=new JLabel();
        js=new JScrollBar(JScrollBar.HORIZONTAL,0,1,0,length);
        js.addAdjustmentListener(this);
        plt.setLineColor(this.value.size(), c[this.value.size()]);
        plt.setValue(key, value,false,true);
        df=new DecimalFormat();
        df.applyPattern("0");
        df.setMaximumFractionDigits(3);
        
        
        this.init();
    }
    public void setValue(String key,double[][] value){
        plt.setLineColor(this.value.size(), c[this.value.size()]);
        if(length!=value[0].length){
            return;
        }
        plt.setValue(key, value,false,true);
        this.value.add(value);
    }
    public JPanel getPanel(){
        for(int s=0;s<value.size();s++){
            plt.setLineColor(value.size()+s, c[s]);
        }
        return (JPanel)this.getContentPane();
    }
    public void setStatuslabel(JLabel label){
        //GraphPlotterに表示させたかったらここになんか書けば？
    }
    public final void update(int i){
        l.setText("STEP: "+i);
        l2.setText("X:"+df.format(value.get(0)[0][i]));
        l3.setText("Y:"+df.format(value.get(0)[1][i]));
        for(int s=0;s<value.size();s++){
//            System.out.println(value.size()+s);
            plt.setValue(value.size()+s, new double[][]{{value.get(s)[0][i]},
                {value.get(s)[1][i]}},false,false,12);        
        }
        plt.setLineColor(1,Color.BLUE);
//        this.draw(i);
        if(updlistener!=null){
            updlistener.StepUpdated(new SGPEvent(i,this));
        }
    }
    public void adjustmentValueChanged(AdjustmentEvent e){
        this.update(e.getValue());
    }  
    private void init(){
//        this.getContentPane().removeAll();
        GroupLayout layout=new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        JPanel pane=new JPanel();
        pane.setBackground(Color.white);
        pane.setLayout(new GridLayout(1,1));
        pane.add(js);
        JPanel pane2=new JPanel();
        pane2.setBackground(Color.white);
        pane2.setLayout(new GridLayout(1,3));
        pane2.add(l);
        pane2.add(l2);
        pane2.add(l3);

        panel.setBackground(Color.white);
        panel.setLayout(new GridLayout(1,1));
        panel.add(plt.getPanel());
//        panel.add(plt2.getChartPanel());


        /**
         * 垂直方向
         */
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(pane2))
                .addGroup(layout.createParallelGroup()
                    .addComponent(panel))
                .addGroup(layout.createParallelGroup()
                    .addComponent(pane)));
        /**
         * 鉛直方向
         */
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(pane2)
                    .addComponent(panel)
                    .addComponent(pane)));

        this.getContentPane().repaint();
        this.getContentPane().invalidate();
        this.getContentPane().validate();
    }
    public void Start(){
//        this.draw(0);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 500, 500);
        this.setVisible(true);    
    }
    public void addSGPEventListener(SGPEventListener lis){
        updlistener=lis;
    }
    public void removeSGPEventListener(){
        updlistener=null;
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
    public XYGRAPH getXYGRAPH(){return plt;}
}
