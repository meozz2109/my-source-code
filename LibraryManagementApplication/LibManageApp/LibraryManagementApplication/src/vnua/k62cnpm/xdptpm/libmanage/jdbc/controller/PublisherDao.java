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

import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Author;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Publisher;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;

/**
 *
 * @author Admin
 */
public class PublisherDao {
        
    public List<Publisher> getAllPublisher(){
        List<Publisher> publishers = new ArrayList<Publisher>();
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM NhaXuatBan";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Publisher publisher = new Publisher();
                publisher.setManxb(rs.getString("MaNXB"));
                publisher.setTennxb(rs.getString("TenNXB"));
                publisher.setGhichu(rs.getString("GhiChu"));

                publishers.add(publisher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return publishers;
    }
    public void addPublisher(Publisher publisher){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO NhaXuatBan(MaNXB,TenNXB,GhiChu) VALUES(?,?,?)";
        
        try {
            
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, publisher.getManxb());
            preparedStatement.setString(2, publisher.getTennxb());
            preparedStatement.setString(3, publisher.getGhichu());
           
            
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        
    
    }
    public Publisher getPublisherById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM NhaXuatBan WHERE MaNXB = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Publisher publisher = new Publisher();
				
				publisher.setTennxb(rs.getString("TenNXB"));
				publisher.setGhichu(rs.getString("GhiChu"));
				publisher.setManxb(rs.getString("MaNXB"));

				return publisher;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
        public void updatePublisher(Publisher publisher){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE NhaXuatBan SET TenNXB = ?, GhiChu = ? WHERE MaNXB = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, publisher.getTennxb());
            preparedStatement.setString(2, publisher.getGhichu());
            preparedStatement.setString(3, publisher.getManxb());
          
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      public void deletePublisher(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from NhaXuatBan where MaNXB = ?";

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
