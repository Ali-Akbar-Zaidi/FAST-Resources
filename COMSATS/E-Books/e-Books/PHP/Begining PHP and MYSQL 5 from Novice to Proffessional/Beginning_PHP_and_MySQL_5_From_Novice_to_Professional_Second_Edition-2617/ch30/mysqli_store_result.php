<?php
   // Connect to the MySQL server     
   $mysqli = new mysqli();
   $mysqli->connect("127.0.0.1", "siteuser", "secret", "company");

   // Execute the query and store the entire buffered result
   $query = "SELECT productid, name, price FROM product ORDER by name";
   $mysqli->real_query($query);
   $result = $mysqli->store_result();

   // Cycle through the result set
   while(list($productid, $name, $price) = $result->fetch_row())
      echo "($productid) $name: $price <br />";

   // Free the result set
   $result->free();

?>