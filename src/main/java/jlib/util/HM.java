package jlib.util;

import jlib.tool.Print;
import java.util.*;
import jlib.tool.Print;

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
