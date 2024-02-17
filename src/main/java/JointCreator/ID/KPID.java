/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JointCreator.ID;

/**
 *
 * @author keita
 */
public class KPID {

    public static String MODE_4="4";
    public static String MODE_6="6";
    public static String SERIES_N="N";
    public static String SERIES_R="R";
    public static String ICON_KPL="kpl";
    public static String ICON_KPR="kpr";
    public static String ICON_KP="kp";
    
    public static final KPID C400_N16_4M22=
            new KPID(KPID.MODE_4,KPID.SERIES_N,"C400-N16-4M22",16,16,24);
    public static final KPID C400_N19_6M20=
            new KPID(KPID.MODE_6,KPID.SERIES_N,"C400-N19-6M20",19,19,22);
    public static final KPID C400_N22_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_N,"C400-N22-6M22",22,22,24);
    public static final KPID C400_N25_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_N,"C400-N25-6M22",25,25,24);
    
    public static final KPID C400_R22_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C400-R22-6M22",22,22,24);
    public static final KPID C400_R25_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C400-R25-6M22",25,25,24);
    
    public static final KPID C400_R28_6M24=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C400-R28-6M24",28,28,26);
    
    public static final KPID C490_N16_4M22=
            new KPID(KPID.MODE_4,KPID.SERIES_N,"C490-N16-4M22",16,16,24);
    public static final KPID C490_N19_6M20=
            new KPID(KPID.MODE_6,KPID.SERIES_N,"C490-N19-6M20",19,19,22);
    public static final KPID C490_N22_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_N,"C490-N22-6M22",22,22,24);
    public static final KPID C490_N25_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_N,"C490-N25-6M22",25,25,24);
    
    public static final KPID C490_R22_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C490-R22-6M22",22,22,24);
    public static final KPID C490_R25_6M22=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C490-R25-6M22",25,25,24);
    
    public static final KPID C490_R28_6M24=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C490-R28-6M24",28,28,26);
    public static final KPID C490_R32_6M24=
            new KPID(KPID.MODE_6,KPID.SERIES_R,"C490-R32-6M24",32,32,26);
    
    public static final KPID[] KPSET=new KPID[]{
        KPID.C400_N16_4M22,
        KPID.C400_N19_6M20,
        KPID.C400_N22_6M22,
        KPID.C400_N25_6M22,
        KPID.C400_R22_6M22,
        KPID.C400_R25_6M22,
        KPID.C400_R28_6M24,
        KPID.C490_N16_4M22,
        KPID.C490_N19_6M20,
        KPID.C490_N22_6M22,
        KPID.C490_N25_6M22,
        KPID.C490_R22_6M22,
        KPID.C490_R25_6M22,
        KPID.C490_R28_6M24,
        KPID.C490_R32_6M24
    };
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        KPID a=KPID.C400_N16_4M22;
    }
    
    private final String aname;
    private double tk1;
    private double tk2;
    private String basecore;
    private String wingcore;
    private final double D;
    private final String mode;
    private final String series;
    private double winglength;
    private double baselength;

    public KPID(
            String mode, 
            String series,
            String aname, 
            double tk1, 
            double tk2, 
            double D
    ) {
        basecore = "";
        wingcore = "";
        this.aname=aname;
        this.mode=mode;
        this.series=series;
        if (mode == KPID.MODE_4&&series==KPID.SERIES_N) {
            basecore = "dxf/core/4bn.dxf";
            this.winglength=140.0;
            this.baselength=260.0;
            wingcore = "dxf/core/4w.dxf";
        }else if(mode == KPID.MODE_6&&series==KPID.SERIES_N) {
            basecore = "dxf/core/6bn.dxf";
            this.winglength=200.0;
            this.baselength=380.0;
            wingcore = "dxf/core/6w.dxf";
        }else if(mode == KPID.MODE_6&&series==KPID.SERIES_R) {
            basecore = "dxf/core/6br.dxf";
            this.winglength=200.0;
            this.baselength=380.0;
            wingcore = "dxf/core/6w.dxf";
        }
        this.tk1=tk1;
        this.tk2=tk2;
        this.D=D;
    }
        
        public double getWingBoltConnectionLength(){return winglength;}
        public double getBaseBoltConnectionLength(){return baselength;}
        
        public String getBaseCoreShape(){return basecore;}
        public String getWingCoreShape(){return wingcore;}
        public String getAttachmentName(){return aname;}
        public double getD(){return D;}
        public double getgk(){
            if(series==KPID.SERIES_N)return 65;
            else if(series==KPID.SERIES_R)return 78;
            else return 0;
        }
        public double gettk1(){return this.tk1;}
        public double gettk2(){return this.tk2;}
        public double gethk(){return 80;}
        public String getMode(){return mode;}
        public String getSeries(){return series;}
        public double getWingPlateLength(){
            if(mode==KPID.MODE_4)return 140;
            else if(mode==KPID.MODE_6)return 200;
            else return 0;
        }
        public int getBoltnum(){
            if(mode==KPID.MODE_4)return 4;
            else if(mode==KPID.MODE_6)return 6;
            else return 0;
        }
}
