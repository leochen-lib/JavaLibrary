/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlib.tool;

import jlib.util.ALHM;
import java.util.Arrays;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author leo
 */
public class TooletStatic {
    protected static Debug de = new Debug(TooletStatic.class, true);
    
    public static long getPID() {
        String processName =
                java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return Long.parseLong(processName.split("@")[0]);
    }
    
    public static boolean isNumber(String input) {
    	if( input == null ){return false;}
        try {
            Integer check = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            de.println(e);
            return false;
        }
    }
    
    /* Data Sort */
    public static Integer[] sortInt(String... inputs) {
        Integer[] intArr = null;
        if (inputs.length > 0) {
            intArr = new Integer[inputs.length];
            for (int i = 0; i < inputs.length; i++) {
            	if ( isNumber(inputs[i]) ){
                    intArr[i] = Integer.parseInt(inputs[i]);
            	}else{
            		//intArr[i] = -65535;
            		de.println("FORMAT ERROR : " + inputs[i]);
            		return null;
            	}
            }
            Arrays.sort(intArr);
        } else {
            intArr = new Integer[0];
        }
        return intArr;
    }
    
    public static JSONArray list2json(ALHM input) {
        JSONArray jArr = new JSONArray();
        for (Map<String, Object> item : input) {
            JSONObject jObj = new JSONObject();
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
//                System.out.println(entry.getKey() + ":\n" + entry.getValue().getClass().getName() + ":" + entry.getValue());
//                if ( entry.getValue() instanceof List ){
//                    value = list2json((List)entry.getValue());
//                }
                
                try {
                    jObj.put(key, value);
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            jArr.put(jObj);
        }
        return jArr;
    }
}
