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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Location;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Publisher;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;

/**
 *
 * @author Admin
 */
public class LocationDao {
	public List<Location> getAllLocation() {
		List<Location> locations = new ArrayList<Location>();

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM ViTri";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = con.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Location location = new Location();
				location.setMavitri(rs.getString("MaViTri"));
				location.setTenvitri(rs.getString("TenViTri"));
				location.setMota(rs.getString("MoTa"));

				locations.add(location);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return locations;
	}

	public Location getLocationById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM ViTri WHERE MaViTri = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Location location = new Location();
				location.setMavitri(rs.getString("MaViTri"));
				location.setTenvitri(rs.getString("TenVitri"));
				location.setMota(rs.getString("MoTa"));

				return location;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}

	public void addLocation(Location locations) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "INSERT INTO ViTri(MaViTri,TenViTri,MoTa) VALUES(?,?,?)";

		try {

			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, locations.getMavitri());
			preparedStatement.setString(2, locations.getTenvitri());
			preparedStatement.setString(3, locations.getMota());

			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);

		} catch (Exception ex) {
			Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
			System.out.println("Lỗi");
		}

	}

	public void updateLocation(Location location) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "UPDATE ViTri SET TenViTri = ?, MoTa = ? WHERE MaViTri = ? ";
		try {
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, location.getTenvitri());
			preparedStatement.setString(2, location.getMota());
			preparedStatement.setString(3, location.getMavitri());
			

			int rs = preparedStatement.executeUpdate();
			System.out.println(rs);

		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void deleteLocation(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "delete from ViTri where MaViTri = ?";

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
