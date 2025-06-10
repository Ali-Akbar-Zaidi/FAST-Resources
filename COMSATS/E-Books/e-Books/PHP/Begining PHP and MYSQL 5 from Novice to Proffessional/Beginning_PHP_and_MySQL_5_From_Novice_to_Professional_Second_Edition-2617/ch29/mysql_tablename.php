<?php
   mysql_connect("localhost","webuser","secret");
   $tables = mysql_list_tables("company");
   $count = 0;
   while ($count < mysql_numrows($tables))
   {
      echo mysql_tablename($tables,$count)."<br />";
      $count++;
   }
?>