<%@ Page Language="C#" AutoEventWireup="true" CodeFile="ContentAndGraphics.aspx.cs" Inherits="ContentAndGraphics" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Label id="Label1" runat="server">Here is some content.</asp:Label>
	    <br /><br />
	    <asp:Image id="Image1" runat="server" ImageUrl="GraphicalText2.aspx?Name=Joe%20Brown"></asp:Image>
	    <br /><br />
	    <asp:Label id="Label2" runat="server">Here is some more content.</asp:Label>
    </div>
    </form>
</body>
</html>
