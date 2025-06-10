<?php

$query = "SELECT productid, name, price FROM product ORDER BY name";
$result = $mysqli->query($query);
while ($row = $result->fetch_object())
{
    $name = $row->name;
    $productid = $row->productid;
    $price = $row->price;
    echo "($productid) $name: $price <br />";
}

?>