/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import JointCreator.ID.BID;
import util.dxf.DXFDrawer;
import JointCreator.ID.CID;
import JointCreator.ID.CTPID;
import JointCreator.JointState;
import java.io.File;
import util.LineInfo;
import util.section.HSection;
import util.coord.CoordXYZ;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class ColumnGenerator implements Generator{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HSection hsec=new HSection(400,200,9,16,13);
        CID cid=new CID(hsec,3000);
        ColumnGenerator cg=new ColumnGenerator("dxf/",cid,null,null,JointState.INTERMEDIATE_X);
        cg.draw();
    }
    
    
    private static String path;
    private final CID cid;
    private final BID bid;
    private final JointState js;
    private final CTPID ctpid;
    
    
    public void setOutputPath(String outputpath){
        this.path=outputpath;
    }
    public ColumnGenerator(CID cid) {
        this("",cid,null,null,JointState.INTERMEDIATE_X);
    }
    public ColumnGenerator(String outputpath,CID cid,BID bid,CTPID ctpid,JointState js) {
        this.path=outputpath;
        this.bid=bid;
        this.cid=cid;
        this.js=js;
        this.ctpid=ctpid;
        
    }
    
    public String getname(){
        return cid.getSection().getName();
    }
    
    public void draw(){
        //head.txtの読み込み
//        this.drawLine2D("0", tw/2, -(h/2-tf-r), 0, 0)
        HSection sec=cid.getSection();
        int n=3;
        LineInfo[] line=sec.get2DShape(n);
        CoordXYZ direc=new CoordXYZ(0,0,-1);
        double lb=cid.getLength();
        DXFDrawer dxf=new DXFDrawer(path+"c.dxf");
        dxf.setHeader();
        CoordXYZ m=new CoordXYZ(0,0,0);
        if(js.isRF()){
            lb/=2;
            lb+=bid.getSection().getH()/2-ctpid.getThickness();
        }
        for (int i = 0; i < line.length; i++) {
            LineInfo line1 = line[i];
            dxf.drawLine3D("0", line1.getStartPoint(), line1.getEndPoint(), direc, lb);
        }
        dxf.setEnd();
        dxf.Finish();
        
    }
    
        
    
}
