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

namespace RestaurantManagement0
{
    /// <summary>
    /// Interaction logic for LogIn.xaml
    /// </summary>
    public partial class LogIn : Window
    {
        string cvName = "";
        public LogIn()
        {
            InitializeComponent();
        }
        private void CheckOnClickEnter(object sender, KeyEventArgs e)
        {
            if (e.Key == Key.Enter)
            {
                BtnLogIn_Click(sender, e);
            }
        }
        private void BtnLogIn_Click(object sender, RoutedEventArgs e)
        {
            // Method 1: check the log in information at database

            // create a connection
            try
            {

                SqlConnection sqlConn = new SqlConnection();
                sqlConn.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagement.mdf\";Integrated Security=True";
                sqlConn.Open();

                // check State of connection
                /*
                if (sqlConn.State == ConnectionState.Open)
                    MessageBox.Show("OK connected.");
                else
                    MessageBox.Show("Not connected.");
                */
                // choose SELECT command
                string selectCommand1 = "Select Role from tblLogInAccount where Username = @username and Password = @password";
                SqlCommand selectCm1 = new SqlCommand(selectCommand1, sqlConn);

                // avoid SQL Injection by using parametrized query
                selectCm1.Parameters.Add("@username", SqlDbType.VarChar).Value = TxtBoxUserName.Text.ToString();
                selectCm1.Parameters.Add("@password", SqlDbType.VarChar).Value = TxtBoxPass.Password.ToString();
                //selectCm1.Parameters.AddWithValue("@username", txtBoxUserName.Text.ToString());
                //selectCm1.Parameters.AddWithValue("@password", txtBoxPass.Password.ToString());

                // check whether input information exists or not by using .ExecuteNonQuery() method
                var nameCk = selectCm1.ExecuteScalar();// get the first cols of the row
                if (nameCk != null)
                {
                    string positionCk = nameCk.ToString();
                    cvName = positionCk;
                    MessageBox.Show("Chào mừng " + positionCk + " truy cập vào hệ thống.");
                }
                else
                {
                    if (TxtBoxUserName.Text.ToString() == "" && TxtBoxPass.Password.ToString() == "")
                    {
                        MessageBox.Show("Xin vui lòng điền tên tài khoản và mật khẩu.");
                    }
                    if (TxtBoxPass.Password.ToString() == "" && TxtBoxUserName.Text.ToString() != "")
                    {
                        MessageBox.Show("Xin vui lòng điền mật khẩu.");
                    }
                    if (TxtBoxPass.Password.ToString() != "" && TxtBoxUserName.Text.ToString() == "")
                        MessageBox.Show("Xin vui lòng điền tên tài khoản.");
                    if(TxtBoxUserName.Text.ToString() != "" && TxtBoxPass.Password.ToString() != "")
                    {
                        MessageBox.Show("Đăng nhập không thành công. Xin vui lòng kiểm tra lại thông tin tài khoản đăng nhập và thử lại lần nữa.");
                        
                        // check the status of CheckBox isChecked or not
                        if(CheckBoxRememberPass.IsChecked == false)
                        {
                            TxtBoxUserName.Text = "";
                            TxtBoxPass.Password = "";
                        }
                        else
                        {
                            TxtBoxUserName.Text = "";
                        }
                    }

                }

                // declare new DataAdapter
                SqlDataAdapter adapter = new SqlDataAdapter(selectCm1);

                // declare new DataTable and use adapter to fill the data into the DataTable
                DataSet dataSet1 = new DataSet();
                adapter.Fill(dataSet1);
                sqlConn.Close();

                // check "Chức vụ" and declare the output window
                DataTable dt1 = dataSet1.Tables[0];
                for (int i = 0; i < dataSet1.Tables[0].Rows.Count; i++)
                {
                    DataRow dr = dt1.Rows[i];
                    string cv = (string)dr["Role"];

                    // declare the output window
                    if (cv.Equals("Quản lý"))
                    {
                        var managerWindow = new Manager();
                        managerWindow.Show();
                        this.Close();
                    }
                    if (cv.Equals("Quản lý kho"))
                    {
                        var stockManagerWindow = new StockManager();
                        stockManagerWindow.Show();
                        this.Close();
                    }
                    if (cv.Equals("Thu ngân"))
                    {
                        var cashierWindow = new Cashier();
                        cashierWindow.Show();
                        this.Close();
                    }
                }

                //MessageBox.Show("OK continue running.");
                //if (dataSet1.)
                //{
                //    MessageBox.Show("It is greater than 0.");
                //}
                //else
                //    MessageBox.Show("Is just equal 0.");
            }
            catch (SqlException sqlEx)
            {
                Console.WriteLine("Exception: " + sqlEx.Message);
            }


            // Method 2: check directly those information

            /*
            if (txtBoxUserName.Text.ToString() != "" && txtBoxPass.Password.ToString()  != "")
            {
                if (txtBoxUserName.Text.ToString() == "manager2401" && txtBoxPass.Password.ToString() == "guide2109")
                {
                    var managerWindow = new Manager();
                    managerWindow.Show();
                    this.Close();
                }
                if (txtBoxUserName.Text.ToString() == "stockmanager5214" && txtBoxPass.Password.ToString() == "empty4204")
                {
                    var stockManagerWindow = new StockManager();
                    stockManagerWindow.Show();
                    this.Close();
                }
                if (txtBoxUserName.Text.ToString() == "cashier3201" && txtBoxPass.Password.ToString() == "fund1406")
                {
                    var cashierWindow = new Cashier();
                    cashierWindow.Show();
                    this.Close();
                }
                if (txtBoxUserName.Text.ToString() == "accountant2439" && txtBoxPass.Password.ToString() == "justuknow7421")
                {
                    var accountantWindow = new Accountant();
                    accountantWindow.Show();
                    this.Close();
                }
                else
                    MessageBox.Show("Your input username and password is wrong. Make sure it totally right and try again.");
            }
            else
            {
                if (txtBoxUserName.Text.ToString() == "" && txtBoxPass.Password.ToString() == "")
                {
                    MessageBox.Show("Pls type in username and password before clicking the button.");
                }
                if(txtBoxPass.Password.ToString() == "" && txtBoxUserName.Text.ToString() != "")
                {
                    MessageBox.Show("Pls type in password.");
                }
                if(txtBoxPass.Password.ToString() != "" && txtBoxUserName.Text.ToString() == "")
                    MessageBox.Show("Pls type in username.");
            }
            */
        }

        private void TxtBoxForgetPass_PreviewMouseDown(object sender, MouseButtonEventArgs e)
        {
            var forgetPassWindow = new ForgetPass();
            forgetPassWindow.Show();
            this.Close();
        }
    }
}
