/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.HIST;

import util.GRAPH_LIB.GP.GPInterface;
import util.GRAPH_LIB.GLInterface;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.data.statistics.HistogramDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.*;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.ChartPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
//import java.awt.BasicStroke;

import org.jfree.ui.RectangleEdge;
import java.text.DecimalFormat;
import org.jfree.chart.title.TextTitle;

import org.jfree.chart.ChartUtilities;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import javax.swing.JLabel;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.sourceforge.jlibeps.epsgraphics.EpsGraphics2D;


/**
 *XY軸のグラフをプロットするクラスです。
 * 主に変更する数値はメソッドにて書き下しますが、めったに変更しないだろうと判断した設定事項については
 * result()メソッド内にて変更する(※印部分)という方法をとります。
 * @author keita
 */
public class HISTOGRAM extends JFrame implements GPInterface,GLInterface{
    String title,Xlabel,Ylabel;//タイトルの大きさ等の設定はJAVADRIVEにて参照のこと
    HistogramDataset data=new HistogramDataset();
//    double Xinterval,Yinterval,Xmin,Xmax,Ymin,Ymax;
//    int Xpartition,Ypartition;
//    ArrayList<Integer> LineStrokeSeries=new ArrayList<Integer>();
//    ArrayList<BasicStroke> LineStroke=new ArrayList<BasicStroke>();
    ArrayList<Integer> LineColorSeries=new ArrayList<Integer>();
    ArrayList<Color> LineColor=new ArrayList<Color>();
    double Xinterval,Yinterval,Xmin,Xmax,Ymin,Ymax;
    int Xpartition,Ypartition;
    boolean X1=false,Y1=false,X2=false,Y2=false,leg=false,LSS=false,LCS=false,T=false,LogX=false,LogY=false;
    int s=0;
    JFreeChart chart;
    ChartPanel cpanel;
    XYBarRenderer shape=new XYBarRenderer();

    /**
     * グラフのタイトルを設定するメソッド
     * @param title　タイトルの内容をstring型で。
     * @param T  タイトルを表示するか否か。trueで表示falseで非表示
     */
    public void settitle(String title,boolean T){
        this.title=title;
        this.T=T;
    }
    /**
     * 凡例を消したい時はここ。
     */
    public void removelegend(){
        leg=true;
    }
    /**
     *X軸のラベルを表示するメソッド
     * @param Xlabel　ラベルの内容
     */
    public void setXlabel(String Xlabel){
        this.Xlabel=Xlabel;
    }
    /**
     *目盛りの間隔を設定するメソッド
     * @param Xinterval 大きな目盛の間隔をあらわすdouble型
     * @param Xpartition 大きなめもりの間を小さな目盛で何分割するかというint型
     */
    public void setXtick(double Xinterval,int Xpartition){
        this.Xinterval=Xinterval;
        this.Xpartition=Xpartition;
        X1=true;
    }
    /**
     *X軸の最大最小の範囲を設定するメソッド
     * @param Xmin　設定する範囲の最小値
     * @param Xmax　設定する範囲の最大値
     */
    public void setXrange(double Xmin,double Xmax){
        this.Xmin=Xmin;this.Xmax=Xmax;X2=true;
    }
    /**
     *Y軸の最大最小の範囲を設定するメソッド
     * @param Ymin　設定する範囲の最小値
     * @param Ymax　設定する範囲の最大値
     */
    public void setYrange(double Ymin,double Ymax){
        this.Ymin=Ymin;this.Ymax=Ymax;Y2=true;
    }
    /**
     *目盛りの間隔を設定するメソッド
     * @param Yinterval 大きな目盛の間隔をあらわすdouble型
     * @param Ypartition 大きなめもりの間を小さな目盛で何分割するかというint型
     */
    public void setYtick(double Yinterval,int Ypartition){
        this.Yinterval=Yinterval;
        this.Ypartition=Ypartition;
        Y1=true;
    }
    /**
     *Y軸のラベルを表示するメソッド
     * @param Ylabel　ラベルの内容
     */
    public void setYlabel(String Ylabel){
        this.Ylabel=Ylabel;
    }
    public void setValue(java.lang.Comparable key,double[] XYvalue,int dnum){
        data.addSeries(key, XYvalue,dnum);
    }
    /**
     * プロットするデータを一気に入れるメソッド
     * @param key　凡例を格納する配列
     * @param Value　0番目の配列をX軸の値。次以降はY軸の値を入れる。
     */
    public void setValue(java.lang.Comparable[] key,double[][] Value,int dnum){
        for(int i=0;i<Value.length;i++){
            data.addSeries(key[i], Value[i],dnum);
            s++;
        }
    }

    /**
     * 線の色を決めるメソッド
     * @param Series　色を変える線の識別記号。凡例。
     * @param C　設定する線の色。
     */
    public void setBarColor(int SeriesIndex,Color C){
        LineColorSeries.add(SeriesIndex);
        LineColor.add(C);
        LCS=true;
    }
    /**
     *設定結果をまとめて、PLOTクラスでプロットする内容を表すメソッドです。
     */
    public void result(){
        //////////////グラフに関する内容/////////////////////////////////////////////////
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
        chart=ChartFactory.createHistogram("", "", "", data, PlotOrientation.VERTICAL, true, false, false);
        chart.setBackgroundPaint(Color.white);//※外の背景の色を設定するときはこれ※
        chart.getXYPlot().setOutlineVisible(true); //※外枠を消したいときはこれ※
        if(T==true){
            TextTitle title2=new TextTitle(title);
            Font titleFont=new Font("IPA モナー Pゴシック",Font.PLAIN,12);//※タイトルのフォント※
            title2.setFont(titleFont);
            chart.setTitle(title2);
        }
        Font legendFont=new Font("IPA モナー Pゴシック",Font.PLAIN,12);//※凡例のフォント※
        LegendTitle legend = chart.getLegend();
        legend.setItemFont(legendFont);
        legend.setPosition(RectangleEdge.BOTTOM);///※凡例の位置。top,bottom,left,rightから選ぶ※
        if(leg==true){
            chart.removeLegend();
            leg=false;
        }
        Font XYlabelFont=new Font("IPA モナー Pゴシック",Font.PLAIN,12);//XY軸のラベルのフォント※

/////Ｘ軸に関する内容////////////////////////////////////////////////////////////
        NumberAxis xaxis;//domeinaxis…Ｘ軸
        xaxis=new NumberAxis();
        xaxis.setVisible(true);//軸を全て消すかどうか※
        xaxis.setMinorTickMarksVisible(true);//ちっちゃい目盛を表示するか否か※
        xaxis.setTickMarksVisible(true);//大きな目盛を表示するか否か※
        if(X1==true){
            DecimalFormat Xformat=new DecimalFormat();
            NumberTickUnit Xtick=new NumberTickUnit(Xinterval,Xformat,Xpartition);//目盛について。最初の引数は大きな目盛の間隔を表すdouble型。二つ目は意味不明で三つ目の引数は大きな目盛間で小さな目盛を何等分入れるかというint型※
            xaxis.setTickUnit(Xtick);//ここまでが目盛間隔についての考察
            X1=false;
        }
        if(X2==true){
            xaxis.setRange(Xmin, Xmax);
            X2=false;
        }else{
            xaxis.setAutoRange(true);
        }//上限下限を設定する。
        xaxis.setLabelFont(XYlabelFont);
        xaxis.setTickLabelsVisible(true);//目盛の数値を消すかどうかということ
        chart.getXYPlot().setDomainAxis(xaxis);
        /////Y軸に関する内容///////////////////////////////////////////////////////////////
        NumberAxis yaxis;//rangeaxis…Y軸
        yaxis=new NumberAxis();
        yaxis.setVisible(true);//軸を全て消すかどうか※
        yaxis.setMinorTickMarksVisible(true);//ちっちゃい目盛を表示するか否か※
        yaxis.setTickMarksVisible(true);//大きな目盛を表示するか否か※
        if(Y1==true){
            DecimalFormat Yformat=new DecimalFormat("");
            NumberTickUnit Ytick=new NumberTickUnit(Yinterval,Yformat,Ypartition);//目盛について。最初の引数は大きな目盛の間隔を表すdouble型。二つ目は意味不明で三つ目の引数は大きな目盛間で小さな目盛を何等分入れるかというint型※
            yaxis.setTickUnit(Ytick);//ここまでが目盛間隔についての考察
            Y1=false;
        }
        if(Y2==true){
            yaxis.setRange(Ymin,Ymax );//上限下限を設定する。
            Y2=false;
        }else{
            yaxis.setAutoRange(true);
        }
        yaxis.setLabelFont(XYlabelFont);
        yaxis.setTickLabelsVisible(true);//目盛の数値を消すかどうかということ
        chart.getXYPlot().setRangeAxis(yaxis);

        if(LCS==true){
            for(int i=0;i<LineColorSeries.size();i++){
                shape.setSeriesPaint(LineColorSeries.get(i), LineColor.get(i));
            }
        }
        shape.setShadowVisible(false);
        shape.setBarPainter(new StandardXYBarPainter());
        chart.getXYPlot().setRenderer(shape);

        cpanel =new ChartPanel(chart);
        this.getContentPane().add(cpanel,BorderLayout.CENTER);
    }
    public void setStatuslabel(JLabel label){
        //GraphPlotterに表示させたかったらここになんか書けば？
    }
    public JPanel getPanel(){
        this.result();
        return cpanel;
    }
    public JFreeChart getChart(){
        this.result();
        return chart;
    }
    public void OUTPUTasJPEG(String pass,float quality,int width,int height){
        this.result();
        File file=new File(pass);
        try{
            ChartUtilities.saveChartAsJPEG(file, quality, this.chart, width, height);
        }catch(IOException e){e.printStackTrace();}
    }
    public void OUTPUTasPNG(String pass,int width,int height){
        this.result();
        File file=new File(pass);
        try{
            ChartUtilities.saveChartAsPNG(file, this.chart, width, height);
        }catch(IOException e){e.printStackTrace();}
    }
    public void OUTPUTasSVG(String pass,int width,int height){
        this.result();
        DOMImplementation domImpl=GenericDOMImplementation.getDOMImplementation();// DOMImplementationの取得
        Document document=domImpl.createDocument(null, "svg", null);// XMLドキュメントの作成
        SVGGraphics2D svg2d=new SVGGraphics2D(document);// SVGジェネレータの作成
        Rectangle2D r2d=new Rectangle(width,height);
        this.chart.draw(svg2d, r2d);// Chartの描画
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
    public void OUTPUTasEPS(String pass,int width,int height){
        this.result();
        try{
            String epstitle="graph";
            OutputStream os=new FileOutputStream(pass);
            BufferedOutputStream bos=new BufferedOutputStream(os);
            EpsGraphics2D eps2d=new EpsGraphics2D(epstitle,bos,0,0,width,height);
            Rectangle2D r2d=new Rectangle(width,height);
            this.chart.draw(eps2d, r2d);
            eps2d.close();
            bos.close();
        }catch(IOException ioe){ioe.printStackTrace();}
    }
    public void PLOT(){
        this.result();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 500, 500);
        this.setTitle("グラフサンプル");
        this.setVisible(true);
    }
}
