/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.GP;

import util.GRAPH_LIB.GP.GPKeyEvent;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.event.InternalFrameEvent;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JToggleButton;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultDesktopManager;
import javax.swing.JLayeredPane;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.Rectangle2D;
import java.awt.Graphics2D;
import java.util.Arrays;
import javax.swing.*;




/**
 *
 * @author araki keita
 */
public class GraphPlotter extends abstractGraphPlotter {
    private JDesktopPane desktop;
    private JInternalFrame list;
    private ListPanel lp;
//    ArrayList<JInternalFrame> iframe;
//    ArrayList<String> name;
//    ArrayList<JToggleButton> button;
//    JButton on,off;
    boolean listflag;
    int iniw,inih;
    private  GPKeyEvent gpkey;
    private boolean listvisible;
    private JLabel statuslabel;
    boolean updated;
    public GraphPlotter(String[] graphname,GPInterface[] graph){
        this("GraphPlotter");
        for(int i=0;i<graph.length;i++){
            graph[i].setStatuslabel(statuslabel);
            lp.setGraph(graphname[i], graph[i]);
        }
    }
    public GraphPlotter(){
        this("GraphPlotter");
    }
    public GraphPlotter(String title){
        updated=false;
        iniw=400;
        inih=400;
        listflag=false;
        listvisible=true;
        desktop=new JDesktopPane();
        desktop.setBackground(new Color(234,224,213));
        desktop.setDesktopManager(new MyDesktopManager());
        gpkey = new GPKeyEvent(this);
        
        //statuslabelのセット
        statuslabel=new JLabel();
        Font font=new Font("KonatuTohaba",0,10);
        statuslabel.setFont(font);
        //listpanelのセット
        lp=new ListPanel(this);

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
    }
    public final void updateList(){
        if(updated){
            desktop.remove(list);
        }
        listflag=false;
//        System.out.println("initlist");
        lp.updateListPanel();
//        System.out.println(lp.getListSize().width+"\t"+lp.getListSize().height);
        list=new JInternalFrame();
        list.setSize(lp.getListSize());
        list.getContentPane().add(lp);
        list.setLocation(0, 300);
        list.setTitle("グラフリスト");
        list.setMaximizable(false);
        list.setResizable(false);
        list.setClosable(false);
        list.setIconifiable(true);
        list.setVisible(listvisible);
        desktop.add(list, Integer.valueOf(JLayeredPane.MODAL_LAYER+1));
    }
    public void setGraph(String GraphName,GPInterface graph){
//        System.out.println("setGraph");
        graph.setStatuslabel(statuslabel);
        lp.setGraph(GraphName, graph);
        this.updateList();
    }
    public void actionPerformed(ActionEvent e){
        lp.actionPerformed(e);
    }
    public void ArrangePanel(int row){
//        int row=2;
        ArrayList<JInternalFrame> active=new ArrayList<JInternalFrame>();
        JInternalFrame[] iframes=lp.getActiveFrame();
        active.addAll(Arrays.asList(iframes));
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
