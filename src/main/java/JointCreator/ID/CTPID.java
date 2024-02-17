/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.ID;
import util.LineInfo;
import util.coord.CoordXYZ;
import util.section.HSection;

/**
 *
 * @author keita
 */
public final class CTPID {
    
    public static String ICON_CTP="ctp";
    private final double t;
    private final double h;
    private final double bolttype;
    

    
    
    public CTPID(double t,double h,double bolttype) {
        this.t=t;
        this.h=h;
        this.bolttype=bolttype;
    }
    
    public double getThickness(){return t;}
    public double getBoltType(){return bolttype;}
    public LineInfo[] getLineInfo(){
        LineInfo[] li=new LineInfo[]{
            new LineInfo(new CoordXYZ(h/2+15,175,0),new CoordXYZ(-h/2-15,175,0)),
            new LineInfo(new CoordXYZ(h/2+15,-175,0),new CoordXYZ(-h/2-15,-175,0)),
            new LineInfo(new CoordXYZ(-h/2-15,-175,0),new CoordXYZ(-h/2-15,175,0)),
            new LineInfo(new CoordXYZ(h/2+15,175,0),new CoordXYZ(h/2+15,-175,0)),
        };
        return li;
    }
    public CoordXYZ[] etCircle(){
        CoordXYZ[] pos=new CoordXYZ[]{
            new CoordXYZ(30,75,0),
            new CoordXYZ(30,135,0),
            new CoordXYZ(30+60,75,0),
            new CoordXYZ(30+60,135,0),
            new CoordXYZ(30+60*2,75,0),
            new CoordXYZ(30+60*2,135,0),
            new CoordXYZ(-30,75,0),
            new CoordXYZ(-30,135,0),
            new CoordXYZ(-30-60,75,0),
            new CoordXYZ(-30-60,135,0),
            new CoordXYZ(-30-60*2,75,0),
            new CoordXYZ(-30-60*2,135,0),
            new CoordXYZ(30,-75,0),
            new CoordXYZ(30,-135,0),
            new CoordXYZ(30+60,-75,0),
            new CoordXYZ(30+60,-135,0),
            new CoordXYZ(30+60*2,-75,0),
            new CoordXYZ(30+60*2,-135,0),
            new CoordXYZ(-30,-75,0),
            new CoordXYZ(-30,-135,0),
            new CoordXYZ(-30-60,-75,0),
            new CoordXYZ(-30-60,-135,0),
            new CoordXYZ(-30-60*2,-75,0),
            new CoordXYZ(-30-60*2,-135,0),
        };
        return pos;
    }
    
}
