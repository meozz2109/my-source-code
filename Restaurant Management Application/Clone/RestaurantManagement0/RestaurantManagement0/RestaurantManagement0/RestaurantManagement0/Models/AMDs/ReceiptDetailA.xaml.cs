using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Data;
using System.Globalization;
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
    /// Interaction logic for ReceiptDetailA.xaml
    /// </summary>
    public partial class ReceiptDetailA : Window
    {
        public ReceiptDetailA()
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
            
            // check null input information or not valid input 
            if (valueMaHoaDon == "" || valueMaMon == "" || valueTenMon == "" || valueSoLuong == "" || valueDonGia == "" || valueSoLuongKhach == "" || valueKhuyenMai == "" || valueThanhTien == "" )
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
                string insertCommand1 = "Insert into tblCTHoaDon (MaHoaDon, MaMon, TenMon, SoLuong, DonGia, SlKhach, KhuyenMai, ThanhTien) Values (@mahoadon, @mamon, @tenmon, @soluong, @dongia, @soluongkhach, @khuyenmai, @thanhtien)";
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
