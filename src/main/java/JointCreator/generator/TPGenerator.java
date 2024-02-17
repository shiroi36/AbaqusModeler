/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.BID;
import JointCreator.ID.CTPID;
import JointCreator.ID.TPID;
import java.io.File;
import util.LineInfo;
import util.section.HSection;
import util.coord.CoordXYZ;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class TPGenerator implements Generator{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        HSection hsec=new HSection(400,200,9,16,13);
//        BID bid=new BID(hsec,1000);
//        CTPGenerator a=new CTPGenerator("dxf/",ctpid);
//        a.draw();
    }
    
    private String path;
    private TPID tpid;
    
    
    public TPGenerator(String outputpath,TPID bid) {
        this.path=outputpath;
        this.tpid=bid;
    }
    public TPGenerator(TPID bid) {
        this.tpid=bid;
    }
    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    
    public String getname(){
        return "not implemented yet";
    }
    
    public void draw(){
        //head.txtの読み込み
        int n=3;
        LineInfo[] line=tpid.getLineInfo();
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        double t=tpid.getThickness();
        CoordXYZ[] pos=tpid.etCircle();
        double phi=tpid.getBoltType()+2;
        
        DXFDrawer dxf=new DXFDrawer(path+"tp.dxf");
        dxf.setHeader();
        for (int i = 0; i < line.length; i++) {
            LineInfo line1 = line[i];
            dxf.drawLine3D("0", line1.getStartPoint(), line1.getEndPoint(), direc, t);
        }
        for (int i = 0; i < pos.length; i++) {
            CoordXYZ po = pos[i];
            dxf.drawCircle3D("0", po, phi/2, direc, t);
        }
        dxf.setEnd();
        dxf.Finish();
        
    }
    
    
}
