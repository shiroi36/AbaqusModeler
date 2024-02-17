/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.coord;

/**
 *
 * @author keita
 */
public class CoordXYZ {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private double y;
    private double x;
    private double z;
    private final String name;
    public CoordXYZ(double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name="";
    }
    public CoordXYZ(String name,double x,double y,double z){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name=name;
    }
    
    public double getX(){return x;}
    
    public double getY(){return y;}
    
    public double getZ(){return z;}
    
    public String getName(){return "( "+x+", "+y+", "+z+" )";}
    
    public double getLength(){return Math.sqrt(x*x+y*y+z*z);}
    
    public void print(){
        System.out.println(name+"\t"+x+","+y+","+z);
    }
    
    public void add(CoordXYZ p){
        x+=p.getX();
        y+=p.getY();
        z+=p.getZ();
    }
    
    public void multiply(double mag){
        x*=mag;
        y*=mag;
        z*=mag;
    }
    
    public void multiply(CoordXYZ mag){
        x*=mag.getX();
        y*=mag.getY();
        z*=mag.getZ();
    }
    
    public boolean compare(CoordXYZ p){
        double a=Math.sqrt(Math.pow(p.getX()-x, 2)
                +Math.pow(p.getY()-y, 2)+Math.pow(p.getZ()-z, 2));
        if(a<0.001)return true;
        else return false;
    }
    
}
