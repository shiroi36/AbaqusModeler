/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package util.io;

import java.io.*;

/**
 *テキストファイルを出力するクラスです。
 * @author araki keita
 */
public class TXT_OPE {
     /**
     *出力するテキストファイルを保存するパスを指定するメソッド
     *@param pass　テキストファイルを保存するパスをstring型で入力
     */
    PrintWriter pw;
    public void setFileName(String pass){
        try{
            pw = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(new File(pass)), "Shift_JIS")));
        }
        catch(IOException e){
            System.out.println("入出力エラーです");///ちゃんとフォルダを作ったか？
        }
    }
    /**
     *ファイルに1行書き込むメソッド
     * @param obj　ファイルに書き込む内容
     */
    public void println(Object obj){
        pw.println(obj);
    }
    /**
     *数値配列を面的に書き込むメソッド
     * @param  obj 書き込む数値配列
     * @param  num　書き込む長さ
     */
    public void printSequence(double[][] obj,int num){
        for(int i=0;i<num;i++){
            for(int s=0;s<obj.length;s++){
                if(i>=obj[s].length){
                    pw.print("    ");
                }
                else{
                    pw.print(obj[s][i]+"    ");
                }
            }
            pw.println();
        }
    }
    /**
     *テキストファイルを書き終えたら必ずこのメソッドを実行する。
     */
    public void finish(){
        pw.close();
    }
    
    
    public void append(String path){
        //head.txtの読み込み
        try {
            File file = new File(path);
            BufferedReader b_reader = new BufferedReader(
                    new InputStreamReader(new FileInputStream(file), "Shift_JIS"));
            String s;
            while ((s = b_reader.readLine()) != null) {
                this.println(s);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    
    
}
