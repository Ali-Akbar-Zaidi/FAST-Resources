$query = "SELECT sku, name FROM product ORDER BY rowid";
foreach ($dbh->query($query) AS $row) {
   $sku = $row['sku'];
   $name = $row['name'];
   echo "Product: $name ($sku) <br />";
}