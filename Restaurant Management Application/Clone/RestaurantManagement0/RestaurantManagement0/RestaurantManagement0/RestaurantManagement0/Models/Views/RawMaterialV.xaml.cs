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

namespace RestaurantManagement0.Models.Views
{
    /// <summary>
    /// Interaction logic for RawMaterialV.xaml
    /// </summary>
    public partial class RawMaterialV : Window
    {
        public RawMaterialV()
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

        }

        private void btnEdit_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnDel_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnSkip_Click(object sender, RoutedEventArgs e)
        {

        }

        private void btnSave_Click(object sender, RoutedEventArgs e)
        {

        }
        private void Window_Loaded(object sender, RoutedEventArgs e)
        {
            // connect to database
            Class.Functions.Connect();

            string sql = "select MaMatHang, TenMatHang, TenLoaiMH, DonViTinh, GiaNhap from tblMatHang";
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
            lvRawMaterial.Items.Clear();

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
            foreach (ListViewItem item in lvRawMaterial.Items)
            {
                item.Background = br;
            }

            // add items
            lvRawMaterial.DataContext = ds.Tables[0].DefaultView;

            // disconnect to database
            Class.Functions.Disconnect();
        }
    }
}
