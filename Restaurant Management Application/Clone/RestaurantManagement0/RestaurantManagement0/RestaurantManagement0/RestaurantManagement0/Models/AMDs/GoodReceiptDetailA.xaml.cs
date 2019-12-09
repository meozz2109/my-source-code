using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Data;
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
    /// Interaction logic for GoodReceiptDetailA.xaml
    /// </summary>
    public partial class GoodReceiptDetailA : Window
    {
        public GoodReceiptDetailA()
        {
            InitializeComponent();
        }


        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {

            string valueMaPhieuNhap = TxtBoxMaPhieuNhap.Text.ToString();
            string valueMaMatHang = TxtBoxMaMatHang.Text.ToString();
            string valueSoLuong = TxtBoxSoLuong.Text.ToString();
            string valueDonViTinh = TxtBoxDonViTinh.Text.ToString();
            string valueDonGia = TxtBoxDonGia.Text.ToString();
            string valueThanhTien = TxtBoxThanhTien.Text.ToString();

            // check null input information or not valid input 
            if (valueMaPhieuNhap == "" || valueMaMatHang == "" || valueSoLuong == "" || valueDonViTinh == "" || valueDonGia == "" || valueThanhTien == "")
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
                string updateCommand1 = "Insert into tblCTPhieuNhap (MaPNhap, MaMatHang, SoLuong, DonViTinh, DonGia, ThanhTien) Values (@maphieunhap, @mamathang, @soluong, @donvitinh, @dongia, @thanhtien)";
                SqlCommand updateCm = new SqlCommand(updateCommand1, sqlConn);

                // parametrized query
                updateCm.Parameters.Add("@maphieunhap", SqlDbType.Char).Value = valueMaPhieuNhap;
                updateCm.Parameters.Add("@mamathang", SqlDbType.Char).Value = valueMaMatHang;
                updateCm.Parameters.Add("@soluong", SqlDbType.Int).Value = valueSoLuong;
                updateCm.Parameters.Add("@donvitinh", SqlDbType.NVarChar).Value = valueDonViTinh;
                updateCm.Parameters.Add("@dongia", SqlDbType.Money).Value = valueDonGia;
                updateCm.Parameters.Add("@thanhtien", SqlDbType.Money).Value = valueThanhTien;

                // run command
                try
                {
                    updateCm.ExecuteNonQuery();
                    this.Close();
                }
                catch (Exception ex)
                {
                    Console.WriteLine("Exception: " + ex.Message);
                    MessageBox.Show("Thông tin mà bạn vừa nhập vào không đúng. Xin mời nhập lại.");
                }
            }
        }

        private void BtnSkip_Click(object sender, RoutedEventArgs e)
        {
            
        }
    }
}
