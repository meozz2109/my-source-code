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
import vnua.k62cnpm.xdptpm.libmanage.AllLibmanager.SignoutSheets;

/**
 *
 * @author Admin
 */
public class SignoutSheetsDao {

    public List<SignoutSheets> getAllSignoutSheets() {
        List<SignoutSheets> signoutSheetses = new ArrayList<SignoutSheets>();

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "SELECT * FROM PhieuMuon";

        PreparedStatement preparedStatement;
        try {
            preparedStatement = con.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                SignoutSheets signoutSheets = new SignoutSheets();
                signoutSheets.setMaphieumuon(rs.getString("MaPhieuMuon"));
                signoutSheets.setMadocgia(rs.getString("MaDocGia"));
                signoutSheets.setMatl(rs.getString("MaTaiLieu"));
                signoutSheets.setKieumuon(rs.getString("KieuMuon"));
                signoutSheets.setNgaymuon(rs.getString("NgayMuon"));
                signoutSheets.setHantra(rs.getString("HanTra"));
                signoutSheets.setMangdung(rs.getString("MaNguoiDung"));

                signoutSheetses.add(signoutSheets);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReaderDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return signoutSheetses;
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

    public void deleteSos(String ID) {

        Connection con = JDBCConnection.getJDBCConnection();

        String sql = "delete from PhieuMuon where MaPhieuMuon = ?";

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
