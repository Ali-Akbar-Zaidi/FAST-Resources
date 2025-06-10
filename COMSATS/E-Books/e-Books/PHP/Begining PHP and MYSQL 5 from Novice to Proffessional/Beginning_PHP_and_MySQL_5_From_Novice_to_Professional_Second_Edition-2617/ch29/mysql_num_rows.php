$query = "SELECT name FROM product WHERE price > 15.99";
$result = mysql_query($query);
echo "There are ".mysql_num_rows($result)." product(s) priced above \$15.99.";