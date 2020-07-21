/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.jdbc.controller;

import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
          
            
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        
    
    }
        public void updateReader(Reader readers){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE DocGia SET HoTen = ?, Email = ?, SoDienThoai = ?,CMT = ?,"
                  + "NgayCapTK = ?,NgayHetHanTK = ?,MaNguoiDungCapNhap = ? WHERE MaDocGia = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, readers.getName());
            preparedStatement.setString(2, readers.getEmail());
            preparedStatement.setString(3, readers.getSdt());
            preparedStatement.setString(4, readers.getEmail());
//            preparedStatement.setDate(5, readers.getNgaycap());
//            preparedStatement.setDate(6, readers.getNgayhethan());
//            preparedStatement.setString(7, readers.getNgdungcapnhap());
//            preparedStatement.setString(7, readers.getID());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
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
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
