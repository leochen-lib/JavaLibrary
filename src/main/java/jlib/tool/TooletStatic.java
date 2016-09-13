/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlib.tool;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import jlib.util.ALHM;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.json.JSONArray;
import org.json.JSONException;
//import org.json.JSONObject;
import net.sf.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;
import net.sf.json.util.JSONUtils;
import net.sf.json.util.PropertyFilter;

/**
 *
 * @author leo
 */
public class TooletStatic {

    protected static Debug de = new Debug(TooletStatic.class, true);

    public static long getPID() {
        String processName
                = java.lang.management.ManagementFactory.getRuntimeMXBean().getName();
        return Long.parseLong(processName.split("@")[0]);
    }

    public static boolean isNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer check = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            de.println(e);
            return false;
        }
    }

    public static boolean isNull(Object input){
        if ( null == input ){return true;}
        else{return false;}
    }
    
    public static boolean isEmpty(Object input){
        boolean isNull = isNull(input);
        if(isNull){return true;}
        else if( "".equals(input.toString()) ){
            return true;
        }else{
            return false;
        }
    }

    /* Data Sort */
    public static Integer[] sortInt(String... inputs) {
        Integer[] intArr = null;
        if (inputs.length > 0) {
            intArr = new Integer[inputs.length];
            for (int i = 0; i < inputs.length; i++) {
                if (isNumber(inputs[i])) {
                    intArr[i] = Integer.parseInt(inputs[i]);
                } else {
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
    
    public static JSONArray list2json(List<? extends Map<String, Object>> input) {
        return list2json(input, true);
    }

    /**
     *
     * @param input
     * @param returnNull
     * @return
     */
    public static JSONArray list2json(List<? extends Map<String, Object>> input, boolean returnNull) {
        JSONArray jArr = new JSONArray();
        for (Map<String, Object> item : input) {
            JSONObject jObj = new JSONObject();
            Map<String, Object> jsonMap = new HashMap();
            for (Map.Entry<String, Object> entry : item.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();

                jsonMap.put(key, value);
//                }

//                if(null != value ){
//                    de.println("++++++++++++++ "+value.getClass().getSimpleName());
//                }
                try {
                    JsonConfig config = new JsonConfig();
                    if ( !returnNull ) {
                        config.setJsonPropertyFilter(new PropertyFilter() {
                            @Override
                            public boolean apply(Object o, String string, Object o1) {
                                if (o1 == null) {
                                    return true;
                                } else {
                                    return false;
                                }

                            }
                        });
                    }
                    config.registerJsonValueProcessor(Timestamp.class, new _DateJsonValueProcessor());
                    jObj = JSONObject.fromObject(jsonMap, config);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            jArr.add(jObj);
        }
        return jArr;
    }
}

class _DateJsonValueProcessor implements JsonValueProcessor{
    public static final String Default_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private DateFormat dateFormat;
     public _DateJsonValueProcessor() {
        dateFormat = new SimpleDateFormat(Default_DATE_PATTERN);
    }
     private Object process(Object value) {
        if (value == null) {
            return "";
        } else {
            return dateFormat.format((Timestamp) value);
        }
    }
    @Override
    public Object processArrayValue(Object o, JsonConfig jc) {
        return process(o);
    }

    @Override
    public Object processObjectValue(String string, Object o, JsonConfig jc) {
         return process(o);
    }
    
}
