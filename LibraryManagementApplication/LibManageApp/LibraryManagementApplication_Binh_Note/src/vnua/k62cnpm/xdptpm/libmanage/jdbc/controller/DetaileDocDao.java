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
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.DetaileDoc;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Reader;

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
				detaileDoc.setNgaycapnhat(rs.getString("NgayCapNhat"));
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

	public void addReader(Reader readers) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO DocGia(MaDocGia,HoTen,Email,SoDienThoai,MaTK,NgayCapTK,"
				+ "NgayHetHanTK,MaNguoiDungCapNhap,NgayGiaHanTK) VALUES(?,?,?,?,?,?,?,?,?)";

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

	public void updateReader(Reader readers) {

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

	public void deleteDetailed(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from TaiLieuChiTiet where MaVachSach = ?";

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
