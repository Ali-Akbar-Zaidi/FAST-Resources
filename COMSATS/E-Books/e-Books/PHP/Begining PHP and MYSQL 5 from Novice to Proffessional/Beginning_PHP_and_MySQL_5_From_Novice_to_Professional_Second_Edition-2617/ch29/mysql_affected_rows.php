$query = "UPDATE product SET price = '39.99' WHERE price='34.99'";
$result = mysql_query($query);
echo "There were ".mysql_affected_rows()." product(s) affected. ";