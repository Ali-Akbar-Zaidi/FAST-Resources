// Map the error numbers to user-friendly messages
$errors = array (
          "1045" => "Unable to authenticate user. Are you sure you 
                     entered the correct username and password?",
          "2005" => "Unable to contact the MySQL server.",
          "2013" => "Lost MySQL connection. Verify the database 
                     name was entered correctly."
                );
// Connect to the MySQL server
$link = @mysqli_connect("127.0.0.1", "siteuser", "secrett", "company");

// If the connection failed, display message
if (!$link) {
   $errornum = mysqli_connect_errorno();
   echo $errors[$errornum];
}