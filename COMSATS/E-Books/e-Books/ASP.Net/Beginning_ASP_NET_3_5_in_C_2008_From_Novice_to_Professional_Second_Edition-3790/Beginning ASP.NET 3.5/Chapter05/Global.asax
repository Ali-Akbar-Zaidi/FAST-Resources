<%@ Application Language="C#" %>

<script runat="server">

    protected void Application_OnEndRequest()
    {
        Response.Write("<hr>This page was served at " + 
         DateTime.Now.ToString());
    }

       
</script>
