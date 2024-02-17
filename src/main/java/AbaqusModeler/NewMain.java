/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler;

import util.LineInfo;
import util.dxf.DXFDrawer;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DXFDrawer dxf=new DXFDrawer("test.dxf");
        HSection h=new HSection(596,199,10,15,13);
        LineInfo[] li=h.get2DShape(3);
        
        dxf.setHeader();
        for (int i = 0; i < li.length; i++) {
            LineInfo lineInfo = li[i];
            dxf.drawLine3D("0", lineInfo.getStartPoint(), lineInfo.getEndPoint());
        }
        dxf.setEnd();
        dxf.Finish();
    }
    
}
