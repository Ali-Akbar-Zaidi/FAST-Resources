<%@ Page Language="C#" AutoEventWireup="true" CodeFile="TraceExample.aspx.cs" Inherits="TraceExample" Trace="true" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Button ID="cmdWrite" runat="server" OnClick="cmdWrite_Click" Text="Write" />
        <asp:Button ID="cmdWriteCategory" runat="server" OnClick="cmdWriteCategory_Click"
            Text="Write Category" />
        <asp:Button ID="cmdError" runat="server" OnClick="cmdError_Click" Text="Write Error" />
    
    </div>
    </form>
</body>
</html>
