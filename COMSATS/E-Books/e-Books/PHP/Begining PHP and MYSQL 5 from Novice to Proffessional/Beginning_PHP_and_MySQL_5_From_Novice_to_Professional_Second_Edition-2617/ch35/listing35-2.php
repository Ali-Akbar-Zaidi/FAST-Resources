<p>
Search the employee database:<br />
<form action="searchextended.php" method="post">
   Keyword:<br />
   <input type="text" name="keyword" size="20" maxlength="40" value="" /><br />
   Field:<br />
   <select name="field">
      <option value="">Choose field:</option>
      <option value="lastname">Last Name</option>
      <option value="email">Email Address</option>
      </select>
   <input type="submit" value="Search!" />
</form>
</p>

<?php
   // If the form has been submitted with a supplied keyword
   if (isset($_POST['field'])) {

      // Connect to server and select database
      $mysqldb = new mysqli("localhost","websiteuser","secret","corporate");

      // Set the posted variables to a convenient names
      $keyword = $mysqldb->mysqli_real_escape_string($_POST['keyword']);
      $field = $mysqldb->mysqli_real_escape_string($_POST['field']);

      // Create the query
      if ($field == "lastname" ) {
         $result = $mysqldb->query("SELECT firstname, lastname, email 
                                    FROM employee WHERE lastname='$keyword'");
      } elseif ($field == "email") {
         $result = $mysqldb->query("SELECT firstname, lastname, email 
                                    FROM employee WHERE email='$keyword'");
      }

      // If records found, output firstname, lastname, and email field
      if ($result->num_rows > 0) {
         while ($row = $result->fetch_object())
            echo "$row->lastname, $row->firstname ($row->email)<br />";
      } else {
         echo "No results found.";
      }
   }
?>