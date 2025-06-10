$query = "SELECT productid, name FROM product ORDER BY name";
$result = mysql_query($query);
while ($row = mysql_fetch_array($result, MYSQL_NUM))
{
    $name = $row[1];
    $productid = $row[0];
    echo "Product:  $name ($productid) <br />";
}