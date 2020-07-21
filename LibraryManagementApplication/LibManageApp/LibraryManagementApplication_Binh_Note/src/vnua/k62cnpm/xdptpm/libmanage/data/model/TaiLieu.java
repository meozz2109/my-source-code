package vnua.k62cnpm.xdptpm.libmanage.data.model;

/**
 *
 * @author afsal
 */
public class TaiLieu {
    private String maTaiLieu;
    private String tenTheLoai;
    private String maTheLoai;
    private String maNXB;
    private String maTacGia;
    private String namXuatBan;
    private String noiDung;
    private int soTrang;
    private String giaBia;
    private String maViTri;
    private String ngayCapNhat;

    

    public TaiLieu(String maTaiLieu, String tenTheLoai, String maTheLoai, String maNXB, String maTacGia,
			String namXuatBan, String noiDung, int soTrang, String giaBia, String maViTri, String ngayCapNhat) {
		super();
		this.maTaiLieu = maTaiLieu;
		this.tenTheLoai = tenTheLoai;
		this.maTheLoai = maTheLoai;
		this.maNXB = maNXB;
		this.maTacGia = maTacGia;
		this.namXuatBan = namXuatBan;
		this.noiDung = noiDung;
		this.soTrang = soTrang;
		this.giaBia = giaBia;
		this.maViTri = maViTri;
		this.ngayCapNhat = ngayCapNhat;
	}



	public String getMaTaiLieu() {
		return maTaiLieu;
	}



	public String getTenTheLoai() {
		return tenTheLoai;
	}



	public String getMaTheLoai() {
		return maTheLoai;
	}



	public String getMaNXB() {
		return maNXB;
	}



	public String getMaTacGia() {
		return maTacGia;
	}



	public String getNamXuatBan() {
		return namXuatBan;
	}



	public String getNoiDung() {
		return noiDung;
	}



	public int getSoTrang() {
		return soTrang;
	}



	public String getGiaBia() {
		return giaBia;
	}



	public String getMaViTri() {
		return maViTri;
	}



	public String getNgayCapNhat() {
		return ngayCapNhat;
	}
    
    
}
