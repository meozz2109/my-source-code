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
    /// Interaction logic for FoodA.xaml
    /// </summary>
    public partial class FoodA : Window
    {
        public FoodA()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaMon = TxtBoxMaMon.Text.ToString();
            string valueTenNhomMon = TxtBoxTenNhomMon.Text.ToString();
            string valueTenMon = TxtBoxTenMon.Text.ToString();
            string valueTenTiengAnh = TxtBoxTenTiengAnh.Text.ToString();
            string valueDonGia = TxtBoxDonGia.Text.ToString();

            // check null input information or not valid input 
            if (valueMaMon == "" || valueTenMon == "" || valueTenNhomMon == "" || valueDonGia == "" || valueTenTiengAnh == "")
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
                string insertCommand1 = "Insert into tblMonAn (MaMon, TenNhomMon, TenMon, TenTiengAnh, DonGia) Values (@mamon, @tennhommon, @tenmon, @tentienganh, @dongia)";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mamon", SqlDbType.VarChar).Value = valueMaMon;
                insertCm.Parameters.Add("@tennhommon", SqlDbType.NVarChar).Value = valueTenNhomMon;
                insertCm.Parameters.Add("@tenmon", SqlDbType.NVarChar).Value = valueTenMon;
                insertCm.Parameters.Add("@tentienganh", SqlDbType.NVarChar).Value = valueTenTiengAnh;
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
            TxtBoxMaMon.Focusable = false;
            TxtBoxTenNhomMon.Focusable = false;
            TxtBoxTenMon.Focusable = false;
            TxtBoxTenTiengAnh.Focusable = false;
            TxtBoxDonGia.Focusable = false;
        }
    }
}
