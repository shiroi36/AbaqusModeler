/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FrameCreator.input.junsato;

import FrameCreator.FAMID;
import java.util.ArrayList;
import java.util.Arrays;
import util.LineInfo;
import util.coord.CoordXYZ;
import util.coord.XYZSW;
import util.io.SQL_OPE;
import util.section.HSection;


/**
 *
 * @author keita
 */
public class DataCollector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        DataCollector a=new DataCollector();
    }
    private final SQL_OPE sql;

    public DataCollector(SQL_OPE sql){
        this.sql=sql;
        sql.executeUpdate("create schema if not exists junsato");
        sql.executeUpdate("drop table if exists junsato.memberinfo");
        sql.executeUpdate("create table if not exists junsato.memberinfo("
                + "num int identity(1,1),"
                + "mname varchar ,"
                + "section varchar,"
                + "tanju double,"
                + "lb double,"
                + "end1 varchar,"
                + "end2 varchar)");
        sql.executeUpdate("create index if not exists minfo_name on junsato.memberinfo(mname)");
        sql.executeUpdate("drop table if exists junsato.node");
        sql.executeUpdate("create table if not exists junsato.node("
                + "num int identity(1,1),"
                + "id int ,"
                + "cx double,"
                + "cy double,"
                + "cz double)");
        sql.executeUpdate("create index if not exists node_id on junsato.node(id)");
        sql.executeUpdate("drop table if exists junsato.elem");
        sql.executeUpdate("create table if not exists junsato.elem("
                + "num int identity(1,1),"
                + "id int ,"
                + "type varchar,"
                + "section int,"
                + "node1 int,"
                + "node2 int,"
                + "end1 varchar,"
                + "end2 varchar)");
        sql.executeUpdate("create index if not exists elem_id on junsato.elem(id)");
        sql.executeUpdate("drop table if exists junsato.sect");
        sql.executeUpdate("create table if not exists junsato.sect("
                + "num int identity(1,1),"
                + "id int ,"
                + "name varchar,"
                + "direc varchar,"
                + "size0 double,"
                + "size1 double,"
                + "size2 double,"
                + "size3 double,"
                + "size4 double,"
                + "size5 double)");
        sql.executeUpdate("create index if not exists sect_id on junsato.sect(id)");
    }
    
    public SQL_OPE getSQL(){return sql;}
    
    public void setNode(node node){
        double[] c=node.getCoord();
        sql.executeUpdate("insert into junsato.node(id,cx,cy,cz)values("
                + node.getID()+ ","
                + c[0]+ ","
                + c[1]+ ","
                + c[2]+ ")");
    }
    public void setElement(elem elem){
        String type=elem.getType().toLowerCase();
        if ("brace".equals(type)) {
            int[] node = elem.getENode();
            sql.executeUpdate("insert into junsato.elem(id,type,section,"
                    + "node1,node2,end1,end2)values("
                    + "" + elem.getID() + ","
                    + "'" + type + "'," 
                    + elem.getSectID() + ","
                    + node[0] + ","
                    + node[1] + ","
                    + "'p','p')");
        }
        else if ("column".equals(type)) {
            int[] node = elem.getENode();
            sql.executeUpdate("insert into junsato.elem(id,type,section,node1,node2,end1,end2)values("
                    + "" + elem.getID() + ","
                    + "'" + type + "',"
                    + elem.getSectID() + ","
                    + node[0] + ","
                    + node[1] + ","
                    + "'r',"
                    + "'r')");
        }
        else if ("girder".equals(type)) {
            int[] node = elem.getENode();
            int[][] bond=elem.getBond();
            String[] b=new String[bond.length];
            for (int i = 0; i < b.length; i++) {
                int[] n=bond[i];
                if(n[4]==1||n[4]==2)b[i]="p";
                else b[i]="r";
            }
            sql.executeUpdate("insert into junsato.elem(id,type,section,node1,node2,end1,end2)values("
                    + "" + elem.getID() + ","
                    + "'" + type + "',"
                    + elem.getSectID() + ","
                    + node[0] + ","
                    + node[1] + ","
                    + "'"+ b[0]+ "',"
                    + "'"+ b[1]+ "')");
        }
        
    }

    public void setMemberInfo(FAMID[] famid) {
        for (int i = 0; i < famid.length; i++) {
            FAMID f1 = famid[i];
            HSection sec = (HSection) f1.getSectionInfo();
            String mname = f1.getLabel();
            double lb = f1.getLineInfo().getLineLength();
            String[] end = f1.getEndInfo();
            sql.executeUpdate("insert into junsato.memberinfo"
                    + "(mname,section,tanju,lb,end1,end2)values("
                    + "'" + mname + "',"
                    + "'" + sec.getName()+ "',"
                    + "" + sec.getOwnWeight_Nmm()*1000/9.8 + ","
                    + "" + lb + ","
                    + "'" + end[0]+ "',"
                    + "'" + end[1] + "')");
        }
    }
    public void setSection(section sec){
        String type=sec.getMemberType().toLowerCase();
        if ("brace".equals(type)) {
            sql.executeUpdate("insert into junsato.sect(id,name)values("
                    + "" + sec.getID() + ","
                    + "'" + sec.getMemberName() + "')");
        }
        else if ("column".equals(type)||"girder".equals(type)) {
            HSection hsec=sec.getHSection();
            sql.executeUpdate("insert into junsato.sect(id,name,direc,size0,size1,size2,size3,size4)values("
                    + "" + sec.getID() + ","
                    + "'" + sec.getMemberName() + "',"
                    + "'" + sec.getSecType() + "',"
                    + "" + hsec.getH() + ","
                    + "" + hsec.getB() + ","
                    + "" + hsec.getWebThickness() + ","
                    + "" + hsec.getFlangeThickness() + ","
                    + "" + hsec.getR() + ")");
        }
    }
    
    public LineInfo[] getStandardLine() {
        String[][] val = sql.getQueryDataString("select "
                + "e.ID,e.\"TYPE\",e.END1,e.END2,\n"
                + "n1.CX,n1.cy,n1.CZ,\n"
                + "n2.CX,n2.cy,n2.CZ \n"
                + " from JUNSATO.elem02 e,\n"
                + "junsato.NODE n1,junsato.NODE n2\n"
                + "where e.NODE1=n1.ID \n"
                + "and e.NODE2=n2.ID\n"
                + " order by e.ID;");
        LineInfo[] line=new LineInfo[val[0].length];
        for (int i = 0; i < val[0].length; i++) {
            double x=Double.parseDouble(val[4][i]);
            double y=Double.parseDouble(val[5][i]);
            double z=Double.parseDouble(val[6][i]);
            CoordXYZ s=new CoordXYZ(x,y,z);
            x=Double.parseDouble(val[7][i]);
            y=Double.parseDouble(val[8][i]);
            z=Double.parseDouble(val[9][i]);
            CoordXYZ g=new CoordXYZ(x,y,z);
            line[i]=new LineInfo(s,g);
        }
        return line;
    }
    
    public FAMID[] getFAMIDS(){
        
        return this.getFAMIDS("\n"
                + "select e.ID,e.\"TYPE\",e.END1,e.END2,\n"
                + "n1.CX,n1.cy,n1.CZ,\n"
                + "n2.CX,n2.cy,n2.CZ,sec.\"NAME\",sec.DIREC,\n"
                + "sec.SIZE0,sec.SIZE1,sec.SIZE2,sec.SIZE3,sec.SIZE4,"
                + "e.node1,e.node2\n"
                + " from JUNSATO.elem02 e,\n"
                + "junsato.NODE n1,junsato.NODE n2, \n"
                + "junsato.SECT sec \n"
                + "where e.NODE1=n1.ID \n"
                + "and e.NODE2=n2.ID\n"
                + "and sec.ID=e.\"SECTION\" \n"
                + "and (e.type='girder' or e.type='column')"
                + " order by e.ID;");
    }
    
    public FAMID[] getFAMIDS(XYZSW direc,double min,double max){
        sql.executeUpdate("set @min="+min);
        sql.executeUpdate("set @max="+max);
        String d=new String();
        ArrayList<FAMID> fam=new ArrayList<FAMID>();
        if(direc==XYZSW.X)d="CX";
        else if(direc==XYZSW.Y)d="CY";
        else if(direc==XYZSW.Z){
            d="CZ";
            FAMID[] f1 = this.getFAMIDS("select e.ID,e.\"TYPE\",e.END1,e.END2,\n"
                    + "n1.CX,n1.cy,n1.CZ,\n"
                    + "n2.CX,n2.cy,n2.CZ,sec.\"NAME\",sec.DIREC,\n"
                    + "sec.SIZE0,sec.SIZE1,sec.SIZE2,sec.SIZE3,sec.SIZE4,"
                    + "e.node1,e.node2\n"
                    + " from JUNSATO.elem02 e,\n"
                    + "(select * from junsato.NODE a where a.CZ>@min and a.CZ<@max) n1,\n"
                    + "junsato.NODE n2, \n"
                    + "junsato.SECT sec \n"
                    + "where e.NODE1=n1.ID\n"
                    + "and e.NODE2=n2.ID\n"
                    + "and sec.ID=e.\"SECTION\" \n"
                    + "and e.type='column'\n"
                    + " order by e.ID;");
            for (int i = 0; i < f1.length; i++) {
                fam.add(f1[i]);
            }
            f1 = this.getFAMIDS("select e.ID,e.\"TYPE\",e.END1,e.END2,\n"
                    + "n1.CX,n1.cy,n1.CZ,\n"
                    + "n2.CX,n2.cy,n2.CZ,sec.\"NAME\",sec.DIREC,\n"
                    + "sec.SIZE0,sec.SIZE1,sec.SIZE2,sec.SIZE3,sec.SIZE4 ,"
                    + "e.node1,e.node2\n"
                    + " from JUNSATO.elem02 e,\n"
                    + "junsato.NODE n1,\n"
                    + "(select * from junsato.NODE a where a.CZ>@min and a.CZ<@max) n2, \n"
                    + "junsato.SECT sec \n"
                    + "where e.NODE1=n1.ID\n"
                    + "and e.NODE2=n2.ID\n"
                    + "and sec.ID=e.\"SECTION\" \n"
                    + "and e.type='column'\n"
                    + " order by e.ID;");
            for (int i = 0; i < f1.length; i++) {
                fam.add(f1[i]);
            }
        }
        FAMID[] famid1=this.getFAMIDS("select e.ID,e.\"TYPE\",e.END1,e.END2,\n"
                + "n1.CX,n1.cy,n1.CZ,\n"
                + "n2.CX,n2.cy,n2.CZ,sec.\"NAME\",sec.DIREC,\n"
                + "sec.SIZE0,sec.SIZE1,sec.SIZE2,sec.SIZE3,sec.SIZE4,"
                + "e.node1,e.node2\n"
                + " from JUNSATO.elem02 e,\n"
                + "(select * from junsato.NODE a where a."+ d+ ">@min and a."+ d+ "<@max) n1,\n"
                + "(select * from junsato.NODE a where a."+ d+ ">@min and a."+ d+ "<@max) n2, \n"
                + "junsato.SECT sec \n"
                + "where e.NODE1=n1.ID \n"
                + "and e.NODE2=n2.ID\n"
                + "and sec.ID=e.\"SECTION\" \n"
                + "and (e.type='girder' or e.type='column')\n"
                + " order by e.ID;");
        for (int i = 0; i < famid1.length; i++) {
            fam.add(famid1[i]);
        }
        return (FAMID[]) fam.toArray(new FAMID[0]);
    }
    
    private FAMID[] getFAMIDS(String query) {
        String[][] val = sql.getQueryDataString(query);
        FAMID[] famid=new FAMID[val[0].length];
        for (int i = 0; i < val[0].length; i++) {
            String type=val[1][i];
            double x=Double.parseDouble(val[4][i]);
            double y=Double.parseDouble(val[5][i]);
            double z=Double.parseDouble(val[6][i]);
            CoordXYZ s=new CoordXYZ(x,y,z);
            x=Double.parseDouble(val[7][i]);
            y=Double.parseDouble(val[8][i]);
            z=Double.parseDouble(val[9][i]);
            CoordXYZ g=new CoordXYZ(x,y,z);
            LineInfo line=new LineInfo(s,g);
            String name=val[10][i];
            String direc=val[11][i];
//            for (int j = 0; j < val.length; j++) {
//                System.out.print(val[j][i]+"\t");
//            }
//            System.out.println("");
            HSection sec=new HSection(
                    Double.parseDouble(val[12][i]),
                    Double.parseDouble(val[13][i]),
                    Double.parseDouble(val[14][i]),
                    Double.parseDouble(val[15][i]),
                    Double.parseDouble(val[16][i])
            );
            String[] end={val[2][i],val[3][i]};
            String[] node={val[17][i],val[18][i]};
            int memb=0;
            double theta=0;
            if("column".equals(type)){
                memb=FAMID.TYPE_COLUMN;
                if("hkyou".equals(direc.toLowerCase()))theta=90;
            }
            else if("girder".equals(type)||"beam".equals(type)){
                if("r".equals(end[0])){
//                    System.out.println("node:"+node[0]);
                    end[0]+=this.getRigidOffset(Integer.parseInt(node[0]));
                    //ここでIDを探してそのIDに対応する柱せい/2を入れるといいと思う
                }
                if("r".equals(end[1])){
                    //ここでIDを探してそのIDに対応する柱せい/2を入れるといいと思う
                    end[1]+=this.getRigidOffset(Integer.parseInt(node[1]));
                }
                memb=FAMID.TYPE_BEAM;
            }
//            if("hkyou".equals(direc.toLowerCase()))theta=90;
            famid[i]=new FAMID(line,sec,memb,name,theta,end);
        }
        return famid;
    }
    
    private double getRigidOffset(int id){
//        System.out.println("set @node"+id+";");
        sql.executeUpdate("set @node="+ id+ ";");
        double[][] a=sql.getQueryData(
                "select size0 from junsato.COLUMNNODE2 a, junsato.SECT b \n"
                + "where a.\"SECTION\"=b.ID and a.ID=@node;");
//        System.out.println("a.length = " + a.length);
        if(a[0].length==0)return 0;
        else return 0.5*a[0][0];
    }
    
    public void ClearInterMediateNode() {
        //columnnode2: 柱がある節点の検索
        //2016年4月19日 b.sectionをmax()とした。これでいいのかはわからない･･･
        sql.executeUpdate("drop table if exists junsato.COLUMNNODE2;");
        sql.executeUpdate("create table if not exists junsato.columnnode2 as(\n"
                + "select a.ID,b.\"SECTION\" from junsato.NODE a,junsato.ELEM b \n"
                + "where lower(b.\"TYPE\")='column' and (a.ID=b.NODE1 or a.ID=b.NODE2) \n"
                + "group by a.ID,b.\"SECTION\");");

        //columnnode: 柱がない節点を検索した。
        sql.executeUpdate("drop table if exists junsato.columnnode;");
        sql.executeUpdate("create table if not exists junsato.columnnode as(\n"
                + "select a.id\n"
                + "from junsato.node a \n"
                + "where not exists(\n"
                + " select * from (select * from JUNSATO.ELEM  where lower(type)='column')b where a.id=b.node1) \n"
                + " and not exists(\n"
                + " select * from (select * from JUNSATO.ELEM  where lower(type)='column')b where a.id=b.node2) \n"
                + ");");
        //elem00: 柱がない節点を含む梁要素を検索した。
        sql.executeUpdate("drop table if exists junsato.ELEM00;");
        sql.executeUpdate("create table if not exists junsato.elem00 as(\n"
                + "select e.* from junsato.ELEM e\n"
                + ",junsato.COLUMNNODE b \n"
                + "where \n"
                + "e.node1=b.id \n"
                + "and lower(e.end1)='r'\n"
                + "union\n"
                + "select e.* from junsato.ELEM e\n"
                + ",junsato.COLUMNNODE b \n"
                + "where \n"
                + "e.node2=b.id \n"
                + "and lower(e.end2)='r'\n"
                + ");");
        //elem01: 両端とも柱を共有している梁要素を検討した。
        sql.executeUpdate("drop table if exists junsato.ELEM01;");
        sql.executeUpdate("create table if not exists junsato.elem01 as(\n"
                + "select a.*\n"
                + "from junsato.elem a \n"
                + "where not exists(select * from junsato.elem00 b where a.id=b.id) \n"
                + ");");
        
        //中間接点の除去。必ず1端→（中間接点） （中間接点）→2端というルールとする。
        String[] imn=sql.getQueryDataString("select id from junsato.columnnode;")[0];
        for (int i = 0; i < imn.length; i++) {
            String imn1 = imn[i];
            sql.executeUpdate("set @node="+ imn1+ ";");
            String[][] val = sql.getQueryDataString("select id,type,section,node1,node2 \n"
                    + "from JUNSATO.ELEM00 where node2=@node and lower(end2)='r';");
            if(val[0].length!=1)continue;
            sql.executeUpdate("set @sec="+ val[2][0]+ ";");
            sql.executeUpdate("set @mid="+ val[0][0]+ ";");
            val = sql.getQueryDataString("select id,type,section,node1,node2,end1,end2 \n"
                    + "from JUNSATO.ELEM00 where node1=@node and section=@sec and lower(end1)='r';");
            if(val[0].length!=1)continue;
            sql.executeUpdate("set @did="+ val[0][0]+ ";");
            sql.executeUpdate("set @node2="+ val[4][0]+ ";");
            sql.executeUpdate("set @end2='"+ val[6][0]+ "';");
            sql.executeUpdate("update junsato.elem00 set node2=@node2 where id=@mid;");
            sql.executeUpdate("update junsato.elem00 set end2=@end2 where id=@mid;");
            sql.executeUpdate("delete from junsato.elem00 where id=@did;");
        }
        
        sql.executeUpdate("drop table if exists junsato.ELEM02;");
        sql.executeUpdate("create table if not exists junsato.elem02 as(\n"
                + "select * from junsato.elem00\n"
                + "union\n"
                + "select * from junsato.elem01\n"
                + ");");
        
    }
    

}
