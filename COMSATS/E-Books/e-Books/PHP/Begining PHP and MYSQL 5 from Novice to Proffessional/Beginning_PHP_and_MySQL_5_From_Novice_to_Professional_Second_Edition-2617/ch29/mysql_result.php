<?php
     ...
     $query = "SELECT productid, name FROM product ORDER BY name";
     $result = mysql_query($query);
     $productid = mysql_result($result, 0, "productid");
     $name = mysql_result($result, 0, "name");
     ...
?>