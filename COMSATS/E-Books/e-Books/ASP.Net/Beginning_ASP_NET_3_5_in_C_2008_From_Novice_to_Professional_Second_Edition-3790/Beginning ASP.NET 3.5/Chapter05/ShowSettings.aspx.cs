using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Web.Configuration;

public partial class ShowSettings : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {
		lblTest.Text = "This app will look for data in the directory:<br /><b>";
		lblTest.Text += WebConfigurationManager.AppSettings["DataFilePath"];
		lblTest.Text += "</b>";		
    }
}
