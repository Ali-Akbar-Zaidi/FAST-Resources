<%@ Page Language="C#" AutoEventWireup="true" CodeFile="SimpleTrace.aspx.cs" Inherits="SimpleTrace" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        A Simple Tracing Example<br />
        <br />
        <asp:Button ID="cmdTrace" runat="server" OnClick="cmdTrace_Click" Text="Trace" /></div>
    </form>
</body>
</html>
