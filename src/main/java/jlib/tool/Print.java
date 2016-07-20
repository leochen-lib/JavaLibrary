package jlib.tool;

import jlib.tool.Debug;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author Leo Chen
 */
public class Print {
    protected static String TAG = Print.class.getName();
    protected static Debug de = new Debug(true);
    
    public Print(){}
    
    public Print(String tag){
        de = new Debug(tag, true);
    }
    
    public Print(Class cc){
        de = new Debug(cc, true);
    }
    
    public static void printALHM(ArrayList<HashMap<String, Object>> input) {
        de.println("[");
        for( HashMap map : input ){
            printHM(map);
        }
        de.println("]");
    }
    
    public static void printHM(HashMap<String, Object> input) {
        de.println("{");
        for(Map.Entry<String, Object> entry : input.entrySet()){
            if ( entry.getValue() instanceof java.util.ArrayList ){
                de.print(entry.getKey() + ":");
                printALHM((ArrayList<HashMap<String, Object>>) entry.getValue());
            }else if ( entry.getValue() instanceof java.util.HashMap ){
                de.print(entry.getKey() + ":");
                printHM((HashMap<String, Object>) entry.getValue());
            }else{
//                de.println(entry.getKey() + "-" + entry.getValue().getClass().getName() + ":" + entry.getValue());
                de.println(entry.getKey() + ":" + entry.getValue() + ";");
            }
        }
        de.println("}");
    }
    
    
}
