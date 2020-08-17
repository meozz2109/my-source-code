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
public class DetaileDoc {
    private String mavachsach;
    private String matl;
    private Date ngaycapnhat;
    private String mangdung;
    private String tinhtrang;
    private String danhdauxuly;

    public String getMavachsach() {
        return mavachsach;
    }

    public void setMavachsach(String mavachsach) {
        this.mavachsach = mavachsach;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }


    public Date getNgaycapnhat() {
		return ngaycapnhat;
	}

	public void setNgaycapnhat(Date ngaycapnhat) {
		this.ngaycapnhat = ngaycapnhat;
	}

	public String getMangdung() {
        return mangdung;
    }

    public void setMangdung(String mangdung) {
        this.mangdung = mangdung;
    }

    public String getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(String tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    public String getDanhdauxuly() {
        return danhdauxuly;
    }

    public void setDanhdauxuly(String danhdauxuly) {
        this.danhdauxuly = danhdauxuly;
    }
    
    
    
}
