<%@ Page Language="C#" AutoEventWireup="true" CodeFile="XmlDocumentTest.aspx.cs" Inherits="XmlDocumentTest" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>XmlDocument Test</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    
    <div class="Box">
        <asp:Button ID="cmdCreateXml" runat="server" Text="Create XML" OnClick="cmdCreateXml_Click" />
        <asp:Button ID="cmdReadXml" runat="server" Text="Read XML" OnClick="cmdReadXml_Click" Width="103px" />
        <asp:Button ID="cdmSearchXml" runat="server" Text="Search XML" OnClick="cmdSearchXml_Click" Width="115px" />
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
