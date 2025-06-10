<?php
   // Create a new server connection
   $mysqli = new mysqli("127.0.0.1", "siteuser", "secret", "company");

   // Create query
   $query = "SELECT productid, name, price, description
             FROM product ORDER BY productid";

   // Prepare the statement for execution
   $stmt = $mysqli->prepare($query);

   // Execute the statement
   $stmt->execute();

   // Bind the result parameters
   $stmt->bind_result($productid, $name, $price, $description);

   // Cycle through the results and output the data
   
   while($stmt->fetch()) {

      echo "$productid, $name, $price, $description <br />";

   }

   // Recuperate the statement resources
   $stmt->close();

   // Close the connection
   $mysqli->close();

?>