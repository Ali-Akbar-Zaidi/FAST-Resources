<?php

   $mysqli = new mysqli("127.0.0.1", "siteuser", "secret", "company");

   $query = "SELECT productid, name, price FROM product ORDER by name";
   $result = $mysqli->query($query, MYSQLI_STORE_RESULT);

   // Cycle through the result set
   while(list($productid, $name, $price) = $result->fetch_row())
      echo "($productid) $name: $price <br />";

   // Free the result set
   $result->free();

?>