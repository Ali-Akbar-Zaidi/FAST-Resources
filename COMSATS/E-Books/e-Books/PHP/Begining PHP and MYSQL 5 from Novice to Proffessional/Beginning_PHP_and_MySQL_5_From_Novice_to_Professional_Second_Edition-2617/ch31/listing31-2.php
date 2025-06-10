<?php

   // Instantiate the mysqli class
   $db = new mysqli("localhost", "root", "jason", "corporate");

   // Assign the employeeID
   $eid = $_POST['employeeid'];

   // Execute the stored procedure
   $result = $db->query("SELECT calculate_bonus('$eid')");

   $row = $result->fetch_row();

   echo "Your bonus is \$".$row[0];
?>