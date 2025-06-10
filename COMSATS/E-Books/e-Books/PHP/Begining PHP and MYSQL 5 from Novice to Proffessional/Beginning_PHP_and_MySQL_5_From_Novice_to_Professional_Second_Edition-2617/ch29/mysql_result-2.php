<?php
   ...
   $query = "SELECT productid, name FROM product ORDER BY name";
   $result = mysql_query($query);

   // Loop through each row, outputting the productid and name
   for ($count=0; $count <= mysql_numrows($result); $count++)
   {
      $productid = mysql_result($result, $count, "productid");
      $name = mysql_result($result, $count, "name");
      echo "Product: $name ($productid) <br />";
   }
   ...
?>