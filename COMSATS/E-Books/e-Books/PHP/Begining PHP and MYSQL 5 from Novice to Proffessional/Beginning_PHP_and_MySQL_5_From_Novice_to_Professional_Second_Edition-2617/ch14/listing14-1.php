<?php
   if (isset($_SERVER['PHP_AUTH_USER']) and isset($_SERVER['PHP_AUTH_PW'])) {
      // execute additional authentication tasks 
   } else {
        echo "<p>Please enter both a username and a password!</p>";
   }
?>