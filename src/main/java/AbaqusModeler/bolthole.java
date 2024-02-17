/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AbaqusModeler;

/**
 *
 * @author keita
 */
public class bolthole {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
        private final double holesize;
        private final double nutsize;
        private final double nutheight;
        private final double axislength;
        private final double axissize;
        public bolthole(
                double holesize,
                double nutsize,
                double nutheight,
                double axislength,
                double axissize){
            this.holesize=holesize;
            this.nutsize=nutsize;
            this.nutheight=nutheight;
            this.axislength=axislength;
            this.axissize=axissize;
        }
        public double getHoleSize(){return holesize;}
        public double getNutSize(){return nutsize;}
        public double getNutHeight(){return nutheight;}
        public double getAxisLength(){return axislength;}
        public double getAxisSize(){return axissize;}
}
