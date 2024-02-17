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
public class XYZSW {
        public static XYZSW X=new XYZSW("X方向");
        public static XYZSW Y=new XYZSW("Y方向");
        public static XYZSW Z=new XYZSW("Z方向");
        public static XYZSW S=new XYZSW("強軸");
        public static XYZSW W=new XYZSW("弱軸");
        private final String name;
        private XYZSW(String name){
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
