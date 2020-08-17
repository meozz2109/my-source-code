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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Publisher;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Supplier;

/**
 *
 * @author Admin
 */
public class SubjectDao {
        
    public List<Subject> getAllSubject(){
        List<Subject> subjects = new ArrayList<Subject>();
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM TheLoai";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            Subject subject = new Subject();
            subject.setMatheloai(rs.getString("MaTheLoai"));
            subject.setTentheloai(rs.getString("TenTheLoai"));
            subject.setGhichu(rs.getString("GhiChu"));
            
           

            subjects.add(subject);
        }
         } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return subjects;
    }
    public Subject getSubjectById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TheLoai WHERE MaTheLoai = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				 Subject subject = new Subject();
		            subject.setMatheloai(rs.getString("TenTheLoai"));
		            subject.setTentheloai(rs.getString("GhiChu"));
		            subject.setGhichu(rs.getString("MaTheLoai"));

				return subject;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
    public void addSubject(Subject subject){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO TheLoai(MaTheLoai, TenTheLoai, GhiChu) VALUES(?,?,?)";
        
        try {
            
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, subject.getMatheloai());
            preparedStatement.setString(2, subject.getTentheloai());
            preparedStatement.setString(3, subject.getGhichu());
  
            preparedStatement.executeUpdate();
                       
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        
    
    }
        public void updateSubject(Subject subject){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE TheLoai SET TenTheLoai = ?, GhiChu = ? WHERE MaTheLoai = ? ";
        try {          
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, subject.getTentheloai());
            preparedStatement.setString(2, subject.getGhichu());
            preparedStatement.setString(3, subject.getMatheloai());
            
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      public void deleteSubject(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from TheLoai where MaTheLoai = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
