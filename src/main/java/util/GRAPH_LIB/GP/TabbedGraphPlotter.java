/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP;

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
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 *
 * @author araki keita
 */
public final class TabbedGraphPlotter extends abstractGraphPlotter {

    private JDesktopPane desktop;
    private JInternalFrame list;
//    private ListPanel lp;
    private HashMap<String, TGPListPanel> map;
    boolean listflag;
    int iniw, inih;
    private TGPKeyEvent gpkey;
    private boolean listvisible;
    private static JLabel statuslabel;
    boolean updated;
    boolean graphinfoflag;
    boolean graphflag;
    int[] keys;
    private JTabbedPane tabs;
    private int tabind;
    private int graphind;

    public TabbedGraphPlotter() {
        this("TabbedGraphPlotter");
    }
    public TabbedGraphPlotter(String title) {

        //初期化
        this.init();

        this.getContentPane().add(desktop, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocation(10, 10);
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.getContentPane().setBackground(Color.gray);
        this.setTitle(title);
        this.setVisible(true);
        this.updateList();
        updated = true;
    }

    public void init() {
        updated = false;
        graphind=-1;
        tabind=0;
        iniw = 400;
        inih = 400;
        listflag = false;
        listvisible = true;
        desktop = new JDesktopPane();
        desktop.setBackground(new Color(234, 224, 213));
        desktop.setDesktopManager(new MyDesktopManager());
        gpkey = new TGPKeyEvent(this);
        graphinfoflag = true;
        //statuslabelのセット
        statuslabel = new JLabel();
        Font font = new Font("KonatuTohaba", 0, 10);
        statuslabel.setFont(font);
        //Hashmapの初期化
        map = new HashMap<String, TGPListPanel>();
        keys = new int[]{KeyEvent.VK_A, KeyEvent.VK_S, KeyEvent.VK_D, KeyEvent.VK_F,
            KeyEvent.VK_G, KeyEvent.VK_H, KeyEvent.VK_J, KeyEvent.VK_K, KeyEvent.VK_L,
            KeyEvent.VK_Z, KeyEvent.VK_X, KeyEvent.VK_C, KeyEvent.VK_V, KeyEvent.VK_B,
            KeyEvent.VK_N, KeyEvent.VK_M};
    }

    public final void updateList() {
        if (updated) {
            desktop.remove(list);
        }
        listflag = false;
        tabs = new JTabbedPane();
        Dimension size = new Dimension();
        for (String key : map.keySet()) {
            TGPListPanel p = map.get(key);
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
//        System.out.println(tabs.getSelectedIndex());
        list = new JInternalFrame();
        list.setSize(size.width, size.height + 40);
        list.setResizable(false);
        list.add(tabs);
        list.setLocation(0, 300);
        list.setTitle("グラフリスト");
        list.setMaximizable(false);
        list.setClosable(false);
        list.setIconifiable(true);
        list.setVisible(listvisible);
        desktop.add(list, Integer.valueOf(JLayeredPane.MODAL_LAYER + 1));
    }

    public void setGraph(String tubname, String GraphName, GPInterface graph) {
//        System.out.println("setGraph");
        if (map.containsKey(tubname) == false) {
            TGPListPanel p = new TGPListPanel(this, graphinfoflag);
            map.put(tubname, p);
            graphinfoflag = false;
        }
        graph.setStatuslabel(statuslabel);
        map.get(tubname).setGraph(GraphName, graph);
        this.updateList();
    }

    public void actionPerformed(ActionEvent e) {
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

    public void ArrangePanel(int row) {
//        int row=2;
        ArrayList<JInternalFrame> active = new ArrayList<JInternalFrame>();

        for (String key : map.keySet()) {
            JInternalFrame[] iframes = map.get(key).getActiveFrame();
            active.addAll(Arrays.asList(iframes));
        }
        int dw = desktop.getWidth();
        int dh = desktop.getHeight();
        int num = active.size();
        int sh;
        if (num % row == 0) {
            sh = active.size() / row;
        } else {
            sh = active.size() / row + 1;
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < sh; j++) {
                if (j + sh * i < active.size()) {
                    active.get(j + sh * i).setLocation(j * (dw / sh), i * (dh / row));
                    active.get(j + sh * i).setSize(dw / sh, dh / row);
                }
            }
        }
    }

    public void screenshot(String pass) {
        BufferedImage readImage = null;
        if (readImage == null) {
            readImage = new BufferedImage(this.getContentPane().getWidth(), this.getContentPane().getHeight(),
                    BufferedImage.TYPE_INT_BGR);
        }
        Graphics2D g2 = readImage.createGraphics();
        this.getContentPane().paint(g2);
        try {
            File file = new File(pass);
            boolean result = ImageIO.write(readImage, "png", file);
            System.out.println("SCREENSHOT DUMPED→" + file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setScreenShotFilePath(String path) {
        gpkey.setScreenShotFile(path);
    }

    public void setGraphListVisible() {
        if (listvisible) {
            listvisible = false;
        } else {
            listvisible = true;
        }
        list.setVisible(listvisible);
    }

    class MyDesktopManager extends DefaultDesktopManager {

        @Override
        public void endDraggingFrame(JComponent f) {
            if (!f.equals(list)) {
                super.endDraggingFrame(f);
                int right = f.getX();
                int left = f.getX() + f.getWidth();
                if (right < 0) {
                    f.setLocation(0, 0);
                    f.setSize(desktop.getWidth(), desktop.getHeight());
                } else if (left > desktop.getWidth()) {
                    f.setLocation(desktop.getWidth() / 2, 0);
                    f.setSize(desktop.getWidth() / 2, desktop.getHeight());
                }
            }
        }

        public void minimizeFrame(JInternalFrame f) {
            f.setSize(iniw, inih);
        }
    }

    public JLabel getStatusLabel() {
        return statuslabel;
    }

    ;
    public void ApplyFrame(JInternalFrame internalframe) {
        desktop.add(internalframe);
    }
}
