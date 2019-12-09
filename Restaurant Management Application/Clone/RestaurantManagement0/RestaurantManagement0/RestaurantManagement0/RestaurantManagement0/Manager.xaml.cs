using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace RestaurantManagement0
{
    /// <summary>
    /// Interaction logic for Manager.xaml
    /// </summary>
    public partial class Manager : Window
    {
        public Manager()
        {
            InitializeComponent();
        }

        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
        }

        private void DemoItemsListBox_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
            var dependencyObject = Mouse.Captured as DependencyObject;
            while (dependencyObject != null)
            {
                dependencyObject = VisualTreeHelper.GetParent(dependencyObject);
            }
            MenuToggleButton.IsChecked = false;

        }

        private void lbiFoodList_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
        }

        private void lbiTableSize_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {
        }

        private void btnStartWorking_Click(object sender, RoutedEventArgs e)
        {
            if (App.Current.Properties["AdLogin"] != null)
            {
                return;
            }

            if (App.Current.Properties["CurrentEmpWorking"] != null)
            {
                MessageBox.Show("It's have some employee on working! Please wait!");
                return;
            }

        }

        private void btnEndWorking_Click(object sender, RoutedEventArgs e)
        {
            //check admin
            if (App.Current.Properties["AdLogin"] != null)
            {
                App.Current.Properties["AdLogin"] = null;

                if (App.Current.Properties["CurrentEmpWorking"] != null)
                {
                }
                else if (App.Current.Properties["CurrentEmpWorking"] == null)
                {
                }

                return;
            }

            //check employee
            if (App.Current.Properties["CurrentEmpWorking"] == null)
            {
            }
            else if (App.Current.Properties["CurrentEmpWorking"] != null)
            {
                App.Current.Properties["CurrentEmpWorking"] = null;

            }

        }

        private void btnOtherEmp_Click(object sender, RoutedEventArgs e)
        {
            if (App.Current.Properties["AdLogin"] != null)
            {
                return;
            }

        }

        private void btnEmpDetail_Click(object sender, RoutedEventArgs e)
        {
            if (App.Current.Properties["AdLogin"] != null)
            {
                return;
            }

        }

        private void bntLogout_Click(object sender, RoutedEventArgs e)
        {
            if (App.Current.Properties["AdLogin"] != null)
            {
                return;
            }


            if (App.Current.Properties["CurrentEmpWorking"] == null)
            {

            }
        }
        private void lbiChangeTheme_PreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {

        }

        private void LbiEODReport_OnPreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {

        }

        private void LbiFireDessert_OnPreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {

        }

        private void LbiFireStater_OnPreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {

        }

        private void LbiFireMain_OnPreviewMouseLeftButtonUp(object sender, MouseButtonEventArgs e)
        {

        }

        private void BtnAccessLIA_Click(object sender, RoutedEventArgs e)
        {
            var lIAWindow = new Models.AMDs.AccountLogInAMD();
            lIAWindow.Show();
        }

        private void BtnAccessEmployee_Click(object sender, RoutedEventArgs e)
        {
            var employeeWindow = new Models.AMDs.EmployeeAMD();
            employeeWindow.Show();
        }

        private void BtnAccessSupplier_Click(object sender, RoutedEventArgs e)
        {
            var supplierWindow = new Models.AMDs.SupplierAMD();
            supplierWindow.Show();
        }

        private void BtnAccessMaterial_Click(object sender, RoutedEventArgs e)
        {
            var materialWindow = new Models.AMDs.RawMaterialAMD();
            materialWindow.Show();
        }

        private void BtnAccessFood_Click(object sender, RoutedEventArgs e)
        {
            var foodWindow = new Models.AMDs.FoodAMD();
            foodWindow.Show();
        }

        private void BtnAccessReport_Click(object sender, RoutedEventArgs e)
        {
            var window = new Models.AMDs.Report();
            window.Show();
        }

        private void BtnAccessReceipt_Click(object sender, RoutedEventArgs e)
        {
            var receiptWindow = new Models.Views.ReceiptV();
            receiptWindow.Show();
        }

        private void BtnAccessInventoryReport_Click(object sender, RoutedEventArgs e)
        {
            var inRepWindow = new Models.Views.InventoryReportV();
            inRepWindow.Show();
        }

        private void BtnAccessReceiptDetail_Click(object sender, RoutedEventArgs e)
        {
            var reDetailWindow = new Models.Views.ReceiptDetailV();
            reDetailWindow.Show();
        }

        private void BtnAccessGoodReceiptDetail_Click(object sender, RoutedEventArgs e)
        {
            var goodReDetailWindow = new Models.Views.GoodReceiptDetailV();
            goodReDetailWindow.Show();
        }

        private void BtnLogOutAccept_Click(object sender, RoutedEventArgs e)
        {
            var lIWindow = new LogIn();
            lIWindow.Show();
            this.Close();
        }

        private void BtnLogout_Click(object sender, RoutedEventArgs e)
        {
            var lIWindow = new LogIn();
            lIWindow.Show();
            this.Close();
        }

        private void BtnAccessGoodReceipt_Click(object sender, RoutedEventArgs e)
        {
            var goodReceiptWindow = new Models.Views.GoodReceiptV();
            goodReceiptWindow.Show();
        }
    }
}
