/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler.model.jsce3;

import JointCreator.ID.KPID;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class study2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HSection[] bsec={
            new HSection(600,300,12,17),
            new HSection(600,300,12,14),
            new HSection(600,300,12,12),
        };
        double[] bmat={
            235,
            235,
            235,
        };
        System.out.println(Math.sqrt(235.0/325.0));
        
        KPID[] at={
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
            KPID.C490_N22_6M22,
        };
        double[] kmat={
            325,
            325,
            325,
        };
        for (int i = 0; i < bsec.length; i++) {
            HSection bsec1 = bsec[i];
            double bmat1=bmat[i];
            double Mby=bsec1.getZex()*bmat1;
            KPID at1=at[i];
            double kmat1=kmat[i];
            double dk=bsec1.getH()+2*at1.gettk2()+at1.gethk();
            double Mky=dk*at1.gettk1()*2*at1.gethk()*kmat1;
            System.out.println(bsec1.getName());
            System.out.println("Mby = " + Mby);
            System.out.println("Mky = " + Mky);
            System.out.println("b/t_flange = " + bsec1.getB()/2/bsec1.getFlangeThickness());
            System.out.println("b/t_web = " + bsec1.getH()/bsec1.getWebThickness());
            System.out.println(Mky/Mby+"");
            System.out.println("---------------------------------");
        }
        
    }
    
}
