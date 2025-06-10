<?php
     mysql_connect("localhost","webuser","secret");
     $status = explode('  ', mysql_stat());
     foreach($status as $value) echo $value."<br />";
?>