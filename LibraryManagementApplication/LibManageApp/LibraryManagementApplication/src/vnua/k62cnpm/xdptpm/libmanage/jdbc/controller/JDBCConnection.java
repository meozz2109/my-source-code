/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.jdbc.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import vnua.k62cnpm.xdptpm.libmanage.logic.AppConst;

/**
 *
 * @author Admin
 */
public class JDBCConnection {
        
    public static Connection getJDBCConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            try {
                return DriverManager.getConnection(""+AppConst.JDBC_CONNECTION);
            } catch (SQLException ex) {
                Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    
    }
    public static void main(String[] args) {
        Connection connection = getJDBCConnection();
        if(connection != null){
            System.out.println("Thanh cong");
        }
        else{
            System.out.println("That bai");
        }
    }
    
}
