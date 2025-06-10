<?php
   ...
   $query = "SELECT productid, name FROM product ORDER BY name";
   $result = mysql_query($query);
   while (list($productid, $name) = mysql_fetch_row($result))
   {
      echo "Product: $name ($productid) <br />";
   }
   ...
?>