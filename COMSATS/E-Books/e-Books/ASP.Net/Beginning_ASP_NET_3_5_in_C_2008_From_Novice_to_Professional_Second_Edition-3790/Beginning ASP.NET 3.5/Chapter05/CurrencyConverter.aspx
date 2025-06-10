<%@ Page Language="C#" AutoEventWireup="true"
    CodeFile="CurrencyConverter.aspx.cs" Inherits="CurrencyConverter" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.1//EN"
 "http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>Currency Converter</title>
  </head>
  <body>
    <form ID="Form1" method="post" runat="server">

        <div style="border-right: thin ridge; padding-right: 20px; border-top: thin ridge;
            padding-left: 20px; padding-bottom: 20px; border-left: thin ridge; width: 531px;
            padding-top: 20px; border-bottom: thin ridge; font-family: Verdana; background-color: #FFFFE8">
        Convert: &nbsp;
        <input type="text" ID="US" runat="server" style="width: 102px" />&nbsp; U.S. dollars to &nbsp;
        <select ID="Currency" runat="server" />
        <br /><br />
        <input type="submit" value="OK" ID="Convert" runat="server" OnServerClick="Convert_ServerClick" />
        <input type="submit" value="Show Graph" ID="ShowGraph" runat="server" OnServerClick="ShowGraph_ServerClick" />
        <br /><br />
        <img ID="Graph" alt="Currency Graph" scr="" runat="server" />
        <br /><br />
        <div style="font-weight: bold" ID="Result" runat="server"></div>
      </div>
    </form>
  </body>
</html>
