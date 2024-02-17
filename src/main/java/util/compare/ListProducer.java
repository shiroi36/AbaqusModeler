/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.compare;

import java.util.ArrayList;

/**
 *
 * @author keita
 */
public class ListProducer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    private final CompareInterface[] list2;
    private final String[][] idset;
    public ListProducer(String[] id,CompareInterface[] ci){
//        System.out.println("id.length = " + id.length);
//        System.out.println("ci.length = " + ci.length);
        ArrayList<CompareInterface> list=new ArrayList<CompareInterface>();
        this.idset=new String[id.length][2];
        list.add(ci[0]);
        for (int i = 0; i < ci.length; i++) {
            CompareInterface ci1 = ci[i];
            String idval=id[i];
            boolean flag=true;
            for (int j = 0; j < list.size(); j++) {
                if(ci1.compare(list.get(j))){
                    idset[i][0]=idval;
                    idset[i][1]=""+j;
                    flag=false;
                    break;
                }
            }
            if(flag){
                list.add(ci1);
                idset[i][0]=idval;
                idset[i][1]=""+(list.size()-1);
            }
        }
        this.list2=(CompareInterface[])list.toArray(new CompareInterface[0]);
    }
    public String[][] getIDSet(){return idset;}
    public CompareInterface[] getList(){return list2;}
    
}
