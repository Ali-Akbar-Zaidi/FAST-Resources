using System;
using System.Data;
using System.Configuration;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

public class LinkClickedEventArgs : EventArgs
{
    private string url;
    public string Url
    {
        get { return url; }
        set { url = value; }
    }

    private bool cancel = false;
    public bool Cancel
    {
        get { return cancel; }
        set { cancel = value; }
    }

    public LinkClickedEventArgs(string url)
    {
        Url = url;
    }
}
public delegate void LinkClickedEventHandler(object sender,
  LinkClickedEventArgs e);

