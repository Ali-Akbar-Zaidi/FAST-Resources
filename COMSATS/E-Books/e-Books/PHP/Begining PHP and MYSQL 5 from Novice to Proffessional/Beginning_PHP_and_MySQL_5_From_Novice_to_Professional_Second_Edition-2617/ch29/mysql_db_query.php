<?php
   /* Connect to MySQL server and select database. */
   $linkID = @mysql_connect("localhost","webuser","secret") 
                  or die("Could not connect to MySQL server");

   /* Create and execute query. */
   $query = "INSERT INTO product set productid='A0022JKL', name='pants', 
             price='45.20'";
   $result = mysql_db_query("company", $query);

   /* Close connection to database server. */
   mysql_close();
?>