/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrameCreator;

import util.LineInfo;
import util.coord.CoordXYZ;
import util.section.SectionInterface;

/**
 *
 * @author keita
 */
public class FAMID {
    public static final int TYPE_BEAM=0;
    public static final int TYPE_COLUMN=1;
    public static final int TYPE_BRACE=2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final SectionInterface sec;
    private final LineInfo line;
    private final int type;
    private final String label;
    private final double theta;
    private final double[] offset;
    private final String[] end;
    public FAMID(
            LineInfo line,
            SectionInterface sec,
            int Membertype,
            String label,
            double theta,
            String[] offset
    ){
        this.line=line;
        this.sec=sec;
        this.type=Membertype; 
        this.label=label;
        this.theta=theta;
        double end1 = 0;
        double end2 = 0;
        if ("p".equals(offset[0].toLowerCase())) {
            end1 = 600;
        }else if (offset[0].length()>1&&offset[0].contains("r")){
            end1=Double.parseDouble(offset[0].substring(1));
        }
        if ("p".equals(offset[1].toLowerCase())) {
            end2 = 600;
        }else if (offset[1].length()>1&&offset[1].contains("r")){
            end2=Double.parseDouble(offset[1].substring(1));
        }
        this.end=offset;
        this.offset=new double[]{end1,end2};
    }
    public String[] getEndInfo(){return end;}
    public double[] getOffset(){return offset;}
    public LineInfo getLineInfo(){return line;}
    public SectionInterface getSectionInfo(){return sec;}
    public int getMemberType(){return type;}
    public String getLabel(){return label;}
    public double getTheta(){return theta;}
    
    public CoordXYZ[] getTextPosition(int type){
        return sec.getTextPosition(type);
    }
    
}
