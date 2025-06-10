<?php
   session_start();
   // Has a session been initiated previously?
   if (! isset($_SESSION['name'])) {
      // If no previous session, has the user submitted the form?
      if (isset($_POST['username']))
      {
         $username = $_POST['username'];
         $pswd = $_POST['pswd'];

         // Connect to the PGSQL database
         $conn=pg_connect("host=localhost dbname=corporate
                           user=website password=secret") 
               or die(pg_last_error($conn));

         // Look for the user in the users table.
         $query = "SELECT name FROM users 
                   WHERE username='$username' AND pswd='$pswd'";
         $result = pg_query($conn, $query);
         // If the user was found, assign some session variables.
         if (pg_num_rows($result) == 1)
         {
            $_SESSION['name'] = pg_fetch_result($result,0,'name');
            $_SESSION['username'] = pg_fetch_result($result,0,'username');
            echo "You're logged in. Feel free to return at a later time.";
         }
      // If the user has not previously logged in, show the login form
      } else {
         include "login.html";
      }
   // The user has returned. Offer a welcoming note.
   } else {
       $name = $_SESSION['name'];
       echo "Welcome back, $name!";
    }
?>