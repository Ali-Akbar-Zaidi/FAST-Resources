<?php
   // Function used to check email syntax
   function validate_email($email) 
   { 
      // Create the syntactical validation regular expression 
      $regexp = "^([_a-z0-9-]+)(\.[_a-z0-9-]+)*@([a-z0-9-]+)
                  (\.[a-z0-9-]+)*(\.[a-z]{2,6})$";

      // Validate the syntax 
      if (eregi($regexp, $email)) return 1;
         else return 0;
   }

   // Has the form been submitted?
   if (isset($_POST['submit']))
   {
      echo "Hi ".$_POST['name']."!<br />";
      if (validate_email($_POST['email']))
         echo "The address ".$_POST['email']." is valid!";
      else
         echo "The address <strong>".$_POST['email']."</strong> is invalid!";
   }
?>

<form action="subscribe.php" method="post">
   <p>
      Name:<br />
      <input type="text" name="name" size="20" maxlength="40" value="" />
   </p>

   <p>
      Email Address:<br />
      <input type="text" name="email" size="20" maxlength="40" value="" />
   </p>

   <input type="submit" name = "submit" value="Go!" />
</form>