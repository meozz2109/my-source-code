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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Subject;

/**
 *
 * @author Admin
 */
public class SignoutSheetsDao {

	public List<SignoutSheets> getAllSignoutSheets() {
		List<SignoutSheets> signoutSheetss = new ArrayList<SignoutSheets>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM PhieuTra";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SignoutSheets signoutSheets = new SignoutSheets();
				signoutSheets.setMaphieutra(rs.getString("MaPhieuTra"));
				signoutSheets.setMadocgia(rs.getString("MaDocGia"));
				signoutSheets.setMatl(rs.getString("MaTaiLieu"));
				signoutSheets.setKieumuon(rs.getString("KieuMuon"));
				signoutSheets.setNgaytra(rs.getDate("NgayTra"));
				signoutSheets.setMangdung(rs.getString("MaNguoiDung"));

				signoutSheetss.add(signoutSheets);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return signoutSheetss;
	}

	public SignoutSheets getSignoutById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM PhieuTra WHERE MaPhieuTra = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				SignoutSheets signoutSheets = new SignoutSheets();
				signoutSheets.setMaphieutra(rs.getString("MaPhieuTra"));
				signoutSheets.setMadocgia(rs.getString("MaDocGia"));
				signoutSheets.setMatl(rs.getString("MaTaiLieu"));
				signoutSheets.setKieumuon(rs.getString("KieuMuon"));
				signoutSheets.setNgaytra(rs.getDate("NgayTra"));
				signoutSheets.setMangdung(rs.getString("MaNguoiDung"));

				return signoutSheets;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addSignoutSheets(SignoutSheets sheets) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO PhieuTra(MaPhieuTra,MaDocGia,MaTaiLieu,KieuMuon,NgayTra,MaNguoiDung) VALUES(?,?,?,?,?,?)";

		try {

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sheets.getMaphieutra());
			preparedStatement.setString(2, sheets.getMadocgia());
			preparedStatement.setString(3, sheets.getMatl());
			preparedStatement.setString(4, sheets.getKieumuon());
			preparedStatement.setDate(5, sheets.getNgaytra());
			preparedStatement.setString(6, sheets.getMangdung());

			preparedStatement.executeUpdate();

		} catch (Exception ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Lỗi");
		}

	}

	public void updateSignoutSheets(SignoutSheets sheets) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE PhieuTra SET MaDocGia = ?, MaTaiLieu = ?, KieuMuon = ?,NgayTra = ?,"
				+ "MaNguoiDung = ? WHERE MaPhieuTra = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, sheets.getMadocgia());
			preparedStatement.setString(2, sheets.getMatl());
			preparedStatement.setString(3, sheets.getKieumuon());
			preparedStatement.setDate(4, sheets.getNgaytra());
			preparedStatement.setString(5, sheets.getMangdung());
			preparedStatement.setString(6, sheets.getMaphieutra());

			preparedStatement.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void deleteSos(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from PhieuTra where MaPhieuTra = ?";

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			preparedStatement.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
}
