<?php
// Create unique identifier
$id = md5(uniqid(rand(),1));

// Set user's uniqueIdentifier field to a unique id.
$query = "UPDATE userauth SET uniqueidentifier='$id' WHERE email=$_POST[email]";
$result = pg_query($query);

$email = <<< email
Dear user,
Click on the following link to reset your password:
http://www.example.com/users/lostpassword.php?id=$id
email;

// Email user password reset options
mail($_POST['email'],"Password recovery","$email","FROM:services@example.com");
echo "<p>Instructions regarding resetting your password have been sent to 
         $_POST[email]</p>";
?>