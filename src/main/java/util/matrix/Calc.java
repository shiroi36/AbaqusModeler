/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.matrix;

import java.util.Arrays;
import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import util.Util;
import util.coord.CoordXYZ;


/**
 *
 * @author keita
 */
public class Calc {
    
    private final RealVector Wx = new ArrayRealVector(new double[]{1, 0, 0});
    private final RealVector Wy = new ArrayRealVector(new double[]{0, 1, 0});
    private final RealVector Wz = new ArrayRealVector(new double[]{0, 0, 1});

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Calc a=new Calc();
        RealMatrix rm=a.getOCSUnit(new CoordXYZ(1,0,0));
        new Util().printMatrix(rm);
        rm=a.getOCSUnit(new CoordXYZ(0,0,1));
        new Util().printMatrix(rm);
        
    }

    public Calc(){
    }

    
    public RealMatrix getOCSUnit(CoordXYZ az){
        RealVector azv=new ArrayRealVector(new double[]{az.getX(),az.getY(),az.getZ()});
        azv=azv.mapMultiply(1.0/azv.getNorm());
        azv.getNorm();
        RealVector axv;
        if((Math.abs(azv.getEntry(0))<1.0/64.0)&&(Math.abs(azv.getEntry(1))<1.0/64.0)){
            axv=this.getGaiseki3D(Wy, azv);
//            System.out.println("true");
        }else{
            axv=this.getGaiseki3D(Wz, azv);
//            System.out.println("true");
        }
        axv=axv.mapMultiply(1.0/axv.getNorm());
        RealVector ayv=this.getGaiseki3D(azv, axv);
        ayv=ayv.mapMultiply(1.0/ayv.getNorm());
//        Realvector axv=new ArrayReal
        RealMatrix ans=MatrixUtils.createRealMatrix(3,3);
        ans.setColumnVector(0, axv);
        ans.setColumnVector(1, ayv);
        ans.setColumnVector(2, azv);
        return ans;
    }
    
    public CoordXYZ getCoordInOCS(CoordXYZ wcs,CoordXYZ zaxis){
//        wcs.print();
        RealMatrix trans=this.getOCSUnit(zaxis);
//        new Util().printMatrix(trans);
        RealMatrix a=this.convertRealMatrix(new CoordXYZ[]{wcs});
        RealMatrix b0= new LUDecomposition(trans).getSolver().getInverse();
//        new Util().printMatrix(b0);
//        new Util().printMatrix(trans.multiply(b0));
        RealMatrix b= b0.multiply(a);
        return new CoordXYZ(b.getEntry(0, 0),b.getEntry(1, 0),b.getEntry(2, 0));
    }
    
    public CoordXYZ getCoordInWCS(CoordXYZ ocs,CoordXYZ zaxis){
        RealMatrix trans=this.getOCSUnit(zaxis);
        RealMatrix a=this.convertRealMatrix(new CoordXYZ[]{ocs});
        RealMatrix b=trans.multiply(a);
        return new CoordXYZ(b.getEntry(0, 0),b.getEntry(1, 0),b.getEntry(2, 0));
    }
    
    public RealMatrix convertRealMatrix(CoordXYZ[] a){
        double[][] v=new double[3][a.length];
        for (int i = 0; i < a.length; i++) {
            CoordXYZ a1 = a[i];
            v[0][i]=a1.getX();
            v[1][i]=a1.getY();
            v[2][i]=a1.getZ();
        }
        return MatrixUtils.createRealMatrix(v);
    }
    
    public RealVector getGaiseki3D(RealVector a,RealVector b){
        double v1=a.getEntry(1)*b.getEntry(2)-a.getEntry(2)*b.getEntry(1);
        double v2=a.getEntry(2)*b.getEntry(0)-a.getEntry(0)*b.getEntry(2);
        double v3=a.getEntry(0)*b.getEntry(1)-a.getEntry(1)*b.getEntry(0);
        return new ArrayRealVector(new double[]{v1,v2,v3});
    }
    
    
    public CoordXYZ getRotate(CoordXYZ p,CoordXYZ vector,double theta_inD){
        RealMatrix n=MatrixUtils.createRowRealMatrix(new double[]{vector.getX(),vector.getY(),vector.getZ()});
        
        Util u=new Util();
//        u.printMatrix();
        double norm=Math.sqrt(n.multiply(n.transpose()).getEntry(0, 0));
        n=n.scalarMultiply(1.0/norm);
        double n1=n.getEntry(0, 0);
        double n2=n.getEntry(0, 1);
        double n3=n.getEntry(0, 2);
        
        double theta=theta_inD/360*2*Math.PI;
        double s=Math.sin(theta);
        double c=Math.cos(theta);
        
        RealMatrix tr=MatrixUtils.createRealMatrix(
                new double[][]{
                    {n1*n1+(1-n1*n1)*c,n1*n2*(1-c)+n3*s,n1*n3*(1-c)-n2*s,0.0},
                    {n1*n2*(1-c)-n3*s,n2*n2+(1-n2*n2)*c,n2*n3*(1-c)+n1*s,0.0},
                    {n1*n3*(1-c)+n2*s,n2*n3*(1-c)-n1*s,n3*n3+(1-n3*n3)*c,0.0},
                    {0,0,0,1.0},
                });
        
        RealMatrix point=MatrixUtils.createRealMatrix(new double[][]{{p.getX(),p.getY(),p.getZ(),1.0}});
        RealMatrix ans=point.multiply(tr);
        
        
//        u.printMatrix(n);
//        u.printMatrix(point);
//        u.printMatrix(tr);
//        u.printMatrix(ans);
        
        
        return new CoordXYZ(ans.getEntry(0, 0),ans.getEntry(0, 1),ans.getEntry(0, 2));
    }

}
