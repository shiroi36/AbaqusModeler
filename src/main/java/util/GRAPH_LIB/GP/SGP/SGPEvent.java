/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.GP.SGP;

import java.util.EventObject;

/**
 *
 * @author ななか
 */
public class SGPEvent extends EventObject{
    int step;
    StepCriterionGraph scg;
    public SGPEvent(int step,StepCriterionGraph scg){
        super(step);
        this.step=step;
        this.scg=scg;
    }
    public SGPEvent(int step){
        super(step);
        this.step=step;
        this.scg=null;
    }
    public int getStepNumber(){
        return step;
    }
    public StepCriterionGraph getCriterionGraph(){return scg;}
}
