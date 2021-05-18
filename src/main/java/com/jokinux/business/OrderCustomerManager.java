/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jokinux.business;

import java.io.*;
import java.util.*;
import java.sql.*;
import oracle.jdbc.OracleTypes;

import com.jokinux.dao.DBConnection;
import com.jokinux.model.OrderCustomer;

/**
 *
 * @author Joe Ronald Florez Rada
 */
public class OrderCustomerManager {
    private DBConnection db = new DBConnection();
    
    public OrderCustomerManager() {
        this.db.setConnection("192.168.1.33", "ot", "C0l0mb14", "1523", "northwind");
    }
    
    public List<OrderCustomer> GetOrderCustomers() throws Exception {
        List<OrderCustomer> lstDatos = new ArrayList<OrderCustomer>();
        
        try {
            String sqlProc = "pq_api_orders.pr_get_orders_customers(?)";
            
            CallableStatement stmt = db.ExecuteProcedure(sqlProc);
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            stmt.execute();
            
            ResultSet rs = (ResultSet) stmt.getObject(1);
            
            while (rs.next()) {
                OrderCustomer item = new OrderCustomer();
                
                item.setOrderId(rs.getInt("orderid"));
                item.setCustomerId(rs.getString("customerid"));
                item.setEmployeeId(rs.getInt("employeeid"));
                item.setOrderDate(rs.getString("orderdate"));
                item.setRequiredDate(rs.getString("requireddate"));
                item.setShippedDate(rs.getString("shippeddate"));
                item.setShipVia(rs.getString("shipvia"));
                item.setFreight(rs.getString("freight"));
                item.setShipName(rs.getString("shipname"));
                item.setShipAddress(rs.getString("shipaddress"));
                item.setShipCity(rs.getString("shipcity"));
                item.setShipRegion(rs.getString("shipregion"));
                item.setShipPostalCode(rs.getString("shippostalcode"));
                item.setShipCountry(rs.getString("shipcountry"));
                item.setCompanyName(rs.getString("companyname"));
                item.setAddress(rs.getString("address"));
                item.setCity(rs.getString("city"));
                item.setRegion(rs.getString("region"));
                item.setPostalCode(rs.getString("postalcode"));
                item.setCountry(rs.getString("country"));
                
                lstDatos.add(item);
            }
        }
        catch (Exception ex) {
            throw new Exception("GetOrderCustomers: " + ex.getMessage());
        }
        
        return lstDatos;
    }
}
