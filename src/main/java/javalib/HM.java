package javalib;

import javalib.Print;
import java.util.*;

/**
 *
 * @author leo
 */
public class HM extends HashMap<String, Object> implements java.io.Serializable{
    
    public HM(){
        super();
    }
    
    public HM(HM hm){
        this.putAll(hm);
    }
    
    public HM(HashMap hm){
        this.putAll(hm);
    }
    
    public HM(String key, Object value){
        this.put(key, value);
    }
    
    public void printAll(){
        Print.printHM(this);
    }
    
}
