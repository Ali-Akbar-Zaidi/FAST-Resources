using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

/// <summary>
/// Summary description for Product
/// </summary>
public class Product
{
	private int id;
	private string name;
	private decimal price;

	public int ID
	{
		get { return id; }
		set { id = value; }
	}

	public string Name
	{
		get { return name; }
		set { name = value; }
	}

	public decimal Price
	{
		get { return price; }
		set { price = value; }
	}
}