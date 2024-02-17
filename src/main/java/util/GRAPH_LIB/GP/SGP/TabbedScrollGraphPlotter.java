/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.GP.SGP;

import util.GRAPH_LIB.GP.GPKeyEvent;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import java.util.ArrayList;
import javax.swing.JToggleButton;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultDesktopManager;
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.HashMap;
import javax.swing.*;
import util.GRAPH_LIB.GP.GPInterface;
import util.GRAPH_LIB.GP.TGPListPanel;
import util.GRAPH_LIB.GP.abstractGraphPlotter;




/**
 *
 * @author araki keita
 */
public class TabbedScrollGraphPlotter extends abstractGraphPlotter implements SGPEventListener {
    private JDesktopPane desktop;
    private JInternalFrame list;
    private HashMap<String, TGPListPanel> map;
    private HashMap<String, ArrayList<GPInterface>> map2;
//    ArrayList<JInternalFrame> iframe;
//    ArrayList<String> name;
//    ArrayList<JToggleButton> button;
//    JButton on,off;
    boolean listflag;
    int iniw,inih;
    private TSGPKeyEvent gpkey;
    private boolean listvisible;
    private JLabel statuslabel;
    boolean updated;
    ArrayList<Integer> steplength;
    private JTabbedPane tabs;
    private int tabind;
    private int graphind;
    public TabbedScrollGraphPlotter(){
        this("ScrollGraphPlotter");
    }
    public TabbedScrollGraphPlotter(String title){
        updated=false;
        iniw=400;
        inih=400;
        listflag=false;
        listvisible=true;
        desktop=new JDesktopPane();
        desktop.setBackground(new Color(234,224,213));
        desktop.setDesktopManager(new MyDesktopManager());
        gpkey = new TSGPKeyEvent(this);
        map=new HashMap<String, TGPListPanel>();
        map2=new HashMap<String, ArrayList<GPInterface>>();
        
        //statuslabelのセット
        statuslabel=new JLabel();
        Font font=new Font("KonatuTohaba",0,10);
        statuslabel.setFont(font);

        this.getContentPane().add(desktop, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.setLocation(10, 10);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.gray);
        this.setTitle(title);
        this.setVisible(true);
        this.updateList();
        updated=true;
//        sg.addSGPEventListener(this);
//        this.setGraph("基準グラフ", sg);
//        steplength=sg.getLength();
    }
    public final void updateList(){
        if(updated){
            desktop.remove(list);
        }
        listflag = false;
        tabs = new JTabbedPane();
        Dimension size = new Dimension();
        for (String key : map.keySet()) {
            TGPListPanel p = map.get(key);
//            System.out.println(key);
            tabs.addTab(key, p);
            p.updateListPanel();
            Dimension psize = p.getListSize();
            if (size.width < psize.width) {
                size.setSize(psize.width, size.height);
            }
            if (size.height < psize.height) {
                size.setSize(size.width, psize.height);
            }
        }
//        System.out.println(lp.getListSize().width+"\t"+lp.getListSize().height);
        list=new JInternalFrame();
        list.setSize(size.width,size.height+40);
        list.getContentPane().add(tabs);
        list.setLocation(0, 300);
        list.setTitle("グラフリスト");
        list.setMaximizable(false);
        list.setResizable(false);
        list.setClosable(false);
        list.setIconifiable(true);
        list.setVisible(listvisible);
        desktop.add(list, Integer.valueOf(JLayeredPane.MODAL_LAYER+1));
    }
    public void setGraph(String tubname,String GraphName,GPInterface graph){
//        System.out.println("setGraph");
        if (map.containsKey(tubname) == false) {
            return;
        }
        graph.setStatuslabel(statuslabel);
        map.get(tubname).setGraph(GraphName, graph);
        map2.get(tubname).add(graph);
        this.updateList();
    }
    public void setCriterionGraph(String tubname,StepCriterionGraph sg){
//        System.out.println("setGraph");
        if (map.containsKey(tubname) == false) {
            TGPListPanel p = new TGPListPanel(this, false);
            map.put(tubname, p);
            map2.put(tubname,new ArrayList<GPInterface>());
        }
        sg.setStatuslabel(statuslabel);
        sg.addSGPEventListener(this);
        map.get(tubname).setGraph("Criterion: "+tubname, sg);
        map2.get(tubname).add(sg);
        this.updateList();
    }
    public void actionPerformed(ActionEvent e){
        for (String key : map.keySet()) {
            TGPListPanel p = map.get(key);
            p.actionPerformed(e);
            if (e.getSource() == p.getGraphInfo()) {
                boolean flag = p.GraphInfoClicked();
                for (String key2 : map.keySet()) {
                    if (!key2.equals(key)) {
                        map.get(key2).setGraphInfoClicked(flag);
                    }
                }
            }
        }
    }
    public void NextTab(){
        tabind++;
        if(tabind==tabs.getTabCount()){
            tabind=0;
        }
        tabs.setSelectedIndex(tabind);
    }
    public void NextGraph(){
        graphind++;
        for (String key : map.keySet()) {
            if(graphind==map.get(key).getGraphCount())graphind=0;
        }
//        if(graphind==-1)graphind=0;
        for (String key : map.keySet()) {
            TGPListPanel p=map.get(key);
            p.GraphVisibleAt(graphind);
        }
        this.ArrangePanel(2);
    }
    public void PreviousTab(){
        tabind--;
        if(tabind==-1){
            tabind=tabs.getTabCount()-1;
        }
        tabs.setSelectedIndex(tabind);
    }
    public void PreviousGraph(){
        graphind--;
        for (String key : map.keySet()) {
            if(graphind<=-1)graphind=map.get(key).getGraphCount()-1;
            if(graphind>=map.get(key).getGraphCount())graphind=map.get(key).getGraphCount()-1;
        }
        for (String key : map.keySet()) {
            TGPListPanel p=map.get(key);
            p.AllOff();
            p.GraphVisibleAt(graphind);
        }
        this.ArrangePanel(2);
    }
    public void ArrangePanel(int row){
//        int row=2;
        ArrayList<JInternalFrame> active = new ArrayList<JInternalFrame>();

        for (String key : map.keySet()) {
            JInternalFrame[] iframes = map.get(key).getActiveFrame();
            active.addAll(Arrays.asList(iframes));
        }
        int dw=desktop.getWidth();
        int dh=desktop.getHeight();
        int num=active.size();
        int sh;
        if(num%row==0){
            sh=active.size()/row;
        }else{
            sh=active.size()/row+1;
        }
        for(int i=0;i<row;i++){
            for(int j=0;j<sh;j++){
                if(j+sh*i<active.size()){
                    active.get(j+sh*i).setLocation(j*(dw/sh), i*(dh/row));
                    active.get(j+sh*i).setSize(dw/sh, dh/row);
                }
            }
        }
    }
    public void screenshot(String pass){
        BufferedImage readImage = null;
        if (readImage == null){
          readImage = new BufferedImage(this.getContentPane().getWidth(), this.getContentPane().getHeight(),
            BufferedImage.TYPE_INT_BGR);
        }
        Graphics2D g2 = readImage.createGraphics();
        this.getContentPane().paint(g2);
        try {
            File file=new File(pass);
            boolean result =ImageIO.write(readImage, "png", file);
            System.out.println("SCREENSHOT DUMPED→"+file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void setScreenShotFilePath(String path){
        gpkey.setScreenShotFile(path);
    }
    public void setGraphListVisible(){
        if(listvisible){
            listvisible=false;
        }else{
            listvisible=true;
        }
        list.setVisible(listvisible);
    }
    public void StepUpdated(SGPEvent e){
        int step=e.getStepNumber();
        System.out.println(e.getSource().toString());
        StepCriterionGraph scg=e.getCriterionGraph();
        
        for (String key : map2.keySet()) {
            ArrayList<GPInterface> graphs=map2.get(key);
            if(graphs.get(0).equals(scg)){
                for(int i=0;i<graphs.size(); i++) {
                    if (graphs.get(i) instanceof StepGraph) {
                        ((StepGraph) graphs.get(i)).update(step);
                    } else if (graphs.get(i) instanceof StepSeriesGraph) {
                        ((StepSeriesGraph) graphs.get(i)).update(step);
                    }  else if (graphs.get(i) instanceof StepScrollGraph) {
                        ((StepScrollGraph) graphs.get(i)).update(step);
                    }
                }
            }
        }
//        System.out.println(step);
    }
    class MyDesktopManager extends DefaultDesktopManager{
        @Override
        public void endDraggingFrame(JComponent f) {
            if(!f.equals(list)){
                super.endDraggingFrame(f);
                int right=f.getX();
                int left=f.getX()+f.getWidth();
                if(right<0){
                    f.setLocation(0, 0);
                    f.setSize(desktop.getWidth()/2, desktop.getHeight());
                }else if(left>desktop.getWidth()){
                    f.setLocation(desktop.getWidth()/2, 0);
                    f.setSize(desktop.getWidth()/2, desktop.getHeight());
                }
            }
        }
        public void minimizeFrame(JInternalFrame f){
            f.setSize(iniw, inih);
        }
    }
    public JLabel getStatusLabel(){
        return statuslabel;
    };
    public void ApplyFrame(JInternalFrame internalframe){
        desktop.add(internalframe);
    }
}
