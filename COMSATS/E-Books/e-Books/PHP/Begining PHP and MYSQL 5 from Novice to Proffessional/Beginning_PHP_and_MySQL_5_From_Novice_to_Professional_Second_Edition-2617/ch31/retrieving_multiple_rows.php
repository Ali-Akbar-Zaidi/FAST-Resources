<?php
  // Instantiate the mysqli class
  $db = new mysqli("localhost", "root", "jason", "corporate");

  // Execute the stored procedure
  $result = $db->query("CALL get_employees()");

  // Loop through the results
  while (list($employee_id, $name, $position) = $result->fetch_row()) {
     echo "$employeeid, $name, $position <br />";
  }

?>