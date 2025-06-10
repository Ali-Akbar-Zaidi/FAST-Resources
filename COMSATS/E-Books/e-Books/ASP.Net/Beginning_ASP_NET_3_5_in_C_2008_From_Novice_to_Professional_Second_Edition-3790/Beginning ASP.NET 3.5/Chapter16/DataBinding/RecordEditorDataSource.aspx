<%@ Page Language="C#" AutoEventWireup="true" CodeFile="RecordEditorDataSource.aspx.cs" Inherits="RecordEditorDataSource" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head id="Head1" runat="server">
    <title>Record Editor</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:DropDownList ID="lstProduct" runat="server" AutoPostBack="True" Width="280px" DataSourceID="sourceProducts" DataTextField="ProductName" DataValueField="ProductID">
        </asp:DropDownList>
        <br />
        <br />
        <hr />
        <br />
        <table>
            <tr>
                <td style="width: 234px" valign="top">
                    <asp:DetailsView ID="DetailsView1" runat="server" DataSourceID="sourceProductDetails"
                        Height="50px" Width="200px" AutoGenerateEditButton="True">
                    </asp:DetailsView>
                    &nbsp;
                </td>
                <td style="width: 190px">
                    <br />
                    <br />
                    </td>
            </tr>
        </table>
        
        <asp:SqlDataSource ID="sourceProducts" runat="server"
  ProviderName="System.Data.SqlClient"
  ConnectionString="<%$ ConnectionStrings:Northwind %>"
  SelectCommand="SELECT ProductName, ProductID FROM Products"
/>
<asp:SqlDataSource ID="sourceProductDetails" runat="server"
  ProviderName="System.Data.SqlClient"
  ConnectionString="<%$ ConnectionStrings:Northwind %>"
  SelectCommand="SELECT ProductID, ProductName, UnitPrice, UnitsInStock, UnitsOnOrder, ReorderLevel, Discontinued FROM Products WHERE ProductID=@ProductID"
  UpdateCommand="UPDATE Products SET ProductName=@ProductName, UnitPrice=CONVERT(money, @UnitPrice), UnitsInStock=@UnitsInStock, UnitsOnOrder=@UnitsOnOrder, ReorderLevel=@ReorderLevel, Discontinued=@Discontinued WHERE ProductID=@ProductID">
  <SelectParameters>
        <asp:ControlParameter ControlID="lstProduct" Name="ProductID"
     PropertyName="SelectedValue" />
  </SelectParameters>
</asp:SqlDataSource>
        <asp:Label ID="lblInfo" runat="server" EnableViewState="False" Font-Bold="True" ForeColor="#C00000"></asp:Label>
        <br />
        <br />
        <br />
        <hr />
        This page allows overlapping edits. See RecordEditorDataSource_MatchAllValues.aspx
        for a version that doesn't.
</div>
    </form>
</body>
</html>
