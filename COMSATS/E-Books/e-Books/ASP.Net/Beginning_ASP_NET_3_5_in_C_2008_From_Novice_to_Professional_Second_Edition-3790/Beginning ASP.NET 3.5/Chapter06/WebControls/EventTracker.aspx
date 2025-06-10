<%@ Page language="c#" Inherits="WebControls.EventTracker" CodeFile="EventTracker.aspx.cs" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
 "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head id="Head1" runat="server">
  <title>Event Tracker</title>
</head>
<body>
  <form id="Form1" runat="server">
    <div>       
      <h1>Controls being monitored for change events:</h1>
      <asp:TextBox ID="txt" runat="server" AutoPostBack="true"
       OnTextChanged="CtrlChanged" />
      <br /><br />
      <asp:CheckBox ID="chk" runat="server" AutoPostBack="true"
       OnCheckedChanged="CtrlChanged"/>
      <br /><br />
      <asp:RadioButton ID="opt1" runat="server" GroupName="Sample"
       AutoPostBack="true" OnCheckedChanged="CtrlChanged"/>
      <asp:RadioButton ID="opt2" runat="server" GroupName="Sample"
       AutoPostBack="true" OnCheckedChanged="CtrlChanged"/>
      <br /><br /><br /> 
       <h1>List of events:</h1>
      <asp:ListBox ID="lstEvents" runat="server" Width="355px"
       Height="305px" /><br />      
    </div>
  </form>
</body>
</html>
