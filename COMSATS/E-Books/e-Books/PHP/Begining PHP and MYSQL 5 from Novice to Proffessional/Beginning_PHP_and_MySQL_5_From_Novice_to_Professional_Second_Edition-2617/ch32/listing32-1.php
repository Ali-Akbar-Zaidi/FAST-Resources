<?php

   // Connect to the MySQL database
   $mysqli = new mysqli("localhost", "root", "jason", "helpdesk");

   // Assign the POSTed values for convenience
   $name = $_POST['name'];
   $email = $_POST['email'];
   $available = $_POST['available'];

   // Note for purposes of this example the technicianID was stored
   // in a hidden variable. In a real-world implementation this
   // information would be stored in a session variable or protected
   // in some other manner.
   $technicianid = $_POST['technicianid'];

   // Create the UPDATE query
   $query = "UPDATE technician SET name='$name', email='$email',   
             available='$available' WHERE technicianID='$technicianid'";

   // Execute query and offer user output.
   if ($mysqli->query($query)) {

      echo "<p>Thank you for updating your profile.</p>";

      if ($available == 0) {
         echo "<p>Because you'll be out of the office, 
               your tickets will be reassigned to another
               technician.</p>";
      }

   } else {
      echo "<p>There was a problem updating your profile.
            Please contact the administrator.</p>";
   }

?>