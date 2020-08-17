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

import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.SignoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;

/**
 *
 * @author Admin
 */
public class DetaileDocDao {
	public List<DetaileDoc> getAllDetaileDoc() {
		List<DetaileDoc> detaileDocs = new ArrayList<DetaileDoc>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiLieuChiTiet";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				DetaileDoc detaileDoc = new DetaileDoc();
				detaileDoc.setMavachsach(rs.getString("MaVachSach"));
				detaileDoc.setMatl(rs.getString("MaTaiLieu"));
				detaileDoc.setNgaycapnhat(rs.getDate("NgayCapNhat"));
				detaileDoc.setMangdung(rs.getString("MaNguoiDungCapNhat"));
				detaileDoc.setTinhtrang(rs.getString("TinhTrang"));
				detaileDoc.setDanhdauxuly(rs.getString("DanhDauXuLy"));

				detaileDocs.add(detaileDoc);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return detaileDocs;
	}

	public DetaileDoc getDetaileById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiLieuChiTiet WHERE MaVachSach = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				DetaileDoc detaileDoc = new DetaileDoc();
				detaileDoc.setMavachsach(rs.getString("MaVachSach"));
				detaileDoc.setMatl(rs.getString("MaTaiLieu"));
				detaileDoc.setNgaycapnhat(rs.getDate("NgayCapNhat"));
				detaileDoc.setMangdung(rs.getString("MaNguoiDungCapNhat"));
				detaileDoc.setTinhtrang(rs.getString("TinhTrang"));
				detaileDoc.setDanhdauxuly(rs.getString("DanhDauXuLy"));

				return detaileDoc;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addDetaileDoc(DetaileDoc detaileDoc) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO TaiLieuChiTiet(MaVachSach,MaTaiLieu,NgayCapNhat,MaNguoiDungCapNhat,TinhTrang,DanhDauXuLy) VALUES(?,?,?,?,?,?)";

		try {

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, detaileDoc.getMavachsach());
			preparedStatement.setString(2, detaileDoc.getMatl());
			preparedStatement.setDate(3, detaileDoc.getNgaycapnhat());
			preparedStatement.setString(4, detaileDoc.getMangdung());
			preparedStatement.setString(5, detaileDoc.getTinhtrang());
			preparedStatement.setString(6, detaileDoc.getDanhdauxuly());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Lỗi");
		}

	}

	public void updateDetaileDoc(DetaileDoc detaileDoc) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE TaiLieuChiTiet SET MaTaiLieu = ?, NgayCapNhat = ?, MaNguoiDungCapNhat = ?,TinhTrang = ?,"
				+ "DanhDauXuLy = ? WHERE MaVachSach = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, detaileDoc.getMatl());
			preparedStatement.setDate(2, detaileDoc.getNgaycapnhat());
			preparedStatement.setString(3, detaileDoc.getMangdung());
			preparedStatement.setString(4, detaileDoc.getTinhtrang());
			preparedStatement.setString(5, detaileDoc.getDanhdauxuly());
			preparedStatement.setString(6, detaileDoc.getMavachsach());

			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void updateBorrowedStatus(String docID){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "UPDATE TaiLieuChiTiet SET TinhTrang = 'Borrowed' WHERE MaTaiLieu = ? ";
      try {
          PreparedStatement preparedStatement = con.prepareStatement(sql);
          preparedStatement.setString(1, docID);
          
          preparedStatement.executeUpdate();  
          
      } catch (SQLException ex) {
          Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
      }
      
  }
	public void updateIdleStatus(String docID){
		
		Connection con = JDBCConnection.getJDBCConnection();
		
		String sql = "UPDATE TaiLieuChiTiet SET TinhTrang = 'Idle' WHERE MaTaiLieu = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, docID);
			
			preparedStatement.executeUpdate();  
			
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	public void deleteDetailed(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from TaiLieuChiTiet where MaVachSach = ?";

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
