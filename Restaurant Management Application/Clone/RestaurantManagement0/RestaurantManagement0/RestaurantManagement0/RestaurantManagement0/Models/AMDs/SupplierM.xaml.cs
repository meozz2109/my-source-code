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
    /// Interaction logic for SupplierM.xaml
    /// </summary>
    public partial class SupplierM : Window
    {
        public SupplierM()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string valueMaNCC = TxtBoxMaNCC.Text.ToString();
            string valueTenNCC = TxtBoxTenNCC.Text.ToString();
            string valueDiachi = TxtBoxDiaChi.Text.ToString();
            string valueDienThoai = TxtBoxDienThoai.Text.ToString();
            string valueEmail = TxtBoxEmail.Text.ToString();
            string valueMaNCCMoi = TxtBoxMaNCCNew.Text.ToString();
            string valueTenNCCMoi = TxtBoxTenNCCNew.Text.ToString();
            string valueDiachiMoi = TxtBoxDiaChiNew.Text.ToString();
            string valueDienThoaiMoi = TxtBoxDienThoaiNew.Text.ToString();
            string valueEmailMoi = TxtBoxEmailNew.Text.ToString();

            // check null input information or not valid input 
            if (valueMaNCC == "" || valueTenNCC == "" || valueDiachi == "" || valueDienThoai == "" || valueEmail == "" || valueMaNCCMoi == "" || valueTenNCCMoi == "" || valueDiachiMoi == "" || valueDienThoaiMoi == "" || valueEmailMoi == "")
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
                string insertCommand1 = "Update tblNCC Set MaNCC = @manccmoi, TenNCC = @tennccmoi, DiaChi = @diachimoi, DienThoai = @dienthoaimoi, Email = @emailmoi Where MaNCC = @mancc and TenNCC = @tenncc and DiaChi = @diachi and DienThoai = @dienthoai and Email = @email";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@mancc", SqlDbType.Char).Value = valueMaNCC;
                insertCm.Parameters.Add("@tenncc", SqlDbType.NVarChar).Value = valueTenNCC;
                insertCm.Parameters.Add("@diachi", SqlDbType.NVarChar).Value = valueDiachi;
                insertCm.Parameters.Add("@dienthoai", SqlDbType.NChar).Value = valueDienThoai;
                insertCm.Parameters.Add("@email", SqlDbType.NVarChar).Value = valueEmail;
                insertCm.Parameters.Add("@manccmoi", SqlDbType.Char).Value = valueMaNCCMoi;
                insertCm.Parameters.Add("@tennccmoi", SqlDbType.NVarChar).Value = valueTenNCCMoi;
                insertCm.Parameters.Add("@diachimoi", SqlDbType.NVarChar).Value = valueDiachiMoi;
                insertCm.Parameters.Add("@dienthoaimoi", SqlDbType.NChar).Value = valueDienThoaiMoi;
                insertCm.Parameters.Add("@emailmoi", SqlDbType.NVarChar).Value = valueEmailMoi;

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
            TxtBoxTenNCC.Focusable = false;
            TxtBoxDiaChi.Focusable = false;
            TxtBoxDienThoai.Focusable = false;
            TxtBoxEmail.Focusable = false;
        }
    }
}
