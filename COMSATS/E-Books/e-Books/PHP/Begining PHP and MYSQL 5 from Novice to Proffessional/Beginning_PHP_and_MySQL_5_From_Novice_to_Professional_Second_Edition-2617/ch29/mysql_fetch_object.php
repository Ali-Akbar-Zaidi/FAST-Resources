$query = "SELECT productid, name FROM product ORDER BY name";
$result = mysql_query($query);
while ($row = mysql_fetch_object($result))
{
    $name = $row->name;
    $productid = $row->productid;
    echo "Product:  $name ($productid) <br />";
}