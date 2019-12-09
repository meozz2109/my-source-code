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
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.Configuration;

namespace RestaurantManagement0
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void BtnLogInAccept_Click(object sender, RoutedEventArgs e)
        {
            
            var logInWindow = new LogIn();
            logInWindow.Show();
            this.Close();
            
            /*
            var testWindow = new Models.AMDs.AccountLogInAMD();
            testWindow.Show();
            this.Close();
            */
        }

        private void BtnLogOutAccept_Click(object sender, RoutedEventArgs e)
        {
            this.Close();
        }
    }
}
