<?php

   /*
   Has the user submitted data? 
   If not, display the registration form.
   */
   if (! isset($_POST['submitbutton'])) {
      echo file_get_contents("/templates/registration.html");

   /* Form data has been submitted. */
   } else {

           $conn = mysql_connect("localhost", "jason", "secret");

           $db = mysql_select_db("corporate");

      /* Ensure that the password and password verifier match. */
      if ($_POST['pswd'] != $_POST['pswdagain']) {
         echo "<p>The passwords do not match. Please go back and try again.</p>";
 
         /* Passwords match, attempt to insert information into userauth table. */
         } else {

            try {
               $query = "INSERT INTO userauth (commonname, email, username, pswd)
                         VALUES ('$_POST[name]', '$_POST[email]',  
                         '$_POST[username]', md5('$_POST[pswd]'));

               $result = mysql_query($query);
               if (! $result) {
                  throw new Exception(
                     "Registration problems were encountered!"
                  );
               } else {
                  echo "<p>Registration was successful!</p>";
               }
            } catch(Exception $e) {
               echo "<p>".$e->getMessage()."</p>";
            } #endCatch
      }
   }
?>