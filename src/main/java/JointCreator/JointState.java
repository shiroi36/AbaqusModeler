/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JointCreator;

/**
 *
 * @author keita
 */
public class JointState {
    public static final JointState INTERMEDIATE_X
            =new JointState(JointState.XSHAPE,JointState.INTERMEDIATE);
    public static final JointState INTERMEDIATE_T
            =new JointState(JointState.TSHAPE,JointState.INTERMEDIATE);
    public static final JointState RF_X
            =new JointState(JointState.XSHAPE,JointState.RF);
    public static final JointState RF_T
            =new JointState(JointState.TSHAPE,JointState.RF);
    private static final int XSHAPE=0;
    private static final int TSHAPE=1;
    private static final int RF=2;
    private static final int INTERMEDIATE=3;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final int isx;
    private final int isrf;
    private JointState(int isXShape,int isRF){
        this.isx=isXShape;
        this.isrf=isRF;
    }
    
    public boolean isXShape(){
        if(isx==XSHAPE)return true;
        else return false;
    }
    public boolean isRF(){
        if(isrf==RF)return true;
        else return false;
    }
    
}
