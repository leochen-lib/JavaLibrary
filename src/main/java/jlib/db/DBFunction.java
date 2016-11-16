/*
 * DB.java
 *
 * Created on 2007/12/24, 上午 11:28:38
 * Updated by Leo Chen 2016.07.20
 */
package jlib.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jlib.util.ALHM;
import jlib.util.HM;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static jlib.tool.TooletStatic.isNumber;

/**
 *
 * @author Leo Chen TODO 1. setData : update / insert / delete(?) 2. pure
 * functions 3. auto commit checking 4. SQLite support
 */
public class DBFunction extends DBBase {

    public DBFunction(String driverStr, String connectStr, String account, String password) {
        super(driverStr, connectStr, account, password);
    }
    
    ArrayList metaList;
    public ArrayList getMetaList() {return metaList;}

    public ALHM getALHM(String sql, Object... inputs) throws Exception{
        rs = exeQuery(sql, inputs);
        if (rs.getType() != java.sql.ResultSet.TYPE_FORWARD_ONLY) {
            rs.beforeFirst();
        }
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        
        ALHM resultALHM = new ALHM();
        metaList = new ArrayList();
        for (int i = 1; rs.next(); i++) {
            // add meta data
            if (i == 1) {
                for (int j = 1; j <= numColumns; j++) {
                    metaList.add(rsmd.getColumnLabel(j));
                }
            }
            // add row data
            HM item = new HM();
            for (int j = 1; j <= numColumns; j++) {
                String columnLabel = rsmd.getColumnLabel(j);
                int columnType = rsmd.getColumnType(j);
                
                switch(columnType){
                    case java.sql.Types.ARRAY :
                        item.put(columnLabel, rs.getArray(j));
                        break;
                    case java.sql.Types.BIGINT :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.BOOLEAN :
                        item.put(columnLabel, rs.getBoolean(j));
                        break;
                    case java.sql.Types.BLOB :
                        item.put(columnLabel, rs.getBlob(j));
                        break;
                    case java.sql.Types.DOUBLE :
                        item.put(columnLabel, rs.getDouble(j));
                        break;
                    case java.sql.Types.FLOAT :
                        item.put(columnLabel, rs.getFloat(j));
                        break;
                    case java.sql.Types.INTEGER :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.NVARCHAR :
                        item.put(columnLabel, rs.getNString(j));
                        break;
                    case java.sql.Types.VARCHAR :
                        item.put(columnLabel, rs.getString(j));
                        break;
                    case java.sql.Types.LONGVARBINARY :
                        item.put(columnLabel, rs.getString(j));
                        break;
                    case java.sql.Types.TINYINT :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.SMALLINT :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.DATE :
                        item.put(columnLabel, rs.getDate(j));
                        break;
                    case java.sql.Types.TIMESTAMP :
                        item.put(columnLabel, rs.getTimestamp(j));
                        break;
                    case java.sql.Types.NULL :
                        item.put(columnLabel, null);
                        break;
                    default :
                        item.put(columnLabel, rs.getObject(j));
                        break;
                }
                
            }
            resultALHM.add(item);
        }
        return resultALHM;
    }
    
    public JSONArray getJson(String sql, Object... inputs) throws Exception{
        rs = exeQuery(sql, inputs);
        if (rs.getType() != java.sql.ResultSet.TYPE_FORWARD_ONLY) {
            rs.beforeFirst();
        }
        
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();

        JSONArray resultJArr = new JSONArray();
        metaList = new ArrayList();
        for (int i = 1; rs.next(); i++) {
            // add meta data
            if (i == 1) {
                for (int j = 1; j <= numColumns; j++) {
                    metaList.add(rsmd.getColumnLabel(j));
                }
            }
            // add row data
            JSONObject item = new JSONObject();
            for (int j = 1; j <= numColumns; j++) {
                String columnLabel = rsmd.getColumnLabel(j);
                int columnType = rsmd.getColumnType(j);
                
                switch(columnType){
                    case java.sql.Types.ARRAY :
                        item.put(columnLabel, rs.getArray(j));
                        break;
                    case java.sql.Types.BIGINT :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.BOOLEAN :
                        item.put(columnLabel, rs.getBoolean(j));
                        break;
                    case java.sql.Types.BLOB :
                        item.put(columnLabel, rs.getBlob(j));
                        break;
                    case java.sql.Types.DOUBLE :
                        item.put(columnLabel, rs.getDouble(j));
                        break;
                    case java.sql.Types.FLOAT :
                        item.put(columnLabel, rs.getFloat(j));
                        break;
                    case java.sql.Types.INTEGER :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.NVARCHAR :
                        item.put(columnLabel, rs.getNString(j));
                        break;
                    case java.sql.Types.VARCHAR :
                        item.put(columnLabel, rs.getString(j));
                        break;
                    case java.sql.Types.TINYINT :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.SMALLINT :
                        item.put(columnLabel, rs.getInt(j));
                        break;
                    case java.sql.Types.DATE :
                        item.put(columnLabel, rs.getDate(j));
                        break;
                    case java.sql.Types.TIMESTAMP :
                        item.put(columnLabel, rs.getTimestamp(j));
                        break;
                    default :
                        item.put(columnLabel, rs.getObject(j));
                        break;
                }
            }
            resultJArr.put(item);
        }
        return resultJArr;
    }

    public ALHM setALHM(String sql, Object... inputs) throws Exception {
        int result = exeUpdate(sql, inputs);
        return new ALHM(new HM("result", result));
    }
    
    public JSONArray setJson(String sql, Object... inputs) throws Exception {
        int result = exeUpdate(sql, inputs);
        JSONObject item = new JSONObject();
        item.put("result", result);
        JSONArray resultJArr = new JSONArray();
        resultJArr.put(item);
        return resultJArr;
    }
    
    
    static int defaultPageSize = 10;
    static void setDefaultPageSize(int input){defaultPageSize = input;}
    static int getDefaultPageSize(int input){return defaultPageSize;}
    public static String pageing(String pageNumber){
        if ( isNumber(pageNumber) ){
            return pageing(defaultPageSize, Integer.parseInt(pageNumber));
        }else{
            return "";
        }
    }
    public static String pageing(int pageNumber){
        return pageing(defaultPageSize, pageNumber);
    }
    public static String pageing(String pageSize, String pageNumber){
        if ( isNumber(pageSize) && isNumber(pageNumber) ){
            return pageing(Integer.parseInt(pageSize), Integer.parseInt(pageNumber));
        }else{
            return "";
        }
    }
    public static String pageing(int pageSize, int pageNumber){
        if ( pageSize > 0 ){ 
            return " LIMIT "+ (pageNumber - 1) * pageSize + ", " + pageSize ;
        }else{
            return "";
        }
    }
    
}
