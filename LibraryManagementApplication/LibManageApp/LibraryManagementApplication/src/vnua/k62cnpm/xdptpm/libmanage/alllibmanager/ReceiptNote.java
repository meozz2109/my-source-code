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
public class ReceiptNote {
        private String maphieunhap;
        private String matl;
        private String mancc;
        private String mangdung;
        private Date ngaynhap;
        private String soluong;

    public String getMaphieunhap() {
        return maphieunhap;
    }

    public Date getNgaynhap() {
        return ngaynhap;
    }

    public void setNgaynhap(Date ngaynhap) {
        this.ngaynhap = ngaynhap;
    }

    public void setMaphieunhap(String maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public String getMatl() {
        return matl;
    }

    public void setMatl(String matl) {
        this.matl = matl;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getMangdung() {
        return mangdung;
    }

    public void setMangdung(String mangdung) {
        this.mangdung = mangdung;
    }

   
    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
       
        
    
}
