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
public final class CID {
    
    public static String ICON_C="c";
    private final HSection hsec;
    private final double lb;
    

    
    
    public CID(HSection hsec,double lb) {
       this.hsec=hsec;
       this.lb=lb;
    }
    
    public double getLength(){return lb;}
    public HSection getSection(){return hsec;}
    
}
