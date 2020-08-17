/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.jdbc.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.User;

/**
 *
 * @author Admin
 */
public class UserDao {
    
     public List<User> getAllUser(){
        List<User> users = new ArrayList<User>();
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM NguoiDung";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
        ResultSet us = preparedStatement.executeQuery();

        while (us.next()) {
            User user = new User();
            user.setUsername(us.getString("Username"));
            user.setHoten(us.getString("HoTen"));
            user.setNamsinh(us.getDate("NamSinh"));
            user.setGioitinh(us.getString("GioiTinh"));
            user.setEmail(us.getString("Email"));
            user.setDienthoai(us.getString("DienThoai"));
            user.setMangdung(us.getString("MaNguoiDung"));
            
            users.add(user);
        }
         } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }
     public List<User> getUserID(String userName){
    	 List<User> userss = new ArrayList<User>();
    	 
    	 Connection con = JDBCConnection.getJDBCConnection();
    	 
    	 String sql = "SELECT * FROM NguoiDung nd where nd.Username = "+"\'"+userName+"\'";
    	 PreparedStatement preparedStatement;
         try {
             preparedStatement = con.prepareStatement(sql);
         ResultSet us = preparedStatement.executeQuery();
//         preparedStatement.setString(1, userName);
         while (us.next()) {
             User user = new User();
             user.setUsername(us.getString("Username"));
             user.setHoten(us.getString("HoTen"));
             user.setNamsinh(us.getDate("NamSinh"));
             user.setGioitinh(us.getString("GioiTinh"));
             user.setEmail(us.getString("Email"));
             user.setDienthoai(us.getString("DienThoai"));
             user.setMangdung(us.getString("MaNguoiDung"));
             
             userss.add(user);
         }
          } catch (SQLException ex) {
             Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
         }
         return userss;
     }
     public User getUserById(String ID) {

 		Connection con = JDBCConnection.getJDBCConnection();

 		String sql = "SELECT * FROM NguoiDung WHERE Username = ?";

 		PreparedStatement preparedStatement;
 		try {

 			preparedStatement = con.prepareStatement(sql);
 			preparedStatement.setString(1, ID);
 			ResultSet rs = preparedStatement.executeQuery();

 			while (rs.next()) {
 				 User user = new User();
 	             user.setUsername(rs.getString("Username"));
 	             user.setHoten(rs.getString("HoTen"));
 	             user.setNamsinh(rs.getDate("NamSinh"));
 	             user.setGioitinh(rs.getString("GioiTinh"));
 	             user.setEmail(rs.getString("Email"));
 	             user.setDienthoai(rs.getString("DienThoai"));
 	             user.setMangdung(rs.getString("MaNguoiDung"));

 	              return user;
 			}
 		} catch (SQLException ex) {
 			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
 		}
 		return null;
 	}
    public void addUser(User users){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO NguoiDung(Username, HoTen, NamSinh, GioiTinh, Email, DienThoai, MaNguoiDung) VALUES(?,?,?,?,?,?,?)";
        
        try {    
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, users.getUsername());
            preparedStatement.setString(2, users.getHoten());
            preparedStatement.setDate(3, users.getNamsinh());
            preparedStatement.setString(4, users.getGioitinh());
            preparedStatement.setString(5, users.getEmail());
            preparedStatement.setString(6, users.getDienthoai());
            preparedStatement.setString(7, users.getMangdung());
            
            preparedStatement.executeUpdate();
            
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        
    
    }
        public void updateUser(User user){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE NguoiDung SET HoTen = ?, NamSinh = ?, GioiTinh = ?,Email = ?,"
                  + "DienThoai = ?,MaNguoiDung = ? WHERE Username = ? ";
        try {
        	  PreparedStatement preparedStatement = con.prepareStatement(sql);
              preparedStatement.setString(1, user.getUsername());
              preparedStatement.setString(2, user.getHoten());
              preparedStatement.setDate(3, user.getNamsinh());
              preparedStatement.setString(4, user.getGioitinh());
              preparedStatement.setString(5, user.getEmail());
              preparedStatement.setString(6, user.getDienthoai());
              preparedStatement.setString(7, user.getMangdung());
              
              preparedStatement.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      public void deleteUser(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from NguoiDung where MaNguoiDung = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
