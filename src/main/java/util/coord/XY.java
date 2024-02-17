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
public class XY {
        public static XY X=new XY("X方向");
        public static XY Y=new XY("Y方向");
        private final String name;
        private XY(String name){
            this.name=name;
        }
        public String getName(){return name;}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
