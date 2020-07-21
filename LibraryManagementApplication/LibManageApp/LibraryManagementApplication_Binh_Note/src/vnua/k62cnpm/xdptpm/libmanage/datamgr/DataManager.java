/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.datamgr;

import java.util.List;
import java.util.ArrayList;
import vnua.k62cnpm.xdptpm.libmanage.data.model.DocGia;
import vnua.k62cnpm.xdptpm.libmanage.data.model.TaiLieu;
import vnua.k62cnpm.xdptpm.libmanage.data.model.TaiLieuChiTiet;
import vnua.k62cnpm.xdptpm.libmanage.data.model.NhaCungCap;
import vnua.k62cnpm.xdptpm.libmanage.data.model.PhieuNhap;
import vnua.k62cnpm.xdptpm.libmanage.data.model.TaiKhoanDangNhap;
import vnua.k62cnpm.xdptpm.libmanage.data.model.NguoiDung;
import vnua.k62cnpm.xdptpm.libmanage.data.model.PhieuMuon;
import vnua.k62cnpm.xdptpm.libmanage.data.model.PhieuTra;
import vnua.k62cnpm.xdptpm.libmanage.data.model.NhaXuatBan;
import vnua.k62cnpm.xdptpm.libmanage.data.model.TacGia;
import vnua.k62cnpm.xdptpm.libmanage.data.model.ViTri;
import vnua.k62cnpm.xdptpm.libmanage.data.model.TheLoai;


/**
 *
 * @author Minh
 */
public class DataManager {
    private List<TaiLieu> listTaiLieu;
	private List<TaiLieuChiTiet> listTaiLieuChiTiet;
    private List<DocGia> listDocGia;
    private List<NhaCungCap> listNhaCungCap;
    private List<PhieuNhap> listPhieuNhap;
    private List<PhieuTra> listPhieuTra;
    private List<PhieuMuon> listPhieuMuon;
    private List<TaiKhoanDangNhap> listTaiKhoanDangNhap;
    private List<NguoiDung> listNguoiDung;
    private List<NhaXuatBan> listNhaXuatBan;
    private List<TacGia> listTacGia;
    private List<ViTri> listViTri;
    private List<TheLoai> listTheLoai;
    
    public DataManager() {
    	listTaiLieu = new ArrayList<TaiLieu>();
    	listTaiLieuChiTiet = new ArrayList<TaiLieuChiTiet>();
    	listDocGia = new ArrayList<DocGia>();
    	listNhaCungCap = new ArrayList<NhaCungCap>();
    	listPhieuNhap = new ArrayList<PhieuNhap>();
    	listPhieuTra = new ArrayList<PhieuTra>();
    	listPhieuMuon = new ArrayList<PhieuMuon>();
    	listTaiKhoanDangNhap = new ArrayList<TaiKhoanDangNhap>();
    	listNguoiDung = new ArrayList<NguoiDung>();
    	listNhaXuatBan = new ArrayList<NhaXuatBan>();
    	listTacGia = new ArrayList<TacGia>();
    	listViTri = new ArrayList<ViTri>();
    	listTheLoai = new ArrayList<TheLoai>();
    	
	}
    
    public List<TaiLieu> getListTaiLieu() {
		return listTaiLieu;
	}

	public List<TaiLieuChiTiet> getListTaiLieuChiTiet() {
		return listTaiLieuChiTiet;
	}

	public List<DocGia> getListDocGia() {
		return listDocGia;
	}

	public List<NhaCungCap> getListNhaCungCap() {
		return listNhaCungCap;
	}

	public List<PhieuNhap> getListPhieuNhap() {
		return listPhieuNhap;
	}

	public List<PhieuTra> getListPhieuTra() {
		return listPhieuTra;
	}

	public List<PhieuMuon> getListPhieuMuon() {
		return listPhieuMuon;
	}

	public List<TaiKhoanDangNhap> getListTaiKhoanDangNhap() {
		return listTaiKhoanDangNhap;
	}

	public List<NguoiDung> getListNguoiDung() {
		return listNguoiDung;
	}

	public List<NhaXuatBan> getListNhaXuatBan() {
		return listNhaXuatBan;
	}

	public List<TacGia> getListTacGia() {
		return listTacGia;
	}

	public List<ViTri> getListViTri() {
		return listViTri;
	}

	public List<TheLoai> getListTheLoai() {
		return listTheLoai;
	}
    
    public void themLogInAccount(String user, String pass, String role) {
    	if(!user.equals("") && !pass.equals("") && !role.equals("")) {
    		TaiKhoanDangNhap tk1 = new TaiKhoanDangNhap(user, pass, role);
    		listTaiKhoanDangNhap.add(tk1);
    	}
    }
    
}
