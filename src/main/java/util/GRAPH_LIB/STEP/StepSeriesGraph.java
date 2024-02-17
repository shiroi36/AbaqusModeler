/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.STEP;
import util.GRAPH_LIB.XY.XYGRAPH;
import util.GRAPH_LIB.GP.GPInterface;
import javax.swing.JScrollBar;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Color;

/**
 *
 * @author keita
 */
public class StepSeriesGraph extends JFrame implements AdjustmentListener,GPInterface{
    XYGRAPH plt;
//    XYGRAPH plt2;
    JScrollBar js;
    JLabel l,l2,l3;
    JPanel panel;
    int length;
//    private double[][][] value;
    private ArrayList<double[][][]> value;
    private ArrayList<String[]> key;
    private boolean Xflag,XLflag;
    private boolean Yflag,YLflag;
    private double xmax,xmin,ymax,ymin;
    private double[] val;
    private double[] val2;
    private String Xlabel;
    private String Ylabel;
    /**
     *
     * @param FrameTitle
     * @param key
     * @param value index[]→ステップ  index[][] 0番目はＸ値　それ以降はＹ値
     */
    public StepSeriesGraph(String FrameTitle,String[] key,double[][][] value){
        this.value=new ArrayList<double[][][]>();
        this.key=new ArrayList<String[]>();
        panel=new JPanel();
        this.value.add(value);
        this.key.add(key);
        Xflag=false;XLflag=false;
        Yflag=false;XLflag=false;

        plt=new XYGRAPH();
        plt.setMouseVerify(false);
        length=value.length;
        js=new JScrollBar(JScrollBar.HORIZONTAL,0,1,0,value.length);
        js.addAdjustmentListener(this);
        l=new JLabel();
        l2=new JLabel();
        l3=new JLabel();
        Font font=new Font("KonatuTohaba",0,12);
        l.setFont(font);
        l2.setFont(font);
        l3.setFont(font);
        this.setTitle(FrameTitle);
    }
    public StepSeriesGraph(String FrameTitle,int scrolllength){
        this.value=new ArrayList<double[][][]>();
        this.key=new ArrayList<String[]>();
        panel=new JPanel();
        Xflag=false;
        Yflag=false;

        plt=new XYGRAPH();
        plt.setMouseVerify(false);
        length=scrolllength;
        js=new JScrollBar(JScrollBar.HORIZONTAL,0,1,0,scrolllength);
        js.addAdjustmentListener(this);
        l=new JLabel();
        l2=new JLabel();
        l3=new JLabel();
        Font font=new Font("KonatuTohaba",0,12);
        l.setFont(font);
        l2.setFont(font);
        l3.setFont(font);
        this.setTitle(FrameTitle);
    }
    public void setlabelValue(double[] val,double[] val2){
        this.val=val;
        this.val2=val2;    
    }
    public void setValue(String[] key,double[][][] value){
        if(value.length!=length){
            System.out.println(key+"    length is not equal!");
        }
        this.value.add(value);
        this.key.add(key);
    }
    public void setValue(String key,double[] x,double[][] value){
        if(value[0].length!=length){
            System.out.println(key+"    length don't matched!");
        }
        if(x.length!=value.length){
            System.out.println(key+"xlength don't matched!");
        }
        double[][][] value2=new double[length][][];
        for(int i=0;i<length;i++){
            double[][] value3=new double[2][x.length];
            for(int j=0;j<value3[0].length;j++){
                value3[0][j]=x[j];
                value3[1][j]=value[j][i];
            }
            value2[i]=value3;
        }
        this.value.add(value2);
        this.key.add(new String[]{key});
    }
    public void Start(){
        this.draw(0);
        this.getContentPane().setBackground(Color.white);
//        this.addMouseListener(plt);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 500, 500);
        this.setVisible(true);
    
    }
    public void setXRange(double min,double max){
        Xflag=true;
        xmax=max;
        xmin=min;
    }
    public void setYRange(double min,double max){
        Yflag=true;
        ymax=max;
        ymin=min;
    }
    public void setXLabel(String label){
        Xlabel=label;
        XLflag=true;
    }
    public void setYLabel(String label){
        Ylabel=label;
        YLflag=true;
    }
    public void update(int i){
        l.setText("STEP → "+i);
        l2.setText("変数1→"+val[i]);
        l3.setText("変数2→"+val2[i]);
        for(int j=0;j<value.size();j++){
            plt.setValue(key.get(j), value.get(j)[i],true,true);
        }
        plt.setLineWidth(0, 2f, Color.BLUE);
        plt.setLineWidth(0, 2f, Color.red);
        plt.setLineWidth(0, 2f, Color.black);

        if(Xflag){
            plt.setXrange(xmin,xmax);
        }
        if(XLflag){
            plt.setXlabel(Xlabel);
        }
        if(Yflag){
            plt.setYrange(ymin,ymax);
        }
        if(YLflag){
            plt.setYlabel(Ylabel);
        }
    }
    public JPanel getPanel(){
        this.draw(0);
        this.getContentPane().setBackground(Color.white);
        return (JPanel)this.getContentPane();
    }
    public void setStatuslabel(JLabel label){
        //GraphPlotterに表示させたかったらここになんか書けば？
    }
    private void draw(int i){
        this.update(i);
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
        JPanel pane3=new JPanel();
        pane3.setBackground(Color.white);
        pane3.setLayout(new GridLayout(1,3));
        pane3.add(l2);
        JPanel pane4=new JPanel();
        pane4.setBackground(Color.white);
        pane4.setLayout(new GridLayout(1,3));
        pane4.add(l3);

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
                    .addComponent(pane3))
                .addGroup(layout.createParallelGroup()
                    .addComponent(pane4))
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
                    .addComponent(pane3)
                    .addComponent(pane4)
                    .addComponent(panel)
                    .addComponent(pane)));

        this.getContentPane().repaint();
        this.getContentPane().invalidate();
        this.getContentPane().validate();
    }
    public void adjustmentValueChanged(AdjustmentEvent e){
        int a=e.getValue();
        this.update(a);
    }    /**     * ラジアンから度に変換します。     *
     * @param radian     * @return     */
    public static double radsToDegrees(double radian) {
        return radian * (180f / Math.PI);
    }
    /**     * 度からラジアンに変換します。*
     * @param degrees     * @return     */
    public static double degreesToRads(double degrees) {
        return degrees * (Math.PI / 180f);
    }
}
