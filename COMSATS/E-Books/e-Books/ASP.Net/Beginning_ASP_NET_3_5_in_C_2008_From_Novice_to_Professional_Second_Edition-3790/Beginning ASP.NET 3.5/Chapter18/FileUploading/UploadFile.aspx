<%@ Page Language="C#" AutoEventWireup="true"  CodeFile="UploadFile.aspx.cs" Inherits="UploadFile" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
    <asp:FileUpload ID="Uploader" runat="server" Height="24px" Width="472px" />&nbsp;
        <asp:Button ID="cmdUpload" runat="server" Height="24px" OnClick="cmdUpload_Click"
            Text="Upload" Width="88px" /><br />
        <br />
        <asp:Label ID="lblInfo" runat="server" EnableViewState="False" Font-Bold="True"></asp:Label></div>
    </form>
</body>
</html>
