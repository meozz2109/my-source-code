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
    /// Interaction logic for RawMaterialsA.xaml
    /// </summary>
    public partial class RawMaterialsA : Window
    {
        public RawMaterialsA()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaMatHang = TxtBoxMaMatHang.Text.ToString();
            string valueTenMatHang = TxtBoxTenMatHang.Text.ToString();
            string valueTenLoaiMatHang = TxtBoxTenLoaiMatHang.Text.ToString();
            string valueDonViTinh = TxtBoxDonViTinh.Text.ToString();
            string valueDonGia = TxtBoxDonGia.Text.ToString();

            // check null input information or not valid input 
            if (valueMaMatHang == "" || valueTenMatHang == "" || valueTenLoaiMatHang == "" || valueDonViTinh == "" || valueDonGia == "")
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
                string insertCommand1 = "Insert into tblMatHang (MaMatHang, TenMatHang, TenLoaiMH, DonViTinh, GiaNhap) Values (@mamathang, @tenmathang, @tenloaimh, @donvitinh, @dongia)";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mamathang", SqlDbType.Char).Value = valueMaMatHang;
                insertCm.Parameters.Add("@tenmathang", SqlDbType.NVarChar).Value = valueTenMatHang;
                insertCm.Parameters.Add("@tenloaimh", SqlDbType.NVarChar).Value = valueTenLoaiMatHang;
                insertCm.Parameters.Add("@donvitinh", SqlDbType.NVarChar).Value = valueDonViTinh;
                insertCm.Parameters.Add("@dongia", SqlDbType.Money).Value = valueDonGia;

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
            TxtBoxTenLoaiMatHang.Focusable = false;
            TxtBoxDonViTinh.Focusable = false;
            TxtBoxDonGia.Focusable = false;
        }
    }
}
