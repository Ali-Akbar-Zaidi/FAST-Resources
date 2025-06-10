<?php

$query = "SELECT productid, name FROM product ORDER BY name";
$result = $mysqli->query($query);
while ($row = $result->fetch_array(MYSQLI_ASSOC))
{
    $name = $row['name'];
    $productid = $row['productid'];
    echo "Product:  $name ($productid) <br />";
}

?>