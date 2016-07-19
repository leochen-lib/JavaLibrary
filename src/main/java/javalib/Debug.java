/*
 * debug.java
 *
 * Created on 2007/12/24, 上午 11:28:38
 *
 */
package javalib;

import java.util.Date;

/**
 *
 * @author Leo Chen
 */
public class Debug {
    protected static boolean debug = true;
    protected static String TAG = null;
    
    public Debug() {
        this.debug = true;
    }
    
    public Debug(boolean flag) {
        this.debug = flag;
    }
    
    public Debug(String TAG, boolean flag) {
        this.debug = flag;
        this.TAG = TAG;
    }
    
    public Debug(Class cc, boolean flag) {
        this.debug = flag;
        this.TAG = cc.getName();
    }
    
    public static void setDebug(boolean input){debug = debug;}
    public static boolean getDebug(){return debug;}
    
    public static void print(Object obj){
      if (debug){ System.out.print(obj.toString()); }
    } 
    
    public static void println(Object obj){
      if (debug){ System.out.println(obj.toString()); }
    } 
    
    public static void printDate(){
        Date date = new Date();
        if (debug){println("At "+ date.getTime() +" millisecond."); }
    }
    
}
