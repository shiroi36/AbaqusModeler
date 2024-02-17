/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util.dxf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import util.ArcInfo;
import util.CircleInfo;
import util.LineInfo;
import util.coord.CoordXYZ;
import util.Util;
import util.io.TXT_OPE;
import util.matrix.Calc;

/**
 *
 * @author keita
 */
public final class DXFDrawer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        DXFDrawer a=new DXFDrawer("dxf/texttest.dxf");
        a.setHeader();
        a.drawTEXT3D("0", 
                new CoordXYZ(1000,1000,1000),
                "test", new CoordXYZ(0,-1,0));
//        a.drawCircle3D("0", new CoordXYZ(1000,1000,0), 200,new CoordXYZ(0,0,-1),500);
        a.drawLine3D("0", new CoordXYZ(1000,1000,1000), new CoordXYZ(2000,2000,2000), new CoordXYZ(0,0,1), 500);
//        a.drawLine3D("0", new CoordXYZ(0,0,0), new CoordXYZ(1000,0,0), new CoordXYZ(0,0,-1), 5000);
        a.setEnd();
        a.Finish();
//        
//        a=new DXFDrawer("dxf/test2.dxf");
//        a.setHeader();
////        a.Move3D("dxf/test.dxf", "0", new CoordXYZ(1000,1000,1000), new CoordXYZ(1,1,1));
////        a.Rotate3D("dxf/test.dxf", "0", new CoordXYZ(1,0,0), 45);
////        a.Rotate3D("dxf/test.dxf", "0", new CoordXYZ(1,0,0), 45);
//        a.extrusion("dxf/4w.dxf", "0", new CoordXYZ(0,0,-1), 30);
//        a.setEnd();
//        a.Finish();
        
//        DXFpiece[] piece=new DXFDrawer().getAllPieces("dxf/core/main/header020.part");
//        for (int i = 0; i < piece.length; i++) {
//            DXFpiece piece1 = piece[i];
////            if (true) {
//            if (piece1.getGroupCode() == 5) {
//                System.out.println(piece1.getGroupCode() + "\t" + piece1.getValue());
//            }
//        }
//        System.out.println(Integer.toHexString(Integer.MAX_VALUE));
    }
    private static String path="dxf\\core\\main\\";
    private int hundlenum;
    private  TXT_OPE to;
    private final Util u;
    private final int el;
    private Calc calc;
    
    
    
    public DXFDrawer() {
        this("");
    }
    
    public DXFDrawer(String outputpath) {
        
        if (outputpath != "") {
            this.to = new TXT_OPE();
            to.setFileName(outputpath);
        }
        hundlenum=2000;
        this.u=new Util();
        this.el=4;
        calc=new Calc();
    }
    
    public void setoutputpath(String outputpath){
        if (outputpath != "") {
            this.to = new TXT_OPE();
            to.setFileName(outputpath);
        }
//        to.setFileName(outputpath);
    }
    
    public TXT_OPE getTXTOPE(){return to;}
    
    public void extrusion(String inputpath,String layer,CoordXYZ direc,double thickness){
        //必ずZ軸に平行に押し出しを行う。
        DXFEntities[] ent=this.getEntities(inputpath);
        for (int i = 0; i < ent.length; i++) {
            
            DXFEntities ent1=ent[i];
            String cg=ent1.getEntitiyTitle().toLowerCase();
//            System.out.println(cg);
            if("line".equals(cg)){
                boolean flag = ent1.getDimensionFlag();
                CoordXYZ p1 = ent1.getPosition1();
                CoordXYZ p2 = ent1.getPosition2();
                if(flag==DXFEntities.ENTITIES_2D){
                    this.drawLine3D(layer, p1, p2,direc,thickness);
                }else if(flag==DXFEntities.ENTITIES_3D){
                }
            }else if("circle".equals(cg)){
                
            }
        }
    }
    
    public void Rotate3D(String inputpath,String layer,CoordXYZ vector,double theta){
        this.Rotate3D(inputpath, layer, vector, theta, new CoordXYZ(0,0,0));
    }
    public void Rotate3D(String inputpath,CoordXYZ[] vector,double[] theta,CoordXYZ move){
        DXFEntities[] ent=this.getEntities(inputpath);
        for (int i = 0; i < ent.length; i++) {
            
            DXFEntities ent1=ent[i];
            String cg=ent1.getEntitiyTitle().toLowerCase();
//            System.out.println(cg);
            if("line".equals(cg)){
                boolean flag = ent1.getDimensionFlag();
                CoordXYZ p1 = calc.getRotate(ent1.getPosition1(), vector[0], theta[0]);
                CoordXYZ p2 = calc.getRotate(ent1.getPosition2(), vector[0], theta[0]);
                for (int j = 1; j < vector.length; j++) {
                    p1 = calc.getRotate(p1, vector[j], theta[j]);
                    p2 = calc.getRotate(p2, vector[j], theta[j]);
                }
                p1.add(move);
                p2.add(move);
                if(flag==DXFEntities.ENTITIES_2D){
                    this.drawLine3D(ent1.getLayerName(), p1, p2);
                }else if(flag==DXFEntities.ENTITIES_3D){
                    //原点のrotate
                    CoordXYZ o1=calc.getRotate(new CoordXYZ(0,0,0), vector[0], theta[0]);
                    CoordXYZ o2 = calc.getRotate(ent1.getExtrusionDirection(), vector[0], theta[0]);
                    for (int j = 1; j < vector.length; j++) {
                        o1 = calc.getRotate(o1, vector[j], theta[j]);
                        o2 = calc.getRotate(o2, vector[j], theta[j]);
                    }
                    CoordXYZ direc=new CoordXYZ(o2.getX()-o1.getX(),o2.getY()-o1.getY(),o2.getZ()-o1.getZ());
                    this.drawLine3D(ent1.getLayerName(), p1, p2,direc,ent1.getThickness());
                }
            }else if("circle".equals(cg)){
//                System.out.println("circle-3D");
                if(ent1.getDimensionFlag()==DXFEntities.ENTITIES_3D){
                    CoordXYZ p1=calc.getCoordInWCS(ent1.getPosition1(),
                            ent1.getExtrusionDirection());
                    CoordXYZ p11 = calc.getRotate(p1, vector[0], theta[0]);
                    for (int j = 1; j < vector.length; j++) {
                        p11 = calc.getRotate(p11, vector[j], theta[j]);
                    }
                    p11.add(move);
                    CoordXYZ o1=calc.getRotate(new CoordXYZ(0,0,0), vector[0], theta[0]);
                    CoordXYZ o2=calc.getRotate(ent1.getExtrusionDirection(), vector[0], theta[0]);
                    for (int j = 1; j < vector.length; j++) {
                        o1 = calc.getRotate(o1, vector[j], theta[j]);
                        o2 = calc.getRotate(o2, vector[j], theta[j]);
                    }
                    CoordXYZ direc=new CoordXYZ(o2.getX()-o1.getX(),o2.getY()-o1.getY(),o2.getZ()-o1.getZ());
                    this.drawCircle3D(ent1.getLayerName(),p11,ent1.getR(),
                            direc,ent1.getThickness());
                }
            }
        }
    }
    public void Rotate3D(String inputpath,CoordXYZ vector,double theta,CoordXYZ move){
        DXFEntities[] ent=this.getEntities(inputpath);
        for (int i = 0; i < ent.length; i++) {
            
            DXFEntities ent1=ent[i];
            String cg=ent1.getEntitiyTitle().toLowerCase();
//            System.out.println(cg);
            if("line".equals(cg)){
                boolean flag = ent1.getDimensionFlag();
                CoordXYZ p1 = calc.getRotate(ent1.getPosition1(), vector, theta);
                CoordXYZ p2 = calc.getRotate(ent1.getPosition2(), vector, theta);
                p1.add(move);
                p2.add(move);
                if(flag==DXFEntities.ENTITIES_2D){
                    this.drawLine3D(ent1.getLayerName(), p1, p2);
                }else if(flag==DXFEntities.ENTITIES_3D){
                    //原点のrotate
                    CoordXYZ o1=calc.getRotate(new CoordXYZ(0,0,0), vector, theta);
                    CoordXYZ o2=calc.getRotate(ent1.getExtrusionDirection(), vector, theta);
                    CoordXYZ direc=new CoordXYZ(o2.getX()-o1.getX(),o2.getY()-o1.getY(),o2.getZ()-o1.getZ());
                    this.drawLine3D(ent1.getLayerName(), p1, p2,direc,ent1.getThickness());
                }
            }else if("circle".equals(cg)){
//                System.out.println("circle-3D");
                if(ent1.getDimensionFlag()==DXFEntities.ENTITIES_3D){
                    CoordXYZ p1=calc.getCoordInWCS(ent1.getPosition1(),
                            ent1.getExtrusionDirection());
                    CoordXYZ p11=calc.getRotate(p1, vector, theta);
                    p11.add(move);
                    CoordXYZ o1=calc.getRotate(new CoordXYZ(0,0,0), vector, theta);
                    CoordXYZ o2=calc.getRotate(ent1.getExtrusionDirection(), vector, theta);
                    CoordXYZ direc=new CoordXYZ(o2.getX()-o1.getX(),o2.getY()-o1.getY(),o2.getZ()-o1.getZ());
                    this.drawCircle3D(ent1.getLayerName(),p11,ent1.getR(),
                            direc,ent1.getThickness());
                }
            }
        }
    }
    public void Rotate3D(String inputpath,String layer,CoordXYZ vector,double theta,CoordXYZ move){
        DXFEntities[] ent=this.getEntities(inputpath);
        for (int i = 0; i < ent.length; i++) {
            
            DXFEntities ent1=ent[i];
            String cg=ent1.getEntitiyTitle().toLowerCase();
//            System.out.println(cg);
            if("line".equals(cg)){
                boolean flag = ent1.getDimensionFlag();
                CoordXYZ p1 = calc.getRotate(ent1.getPosition1(), vector, theta);
                CoordXYZ p2 = calc.getRotate(ent1.getPosition2(), vector, theta);
                p1.add(move);
                p2.add(move);
                if(flag==DXFEntities.ENTITIES_2D){
                    this.drawLine3D(layer, p1, p2);
                }else if(flag==DXFEntities.ENTITIES_3D){
                    //原点のrotate
                    CoordXYZ o1=calc.getRotate(new CoordXYZ(0,0,0), vector, theta);
                    CoordXYZ o2=calc.getRotate(ent1.getExtrusionDirection(), vector, theta);
                    CoordXYZ direc=new CoordXYZ(o2.getX()-o1.getX(),o2.getY()-o1.getY(),o2.getZ()-o1.getZ());
                    this.drawLine3D(layer, p1, p2,direc,ent1.getThickness());
                }
            }else if("circle".equals(cg)){
//                System.out.println("circle-3D");
                if(ent1.getDimensionFlag()==DXFEntities.ENTITIES_3D){
                    CoordXYZ p1=calc.getCoordInWCS(ent1.getPosition1(),
                            ent1.getExtrusionDirection());
                    CoordXYZ p11=calc.getRotate(p1, vector, theta);
                    p11.add(move);
                    CoordXYZ o1=calc.getRotate(new CoordXYZ(0,0,0), vector, theta);
                    CoordXYZ o2=calc.getRotate(ent1.getExtrusionDirection(), vector, theta);
                    CoordXYZ direc=new CoordXYZ(o2.getX()-o1.getX(),o2.getY()-o1.getY(),o2.getZ()-o1.getZ());
                    this.drawCircle3D(layer,p11,ent1.getR(),
                            direc,ent1.getThickness());
                }
            }
        }
    }
    
    public void Move3D(String inputpath,CoordXYZ d,CoordXYZ mag){
        DXFEntities[] ent=this.getEntities(inputpath);
        for (int i = 0; i < ent.length; i++) {
            
            DXFEntities ent1=ent[i];
            String cg=ent1.getEntitiyTitle().toLowerCase();
//            System.out.println(cg);
            if("line".equals(cg)){
                boolean flag = ent1.getDimensionFlag();
                String layer=ent1.getLayerName();
                CoordXYZ p1 = ent1.getPosition1();
                CoordXYZ p11 = new CoordXYZ(
                        mag.getX() * (p1.getX() + d.getX()),
                        mag.getY() * (p1.getY() + d.getY()),
                        mag.getZ() * (p1.getZ() + d.getZ())
                );
                CoordXYZ p2 = ent1.getPosition2();
                CoordXYZ p21 = new CoordXYZ(
                        mag.getX() * (p2.getX() + d.getX()),
                        mag.getY() * (p2.getY() + d.getY()),
                        mag.getZ() * (p2.getZ() + d.getZ())
                );
                if(flag==DXFEntities.ENTITIES_2D){
                    this.drawLine3D(layer, p11, p21);
                }else if(flag==DXFEntities.ENTITIES_3D){
                    CoordXYZ direc=ent1.getExtrusionDirection();
                    this.drawLine3D(layer, p11, p21,
                            new CoordXYZ(
                                    direc.getX()*mag.getX(),
                                    direc.getY()*mag.getY(),
                                    direc.getZ()*mag.getZ()),
                            ent1.getThickness());
                }
            }else if("circle".equals(cg)){
//                System.out.println("circle-3D");
                if(ent1.getDimensionFlag()==DXFEntities.ENTITIES_3D){
                    String layer=ent1.getLayerName();
                    CoordXYZ p1=calc.getCoordInWCS(ent1.getPosition1(),
                            ent1.getExtrusionDirection());
                    CoordXYZ p11=new CoordXYZ(
                            mag.getX()*(p1.getX()+d.getX()),
                            mag.getY()*(p1.getY()+d.getY()),
                            mag.getZ()*(p1.getZ()+d.getZ())
                    );
                    CoordXYZ direc=ent1.getExtrusionDirection();
                    this.drawCircle3D(layer,p11,ent1.getR(),
                            new CoordXYZ(
                                    direc.getX()*mag.getX(),
                                    direc.getY()*mag.getY(),
                                    direc.getZ()*mag.getZ()),
                            ent1.getThickness());
                }
            }
        }
    }
    
    public void Move3D(String inputpath,String layer,CoordXYZ d,CoordXYZ mag){
        DXFEntities[] ent=this.getEntities(inputpath);
        for (int i = 0; i < ent.length; i++) {
            
            DXFEntities ent1=ent[i];
            String cg=ent1.getEntitiyTitle().toLowerCase();
//            System.out.println(cg);
            if("line".equals(cg)){
                boolean flag = ent1.getDimensionFlag();
                CoordXYZ p1 = ent1.getPosition1();
                CoordXYZ p11 = new CoordXYZ(
                        mag.getX() * (p1.getX() + d.getX()),
                        mag.getY() * (p1.getY() + d.getY()),
                        mag.getZ() * (p1.getZ() + d.getZ())
                );
                CoordXYZ p2 = ent1.getPosition2();
                CoordXYZ p21 = new CoordXYZ(
                        mag.getX() * (p2.getX() + d.getX()),
                        mag.getY() * (p2.getY() + d.getY()),
                        mag.getZ() * (p2.getZ() + d.getZ())
                );
                if(flag==DXFEntities.ENTITIES_2D){
                    this.drawLine3D(layer, p11, p21);
                }else if(flag==DXFEntities.ENTITIES_3D){
                    CoordXYZ direc=ent1.getExtrusionDirection();
                    this.drawLine3D(layer, p11, p21,
                            new CoordXYZ(
                                    direc.getX()*mag.getX(),
                                    direc.getY()*mag.getY(),
                                    direc.getZ()*mag.getZ()),
                            ent1.getThickness());
                }
            }else if("circle".equals(cg)){
//                System.out.println("circle-3D");
                if(ent1.getDimensionFlag()==DXFEntities.ENTITIES_3D){
                    CoordXYZ p1=calc.getCoordInWCS(ent1.getPosition1(),
                            ent1.getExtrusionDirection());
                    CoordXYZ p11=new CoordXYZ(
                            mag.getX()*(p1.getX()+d.getX()),
                            mag.getY()*(p1.getY()+d.getY()),
                            mag.getZ()*(p1.getZ()+d.getZ())
                    );
                    CoordXYZ direc=ent1.getExtrusionDirection();
                    this.drawCircle3D(layer,p11,ent1.getR(),
                            new CoordXYZ(
                                    direc.getX()*mag.getX(),
                                    direc.getY()*mag.getY(),
                                    direc.getZ()*mag.getZ()),
                            ent1.getThickness());
                }
            }
        }
    }
    
    public DXFpiece[] getAllPieces(String inputpath){
        
        ArrayList<DXFpiece> p=new ArrayList<DXFpiece>();
        try {
            File file = new File(inputpath);
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            int i=0;
            String s;
            int code=0;
            
            while ((s = b_reader.readLine()) != null) {
                //グループコードの取得
                if (i % 2 == 0) {
                    code=Integer.parseInt(s.trim());
                } else {
                    p.add(new DXFpiece(code,s.trim()));
                }
                i++;
            }

            b_reader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return (DXFpiece[])p.toArray(new DXFpiece[0]);
    }
    
    
    public DXFpiece[] getPieces(String inputpath){
        
        ArrayList<DXFpiece> p=new ArrayList<DXFpiece>();
        try {
            File file = new File(inputpath);
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            int i=0;
            String s;
            boolean flag=false;
            int code=0;
            
            if(inputpath.contains(".part"))flag=true;
            while ((s = b_reader.readLine()) != null) {
//                System.out.println(flag);
               if(s.contains("ENTITIES")){
                   flag=true;
                   continue;
               }
                //グループコードの取得
               if(!flag)continue;
//                System.out.println(s);
                if (i % 2 == 0) {
                    code=Integer.parseInt(s.trim());
                } else {
                    if (s.contains("ENDSEC") == true && flag == true) {
                        flag = false;
                        break;
                    }
                    p.add(new DXFpiece(code,s.trim()));
                }
                i++;
            }

            b_reader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
        return (DXFpiece[])p.toArray(new DXFpiece[0]);
    }
    
    public DXFEntities[] getEntities(String inputpath) {
        ArrayList<DXFEntities> ent = new ArrayList<DXFEntities>();
        DXFpiece[] p = this.getPieces(inputpath);
        for (int j = 0; j < p.length; j++) {
            DXFpiece p1 = p[j];
            if (p1.getGroupCode() == 0) {
                DXFEntities en = new DXFEntities();
                en.setInfomation(p1);
//                    p1.print();
                j++;
                while (j < p.length) {
                    p1 = p[j];
                    if (p1.getGroupCode() == 0) {
                        ent.add(en);
                        j--;
                        break;
                    }
//                        p1.print();
                    en.setInfomation(p1);
                    j++;
                    if (j == p.length) {
                        ent.add(en);
                    }
                }
            }
        }
        return (DXFEntities[]) ent.toArray(new DXFEntities[0]);
    }
    
        
    public void println(String val){to.println(val);}
    
    
    public void drawLine3D(String layer, CoordXYZ s, CoordXYZ g) {
        String content ="  0\n"+ "LINE\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "  8\n"+ layer+"\n"
                + " 10\n"+ u.marume(s.getX(),el)+"\n"
                + " 20\n"+ u.marume(s.getY(),el)+"\n"
                + " 30\n"+ u.marume(s.getZ(),el)+"\n"
                + " 11\n"+ u.marume(g.getX(),el)+"\n"
                + " 21\n"+ u.marume(g.getY(),el)+"\n"
                + " 31\n"+ u.marume(g.getZ(),el)+"";
        hundlenum++;
//        System.out.println(content);
        to.println(content);
    }
    
    public void drawLine3D(LineInfo li) {
        this.drawLine3D(li.getLayerName(), 
                li.getStartPoint(), 
                li.getEndPoint(), 
                li.getExtrudeDirection(), 
                li.getThickness()
        );
    }
    public void drawLine3D(String layer, CoordXYZ s, CoordXYZ g,CoordXYZ direc,double thickness) {
        CoordXYZ s1=s;
        CoordXYZ g1=g;
//        CoordXYZ s1=calc.getCoordInOCS(s, direc);
//        CoordXYZ g1=calc.getCoordInOCS(g, direc);
        String content ="  0\n"+ "LINE\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "  8\n"+ layer+"\n"
                + " 10\n"+ u.marume(s1.getX(),el)+"\n"
                + " 20\n"+ u.marume(s1.getY(),el)+"\n"
                + " 30\n"+ u.marume(s1.getZ(),el)+"\n"
                + " 11\n"+ u.marume(g1.getX(),el)+"\n"
                + " 21\n"+ u.marume(g1.getY(),el)+"\n"
                + " 31\n"+ u.marume(g1.getZ(),el) + "\n"
                + "39\n"+ thickness + "\n"
                + "210\n"+ u.marume(direc.getX(), el) + "\n"
                + "220\n"+ u.marume(direc.getY(), el) + "\n"
                + "230\n"+ u.marume(direc.getZ(), el);
        hundlenum++;
//        System.out.println(content);
        to.println(content);
    }
    public void drawCircle3D(CircleInfo ci){
        this.drawCircle3D(ci.getLayerName(), ci.getCenterPoint(), ci.getRadius(), ci.getExtrudeDirection(), ci.getThickness());
    }
    public void drawCircle3D(String layer, CoordXYZ c,double R,CoordXYZ direc,double thickness) {
        CoordXYZ c2=calc.getCoordInOCS(c, direc);
        String content = " 0\n"
                + "CIRCLE\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "  8\n"+ layer+"\n"
                + " 10\n" + u.marume(c2.getX(), el) + "\n"
                + " 20\n" + u.marume(c2.getY(), el) + "\n"
                + " 30\n" + u.marume(c2.getZ(), el) + "\n"
                + "39\n"+ 0 + "\n"
                + " 40\n" + R + "\n"
                + "210\n"+ u.marume(direc.getX(), el) + "\n"
                + "220\n"+ u.marume(direc.getY(), el) + "\n"
                + "230\n"+ u.marume(direc.getZ(), el);
        hundlenum++;
        to.println(content);
        double t=thickness;
        double l=direc.getLength();
        double x=direc.getX()/l;
        double y=direc.getY()/l;
        double z=direc.getZ()/l;
        c2=calc.getCoordInOCS(new CoordXYZ(
                c.getX()+t*x,c.getY()+t*y,c.getZ()+t*z), direc);
        content = " 0\n"
                + "CIRCLE\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "  8\n"+ layer+"\n"
                + " 10\n" + u.marume(c2.getX(), el) + "\n"
                + " 20\n" + u.marume(c2.getY(), el) + "\n"
                + " 30\n" + u.marume(c2.getZ(), el) + "\n"
                + "39\n"+ 0 + "\n"
                + " 40\n" + R + "\n"
                + "210\n"+ u.marume(direc.getX(), el) + "\n"
                + "220\n"+ u.marume(direc.getY(), el) + "\n"
                + "230\n"+ u.marume(direc.getZ(), el);
        hundlenum++;
        to.println(content);
//        CoordXYZ c2=calc.getCoordInOCS(c, direc);
//        String content = " 0\n"
//                + "CIRCLE\n"
//                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
//                + "  8\n"+ layer+"\n"
//                + " 10\n" + u.marume(c2.getX(), el) + "\n"
//                + " 20\n" + u.marume(c2.getY(), el) + "\n"
//                + " 30\n" + u.marume(c2.getZ(), el) + "\n"
//                + "39\n"+ thickness + "\n"
//                + " 40\n" + R + "\n"
//                + "210\n"+ u.marume(direc.getX(), el) + "\n"
//                + "220\n"+ u.marume(direc.getY(), el) + "\n"
//                + "230\n"+ u.marume(direc.getZ(), el);
//        hundlenum++;
//        to.println(content);
    }
    public void drawTEXT3D(String layer, CoordXYZ c1,String text,CoordXYZ direc) {
//        CoordXYZ c2=c;
//        c1.print();
        this.drawTEXT3D(layer, c1, text, direc, 400);
    }
    public void drawTEXT3D(String layer, CoordXYZ c1,String text,CoordXYZ direc,int size) {
//        CoordXYZ c2=c;
//        c1.print();
        CoordXYZ c11=calc.getCoordInOCS(c1, direc);
//        CoordXYZ c21=calc.getCoordInOCS(c2, direc);
//        c11.print();
        String content = " 0\n"
                + "TEXT\n"
                + "  5\n"+ Integer.toHexString(hundlenum)+"\n"
                + "  8\n"+ layer+"\n"
                + "  72\n"+ "1\n"
                + "  73\n"+ "1\n"
                + "  40\n"+ size+"\n"
                + "  1\n"+ text+"\n"
                + " 10\n" + u.marume(c11.getX(), el) + "\n"
                + " 20\n" + u.marume(c11.getY(), el) + "\n"
                + " 30\n" + u.marume(c11.getZ(), el) + "\n"
                + " 11\n" + u.marume(c11.getX(), el) + "\n"
                + " 21\n" + u.marume(c11.getY(), el) + "\n"
                + " 31\n" + u.marume(c11.getZ(), el) + "\n"
                + "210\n"+ u.marume(direc.getX(), el) + "\n"
                + "220\n"+ u.marume(direc.getY(), el) + "\n"
                + "230\n"+ u.marume(direc.getZ(), el);
        hundlenum++;
//        System.out.println(content);
        to.println(content);
    }
    
    public LineInfo[] getLineInfoFromFile(String path){
        DXFEntities[] ent=this.getEntities(path);
        ArrayList<LineInfo> li=new ArrayList<LineInfo>();
        for (int i = 0; i < ent.length; i++) {
            DXFEntities ent1 = ent[i];
//            System.out.println(ent1.getEntitiyTitle());
            if("line".equals(ent1.getEntitiyTitle().toLowerCase())){
                LineInfo lli=new LineInfo(
                        ent1.getLayerName(),
                        ent1.getPosition1(),
                        ent1.getPosition2(),
                        ent1.getExtrusionDirection(),
                        ent1.getThickness());
                li.add(lli);
            }
        }
        return (LineInfo[])li.toArray(new LineInfo[0]);
    }
    public CircleInfo[] getCircleInfoFromFile(String path){
        DXFEntities[] ent=this.getEntities(path);
        ArrayList<CircleInfo> li=new ArrayList<CircleInfo>();
        for (int i = 0; i < ent.length; i++) {
            DXFEntities ent1 = ent[i];
//            System.out.println(ent1.getEntitiyTitle());
            if("circle".equals(ent1.getEntitiyTitle().toLowerCase())){
                CircleInfo lli=new CircleInfo(
                        ent1.getLayerName(),
                        ent1.getPosition1(),
                        ent1.getR(),
                        ent1.getExtrusionDirection(),
                        ent1.getThickness()
                );
                li.add(lli);
            }
        }
        return (CircleInfo[])li.toArray(new CircleInfo[0]);
    }
      
    public void drawArc3D(String layer, CoordXYZ p, double R, double s, double g,int n,CoordXYZ direc,double thickness) {
//        System.out.println("n = " + n);
        for (int i = 0; i < n; i++) {
            double ts=(g-s)/360*Math.PI*2/n*i+s/360*Math.PI*2;
            double tg=(g-s)/360*Math.PI*2/n*(i+1)+s/360*Math.PI*2;
            
            CoordXYZ cs=new CoordXYZ(p.getX()+R*Math.cos(ts),p.getY()+R*Math.sin(ts),p.getZ());
            CoordXYZ cg=new CoordXYZ(p.getX()+R*Math.cos(tg),p.getY()+R*Math.sin(tg),p.getZ());
            Calc c=new Calc();
            cs=c.getCoordInWCS(cs, direc);
            cg=c.getCoordInWCS(cg, direc);
            this.drawLine3D(layer, cs, cg,direc,thickness);
        }
    }
    public LineInfo[] drawArc3D(ArcInfo ai){
//        this.drawArc3D(ai.getLayerName(), ai.getCenterPoint(), 
//                ai.getRadius(), ai.getStartDeg(), ai.getEndDeg(), 8, 
//                ai.getExtrudeDirection(), ai.getThickness());
        double g=ai.getEndDeg();
        double s=ai.getStartDeg();
        double R=ai.getRadius();
        CoordXYZ p=ai.getCenterPoint();
        String layer=ai.getLayerName();
        CoordXYZ direc=ai.getExtrudeDirection();
        double thickness=ai.getThickness();
        int n=8;
        LineInfo[] li=new LineInfo[n];
        for (int i = 0; i < n; i++) {
            double ts=(g-s)/360*Math.PI*2/n*i+s/360*Math.PI*2;
            double tg=(g-s)/360*Math.PI*2/n*(i+1)+s/360*Math.PI*2;
            
            CoordXYZ cs=new CoordXYZ(p.getX()+R*Math.cos(ts),p.getY()+R*Math.sin(ts),p.getZ());
            CoordXYZ cg=new CoordXYZ(p.getX()+R*Math.cos(tg),p.getY()+R*Math.sin(tg),p.getZ());
            li[i]=new LineInfo(layer, cs, cg,direc,thickness);
        }
        return li;
    }
        
    public void setHeader(String[] layer,int[] color,String[] linetype){
        //head.txtの読み込み
        try {
            File file = new File(path+"header021.part");
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            String s;
            while ((s = b_reader.readLine()) != null) {
                to.println(s);
            }

            for (int i = 0; i < layer.length; i++) {
                s = "  0\n"+ "LAYER\n"
                        + "  2\n"+ layer[i]+"\n"
                        + " 70\n"+ "     0\n"
                        + " 62\n"+ color[i]+"\n"
                        + "  6\n"+ linetype[i];
//                s = "0\n" + "LAYER\n"
//                        + "  5\n"+ Integer.toHexString(hundlenum).toUpperCase()+"\n"
//                        + "102\n"+ "{ACAD_XDICTIONARY\n"
//                        + "360\n"+ "13C\n"
//                        + "102\n"+ "}\n"
//                        + "330\n"+ "2\n"
//                        + "100\n"+ "AcDbSymbolTableRecord\n"
//                        + "100\n"+ "AcDbLayerTableRecord\n"
//                        + "  2\n"+ layer[i]+"\n"
//                        + " 70\n"+ "0\n"
//                        + " 62\n"+ color[i]+"\n"
//                        + "  6\n"+ linetype[i]+"\n"
//                        + "370\n"+ "-3\n"
//                        + "390\n"+ "F";
                to.println(s);
                hundlenum++;
            }
            
            file = new File(path+"header022.part");
            b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            
            while ((s = b_reader.readLine()) != null) {to.println(s);}
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void setHeader(){
        //head.txtの読み込み
        try {
            File file = new File(path+"header020.part");
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            int i = 0;

            String s;
            while ((s = b_reader.readLine()) != null) {

                to.println(s);
                i++;
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void setEnd(){
        //head.txtの読み込み
        try {
            File file = new File(path+"end020.part");
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            int i = 0;

            String s;
            while ((s = b_reader.readLine()) != null) {

                to.println(s);
                i++;
                
            }
            
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    public void Finish(){to.finish();}
    
}
