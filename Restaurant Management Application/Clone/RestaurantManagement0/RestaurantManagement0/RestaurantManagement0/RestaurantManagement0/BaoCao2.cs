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
    public partial class BaoCao2 : Form
    {
        public BaoCao2()
        {
            InitializeComponent();
        }

        private void BaoCao2_Load(object sender, EventArgs e)
        {
          
        }

        private void button1_Click(object sender, EventArgs e)
        {
            SqlConnection con = new SqlConnection();
            con.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\Clone\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagementNum0.mdf\";Integrated Security=True";
            con.Open();
            SqlCommand cmd = new SqlCommand();
            cmd.CommandText = "BaoCaoHD";
            cmd.CommandType = CommandType.StoredProcedure;
            cmd.Connection = con;
            cmd.Parameters.Add(new SqlParameter("@NgayLap", dateTimePicker2.Value.ToString()));
            DataSet ds = new DataSet();
            SqlDataAdapter dap = new SqlDataAdapter(cmd);
            dap.Fill(ds);

            reportViewer2.ProcessingMode = ProcessingMode.Local;
            reportViewer2.LocalReport.ReportPath = "BaoCao2.rdlc";
            if (ds.Tables[0].Rows.Count > 0)
            {
                ReportDataSource rds = new ReportDataSource();
                rds.Name = "DataSet2";
                rds.Value = ds.Tables[0];
                //Gan len bao cao
                reportViewer2.LocalReport.DataSources.Clear();
                reportViewer2.LocalReport.DataSources.Add(rds);
                reportViewer2.RefreshReport();
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }
    }
}
