<?php
   mysql_connect("localhost","webuser","secret");
   $tables = mysql_list_tables("company");
   while (list($table) = mysql_fetch_row($tables))
   {
      echo "$table <br />";   
   }
?>