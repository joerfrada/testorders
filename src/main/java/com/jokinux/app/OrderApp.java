/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jokinux.app;

import java.util.*;

import com.jokinux.business.*;
import com.jokinux.model.*;

/**
 *
 * @author Joe Ronald Florez Rada
 */
public class OrderApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        OrderCustomerManager ocMgr = new OrderCustomerManager();
        
        try {
            List<OrderCustomer> lstDatos = ocMgr.GetOrderCustomers();
            
            for (OrderCustomer oc: lstDatos) {
                System.out.println(oc.getOrderId() + ", " + oc.getCustomerId() + ", " + oc.getShippedDate());
            }
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
