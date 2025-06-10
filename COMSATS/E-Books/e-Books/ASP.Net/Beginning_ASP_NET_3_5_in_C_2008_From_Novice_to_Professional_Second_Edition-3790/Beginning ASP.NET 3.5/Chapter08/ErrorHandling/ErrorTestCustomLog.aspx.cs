using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;
using System.Diagnostics;
using System.Drawing;

public partial class ErrorTestCustomLog : System.Web.UI.Page
{
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void cmdCompute_Click(object sender, EventArgs e)
    {
        try
        {
            decimal a, b, result;
            a = Decimal.Parse(txtA.Text);
            b = Decimal.Parse(txtB.Text);
            result = a / b;
            lblResult.Text = result.ToString();
            lblResult.ForeColor = Color.Black;
        }
        catch (Exception err)
        {
            lblResult.Text = "<b>Message:</b> " + err.Message + "<br /><br />";
            lblResult.Text += "<b>Source:</b> " + err.Source + "<br /><br />";
            lblResult.Text += "<b>Stack Trace:</b> " + err.StackTrace;
            lblResult.ForeColor = Color.Red;

            // Write the information to the event log.
            // Register the event source if needed.
            if (!EventLog.SourceExists("DivideByZeroApp"))
            {
                // This registers the event source and creates the custom log,
                // if needed.
                EventLog.CreateEventSource("DivideByZeroApp", "ProseTech");
            }

            // Open the log. If the log doesn't exist,
            // it will be created automatically.
            EventLog log = new EventLog("ProseTech");
            log.Source = "DivideByZeroApp";
            log.WriteEntry(err.Message, EventLogEntryType.Error);

        }
    }
}
