<?php
   // Create a new server connection
   $mysqli = new mysqli("127.0.0.1", "siteuser", "secret", "company");

   // Create the query and corresponding placeholders
   $query = "SELECT productid, name, price, description
             FROM product ORDER BY productid";

   // Prepare the statement for execution
   $stmt = $mysqli->prepare($query);

   .. Do something with the prepared statement

   // Recuperate the statement resources
   $stmt->close();

   // Close the connection
   $mysqli->close();

?>
