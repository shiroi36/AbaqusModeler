/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import util.dxf.DXFDrawer;
import JointCreator.ID.BID;
import JointCreator.ID.CTPID;
import java.io.File;
import util.LineInfo;
import util.section.HSection;
import util.coord.CoordXYZ;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class CTPGenerator implements Generator{

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
    private CTPID ctpid;
    
    
    public CTPGenerator(String outputpath,CTPID bid) {
        this.path=outputpath;
        this.ctpid=bid;
    }
    public CTPGenerator(CTPID bid) {
        this.ctpid=bid;
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
        LineInfo[] line=ctpid.getLineInfo();
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        double t=ctpid.getThickness();
        CoordXYZ[] pos=ctpid.etCircle();
        double phi=ctpid.getBoltType()+2;
        
        DXFDrawer dxf=new DXFDrawer(path+"ctp.dxf");
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
