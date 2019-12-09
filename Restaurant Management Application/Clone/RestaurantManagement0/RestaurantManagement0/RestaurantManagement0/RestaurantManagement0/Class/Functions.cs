using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Data;
using System.Data.SqlClient;

namespace RestaurantManagement0.Class
{
    class Functions
    {
        public static SqlConnection Con;

        public static void Connect()
        {
            Con = new SqlConnection();
            Con.ConnectionString = "Data Source=(LocalDB)\\MSSQLLocalDB;AttachDbFilename=" + "\"E:\\C Sharp Project\\RestaurantManagement0\\RestaurantManagement0\\RestaurantManagement0\\ResManagement.mdf\";Integrated Security=True";
            Con.Open();

            // check the state of connection
            /*
            if (Con.State == ConnectionState.Open)
                System.Windows.MessageBox.Show("Connect successful.");
            else
                System.Windows.MessageBox.Show("Connect unsuccessful.");
            */
        }

        public static void Disconnect()
        {
            if (Con.State == ConnectionState.Open)
            {
                Con.Close();

                // release the resource
                Con.Dispose();

                Con = null;
            }
        }
    }
}
