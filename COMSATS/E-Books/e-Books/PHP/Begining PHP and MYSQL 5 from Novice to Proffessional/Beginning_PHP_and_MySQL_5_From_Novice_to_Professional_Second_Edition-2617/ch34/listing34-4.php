$mysqli = new corporate_mysqli("localhost", "root", "jason", "corporate");

$pagesize = 4;

$recordstart = (int) $_GET['recordstart'];

$recordstart = (isset($_GET['recordstart'])) ? $recordstart : 0;

$query = "SELECT orderID AS `Order ID`, clientID AS `Client ID`, 
                    order_time AS `Order Time`, 
                    CONCAT('$', sub_total) AS `Sub Total`, 
                    CONCAT('$', shipping_cost) AS `Shipping Cost`, 
                    CONCAT('$', total_cost) AS `Total Cost`
                    FROM sales ORDER BY orderID LIMIT $recordstart, $pagesize";

$mysqli->tabular_output($query);

// Retrieve total rows in order to determine whether 'next' link should appear

$result = $mysqli->query("select count(clientID) as count FROM sales");

list($totalrows) = $result->fetch_row();

// Create the 'previous' link
if ($recordstart > 0) {
   $prev = $recordstart - $pagesize;
   $url = $_SERVER['PHP_SELF']."?recordstart=$prev";
   echo "<a href=\"$url\">Previous Page</a> ";
}

// Create the 'next' link
if ($totalrows > ($recordstart + $pagesize)) {
   $next = $recordstart + $pagesize;
   $url = $_SERVER['PHP_SELF']."?recordstart=$next";
   echo "<a href=\"$url\">Next Page</a>";
}