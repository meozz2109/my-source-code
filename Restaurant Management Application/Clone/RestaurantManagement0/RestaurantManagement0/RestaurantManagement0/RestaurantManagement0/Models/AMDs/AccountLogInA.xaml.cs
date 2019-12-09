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
    /// Interaction logic for AccountLogInA.xaml
    /// </summary>
    public partial class AccountLogInA : Window
    {
        public AccountLogInA()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {
            string username = TxtBoxUserName.Text.ToString();
            string password = TxtBoxPassword.Text.ToString();
            string role = TxtBoxRole.Text.ToString();

            // check null input information or not valid input 
            if (username == "" || password == "" || role == "")
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
                string insertCommand1 = "Insert into tblLogInAccount (Username, Password, Role) Values (@userName, @password, @role)";
                SqlCommand insertCm = new SqlCommand(insertCommand1, sqlConn);

                // parametrized query
                insertCm.Parameters.Add("@userName", SqlDbType.VarChar).Value = TxtBoxUserName.Text.ToString();
                insertCm.Parameters.Add("@password", SqlDbType.VarChar).Value = TxtBoxPassword.Text.ToString();
                insertCm.Parameters.Add("@role", SqlDbType.NVarChar).Value = TxtBoxRole.Text.ToString();

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
            TxtBoxPassword.Focusable = false;
            TxtBoxUserName.Focusable = false;
            TxtBoxRole.Focusable = false;
        }
    }
}
