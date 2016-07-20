/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jlib.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import jlib.util.ALHM;
import jlib.util.HM;

/**
 *
 * @author Leo Chen 2016.07.20
 */
public class DBBase {
    
    //debug de = new debug(false);

//    public static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";

    protected String driverStr;
    protected String connectStr;
    protected String account;
    protected String password;
    protected Connection con = null;

    public void setDriver(String driverStr) {this.driverStr = driverStr;}
    public String getDriver() {return this.driverStr;}
    public void setConnectStr(String connectStr) {this.connectStr = connectStr;}
    public String getConnectStr() {return this.connectStr;}
    public void setAccount(String account) {this.account = account;}
    public void setPassword(String password) {this.password = password;}
    public Connection getConnection() {return con;}
    public void setConnection(Connection con) {this.con = con;}

    protected PreparedStatement ppst = null;
    protected ResultSet rs = null;

    /**
     * not support now. public DBPrepared(String driverStr, String connectStr) {
     * this.driverStr = driverStr; this.connectStr = connectStr; }
     */
    public DBBase(String driverStr, String connectStr, String account, String password) {
        this.driverStr = driverStr;
        this.connectStr = connectStr;
        this.account = account;
        this.password = password;
    }

    /**
     * not support sqlite now : 2014.05.22
     * Default AutoCommit : false
     */
    public void connect() throws Exception {
        Class.forName(driverStr);
        con = DriverManager.getConnection(connectStr, account, password);
        con.setAutoCommit(false);
    }
    
    public void connect(boolean autocommitFlag) throws Exception {
        Class.forName(driverStr);
        con = DriverManager.getConnection(connectStr, account, password);
        con.setAutoCommit(autocommitFlag);
    }

    public void disconnect() throws Exception {
        if (null != rs) {
            rs.close();
        }
        if (null != ppst) {
            ppst.close();
        }
        if (null != con) {
            con.close();
        }
    }
    
    public void setAutoCommit(boolean flag) throws Exception {
        con.setAutoCommit(flag);
    }
    
    public void commit() throws Exception {
        con.commit();
    }
    
    public void rollback() throws Exception {
        con.rollback();
    }
    
    // Basic Function
    
    /**
     * 
     * @param sql : "SELECT id, account, name FROM account WHERE account = ? and password = ?"
     * @param valueList : "['account', 'password']"
     * @return
     * @throws Exception 
     */
    protected ResultSet exeQuery(String sql, List valueList) throws Exception {
        ppst = con.prepareStatement(sql);
        for (int i = 0; null != valueList && i < valueList.size(); i++) {
            Object item = valueList.get(i);
            if ( item instanceof java.lang.String ){
                ppst.setString(i + 1, (String) item);
            }else if( item instanceof java.lang.Integer ){
                ppst.setInt(i + 1, (Integer) item);
            }
            
        }
        rs = ppst.executeQuery();
        return rs;
    }

    /**
     * 
     * @param sql : "INSERT INTO person (name, email) values (?, ?)"
     * @param valueList : "['name', 'email']"
     * @return
     * @throws Exception 
     */
    protected int exeUpdate(String sql, List valueList) throws Exception {
        int result = 0;
        ppst = con.prepareStatement(sql);
        for (int i = 0; null != valueList && i < valueList.size(); i++) {
            Object item = valueList.get(i);
            if ( item instanceof java.lang.String ) {
                ppst.setString( i + 1, (String) valueList.get(i) );
            }else if( item instanceof java.lang.Integer ){
                ppst.setInt(i + 1, (Integer) item);
            }
            
        }
        result = ppst.executeUpdate();
        return result;
    }
    
}