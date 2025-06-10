<?php
   mysql_connect("localhost","root","secret");
   $dbs = mysql_list_dbs();
   echo "Databases: <br />";
   while (list($db) = mysql_fetch_row($dbs))
   { 
      echo "$db <br />";   
   }
?>