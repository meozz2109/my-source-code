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

namespace RestaurantManagement0
{
    /// <summary>
    /// Interaction logic for ForgetPass.xaml
    /// </summary>
    public partial class ForgetPass : Window
    {
        public ForgetPass()
        {
            InitializeComponent();
        }

        private void BtnResetPass_Click(object sender, RoutedEventArgs e)
        {
            string nameOfEmployee = NameTextBox.Text.ToString(),
                dateOfBirth = BirthDayTextBox.Text.ToString(),
                phoneNumber = PhoneNumberTextBox.Text.ToString(),
                role = RoleTextBox.Text.ToString(),
                username = UsernameTextBox.Text.ToString(),
                newPass = PasswordTextBox.Text.ToString(),
                newPassConfirm = PasswordAgain.Text.ToString();
            // Check Role
            if (role == "Quản lý" || role == "Thu ngân" || role == "Quản lý kho")
            {
                // connect to database
                SqlConnection sqlConn = new SqlConnection();
                sqlConn.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagement.mdf\";Integrated Security=True";
                sqlConn.Open();

                // choose SELECT command
                string selectCommand1 = "Select MaNV from tblNhanVien where TenNV = @name and NgaySinh = @dateofbirth and SoDT = @phonenumber and ChucVu = @role";
                SqlCommand selectCm1 = new SqlCommand(selectCommand1, sqlConn);

                // avoid SQL Injection by using parametrized query
                selectCm1.Parameters.Add("@name", SqlDbType.NVarChar).Value = nameOfEmployee;
                selectCm1.Parameters.Add("@dateofbirth", SqlDbType.Date).Value = dateOfBirth;
                selectCm1.Parameters.Add("@phonenumber", SqlDbType.Int).Value = phoneNumber;
                selectCm1.Parameters.Add("@role", SqlDbType.NVarChar).Value = role;

                // check whether input information exists or not by using .ExecuteNonQuery() method
                var rowFirstCk = selectCm1.ExecuteScalar();// get the first cols of the row
                if(rowFirstCk!= null)
                {
                    // check newPass and newPassConfirm which has the same value
                    if(newPass == newPassConfirm && username != null)
                    {
                        // update database with new Password
                        string updateCommand1 = "Update tblLogInAccount set Password = @password where Username = @username";
                        SqlCommand updateCm = new SqlCommand(updateCommand1, sqlConn);

                        // parametrized query
                        updateCm.Parameters.Add("@password", SqlDbType.VarChar).Value = newPass;
                        updateCm.Parameters.Add("@username", SqlDbType.VarChar).Value = username;

                        // run the command
                        try
                        {
                            updateCm.ExecuteNonQuery();
                            MessageBox.Show("Đã cập nhật mật khẩu mới thành công.");
                        } catch(Exception ex)
                        {
                            MessageBox.Show("Exception: " + ex.Message);
                        }
                    }
                    else
                    {
                        MessageBox.Show("Xin mời nhập lại mật khẩu. 2 mật khẩu bạn vừa nhập vào không khớp với nhau.");
                    }
                }
                else
                {

                }
            } else 
            {
                MessageBox.Show("Thông tin bạn nhập vào chưa đúng. Xin mời nhập lại.");
            }
            // Check exists
            // Check confirm true 
        }
        private void SearchIBox_KeyDown(object sender, KeyEventArgs e)
        {

        }

        private void SearchIBox_TextChanged(object sender, TextChangedEventArgs e)
        {

        }

        private void SearchIBox_GotFocus(object sender, RoutedEventArgs e)
        {

        }

        private void Image_PreviewMouseLeftButtonDown(object sender, MouseButtonEventArgs e)
        {
            var logInWindow = new LogIn();
            logInWindow.Show();
            this.Close();
        }
    }
}
