/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.Modeler;

import AbaqusModeler.*;
import JointCreator.ID.KPID;
import java.io.File;
import static org.mozilla.javascript.ScriptRuntime.name;
import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class KPModeler {
    
    public static void main(String[] args) {
        KPID kpid=KPID.C400_N25_6M22;
        HSection bsec=new HSection(600,200,9,19);
        double ts=12;
    }
    private String pnkpl;
    private String pnkpr;

    /**
     * @param args the command line arguments
     */


    public KPModeler(KPID kpid){
//        int h=550;
//        int h=(int)bsec.getH();
        double tk1= kpid.gettk1();
        if (tk1 == 16) {
            this.pnkpl = "n16l";
            this.pnkpr = "n16r";
        } else if (tk1 == 19) {
            this.pnkpl = "n19l";
            this.pnkpr = "n19r";
        }  else if (tk1 == 22) {
            this.pnkpl = "n22l";
            this.pnkpr = "n22r";
        } else if (tk1 == 25) {
            this.pnkpl = "n25l";
            this.pnkpr = "n25r";
        } else if (tk1 == 28) {
            this.pnkpl = "r28l";
            this.pnkpr = "r28r";
        }
            
    }
    
    public String getKPLname(){return pnkpl;}
    public String getKPRname(){return pnkpr;}
    
}
