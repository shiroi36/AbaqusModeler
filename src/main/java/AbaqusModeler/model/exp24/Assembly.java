/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package AbaqusModeler.model.exp24;

import AbaqusModeler.Modeler.Assembler;
import util.coord.CoordXYZ;
import util.io.TXT_OPE;

/**
 * @author user
 */
public class Assembly {
    public static void main(String[] args) {
        TXT_OPE txt = new TXT_OPE();
        txt.setFileName("assembly.py");
        txt.append("abaqus/core/header.part");

        setBeam(txt);
        setColumn(txt);

        setLeftBrackets(txt);
        setRightBrackets(txt);

        setBeamFlangeBolts(txt);
        setBeamWebBolts(txt);
        setColumnWebBolts(txt);

        setColumnFlangePlates(txt);
        setColumnWebPlates(txt);

        txt.finish();
    }

    private static void setBeam(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "beam";
        String instanceName = "beam";
        String memo = "beam";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setColumn(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "column";
        String instanceName = "column";
        String memo = "column";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setLeftBrackets(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "r25l";
        String instanceName = "r25l";
        String memo = "r25l";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setRightBrackets(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "r25r";
        String instanceName = "r25r";
        String memo = "r25r";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setBeamFlangeBolts(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "bolt_bf";
        String instanceName = "bolt_bf";
        String memo = "bolt_bf";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setBeamWebBolts(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "bolt_bw";
        String instanceName = "bolt_bw";
        String memo = "bolt_bw";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setColumnWebBolts(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "bolt_cw";
        String instanceName = "bolt_cw";
        String memo = "bolt_cw";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setColumnFlangePlates(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "cfr";
        String instanceName = "cfr";
        String memo = "cfr";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void setColumnWebPlates(TXT_OPE txt) {
        Assembler assembler = new Assembler();
        String partName = "cwr";
        String instanceName = "cwr";
        String memo = "cwr";

        txt.println(assembler.getInstance(partName, instanceName, memo));
        txt.println(assembler.Rotate(instanceName, new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));
        txt.println(assembler.Move(instanceName, new CoordXYZ(0, 100, 0)));
    }

    private static void example() {
        TXT_OPE txt = new TXT_OPE();
        txt.setFileName("assembly.py");
        txt.append("abaqus/core/header.part");

        Assembler assembler = new Assembler();

        // パート名、インスタンス名、コメントアウト部の詳細の順
        // パートcolからインスタンスcを作る。一応.pyにコメントを挿入
        txt.println(assembler.getInstance("beam", "c", "hari"));

        // インスタンスcを0,0,0→1,0,0を回転軸として90度反時計回りに回転
        txt.println(assembler.Rotate("c", new CoordXYZ(0, 0, 0), new CoordXYZ(1, 0, 0), 90));//反時計回り

        // インスタンスcを0,100,0に移動
        txt.println(assembler.Move("c", new CoordXYZ(0, 100, 0)));

        txt.finish();
    }

}
