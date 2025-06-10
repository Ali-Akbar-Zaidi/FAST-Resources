<%@ Page Language="C#" AutoEventWireup="true" CodeFile="PreserveMembers.aspx.cs" Inherits="PreserveMembers" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:TextBox ID="txtValue" runat="server" Height="153px" TextMode="MultiLine" Width="460px">This is a test of the PreserveMembers.aspx page</asp:TextBox><br />
        <br />
        <asp:Button ID="cmdSave" runat="server" OnClick="cmdSave_Click" Text="Save Contents" />
        &nbsp;<asp:Button ID="cmdLoad" runat="server" OnClick="cmdLoad_Click" Text="Load Contents" /></div>
    </form>
</body>
</html>
