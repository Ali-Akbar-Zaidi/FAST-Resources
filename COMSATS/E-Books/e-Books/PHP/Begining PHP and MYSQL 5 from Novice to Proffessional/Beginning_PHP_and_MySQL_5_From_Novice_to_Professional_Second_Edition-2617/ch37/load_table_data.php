<?php
   /* Connect to the MySQL server and select the corporate database. */
   $mysqli = new mysqli("localhost","someuser","secret","corporate");

   /* Open and parse the sales.csv file. */
   $fh = fopen("sales.csv", "r");

   while ($line = fgetcsv($fh, 1000, ","))
   {
      $orderID = $line[0];
      $clientID = $line[1];
      $order_time = $line[2];
      $sub_total = $line[3];
      $shipping_cost = $line[4];
      $total_cost = $line[5];

      /* Insert the data into the sales table. */
      $query = "INSERT INTO sales SET orderID='$orderID', 
              clientID='$clientID', order_time='$order_time',
              sub_total='$sub_total', shipping_cost='$shipping_cost',
              total_cost='$total_cost'";
      $result = $mysqli->query($query);
   }

   fclose($fh);
   mysqli_close();
?>