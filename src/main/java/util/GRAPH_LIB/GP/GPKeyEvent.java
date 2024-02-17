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
public class GPKeyEvent {
    abstractGraphPlotter gp;
    int filenum;
    String filepass;
    public GPKeyEvent(abstractGraphPlotter gp){
        this.gp=gp;
        filenum=0;
        filepass="shot";
        InputMap imap=gp.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap amap=gp.getRootPane().getActionMap();

        Action[] keyevent={new Arrange1(),new Arrange2(),new Arrange3(),new Arrange4(),new screenshot(),new listvisible()};
        int[] keycode={KeyEvent.VK_1,KeyEvent.VK_2,KeyEvent.VK_3,KeyEvent.VK_4,KeyEvent.VK_P,KeyEvent.VK_L};
        for(int i=0;i<keyevent.length;i++){
            imap.put(KeyStroke.getKeyStroke(keycode[i], 0), keyevent[i]);
            amap.put(keyevent[i], keyevent[i]);
        }
    }
    class Arrange1 extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            gp.ArrangePanel(1);
            System.out.println("pressA");
        }
    }
    class Arrange2 extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            gp.ArrangePanel(2);
            System.out.println("pressA");
        }
    }
    class Arrange3 extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            gp.ArrangePanel(3);
            System.out.println("pressA");
        }
    }
    class Arrange4 extends AbstractAction{
        public void actionPerformed(ActionEvent e){
           gp.ArrangePanel(4);
            System.out.println("pressA");
        }
    }
    public void setScreenShotFile(String pass){
        filepass=pass;
    }
    class screenshot extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            gp.screenshot(filepass+filenum+".png");
        }
    }
    class listvisible extends AbstractAction{
        public void actionPerformed(ActionEvent e){
            gp.setGraphListVisible();
        }
    }
}
