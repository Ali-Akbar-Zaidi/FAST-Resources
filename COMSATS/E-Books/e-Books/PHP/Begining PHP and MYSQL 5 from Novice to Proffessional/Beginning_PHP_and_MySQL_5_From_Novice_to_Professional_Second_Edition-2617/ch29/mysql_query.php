<?php
     /* Connect to MySQL server and select database. */
     $linkID = @mysql_connect("localhost","webuser","secret") 
                        or die("Could not connect to MySQL server");
     @mysql_select_db("company") or die("Could not select database");

     /* Create and execute query. */
     $query = "INSERT INTO product set productid='abcd123', name='pants', 
               price='45.20'";
     $result = mysql_query($query);

     /* Close connection to database server. */
     mysql_close();
?>