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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Supplier;

/**
 *
 * @author Admin
 */
public class SupplierDao {

	public List<Supplier> getAllSupplier() {
		List<Supplier> suppliers = new ArrayList<Supplier>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM NhaCungCap";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setMancc(rs.getString("MaNCC"));
				supplier.setTenncc(rs.getString("TenNCC"));
				supplier.setDiachi(rs.getString("DiaChi"));
				supplier.setSdt(rs.getString("DienThoai"));
				supplier.setEmail(rs.getString("Email"));

				suppliers.add(supplier);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return suppliers;
	}

	public Supplier getSupplierById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM NhaCungCap WHERE MaNCC = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Supplier supplier = new Supplier();
				supplier.setMancc(rs.getString("MaNCC"));
				supplier.setTenncc(rs.getString("TenNCC"));
				supplier.setDiachi(rs.getString("DiaChi"));
				supplier.setSdt(rs.getString("DienThoai"));
				supplier.setEmail(rs.getString("Email"));

				return supplier;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addSupplier(Supplier supplier) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO NhaCungCap(MaNCC,TenNCC,DiaChi,DienThoai,Email) VALUES(?,?,?,?,?)";

		try {

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, supplier.getMancc());
			preparedStatement.setString(2, supplier.getTenncc());
			preparedStatement.setString(3, supplier.getDiachi());
			preparedStatement.setString(4, supplier.getSdt());
			preparedStatement.setString(5, supplier.getEmail());

			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);

		} catch (Exception ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Lỗi");
		}

	}

	public void updateSupplier(Supplier supplier) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE NhaCungCap SET TenNCC = ?, DiaChi = ?, DienThoai = ?,Email = ? WHERE MaNCC = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, supplier.getTenncc());
			preparedStatement.setString(2, supplier.getDiachi());
			preparedStatement.setString(3, supplier.getSdt());
			preparedStatement.setString(4, supplier.getEmail());
			preparedStatement.setString(5, supplier.getMancc());
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

	public void deleteSupplier(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from NhaCungCap where MaNCC = ?";

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
