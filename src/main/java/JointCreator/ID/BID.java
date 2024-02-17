/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.ID;
import util.section.HSection;

/**
 *
 * @author keita
 */
public final class BID {
    public static String ICON_B="b";
    
    private final HSection hsec;
    private final double lb;
    

    
    
    public BID(HSection hsec,double lb) {
       this.hsec=hsec;
       this.lb=lb;
    }
    
    public double getLength(){return lb;}
    public HSection getSection(){return hsec;}
    
}
