<%@ Page Language="C#" AutoEventWireup="true" CodeFile="SessionStateExample.aspx.cs" Inherits="SessionStateExample" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
</head>
<body>
    <form id="form1" runat="server">
      <div>
   
   <asp:Label id="sessionLabel" runat="server" Width="472px" Font-Size="Medium" Font-Names="Verdana" Font-Bold="True"></asp:Label>
   <br /><br />
	<div style="border-right: 2px groove; border-top: 2px groove; border-left: 2px groove; width: 576px; border-bottom: 2px groove; HEIGHT: 160px; background-color: #FFFFD9">
	<table>
	  <tr>
	  <td style="padding: 10px; height: 152px;">
	  <asp:ListBox id="itemsList" Width="208px" Height="128px" runat="server"></asp:ListBox>
	  </td>
	  <td style="padding: 10px; height: 152px;">
	  <asp:Button id="infoButton" Width="120px" Text="More Information" runat="server" 
			OnClick="cmdMoreInfo_Click"></asp:Button>
	  <br />
	  <br />
	  <asp:Label id="recordLabel" runat="server" Width="272px" Font-Size="Small" Font-Names="Verdana" Font-Bold="True"></asp:Label>
	  </td>	  
	  </tr>
	</table>
	</div>
	
	</div>
    </form>
</body>
</html>
