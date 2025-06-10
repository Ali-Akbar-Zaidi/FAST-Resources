// Connect to the database server
$mysqli = new mysqli("localhost", "siteuser", "secret");

// Select the database
$mysqli->select_db("book") or die("Can't select db!");