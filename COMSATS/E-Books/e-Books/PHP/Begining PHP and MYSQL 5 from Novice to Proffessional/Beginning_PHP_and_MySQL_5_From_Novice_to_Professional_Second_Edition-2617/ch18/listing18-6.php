<?php
   // Start session
   session_start();
   // Retrieve requested article id
   $articleid = $_GET['articleid'];

   // Connect to server and select database
     $conn = mysql_connect("localhost", "jason", "secret");

     $db = mysql_select_db("corporate");
 
   // Create and execute query
   $query = "SELECT title, content FROM articles WHERE articleid='$articleid'";
   $result = mysql_query($query);

   // Retrieve query results
   list($title,$content) = mysql_fetch_row($result, 0);

   // Add article title and link to list 
   $articlelink = "<a href='article.php?articleid=$articleid'>$title</a>";
   if (! in_array($articlelink, $_SESSION['articles'])) 
      $_SESSION['articles'][] = "$articlelink";

   // Output list of requested articles
   echo "<p>$title</p><p>$content</p>";
   echo "<p>Recently Viewed Articles</p>";
   echo "<ul>";
   foreach($_SESSION['articles'] as $doc) echo "<li>$doc</li>";
   echo "</ul>";
?>