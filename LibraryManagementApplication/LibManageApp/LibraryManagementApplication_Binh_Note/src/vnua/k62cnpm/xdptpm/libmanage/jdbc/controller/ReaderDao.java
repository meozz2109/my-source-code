/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.jdbc.controller;

import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Reader;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
//import qlythuvien.model.Reader;

/**
 *
 * @author Admin
 */
public class ReaderDao {
    public List<Reader> getAllReader(){
        List<Reader> reader = new ArrayList<Reader>();
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM DocGia";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()) {
            Reader readers = new Reader();
            readers.setID(rs.getString("MaDocGia"));
            readers.setName(rs.getString("HoTen"));
            readers.setEmail(rs.getString("Email"));
            readers.setSdt(rs.getString("SoDienThoai"));
            readers.setMatk(rs.getString("MaTK"));
            readers.setNgaycap(rs.getDate("NgayCapTK"));
            readers.setNgayhethan(rs.getDate("NgayHetHanTK"));
            readers.setNgdungcapnhap(rs.getString("MaNguoiDungCapNhat"));
            readers.setNgaygiahan(rs.getDate("NgayGiaHanTK"));
            reader.add(readers);
        }
         } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reader;
    }
    public void addReader(Reader readers){
        Connection con = JDBCConnection.getJDBCConnection();
        System.out.println("Reader DAO "+readers.getID()+" "+readers.getNgaycap()+" "+readers.getNgaygiahan()+" "+readers.getEmail());
        String sql = "INSERT INTO DocGia(MaDocGia,HoTen,Email,SoDienThoai,MaTK,NgayCapTK,"
                + "NgayHetHanTK,MaNguoiDungCapNhat,NgayGiaHanTK) VALUES(?,?,?,?,?,?,?,?,?)";
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
            System.out.println("On try catch ");
            int rs = preparedStatement.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error");
        }
        
    
    }
        public void updateReader(Reader readers){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE DocGia SET HoTen = ?, Email = ?, SoDienThoai = ?,MaTK = ?,"
                  + "NgayCapTK = ?,NgayHetHanTK = ?,MaNguoiDungCapNhap = ?, NgayGiaHanTK = ?  WHERE MaDocGia = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, readers.getName());
            preparedStatement.setString(2, readers.getEmail());
            preparedStatement.setString(3, readers.getSdt());
            preparedStatement.setString(4, readers.getEmail());
            preparedStatement.setDate(5, readers.getNgaycap());
            preparedStatement.setDate(6, readers.getNgayhethan());
            preparedStatement.setString(7, readers.getNgdungcapnhap());
            preparedStatement.setDate(8, readers.getNgaygiahan());
            preparedStatement.setString(9, readers.getID());
            
            int rs = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public void deleteReader(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from DocGia where MaDocGia = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            int rs = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
      public List<Reader> getOverdueReaders() {
  		List<Reader> reader = new ArrayList<Reader>();

  		Connection con = JDBCConnection.getJDBCConnection();

  		String sql = "select * from DocGia dg "
  				+ " join PhieuMuon pm on dg.MaDocGia=pm.MaDocGia\r\n" + 
  				"	where CAST(pm.HanTra as date) < CAST(GETDATE() as date)\r\n" + 
  				"	and not exists ("
  				+ "		select * from PhieuTra pt "
  				+ "		where pt.MaDocGia=dg.MaDocGia and pt.MaTaiLieu=pm.MaTaiLieu)";

  		PreparedStatement preparedStatement;
  		try {
  			preparedStatement = con.prepareStatement(sql);
  			ResultSet rs = preparedStatement.executeQuery();

  			while (rs.next()) {
  				Reader readers = new Reader();
  	            readers.setID(rs.getString("MaDocGia"));
  	            readers.setName(rs.getString("HoTen"));
  	            readers.setEmail(rs.getString("Email"));
  	            readers.setSdt(rs.getString("SoDienThoai"));
  	            readers.setMatk(rs.getString("MaTK"));
  	            readers.setNgaycap(rs.getDate("NgayCapTK"));
  	            readers.setNgayhethan(rs.getDate("NgayHetHanTK"));
  	            readers.setNgdungcapnhap(rs.getString("MaNguoiDungCapNhat"));
  	            readers.setNgaygiahan(rs.getDate("NgayGiaHanTK"));
  	            reader.add(readers);
  			}
  		} catch (SQLException ex) {
  			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
  		}
  		return reader;
  	}
}       
    
    

