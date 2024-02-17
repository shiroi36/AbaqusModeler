/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.section;

import FrameCreator.FAMID;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import util.LineInfo;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;

/**
 *
 * @author bat
 */
public class HSection implements SectionInterface{

    private double h;
    private double b;
    private double tw;
    private double tf;
    private double r;

    public static void main(String[] args) {
        HSection h = new HSection(500, 300, 12.5, 15, 0);
        h.print();
        
//        double lb=8000;
//        System.out.println(lb/h.getib());
    }

    public HSection(String sec) {
        String[] val;
        if(sec.contains("*")){
            val=sec.split("-")[1].trim().split("\\*");
        }else if(sec.contains("×")){
            val=sec.split("-")[1].trim().split("×");
        }else val=new String[1];
        this.h = Double.parseDouble(val[0].trim());
        this.b = Double.parseDouble(val[1].trim());
        this.tw = Double.parseDouble(val[2].trim());
        this.tf = Double.parseDouble(val[3].trim());
        if(val.length==5)this.r = Double.parseDouble(val[4].trim());
        else this.r=0;
    }

    public HSection(double H, double B, double tw, double tf, double r) {
        this.h = H;
        this.b = B;
        this.tw = tw;
        this.tf = tf;
        this.r = r;
    }

    public HSection(double H, double B, double tw, double tf) {
        this.h = H;
        this.b = B;
        this.tw = tw;
        this.tf = tf;
        this.r = 0;
    }

    public double getB() {return b;}
    public void setB(double B){this.b=B;}

    public double getH() {return h;}
    public void setH(double H){this.h=H;}

    public double getWebThickness() {return tw;}
    public void setWebThickness(double tw){this.tw=tw;}

    public double getFlangeThickness() {return tf;}
    public void setFlangeThickness(double tf){this.tf=tf;}
    
    public boolean compare(HSection sec){
        double a=Math.sqrt(
                Math.pow(h-sec.getH(), 2)+
                Math.pow(b-sec.getB(), 2)+
                Math.pow(tw-sec.getWebThickness(), 2)+
                Math.pow(tf-sec.getFlangeThickness(), 2)+
                Math.pow(r-sec.getR(), 2)
                );
        if(a<0.001)return true;
        else return false;
    }

    public double getR() {
        return r;
    }
    public String getName(){
        return "H-"+(int)h+"x"+(int)b+"x"+(int)tw+"x"+(int)tf+"x"+(int)r;
    }

    public double getIx() {
        double B2 = b;
        double b = B2 - tw;
        double h = this.h - 2 * tf;
        double Ib = (B2 * Math.pow(this.h, 3) - b * Math.pow(h, 3)) / 12
                + 4 * (0.0075 * Math.pow(r, 4) + 0.2146 * r * r * Math.pow(this.h / 2 - tf - 0.2234 * r, 2));
        
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Ib, mc);
        return decimal.doubleValue();
    }
    public double getix(){
        double ix= Math.sqrt(this.getIx()/this.getA());
        
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(ix, mc);
        return decimal.doubleValue();
    }
    public double getiy(){
        double iy= Math.sqrt(this.getIy()/this.getA());
        
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(iy, mc);
        return decimal.doubleValue();
    }
    
    public double getib(){
        //圧縮側フランジと梁せい1/6のウェブからなる、H形断面ウェブ周りの断面二次半径
        double h=this.h/6;
        double t=tw;
        double f=tf;
        double w=h-f;
        double A=b*f+w*t+0.2146*Math.pow(r, 2)*2;
        double Ib=f*Math.pow(b, 3)/12+w*Math.pow(t, 3)/12
                +2*(0.0075*Math.pow(r, 4)+0.2146*Math.pow(r, 2)*Math.pow((0.5*t+0.2234*r), 2));
        double ib=Math.sqrt(Ib/A);
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(ib, mc);
        return decimal.doubleValue();
    }

    public double getIy() {
        double B1=2*tf;
        double H1=b;
        double B2=h-2*tf;
        double H2=tw;
        double Iy=B1*Math.pow(H1, 3)/12+B2*Math.pow(H2, 3)/12
                +4*(0.0075*Math.pow(r, 3)+0.2146*r*r*Math.pow(tw/2+0.2234*r, 2));
        
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Iy, mc);
        return decimal.doubleValue();
    }

    public double getZex() {
//        double bolthole=0;//フランジボルト孔欠損部長さ。たとえば、上下にΦ24に2列ならば、24*2とする。
        double Zb = this.getIx() / (h / 2);
        
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Zb, mc);
        return decimal.doubleValue();
    }
    
    public double getZey() {
//        double bolthole=0;//フランジボルト孔欠損部長さ。たとえば、上下にΦ24に2列ならば、24*2とする。
        double Zb = this.getIy() / (b / 2);
        
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Zb, mc);
        return decimal.doubleValue();
    }

    public double getZpx() {
//        double bolthole=0;//フランジボルト孔欠損部長さ。たとえば、上下にΦ24に2列ならば、24*2とする。
        double B2 = b;
        double Zp = (B2 * tf) * (h - tf) + ((h - tf) / 2 * tw) * (h / 2 - tf)
                + 2 * (0.2146 * r * r * (h / 2 - tf - 0.2234 * r));
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Zp, mc);
        return decimal.doubleValue();
    }
    
    public double getZpy() {
//        double bolthole=0;//フランジボルト孔欠損部長さ。たとえば、上下にΦ24に2列ならば、24*2とする。
//        double B2 = B;
        double Zp = 0.5*b*b*tf+0.25*(h-2*tf)*tw*tw
                +0.4292*r*r*(tw+0.4467*r);
        //有効数字を3桁に設定
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Zp, mc);
        return decimal.doubleValue();
    }

    public double getA() {
        double A= b * tf * 2 + (h - 2 * tf) * tw + 4 * 0.2146 * r * r;
        //有効数字を3桁に設定
        MathContext mc = new MathContext(4);
        BigDecimal decimal = new BigDecimal(A, mc);
        return decimal.doubleValue();
    }
    public double getAw() {
        double A= tw*(h-2*tf);
        //有効数字を3桁に設定
        MathContext mc = new MathContext(4);
        BigDecimal decimal = new BigDecimal(A, mc);
        return decimal.doubleValue();
    }
    public double getAf() {
        double A= tf*b;
        //有効数字を3桁に設定
        MathContext mc = new MathContext(4);
        BigDecimal decimal = new BigDecimal(A, mc);
        return decimal.doubleValue();
    }
    
    public double getIw(){
        //アールの影響はみない
        double B1=2*tf;
        double H1=b;
        double B2=h-2*tf;
        double H2=tw;
        double Iy=B1*Math.pow(H1, 3)/12+B2*Math.pow(H2, 3)/12;
        double h=this.h-tf;
        double Iw=Iy*h*h/4;
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(Iw, mc);
        return decimal.doubleValue();
    }
    
    public double getJ(){
        //アールの影響はみない
        double h=this.h-tf;
        double J=(2*b*Math.pow(tf, 3)+h*Math.pow(tw, 3))/3;
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(J, mc);
        return decimal.doubleValue();
    }
    
    public double getOwnWeight_Nmm(){
        double V=this.getA()/1000000*1*7850*9.8/1000;
        MathContext mc = new MathContext(3);
        BigDecimal decimal = new BigDecimal(V, mc);
        return decimal.doubleValue();
    }

    public String[] print() {
        ArrayList<String> val=new ArrayList<String>();
        val.add("");
        val.add("△梁断面");
        val.add("H-" + h + "×" + b + "×" + tw + "×" + tf + "×" + r);
        val.add("断面積　　A="+this.getA()+" mm^2");
        val.add("1mあたり重量　　"+this.getOwnWeight_Nmm()+"N/mm");
        val.add("ウェブのみの断面積　　Aw="+this.getAw()+" mm^2");
        val.add("片側フランジのみの断面積　　Af="+this.getAf()+" mm^2");
        val.add("断面二次モーメント");
        val.add("・・・Ix="+this.getIx()+" mm^4");
        val.add("・・・Iy="+this.getIy()+" mm^4");
        val.add("断面二次半径");
        val.add("・・・ix="+this.getix()+" mm");
        val.add("・・・iy="+this.getiy()+" mm");
        val.add("曲げ応力算定の為の断面二次半径　ib="+this.getib()+" mm");
        val.add("断面係数");
        val.add("・・・Zex="+this.getZex()+" mm^3");
        val.add("・・・Zey="+this.getZey()+" mm^3");
        val.add("塑性断面係数");
        val.add("・・・Zpx="+this.getZpx()+" mm^3");
        val.add("・・・Zpy="+this.getZpy()+" mm^3");
        val.add("曲げねじり定数　　Iw="+this.getIw()+" mm^6");
        val.add("サンブナンねじり定数　　J="+this.getJ()+" mm^4");
        return (String[])val.toArray(new String[0]);
    }
    
    public LineInfo[] get2DShape(int RSeparationNumber) {
        int n=RSeparationNumber;
        double[][] ans;
        ArrayList<LineInfo> line=new ArrayList<LineInfo>();
        
        line.add(new LineInfo(new CoordXYZ(tw/2, -(h/2-tf-r),0),new CoordXYZ(tw/2, h/2-tf-r,0)));
        line.add(new LineInfo(new CoordXYZ(-tw/2, -(h/2-tf-r),0),new CoordXYZ(-tw/2, h/2-tf-r,0)));
        line.add(new LineInfo(new CoordXYZ( (b/2), (h/2),0),new CoordXYZ( -(b/2), (h/2),0)));
        line.add(new LineInfo(new CoordXYZ( (b/2), -(h/2),0),new CoordXYZ( -(b/2), -(h/2),0)));
        
        LineInfo[] arc1=this.getArc("0",new CoordXYZ( tw/2+r, h/2-tf-r,0),r, 90, 180,n);
        LineInfo[] arc2=this.getArc("0", new CoordXYZ(tw/2+r, -(h/2-tf-r),0),r, 180, 270,n);
        LineInfo[] arc3=this.getArc("0", new CoordXYZ(-(tw/2+r), -(h/2-tf-r),0),r, 270, 360,n);
        LineInfo[] arc4=this.getArc("0",new CoordXYZ( -(tw/2+r), h/2-tf-r,0),r, 0, 90,n);
        
        for (int i = 0; i < n; i++) {
            line.add(arc1[i]);
            line.add(arc2[i]);
            line.add(arc3[i]);
            line.add(arc4[i]);
        }
        
        double[][] a = {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}};
        for (int i = 0; i < a.length; i++) {
            double[] a1 = a[i];
            line.add(new LineInfo(new CoordXYZ(a1[0] * (tw / 2 + r), a1[1] * (h / 2 - tf), 0),
            new CoordXYZ(a1[0] * b / 2, a1[1] * (h / 2 - tf), 0)));
            line.add(new LineInfo(new CoordXYZ(a1[0] * (b / 2), a1[1] * (h / 2), 0),
            new CoordXYZ(a1[0] * b / 2, a1[1] * (h / 2 - tf), 0)));
        }

        return (LineInfo[])line.toArray(new LineInfo[0]);
    }
    
    public CoordXYZ[] getTextPosition(int type){
        CoordXYZ[] a=null;
        
        double[][] val=null;
        if(type==FAMID.TYPE_COLUMN){
            val=new double[][]{
                {1.1*h,1.1*h,0},
                {1.1*h,1.1*h,0},
                {1.1*h,1.1*h,0}
//                {{h/2,0,0},{1.2*h,0,0}},
//                {{-b/2,0,0},{-1.2*b,0,0}},
//                {{b/2,0,0},{1.2*b,0,0}},
//                {{1.1*b/2,1.1*h/2,0},{1.1*b/2,1.1*h/2,100}},
            };
        }else if(type==FAMID.TYPE_BEAM){
            val=new double[][]{
                {1.1*h,1.1*h,0},
                {1.1*h,1.1*h,0},
                {1.1*h,1.1*h,0}
//                {{b/2,0,0},{1.2*b,0,0}},
//                {{-b/2,0,0},{-1.2*b,0,0}},
//                {{b/2,0,0},{1.2*b,0,0}},
            };
        }

        a = new CoordXYZ[val.length];
        for (int i = 0; i < val.length; i++) {
            double[] val1 = val[i];
            a[i] =new CoordXYZ(val1[0], val1[1], val1[2]);
        }
        
        return a;
    }
    
    
    private LineInfo[] getArc(String layer, CoordXYZ p, double R, double s, double g,int n) {
        LineInfo[] a=new LineInfo[n];
        for (int i = 0; i < n; i++) {
            double ts=(g-s)/360*Math.PI*2/n*i+s/360*Math.PI*2;
            double tg=(g-s)/360*Math.PI*2/n*(i+1)+s/360*Math.PI*2;
            
            CoordXYZ cs=new CoordXYZ(p.getX()+R*Math.cos(ts),p.getY()+R*Math.sin(ts),p.getZ());
            CoordXYZ cg=new CoordXYZ(p.getX()+R*Math.cos(tg),p.getY()+R*Math.sin(tg),p.getZ());
            a[i]=new LineInfo(cs,cg);
        }
        return a;
    }
    
    public void printConsole(){
        String[] val=this.print();
        for (int i = 0; i < val.length; i++) {
            String val1 = val[i];
            System.out.println(val1);
        }
    }

}
