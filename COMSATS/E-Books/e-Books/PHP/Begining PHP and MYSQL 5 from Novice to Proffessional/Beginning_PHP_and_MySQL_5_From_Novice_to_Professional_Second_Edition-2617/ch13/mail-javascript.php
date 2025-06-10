<?php

   // If the mail form has been submitted
   if (isset($_POST['submit']))
   {
      // Designate a mail header and body
      $headers = "FROM:editor@example.com\n";
      $body = $_POST['name']." thought you'd be interested in this
                 article:\nhttp://www.example.com/article.html?id=".$_POST['id'];
      // Mail the article URL
      mail($_POST['recipient'],"Example.com News Article",$body,$headers);

      // Notify the user
      echo "The article has been mailed to ".$_POST['recipient'];
   }
?>
<p>
   Email this article to a friend!
</p>
<form action="mail.html" method="post">
   <input type="hidden" name="id" value="<?php echo $_GET['id'];?>" />
   <p>
      Recipient email:<br />
      <input type="text" name="recipient" size="20" maxlength="40" value="" />
   </p>
   <p>
      Your name:<br />
      <input type="text" name="name" size="20" maxlength="40" value="" />
   </p>
   <input type="submit" name="submit" value="Send Article" />
</form>