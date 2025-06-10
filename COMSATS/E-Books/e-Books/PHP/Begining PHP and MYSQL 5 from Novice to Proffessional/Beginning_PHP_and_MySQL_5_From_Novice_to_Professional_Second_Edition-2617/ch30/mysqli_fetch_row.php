<?php
   ...
   $query = "SELECT productid, name FROM product ORDER BY name";
   $result = $mysqli->mysqli_query($query);
   while (list($productid, $name) = $result->fetch_row())
   {
      echo "($productid) $name: $price <br />";
   }
   ...
?>