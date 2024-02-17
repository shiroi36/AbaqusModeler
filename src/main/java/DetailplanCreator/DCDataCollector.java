/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DetailplanCreator;

import java.util.Arrays;
import util.PATH;
import util.io.SQL_OPE;

/**
 *
 * @author keita
 */
public class DCDataCollector {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DCDataCollector data=new DCDataCollector(
                new SQL_OPE(PATH.DB_DROPBOX_DB+"webclamp_stm/test;ifexists=true","junapp",""));
        data.setColGroup();
        data.setBeamGroup();
        data.setJoistGroup();
        data.setJointID();
        data.setB2CID();
    }
    private final SQL_OPE sql;
    
    public DCDataCollector(SQL_OPE sql){
        sql.executeUpdate("drop schema if exists Detailplan;");
        sql.executeUpdate("create schema if not exists Detailplan;");
        sql.executeUpdate("drop table if exists detailplan.elem;");
        sql.executeUpdate("create table if not exists detailplan.elem as (\n"
                + "    select * from junsato.elem02\n"
                + ");");
        sql.executeUpdate("drop table if exists detailplan.node;");
        sql.executeUpdate("create table if not exists detailplan.node as (\n"
                + "    select * from junsato.node\n"
                + ");");
        sql.executeUpdate("drop table if exists detailplan.sect;");
        sql.executeUpdate("create table if not exists detailplan.sect as (\n"
                + "    select * from junsato.sect\n"
                + ");");
        
        sql.executeUpdate("drop table if exists detailplan.colelem ;");
        sql.executeUpdate("create table if not exists detailplan.colelem as (\n"
                + " select a.ID,a.NODE1,a.NODE2,a.\"SECTION\" \n"
                + " from detailplan.ELEM a \n"
                + " where lower(a.\"TYPE\")='column'\n"
                + " order by id \n"
                + ");");
        
        sql.executeUpdate("drop table if exists detailplan.colgroup;");
        sql.executeUpdate("create table if not exists detailplan.colgroup(\n"
                + "    rownumber int identity(1,1),\n"
                + "    group1 int,\n"
                + "    group2 int,\n"
                + "    id int,\n"
                + "    node1 int,\n"
                + "    node2 int,\n"
                + "    section varchar\n"
                + ");");
        
        sql.executeUpdate("drop table if exists detailplan.colgroup2;");
        sql.executeUpdate("create table if not exists detailplan.colgroup2(\n"
                + "    rownumber int identity(1,1),\n"
                + "    group1 int,\n"
                + "    group2 int,\n"
                + "    id int,\n"//elementidのこと
                + "    node int,\n"
                + "    section varchar,\n"
                + "    state varchar \n"
                + ");");
        
        //大梁のグループ
        sql.executeUpdate("drop table if exists detailplan.beamgroup;");
        sql.executeUpdate("create table if not exists detailplan.beamgroup(\n"
                + "    rownumber int identity(1,1),\n"
                + "    id int,\n"
                + "    node int,\n"
                + "    end varchar,\n"
                + "    dx double,\n"
                + "    dy double,\n"
                + "    dz double,\n"
                + "    section varchar\n"
                + ");");
        //小梁・孫梁のグループ
        sql.executeUpdate("drop table if exists detailplan.joistgroup;");
        sql.executeUpdate("create table if not exists detailplan.joistgroup(\n"
                + "    rownumber int identity(1,1),\n"
                + "    id int,\n"
                + "    node int,\n"
                + "    end varchar,\n"
                + "    dx double,\n"
                + "    dy double,\n"
                + "    dz double,\n"
                + "    section varchar\n"
                + ");");
        sql.executeUpdate("drop table if exists detailplan.jointlist;");
        sql.executeUpdate("create table if not exists detailplan.jointlist(\n"
                + "    rownumber int identity(1,1),\n"
                + "    id int,\n"
                + "    end varchar,\n"
                + "    dx double,\n"
                + "    dy double,\n"
                + "    dz double,\n"
                + "    section varchar\n"
                + ");");
        sql.executeUpdate("drop table if exists detailplan.jointgroup;");
        sql.executeUpdate("create table if not exists detailplan.jointgroup(\n"
                + "    rownumber int identity(1,1),\n"
                + "    node int,\n"
                + "    jointid int\n"
                + ");");
        sql.executeUpdate("drop table if exists detailplan.b2clist;");
        sql.executeUpdate("create table if not exists detailplan.b2clist(\n"
                + "    rownumber int identity(1,1),\n"
                + "    id int,\n"
                + "    jointid int,\n"
                + "    csec int,\n"
                + "    state varchar ,\n"
                + ");");
        sql.executeUpdate("drop table if exists detailplan.b2cgroup");
        sql.executeUpdate("create table if not exists detailplan.b2cgroup(\n"
                + "    rownumber int identity(1,1),\n"
                + "    node int,\n"
                + "    b2cid int \n"
                + ");");
        sql.executeUpdate("drop schema if exists temp;");
        sql.executeUpdate("create schema if not exists temp;");
        this.sql=sql;
    }
    
    public void setColGroup() {
        String[][] val = sql.getQueryDataString(
                "select a.ID,a.NODE1,a.NODE2,a.\"SECTION\" \n"
                + "from detailplan.colelem a \n"
                + "order by id limit 1;");
        val=this.transpose(val);
        sql.executeUpdate("set @did="+val[0][0]);
        sql.executeUpdate("delete from detailplan.colelem where id=@did;");
        int i0=0;
        int i1=0;
        while(true){
            i1=0;
            this.setColumnGroup(i0, i1, val[0]);
            i1++;
            
            //node2から伸びていく方
            sql.executeUpdate("set @node2=" + val[0][2]);
            String[][] val2 = sql.getQueryDataString(
                    "select a.ID,a.node1,a.NODE2,a.\"SECTION\" \n"
                    + "from detailplan.colelem  a \n"
                    + "where a.node1=@node2;");
            val2=this.transpose(val2);
            if (val2.length != 0) {
                sql.executeUpdate("set @did=" + val2[0][0]);
                sql.executeUpdate("delete from detailplan.colelem where id=@did;");
                while (true) {
                    this.setColumnGroup(i0, i1, val2[0]);
                    i1++;

                    sql.executeUpdate("set @node2=" + val2[0][2]);
                    val2 = sql.getQueryDataString(
                            "select a.ID,a.node1,a.NODE2,a.\"SECTION\" \n"
                            + "from detailplan.colelem  a \n"
                            + "where a.node1=@node2;");
                    val2=this.transpose(val2);
                    if (val2.length == 0) {
                        break;
                    }
                    sql.executeUpdate("set @did=" + val2[0][0]);
                    sql.executeUpdate("delete from detailplan.colelem where id=@did;");
                }
            }
            
            i1=-1;
            //node1から伸びていく方
            sql.executeUpdate("set @node1=" + val[0][1]);
            String[][] val3 = sql.getQueryDataString(
                    "select a.ID,a.node1,a.NODE2,a.\"SECTION\" \n"
                    + "from detailplan.colelem  a \n"
                    + "where a.node2=@node1;");
            val3=this.transpose(val3);
            if (val3.length != 0) {
                sql.executeUpdate("set @did=" + val3[0][0]);
                sql.executeUpdate("delete from detailplan.colelem where id=@did;");
                while (true) {
                    this.setColumnGroup(i0, i1, val3[0]);
                    i1--;
//
                    sql.executeUpdate("set @node1=" + val3[0][1]);
                    val3 = sql.getQueryDataString(
                            "select a.ID,a.node1,a.NODE2,a.\"SECTION\" \n"
                            + "from detailplan.colelem  a \n"
                            + "where a.node2=@node1;");
                    val3=this.transpose(val3);
                    if (val3.length == 0) {
                        break;
                    }
                    sql.executeUpdate("set @did=" + val3[0][0]);
                    sql.executeUpdate("delete from detailplan.colelem where id=@did;");
                }
            }
            
            
            //次の要素の取得
            val = sql.getQueryDataString(
                "select a.ID,a.NODE1,a.NODE2,a.\"SECTION\" \n"
                + "from detailplan.colelem a \n"
                + "order by id limit 1;");
            val=this.transpose(val);
            if(val.length==0)break;
            sql.executeUpdate("set @did=" + val[0][0]);
            sql.executeUpdate("delete from detailplan.colelem where id=@did;");
            i0++;
            System.out.println(i0);
        }
        sql.executeUpdate("drop table if exists detailplan.colelem");        
        
        //colgroupの整理
        int num=sql.getQueryDataInt("select max(group1) from detailplan.COLGROUP;")[0][0];
        for (int i = 0; i <= num; i++) {
            sql.executeUpdate("set @g1=" + i);
            sql.executeUpdate("set @cnode1=select node1 from detailplan.COLGROUP a \n"
                    + "where a.GROUP1=@g1 and group2=\n"
                    + "(select min(group2) from detailplan.COLGROUP b where group1=@g1) \n"
                    + "order by a.GROUP2;");
            sql.executeUpdate("set @cnode2=select node2 from detailplan.COLGROUP a \n"
                    + "where a.GROUP1=@g1 and group2=\n"
                    + "(select max(group2) from detailplan.COLGROUP b where group1=@g1) \n"
                    + "order by a.GROUP2;");
            
            double node1=sql.getQueryData("select a.CZ from detailplan.NODE a where a.ID=@cnode1;")[0][0];
            double node2=sql.getQueryData("select a.CZ from detailplan.NODE a where a.ID=@cnode2;")[0][0];
            String[][] val4;
            if(node1>node2){
                val4=sql.getQueryDataString(
                        "select group1,group2,id,node1,node2,section "
                                + "from detailplan.COLGROUP a "
                                + "where group1=@g1 order by a.GROUP2 desc;");
                val4=this.transpose(val4);
            }else{
                val4=sql.getQueryDataString(
                        "select group1,group2,id,node1,node2,section "
                                + "from detailplan.COLGROUP a "
                                + "where group1=@g1 order by a.GROUP2 asc;");
                val4=this.transpose(val4);
            }
            for (int j = 0; j < val4.length; j++) {
                val4[j][1]=""+j;
            }
            sql.executeUpdate("delete from detailplan.colgroup where group1=@g1;");
            for (int j = 0; j < val4.length; j++) {
                this.setColumnGroup(val4[j]);
            }
            String[][] val5=new String[val4.length+1][6];
            if(node1>node2){
                for (int j = 0; j < val4.length; j++) {
                    val5[j][0]=val4[j][0];
                    val5[j][1]=val4[j][1];
                    val5[j][2]=val4[j][2];
                    val5[j][3]=val4[j][4];
                    val5[j][4]=val4[j][5];
                    val5[j][5]="x";
                }
                int num2=val4.length;
                    val5[num2][0]=val4[num2-1][0];
                    val5[num2][1]=Integer.parseInt(val4[num2-1][1])+1+"";
                    val5[num2][2]=val4[num2-1][2];
                    val5[num2][3]=val4[num2-1][3];
                    val5[num2][4]=val4[num2-1][5];
                    val5[num2][5]="RF";
            }else{
                for (int j = 0; j < val4.length; j++) {
                    val5[j][0]=val4[j][0];
                    val5[j][1]=val4[j][1];
                    val5[j][2]=val4[j][2];
                    val5[j][3]=val4[j][3];
                    val5[j][4]=val4[j][5];
                    val5[j][5]="x";
                }
                int num2=val4.length;
                    val5[num2][0]=val4[num2-1][0];
                    val5[num2][1]=Integer.parseInt(val4[num2-1][1])+1+"";
                    val5[num2][2]=val4[num2-1][2];
                    val5[num2][3]=val4[num2-1][4];
                    val5[num2][4]=val4[num2-1][5];
                    val5[num2][5]="RF";
            }
            for (int j = 0; j < val5.length; j++) {
                this.setColumnGroup2(val5[j]);
            }
        }
        sql.executeUpdate("drop table if exists detailplan.colgroup");   
    }
    
    public void setB2CID(){
        sql.executeUpdate("drop table if exists temp.test0");
        sql.executeUpdate("create table if not exists temp.test0 as("
                + "select a.NODE,a.\"SECTION\",a.\"STATE\",b.JOINTID \n"
                + "from DETAILPLAN.COLGROUP2 a, \n"
                + "(select a.* from detailplan.JOINTGROUP a,detailplan.JOINTLIST b \n"
                + "where a.JOINTID=b.ID group by a.NODE,a.JOINTID) b \n"
                + "where a.NODE=b.NODE)");
        String[][] val = sql.getQueryDataString("select b.JOINTID,a.\"SECTION\",a.\"STATE\" \n"
                + "from DETAILPLAN.COLGROUP2 a, \n"
                + "(select a.* from detailplan.JOINTGROUP a,detailplan.JOINTLIST b \n"
                + "where a.JOINTID=b.ID group by a.NODE,a.JOINTID) b \n"
                + "where a.NODE=b.NODE \n"
                + "group by a.\"SECTION\",a.\"STATE\",b.jointid \n"
                + "order by b.jointid,a.\"STATE\";");
        val=this.transpose(val);
        for (int i = 0; i < val.length; i++) {
            String[] val1 = val[i];
            sql.executeUpdate("insert into detailplan.b2clist(id,jointid,csec,state)values("
                    + "" + i + ","
                    + "" + val1[0] + ","
                    + "" + val1[1] + ","
                    + "'" + val1[2] + "'"
                    + ")");
        }
        
        val=sql.getQueryDataString("select node,section,state,jointid from temp.test0 order by node");
        val=this.transpose(val);
        for (int i = 0; i < val.length; i++) {
            String[] val1 = val[i];
            sql.executeUpdate("set @sec="+val1[1]);
            sql.executeUpdate("set @state='"+val1[2]+"'");
            sql.executeUpdate("set @jid="+val1[3]);
            String node=val1[0];
            String bcid=sql.getQueryDataString("select id from detailplan.b2clist "
                    + "where csec=@sec "
                    + "and state=@state "
                    + "and jointid=@jid")[0][0];
            sql.executeUpdate("insert into detailplan.b2cgroup(node,b2cid)values("
                    + "" + node + ","
                    + "" + bcid + ""
                    + ")");
        }
        
        sql.executeUpdate("drop table if exists temp.test0;");
        
        
        
    }
    
    public void setJointID(){
        System.out.println("DCDataCollector.setJointID");
        System.out.println("まずはbeamgroupのコピー");
        sql.executeUpdate("drop table if exists temp.beamgroup;");
        sql.executeUpdate("create table if not exists temp.beamgroup as(\n"
                + "    select a.* from detailplan.beamgroup a,detailplan.colgroup2 b \n"
                + "    where a.node=b.node\n"
                + ");");
        int jid=0;
        System.out.println("jointをIDかし、detailedplan.jointlistとjointgroupに再整理");
        while(true){
//            System.out.println("jid="+jid);
            int n0=sql.getQueryDataInt("select count(*) from temp.beamgroup")[0][0];
            if(n0==0)break;
            sql.executeUpdate("set @node=(select node from temp.BEAMGROUP a "
                    + "group by a.NODE order by a.node limit 1);");
            String[][] jcont=sql.getQueryDataString("select end,dx,dy,dz,section "
                    + "from DETAILPLAN.BEAMGROUP a where a.NODE=@node;");
            jcont=this.transpose(jcont);
            //jidの挿入
            for (int i = 0; i < jcont.length; i++) {
                sql.executeUpdate("insert into detailplan.jointlist(id,end,dx,dy,dz,section)values("
                + "" + jid + ","
                + "'" + jcont[i][0] + "',"
                + "" + jcont[i][1] + ","
                + "" + jcont[i][2] + ","
                + "" + jcont[i][3] + ","
                + "'" + jcont[i][4] + "')");
            }
            
            sql.executeUpdate("drop table if exists temp.temp0");
            sql.executeUpdate("create table if not exists temp.temp0 as ("
                    + "select a.* from temp.BEAMGROUP a, \n"
                    + "(select node from temp.beamgroup a \n"
                    + "where a.end='"+ jcont[0][0]+ "' \n"
                    + "and dx="+ jcont[0][1]+ ""
                    + "and dy="+ jcont[0][2]+ ""
                    + "and dz="+ jcont[0][3]+ ""
                    + "and section='"+ jcont[0][4]+ "') b, "
                    + "(select node from DETAILPLAN.BEAMGROUP group by node "
                    + "having count(node)="+ jcont.length+ ") c "
                    + "where a.node=b.node and b.node=c.node)");
            for (int i = 1; i < jcont.length; i++) {
                String[] jcont1 = jcont[i];
                sql.executeUpdate("drop table if exists temp.temp"+i);
                sql.executeUpdate("create table if not exists temp.temp"+ i+ " as ("
                    + "select a.* from temp.BEAMGROUP a, \n"
                    + "(select node from temp.temp"+ (i-1)+ " a \n"
                    + "where a.end='"+ jcont1[0]+ "' \n"
                    + "and dx="+ jcont1[1]+ ""
                    + "and dy="+ jcont1[2]+ ""
                    + "and dz="+ jcont1[3]+ ""
                    + "and section='"+ jcont1[4]+ "') b \n"
                    + "where a.node=b.node)");
            }
            
            
            String[] node=sql.getQueryDataString(
                    "select node from temp.temp"+(jcont.length-1)+" group by node")[0];
            
            for (int i = 0; i < node.length; i++) {
                String node1 = node[i];
//                System.out.println(node);
                sql.executeUpdate("set @node="+node1);
                sql.executeUpdate("insert into detailplan.jointgroup(node,jointid)values("
                + "" + node1 + ","
                + "" + jid + ","
                        + ")");
                sql.executeUpdate("delete from temp.beamgroup where node=@node");
            }
            for (int i = 0; i < jcont.length; i++) {
                sql.executeUpdate("drop table if exists temp.temp"+i);
            }
            jid++;
            
        }
        sql.executeUpdate("drop table if exists temp.beamgroup;");
        
        
        System.out.println("detailplan.sectにないもの(鉄骨断面部分)は除外する");
        String[] id = sql.getQueryDataString("select id from DETAILPLAN.JOINTLIST \n"
                + "except \n"
                + "select a.id from DETAILPLAN.JOINTLIST a, detailplan.SECT b where a.section=b.ID;")[0];
        for (int i = 0; i < id.length; i++) {
            String id1 = id[i];
                sql.executeUpdate("set @node="+id1);
                sql.executeUpdate("delete from detailplan.jointlist where id=@node");
        }
    }
    
    public void setBeamGroup(){
        //柱に接続する梁のグループ
        String[][] val = sql.getQueryDataString(
                "select \n"
                + "a.id,\n"
                + "a.section,\n"
                + "a.node1,\n"
                + "a.node2,\n"
                + "a.END1,\n"
                + "a.END2,\n"
                + "c2.cx-c1.cx,\n"
                + "c2.cy-c1.cy,\n"
                + "c2.cz-c1.cz\n"
                + " from \n"
                + "DETAILPLAN.ELEM a ,\n"
                + "detailplan.COLGROUP2 b,\n"
                + "detailplan.NODE c1,\n"
                + "detailplan.NODE c2\n"
                + "where a.NODE1=b.NODE \n"
                + "and lower(a.\"TYPE\")='girder'\n"
                + "and a.NODE1=c1.ID\n"
                + "and a.NODE2=c2.ID;");
        val=this.transpose(val);
        for (int i = 0; i < val.length; i++) {
            String id=val[i][0];
            String sec=val[i][1];
            String node1=val[i][2];
            String node2=val[i][3];
            String end1=val[i][4];
            String end2=val[i][5];
            double x=Double.parseDouble(val[i][6]);
            double y=Double.parseDouble(val[i][7]);
            double z=Double.parseDouble(val[i][8]);
            double l=Math.sqrt(x*x+y*y+z*z);
            x/=l;y/=l;z/=l;
            this.setBeamGroup(id, node1, end1, x, y, z, sec);
        }
        val = sql.getQueryDataString(
                "select \n"
                + "a.id,\n"
                + "a.section,\n"
                + "a.node1,\n"
                + "a.node2,\n"
                + "a.END1,\n"
                + "a.END2,\n"
                + "c1.cx-c2.cx,\n"
                + "c1.cy-c2.cy,\n"
                + "c1.cz-c2.cz\n"
                + " from \n"
                + "DETAILPLAN.ELEM a ,\n"
                + "detailplan.COLGROUP2 b,\n"
                + "detailplan.NODE c1,\n"
                + "detailplan.NODE c2\n"
                + "where a.NODE2=b.NODE \n"
                + "and lower(a.\"TYPE\")='girder'\n"
                + "and a.NODE1=c1.ID\n"
                + "and a.NODE2=c2.ID;");
        val=this.transpose(val);
        for (int i = 0; i < val.length; i++) {
            System.out.println(Arrays.toString(val[i]));
            String id=val[i][0];
            String sec=val[i][1];
            String node1=val[i][2];
            String node2=val[i][3];
            String end1=val[i][4];
            String end2=val[i][5];
            double x=Double.parseDouble(val[i][6]);
            double y=Double.parseDouble(val[i][7]);
            double z=Double.parseDouble(val[i][8]);
            System.out.println("x"+x+"  y"+y+"  z"+z);
            double l=Math.sqrt(x*x+y*y+z*z);
            x/=l;y/=l;z/=l;
            this.setBeamGroup(id, node2, end2, x, y, z, sec);
        }
        
    }
    public void setJoistGroup(){
        //柱に接続する梁のグループ
        String[][] val = sql.getQueryDataString(
                "select \n"
                + "a.id,\n"
                + "a.section,\n"
                + "a.node1,\n"
                + "a.node2,\n"
                + "a.END1,\n"
                + "a.END2,\n"
                + "c2.cx-c1.cx,\n"
                + "c2.cy-c1.cy,\n"
                + "c2.cz-c1.cz\n"
                + " from \n"
                + "DETAILPLAN.ELEM a ,\n"
                + "-- detailplan.COLGROUP2 b,\n"
                + "detailplan.NODE c1,\n"
                + "detailplan.NODE c2\n"
                + "where a.NODE1 not in( select node from detailplan.COLGROUP2)\n"
                + "and lower(a.TYPE)='girder'\n"
                + "and a.NODE1=c1.ID\n"
                + "and a.NODE2=c2.ID;");
        val=this.transpose(val);
        for (int i = 0; i < val.length; i++) {
            String id=val[i][0];
            String sec=val[i][1];
            String node1=val[i][2];
            String node2=val[i][3];
            String end1=val[i][4];
            String end2=val[i][5];
            double x=Double.parseDouble(val[i][6]);
            double y=Double.parseDouble(val[i][7]);
            double z=Double.parseDouble(val[i][8]);
            double l = Math.sqrt(x * x + y * y + z * z);
            x /= l;
            y /= l;
            z /= l;
            this.setJoistGroup(id, node1, end1, x, y, z, sec);
        }
        val = sql.getQueryDataString(
                "select \n"
                + "a.id,\n"
                + "a.section,\n"
                + "a.node1,\n"
                + "a.node2,\n"
                + "a.END1,\n"
                + "a.END2,\n"
                + "c2.cx-c1.cx,\n"
                + "c2.cy-c1.cy,\n"
                + "c2.cz-c1.cz\n"
                + " from \n"
                + "DETAILPLAN.ELEM a ,\n"
                + "detailplan.NODE c1,\n"
                + "detailplan.NODE c2\n"
                + "where a.NODE2 not in( select node from detailplan.COLGROUP2)\n"
                + "and lower(a.TYPE)='girder'\n"
                + "and a.NODE1=c1.ID\n"
                + "and a.NODE2=c2.ID;");
        val=this.transpose(val);
        for (int i = 0; i < val.length; i++) {
            String id=val[i][0];
            String sec=val[i][1];
            String node1=val[i][2];
            String node2=val[i][3];
            String end1=val[i][4];
            String end2=val[i][5];
            double x=Double.parseDouble(val[i][6]);
            double y=Double.parseDouble(val[i][7]);
            double z=Double.parseDouble(val[i][8]);
            double l=Math.sqrt(x*x+y*y+z*z);
            x/=l;y/=l;z/=l;
            this.setJoistGroup(id, node2, end2, x, y, z, sec);
        }
        
    }
    
    private String[][] transpose(String[][] val){
        String[][] v=new String[val[0].length][val.length];
        for (int i = 0; i < val.length; i++) {
            String[] v1 = val[i];
            for (int j = 0; j < v1.length; j++) {
                String v11 = v1[j];
                v[j][i]=v11;
            }
        }
        return v;
    }
    
    private void setBeamGroup(String id,String node,String end,double dx,double dy,double dz,String sec){
        sql.executeUpdate("insert into detailplan.beamgroup(id,node,end,dx,dy,dz,section)values("
                + "" + id + ","
                + "" + node + ","
                + "'" + end + "',"
                + "" + dx + ","
                + "" + dy + ","
                + "" + dz + ","
                + "'" + sec + "')");
    }
    private void setJoistGroup(String id,String node,String end,double dx,double dy,double dz,String sec){
        sql.executeUpdate("insert into detailplan.joistgroup(id,node,end,dx,dy,dz,section)values("
                + "" + id + ","
                + "" + node + ","
                + "'" + end + "',"
                + "" + dx + ","
                + "" + dy + ","
                + "" + dz + ","
                + "'" + sec + "')");
    }
    private void setColumnGroup(int i0,int i1,String[] val){
        sql.executeUpdate("insert into detailplan.colgroup(group1,group2,id,node1,node2,section)values("
                + "" + i0 + ","
                + "" + i1 + ","
                + "" + val[0] + ","
                + "" + val[1] + ","
                + "" + val[2] + ","
                + "'" + val[3] + "')");
    }
    private void setColumnGroup(String[] val){
        sql.executeUpdate("insert into detailplan.colgroup(group1,group2,id,node1,node2,section)values("
                + "" + val[0] + ","
                + "" + val[1] + ","
                + "" + val[2] + ","
                + "" + val[3] + ","
                + "" + val[4] + ","
                + "'" + val[5] + "')");
    }
    private void setColumnGroup2(String[] val){
        sql.executeUpdate("insert into detailplan.colgroup2(group1,group2,id,node,section,state)values("
                + "" + val[0] + ","
                + "" + val[1] + ","
                + "" + val[2] + ","
                + "" + val[3] + ","
                + "'" + val[4] + "',"
                + "'" + val[5] + "'"
                + ")");
    }
    
}
