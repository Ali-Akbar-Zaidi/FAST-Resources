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

public partial class EventReviewPage : System.Web.UI.Page
{
    protected void chkAll_CheckedChanged(object sender, EventArgs e)
    {
        // The chkAll control has AutoPostback = true.
        if (chkAll.Checked)
        {
            txtSource.Text = "";
            txtSource.Enabled = false;
        }
        else
        {
            txtSource.Enabled = true;
        }

    }
    protected void cmdGet_Click(object sender, EventArgs e)
    {
        lblResult.Text = "";

        // Check if the log exists.
        if (!EventLog.Exists(txtLog.Text))
        {
            lblResult.Text = "The event log " + txtLog.Text;
            lblResult.Text += " doesn't exist.";
        }
        else
        {
            // For maximum performance, join all the event
            // information into one large string using the
            // StringBuilder.
            System.Text.StringBuilder sb = new System.Text.StringBuilder();

            EventLog log = new EventLog(txtLog.Text);
            foreach (EventLogEntry entry in log.Entries)
            {
                // Write the event entries to the StringBuilder.
                if (chkAll.Checked ||
                  entry.Source == txtSource.Text)
                {
                    sb.Append("<b>Entry Type:</b> ");
                    sb.Append(entry.EntryType.ToString());
                    sb.Append("<br /><b>Message:</b> ");
                    sb.Append(entry.Message);
                    sb.Append("<br /><b>Time Generated:</b> ");
                    sb.Append(entry.TimeGenerated);
                    sb.Append("<br /><br />");
                }
            }
            // Copy the complete text to the web page.
            lblResult.Text = sb.ToString();
        }

    }
}
