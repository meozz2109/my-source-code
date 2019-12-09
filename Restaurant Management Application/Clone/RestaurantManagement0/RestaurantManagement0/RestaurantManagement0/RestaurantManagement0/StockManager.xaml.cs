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
    /// Interaction logic for StockManager.xaml
    /// </summary>
    public partial class StockManager : Window
    {
        public StockManager()
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

        private void BtnLogout_Click(object sender, RoutedEventArgs e)
        {
            var lIWindow = new LogIn();
            lIWindow.Show();
            this.Close();
        }

        private void BtnAccessSupplier_Click(object sender, RoutedEventArgs e)
        {
            var window = new Models.Views.SupplierV();
            window.Show();
        }

        private void BtnAccessRawMaterial_Click(object sender, RoutedEventArgs e)
        {
            var window = new Models.Views.RawMaterialV();
            window.Show();
        }

        private void BtnAccessGoodReceipt_Click(object sender, RoutedEventArgs e)
        {
            var window = new Models.AMDs.GoodReceiptAMD();
            window.Show();
        }

        private void BtnAccessGoodReceiptDetail_Click(object sender, RoutedEventArgs e)
        {
            var window = new Models.AMDs.GoodReceiptDetailAMD();
            window.Show();
        }

        private void BtnAccessInventoryReport_Click(object sender, RoutedEventArgs e)
        {
            var window = new Models.AMDs.InventoryReportAMD();
            window.Show();
        }

        private void BtnWriteReportHN_Click(object sender, RoutedEventArgs e)
        {
            Report baoCao1 = new Report();
            baoCao1.Show();
        }

        private void BtnWriteReportTK_Click(object sender, RoutedEventArgs e)
        {
            BaoCao3 baoCao3 = new BaoCao3();
            baoCao3.Show();
        }
    }
}
