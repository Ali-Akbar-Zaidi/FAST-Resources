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
using System.Data.SqlClient;
using System.Web.Configuration;
using System.Xml;

public partial class XmlDataSet : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }

	protected void cmdDataSetToXml_Click(object sender, EventArgs e)
	{
		string connectionString =
  WebConfigurationManager.ConnectionStrings["Pubs"].ConnectionString;
		string SQL = "SELECT * FROM authors WHERE city='Oakland'";

		// Create the ADO.NET objects.
		SqlConnection con = new SqlConnection(connectionString);
		SqlCommand cmd = new SqlCommand(SQL, con);
		SqlDataAdapter adapter = new SqlDataAdapter(cmd);
		DataSet ds = new DataSet("AuthorsDataSet");

		// Retrieve the data.
		con.Open();
		adapter.Fill(ds, "AuthorsTable");
		con.Close();

		// Create the XmlDataDocument that wraps this DataSet.
		XmlDataDocument dataDoc = new XmlDataDocument(ds);

		// Display the XML data (with the help of an XSLT) in the XML web control.
		XmlControl.XPathNavigator = dataDoc.CreateNavigator();
        XmlControl.TransformSource = Request.PhysicalApplicationPath + @"\App_Data\authors.xsl";

	}


	protected void cmdXmlToDataSet_Click(object sender, EventArgs e)
	{
		XmlDataDocument dataDoc = new XmlDataDocument();

		// Set the schema and retrieve the data.
		dataDoc.DataSet.ReadXmlSchema(Request.PhysicalApplicationPath +
		  @"\App_Data\SuperProProductList.xsd");
		dataDoc.Load(Request.PhysicalApplicationPath + @"\App_Data\SuperProProductList.xml");

		// Display the retrieved data.
		gridData.DataSource = dataDoc.DataSet;
		gridData.DataBind();
	}
}
