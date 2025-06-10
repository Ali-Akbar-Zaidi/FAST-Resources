<?php

   // If the submit button has been pressed
   if (isset($_POST['submit']))
   {

      // Connect to the server and select the database
      $linkID = @mysql_connect("localhost","webuser","secret") 
                or die("Could not connect to MySQL server");
      @mysql_select_db("company") or die("Could not select database");

      // Retrieve the posted product information.
      $productid = $_POST['productid'];
      $name = $_POST['name'];
      $price = $_POST['price'];
      $description = $_POST['description'];

      // Insert the product information into the product table
      $query = "INSERT INTO product SET productid='$productid', name='$name', 
                price='$price', description='$description'";

      $result = mysql_query($query);

      // Display an appropriate message
      if ($result) echo "<p>Product successfully inserted!</p>"; 
      else echo "<p>There was a problem inserting the product!</p>";

      mysql_close();
    }

  // Include the insertion form
  include "insert.php";

?>