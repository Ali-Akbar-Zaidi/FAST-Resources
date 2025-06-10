<?php

   // Server
   $servername = $_SERVER['SERVER_NAME'];
   $recipient = "webmaster@example.com";
   $subject = "404 error detected: ".$_SERVER['PHP_SELF'];
   $timestamp = date( "F d, Y G:i:s", time() );
   $referrer = $_SERVER['HTTP_REFERER'];
   $ip = $_SERVER['REMOTE_ADDR'];
   $redirect = $_SERVER['REQUEST_URI'];

   $body = <<< body
      A 404 error was detected at: $timestamp.

      Server: $servername
      Missing page: $redirect
      Referring document: $referrer
      User IP Address: $ip
   body;

   mail($recipient, $subject, $body, "From: administrator\r\n");
?>

<h3>File Not Found</h3>
<p>
Please forgive us, as our Web site is currently undergoing maintenance.
As a result, you may experience occasional difficulties accessing documents
and/or services.
The site administrator has been emailed with a detailed event log of this matter.
</p>
Thank you,<br />
The Web site Crew