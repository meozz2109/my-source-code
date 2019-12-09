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
    /// Interaction logic for GoodReceiptD.xaml
    /// </summary>
    public partial class GoodReceiptD : Window
    {
        public GoodReceiptD()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaPhieuNhap = TxtBoxMaPhieuNhap.Text.ToString();
            DateTime valueNgayNhap = DateTime.Parse(TxtBoxNgayNhap.Text.ToString(), new CultureInfo("en-CA"));
            string valueThueVAT = TxtBoxThueVAT.Text.ToString();
            string valueMaNCC = TxtBoxMaNCC.Text.ToString();

            // check null input information or not valid input 
            if (valueMaPhieuNhap == "" || valueNgayNhap == null || valueThueVAT == "" || valueMaNCC == "")
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
                string insertCommand1 = "Delete from tblPhieuNhap Where MaPhieuNhap = @maphieunhap and NgayNhap = @ngaynhap and ThueVAT = @thuevat and MaNCC = @mancc";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@maphieunhap", SqlDbType.Char).Value = valueMaPhieuNhap;
                insertCm.Parameters.Add("@ngaynhap", SqlDbType.Date).Value = valueNgayNhap;
                insertCm.Parameters.Add("@thuevat", SqlDbType.Money).Value = valueThueVAT;
                insertCm.Parameters.Add("@mancc", SqlDbType.Char).Value = valueMaNCC;

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
            TxtBoxMaNCC.Focusable = false;
            TxtBoxMaPhieuNhap.Focusable = false;
            TxtBoxNgayNhap.Focusable = false;
            TxtBoxThueVAT.Focusable = false;
        }
    }
}
