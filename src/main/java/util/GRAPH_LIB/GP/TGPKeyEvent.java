/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP;

import util.GRAPH_LIB.GP.abstractGraphPlotter;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JComponent;
import javax.swing.Action;
import javax.swing.JFrame;

/**
 *
 * @author araki keita
 */
public class TGPKeyEvent {

    TabbedGraphPlotter gp;
    int filenum;
    String filepass;

    public TGPKeyEvent(TabbedGraphPlotter gp) {
        this.gp = gp;
        filenum = 0;
        filepass = "shot";
        InputMap imap = gp.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap = gp.getRootPane().getActionMap();

        Action[] keyevent = {new Arrange1(), new Arrange2(), new Arrange3(),
            new Arrange4(), new screenshot(), new listvisible(), new NextTab(),
            new PreviousTab(), new NextGraph(), new PreviousGraph()};
        int[] keycode = {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3, KeyEvent.VK_4,
            KeyEvent.VK_P, KeyEvent.VK_L, KeyEvent.VK_Q, KeyEvent.VK_W,
            KeyEvent.VK_R, KeyEvent.VK_E};
        for (int i = 0; i < keyevent.length; i++) {
            imap.put(KeyStroke.getKeyStroke(keycode[i], 0), keyevent[i]);
            amap.put(keyevent[i], keyevent[i]);
        }
    }

    class Arrange1 extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.ArrangePanel(1);
        }
    }

    class Arrange2 extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.ArrangePanel(2);
        }
    }

    class Arrange3 extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.ArrangePanel(3);
        }
    }

    class Arrange4 extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.ArrangePanel(4);
        }
    }

    class NextTab extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.NextTab();
        }
    }

    class NextGraph extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.NextGraph();
        }
    }

    class PreviousTab extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.PreviousTab();
        }
    }

    class PreviousGraph extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.PreviousGraph();
        }
    }

    public void setScreenShotFile(String pass) {
        filepass = pass;
    }

    class screenshot extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.screenshot(filepass + filenum + ".png");
        }
    }

    class listvisible extends AbstractAction {

        public void actionPerformed(ActionEvent e) {
            gp.setGraphListVisible();
        }
    }
}
