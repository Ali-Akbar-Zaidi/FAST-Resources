<?php

   // Include the HTML_Table package
   require_once "HTML/Table.php";

   // Connect to the MySQL database
   $mysqli = new mysqli("localhost", "websiteuser", "secret", "corporate");

   // Create the table object

   $table = new HTML_Table();

   // Set the headers

   $table->setHeaderContents(0, 0, "Order ID");
   $table->setHeaderContents(0, 1, "Client ID");
   $table->setHeaderContents(0, 2, "Order Time");
   $table->setHeaderContents(0, 3, "Sub Total");
   $table->setHeaderContents(0, 4, "Shipping Cost");
   $table->setHeaderContents(0, 5, "Total Cost");

   // Cycle through the array to produce the table data

   // Create and execute the query
   $query = "SELECT orderID AS `Order ID`, clientID AS `Client ID`, 
                    order_time AS `Order Time`, 
                    CONCAT('$', sub_total) AS `Sub Total`, 
                    CONCAT('$', shipping_cost) AS `Shipping Cost`, 
                    CONCAT('$', total_cost) AS `Total Cost`
                    FROM sales ORDER BY orderID";

   $result = $mysqli->query($query);

   // Begin at row 1 so don't overwrite the header

   $rownum = 1;

   // Format each row

   while ($obj = $result->fetch_object()) {

      $table->setCellContents($rownum, 0, $obj->orderID);
      $table->setCellContents($rownum, 1, $obj->clientID);
      $table->setCellContents($rownum, 2, $obj->order_time);
      $table->setCellContents($rownum, 3, $obj->sub_total);
      $table->setCellContents($rownum, 4, $obj->shipping_cost);
      $table->setCellContents($rownum, 5, $obj->total_cost);

      $rownum++;

   }

   // Alternate row styling

   $table->altRowAttributes(1, null, array("class"=>"alt"));

   // Output the data

   echo $table->toHTML();

   // Close the MySQL connection

   $mysqli->close();

?>