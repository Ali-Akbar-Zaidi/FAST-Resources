<%@ Page Language="C#" AutoEventWireup="true" CodeFile="EventReviewPage.aspx.cs" Inherits="EventReviewPage" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Label ID="Label1" runat="server" Text="Event Log:"></asp:Label>
        &nbsp;<asp:TextBox ID="txtLog" runat="server">ProseTech</asp:TextBox>&nbsp;
        <asp:CheckBox ID="chkAll" runat="server" AutoPostBack="True" OnCheckedChanged="chkAll_CheckedChanged"
            Text="Get all entries for this log" /><br />
        <asp:Label ID="Label2" runat="server" Text="Source:"></asp:Label>
        &nbsp; &nbsp;&nbsp;
        <asp:TextBox ID="txtSource" runat="server">DivideByZeroApp</asp:TextBox><br />
        <br />
        <asp:Button ID="cmdGet" runat="server" OnClick="cmdGet_Click" Text="Get Records" /><br />
        <br />
        <br />
        <asp:Panel ID="Panel1" runat="server" BorderStyle="Groove" BorderWidth="2px" Height="168px"
            ScrollBars="Vertical" Width="488px">
            <asp:Label ID="lblResult" runat="server"></asp:Label></asp:Panel>
    
    </div>
    </form>
</body>
</html>
