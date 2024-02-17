/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.GRAPH_LIB.BAR;

import util.GRAPH_LIB.GLInterface;
import util.GRAPH_LIB.GP.GPInterface;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.axis.*;
import org.jfree.chart.event.PlotChangeEvent;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetChangeEvent;
import org.jfree.data.xy.DefaultXYDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.TextAnchor;
import org.sourceforge.jlibeps.epsgraphics.EpsGraphics2D;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;


/**
 *XY軸のグラフをプロットするクラスです。
 * 主に変更する数値はメソッドにて書き下しますが、めったに変更しないだろうと判断した設定事項については
 * result()メソッド内にて変更する(※印部分)という方法をとります。
 * @author keita
 */
public class StackedBarChart extends JFrame implements MouseListener,GPInterface,GLInterface{
    String title,Xlabel,Ylabel,display;//タイトルの大きさ等の設定はJAVADRIVEにて参照のこと
     DefaultCategoryDataset data;//addするには初期化しておくことが必要
    double Yinterval,Ymin,Ymax;
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
    
    public static void main(String[] args) {
        StackedBarChart sbc=new StackedBarChart(22);
        sbc.setValue(100, "A", "0");
        sbc.setValue(100, "A", "1");
        sbc.setValue(100, "A", "2");
        sbc.setValue(100, "B", "0");
        sbc.setValue(100, "B", "1");
        sbc.setValue(100, "B", "2");
        sbc.PLOT();
    }
    public StackedBarChart(){
        this(20);
    }
    public StackedBarChart(int fontsize){
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());

        this.fontsize=fontsize;
        shape=new XYLineAndShapeRenderer();
        LineColor=new ArrayList<Color>();
        LineColorSeries=new ArrayList<Integer>();
        LineStroke=new ArrayList<BasicStroke>();
        LineStrokeSeries=new ArrayList<Integer>();
        ConstInd=new ArrayList<Integer>();
        ConstSeries=new ArrayList<Integer>();
        ConstString=new ArrayList<String>();
        data= new DefaultCategoryDataset();
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
        ind=0;
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
    public void setValue(double val,java.lang.Comparable key,java.lang.Comparable ykey){
        data.addValue(val, key, ykey);
    }
     public void removeAllValue(){
        data=new  DefaultCategoryDataset();
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
    /**
     *設定結果をまとめて、PLOTクラスでプロットする内容を表すメソッドです。
     */
    public void result(){
        //////////////グラフに関する内容/////////////////////////////////////////////////
        ChartFactory.setChartTheme(StandardChartTheme.createLegacyTheme());
        chart=ChartFactory.createStackedBarChart(title, Xlabel, Xlabel, data,
                PlotOrientation.VERTICAL, true, false, false);
        
        chart.setBackgroundPaint(Color.white);//※外の背景の色を設定するときはこれ※
        
        
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


        /////X軸に関する内容///////////////////////////////////////////////////////////////
        CategoryAxis xaxis=new CategoryAxis();//rangeaxis…Y軸
        xaxis.setTickLabelsVisible(true);//ラベルを表示するか否か
        xaxis.setVisible(true);//軸を全て消すかどうか※
        xaxis.setMinorTickMarksVisible(true);//ちっちゃい目盛を表示するか否か※
        xaxis.setTickMarksVisible(true);//大きな目盛を表示するか否か※
        xaxis.setLabelFont(XYlabelFont);
        xaxis.setTickLabelFont(XYtickFont);
        xaxis.setTickLabelsVisible(ticklabel);//目盛の数値を消すかどうかということ
        chart.getCategoryPlot().setDomainAxis(xaxis);
//                getXYPlot().setRangeAxis(yaxis);
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
        yaxis.setTickLabelsVisible(ticklabel);//目盛の数値を消すかどうかということ
        chart.getCategoryPlot().setRangeAxis(yaxis);
//                getXYPlot().setRangeAxis(yaxis);
        
        ////////////////////レンダー設定///////////////////////////////////////
        BarRenderer renderer = (BarRenderer) chart.getCategoryPlot().getRenderer();
        // 棒の間隔を設定
        renderer.setItemMargin(30);
        // 影の設定
        renderer.setShadowVisible(false);

    // シリーズの設定
        renderer.setSeriesPaint(0, Color.RED);
        renderer.setSeriesPaint(1, Color.BLUE);
        renderer.setSeriesPaint(2, Color.green);
        renderer.setSeriesPaint(2, Color.cyan);
        renderer.setSeriesPaint(2, Color.magenta);



        cpanel =new ChartPanel(chart);
        cpanel.addMouseListener(this);
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
    public  DefaultCategoryDataset getXYDataset(){
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
        
        int n=this.chart.getCategoryPlot().getDatasetCount();
        for(int i=0;i<n;i++){
            this.chart.getCategoryPlot().getRenderer().setSeriesVisible(i, false);
        }
        this.chart.getCategoryPlot().getRenderer().setSeriesVisible(0, true);
        this.chart.draw(svg2d, r2d);// Chartの描画
        for(int i=1;i<n;i++){
            this.chart.getCategoryPlot().getRenderer().setSeriesVisible(i-1, false);
            this.chart.getCategoryPlot().getRenderer().setSeriesVisible(i, true);
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
