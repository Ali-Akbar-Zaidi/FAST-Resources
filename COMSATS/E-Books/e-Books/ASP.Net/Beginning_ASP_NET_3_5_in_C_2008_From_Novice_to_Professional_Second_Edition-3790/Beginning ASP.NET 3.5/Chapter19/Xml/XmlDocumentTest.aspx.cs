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
using System.IO;
using System.Xml;
using System.Collections.Generic;

public partial class XmlDocumentTest : System.Web.UI.Page
{
    private string file;
    protected void Page_Load(object sender, EventArgs e)
    {
        file = Path.Combine(Request.PhysicalApplicationPath, @"App_Data\SuperProProductList1.xml");
    }

	protected void cmdCreateXml_Click(object sender, EventArgs e)
	{
		// Start with a blank in-memory document.
		XmlDocument doc = new XmlDocument();

		// Create some variables that will be useful for
		// manipulating XML data.
		XmlElement rootElement, productElement, priceElement;
		XmlAttribute productAttribute;
		XmlComment comment;

		// Create the declaration.
		XmlDeclaration declaration;
		declaration = doc.CreateXmlDeclaration("1.0", null, "yes");

		// Insert the declaration as the first node.
		doc.InsertBefore(declaration, doc.DocumentElement);

		// Add a comment.
		comment = doc.CreateComment("Created with the XmlDocument class.");
		doc.InsertAfter(comment, declaration);

		// Add the root node.
		rootElement = doc.CreateElement("SuperProProductList");
		doc.InsertAfter(rootElement, comment);

		// Add the first product.
		productElement = doc.CreateElement("Product");
		rootElement.AppendChild(productElement);

		// Set and add the product attributes.
		productAttribute = doc.CreateAttribute("ID");
		productAttribute.Value = "1";
		productElement.SetAttributeNode(productAttribute);
		productAttribute = doc.CreateAttribute("Name");
		productAttribute.Value = "Chair";
		productElement.SetAttributeNode(productAttribute);

		// Add the price node.
		priceElement = doc.CreateElement("Price");
		priceElement.InnerText = "49.33";
		productElement.AppendChild(priceElement);

		// Add the second product.
		productElement = doc.CreateElement("Product");
		rootElement.AppendChild(productElement);

		// Set and add the product attributes.
		productAttribute = doc.CreateAttribute("ID");
		productAttribute.Value = "2";
		productElement.SetAttributeNode(productAttribute);
		productAttribute = doc.CreateAttribute("Name");
		productAttribute.Value = "Car";
		productElement.SetAttributeNode(productAttribute);

		// Add the price node.
		priceElement = doc.CreateElement("Price");
		priceElement.InnerText = "43399.55";
		productElement.AppendChild(priceElement);

		// Add the third product.
		productElement = doc.CreateElement("Product");
		rootElement.AppendChild(productElement);

		// Set and add the product attributes.
		productAttribute = doc.CreateAttribute("ID");
		productAttribute.Value = "3";
		productElement.SetAttributeNode(productAttribute);
		productAttribute = doc.CreateAttribute("Name");
		productAttribute.Value = "Fresh Fruit Basket";
		productElement.SetAttributeNode(productAttribute);

		// Add the price node.
		priceElement = doc.CreateElement("Price");
		priceElement.InnerText = "49.99";
		productElement.AppendChild(priceElement);

		// Save the document.
		doc.Save(file);

		lblXml.Text = @"File " + file + " written successfully.";
	}

	protected void cmdReadXml_Click(object sender, EventArgs e)
	{
        // Create the document.
        XmlDocument doc = new XmlDocument();
        doc.Load(file);

        // Loop through all the nodes, and create the list of Product objects .
        List<Product> products = new List<Product>();

        foreach (XmlElement element in doc.DocumentElement.ChildNodes)
        {
            Product newProduct = new Product();
            newProduct.ID = Int32.Parse(element.GetAttribute("ID"));
            newProduct.Name = element.GetAttribute("Name");

            // If there were more than one child node, you would probably use
            // another For Each loop here and move through the
            // Element.ChildNodes collection.
            newProduct.Price = Decimal.Parse(element.ChildNodes[0].InnerText);

            products.Add(newProduct);
        }

        // Display the results.
        gridResults.DataSource = products;
        gridResults.DataBind();
	}

	protected void cmdSearchXml_Click(object sender, EventArgs e)
	{
		XmlDocument doc = new XmlDocument();
		doc.Load(file);

		// Find the matches.
		XmlNodeList results = doc.GetElementsByTagName("Price");

		// Display the results.
		lblXml.Text = "<b>Found " + results.Count.ToString() + " Matches ";
		lblXml.Text += " for the Price tag: </b><br><br>";
		foreach (XmlNode result in results)
		{
			lblXml.Text += result.FirstChild.Value + "<br>";
		}

	}
}
