<%@ Page Language="C#" AutoEventWireup="true" CodeFile="XmlDataBinding.aspx.cs" Inherits="XmlDataBinding" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>XML Data Binding</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <asp:XmlDataSource ID="sourceXml" runat="server"
 DataFile="App_Data\SuperProProductListNested.xml" />
<asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="True"
 DataSourceID="sourceXml" Visible="False"/>
<br />
        <asp:TreeView ID="TreeView1" runat="server" DataSourceID="sourceXml">
            <DataBindings>
                <asp:TreeNodeBinding DataMember="SuperProProductList" Text="Product List" />
                <asp:TreeNodeBinding DataMember="Category"  TextField="Name" />
                <asp:TreeNodeBinding DataMember="Product" TextField="Name" />
                <asp:TreeNodeBinding DataMember="Price" TextField="#InnerText" />
            </DataBindings>
        </asp:TreeView>

    </div>
    </form>
</body>
</html>
