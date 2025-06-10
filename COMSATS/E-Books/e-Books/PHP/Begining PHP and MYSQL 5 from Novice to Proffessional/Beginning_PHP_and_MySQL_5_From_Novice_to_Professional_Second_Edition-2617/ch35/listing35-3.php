<p>
Search the online resources database:<br />
<form action="fulltextsearch.php" method="post">
   Keywords:<br />
   <input type="text" name="keywords" size="20" maxlength="40" value="" /><br />
   <input type="submit" value="Search!" />
</form>
</p>

<?php

   // If the form has been submitted with supplied keywords
   if (isset($_POST['keywords'])) {

      // Connect to server and select database
      $mysqldb = new mysqli("localhost","root","jason","corporate");

      // Retrieve the search keyword string
      $keywords = $mysqldb->mysqli_real_escape_string($_POST['keywords']);

      // Create the query
      $result = $mysqldb->query("SELECT name, url FROM bookmark
                             WHERE MATCH(description) AGAINST('$keywords')");

      // Output retrieved rows or display appropriate message
      if ($result->num_rows > 0) {
         while ($row = $result->fetch_object())
            echo "<a href=\"$row->url\">$row->name</a><br />";
      } else {
         echo "No results found.";
      }
   }
?>