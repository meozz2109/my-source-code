using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Data.SqlClient;
using Microsoft.Reporting.WinForms;

namespace RestaurantManagement0
{
    public partial class BaoCao3 : Form
    {
        public BaoCao3()
        {
            InitializeComponent();
        }

        private void BaoCao3_Load(object sender, EventArgs e)
        {
            // Connect to database
            SqlConnection con = new SqlConnection();
            con.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\Clone\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagementNum0.mdf\";Integrated Security=True";
            con.Open();

            // Customize listbox
            string selectCm = "Select * from tblMatHang";
            SqlCommand selectCmd = new SqlCommand(selectCm, con);

            // declare new DataAdapter
            SqlDataAdapter adapter = new SqlDataAdapter(selectCmd);

            // declare new DataTable and use adapter to fill the data into the DataTable
            DataSet dataSet1 = new DataSet();
            adapter.Fill(dataSet1);
            

            // check "Chức vụ" and declare the output window
            DataTable dt1 = dataSet1.Tables[0];
            for(int i = 0; i < dt1.Rows.Count; i++) 
            {
                listBox1.Items.Add(dt1.Rows[i][2].ToString().Trim());
            }

        }

        private void button1_Click(object sender, EventArgs e)
        {
            
            // Connect to database
            SqlConnection con = new SqlConnection();
            con.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\Clone\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagementNum0.mdf\";Integrated Security=True";
            con.Open();
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "BaoCaoTK";
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Connection = con;

            cmd.Parameters.Add(new SqlParameter("@TenLoaiMH", listBox1.SelectedItem.ToString()));
            DataSet ds = new DataSet();
            SqlDataAdapter dap = new SqlDataAdapter(cmd);
            dap.Fill(ds);
            reportViewer3.ProcessingMode = ProcessingMode.Local;
            reportViewer3.LocalReport.ReportPath = "BaoCao3.rdlc";
            if (ds.Tables[0].Rows.Count > 0)
            {
                ReportDataSource rds = new ReportDataSource();
                rds.Name = "DataSet3";
                rds.Value = ds.Tables[0];
                //Gan len bao cao
                reportViewer3.LocalReport.DataSources.Clear();
                reportViewer3.LocalReport.DataSources.Add(rds);
                reportViewer3.RefreshReport();
            }
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

        private void reportViewer3_Load(object sender, EventArgs e)
        {

        }
    }
}
