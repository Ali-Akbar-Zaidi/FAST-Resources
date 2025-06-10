<%@ Page language="c#" Inherits="WebControls.TablePictures" CodeFile="TablePictures.aspx.cs" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
 "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
  <title>Table Test</title>
</head>
<body>
  <form runat="server">
  <div>
    Rows:
    <asp:TextBox ID="txtRows" runat="server" />&nbsp;
    Cols:
    <asp:TextBox ID="txtCols" runat="server" />
    <br /><br />
    <asp:CheckBox ID="chkBorder" runat="server"
         Text="Put Border Around Cells" />
    <br /><br />
    <asp:Button ID="cmdCreate" OnClick="cmdCreate_Click" runat="server"
     Text="Create" />
    <br /><br />
    <asp:Table ID="tbl" runat="server" />
  </div>
  </form>
</body>
</html>

