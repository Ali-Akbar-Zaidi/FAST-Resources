<%@ Page Language="C#" AutoEventWireup="true" CodeFile="FooterHost.aspx.cs" Inherits="FooterHost" %>
<%@ Register TagPrefix="apress" TagName="Footer" Src="Footer.ascx" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Footer Host</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <h2>A Page With a Configurable Footer</h2>
		<br /><br /><br />
		<hr />
		
		<asp:RadioButton id="optShort" runat="server" GroupName="Format" Text="Short Format"></asp:RadioButton>
		<br />
		<asp:RadioButton id="optLong" runat="server" GroupName="Format" Text="Long Format"></asp:RadioButton>
		<br /><br />
		<asp:Button id="cmdRefresh" runat="server" Text="Refresh"></asp:Button>
		<hr />
		<br />
		<apress:Footer id="Footer1" runat="server"></apress:Footer>

    </div>
    </form>
</body>
</html>
