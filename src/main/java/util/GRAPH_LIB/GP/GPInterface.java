/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.GP;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author keita
 */
public interface GPInterface {
    public JPanel getPanel();
    public void setStatuslabel(JLabel label);
    public String getName();
}
