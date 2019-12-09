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
    /// Interaction logic for SupplierAMD.xaml
    /// </summary>
    public partial class SupplierAMD : Window
    {
        public SupplierAMD()
        {
            InitializeComponent();
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

        private void cboTypeI_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {

        }

        private void btnAdd_Click(object sender, RoutedEventArgs e)
        {
            var window = new SupplierA();
            window.Show();
        }

        private void btnEdit_Click(object sender, RoutedEventArgs e)
        {
            var window = new SupplierM();
            window.Show();
        }

        private void btnDel_Click(object sender, RoutedEventArgs e)
        {
            var window = new SupplierD();
            window.Show();
        }

        private void btnSkip_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {
            // connect to database
            Class.Functions.Connect();

            string sql = "select MaNCC, TenNCC, DiaChi, DienThoai, Email from tblNCC";
            SqlDataAdapter adapter = new SqlDataAdapter();
            adapter.SelectCommand = new SqlCommand();
            adapter.SelectCommand.Connection = Class.Functions.Con;
            adapter.SelectCommand.CommandText = sql;
            DataSet ds = new DataSet();
            adapter.Fill(ds);

            // add items
            lvSupplier.DataContext = ds.Tables[0].DefaultView;
        }
        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            // connect to database
            Class.Functions.Connect();

            string sql = "select MaNCC, TenNCC, DiaChi, DienThoai, Email from tblNCC";
            SqlDataAdapter adapter = new SqlDataAdapter();
            adapter.SelectCommand = new SqlCommand();
            adapter.SelectCommand.Connection = Class.Functions.Con;
            adapter.SelectCommand.CommandText = sql;
            DataSet ds = new DataSet();
            adapter.Fill(ds);

            // Another way
            SqlCommand cmd = new SqlCommand(sql, Class.Functions.Con);
            SqlDataReader reader = cmd.ExecuteReader();

            // Clear the ListView control
            lvSupplier.Items.Clear();

            // create new list
            List<String> ls = new List<string>();

            // Display items in the ListView control
            /*
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                DataRow dr = dt.Rows[i];

                // Only row that have not been deleted
                if( dr.RowState != DataRowState.Deleted)
                {

                    // Define the string list
                    ls.Add(dr["Username"].ToString());
                    ls.Add(dr["Password"].ToString());
                    ls.Add(dr["Chức vụ"].ToString());
                    
                }
            }
            */

            // Change the background color of list view

            BrushConverter bc = new BrushConverter();
            Brush br = (Brush)bc.ConvertFrom("#437577");
            br.Freeze();
            /*
            lvLogInAccount.Background = br;
            */
            foreach (ListViewItem item in lvSupplier.Items)
            {
                item.Background = br;
            }

            // add items
            lvSupplier.DataContext = ds.Tables[0].DefaultView;

            // disconnect to database
            Class.Functions.Disconnect();
        }
    }
}
