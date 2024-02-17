/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.XY;

import util.GRAPH_LIB.GP.GPInterface;
import util.GRAPH_LIB.GLInterface;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.axis.*;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.title.TextTitle;
import org.jfree.ui.RectangleEdge;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.title.LegendTitle;
import org.jfree.ui.TextAnchor;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.awt.BasicStroke;

import java.text.DecimalFormat;

import java.io.File;
import javax.swing.JFrame;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.BufferedOutputStream;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.sourceforge.jlibeps.epsgraphics.EpsGraphics2D;
import java.awt.geom.Point2D;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.GroupLayout;
import java.awt.GridLayout;


/**
 *XY軸のグラフをプロットするクラスです。
 * 主に変更する数値はメソッドにて書き下しますが、めったに変更しないだろうと判断した設定事項については
 * result()メソッド内にて変更する(※印部分)という方法をとります。
 * @author keita
 */
public class XYGRAPH extends JFrame implements MouseListener,GPInterface,GLInterface{
    String title,Xlabel,Ylabel,display;//タイトルの大きさ等の設定はJAVADRIVEにて参照のこと
    DefaultXYDataset data;//addするには初期化しておくことが必要
    double Xinterval,Yinterval,Xmin,Xmax,Ymin,Ymax;
    int Xpartition,Ypartition,ind,Mnum;
    ArrayList<Integer> LineStrokeSeries;
    ArrayList<BasicStroke> LineStroke;
    ArrayList<Integer> LineColorSeries;
    ArrayList<Color> LineColor;
    boolean X1,Y1,X2,Y2,leg,LSS,LCS,T,
            LogX,LogY,mouseflag,mouseVerify,
            ticklabel,ConstFlag,PointFlag,arx,ary;
    XYLineAndShapeRenderer shape;
    JLabel[] l;
    JFreeChart chart;
    ChartPanel cpanel;
    
    ArrayList<Integer> ConstInd;
    ArrayList<Integer> ConstSeries;
    ArrayList<String> ConstString;
    private int fontsize;
    private JLabel status;
    private boolean invertedX;
    private boolean invertedY;
    private LabelGenerator lg;
    
    public XYGRAPH(){
        this(20);
    }
    public XYGRAPH(int fontsize){
        this.fontsize=fontsize;
        shape=new XYLineAndShapeRenderer();
        LineColor=new ArrayList<Color>();
        LineColorSeries=new ArrayList<Integer>();
        LineStroke=new ArrayList<BasicStroke>();
        LineStrokeSeries=new ArrayList<Integer>();
        ConstInd=new ArrayList<Integer>();
        ConstSeries=new ArrayList<Integer>();
        ConstString=new ArrayList<String>();
        data=new DefaultXYDataset();
        mouseflag=true;
        mouseVerify=true;
        ConstFlag=false;
        ticklabel=true;
        PointFlag=true;
        X1=false;
        Y1=false;
        X2=false;
        Y2=false;
        leg=false;
        LSS=false;
        LCS=false;
        T=false;
        LogX=false;
        LogY=false;
        arx=true;
        ary=true;
        invertedX=false;
        invertedY=false;
        ind=0;
        lg=null;
    }

    /**
     * グラフのタイトルを設定するメソッド
     * @param title　タイトルの内容をstring型で。
     * @param T  タイトルを表示するか否か。trueで表示falseで非表示
     */
    public void settitle(String title,boolean T){
        this.setTitle(title);
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
     * 軸を対数軸にしたい時はここ
     * @param LogX　X軸を対数表示するかどうかのフラグ
     * @param LogY　Y軸を対数表示するかどうかのフラグ
     */
    public void setXYLogarithmic(boolean LogX,boolean LogY){
        this.LogX=LogX;
        this.LogY=LogY;
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
     *X軸のラベルを表示するメソッド
     * @param Xlabel　ラベルの内容
     */
    public void setXlabel(String Xlabel){
        this.Xlabel=Xlabel;
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
    /**
     *グラフでプロットする数値をセットするメソッドです。
     *@param key プロットする数値を識別する凡例。数値でもストリング型でもなんでもいい
     *@param XYvalue プロットする数値を長さ２の配列で入れる
     * XYvalue[0][] X座標の数値配列
     * XYvalue[1][] Y座標の数値配列
     */
    public void setValue(java.lang.Comparable key,double[][] XYvalue){
        data.addSeries(key, XYvalue);
        shape.setSeriesShapesVisible(ind, false);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(ind, true);
        this.setLineWidth(ind, 2.0f);
        ind++;
    }
    public void setValue(java.lang.Comparable key,double[][] XYvalue,int skip){
        ArrayList<double[]> list=new ArrayList<double[]>();
        int s=0;
        for(int i=0;i<XYvalue[0].length;i++){
            s++;
            if(s==skip){
                list.add(new double[]{XYvalue[0][i],XYvalue[1][i]});
                s=0;
            }
            if(i==XYvalue[0].length-1||i==0){
                list.add(new double[]{XYvalue[0][i],XYvalue[1][i]});
            }
        }
        double[][] val=new double[2][list.size()];
        for(int i=0;i<list.size();i++){
            val[0][i]=list.get(i)[0];
            val[1][i]=list.get(i)[1];
        }
        data.addSeries(key, val);
        shape.setSeriesShapesVisible(ind, false);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(ind, true);
        ind++;
    }
     public void setValue(java.lang.Comparable key,double[][] XYvalue,boolean linevisible,boolean CircleRectangle,int size,int skip){
        ArrayList<double[]> list=new ArrayList<double[]>();
        int s=0;
        for(int i=0;i<XYvalue[0].length;i++){
            s++;
            if(s==skip){
                list.add(new double[]{XYvalue[0][i],XYvalue[1][i]});
                s=0;
            }
            if(i==XYvalue[0].length-1||i==0){
                list.add(new double[]{XYvalue[0][i],XYvalue[1][i]});
            }
        }
        double[][] val=new double[2][list.size()];
        for(int i=0;i<list.size();i++){
            val[0][i]=list.get(i)[0];
            val[1][i]=list.get(i)[1];
        }
        data.addSeries(key, val);
        shape.setSeriesShapesVisible(ind, true);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        if(CircleRectangle){
            shape.setSeriesShape(ind, new java.awt.geom.Ellipse2D.Double(-size/2,-size/2,size,size), true);
        }else{
            shape.setSeriesShape(ind, new java.awt.Rectangle(-size/2,-size/2,size,size), true);
        }
        shape.setSeriesLinesVisible(ind, linevisible);
        ind++;
     }
    /**
     * グラフでプロットする数値をセットするメソッドです。
     * @param key   プロットする数値を識別する凡例。数値でもストリング型でもなんでもいい
     * @param XYvalue   プロットする数値を長さ２の配列で入れる
     *  XYvalue[0][] X座標の数値配列
     * XYvalue[1][] Y座標の数値配列
     * @param plotvisible   プロット点を表示するか否か
     * @param linevisible   プロットをつないでいる線を表示するか否か
     */
     public void setValue(java.lang.Comparable key,double[][] XYvalue,boolean plotvisible,boolean linevisible){
        data.addSeries(key, XYvalue);
        shape.setSeriesShapesVisible(ind, plotvisible);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesShape(ind, new java.awt.Rectangle(-3,-3,6,6), true);
        shape.setSeriesLinesVisible(ind, linevisible);
        ind++;
     }
     public void setValue(java.lang.Comparable key,double[][] XYvalue,boolean linevisible,boolean CircleRectangle,int size){
        data.addSeries(key, XYvalue);
        shape.setSeriesShapesVisible(ind, true);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        if(CircleRectangle){
            shape.setSeriesShape(ind, new java.awt.geom.Ellipse2D.Double(-size/2,-size/2,size,size), true);
        }else{
            shape.setSeriesShape(ind, new java.awt.Rectangle(-size/2,-size/2,size,size), true);
        }
        shape.setSeriesLinesVisible(ind, linevisible);
        ind++;
     }
     public void setValue(java.lang.Comparable key,double[][] XYvalue,boolean plotvisible,boolean linevisible,boolean CircleRectangle){
        data.addSeries(key, XYvalue);
        shape.setSeriesShapesVisible(ind, plotvisible);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        if(CircleRectangle){
            shape.setSeriesShape(ind, new java.awt.geom.Ellipse2D.Double(-6,-6,12,12), true);
        }else{
            shape.setSeriesShape(ind, new java.awt.Rectangle(-6,-6,12,12), true);
        }
        shape.setSeriesLinesVisible(ind, linevisible);
        ind++;
     }
     public void removeAllValue(){
        data=new DefaultXYDataset();
     }
    /**
     * プロットするデータを一気に入れるメソッド
     * @param key　凡例を格納する配列
     * @param Value　0番目の配列をX軸の値。次以降はY軸の値を入れる。
     */
    public void setValue(java.lang.Comparable[] key,double[][] Value){
        for(int i=0;i<Value.length-1;i++){
            double[][] XYvalue={Value[0],Value[i+1]};
            data.addSeries(key[i], XYvalue);
            shape.setSeriesShapesVisible(ind,false);
            shape.setSeriesLinesVisible(ind, true);
            ind++;
        }
    }
    public void setValue(String prefix,XYGRAPH graph){
        DefaultXYDataset dset=graph.getXYDataset();
        for (int i = 0; i < dset.getSeriesCount(); i++){
            String key =prefix+(String)dset.getSeriesKey(i);
            double[][] val=new double[2][dset.getItemCount(i)];
            for (int s = 0; s < dset.getItemCount(i); s++) {
                val[0][s]=dset.getXValue(i, s);
                val[1][s]=dset.getYValue(i, s);
            }
            this.setValue(key, val);
        }
    }
    public void setValue(java.lang.Comparable[] key,double[][][] Value){
        for(int i=0;i<Value.length;i++){
            this.setValue(key[i], Value[i]);
        }
    }
    public void setValueReversed(java.lang.Comparable[] key,double[][] Value){
        for(int i=0;i<Value.length-1;i++){
            double[][] XYvalue={Value[i+1],Value[0]};
            data.addSeries(key[i], XYvalue);
            shape.setSeriesShapesVisible(ind,false);
            shape.setSeriesLinesVisible(ind, true);
            ind++;
        }
    }
    public void setValue(java.lang.Comparable[] key,double[][] Value,Boolean ShapeVisible,Boolean LineVisible){
        for(int i=0;i<Value.length-1;i++){
            double[][] XYvalue={Value[0],Value[i+1]};
            data.addSeries(key[i], XYvalue);
            shape.setSeriesShapesVisible(ind,ShapeVisible);
            shape.setSeriesLinesVisible(ind, LineVisible);
            ind++;
        }
    }
    public void setValueReversed(java.lang.Comparable[] key,double[][] Value,Boolean ShapeVisible,Boolean LineVisible){
        for(int i=0;i<Value.length-1;i++){
            double[][] XYvalue={Value[i+1],Value[0]};
            data.addSeries(key[i], XYvalue);
            shape.setSeriesShapesVisible(ind,ShapeVisible);
            shape.setSeriesLinesVisible(ind, LineVisible);
            ind++;
        }
    }
    /**
     * 線の太さを決めるメソッド
     * @param Series　線の太さを設定するグラフの凡例。識別記号。
     * @param width　線の太さをfloat型で設定。
     */
    public void setLineWidth(int Series,float width){
        BasicStroke Line=new BasicStroke(width);
        LineStrokeSeries.add(Series);
        LineStroke.add(Line);
        LSS=true;
    }
    public void setAllLineWidth(float width){
        LineStrokeSeries=new ArrayList<Integer>();
        LineStroke=new ArrayList<BasicStroke>();
        BasicStroke Line=new BasicStroke(width);
        for (int i = 0; i < data.getSeriesCount(); i++) {   
            LineStrokeSeries.add(i);
            LineStroke.add(Line);   
        }
    }
    /**
     * オーバーライド。線の太さと色を一気に変えるメソッド。
     * @param Series　設定する線の指定記号。凡例。
     * @param width　設定する線の太さ。
     * @param c　設定する線の色
     */
    public void setLineWidth(int SeriesIndex,float width,Color c){
        BasicStroke Line=new BasicStroke(width);
        LineStrokeSeries.add(SeriesIndex);
        LineStroke.add(Line);
        LineColorSeries.add(SeriesIndex);
        LineColor.add(c);
        LSS=true;
        LCS=true;
    }
    /**
     * 点線を設定するためのメソッド。
     * @param Series　設定する線の識別記号。凡例。
     * @param width　設定する線の太さ。
     * @param dash　設定する線の破線パターン。詳しくはググレカス。
     */
    public void setDashLine(int SeriesIndex,float width, float[] dash){
        BasicStroke Line=new BasicStroke(width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,1.0f,dash,0.0f);
        //※詳細に点線を変えたければ他の変数をここで変えるべし。
        LineStrokeSeries.add(SeriesIndex);
        LineStroke.add(Line);
        LSS=true;
    }
    /**
     * 点線を設定するためのメソッド。
     * @param Series　設定する線の識別記号。凡例。
     * @param width　設定する線の太さ。
     * @param dash　設定する線の破線パターン。詳しくはググレカス。
     * @param c　設定する線の色
     */
    public void setDashLine(int SeriesIndex,float width, float[] dash,Color c){
        BasicStroke Line=new BasicStroke(width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,1.0f,dash,0.0f);
        //詳細に点線を変えたければ他の変数をここで変えるべし。
        LineStrokeSeries.add(SeriesIndex);
        LineStroke.add(Line);
        LineColorSeries.add(SeriesIndex);
        LineColor.add(c);
        LSS=true;
        LCS=true;
    }
    /**
     * 線の色を決めるメソッド
     * @param Series　色を変える線の識別記号。凡例。
     * @param C　設定する線の色。
     */
    public void setLineColor(int SeriesIndex,Color C){
        LineColorSeries.add(SeriesIndex);
        LineColor.add(C);
        LCS=true;
    }
    public void setTickLabel(boolean ticklabel){
        this.ticklabel=ticklabel;
    }
    public void setAutoRangeIncludesZero( boolean xaxis,boolean yaxis){
        arx=xaxis;
    }
    public void setInverted(boolean xaxis,boolean yaxis){
        this.invertedX=xaxis;
        this.invertedY=yaxis;
    }
    
    public void setLabel(int series,int index,String label){
        ConstFlag=true;
        ConstSeries.add(series);
        ConstInd.add(index);
        ConstString.add(label);
    }
    
    /**
     *設定結果をまとめて、PLOTクラスでプロットする内容を表すメソッドです。
     */
    public void result(){
        //////////////グラフに関する内容/////////////////////////////////////////////////
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
        chart=ChartFactory.createXYLineChart(title, Xlabel, Ylabel, data, PlotOrientation.VERTICAL, true, true, true);
        chart.setBackgroundPaint(Color.white);//※外の背景の色を設定するときはこれ※
        chart.getXYPlot().setOutlineVisible(true); //※外枠を消したいときはこれ※
        chart.getXYPlot().setDomainGridlinePaint(Color.BLACK);//※X軸側のグリットライン の色を設定する時はこれ※
        chart.getXYPlot().setDomainGridlinesVisible(true);//※X軸側のグリットラインを付ける又は消す時はこれ※
        chart.getXYPlot().setRangeGridlinePaint(Color.BLACK);//※Y軸側のグリットライン の色を設定する時はこれ※
        chart.getXYPlot().setRangeGridlinesVisible(true);//※Y軸側のぐりっとラインを付ける又は消す時はこれ※
        chart.getXYPlot().setBackgroundPaint(Color.white);//※プロットする空間の背景の色を変更したい時はこれ※
//        barrenderer.setLabelGenerator(new LabelGenerator()); 
//        fontsize=40;
        if(T==true){
            TextTitle title2=new TextTitle(title);
            Font titleFont=new Font("KonatuTohaba",Font.PLAIN,fontsize);//※タイトルのフォント※
            title2.setFont(titleFont);
            chart.setTitle(title2);
        }
        Font legendFont=new Font("KonatuTohaba",Font.PLAIN,fontsize);//※凡例のフォント※
        LegendTitle legend = chart.getLegend();
        legend.setItemFont(legendFont);
        legend.setPosition(RectangleEdge.BOTTOM);///※凡例の位置。top,bottom,left,rightから選ぶ※
        if(leg==true){
            chart.removeLegend();
            leg=false;
        }
        Font XYlabelFont=new Font("KonatuTohaba",Font.PLAIN,fontsize);//XY軸のラベルのフォント※
        Font XYtickFont=new Font("KonatuTohaba",Font.PLAIN,fontsize-4);//XY軸の目盛りのフォント※


        /////Ｘ軸に関する内容////////////////////////////////////////////////////////////
        NumberAxis xaxis;//domeinaxis…Ｘ軸
        if(LogX==true){xaxis=new LogarithmicAxis(Xlabel);}
        else{xaxis=new NumberAxis(Xlabel);}
        xaxis.setAutoRangeIncludesZero(arx);
        xaxis.setVisible(true);//軸を全て消すかどうか※
        xaxis.setTickLabelsVisible(true);//ラベルを表示するか否か
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
        xaxis.setTickLabelFont(XYtickFont);
        xaxis.setInverted(invertedX);
        xaxis.setTickLabelsVisible(ticklabel);//目盛の数値を消すかどうかということ
        chart.getXYPlot().setDomainAxis(xaxis);
        /////Y軸に関する内容///////////////////////////////////////////////////////////////
        NumberAxis yaxis;//rangeaxis…Y軸
        if(LogY==true){yaxis=new LogarithmicAxis(Ylabel);}
        else{yaxis=new NumberAxis(Ylabel);}
        yaxis.setAutoRangeIncludesZero(ary);
        yaxis.setTickLabelsVisible(true);//ラベルを表示するか否か
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
        yaxis.setTickLabelFont(XYtickFont);
        yaxis.setInverted(invertedY);
        yaxis.setTickLabelsVisible(ticklabel);//目盛の数値を消すかどうかということ
        chart.getXYPlot().setRangeAxis(yaxis);
        /////////////////線幅や線の形状に関する内容////////////////////////
        if(LSS==true){
            for(int i=0;i<LineStrokeSeries.size();i++){

                shape.setSeriesStroke(LineStrokeSeries.get(i), LineStroke.get(i));
            }
        }
        if(LCS==true){
            for(int i=0;i<LineColorSeries.size();i++){
                shape.setSeriesPaint(LineColorSeries.get(i), LineColor.get(i));
            }
        }
//        StandardXYItemLabelGenerator sig=new StandardXYItemLabelGenerator();
        if(ConstFlag){
            int[] item=new int[ConstInd.size()];
            int[] seri=new int[ConstSeries.size()];
            String[] lab=new String[ConstString.size()];
            for(int i=0;i<item.length;i++){
                item[i]=ConstInd.get(i);
                seri[i]=ConstSeries.get(i);
                lab[i]=ConstString.get(i);
            }
            shape.setBaseItemLabelGenerator(new LabelGenerator(seri,item,lab));
            shape.setBaseItemLabelFont(new Font("KonatuTohaba",Font.PLAIN,fontsize));
            shape.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.INSIDE10,
                    TextAnchor.BOTTOM_LEFT,
                    TextAnchor.BOTTOM_LEFT,
                    0));
            shape.setBaseItemLabelsVisible(true);
        }
        chart.getXYPlot().setRenderer(shape);
                

        cpanel =new ChartPanel(chart);
        cpanel.addMouseListener(this);
        if(mouseVerify==false){
            cpanel.removeMouseListener(this);
        }
        if(mouseflag){
            mouseflag=false;
            Mnum=data.getSeriesCount();
        }
        l=new JLabel[Mnum];
        Font font=new Font("KonatuTohaba",0,10);
        for(int i=0;i<Mnum;i++){
            l[i]=new JLabel();
            l[i].setFont(font);
//            l.setBackground(Color.white);
            l[i].setText("index"+i);
        }
        
        if(PointFlag)this.setPanelWithToolBar(cpanel, l);
        else this.getContentPane().add(cpanel);
//        this.getContentPane().add(cpanel,BorderLayout.CENTER);
    }
    public void setYConstantValue(double xmin,double xmax,double YValue,int drawedItem,String label){
        ConstFlag=true;
        ConstSeries.add(ind);
        this.setValue(label, new double[][]{{xmin,xmax},{YValue,YValue}});
        ConstInd.add(drawedItem);
        ConstString.add(label);
    }
    public void addLabelinGraph(double[][] XYcoordinate,String[] label){
        ConstFlag=true;
        for(int i=0;i<XYcoordinate[0].length;i++){
            ConstSeries.add(ind);
            ConstInd.add(i);
            ConstString.add(label[i]);
        }
        this.setValue(label[0],XYcoordinate,false,false);
    }
    public int getSeriesCount(){
        return data.getSeriesCount();
    }
    public DefaultXYDataset getXYDataset(){
        return data;
    }
    private void setPanelWithToolBar(ChartPanel c,JLabel[] l){
        JPanel pane=new JPanel();
        pane.setLayout(new GridLayout(l.length,1));
        pane.setBackground(Color.white);
        for(int i=0;i<l.length;i++){
            pane.add(l[i]);
        }
        
//        this.getContentPane().removeAll();
        this.getContentPane().setBackground(Color.WHITE);
        GroupLayout layout=new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);

        /**
         * 垂直方向
         */
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(c))
                .addGroup(layout.createParallelGroup()
                    .addComponent(pane)));
        /**
         * 鉛直方向
         */
        layout.setHorizontalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup()
                    .addComponent(c)
                    .addComponent(pane)));
//        this.getContentPane().repaint();
//        this.getContentPane().invalidate();
//        this.getContentPane().validate();
    }
    public JPanel getPanel(){
        this.result();
//        return (JPanel)this.getContentPane();
        return cpanel;
    }
    public JFreeChart getChart(){
        this.result();
        return chart;
    }
    public void mouseClicked(MouseEvent e){
        cpanel.mouseClicked(e);
        int x =e.getX();
        int y =e.getY();
        Rectangle2D plotArea = cpanel.getScreenDataArea();
        XYPlot plot = (XYPlot) chart.getPlot(); // your plot
        double min=0;
        int minindex=0;
        double[] snapX=new double[Mnum];
        double[] snapY=new double[Mnum];
        String snapvalue="<html>"+"GRAPH TITLE:\t"+this.getTitle();
        for(int i=0;i<Mnum;i++){
            boolean flag=false;
            for(int s=0;s<data.getItemCount(i);s++){
                double a=plot.getDomainAxis().valueToJava2D(data.getXValue(i, s), plotArea, plot.getDomainAxisEdge());
//                if(Math.abs(a-x)>25)continue;
                double b=plot.getRangeAxis().valueToJava2D(data.getYValue(i, s), plotArea, plot.getRangeAxisEdge());
                double length=Math.pow(a-x, 2)+Math.pow(b-y, 2);
                if(flag==false){
                    flag=true;
                    min=length;
                    minindex=s;
                    continue;
                }else if(length<min){
                    min=length;
                    minindex=s;
                    continue;
                }
            }
            snapX[i]=data.getXValue(i, minindex);
            snapY[i]=data.getYValue(i, minindex);
            snapvalue+="<br>KEY INDEX   "+i+"; INDEX   "+minindex+"   X座標 "+snapX[i]+"    Y座標 "+snapY[i]+"\n";
            l[i].setText("KEY INDEX   "+i+"; INDEX   "+minindex+"   X座標 "+snapX[i]+"    Y座標 "+snapY[i]);
        }
        if(status!=null){
//            status=new JLabel();
            status.setFont(new Font("KonatuTohaba",0,20));
            status.setText(snapvalue);
        }else{
            System.out.println(snapvalue);
            System.out.println();
        }
        int pind=ind;
        System.out.println(ind+"th plot will shaped!!!!!");
        data.addSeries(pind, new double[][]{snapX,snapY});
        shape.setSeriesShapesVisible(pind, true);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(pind, false);
        shape.setSeriesPaint(pind, Color.cyan);
        shape.setSeriesShape(ind, 
                new java.awt.geom.Ellipse2D.Double(-10,-10,20,20), true);
//        this.setPanelWithToolBar(cpanel, text);
//        this.update();
    }
    public void setStatuslabel(JLabel label){
        status=label;
    }
    
    public void setMouseVerify(boolean trueVerify){
        mouseVerify=trueVerify;
    }

    public void mousePressed(MouseEvent e){
        cpanel.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e){
        cpanel.mouseReleased(e);
    }
    public void mouseEntered(MouseEvent e){
        cpanel.mouseEntered(e);
    }
    public void mouseExited(MouseEvent e){
        cpanel.mouseExited(e);
    }
    public void update(){
        this.result();
        chart.getXYPlot().datasetChanged(new DatasetChangeEvent(data,data));
        chart.plotChanged(new PlotChangeEvent(chart.getXYPlot()));
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
    public void myMostPopularOutputSetting(String path){
        this.removelegend();
        this.T=false;
        this.OUTPUTasPNG(path+".png", 500, 500);
    }
    public void OUTPUTasSVG(String pass,int width,int height){
        this.result();
        DOMImplementation domImpl=GenericDOMImplementation.getDOMImplementation();// DOMImplementationの取得
        Document document=domImpl.createDocument(null, "svg", null);// XMLドキュメントの作成
        SVGGraphics2D svg2d=new SVGGraphics2D(document);// SVGジェネレータの作成
        Rectangle2D r2d=new Rectangle(width,height);
        
        int n=this.chart.getXYPlot().getSeriesCount();
        for(int i=0;i<n;i++){
            this.chart.getXYPlot().getRenderer().setSeriesVisible(i, false);
        }
        this.chart.getXYPlot().getRenderer().setSeriesVisible(0, true);
        this.chart.draw(svg2d, r2d);// Chartの描画
        for(int i=1;i<n;i++){
            this.chart.getXYPlot().getRenderer().setSeriesVisible(i-1, false);
            this.chart.getXYPlot().getRenderer().setSeriesVisible(i, true);
            this.chart.draw(svg2d, r2d);// Chartの描画
        }        
        boolean useCSS=true;//CSSを使用する
        try{
            OutputStream os=new FileOutputStream(pass);
            BufferedOutputStream bos=new BufferedOutputStream(os);
            Writer out=new OutputStreamWriter(bos,"UTF-8");//文字コードの指定
            svg2d.stream(new BufferedWriter(out),useCSS);//出力
            svg2d.dispose();
            out.close();
            bos.close();
            os.close();
        }catch(UnsupportedEncodingException ue){ue.printStackTrace();}
        catch(SVGGraphics2DIOException se){se.printStackTrace();}
        catch(IOException ioe){ioe.printStackTrace();}
    }
    public void OUTPUTasSVG(String pass,int width,int height,int skip){
        int num=data.getSeriesCount();
        ArrayList<double[][]> vals=new ArrayList<double[][]>();
        ArrayList<Comparable> keys=new ArrayList<Comparable>();
        for (int i = 0; i < num; i++) {
            int num2=data.getItemCount(i);
            ArrayList<double[]> vals2=new ArrayList<double[]>();
            int ind4=0;
//            System.out.println("series "+i);
            for (int j = 0; j < num2; j++) {
                if(ind4==skip||ind4==0||ind4==num2-1){
//                    System.out.println("value_added");
                    ind4=0;
                    vals2.add(new double[]{data.getXValue(i, j),
                        data.getYValue(i, j)});
                }
                ind4++;
            }
            double[][] vals3=new double[2][vals2.size()];
            for (int j = 0; j < vals2.size(); j++) {
                vals3[0][j]=vals2.get(j)[0];
                vals3[1][j]=vals2.get(j)[1];
            }
            keys.add(data.getSeriesKey(i));
            vals.add(vals3);
        }
        data=new DefaultXYDataset();
        for (int i = 0; i < vals.size(); i++) {
            data.addSeries(keys.get(i), vals.get(i));
        }
        this.OUTPUTasSVG(pass, width, height);
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
    public void OUTPUTasPDF(String pass,int width,int height){
        this.result();
        try{int w=(int)PageSize.A4.getWidth();
            com.itextpdf.text.Document document=new com.itextpdf.text.Document();
            PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(pass));
            document.open();
            PdfContentByte cb=writer.getDirectContent();
            PdfTemplate pt=cb.createTemplate(width, height);
            PdfGraphics2D pg2d=new PdfGraphics2D(pt,width,height);
            Rectangle2D r2d=new Rectangle(width,height);
            this.chart.draw(pg2d, r2d);
            pg2d.dispose();
            cb.addTemplate(pt, 0,height/2);
            document.close();
        }catch(Exception ioe){ioe.printStackTrace();}
    }
    public void setPointFlag(boolean true_with_toolbar){
        PointFlag=true_with_toolbar;
    }
    public void setFontSize(int fontsize){
        this.fontsize=fontsize;
    }
    public void PLOT(){
        this.result();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, 500, 500);
        this.setTitle("グラフサンプル");
        this.setVisible(true);
    }
    public void PLOT(int width,int height){
        this.result();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(10, 10, width,height);
        this.setTitle("グラフサンプル");
        this.setVisible(true);
    }
}
