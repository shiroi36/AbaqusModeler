/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package FrameCreator.input.junsato;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import util.PATH;
import util.Util;
import util.dxf.DXFpiece;
import util.io.SQL_OPE;


/**
 *
 * @author keita
 */
public class inpReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
//        inpReader a=new inpReader();
    }

    public inpReader(String path,DataCollector data){
//        DataCollector data=new DataCollector(new SQL_OPE(PATH.DB_DROPBOX_DB+"test;ifexists=true","junapp",""));
        ArrayList<DXFpiece> p=new ArrayList<DXFpiece>();
        try {
            File file = new File(path);
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            
            String s;
            
            while ((s = b_reader.readLine()) != null) {
                //グループコードの取得
                String[] s1=new Util().clean(s.split(" "));
                
//                System.out.println(Arrays.toString(s1));
                if(s1.length==0)continue;
                else if("node".equals(s1[0].toLowerCase().trim())){
//                    System.out.println("node!!!");
                    node n=new node(s1);
                    data.setNode(n);
//                    n.print();
                }
                else if("elem".equals(s1[0].toLowerCase().trim())){
                    String v=s;
                    boolean flag=true;
                    while(flag){
                        s=b_reader.readLine();
                        v+="  "+s;
                        s1=new Util().clean(s.split(" "));
                        if("type".equals(s1[0].toLowerCase().trim())){
                            elem elem=new elem(new Util().clean(v.split(" ")));
                            data.setElement(elem);
                            flag=false;
                        }
                    }
                }
            }

            b_reader.close();

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
