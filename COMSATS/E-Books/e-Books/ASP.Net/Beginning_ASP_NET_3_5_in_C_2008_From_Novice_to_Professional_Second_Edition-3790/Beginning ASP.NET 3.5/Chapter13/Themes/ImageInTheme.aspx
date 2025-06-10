<%@ Page Language="C#" AutoEventWireup="true" CodeFile="ImageInTheme.aspx.cs" Inherits="ImageInTheme" Theme="FunkyTheme" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:ImageButton ID="ImageButton1" runat="server" SkinID="OKButton" />
        <asp:ImageButton ID="ImageButton2" runat="server" SkinID="CancelButton" />
     </div>
    </form>
</body>
</html>
