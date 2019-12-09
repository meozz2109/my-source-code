using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace RestaurantManagement0.Models.AMDs
{
    /// <summary>
    /// Interaction logic for ReceiptDetailM.xaml
    /// </summary>
    public partial class ReceiptDetailM : Window
    {
        public ReceiptDetailM()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {

            string valueMaHoaDon = TxtBoxMaHoaDon.Text.ToString();
            string valueMaMon = TxtBoxMaMon.Text.ToString();
            string valueTenMon = TxtBoxTenMon.Text.ToString();
            string valueSoLuong = TxtBoxSoLuong.Text.ToString();
            string valueDonGia = TxtBoxDonGia.Text.ToString();
            string valueSoLuongKhach = TxtBoxSoLuongKhach.Text.ToString();
            string valueKhuyenMai = TxtBoxKhuyenMai.Text.ToString();
            string valueThanhTien = TxtBoxThanhTien.Text.ToString();
            string valueMaHoaDonMoi = TxtBoxMaHoaDonNew.Text.ToString();
            string valueMaMonMoi = TxtBoxMaMonNew.Text.ToString();
            string valueTenMonMoi = TxtBoxTenMonNew.Text.ToString();
            string valueSoLuongMoi = TxtBoxSoLuongNew.Text.ToString();
            string valueDonGiaMoi = TxtBoxDonGiaNew.Text.ToString();
            string valueSoLuongKhachMoi = TxtBoxSoLuongKhachNew.Text.ToString();
            string valueKhuyenMaiMoi = TxtBoxKhuyenMaiNew.Text.ToString();
            string valueThanhTienMoi = TxtBoxThanhTienNew.Text.ToString();

            // check null input information or not valid input 
            if (valueMaHoaDon == "" || valueMaMon == "" || valueTenMon == "" || valueSoLuong == "" || valueDonGia == "" || valueSoLuongKhach == "" || valueKhuyenMai == "" || valueThanhTien == "" || valueMaHoaDonMoi == "" || valueMaMonMoi == "" || valueTenMonMoi == "" || valueSoLuongMoi == "" || valueDonGiaMoi == "" || valueSoLuongKhachMoi == "" || valueKhuyenMaiMoi == "" || valueThanhTienMoi == "")
            {
                MessageBox.Show("Bạn đã chưa nhập đầy đủ thông tin. Xin hãy bổ sung thông tin còn thiếu.");
            }
            else
            {
                // connect to database
                SqlConnection sqlConn = new SqlConnection();
                sqlConn.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagement.mdf\";Integrated Security=True";
                sqlConn.Open();

                // insert new row to database with new data
                string insertCommand1 = "Update tblCTHoaDon Set MaHoaDon = @mahoadonmoi, MaMon = @mamonmoi, TenMon = @tenmonmoi, SoLuong = @soluongmoi, DonGia = @dongiamoi, SlKhach = @soluongkhachmoi, KhuyenMai = @khuyenmaimoi, ThanhTien = @thanhtienmoi Where MaHoaDon = @mahoadon and MaMon = @mamon and TenMon = @tenmon and SoLuong = @soluong and DonGia = @dongia and SlKhach = @soluongkhach and KhuyenMai = @khuyenmai and ThanhTien = @thanhtien";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mahoadon", SqlDbType.Char).Value = TxtBoxMaHoaDon.Text.ToString();
                insertCm.Parameters.Add("@mamon", SqlDbType.Char).Value = TxtBoxMaMon.Text.ToString();
                insertCm.Parameters.Add("@tenmon", SqlDbType.NVarChar).Value = TxtBoxTenMon.Text.ToString();
                insertCm.Parameters.Add("@soluong", SqlDbType.Int).Value = valueSoLuong;
                insertCm.Parameters.Add("@dongia", SqlDbType.Money).Value = valueDonGia;
                insertCm.Parameters.Add("@soluongkhach", SqlDbType.Int).Value = valueSoLuongKhach;
                insertCm.Parameters.Add("@khuyenmai", SqlDbType.NChar).Value = valueKhuyenMai;
                insertCm.Parameters.Add("@thanhtien", SqlDbType.Money).Value = valueThanhTien;
                insertCm.Parameters.Add("@mahoadonmoi", SqlDbType.Char).Value = valueMaHoaDonMoi;
                insertCm.Parameters.Add("@mamonmoi", SqlDbType.Char).Value = valueMaMonMoi;
                insertCm.Parameters.Add("@tenmonmoi", SqlDbType.NVarChar).Value = valueTenMonMoi;
                insertCm.Parameters.Add("@soluongmoi", SqlDbType.Int).Value = valueSoLuongMoi;
                insertCm.Parameters.Add("@dongiamoi", SqlDbType.Money).Value = valueDonGiaMoi;
                insertCm.Parameters.Add("@soluongkhachmoi", SqlDbType.Int).Value = valueSoLuongKhachMoi;
                insertCm.Parameters.Add("@khuyenmaimoi", SqlDbType.NChar).Value = valueKhuyenMaiMoi;
                insertCm.Parameters.Add("@thanhtienmoi", SqlDbType.Money).Value = valueThanhTienMoi;

                // run command
                try
                {
                    insertCm.ExecuteNonQuery();
                    this.Close();
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Exception: " + ex.Message);
                    MessageBox.Show("Thông tin mà bạn vừa nhập vào không đúng hoặc sai định dạng ngày tháng năm. Xin mời nhập lại.");
                }
            }
        }

        private void BtnSkip_Click(object sender, RoutedEventArgs e)
        {

        }
    }
}
