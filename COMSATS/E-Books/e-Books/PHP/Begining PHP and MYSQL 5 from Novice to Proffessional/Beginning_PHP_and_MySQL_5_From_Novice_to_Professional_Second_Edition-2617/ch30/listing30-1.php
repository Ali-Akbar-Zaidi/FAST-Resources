<?php
   // Create a new server connection
   $mysqli = new mysqli("127.0.0.1", "siteuser", "secret", "company");

   // Create the query and corresponding placeholders
   $query = "INSERT INTO product SET rowID=NULL, productID=?, 
             name=?, price=?, description=?";

   // Prepare the statement for execution
   $stmt = $mysqli->prepare($query);

   // Bind the parameters
   $stmt->bind_param('ssds', $productid, $name, $price, $description);

   // Assign the posted productid array
   $productidarray = $_POST['productid'];

   // Assign the posted name array
   $namearray = $_POST['name'];

   // Assign the posted price array
   $pricearray = $_POST['price'];

   // Assign the posted description array
   $descarray = $_POST['description'];

   // Initialize the counter
   $x = 0;

   // Cycle through each posted URL in the array, and iteratively execute the query
   while ($x < sizeof($productidarray)) {

      $productid = $productidarray[$x];
      $name = $namearray[$x];
      $price = $pricearray[$x];
      $description = $descarray[$x];

      $stmt->execute();

   }

   // Recuperate the statement resources
   $stmt->close();

   // Close the connection
   $mysqli->close();

?>