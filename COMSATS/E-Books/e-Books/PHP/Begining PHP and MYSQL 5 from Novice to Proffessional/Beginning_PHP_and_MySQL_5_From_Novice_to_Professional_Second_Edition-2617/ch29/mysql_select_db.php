<?php
   @mysql_connect("localhost", "webuser", "secret") 
   or die("Could not connect to MySQL server!");

   @mysql_select_db("company") 
   or die("Could not select database!");
?>