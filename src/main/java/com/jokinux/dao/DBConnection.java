/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jokinux.dao;

import java.sql.*;

/**
 *
 * @author Joe Ronald Florez Rada
 */
public class DBConnection {
    private String username;
    private String password;
    private String url = null;
    
    public DBConnection() {}
    
    public void setConnection(String hostname, String username, String password, String port, String dbname) {
        this.url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + dbname;
        this.username = username;
        this.password = password;
    }
    
    private Connection getConnection() throws Exception {
        Connection conn = null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            
            conn = DriverManager.getConnection(this.url, this.username, this.password);
            
            if (conn == null) {
                System.out.println("Error: The database could not open.");
            }
        }
        catch (ClassNotFoundException ex) {
            throw new Exception("ClassNotFoundException: " + ex.getMessage());
        }
        catch (SQLException ex) {
            throw new Exception("SQLException: " + ex.getMessage());
        }
        
        return conn;
    }
    
    public CallableStatement ExecuteProcedure(String sqlProcedure) throws Exception {
        Connection conn = null;
        CallableStatement callStmt = null;
        
        try {
            conn = this.getConnection();
            String psql = "begin " + sqlProcedure + "; end;";
            
            callStmt = conn.prepareCall(psql);
        }
        catch (Exception ex) {
            throw new Exception("ExecuteProcedure: " + ex.getMessage());
        }
        
        return callStmt;
    }
}
