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
public class User {
    
    private String username;
    private String hoten;
    private Date namsinh;
    private String gioitinh;
    private String email;
    private String dienthoai;
    private String mangdung;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Date getNamsinh() {
        return namsinh;
    }

    public void setNamsinh(Date namsinh) {
        this.namsinh = namsinh;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDienthoai() {
        return dienthoai;
    }

    public void setDienthoai(String dienthoai) {
        this.dienthoai = dienthoai;
    }

    public String getMangdung() {
        return mangdung;
    }

    public void setMangdung(String mangdung) {
        this.mangdung = mangdung;
    }
    
    
    
}
