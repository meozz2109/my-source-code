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
    /// Interaction logic for ReceiptM.xaml
    /// </summary>
    public partial class ReceiptM : Window
    {
        public ReceiptM()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaHoaDon = TxtBoxMaHoaDon.Text.ToString();
            DateTime valueNgayLap = DateTime.Parse(TxtBoxNgayLap.Text.ToString(), new CultureInfo("en-CA"));
            string valueMaNhanVien = TxtBoxMaNhanVien.Text.ToString();
            string valueThueVAT = TxtBoxThueVAT.Text.ToString();
            string valueMaBan = TxtBoxMaBan.Text.ToString();
            string valueMaHoaDonMoi = TxtBoxMaHoaDonNew.Text.ToString();
            DateTime valueNgayLapMoi = DateTime.Parse(TxtBoxNgayLapNew.Text.ToString(), new CultureInfo("en-CA"));
            string valueMaNhanVienMoi = TxtBoxMaNhanVienNew.Text.ToString();
            string valueThueVATMoi = TxtBoxThueVATNew.Text.ToString();
            string valueMaBanMoi = TxtBoxMaBanNew.Text.ToString();

            // check null input information or not valid input 
            if (valueMaHoaDon == "" || valueNgayLap == null || valueThueVAT == "" || valueMaNhanVien == "" || valueMaBan == "" || valueMaHoaDonMoi == "" || valueNgayLapMoi == null || valueThueVATMoi == "" || valueMaNhanVienMoi == "" || valueMaBanMoi == "")
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
                string insertCommand1 = "Update tblHoaDon Set MaHoaDon = @mahoadonmoi, MaBan = @mabanmoi, MaNhanVien = @manhanvienmoi, ThueVAT = @thuevatmoi, NgayLap = @ngaylapmoi Where MaHoaDon = @mahoadon and MaBan = @maban and MaNhanVien = @manhanvien and ThueVAT = @thuevat and NgayLap = @ngaylap";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mahoadon", SqlDbType.Char).Value = valueMaHoaDon;
                insertCm.Parameters.Add("@ngaylap", SqlDbType.Date).Value = valueNgayLap;
                insertCm.Parameters.Add("@thuevat", SqlDbType.Money).Value = valueThueVAT;
                insertCm.Parameters.Add("@maban", SqlDbType.Char).Value = valueMaBan;
                insertCm.Parameters.Add("@manhanvien", SqlDbType.Char).Value = valueMaNhanVien;
                insertCm.Parameters.Add("@mahoadonmoi", SqlDbType.Char).Value = valueMaHoaDonMoi;
                insertCm.Parameters.Add("@ngaylapmoi", SqlDbType.Date).Value = valueNgayLapMoi;
                insertCm.Parameters.Add("@thuevatmoi", SqlDbType.Money).Value = valueThueVATMoi;
                insertCm.Parameters.Add("@mabanmoi", SqlDbType.Char).Value = valueMaBanMoi;
                insertCm.Parameters.Add("@manhanvienmoi", SqlDbType.Char).Value = valueMaNhanVienMoi;

                // run command
                try
                {
                    insertCm.ExecuteNonQuery();
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
            TxtBoxMaNhanVien.Focusable = false;
            TxtBoxMaBan.Focusable = false;
            TxtBoxNgayLap.Focusable = false;
            TxtBoxThueVAT.Focusable = false;
            TxtBoxMaHoaDon.Focusable = false;
        }
    }
}
