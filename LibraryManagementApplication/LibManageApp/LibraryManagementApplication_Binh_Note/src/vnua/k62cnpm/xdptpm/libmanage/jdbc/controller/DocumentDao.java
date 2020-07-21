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
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.Reader;

/**
 *
 * @author Admin
 */
public class DocumentDao {
	public List<Document> getAllDocument() {
		List<Document> documents = new ArrayList<Document>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiLieu";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Document document = new Document();
				document.setMatl(rs.getString("MaTaiLieu"));
				document.setTentl(rs.getString("TenTaiLieu"));
				document.setMatheloai(rs.getString("MaTheLoai"));
				document.setManxb(rs.getString("MaNXB"));
				document.setMatg(rs.getString("MaTacGia"));
				document.setNamxb(rs.getString("NamXB"));
				document.setNoidung(rs.getString("NoiDung"));
				document.setSotrang(rs.getString("SoTrang"));
				document.setGiabia(rs.getString("GiaBia"));
				document.setMavitri(rs.getString("MaViTri"));
				document.setNgcapnhat(rs.getDate("NgayCapNhat"));

				documents.add(document);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return documents;
	}

	public void addReader(Reader readers) {

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

	public void updateDocument(Document document) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE DocGia SET HoTen = ?, Email = ?, SoDienThoai = ?,CMT = ?,"
				+ "NgayCapTK = ?,NgayHetHanTK = ?,MaNguoiDungCapNhap = ? WHERE MaDocGia = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
//            preparedStatement.setString(1, readers.getName());
//            preparedStatement.setString(2, readers.getEmail());
//            preparedStatement.setString(3, readers.getSdt());
//            preparedStatement.setString(4, readers.getEmail());
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

	public void deleteDocument(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from TaiLieu where MaTaiLieu = ?";

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public List<Document>  getRecentIdleDocs() {
		List<Document> documents = new ArrayList<Document>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiLieu tl INNER JOIN TaiLieuChiTiet tlct on tl.MaTaiLieu = tlct.MaTaiLieu  "
				+ "WHERE tlct.TinhTrang = 'Idle' ";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Document document = new Document();
				document.setMatl(rs.getString("MaTaiLieu"));
				document.setTentl(rs.getString("TenTaiLieu"));
				document.setMatheloai(rs.getString("MaTheLoai"));
				document.setManxb(rs.getString("MaNXB"));
				document.setMatg(rs.getString("MaTacGia"));
				document.setNamxb(rs.getString("NamXB"));
				document.setNoidung(rs.getString("NoiDung"));
				document.setSotrang(rs.getString("SoTrang"));
				document.setGiabia(rs.getString("GiaBia"));
				document.setMavitri(rs.getString("MaViTri"));
				document.setNgcapnhat(rs.getDate("NgayCapNhat"));

				documents.add(document);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return documents;
	}

	public List<Document> getRecentBorDocs() {
		List<Document> documents = new ArrayList<Document>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiLieu tl INNER JOIN TaiLieuChiTiet tlct on tl.MaTaiLieu = tlct.MaTaiLieu  "
				+ "WHERE tlct.TinhTrang = 'Borrowed' ";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Document document = new Document();
				document.setMatl(rs.getString("MaTaiLieu"));
				document.setTentl(rs.getString("TenTaiLieu"));
				document.setMatheloai(rs.getString("MaTheLoai"));
				document.setManxb(rs.getString("MaNXB"));
				document.setMatg(rs.getString("MaTacGia"));
				document.setNamxb(rs.getString("NamXB"));
				document.setNoidung(rs.getString("NoiDung"));
				document.setSotrang(rs.getString("SoTrang"));
				document.setGiabia(rs.getString("GiaBia"));
				document.setMavitri(rs.getString("MaViTri"));
				document.setNgcapnhat(rs.getDate("NgayCapNhat"));

				documents.add(document);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return documents;
	}

	public List<Document> getMostBrDocs() {
		List<Document> documents = new ArrayList<Document>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT *, COUNT(pm.MaTaiLieu) FROM TaiLieu tl INNER JOIN TaiLieuChiTiet tlct ON tl.MaTaiLieu = tlct.MaTaiLieu "
				+ "INNER JOIN PhieuMuon pm ON tlct.MaTaiLieu = pm.MaTaiLieu"
				+ ""
				+ "";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Document document = new Document();
				document.setMatl(rs.getString("MaTaiLieu"));
				document.setTentl(rs.getString("TenTaiLieu"));
				document.setMatheloai(rs.getString("MaTheLoai"));
				document.setManxb(rs.getString("MaNXB"));
				document.setMatg(rs.getString("MaTacGia"));
				document.setNamxb(rs.getString("NamXB"));
				document.setNoidung(rs.getString("NoiDung"));
				document.setSotrang(rs.getString("SoTrang"));
				document.setGiabia(rs.getString("GiaBia"));
				document.setMavitri(rs.getString("MaViTri"));
				document.setNgcapnhat(rs.getDate("NgayCapNhat"));

				documents.add(document);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return documents;
	}

	
}
