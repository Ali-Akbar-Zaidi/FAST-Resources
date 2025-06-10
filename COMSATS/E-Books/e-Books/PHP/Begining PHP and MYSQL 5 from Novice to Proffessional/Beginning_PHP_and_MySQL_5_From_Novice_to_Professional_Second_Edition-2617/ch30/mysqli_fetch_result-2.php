<?php

$query = "SELECT productid, name, price FROM product ORDER BY name";
$result = $mysqli->query($query);
while ($row = $result->fetch_array(MYSQLI_NUM))
{
   $productid = $row[0];
   $name = $row[1];
   $price = $row[2];
   echo "($productid) $name: $price <br />";
}

?>