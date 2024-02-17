/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.io;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Araki Keita
 */
public class SQL_OPE {
//    public static void main(String[] args) {
//        SQL_OPE sql=new SQL_OPE(SQL_OPE.DBpath+"test","junapp","");
//        double[][] value=new double[4][100];
//        for(int i=0;i<100;i++){
//            value[0][i]=i;
//            value[1][i]=i+1;
//            value[2][i]=i+2;
//            value[3][i]=i+3;
//        }
//        sql.Write("test1", new String[]{"r1","r2","r3","r4"}, value);
//    }
    Statement st;
    ResultSet result;
    Connection con;
    public static final String DBpath="jdbc:h2:tcp://localhost/C:/DB/";
    public static final String username="junapp";
    public static final String password="";
    private TXT_OPE to;
    public SQL_OPE(String path,String username,String password){
        try{
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(path, username, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//これを宣言しないとResultSetがスクロールしない
        }catch(Exception e){e.printStackTrace();}
        to=new TXT_OPE();
        to.setFileName("log.txt");
        to.println("SQL_OPE: log "
                +new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }
    public SQL_OPE(Connection con){
        try {
        this.con=con;
        st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//これを宣言しないとResultSetがスクロールしない
                } catch (Exception e) {
                    e.printStackTrace();
        }
        to=new TXT_OPE();
        to.setFileName("log.txt");
        to.println("SQL_OPE: log "
                +new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }
    public SQL_OPE(String path){
        try{
            Class.forName("org.h2.Driver");
            con = DriverManager.getConnection(path, username, password);
            st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//これを宣言しないとResultSetがスクロールしない
        }catch(Exception e){e.printStackTrace();}
        to=new TXT_OPE();
        to.setFileName("log.txt");
        to.println("SQL_OPE: log "
                +new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));
    }
    public void executeQuery(String query){
        try{
            result=st.executeQuery(query);
        }catch(SQLException e){e.printStackTrace();}
    }
    public void executeUpdate(String query){
        try{
            st.executeUpdate(query);
        }catch(SQLException e){e.printStackTrace();}
    }
    public void Write(String tbname,String[] columnname,double[][] values){
        try{
            st.executeUpdate("drop table "+tbname+" if exists");
            String mkdb="create table "+tbname+" (id int IDENTITY(0,1) not null,";
            for(int i=0;i<columnname.length;i++){
                mkdb+=columnname[i]+" double,";
            }
            mkdb+=")";
            System.out.println(mkdb);
            st.executeUpdate(mkdb);
            String insert="INSERT INTO "+tbname+" (";
            for(int i=0;i<columnname.length;i++){
                insert+=columnname[i]+" ,";
            }
            insert+=")";
            for(int i=0;i<values[0].length;i++){
                String latterhalf=" values(";
                for(int j=0;j<columnname.length;j++){
                    latterhalf+=values[j][i]+",";
                }
                latterhalf+=")";
                st.executeUpdate(insert+latterhalf);
            }
        }catch(SQLException e){e.printStackTrace();}
    }
    public void Write(String tbname,double[][] values){
        try{
            String mkdb="create table "+tbname+" (id int IDENTITY(0,1) not null,";
            for(int i=0;i<values.length;i++){
                mkdb+="r"+i+" double,";
            }
            mkdb+=")";
            System.out.println(mkdb);
            st.executeUpdate(mkdb);
            String insert="INSERT INTO "+tbname+" (";
            for(int i=0;i<values.length;i++){
                insert+="r"+i+" ,";
            }
            insert+=")";
            for(int i=0;i<values[0].length;i++){
                String latterhalf=" values(";
                for(int j=0;j<values.length;j++){
                    latterhalf+=values[j][i]+",";
                }
                latterhalf+=")";
                st.executeUpdate(insert+latterhalf);
            }
        }catch(SQLException e){e.printStackTrace();}
    }
    public void Write(String tbname,int startno,String[] columnname,int[][] values){
        try{
            String mkdb="create table "+tbname+" (id int IDENTITY("+startno+",1) not null,";
            for(int i=0;i<columnname.length;i++){
                mkdb+=columnname[i]+" int,";
            }
            mkdb+=")";
            System.out.println(mkdb);
            st.executeUpdate(mkdb);
            String insert="INSERT INTO "+tbname+" (";
            for(int i=0;i<columnname.length;i++){
                insert+=columnname[i]+" ,";
            }
            insert+=")";
            for(int i=0;i<values[0].length;i++){
                String latterhalf=" values(";
                for(int j=0;j<columnname.length;j++){
                    latterhalf+=values[j][i]+",";
                }
                latterhalf+=")";
                st.executeUpdate(insert+latterhalf);
            }
        }catch(SQLException e){e.printStackTrace();}
    }
    public void Write(String tbname,String[] columnname,double[][][] values){
        try{
            st.executeUpdate("drop table "+tbname+" if exists");
            String mkdb="create table "+tbname+" (id int IDENTITY(0,1) not null,step int,";
            for(int i=0;i<columnname.length;i++){
                mkdb+=columnname[i]+" double,";
            }
            mkdb+=")";
            System.out.println(mkdb);
            st.executeUpdate(mkdb);
            st.executeUpdate("create index on "+tbname+"(step)");
            for(int s=0;s<values.length;s++){
                System.out.println(s);
                String insert="INSERT INTO "+tbname+" (step,";
                for(int i=0;i<columnname.length;i++){
                    insert+=columnname[i]+" ,";
                }
                insert+=")";
                for(int i=0;i<values[s][0].length;i++){
                    String latterhalf=" values("+s+",";
                    for(int j=0;j<columnname.length;j++){
                        latterhalf+=values[s][j][i]+",";
                    }
                    latterhalf+=")";
                    st.executeUpdate(insert+latterhalf);
                }
            }
        }catch(SQLException e){e.printStackTrace();}
    }
    public void Write(String tbname,String[] columnname,double[][][] values, int startstep,int goalstep){
        try{
            st.executeUpdate("drop table "+tbname+" if exists");
            String mkdb="create table "+tbname+" (id int IDENTITY(0,1) not null,step int,";
            for(int i=0;i<columnname.length;i++){
                mkdb+=columnname[i]+" double,";
            }
            mkdb+=")";
            System.out.println(mkdb);
            st.executeUpdate(mkdb);
            st.executeUpdate("create index on "+tbname+"(step)");
            for(int s=startstep;s<goalstep;s++){
                System.out.println(s);
                String insert="INSERT INTO "+tbname+" (step,";
                for(int i=0;i<columnname.length;i++){
                    insert+=columnname[i]+" ,";
                }
                insert+=")";
                for(int i=0;i<values[s][0].length;i++){
                    String latterhalf=" values("+(s-startstep)+",";
                    for(int j=0;j<columnname.length;j++){
                        latterhalf+=values[s][j][i]+",";
                    }
                    latterhalf+=")";
                    st.executeUpdate(insert+latterhalf);
                }
            }
        }catch(SQLException e){e.printStackTrace();}
    }
    public void Write(String tbname,int startno,String[] columnname,double[][] values){
        try{
            st.executeUpdate("drop table "+tbname+" if exists");
            String mkdb="create table "+tbname+" (id int IDENTITY("+startno+",1) not null,";
            for(int i=0;i<columnname.length;i++){
                mkdb+=columnname[i]+" double,";
            }
            mkdb+=")";
            System.out.println(mkdb);
            st.executeUpdate(mkdb);
            String insert="INSERT INTO "+tbname+" (";
            for(int i=0;i<columnname.length;i++){
                insert+=columnname[i]+" ,";
            }
            insert+=")";
            for(int i=0;i<values[0].length;i++){
                String latterhalf=" values(";
                for(int j=0;j<columnname.length;j++){
                    latterhalf+=values[j][i]+",";
                }
                latterhalf+=")";
                st.executeUpdate(insert+latterhalf);
            }
        }catch(SQLException e){e.printStackTrace();}
    }
    public double[] getResultSetColumnData(int Columnnumber){
        int row=0;
        double[] value;
        try{
            result.beforeFirst();
            result.last();
            row=result.getRow();
            result.beforeFirst();
        }catch(SQLException e){e.printStackTrace();}

        value=new double[row];
        row=0;

        try{
            while ( result.next() ) {
                    // 列番号による指定
                value[row]=result.getDouble(Columnnumber);
                row++;
            }
        }catch(SQLException e){e.printStackTrace();}

        return value;
    }
    public double[][] getResultSetData(){
        int col=0;
        int row=0;
        double[][] value;
        try{
            result.beforeFirst();
            result.last();
            row=result.getRow();
            col=result.getMetaData().getColumnCount();
            result.beforeFirst();
        }catch(SQLException e){
            e.printStackTrace();}
        value=new double[col][row];
        try{
            for(int i=0;i<row;i++){
                result.next();
                for(int s=1;s<=col;s++){
                    value[s-1][i]=result.getDouble(s);
                }
            }
        }catch(SQLException e){e.printStackTrace();}
        return value;
    }
    public int[][] getResultSetDataInt(){
        int col=0;
        int row=0;
        int[][] value;
        try{
            result.beforeFirst();
            result.last();
            row=result.getRow();
            col=result.getMetaData().getColumnCount();
            result.beforeFirst();
        }catch(SQLException e){
            e.printStackTrace();}
        value=new int[col][row];
        try{
            for(int i=0;i<row;i++){
                result.next();
                for(int s=1;s<=col;s++){
                    value[s-1][i]=result.getInt(s);
                }
            }
        }catch(SQLException e){e.printStackTrace();}
        return value;
    }
    public String[][] getResultSetDataString(){
        int col=0;
        int row=0;
        String[][] value;
        try{
            result.beforeFirst();
            result.last();
            row=result.getRow();
            col=result.getMetaData().getColumnCount();
            result.beforeFirst();
        }catch(SQLException e){
            e.printStackTrace();}
        value=new String[col][row];
        try{
            for(int i=0;i<row;i++){
                result.next();
                for(int s=1;s<=col;s++){
                    value[s-1][i]=result.getString(s);
                }
            }
        }catch(SQLException e){e.printStackTrace();}
        return value;
    }
    public double[][] getQueryData(String query){
        to.println("------------------------------------------------------------"
                + "-------------------------------------------------------------");
        to.println(query);
        this.executeQuery(query);
        return this.getResultSetData();
    }
    public int[][] getQueryDataInt(String query){
        to.println("------------------------------------------------------------"
                + "-------------------------------------------------------------");
        to.println(query);
        this.executeQuery(query);
        return this.getResultSetDataInt();
    }
    public String[][] getQueryDataString(String query){
        to.println("------------------------------------------------------------"
                + "-------------------------------------------------------------");
        to.println(query);
        this.executeQuery(query);
        return this.getResultSetDataString();
    }
    public Connection getConnection(){
        return con;
    }
    public void Close(){
        try{
            con.close();
            st.close();
            result.close();
        }catch(Exception e){e.printStackTrace();}
    }
    
    
    public String[][] transpose(String[][] val){
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
    
    
}
