<?php
   $mysqli = new mysqli();
   $mysqli->connect("127.0.0.1", "siteuser", "secret", "company");

   $query = "SELECT productid, name, price FROM product ORDER by name";
   $mysqli->query($query);
   // Do something with the result set

   // Recuperate the query resources
   $result->free();

   // Perhaps perform some other large query

?>