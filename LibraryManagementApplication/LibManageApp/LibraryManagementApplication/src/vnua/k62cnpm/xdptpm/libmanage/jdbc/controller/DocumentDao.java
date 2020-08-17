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

import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Document;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;

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

	public Document getDocumentById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiLieu WHERE MaTaiLieu = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
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

				return document;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addDocument(Document document) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO TaiLieu(MaTaiLieu,TenTaiLieu,MaTheLoai,MaNXB,MaTacGia,NamXB,"
				+ "NoiDung,SoTrang,GiaBia,MaViTri,NgayCapNhat) VALUES(?,?,?,?,?,?,?,?,?,?,?)";

		try {

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, document.getMatl());
			preparedStatement.setString(2, document.getTentl());
			preparedStatement.setString(3, document.getMatheloai());
			preparedStatement.setString(4, document.getManxb());
			preparedStatement.setString(5, document.getMatg());
			preparedStatement.setString(6, document.getNamxb());
			preparedStatement.setString(7, document.getNoidung());
			preparedStatement.setString(8, document.getSotrang());
			preparedStatement.setString(9, document.getGiabia());
			preparedStatement.setString(10, document.getMavitri());
			preparedStatement.setDate(11, document.getNgcapnhat());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Lỗi");
		}

	}

	public void updateDocument(Document document) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE TaiLieu SET TenTaiLieu = ?,MaTheLoai = ?,MaNXB = ?,MaTacGia = ?,NamXB = ?, NoiDung = ?,SoTrang = ?,GiaBia = ?,MaViTri = ?,NgayCapNhat = ? WHERE MaTaiLieu = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, document.getTentl());
			preparedStatement.setString(2, document.getMatheloai());
			preparedStatement.setString(3, document.getManxb());
			preparedStatement.setString(4, document.getMatg());
			preparedStatement.setString(5, document.getNamxb());
			preparedStatement.setString(6, document.getNoidung());
			preparedStatement.setString(7, document.getSotrang());
			preparedStatement.setString(8, document.getGiabia());
			preparedStatement.setString(9, document.getMavitri());
			preparedStatement.setDate(10, document.getNgcapnhat());
			preparedStatement.setString(11, document.getMatl());

			preparedStatement.executeUpdate();

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
			preparedStatement.executeUpdate();
		
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
			ex.printStackTrace();
			System.out.println("Lỗi");
		}

	}

	// Xử lý lấy dữ liệu từ CSDL SQL (TaiLieu -> những tài liệu hiện tại chưa được mượn hoặc trạng thái = "Idle")
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

	//Tài liệu đang được mượn
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

	//Tài liệu mượn nhiều nhất.
	public List<Document> getMostBrDocs() {
		List<Document> documents = new ArrayList<Document>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "Select y.MaTaiLieu, tll.TenTaiLieu, tll.NoiDung, y.SoLanDuocMuon\r\n" + 
				"from\r\n" + 
				"(Select x.MaTaiLieu, x.b as SoLanDuocMuon\r\n" + 
				"	from (\r\n" + 
				"		select tl.MaTaiLieu , COUNT(*) as b\r\n" + 
				"		from TaiLieu tl inner join PhieuMuon pm\r\n" + 
				"		on tl.MaTaiLieu = pm.MaTaiLieu\r\n" + 
				"		group by tl.MaTaiLieu\r\n" + 
				"		) x\r\n" + 
				") y, TaiLieu tll\r\n" + 
				"where y.MaTaiLieu = tll.MaTaiLieu";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Document document = new Document();
				document.setMatl(rs.getString("MaTaiLieu"));
				document.setTentl(rs.getString("TenTaiLieu"));
				document.setNoidung(rs.getString("NoiDung"));
				document.setSoLanDuocMuon(rs.getInt("SoLanDuocMuon"));
				
				documents.add(document);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return documents;
	}
}
