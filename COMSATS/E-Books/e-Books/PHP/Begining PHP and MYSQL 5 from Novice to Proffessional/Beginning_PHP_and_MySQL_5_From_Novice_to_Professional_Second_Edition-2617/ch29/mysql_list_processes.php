<?php
   mysql_connect("localhost","webuser","secret");

   $processes = mysql_list_processes();

   echo "<table>";
   echo "<tr><th>ID</th><th>User</th><th>Host</th><th>DB</th>
         <th>Command</th><th>Time</th><th>State</th><th>Info</th>
         </tr>";

   while ($row = mysql_fetch_row($processes))
   {
      list($id,$user,$host,$db,$command,$time,$state,$info) = $row;
      echo "<tr><td>$id</td><td>$user</td>
            <td>$host</td><td>$db</td><td>$command</td>
            <td>$time</td><td>$state</td><td>$info</td>
            </tr>";
   }

   echo "</table>";
?>
