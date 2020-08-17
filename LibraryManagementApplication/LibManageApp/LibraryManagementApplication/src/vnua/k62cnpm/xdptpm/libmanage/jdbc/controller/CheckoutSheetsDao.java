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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Author;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.CheckoutSheets;

/**
 *
 * @author Admin
 */
public class CheckoutSheetsDao {


	public List<CheckoutSheets> getAllCheckoutSheets() {
        List<CheckoutSheets> checkoutSheets = new ArrayList<CheckoutSheets>();

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM PhieuMuon";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                CheckoutSheets checkoutSheets2 = new CheckoutSheets();
                checkoutSheets2.setMaphieumuon(rs.getString("MaPhieuMuon"));
                checkoutSheets2.setMadocgia(rs.getString("MaDocGia"));
                checkoutSheets2.setMatl(rs.getString("MaTaiLieu"));
                checkoutSheets2.setKieumuon(rs.getString("KieuMuon"));
                checkoutSheets2.setNgaymuon(rs.getDate("NgayMuon"));
                checkoutSheets2.setHantra(rs.getDate("HanTra"));
                checkoutSheets2.setMangdung(rs.getString("MaNguoiDung"));

                checkoutSheets.add(checkoutSheets2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return checkoutSheets;
    }

	public CheckoutSheets getCheckoutById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM PhieuMuon WHERE MaPhieuMuon = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				CheckoutSheets checkoutSheets = new CheckoutSheets();
				checkoutSheets.setMaphieumuon(rs.getString("MaPhieuMuon"));
				checkoutSheets.setMadocgia(rs.getString("MaDocGia"));
				checkoutSheets.setMatl(rs.getString("MaTaiLieu"));
				checkoutSheets.setKieumuon(rs.getString("KieuMuon"));
				checkoutSheets.setNgaymuon(rs.getDate("NgayMuon"));
				checkoutSheets.setHantra(rs.getDate("HanTra"));
				checkoutSheets.setMangdung(rs.getString("MaNguoiDung"));

				return checkoutSheets;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
    public void addCheckoutSheets(CheckoutSheets checkoutSheets) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "INSERT INTO PhieuMuon(MaPhieuMuon,MaDocGia,MaTaiLieu,KieuMuon,NgayMuon,HanTra,"
                + "MaNguoiDung) VALUES(?,?,?,?,?,?,?)";

        try {
        	System.out.println("OK 1");
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            System.out.println("OK 2");
            preparedStatement.setString(1, checkoutSheets.getMaphieumuon());
            System.out.println("OK 4 " + checkoutSheets.getMadocgia());
            preparedStatement.setString(2, checkoutSheets.getMadocgia());
            preparedStatement.setString(3, checkoutSheets.getMatl());
            preparedStatement.setString(4, checkoutSheets.getKieumuon());
            preparedStatement.setDate(5, checkoutSheets.getNgaymuon());
            preparedStatement.setDate(6, checkoutSheets.getHantra());
            System.out.println("OK 3");
            preparedStatement.setString(7, checkoutSheets.getMangdung());
            

            int rs = preparedStatement.executeUpdate();
            System.out.println(rs+" res");
        } catch (Exception ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Err");
        }

    }

    public void updateCheckoutSheets(CheckoutSheets checkoutSheets) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "UPDATE PhieuMuon SET MaDocGia = ?, MaTaiLieu = ?, KieuMuon = ?,NgayMuon = ?,"
                + "HanTra = ?,MaNguoiDung = ? WHERE MaPhieuMuon = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, checkoutSheets.getMadocgia());
            preparedStatement.setString(2, checkoutSheets.getMatl());
            preparedStatement.setString(3, checkoutSheets.getKieumuon());
            preparedStatement.setDate(4, checkoutSheets.getNgaymuon());
            preparedStatement.setDate(5, checkoutSheets.getHantra());
            preparedStatement.setString(6, checkoutSheets.getMangdung());
            preparedStatement.setString(7, checkoutSheets.getMaphieumuon());

            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteCos(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from PhieuMuon where MaPhieuMuon = ?";

        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, ID);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
