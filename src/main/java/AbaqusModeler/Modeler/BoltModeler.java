/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AbaqusModeler.Modeler;

import AbaqusModeler.*;
import JointCreator.ID.KPID;
import java.io.File;
import static org.mozilla.javascript.ScriptRuntime.name;
import util.PATH;
import util.coord.CoordXYZ;
import util.dxf.DXFDrawer;
import util.io.TXT_OPE;
import util.section.HSection;

/**
 *
 * @author keita
 */
public class BoltModeler {
    
    public static void main(String[] args) {
        
        KPID kp=KPID.C400_N25_6M22;
        double bk=24+25;
        double tk1=kp.gettk1();
        double ts=12;
        double tctp=25;
        double ttp=25;
        HSection bsec=new HSection(600,200,9,19);
        double a=0,b=0,c=0,d=0;
        if(kp.getD()==22){
            a=36;
            b=13;
            c=22;
            d=22;
        }else if(kp.getD()==24){
            a=40;
            b=14;
            c=24;
            d=22;
        }else if(kp.getD()==26){
            a=44;
            b=15;
            c=26;
            d=22;
        }
        bolthole basebolt=new bolthole(c,a,b,bsec.getFlangeThickness()+tk1,d);
        bolthole ttpbolt=new bolthole(c,a,b,tctp+ttp,d);
        bolthole wingbolt=new bolthole(c,a,b,bk+tk1,d);
        bolthole spbolt=new bolthole(c,a,b,bsec.getWebThickness()+ts,d);
        
        BoltModeler bol=new BoltModeler("test/",basebolt,"bolt1");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler("test/",wingbolt,"bolt2");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler("test/",spbolt,"bolt3");
        bol.outputPythonFile("F14T");
        
        bol=new BoltModeler("test/",ttpbolt,"bolt4");
        bol.outputPythonFile("F14T");
        
        
    }

    /**
     * @param args the command line arguments
     */

    private final String path;
    private final String name;
    private final bolthole bh;

    public BoltModeler(String path,bolthole bh,String name){
//        int h=550;
//        int h=(int)bsec.getH();
        this.path=path;
        this.bh=bh;
        this.name=name;
        
    }
    
    
    
    public String boltinfo(String partname,
            double d1,double t1,double d2,double t2){
        return "#-------------------------------------------------------------------------------------------\n"
                + "#入力事項\n"
                + "cpartname='"+ partname+ "'\n"
                + "d1="+ d1+ ";\n"
                + "t1="+ t1+ "\n"
                + "d2="+ d2+ ";\n"
                + "t2="+ t2+ ";\n"
                + "";
    }
    
    public void outputPythonFile(String mat){
        
        TXT_OPE txt = new TXT_OPE();
        txt.setFileName(path + name + ".py");
        txt.append("abaqus/core/header.part");
        txt.println(this.boltinfo(name,
                bh.getNutSize() / 2,
                bh.getNutHeight(),
                bh.getAxisSize() / 2,
                bh.getAxisLength()));
        txt.append("abaqus/core/bolt.part");
        txt.finish();
    }
    
    
}
