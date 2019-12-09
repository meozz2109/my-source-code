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
    /// Interaction logic for InventoryReportA.xaml
    /// </summary>
    public partial class InventoryReportA : Window
    {
        public InventoryReportA()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaMatHang = TxtBoxMaMatHang.Text.ToString();
            string valueTenMatHang = TxtBoxTenMatHang.Text.ToString();
            string valueSoLuongTonKho = TxtBoxSoLuongTonKho.Text.ToString();
            string valueDonViTinh = TxtBoxDonViTinh.Text.ToString();

            // check null input information or not valid input 
            if (valueMaMatHang == "" || valueTenMatHang == "" || valueSoLuongTonKho == "" || valueDonViTinh == "")
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
                string insertCommand1 = "Insert into tblTonKho (MaMatHang, TenMatHang, SLTonKho, DonViTinh) Values (@mamathang, @tenmathang, @soluongtonkho, @donvitinh)";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mamathang", SqlDbType.Char).Value = valueMaMatHang;
                insertCm.Parameters.Add("@tenmathang", SqlDbType.NVarChar).Value = valueTenMatHang;
                insertCm.Parameters.Add("@soluongtonkho", SqlDbType.Int).Value = valueSoLuongTonKho;
                insertCm.Parameters.Add("@donvitinh", SqlDbType.NVarChar).Value = valueDonViTinh;

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
            TxtBoxMaMatHang.Focusable = false;
            TxtBoxTenMatHang.Focusable = false;
            TxtBoxSoLuongTonKho.Focusable = false;
            TxtBoxDonViTinh.Focusable = false;
        }
    }
}
