/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;

/**
 *
 * @author keita
 */
public class SheetSize {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SheetSize a=new SheetSize();
    }

    public SheetSize(){
        double w=420;
        double h=297;
        CoordXYZ p0=new CoordXYZ(-w/2,h/2,0);
        CoordXYZ p1=new CoordXYZ(-w/2,-h/2,0);
        CoordXYZ p2=new CoordXYZ(w/2,h/2,0);
        CoordXYZ p3=new CoordXYZ(w/2,-h/2,0);
        double mag=50;
        p0.multiply(mag);
        p1.multiply(mag);
        p2.multiply(mag);
        p3.multiply(mag);
        DXFDrawer dxf=new DXFDrawer("dxf/A3_mag"+mag+".dxf");
        dxf.setHeader();
        dxf.drawLine3D("0", p0, p1);
        dxf.drawLine3D("0", p0, p2);
        dxf.drawLine3D("0", p2, p3);
        dxf.drawLine3D("0", p1, p3);
        dxf.setEnd();
        dxf.Finish();
        
    }

}
