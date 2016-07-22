package jlib.util;

import jlib.tool.Print;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import jlib.tool.Print;

/**
 *
 * @author leo
 */
public class ALHM extends ArrayList<HashMap<String, Object>> implements java.io.Serializable {
    
    public ALHM(){
        super();
    }
    public ALHM(ALHM alhm){
        this.addAll(alhm);
    }
    public ALHM(ArrayList al){
        this.addAll(al);
    }
    public ALHM(HM hm){
        this.add(hm);
    }

    
    public void printAll(){
        Print.printALHM(this);
    }
    public void print(int i){
        Print.printHM((HM)this.get(i));
    }
}
