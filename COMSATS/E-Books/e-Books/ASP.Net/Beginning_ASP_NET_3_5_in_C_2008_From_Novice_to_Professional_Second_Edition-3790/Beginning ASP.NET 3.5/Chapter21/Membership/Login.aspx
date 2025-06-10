<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Login.aspx.cs" Inherits="Login" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN" "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" >
<head runat="server">
    <title>Untitled Page</title>
    <link href="StyleSheet.css" rel="stylesheet" type="text/css" />
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Login ID="Login1" runat="server" BackColor="#EFF3FB" BorderColor="#B5C7DE" BorderPadding="4" BorderStyle="Solid" BorderWidth="1px" Font-Names="Verdana" ForeColor="#333333"
            CreateUserText="Register for the first time" 
            CreateUserUrl="Register.aspx"
              PasswordRecoveryText="Forgot your password?" PasswordRecoveryUrl="PasswordRecovery.aspx"
            InstructionText="Please enter your username and password for <br />logging into the system." Height="256px" Width="368px">
            <TitleTextStyle BackColor="#507CD1" Font-Bold="True" Font-Size="Large" ForeColor="White" Height="35px" />
            <InstructionTextStyle Font-Italic="True" ForeColor="Black"  />         
            <LoginButtonStyle BackColor="White" BorderColor="#507CD1" BorderStyle="Solid" BorderWidth="1px"
                Font-Names="Verdana"  ForeColor="#284E98" />
        </asp:Login>
        </div>
    </form>
</body>
</html>
