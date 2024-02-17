/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util.GRAPH_LIB.XY;

import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.data.xy.XYDataset;
/**
 *
 * @author keita
 */
public class LabelGenerator extends StandardXYItemLabelGenerator {
    int[] ind;
    int[] s;
    String[] val;
    public LabelGenerator(int[] series,int[] item,String[] value){
        val=value;
        ind=item;
        s=series;
    }
    public String generateLabel(XYDataset dataset, int series, int item) {
        for(int i=0;i<ind.length;i++){
            if(item==ind[i]&&series==s[i]){
                return val[i];
            }
        }
        return null;
    }

}
