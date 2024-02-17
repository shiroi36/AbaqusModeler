/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP.SGP;

import java.util.EventListener;

/**
 *
 * @author ななか
 */
public interface SGPEventListener extends EventListener {
    public void StepUpdated(SGPEvent e);
}
