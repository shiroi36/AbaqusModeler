/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;


import java.awt.Dimension;

/**
 *
 * @author ななか
 */
public class ListPanel extends JPanel {

    ArrayList<JInternalFrame> iframe;
    ArrayList<JToggleButton> button;
    JButton on, off;
    JToggleButton graphinfo;
    JInternalFrame graphinfoframe;
    boolean graphinfoflag;
    abstractGraphPlotter gp;
    int col;
    Dimension d;

    public ListPanel(abstractGraphPlotter gp) {
        this(gp,true);
    }
    public ListPanel(abstractGraphPlotter gp,boolean graphinfoflag) {
        col = 4;
        iframe = new ArrayList<JInternalFrame>();
        button = new ArrayList<JToggleButton>();
        this.gp = gp;
        on = new JButton("ALL-ON");
        off = new JButton("ALL-OFF");
        on.addActionListener(this.gp);
        off.addActionListener(this.gp);
        this.graphinfoflag=graphinfoflag;
        graphinfo = new JToggleButton("GRAPH_INFO");
        if (graphinfoflag) {
            graphinfoframe = new JInternalFrame("GRAPH_INFO");
            graphinfoframe.setSize(300, 300);
            graphinfoframe.setLocation(0, 0);
            graphinfoframe.setMaximizable(false);
            graphinfoframe.setResizable(true);
            graphinfoframe.setClosable(false);
            graphinfoframe.setIconifiable(false);
            graphinfoframe.setVisible(false);
            graphinfoframe.getContentPane().add(this.gp.getStatusLabel(), BorderLayout.CENTER);
            this.gp.ApplyFrame(graphinfoframe);
            graphinfo.addActionListener(this.gp);
        }
    }

    public void setGraph(String graphname, GPInterface graph) {
        JInternalFrame iframe2 = new JInternalFrame(graphname);
        iframe2.setSize(300, 300);
        iframe2.setLocation(0, 0);
        iframe2.setMaximizable(true);
        iframe2.setResizable(true);
        iframe2.setClosable(false);
        iframe2.setIconifiable(false);
        iframe2.setVisible(false);
        iframe2.getContentPane().add(graph.getPanel(), BorderLayout.CENTER);
        gp.ApplyFrame(iframe2);
        iframe.add(iframe2);
//            this.name.add(graphname);
        JToggleButton button = new JToggleButton(graphname);
        button.addActionListener(gp);
        this.button.add(button);
    }

    public AbstractButton[] getPanelButton() {
        AbstractButton[] b = new AbstractButton[button.size() + 3];
        b[0] = on;
        b[1] = off;
        b[2] = graphinfo;
        for (int i = 3; i < button.size(); i++) {
            b[3 + i] = button.get(i);
        }
        return b;
    }

    public int getCollumn() {
        return col;
    }

    public int getRow() {
        int row = (button.size() + 3) / col;
        if ((button.size() + 3) % 4 > 0) {
            row++;
        }
        return row;
    }

    public void updateListPanel() {
        int r, c;
        c = this.getCollumn();
        r = this.getRow();
//        System.out.println(c+"\t"+r);
        d = new Dimension(200 * c, 30 * (r + 1));
        this.setSize(d);
        this.setLayout(new GridLayout(0, c));
        this.add(on);
        this.add(off);
        this.add(graphinfo);
        for (int i = 0; i < button.size(); i++) {
            this.add(button.get(i));
        }
    }

    public Dimension getListSize() {
        return d;
    }

    public JInternalFrame[] getActiveFrame() {
        ArrayList<JInternalFrame> arr = new ArrayList<JInternalFrame>();
        if(graphinfo.isVisible()&&graphinfoflag){
            arr.add(graphinfoframe);
        }
        for (int i = 0; i < iframe.size(); i++) {
            if (iframe.get(i).isVisible()) {
                arr.add(iframe.get(i));
            }
        }
        JInternalFrame[] iframes = new JInternalFrame[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            iframes[i] = arr.get(i);
        }
        return iframes;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == on) {
            if (!graphinfo.isSelected()) {
                graphinfo.doClick();
            }
            for (int i = 0; i < button.size(); i++) {
                if (!iframe.get(i).isVisible()) {
                    button.get(i).doClick();
                }
                gp.ArrangePanel(2);
            }
            return;
        }
        if (e.getSource() == graphinfo) {
            if(graphinfoflag){
                if (graphinfo.isSelected()) {
                    graphinfoframe.setLocation(0, 0);
                    graphinfoframe.setVisible(true);
                } else {
                    graphinfoframe.setVisible(false);
                }
            }
            return;
        }
        if (e.getSource() == off) {
            if (graphinfo.isSelected()) {
                graphinfo.doClick();
            }
            for (int i = 0; i < button.size(); i++) {
                if (iframe.get(i).isVisible()) {
                    button.get(i).doClick();
                }
            }
            return;
        }
        for (int i = 0; i < button.size(); i++) {
            if (e.getSource() == button.get(i)) {
                if (button.get(i).isSelected()) {
                    iframe.get(i).setLocation(0, 0);
                    iframe.get(i).setVisible(true);
                } else {
                    iframe.get(i).setVisible(false);
                }
            }
        }
    }
}
