/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.BID;
import java.io.File;
import util.LineInfo;
import util.section.HSection;
import util.coord.CoordXYZ;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class BeamGenerator implements Generator{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HSection hsec=new HSection(294,302,12,12,13);
//        HSection hsec=new HSection(294,200,8,12,13);
//        HSection hsec=new HSection(175,90,5,8,8);
        BID bid=new BID(hsec,1000);
        BeamGenerator a=new BeamGenerator("dxf/",bid);
        a.draw();
    }
    
    private String path;
    private BID bid;
    
    
    public BeamGenerator(String outputpath,BID bid) {
        this.path=outputpath;
        this.bid=bid;
    }
    public BeamGenerator(BID bid) {
        this.bid=bid;
    }
    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public String getname(){
        return bid.getSection().getName();
    }
    
    public void draw(){
        //head.txtの読み込み
        HSection sec=bid.getSection();
        int n=3;
        LineInfo[] line=sec.get2DShape(n);
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        double lb=bid.getLength();
        DXFDrawer dxf=new DXFDrawer(path+sec.getName()+".dxf");
        dxf.setHeader();
        for (int i = 0; i < line.length; i++) {
            LineInfo line1 = line[i];
            dxf.drawLine3D("0", line1.getStartPoint(), line1.getEndPoint(), direc, lb);
        }
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    
}
