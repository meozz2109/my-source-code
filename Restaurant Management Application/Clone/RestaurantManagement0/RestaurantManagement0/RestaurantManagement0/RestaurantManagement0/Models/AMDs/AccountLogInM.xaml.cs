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
    /// Interaction logic for AccountLogInM.xaml
    /// </summary>
    public partial class AccountLogInM : Window
    {
        public AccountLogInM()
        {
            InitializeComponent();
        }

        private void BtnSave_Click(object sender, RoutedEventArgs e)
        {

            string username = TxtBoxUserName.Text.ToString();
            string password = TxtBoxPassword.Text.ToString();
            string role = TxtBoxRole.Text.ToString();
            string newUsername = TxtBoxUserNameNew.Text.ToString();
            string newPassword = TxtBoxPasswordNew.Text.ToString();
            string newRole = TxtBoxRoleNew.Text.ToString();

            // check null input information or not valid input 
            if (username == "" || password == "" || role == "" || newUsername == "" || newPassword == "" || newRole == "")
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
                string updateCommand1 = "Update tblLogInAccount Set Username = @newUsername, Password = @newPassword, Role = @newRole where Username = @userName and Password = @password and Role =@role";
                SqlCommand updateCm = new SqlCommand(updateCommand1, sqlConn);

                // parametrized query
                updateCm.Parameters.Add("@userName", SqlDbType.VarChar).Value = TxtBoxUserName.Text.ToString();
                updateCm.Parameters.Add("@password", SqlDbType.VarChar).Value = TxtBoxPassword.Text.ToString();
                updateCm.Parameters.Add("@role", SqlDbType.NVarChar).Value = TxtBoxRole.Text.ToString();
                updateCm.Parameters.Add("@newUsername", SqlDbType.VarChar).Value = TxtBoxUserNameNew.Text.ToString();
                updateCm.Parameters.Add("@newPassword", SqlDbType.VarChar).Value = TxtBoxPasswordNew.Text.ToString();
                updateCm.Parameters.Add("@newRole", SqlDbType.NVarChar).Value = TxtBoxRoleNew.Text.ToString();
                
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
            TxtBoxUserName.Focusable = false;
            TxtBoxPassword.Focusable = false;
            TxtBoxRole.Focusable = false;
            TxtBoxUserNameNew.Focusable = false;
            TxtBoxPasswordNew.Focusable = false;
            TxtBoxRoleNew.Focusable = false;
        }
    }
}
