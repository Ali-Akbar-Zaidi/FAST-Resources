<?php

   // Connect to the MySQL database
   $mysqli = new mysqli("localhost", "root", "jason", "helpdesk");

   // Create the query
   $query = "SELECT * FROM employee_contact_info_view";

   // Execute the query
   if ($result = $mysqli->query($query)) {

      echo "<table border='1'>";
      echo "<tr>";

      // Output the headers
      $fields = $result->fetch_fields();
      foreach ($fields as $field)
         echo "<th>".$field->name."</th>";

      echo "</tr>";

      // Output the results
      while ($employee = $result->fetch_row()) {

         $first_name = $employee[0];
         $last_name = $employee[1];
         $email = $employee[2];
         $phone = $employee[3];

         // Format the phone number
         $phone = ereg_replace("([0-9]{3})([0-9]{3})([0-9]{4})", 
                               "(\\1) \\2-\\3", $phone);

         echo "<tr>";
         echo "<td>$first_name</td><td>$last_name</td>";
         echo "<td>$email</td><td>$phone</td>";
         echo "</tr>";

      }

   }
?>