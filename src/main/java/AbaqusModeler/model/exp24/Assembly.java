/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbaqusModeler.model.exp24;

import AbaqusModeler.Modeler.Assembler;
import util.coord.CoordXYZ;
import util.io.TXT_OPE;

/**
 *
 * @author user
 */
public class Assembly {
    public static void main(String[] args) {
        
        
        TXT_OPE txt=new TXT_OPE();
        txt.setFileName("assembly.py");
        txt.append( "abaqus/core/header.part");
        
        Assembler as=new Assembler();
        //パート名、インスタンス名、コメントアウト部の詳細の順
        //パートcolからインスタンスcを作る。一応.pyにコメントを挿入
        txt.println(as.getInstance("beam", "c", "hari"));
        //インスタンスcを0,0,0→1,0,0を回転軸として90度反時計回りに回転
        txt.println(as.Rotate("c", new CoordXYZ(0,0,0), new CoordXYZ(1,0,0), 90));//反時計回り
        
        //インスタンスcを0,100,0に移動
        txt.println(as.Move("c", new CoordXYZ(0,100,0)));
        
        txt.finish();
        
    }
}
