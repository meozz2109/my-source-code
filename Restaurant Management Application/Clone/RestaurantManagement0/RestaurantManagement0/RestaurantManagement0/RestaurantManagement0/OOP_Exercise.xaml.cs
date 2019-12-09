using System;
using System.Collections.Generic;
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
    /// Interaction logic for OOP_Exercise.xaml
    /// </summary>
    public partial class OOP_Exercise : Window
    {
        public OOP_Exercise()
        {
            InitializeComponent();
        }

        private void BtnLogIn_Click(object sender, RoutedEventArgs e)
        {
            if(TxtBoxUserName.Text=="user123" && TxtBoxPass.Password == "pass123")
            {

            }
        }
    }
}
