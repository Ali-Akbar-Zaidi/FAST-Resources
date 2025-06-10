<%@ Page Language="C#" AutoEventWireup="true" CodeFile="XmlWriterTest.aspx.cs" Inherits="XmlTextWriterTest" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    
    <div class="Box">
        <asp:Button ID="cmdCreateXml" runat="server" Text="Create XML" OnClick="cmdCreateXml_Click" />
        <asp:Button ID="cmdReadXml" runat="server" Text="Read XML (as text)" OnClick="cmdReadXml_Click" />
        <asp:Button ID="cdmReadXmlAsObjects" runat="server" Text="Read XML (as objects)" OnClick="cdmReadXmlAsObjects_Click" Width="172px" />
    </div>
    <div>
        <br />
        <asp:Label ID="lblXml" runat="server" Text="" EnableViewState="false" ></asp:Label>&nbsp;
        <asp:GridView ID="gridResults"
            runat="server" EnableViewState="false" >
        </asp:GridView>
    </div>
    </form>
</body>
</html>
