/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.XY.WITHFFT;

import util.GRAPH_LIB.GP.GPInterface;
import util.GRAPH_LIB.GLInterface;
import util.GRAPH_LIB.XY.LabelGenerator;
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
import java.awt.event.InputEvent;
import javax.swing.SwingUtilities;


/**
 *XY軸のグラフをプロットするクラスです。
 * 主に変更する数値はメソッドにて書き下しますが、めったに変更しないだろうと判断した設定事項については
 * result()メソッド内にて変更する(※印部分)という方法をとります。
 * @author keita
 */
public class ANALYZEDGRAPH extends JFrame implements MouseListener,GPInterface,GLInterface{
    String title,Xlabel,Ylabel,display;//タイトルの大きさ等の設定はJAVADRIVEにて参照のこと
    DefaultXYDataset data;//addするには初期化しておくことが必要
    double Xinterval,Yinterval,Xmin,Xmax,Ymin,Ymax;
    int Xpartition,Ypartition,ind;
    ArrayList<Integer> LineStrokeSeries;
    ArrayList<BasicStroke> LineStroke;
    ArrayList<Integer> LineColorSeries;
    boolean X1,Y1,X2,Y2,leg,LCS,T,LogX,LogY,mouseflag,mouseVerify,ticklabel;
    XYLineAndShapeRenderer shape;
    JLabel l;
    JFreeChart chart;
    ChartPanel cpanel;
    
    ArrayList<Integer> ConstInd;
    ArrayList<Integer> ConstSeries;
    ArrayList<String> ConstString;
    private int fontsize;
    private JLabel status;
    
    public ANALYZEDGRAPH(java.lang.Comparable key,double[][] XYvalue){
        this(20);
        data.addSeries(key, XYvalue);
        shape.setSeriesShapesVisible(ind, false);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(ind, true);
        ind++;
    }
    public ANALYZEDGRAPH(int fontsize){
        this.fontsize=fontsize;
        shape=new XYLineAndShapeRenderer();
        LineColorSeries=new ArrayList<Integer>();
        LineStroke=new ArrayList<BasicStroke>();
        LineStrokeSeries=new ArrayList<Integer>();
        ConstInd=new ArrayList<Integer>();
        ConstSeries=new ArrayList<Integer>();
        ConstString=new ArrayList<String>();
        data=new DefaultXYDataset();
        mouseflag=true;
        mouseVerify=true;
        ticklabel=true;
        X1=false;
        Y1=false;
        X2=false;
        Y2=false;
        leg=false;
        T=false;
        LogX=false;
        LogY=false;
        ind=0;
    }

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
    /**
     * 線の色を決めるメソッド
     * @param Series　色を変える線の識別記号。凡例。
     * @param C　設定する線の色。
     */
    public void setLineColor(Color C){
        shape.setSeriesPaint(0, C);
    }
    public void setTickLabel(boolean ticklabel){
        this.ticklabel=ticklabel;
    }
    /**
     *設定結果をまとめて、PLOTクラスでプロットする内容を表すメソッドです。
     */
    public void result(){
        data.addSeries(1, new double[][]{{0},{0}});
        shape.setSeriesShapesVisible(1, true);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(1, false);
        shape.setSeriesPaint(1, Color.cyan);
        BasicStroke Line=new BasicStroke(2.0f);
        data.addSeries(2, new double[][]{{0},{0}});
        shape.setSeriesStroke(2,Line);
        shape.setSeriesShapesVisible(2, false);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(2, true);
        shape.setSeriesPaint(2, Color.blue);
        data.addSeries(3, new double[][]{{0},{0}});
        shape.setSeriesStroke(3,Line);
        shape.setSeriesShapesVisible(3, false);//※プロット点を表示するか否かということ。逆に線を表示するか否かというメソッドもXYLineAndShapeRendererに存在するので相関図とか書きたかったらそこらへんをいじるといいよ。
        shape.setSeriesLinesVisible(3, true);
        shape.setSeriesPaint(3, Color.GREEN);
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
        xaxis.setTickLabelsVisible(ticklabel);//目盛の数値を消すかどうかということ
        chart.getXYPlot().setDomainAxis(xaxis);
        /////Y軸に関する内容///////////////////////////////////////////////////////////////
        NumberAxis yaxis;//rangeaxis…Y軸
        if(LogY==true){yaxis=new LogarithmicAxis(Ylabel);}
        else{yaxis=new NumberAxis(Ylabel);}
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
        yaxis.setTickLabelsVisible(ticklabel);//目盛の数値を消すかどうかということ
        chart.getXYPlot().setRangeAxis(yaxis);
        chart.getXYPlot().setRenderer(shape);
        

        cpanel =new ChartPanel(chart);
        cpanel.addMouseListener(this);
        if(mouseVerify==false){
            cpanel.removeMouseListener(this);
        }
        l=new JLabel();
        Font font=new Font("KonatuTohaba",0,10);
            l=new JLabel();
            l.setFont(font);
//            l.setBackground(Color.white);
            l.setText("index"+0);
        this.setPanelWithToolBar(cpanel, l);
    }
    public int getSeriesCount(){
        return data.getSeriesCount();
    }
    private void setPanelWithToolBar(ChartPanel c,JLabel l){
        JPanel pane=new JPanel();
        pane.setLayout(new GridLayout(1,1));
        pane.setBackground(Color.white);
        pane.add(l);
        
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
        if(SwingUtilities.isMiddleMouseButton(e)){
            int x =e.getX();
            int y =e.getY();
            double[] snap=this.pressbutton2(x, y);
            data.addSeries(1, new double[][]{{snap[0]},{snap[1]}});
        }else if(SwingUtilities.isLeftMouseButton(e)){
            int x =e.getX();
            if((e.getModifiersEx()&InputEvent.SHIFT_DOWN_MASK)!=0){
                data.addSeries(3, this.endpoint(x));
            }else{
                data.addSeries(2, this.startpoint(x));
            }
        }
        this.update();
    }
    public double[][] endpoint(int x){
        Rectangle2D plotArea = cpanel.getScreenDataArea();
        XYPlot plot = (XYPlot) chart.getPlot(); // your plot
        double min=0;
        int minindex=0;
        double snapX;
        double ymax,ymin;
        String snapvalue="<html>"+"GRAPH TITLE:\t"+this.getTitle();
            boolean flag=false;
            for(int s=0;s<data.getItemCount(0);s++){
                double a=plot.getDomainAxis().valueToJava2D(data.getXValue(0, s), plotArea, plot.getDomainAxisEdge());
                if(Math.abs(a-x)>25)continue;
                double length=Math.abs(a-x);
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
            snapX=data.getXValue(0, minindex);
            ymax=plot.getRangeAxis().getUpperBound();
            ymin=plot.getRangeAxis().getLowerBound();
            double minus=(ymax-ymin)*0.05;
            ymax-=minus;
            ymin+=minus;
            snapvalue+="<br>KEY INDEX   "+0+"; INDEX   "+minindex+"   X座標 "+snapX+"\n";
            l.setText("START!! KEY INDEX   "+0+"; INDEX   "+minindex+"   X座標 "+snapX);
        if(status!=null){
//            status=new JLabel();
            status.setFont(new Font("KonatuTohaba",0,20));
            status.setText(snapvalue);
        }else{
            System.out.println(snapvalue);
            System.out.println();
        }
        return new double[][]{{snapX,snapX},{ymax,ymin}};
    }
    public double[][] startpoint(int x){
        Rectangle2D plotArea = cpanel.getScreenDataArea();
        XYPlot plot = (XYPlot) chart.getPlot(); // your plot
        double min=0;
        int minindex=0;
        double snapX;
        double ymax,ymin;
        String snapvalue="<html>"+"GRAPH TITLE:\t"+this.getTitle();
            boolean flag=false;
            for(int s=0;s<data.getItemCount(0);s++){
                double a=plot.getDomainAxis().valueToJava2D(data.getXValue(0, s), plotArea, plot.getDomainAxisEdge());
                if(Math.abs(a-x)>25)continue;
                double length=Math.abs(a-x);
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
            snapX=data.getXValue(0, minindex);
            ymax=plot.getRangeAxis().getUpperBound();
            ymin=plot.getRangeAxis().getLowerBound();
            double minus=(ymax-ymin)*0.05;
            ymax-=minus;
            ymin+=minus;
            snapvalue+="<br>KEY INDEX   "+0+"; INDEX   "+minindex+"   X座標 "+snapX+"\n";
            l.setText("START!! KEY INDEX   "+0+"; INDEX   "+minindex+"   X座標 "+snapX);
        if(status!=null){
//            status=new JLabel();
            status.setFont(new Font("KonatuTohaba",0,20));
            status.setText(snapvalue);
        }else{
            System.out.println(snapvalue);
            System.out.println();
        }
        return new double[][]{{snapX,snapX},{ymax,ymin}};
    }
    public double[] pressbutton2(int x,int y){
        Rectangle2D plotArea = cpanel.getScreenDataArea();
        XYPlot plot = (XYPlot) chart.getPlot(); // your plot
        double min=0;
        int minindex=0;
        double snapX;
        double snapY;
        String snapvalue="<html>"+"GRAPH TITLE:\t"+this.getTitle();
            boolean flag=false;
            for(int s=0;s<data.getItemCount(0);s++){
                double a=plot.getDomainAxis().valueToJava2D(data.getXValue(0, s), plotArea, plot.getDomainAxisEdge());
                if(Math.abs(a-x)>25)continue;
                double b=plot.getRangeAxis().valueToJava2D(data.getYValue(0, s), plotArea, plot.getRangeAxisEdge());
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
            snapX=data.getXValue(0, minindex);
            snapY=data.getYValue(0, minindex);
            snapvalue+="<br>KEY INDEX   "+0+"; INDEX   "+minindex+"   X座標 "+snapX+"    Y座標 "+snapY+"\n";
            l.setText("KEY INDEX   "+0+"; INDEX   "+minindex+"   X座標 "+snapX+"    Y座標 "+snapY);
        
        if(status!=null){
//            status=new JLabel();
            status.setFont(new Font("KonatuTohaba",0,20));
            status.setText(snapvalue);
        }else{
            System.out.println(snapvalue);
            System.out.println();
        }
        return new double[]{snapX,snapY};
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
