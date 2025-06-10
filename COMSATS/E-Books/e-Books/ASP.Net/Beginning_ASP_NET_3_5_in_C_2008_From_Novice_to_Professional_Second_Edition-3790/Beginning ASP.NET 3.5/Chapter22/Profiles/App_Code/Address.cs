using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

[Serializable()]
public class Address
{
	private string name;
	public string Name
	{
		get { return name; }
		set { name = value; }
	}

	private string street;
	public string Street
	{
		get { return street; }
		set { street = value; }
	}

	private string city;
	public string City
	{
		get { return city; }
		set { city = value; }
	}

	private string zipCode;
	public string ZipCode
	{
		get { return zipCode; }
		set { zipCode = value; }
	}

	private string state;
	public string State
	{
		get { return state; }
		set { state = value; }
	}

	private string country;
	public string Country
	{
		get { return country; }
		set { country = value; }
	}

	public Address(string name, string street, string city,
		string zipCode, string state, string country)
	{
		Name = name;
		Street = street;
		City = city;
		ZipCode = zipCode;
		State = state;
		Country = country;
	}

	public Address() { }
}
