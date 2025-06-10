// Execute the query
$query = "SELECT sku, name FROM product ORDER BY name";
$result = $dbh->query($query);

// Fetch the first row first column
$sku = $result->fetchColumn();

// Fetch the second row second column
$name =  $result->fetchColumn(1);

// Output the data.
echo "Product: $name ($sku)";