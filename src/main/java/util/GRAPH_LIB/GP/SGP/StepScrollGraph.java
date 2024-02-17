/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP.SGP;

import util.GRAPH_LIB.GP.GPInterface;
import util.GRAPH_LIB.XY.XYGRAPH;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author nanaka
 */
public class StepScrollGraph extends javax.swing.JPanel implements GPInterface {

    public static void main(String[] args) {
        int num=100;
        double[][] val=new double[2][num];
        for(int i=0;i<num;i++){
            val[0][i]=i;
            val[1][i]=i;
        }
        int num2=30;
        int num3=50;
        double[][][][] val2=new double[num][num2][2][num3];
        double[] cri=new double[num2];
        for(int i = 0; i < num; i++) {
            for (int t = 0; t < num2; t++) {
                cri[t]=2*t;
                for (int s = 0; s < num3; s++) {
                    val2[i][t][0][s] = s;
                    val2[i][t][1][s] = Math.random();
                }
            }
        }
        StepCriterionGraph scg=new StepCriterionGraph("test",val);
        StepScrollGraph sscg=new StepScrollGraph("test2",cri, val2,false, true, Color.yellow);
        ScrollGraphPlotter sgp=new ScrollGraphPlotter(scg);
        sgp.setGraph("test3", sscg);
    }
    /**
     * Creates new form StepScrollGraph2
     */
    XYGRAPH plt;
    int length;
    ArrayList<double[][][][]> value;
    double[] criterion;
    ArrayList<Boolean> plotvisible;
    ArrayList<Boolean> linevisible;
    ArrayList<String> key;
    ArrayList<Color> c;
    private String name;
    int jsbnum;
    int step;

    public StepScrollGraph(int mozisize,
            String key, 
            double[] criterion, 
            double[][][][] value, 
            boolean plotvisible, 
            boolean linevisible, 
            Color LineColor) {
        this.initComponents();
        plt = new XYGRAPH(mozisize);
        plt.setMouseVerify(false);
        this.value = new ArrayList<double[][][][]>();
        this.criterion = criterion;
        this.plotvisible = new ArrayList<Boolean>();
        this.linevisible = new ArrayList<Boolean>();
        this.key = new ArrayList<String>();
        this.c = new ArrayList<Color>();
        panel.setLayout(new BorderLayout());
        panel.add(plt.getPanel(), BorderLayout.CENTER);
        jsbnum = 0;
        step=0;
        jsb.setValue(0);
        jsb.setMinimum(0);
//        System.out.println("StepScrollGraph length: "+value[0].length);
        jsb.setMaximum(value[0].length);
        jsb.setUnitIncrement(1);
        jsb.setBlockIncrement(1);
        this.key.add(key);
        this.value.add(value);
        this.plotvisible.add(plotvisible);
        this.linevisible.add(linevisible);
        this.c.add(LineColor);
    }

    public StepScrollGraph(
            String key, 
            double[] criterion, 
            double[][][][] value, 
            boolean plotvisible, 
            boolean linevisible, 
            Color LineColor) {
        this(20,key,criterion,value,plotvisible,linevisible,LineColor);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setValue(String key, 
            double[] criterion, 
            double[][][][] value, 
            boolean plotvisible, 
            boolean linevisible, 
            Color LineColor) {
        this.key.add(key);
        this.value.add(value);
        this.plotvisible.add(plotvisible);
        this.linevisible.add(linevisible);
        this.c.add(LineColor);
    }

    public JPanel getPanel() {
        return this;
    }

    public void setStatuslabel(JLabel label) {
        //GraphPlotterに表示させたかったらここになんか書けば？
    }

    public final void update(int i) {
        step=i;
        steplabel.setText(""+i);
        criterionlabel.setText(""+criterion[jsbnum]);
        for (int s = 0; s < value.size(); s++) {
            System.out.println(value.get(s).length);
            double[][][] val = value.get(s)[i];
            String k = key.get(s);
            boolean pflag = plotvisible.get(s);
            boolean lflag = linevisible.get(s);
            plt.setValue(k, val[jsbnum], pflag, lflag);
            plt.setLineColor(s, c.get(s));
        }
    }

    public int getLength() {
        return this.length;
    }

    public void setXlabel(String label) {
        plt.setXlabel(label);
        panel.remove(plt.getPanel());
        panel.add(plt.getPanel(), BorderLayout.CENTER);
    }

    public void setYlabel(String label) {
        plt.setYlabel(label);
        panel.remove(plt.getPanel());
        panel.add(plt.getPanel(), BorderLayout.CENTER);
    }

    public void setTitle(String title) {
        plt.settitle(title, true);
        panel.remove(plt.getPanel());
        panel.add(plt.getPanel(), BorderLayout.CENTER);
    }

    public XYGRAPH getGraphAt(int step,int criterionstep) {
        jsbnum=criterionstep;
        this.update(step);
        return plt;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jsb = new javax.swing.JScrollBar();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        steplabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        criterionlabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jsb.setBlockIncrement(1);
        jsb.setVisibleAmount(1);
        jsb.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jsbAdjustmentValueChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new java.awt.GridLayout(1, 4));

        jLabel2.setFont(new java.awt.Font("Konatu", 0, 12)); // NOI18N
        jLabel2.setText("STEP :");
        jPanel1.add(jLabel2);

        steplabel.setFont(new java.awt.Font("Konatu", 0, 12)); // NOI18N
        steplabel.setText("jLabel4");
        jPanel1.add(steplabel);

        jLabel3.setFont(new java.awt.Font("Konatu", 0, 12)); // NOI18N
        jLabel3.setText("CRITERION: ");
        jPanel1.add(jLabel3);

        criterionlabel.setFont(new java.awt.Font("Konatu", 0, 12)); // NOI18N
        criterionlabel.setText("jLabel1");
        jPanel1.add(criterionlabel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jsb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jsb, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jsbAdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jsbAdjustmentValueChanged
        // TODO add your handling code here:
        jsbnum=evt.getValue();
        this.update(step);
    }//GEN-LAST:event_jsbAdjustmentValueChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel criterionlabel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollBar jsb;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel steplabel;
    // End of variables declaration//GEN-END:variables
}
