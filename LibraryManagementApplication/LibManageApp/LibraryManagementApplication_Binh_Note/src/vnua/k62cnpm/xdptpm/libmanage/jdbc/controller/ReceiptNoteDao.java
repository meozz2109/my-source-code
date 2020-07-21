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
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.ReceiptNote;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Subject;

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
    public void addReader(Reader readers){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO DocGia(MaDocGia,HoTen,Email,SoDienThoai,MaTK,NgayCapTK,"
                + "NgayHetHanTK,MaNguoiDungCapNhap,NgayGiaHanTK) VALUE(?,?,?,?,?,?,?,?,?)";
        
        try {
            
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, readers.getID());
            preparedStatement.setString(2, readers.getName());
            preparedStatement.setString(3, readers.getEmail());
            preparedStatement.setString(4, readers.getSdt());
            preparedStatement.setString(5, readers.getMatk());
            preparedStatement.setDate(6, readers.getNgaycap());
            preparedStatement.setDate(7, readers.getNgayhethan());
            preparedStatement.setString(8, readers.getNgdungcapnhap());
            preparedStatement.setDate(9, readers.getNgaygiahan());
            
            
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
      public void deleteReceiptnote(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from PhieuNhap where MaPhieuNhap = ?";

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
