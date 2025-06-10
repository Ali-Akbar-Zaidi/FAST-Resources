$query = "SELECT description FROM product WHERE productid='tsbxxl'";
$result = mysql_query($query);
$row = mysql_fetch_row($result);

echo mysql_field_len($result, 0);