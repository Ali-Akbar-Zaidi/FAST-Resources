<?php

   $mysqli = new mysqli("127.0.0.1", "root", "jason", "corporate");

   // Retrieve the userID from some session ID
   $userid = $_SESSION['userid'];

   // Create the queries
   $query = "SELECT lastname, firstname FROM user WHERE userID='$userid';";
   $query .= "SELECT product_count, CONCAT('$',total_cost) 
              FROM sales WHERE userID='$userid'";

   // Execute the queries and cycle through their results
   if($mysqli->multi_query($query)) {
      do {
         $result = $mysqli->store_result();
         while ($row = $result->fetch_row())
            echo "$row[0], $row[1] <br />";
         if ($mysqli->more_results())
            echo "********** <br />";
      } while ($mysqli->next_result());
   }
?>