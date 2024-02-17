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
public final class TPID {
    
    public static String ICON_CTP="ctp";
    private final double t;
    private final double h;
    private final double bolttype;
    private final double c;
    private final double b;
    private final double bk;
    

    
    
    public TPID(double t,double h,double bolttype,double c,double b,double bk) {
        this.t=t;
        this.h=h;
        this.c=c;
        this.b=b;
        this.bk=bk;
        this.bolttype=bolttype;
    }
    
    public double getThickness(){return t;}
    public double getBoltType(){return bolttype;}
    public LineInfo[] getLineInfo(){
        LineInfo[] li=new LineInfo[]{
            new LineInfo(new CoordXYZ(h/2+15,175,0),new CoordXYZ(-h/2-15,175,0)),
            new LineInfo(new CoordXYZ(h/2+15,-175,0),new CoordXYZ(-h/2-15,-175,0)),
            new LineInfo(new CoordXYZ(h/2+15,175,0),new CoordXYZ(h/2+15+c+340,b/2,0)),
            new LineInfo(new CoordXYZ(h/2+15,-175,0),new CoordXYZ(h/2+15+c+340,-b/2,0)),
            new LineInfo(new CoordXYZ(-h/2-15,175,0),new CoordXYZ(-h/2-15-c-340,b/2,0)),
            new LineInfo(new CoordXYZ(-h/2-15,-175,0),new CoordXYZ(-h/2-15-c-340,-b/2,0)),
            new LineInfo(new CoordXYZ(h/2+15+c+340,b/2,0),new CoordXYZ(h/2+15+c+340,-b/2,0)),
            new LineInfo(new CoordXYZ(-(h/2+15+c+340),b/2,0),new CoordXYZ(-(h/2+15+c+340),-b/2,0)),
        };
        return li;
    }
    public CoordXYZ[] etCircle(){
        double c1=h/2+c+15;
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
            
            
            new CoordXYZ(c1,bk/2,0),
            new CoordXYZ(c1,-bk/2,0),
            new CoordXYZ(c1+60,bk/2,0),
            new CoordXYZ(c1+60,-bk/2,0),
            new CoordXYZ(c1+60*2,bk/2,0),
            new CoordXYZ(c1+60*2,-bk/2,0),
            new CoordXYZ(c1+60*3,bk/2,0),
            new CoordXYZ(c1+60*3,-bk/2,0),
            new CoordXYZ(c1+60*4,bk/2,0),
            new CoordXYZ(c1+60*4,-bk/2,0),
            new CoordXYZ(c1+60*5,bk/2,0),
            new CoordXYZ(c1+60*5,-bk/2,0),
            
            new CoordXYZ(-c1,bk/2,0),
            new CoordXYZ(-c1,-bk/2,0),
            new CoordXYZ(-c1-60,bk/2,0),
            new CoordXYZ(-c1-60,-bk/2,0),
            new CoordXYZ(-c1-60*2,bk/2,0),
            new CoordXYZ(-c1-60*2,-bk/2,0),
            new CoordXYZ(-c1-60*3,bk/2,0),
            new CoordXYZ(-c1-60*3,-bk/2,0),
            new CoordXYZ(-c1-60*4,bk/2,0),
            new CoordXYZ(-c1-60*4,-bk/2,0),
            new CoordXYZ(-c1-60*5,bk/2,0),
            new CoordXYZ(-c1-60*5,-bk/2,0),
            
            
        };
        return pos;
    }
    
}
