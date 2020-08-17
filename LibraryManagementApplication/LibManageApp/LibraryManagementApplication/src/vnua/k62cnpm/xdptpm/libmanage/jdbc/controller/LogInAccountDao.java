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
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.LogInAccount;
import vnua.k62cnpm.xdptpm.libmanage.alllibmanager.Reader;
import vnua.k62cnpm.xdptpm.libmanage.servicedao.DaoService;

/**
 *
 * @author Admin
 */
public class LogInAccountDao {
     public List<LogInAccount> getAllLogInAccount(){
        List<LogInAccount> logInAccounts = new ArrayList<LogInAccount>();
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "SELECT * FROM TaiKhoanDangNhap";
        
        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                LogInAccount logInAccount = new LogInAccount();
                logInAccount.setUsername(rs.getString("Username"));
                logInAccount.setPassword(rs.getString("Password"));
                logInAccount.setRole(rs.getString("Role"));

                logInAccounts.add(logInAccount);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return logInAccounts;
    }
    public void addLogInAccount(LogInAccount account){
        
        Connection con = JDBCConnection.getJDBCConnection();
        
        String sql = "INSERT INTO TaiKhoanDangNhap(Username,Password,Role) VALUES(?,?,?)";
        
        try {
            
            
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getRole());
            
            
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (Exception ex) {
           
            System.out.println("Lỗi");
        }
        
    
    }
    public LogInAccount getLogInAccountById(String ID) {

		Connection con = JDBCConnection.getJDBCConnection();

		String sql = "SELECT * FROM TaiKhoanDangNhap WHERE Username = ?";

		PreparedStatement preparedStatement;
		try {

			preparedStatement = con.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				LogInAccount logInAccount = new LogInAccount();
				logInAccount.setUsername(rs.getString("Username"));
				logInAccount.setPassword(rs.getString("Password"));
				logInAccount.setRole(rs.getString("Role"));

				return logInAccount;
			}
		} catch (SQLException ex) {
			Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
        public void updateLoginaccount(LogInAccount logInAccount){
        
          Connection con = JDBCConnection.getJDBCConnection();
          
          String sql = "UPDATE TaiKhoanDangNhap SET Password = ?, Role = ? WHERE Username = ? ";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, logInAccount.getPassword());
            preparedStatement.setString(2, logInAccount.getRole());
            preparedStatement.setString(3, logInAccount.getUsername());
           // preparedStatement.setString(4, readers.getEmail());
//            preparedStatement.setDate(5, readers.getNgaycap());
//            preparedStatement.setDate(6, readers.getNgayhethan());
//            preparedStatement.setString(7, readers.getNgdungcapnhap());
//            preparedStatement.setString(7, readers.getID());
            
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);
            
        } catch (SQLException ex) {
            Logger.getLogger(DaoService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
      public void deleteLogInAccount(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from TaiKhoanDangNhap where Username = ?";

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
