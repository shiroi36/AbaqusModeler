/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB;

import util.GRAPH_LIB.GP.GPInterface;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

/**
 *
 * @author keita
 */
public class GraphLayouter extends JFrame implements GPInterface{
    JFreeChart[] chart;
    int rowx;
    int rowy;
    int sizex;
    int sizey;
    public GraphLayouter(GLInterface[] graphs,int rowx,int sizex,int sizey){
        chart=new JFreeChart[graphs.length];
        ChartPanel[] panel=new ChartPanel[graphs.length];
        this.setBounds(0,0,sizex,sizey);
        int rowy=graphs.length/rowx;//ここは後で修正
        this.rowx=rowx;
        this.rowy=rowy;
        this.sizex=sizex;
        this.sizey=sizey;
        for(int i=0;i<graphs.length;i++){
            chart[i]=graphs[i].getChart();
            panel[i]=new ChartPanel(chart[i]);
        }
        GridLayout layout=new GridLayout(rowy,rowx);
        this.getContentPane().setLayout(layout);
        for(int i=0;i<graphs.length;i++){
            this.getContentPane().add(panel[i]);
        }
    }
    public GraphLayouter(GLInterface[] graphs,Dimension rowxy,Dimension sizexy){
        if(rowxy.height*rowxy.width<graphs.length)System.out.println("graph length not matched");
        this.rowx=rowxy.width;
        this.rowy=rowxy.height;
        this.sizex=sizexy.width;
        this.sizey=sizexy.height;
        chart=new JFreeChart[graphs.length];
        ChartPanel[] panel=new ChartPanel[graphs.length];
        this.setBounds(0,0,sizex,sizey);
        for(int i=0;i<graphs.length;i++){
            chart[i]=graphs[i].getChart();
            panel[i]=new ChartPanel(chart[i]);
        }
        GridLayout layout=new GridLayout(rowy,rowx);
        this.getContentPane().setBackground(Color.white);
        this.getContentPane().setLayout(layout);
        for(int i=0;i<graphs.length;i++){
            this.getContentPane().add(panel[i]);
        }
    }
    public void PLOT(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("グラフサンプル");
        this.setVisible(true);

    }
    public JPanel getPanel(){
        return (JPanel)this.getContentPane();
    }
    public void setStatuslabel(JLabel label){
        //GraphPlotterに表示させたかったらここになんか書けば？
    }
    public void OUTPUTasPNG(String pass){
        int x=sizex/rowx;
        int y=sizey/rowy;
        BufferedImage readImage = null;
        if (readImage == null){
          readImage = new BufferedImage(rowx*x, rowy*y,
            BufferedImage.TYPE_INT_BGR);
        }
        Graphics2D off = readImage.createGraphics();
        for(int i=0;i<rowy;i++){
            for(int s=0;s<rowx;s++){
                Rectangle2D r2d=new Rectangle(s * x, i * y, x, y);
                try {
                    chart[i * rowx + s].draw(off, r2d);// Chartの描画
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
        }
        try {
          boolean result =
           ImageIO.write(readImage, "png", new File(pass));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void OUTPUTasPNG(String pass,int ONEGraphWidth,int ONEGraphHeight){
        int x=ONEGraphWidth;
        int y=ONEGraphHeight;
        BufferedImage readImage = null;
        if (readImage == null){
          readImage = new BufferedImage(rowx*x, rowy*y,
            BufferedImage.TYPE_INT_BGR);
        }
        Graphics2D off = readImage.createGraphics();
        for(int i=0;i<rowy;i++){
            for(int s=0;s<rowx;s++){
                Rectangle2D r2d=new Rectangle(s*x,i*y,x,y);
                chart[i*rowx+s].draw(off, r2d);// Chartの描画
            }
        }
        try {
          boolean result =
           ImageIO.write(readImage, "png", new File(pass));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void OUTPUTasSVG(String pass){
        int x=300;
        int y=500;
        DOMImplementation domImpl=GenericDOMImplementation.getDOMImplementation();// DOMImplementationの取得
        Document document=domImpl.createDocument(null, "svg", null);// XMLドキュメントの作成
        SVGGraphics2D svg2d=new SVGGraphics2D(document);// SVGジェネレータの作成
        
        for(int i=0;i<rowy;i++){
            for(int s=0;s<rowx;s++){
                Rectangle2D r2d=new Rectangle(s*x,i*y,x,y);
                chart[i*rowx+s].draw(svg2d, r2d);// Chartの描画
            }
        }
        boolean useCSS=true;//CSSを使用する
        try{
            OutputStream os=new FileOutputStream(pass);
            BufferedOutputStream bos=new BufferedOutputStream(os);
            Writer out=new OutputStreamWriter(bos,"UTF-8");//文字コードの指定
            svg2d.stream(out,useCSS);//出力
        }catch(UnsupportedEncodingException ue){ue.printStackTrace();}
        catch(SVGGraphics2DIOException se){se.printStackTrace();}
        catch(IOException ioe){ioe.printStackTrace();}
    }
    
}
