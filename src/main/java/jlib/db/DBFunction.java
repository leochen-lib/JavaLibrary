/*
 * DB.java
 *
 * Created on 2007/12/24, 上午 11:28:38
 * Updated by Leo Chen 2016.07.20
 */
package jlib.db;

import java.sql.*;
import java.util.List;
import jlib.util.ALHM;
import jlib.util.HM;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Leo Chen TODO 1. setData : update / insert / delete(?) 2. pure
 * functions 3. auto commit checking 4. SQLite support
 */
public class DBFunction extends DBBase {

    public DBFunction(String driverStr, String connectStr, String account, String password) {
        super(driverStr, connectStr, account, password);
    }
    
    ALHM metaList;
    public ALHM getMetaList() {return metaList;}
    
    public JSONArray selectJson(String sql, List valueList) throws SQLException, JSONException, Exception {
        rs = exeQuery(sql, valueList);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        metaList = new ALHM();
        
        if (rs.getType() != java.sql.ResultSet.TYPE_FORWARD_ONLY) {
            rs.beforeFirst();
        }

        JSONArray jArr = new JSONArray();
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
            jArr.put(item);
        }

        return jArr;
    }

    public ALHM selectList(String sql, List valueList) throws Exception {
        rs = exeQuery(sql, valueList);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numColumns = rsmd.getColumnCount();
        metaList = new ALHM();
        
        if (rs.getType() != java.sql.ResultSet.TYPE_FORWARD_ONLY) {
            rs.beforeFirst();
        }
        
        ALHM resultList = new ALHM();
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
            resultList.add(item);
        }
        return resultList;
    }

//    public ALHM set(String sql, List valueList) throws Exception {
//        int result = exeUpdate(sql, valueList);
//        HM hm = new HM();
//        hm.put("result", result);
//        ALHM resultList = new ALHM();
//        resultList.add(hm);
//        return resultList;
//    }
    
    
}
