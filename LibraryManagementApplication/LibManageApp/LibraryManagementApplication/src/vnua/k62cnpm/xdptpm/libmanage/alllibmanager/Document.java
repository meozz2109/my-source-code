/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vnua.k62cnpm.xdptpm.libmanage.alllibmanager;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Document {
        private String matl;
        private String tentl;
        private String matheloai;
        private String manxb;
        private String matg;
        private String namxb;
        private String noidung;
        private String sotrang;
        private String giabia;
        private String mavitri;
        private Date ngcapnhat;
		private int soLanDuocMuon;

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getTentl() {
        return tentl;
    }

    public void setTentl(String tentl) {
        this.tentl = tentl;
    }

    public String getMatheloai() {
        return matheloai;
    }

    public void setMatheloai(String matheloai) {
        this.matheloai = matheloai;
    }

    public String getManxb() {
        return manxb;
    }

    public void setManxb(String manxb) {
        this.manxb = manxb;
    }

    public String getMatg() {
        return matg;
    }

    public void setMatg(String matg) {
        this.matg = matg;
    }

    

	public String getNamxb() {
		return namxb;
	}

	public void setNamxb(String namxb) {
		this.namxb = namxb;
	}

	public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getSotrang() {
        return sotrang;
    }

    public void setSotrang(String sotrang) {
        this.sotrang = sotrang;
    }

    public String getGiabia() {
        return giabia;
    }

    public void setGiabia(String giabia) {
        this.giabia = giabia;
    }

    public String getMavitri() {
        return mavitri;
    }

    public void setMavitri(String mavitri) {
        this.mavitri = mavitri;
    }

    public Date getNgcapnhat() {
        return ngcapnhat;
    }

    public void setNgcapnhat(Date ngcapnhat) {
        this.ngcapnhat = ngcapnhat;
    }
    
	public void setSoLanDuocMuon(int soLanDuocMuon) {
		this.soLanDuocMuon = soLanDuocMuon;
	}

	public int getSoLanDuocMuon() {
		return soLanDuocMuon;
	}
       
}
