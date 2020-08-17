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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.ReceiptNote;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;

/**
 *
 * @author Admin
 */
public class ReceiptNoteDao {
        
        public List<ReceiptNote> getAllReceiptNote(){
        List<ReceiptNote> receiptNotes = new ArrayList<ReceiptNote>();
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM PhieuNhap";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            ReceiptNote receiptNote = new ReceiptNote();
            receiptNote.setMaphieunhap(rs.getString("MaPhieuNhap"));
            receiptNote.setMatl(rs.getString("MaTaiLieu"));
            receiptNote.setMancc(rs.getString("MaNCC"));
            receiptNote.setMangdung(rs.getString("MaNguoiDungNhap"));
            receiptNote.setNgaynhap(rs.getDate("NgayNhap"));
            receiptNote.setSoluong(rs.getString("SoLuong"));
            
           

            receiptNotes.add(receiptNote);
        }
         } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return receiptNotes;
    }
        public ReceiptNote getReceiptNoteById(String ID) {

    		Connection con = JDBCConnection.getJDBCConnection();

    		String sql = "SELECT * FROM PhieuNhap WHERE MaPhieuNhap = ?";

    		PreparedStatement preparedStatement;
    		try {

    			preparedStatement = con.prepareStatement(sql);
    			preparedStatement.setString(1, ID);
    			ResultSet rs = preparedStatement.executeQuery();

    			while (rs.next()) {
    				 ReceiptNote receiptNote = new ReceiptNote();
    		            receiptNote.setMaphieunhap(rs.getString("MaPhieuNhap"));
    		            receiptNote.setMatl(rs.getString("MaTaiLieu"));
    		            receiptNote.setMancc(rs.getString("MaNCC"));
    		            receiptNote.setMangdung(rs.getString("MaNguoiDungNhap"));
    		            receiptNote.setNgaynhap(rs.getDate("NgayNhap"));
    		            receiptNote.setSoluong(rs.getString("SoLuong"));

    				return receiptNote;
    			}
    		} catch (SQLException ex) {
    			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
    		}
    		return null;
    	}
    public void addReceiptNote(ReceiptNote receiptNote){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO PhieuNhap(MaPhieuNhap,MaTaiLieu,MaNCC,MaNguoiDungNhap,NgayNhap,SoLuong) VALUES(?,?,?,?,?,?)";
        
        try {
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, receiptNote.getMaphieunhap());
            preparedStatement.setString(2, receiptNote.getMatl());
            preparedStatement.setString(3, receiptNote.getMancc());
            preparedStatement.setString(4, receiptNote.getMangdung());
            preparedStatement.setDate(5, receiptNote.getNgaynhap());
            preparedStatement.setString(6, receiptNote.getSoluong());
                     
            preparedStatement.executeUpdate();
              
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Lỗi");
        }
        
    
    }
        public void updateReceiptNote(ReceiptNote receiptNote){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE PhieuNhap SET MaTaiLieu = ?, MaNCC = ?, MaNguoiDungNhap = ?,NgayNhap = ?,"
                  + "SoLuong = ? WHERE MaPhieuNhap = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, receiptNote.getMaphieunhap());
            preparedStatement.setString(2, receiptNote.getMatl());
            preparedStatement.setString(3, receiptNote.getMancc());
            preparedStatement.setString(4, receiptNote.getMangdung());
            preparedStatement.setDate(5, receiptNote.getNgaynhap());
            preparedStatement.setString(6, receiptNote.getSoluong());

            preparedStatement.executeUpdate();   
            
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      public void deleteReceiptnote(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from PhieuNhap where MaPhieuNhap = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
