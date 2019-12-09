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
    /// Interaction logic for ReportM.xaml
    /// </summary>
    public partial class ReportM : Window
    {
        public ReportM()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaBaoCao = TxtBoxMaBaoCao.Text.ToString();
            DateTime valueTuNgay = DateTime.Parse(TxtBoxTuNgay.Text.ToString(), new CultureInfo("en-CA"));
            DateTime valueDenNgay = DateTime.Parse(TxtBoxDenNgay.Text.ToString(), new CultureInfo("en-CA"));
            string valueMaNhanVien = TxtBoxMaNhanVien.Text.ToString();
            string valueDoanhThu = TxtBoxDoanhThu.Text.ToString();
            string valueMaBaoCaoMoi = TxtBoxMaBaoCaoNew.Text.ToString();
            DateTime valueTuNgayMoi = DateTime.Parse(TxtBoxTuNgayNew.Text.ToString(), new CultureInfo("en-CA"));
            DateTime valueDenNgayMoi = DateTime.Parse(TxtBoxDenNgayNew.Text.ToString(), new CultureInfo("en-CA"));
            string valueMaNhanVienMoi = TxtBoxMaNhanVienNew.Text.ToString();
            string valueDoanhThuMoi = TxtBoxDoanhThuNew.Text.ToString();

            // check null input information or not valid input 
            if (valueMaBaoCao == "" || valueTuNgay == null || valueDenNgay == null || valueDoanhThu == "" || valueMaNhanVien == "" || valueMaBaoCaoMoi == "" || valueTuNgayMoi == null || valueDenNgayMoi == null || valueDoanhThuMoi == "" || valueMaNhanVienMoi == "")
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
                string insertCommand1 = "Update tblBaoCao Set MaBaoCao = @mabaocaomoi, MaNhanVien = @manhanvienmoi, DoanhThu = @doanhthumoi, TuNgay = @tungaymoi, DenNgay = @denngaymoi Where MaBaoCao = @mabaocao and MaNhanVien = @manhanvien and DoanhThu = @doanhthu and TuNgay = @tungay and DenNgay = @denngay";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mabaocao", SqlDbType.Char).Value = valueMaBaoCao;
                insertCm.Parameters.Add("@manhanvien", SqlDbType.Char).Value = valueMaNhanVien;
                insertCm.Parameters.Add("@doanhthu", SqlDbType.Money).Value = valueDoanhThu;
                insertCm.Parameters.Add("@tungay", SqlDbType.Date).Value = valueTuNgay;
                insertCm.Parameters.Add("@denngay", SqlDbType.Date).Value = valueDenNgay;
                insertCm.Parameters.Add("@mabaocaomoi", SqlDbType.Char).Value = valueMaBaoCaoMoi;
                insertCm.Parameters.Add("@manhanvienmoi", SqlDbType.Char).Value = valueMaNhanVienMoi;
                insertCm.Parameters.Add("@doanhthumoi", SqlDbType.Money).Value = valueDoanhThuMoi;
                insertCm.Parameters.Add("@tungaymoi", SqlDbType.Date).Value = valueTuNgayMoi;
                insertCm.Parameters.Add("@denngaymoi", SqlDbType.Date).Value = valueDenNgayMoi;

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
            TxtBoxMaBaoCao.Focusable = false;
            TxtBoxTuNgay.Focusable = false;
            TxtBoxDenNgay.Focusable = false;
            TxtBoxDoanhThu.Focusable = false;
        }
    }
}
