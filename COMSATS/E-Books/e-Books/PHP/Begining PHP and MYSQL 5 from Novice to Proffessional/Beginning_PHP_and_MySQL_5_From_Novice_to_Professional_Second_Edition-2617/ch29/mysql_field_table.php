$query = "SELECT productid as Product_ID, name FROM product ORDER BY name";
$result = mysql_query($query);
$row = mysql_fetch_row($result);
echo mysql_field_table($result, 0);