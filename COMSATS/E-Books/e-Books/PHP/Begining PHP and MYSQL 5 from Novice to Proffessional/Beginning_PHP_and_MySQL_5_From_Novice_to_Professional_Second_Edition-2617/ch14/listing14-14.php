<?php
     // Create a pseudorandom password five characters in length
     $pswd = substr(md5(uniqid(rand(),1),5));
     // Update the user table with the new password.
     $query = "UPDATE userauth SET pswd='$pswd' WHERE uniqueidentifier=$_GET[id]";
     $result = pg_query($query);

     // Display the new password to the user
     echo "<p>Your password has been reset to $pswd. Please log in and change 
              your password to one of your liking.</p>";
?>