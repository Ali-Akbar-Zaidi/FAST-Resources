<?php
   mysql_connect("localhost","webuser","secret");
   mysql_select_db("company");
   $query = "SELECT * FROM product LIMIT 1";
   $result = mysql_query($query);
   $fields = mysql_num_fields($result);
   for($count=0;$count<$fields;$count++)
   {
      $field = mysql_fetch_field($result,$count);
      echo "<p>$field->name $field->type ($field->max_length)</p>";
   }
?>