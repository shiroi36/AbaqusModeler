/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JointCreator.generator;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import util.section.HSection;
import util.io.TXT_OPE;
import util.coord.XY;

/**
 *
 * @author keita
 */
public final class HSectioGenerater {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        HSection hsec=new HSection(400,200,9,16,0);
        HSectioGenerater a=new HSectioGenerater(path+hsec.getName()+"_01.dxf",hsec);
        a.setHeader();
        a.DrawSection("0");
        a.setEnd();    
        a.Finish();
        a=new HSectioGenerater(path+hsec.getName()+"_02.dxf",hsec);
        a.setHeader();
        a.DrawStrongAxisElevation(path, 1000, XY.Y);
        a.setEnd();    
        a.Finish();
        a=new HSectioGenerater(path+hsec.getName()+"_03.dxf",hsec);
        a.setHeader();
        a.DrawWeakAxisElevation(path, 1000, XY.Y);
        a.setEnd();    
        a.Finish();
    }
    private final boolean debug;
    private final boolean debug2;
    private static String path="dxf\\core\\";
    private int hundlenum;
    private final TXT_OPE to;
    private final double h;
    private final double b;
    private final double tw;
    private final double tf;
    private final double r;
    
    
    public HSectioGenerater(String outputpath,HSection hsec) {
        this.h=hsec.getH();
        this.b=hsec.getB();
        this.tw=hsec.getWebThickness();
        this.tf=hsec.getFlangeThickness();
        this.r=hsec.getR();
        this.to=new TXT_OPE();
        to.setFileName(outputpath);
        debug=true;
        debug2=true;
        hundlenum=2000;//ハンドル番号、おそらく一個一個の要素につけていかないといけない気がする
         
        
//        this.viewHandlenum(to, path+"header.txt");
//        this.viewHandlenum(to, path+"N16_4M22.txt");
//        System.out.println("*************************");
//        this.viewHandlenum(to, path+"end.txt");
    }
    
    public HSectioGenerater(String outputpath,double h,double b,double tw,double tf,double r) {
        this.h=h;
        this.b=b;
        this.tw=tw;
        this.tf=tf;
        this.r=r;
        this.to=new TXT_OPE();
        to.setFileName(outputpath);
        debug=true;
        debug2=true;
        hundlenum=2000;//ハンドル番号、おそらく一個一個の要素につけていかないといけない気がする
         
        
//        this.viewHandlenum(to, path+"header.txt");
//        this.viewHandlenum(to, path+"N16_4M22.txt");
//        System.out.println("*************************");
//        this.viewHandlenum(to, path+"end.txt");
    }
    
    public void Finish(){to.finish();}
    
    public String drawLine2D(String layer, double sx, double sy, double gx, double gy) {
        hundlenum++;
        return "  0\n"+ "LINE\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "330\n"+ "1F\n"
                + "100\n"+ "AcDbEntity\n"
                + "  8\n"+ "0\n"
                + " 62\n"+ "     7\n"
                + " 48\n"+ "0.3\n"
                + "100\n"+ "AcDbLine\n"
                + " 10\n"+ sx+"\n"
                + " 20\n"+ sy+"\n"
                + " 30\n"+ "0.0\n"
                + " 11\n"+ gx+"\n"
                + " 21\n"+ gy+"\n"
                + " 31\n"+ "0.0";
    }
    
    public String drawCircle2D(String layer, double cx, double cy,double R) {
        hundlenum++;
        return "0\n"+ "CIRCLE\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "330\n"+ "1F\n"
                + "100\n"+ "AcDbEntity\n"
                + "  8\n"+ "0\n"
                + " 62\n"+ "     7\n"
                + "100\n"+ "AcDbCircle\n"
                + " 10\n"+ cx+"\n"
                + " 20\n"+ cy+"\n"
                + " 30\n"+ "0.0\n"
                + " 40\n"+ R;
    }
    
        public String drawArc2D(String layer, double cx, double cy, double R,double s,double g) {
        hundlenum++;
        return "  0\n"+ "ARC\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "330\n"+ "1F\n"
                + "100\n"+ "AcDbEntity\n"
                + "  8\n"+ "0\n"
                + " 62\n"+ "     7\n"
                + " 48\n"+ "2.0\n"
                + "100\n"+ "AcDbCircle\n"
                + " 10\n"+ cx+"\n"
                + " 20\n"+ cy+"\n"
                + " 30\n"+ "0.0\n"
                + " 40\n"+ R+"\n"
                + "100\n"+ "AcDbArc\n"
                + " 50\n"+ s+"\n"
                + " 51\n"+ g;
    }
    
    
    public void DrawSection(String layer){
        //head.txtの読み込み
//        this.drawLine2D(layer, tw/2, -(h/2-tf-r), 0, 0)
        to.println(this.drawLine2D(layer, tw/2, -(h/2-tf-r), tw/2, h/2-tf-r));
        to.println(this.drawLine2D(layer, -tw/2, -(h/2-tf-r), -tw/2, h/2-tf-r));
        
        to.println(this.drawArc2D(layer, tw/2+r, h/2-tf-r,r, 90, 180));
        to.println(this.drawArc2D(layer, tw/2+r, -(h/2-tf-r),r, 180, 270));
        to.println(this.drawArc2D(layer, -(tw/2+r), -(h/2-tf-r),r, 270, 360));
        to.println(this.drawArc2D(layer, -(tw/2+r), h/2-tf-r,r, 0, 90));
        
        double[][] a={{1,1},{-1,1},{1,-1},{-1,-1}};
        for (int i = 0; i < a.length; i++) {
            double[] a1 = a[i];
            to.println(this.drawLine2D(layer, a1[0]*(tw/2+r), a1[1]*(h/2-tf), a1[0]*b/2, a1[1]*(h/2-tf)));
            to.println(this.drawLine2D(layer, a1[0]*(b/2), a1[1]*(h/2), a1[0]*b/2, a1[1]*(h/2-tf)));
            to.println(this.drawLine2D(layer, a1[0]*(b/2), a1[1]*(h/2), a1[0]*0, a1[1]*(h/2)));
            
        }
        
    }
    
    
    public void DrawStrongAxisElevation(String layer,double length,XY direc){
        //head.txtの読み込み
        
        double[][] a={{1,1},{-1,1},{1,-1},{-1,-1}};
        for (int i = 0; i < a.length; i++) {
            double[] a1 = a[i];
            if (direc == XY.X) {
                to.println(this.drawLine2D(layer, a1[0] * (0), a1[1] * (h / 2), 
                        a1[0] * (length / 2), a1[1] * (h / 2)
                ));
                to.println(this.drawLine2D(layer, a1[0] * (0), a1[1] * (h / 2 - tf), 
                        a1[0] * (length / 2), a1[1] * (h / 2 - tf)
                ));
                to.println(this.drawLine2D(layer, a1[0] * (length / 2), a1[1] * (0), 
                        a1[0] * (length / 2), a1[1] * (h / 2)
                ));
            }else if(direc==XY.Y){
                to.println(this.drawLine2D(layer, a1[1] * (h / 2), a1[0] * (0), 
                        a1[1] * (h / 2), a1[0] * (length / 2)
                ));
                to.println(this.drawLine2D(layer, a1[1] * (h / 2 - tf), a1[0] * (0), 
                        a1[1] * (h / 2 - tf), a1[0] * (length / 2)
                ));
                to.println(this.drawLine2D(layer, a1[1] * (0), a1[0] * (length / 2), 
                        a1[1] * (h / 2), a1[0] * (length / 2)
                ));
            }
        }
        
    }
    
    public void DrawWeakAxisElevation(String layer,double length,XY direc){
        //head.txtの読み込み
        
        double[][] a={{1,1},{-1,1},{1,-1},{-1,-1}};
        for (int i = 0; i < a.length; i++) {
            double[] a1 = a[i];
            if (direc == XY.X) {
                to.println(this.drawLine2D(layer, 
                        a1[0] * (0), a1[1] * (b / 2), 
                        a1[0] * (length / 2), a1[1] * (b / 2)
                ));
                to.println(this.drawLine2D(layer, 
                        a1[0] * (0), a1[1] * (tw/2), 
                        a1[0] * (length / 2), a1[1] * (tw/2)
                ));
                to.println(this.drawLine2D(layer, 
                        a1[0] * (length / 2), a1[1] * (0), 
                        a1[0] * (length / 2), a1[1] * (b / 2)
                ));
            }else if(direc==XY.Y){
                to.println(this.drawLine2D(layer, 
                        a1[1] * (b / 2), a1[0] * (0), 
                        a1[1] * (b / 2), a1[0] * (length / 2)
                ));
                to.println(this.drawLine2D(layer, 
                        a1[1] * (tw/2), a1[0] * (0), 
                        a1[1] * (tw/2), a1[0] * (length / 2)
                ));
                to.println(this.drawLine2D(layer, 
                        a1[1] * (0), a1[0] * (length / 2), 
                        a1[1] * (b / 2), a1[0] * (length / 2)
                ));
            }
        }
        
    }
    
    

    public void setEnd(){
        //head.txtの読み込み
        try {
            File file = new File(path+"main\\end.txt");
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            int i = 0;

            String s;
            while ((s = b_reader.readLine()) != null) {

                to.println(s);
                i++;
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void setHeader(){
        //head.txtの読み込み
        try {
            File file = new File(path+"main\\header.txt");
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            int i = 0;

            String s;
            while ((s = b_reader.readLine()) != null) {

                to.println(s);
                i++;
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    
}
