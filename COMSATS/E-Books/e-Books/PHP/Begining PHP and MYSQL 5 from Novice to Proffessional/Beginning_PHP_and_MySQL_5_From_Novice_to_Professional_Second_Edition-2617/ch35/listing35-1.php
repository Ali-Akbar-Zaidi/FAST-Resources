<p>
Search the employee database:<br />
<form action="simplesearch.php" method="post">
   Last name:<br />
   <input type="text" name="lastname" size="20" maxlength="40" value="" /><br />
   <input type="submit" value="Search!" />
</form>
</p>

<?php

   // If the form has been submitted with a supplied last name
   if (isset($_POST['lastname'])) {

      // Connect to server and select database

      $mysqldb = new mysqli("localhost","websiteuser","secret","corporate");

      // Set the posted variable to a convenient name
      $lastname = mysqli_real_escape_string($_POST['lastname']);

      // Create the query

      $query = "SELECT firstname, lastname, email FROM employee WHERE  
                lastname='$lastname'";

      // Query the employee table
      $result = $mysqldb->query($query);

      // If records found, output firstname, lastname, and email field of each
      if ($result->num_rows > 0) {

         while ($row = $result->fetch_object())
            echo "$row->lastname, $row->firstname ($row->email)<br />";
      } else {
         echo "No results found.";
      }

   }
?>