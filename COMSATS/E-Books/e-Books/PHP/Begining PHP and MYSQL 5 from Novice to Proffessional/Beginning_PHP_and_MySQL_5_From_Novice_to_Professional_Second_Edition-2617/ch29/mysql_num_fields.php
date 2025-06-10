<?php
   ... 
   $query = "SELECT productid, name FROM product ORDER BY name";
   $result = mysql_query($query);
   echo "Total number of fields returned: ".mysql_num_fields($result).".<br />";
   ...
?>