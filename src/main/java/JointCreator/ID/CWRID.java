/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JointCreator.ID;

import JointCreator.JointState;

/**
 *
 * @author keita
 */
public class CWRID {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    public static String ICON_CWR="cwr";
    public static final String CWR400="CWR400";
    public static final String CWR490="CWR490";
    public static final int TYPE4=4;
    public static final int TYPE6=6;
    private final String header;
    private final double t;
    private final double l;
    private final int boltnum;
    private final int bolthole;
    private final JointState jointstate;
    public CWRID(String header,double t,double l,int boltnum,int bolthole,JointState jointstate){
        this.header=header;
        this.t=t;
        this.l=l;
        this.boltnum=boltnum;
        this.bolthole=bolthole;
        this.jointstate=jointstate;
    }
    public String getHeader(){return header;}
    public JointState getJointState(){return jointstate;}
    public double getThickness(){return t;}
    public double getLcfw(){return l;}
    public int getBoltHoleType(){return boltnum;}
    public int getBoltHoleSize(){return bolthole;}
    public String getName(){return header+"-"+(int)t+"-"+l+"-"+boltnum+"M"+bolthole;}
    public double getWidth(){return 140;}
    
}
