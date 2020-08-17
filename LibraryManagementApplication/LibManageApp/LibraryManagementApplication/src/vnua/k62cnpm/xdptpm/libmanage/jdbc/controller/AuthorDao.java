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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;

/**
 *
 * @author Admin
 */
public class AuthorDao {

	public List<Author> getAllAuthor() {
		List<Author> author = new ArrayList<Author>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TacGia";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Author authorl = new Author();
				authorl.setMatg(rs.getString("MaTacGia"));
				authorl.setTentg(rs.getString("TenTacGia"));
				authorl.setGhichu(rs.getString("GhiChu"));

				author.add(authorl);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return author;
	}

	public Author getAuthorById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TacGia WHERE MaTacGia = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Author authorl = new Author();
				authorl.setMatg(rs.getString("MaTacGia"));
				authorl.setTentg(rs.getString("TenTacGia"));
				authorl.setGhichu(rs.getString("GhiChu"));

				return authorl;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addAuthor(Author author) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO TacGia(MaTacGia,TenTacGia,GhiChu) VALUES(?,?,?)";

		try {

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, author.getMatg());
			preparedStatement.setString(2, author.getTentg());
			preparedStatement.setString(3, author.getGhichu());

			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);

		} catch (Exception ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Lỗi");
		}

	}

	public void updateAuthor(Author author) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE TacGia SET TenTacGia = ?,GhiChu = ? WHERE MaTacGia = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, author.getTentg());
			preparedStatement.setString(2, author.getGhichu());
			preparedStatement.setString(3, author.getMatg());
//            

			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);

		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void deleteAuThor(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from TacGia where MaTacGia = ?";

		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public List<Author> getAuthByDoc() {
		List<Author> author = new ArrayList<Author>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "Select tg.* from TacGia tg \r\n" + 
				"inner join TaiLieu tl on tg.MaTacGia=tl.MaTacGia";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Author authorl = new Author();
				authorl.setMatg(rs.getString("MaTacGia"));
				authorl.setTentg(rs.getString("TenTacGia"));
				authorl.setGhichu(rs.getString("GhiChu"));

				author.add(authorl);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return author;
	}
}
